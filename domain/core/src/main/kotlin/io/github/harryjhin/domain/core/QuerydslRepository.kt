package io.github.harryjhin.domain.core

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Query
import jakarta.persistence.TypedQuery
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.ParameterExpression
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import java.util.function.BiConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.provider.PersistenceProvider
import org.springframework.data.jpa.repository.query.EscapeCharacter
import org.springframework.data.jpa.repository.query.QueryUtils
import org.springframework.data.jpa.repository.support.CrudMethodMetadata
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport
import org.springframework.data.jpa.repository.support.JpaRepositoryConfigurationAware
import org.springframework.data.projection.ProjectionFactory
import org.springframework.data.projection.SpelAwareProxyProjectionFactory
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.util.ProxyUtils
import org.springframework.transaction.annotation.Transactional

@Transactional
@NoRepositoryBean
abstract class QuerydslRepository<T : Any, ID>(
    private val domainClass: Class<T>,
) : JpaRepositoryConfigurationAware {

    @[Autowired PersistenceContext]
    private lateinit var entityManager: EntityManager

    @Autowired
    protected lateinit var jpaQueryFactory: JPAQueryFactory

    private val provider: PersistenceProvider by lazy {
        PersistenceProvider.fromEntityManager(entityManager)
    }

    @Suppress("UNCHECKED_CAST")
    private val entityInformation: JpaEntityInformation<T, ID> by lazy {
        JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager)
                as JpaEntityInformation<T, ID>
    }

    private var metadata: CrudMethodMetadata? = null
    private var projectionFactory: ProjectionFactory? = SpelAwareProxyProjectionFactory()
    private var escapeCharacter: EscapeCharacter = EscapeCharacter.DEFAULT

    override fun setRepositoryMethodMetadata(metadata: CrudMethodMetadata) {
        this.metadata = metadata
    }

    override fun setProjectionFactory(projectionFactory: ProjectionFactory) {
        this.projectionFactory = projectionFactory
    }

    override fun setEscapeCharacter(escapeCharacter: EscapeCharacter) {
        this.escapeCharacter = escapeCharacter
    }

    private fun getCountQueryString(): String {
        val countQuery = String.format(QueryUtils.COUNT_QUERY_STRING, provider.countQueryPlaceholder, "%s")
        return QueryUtils.getQueryString(countQuery, entityInformation.entityName)
    }

    fun deleteById(id: ID) {
        findById(id)?.run(::delete)
    }

    fun delete(entity: T) {
        if (entityInformation.isNew(entity)) {
            return
        }

        if (entityManager.contains(entity)) {
            entityManager.remove(entity)
            return
        }

        // if the entity to be deleted doesn't exist, delete is a NOOP
        ProxyUtils.getUserClass(entity)
            .let { type -> entityManager.find(type, entityInformation.getId(entity)) }
            ?.run(entityManager::remove)
    }

    fun deleteAllById(ids: Iterable<ID>) {
        ids.forEach(::deleteById)
    }

    fun deleteAll(entities: Iterable<T>) {
        entities.forEach(::delete)
    }

    fun deleteAll() {
        findAll().forEach(::delete)
    }

    fun deleteAll(entities: List<T>) {
        entities.forEach(::delete)
    }

    fun findById(id: ID): T? {
        return entityManager.find(domainClass, id)
    }

    fun findAll(): List<T> {
        return getQuery(null, Sort.unsorted()).resultList
    }

    fun findAllById(ids: Iterable<ID>): List<T> {
        if (!ids.iterator().hasNext()) {
            return emptyList()
        }

        if (entityInformation.hasCompositeId()) {
            return ids.mapNotNull(::findById)
        }

        val specification: ByIdsSpecification<T> = ByIdsSpecification(entityInformation)
        val query: TypedQuery<T> = getQuery(specification, Sort.unsorted())

        return query.setParameter(specification.parameter, ids.toCollection()).resultList
    }

    fun count(): Long {
        return entityManager.createQuery(getCountQueryString(), Long::class.java)
            .singleResult
    }

    fun <S : T> save(entity: S): S {
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity)
            return entity
        } else {
            throw UnsupportedOperationException("entityManager.merge(entity) is not supported.")
        }
    }

    fun <S : T> saveAndFlush(entity: S): S {
        val result: S = save(entity)
        flush()
        return result
    }

    fun <S : T> saveAll(entities: Iterable<S>): List<S> {
        return entities.map(::save)
    }

    fun <S : T> saveAllAndFlush(entities: Iterable<S>): List<S> {
        val result: List<S> = saveAll(entities)
        flush()
        return result
    }

    fun flush() {
        entityManager.flush()
    }

    protected fun getQuery(spec: Specification<T>?, sort: Sort): TypedQuery<T> {
        return getQuery(spec, domainClass, sort)
    }

    protected fun getQuery(spec: Specification<T>?, domainClass: Class<T>, sort: Sort): TypedQuery<T> {
        val builder: CriteriaBuilder = entityManager.criteriaBuilder
        val query: CriteriaQuery<T> = builder.createQuery(domainClass)

        val root: Root<T> = applySpecificationToCriteria(spec, domainClass, query)
        query.select(root)

        if (sort.isSorted) {
            query.orderBy(QueryUtils.toOrders(sort, root, builder))
        }

        return applyRepositoryMethodMetadata(entityManager.createQuery(query))
    }

    private fun applySpecificationToCriteria(
        spec: Specification<T>?,
        domainClass: Class<T>,
        query: CriteriaQuery<*>
    ): Root<T> {
        val root: Root<T> = query.from(domainClass)

        if (spec == null) {
            return root
        }

        val builder: CriteriaBuilder = entityManager.criteriaBuilder
        val predicate: Predicate? = spec.toPredicate(root, query, builder)

        if (predicate != null) {
            query.where(predicate)
        }

        return root
    }

    private fun applyRepositoryMethodMetadata(query: TypedQuery<T>): TypedQuery<T> {
        return query
    }

    private fun <S> applyRepositoryMethodMetadataForCount(query: TypedQuery<S>): TypedQuery<S> {
        applyQueryHintsForCount(query)

        return query
    }

    private fun applyQueryHintsForCount(query: Query) {
        if (metadata == null) {
            return
        }

        applyComment(query::setHint)
    }

    private fun applyComment(consumer: BiConsumer<String?, Any?>) {
        if (metadata?.comment != null && provider.commentHintKey != null) {
            consumer.accept(provider.commentHintKey, provider.getCommentHintValue(this.metadata!!.comment!!))
        }
    }

    companion object {
        fun <ID> Iterable<ID>.toCollection(): Collection<ID> {
            return when (this) {
                is Collection<ID> -> this
                else -> this.toList()
            }
        }
    }

    private class ByIdsSpecification<T>(
        private val entityInformation: JpaEntityInformation<T, *>
    ) : Specification<T> {

        var parameter: ParameterExpression<Collection<*>>? = null

        override fun toPredicate(
            root: Root<T>,
            query: CriteriaQuery<*>?,
            criteriaBuilder: CriteriaBuilder
        ): Predicate {
            val path: Path<out Any> = root[entityInformation.idAttribute]
            parameter = criteriaBuilder.parameter(Collection::class.java) as ParameterExpression<Collection<*>>
            return path.`in`(parameter)
        }
    }
}