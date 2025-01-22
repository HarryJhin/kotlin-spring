package io.github.harryjhin.domain.core

import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.support.Querydsl
import org.springframework.data.querydsl.SimpleEntityPathResolver
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
abstract class QuerydslRepositorySupport(
    private val domainClass: Class<*>,
) {

    @[Autowired PersistenceContext]
    private lateinit var _entityManager: EntityManager

    val entityManager: EntityManager by lazy { _entityManager }

    @Autowired
    private lateinit var _jpaQueryFactory: JPAQueryFactory

    val jpaQueryFactory: JPAQueryFactory by lazy { _jpaQueryFactory }

    private val path: EntityPath<*> by lazy {
        SimpleEntityPathResolver.INSTANCE.createPath(domainClass)
    }

    private val builder: PathBuilder<*> by lazy {
        PathBuilder(path.type, path.metadata)
    }

    private val querydsl: Querydsl by lazy {
        Querydsl(entityManager, builder)
    }

    /**
     * ### 슬라이싱
     *
     * 하나의 쿼리로 슬라이싱하는 함수입니다.
     *
     * @param contentQuery 콘텐츠를 가져오는 쿼리
     * @param pageable 페이지네이션 정보
     */
    protected fun <S> slicing(
        contentQuery: JPAQuery<S>,
        pageable: Pageable,
    ): Slice<S> {
        val content: List<S> = contentQuery.execute(pageable)
        return SliceImpl(content, pageable, content.size < pageable.pageSize)
    }

    /**
     * ### 페이지네이션
     *
     * 하나의 쿼리로 페이지네이션하는 함수입니다.
     * `count` 쿼리 최적화가 필요하다면 `paging(#1, #2, #3)`을 사용하세요.
     *
     * **warning**: 외부 조인(outer join)을 하는 쿼리는 이 함수를 사용하지 마세요.
     *
     * @param contentQuery 콘텐츠를 가져오는 쿼리
     * @param pageable 페이지네이션 정보
     */
    protected fun <S> paging(
        contentQuery: JPAQuery<S>,
        pageable: Pageable,
    ): Page<S> {
        val content: List<S> = contentQuery.execute(pageable)
        val total: Long = contentQuery.fetch().size.toLong()
        return PageImpl(content, pageable, total)
    }

    /**
     * ### 페이지네이션
     *
     * 두 개의 쿼리로 페이지네이션하는 함수입니다.
     * `count` 쿼리 최적화가 필요하다면 이 함수를 사용하세요.
     *
     * @param contentQuery 콘텐츠를 가져오는 쿼리
     * @param countQuery 콘텐츠의 총 개수를 가져오는 쿼리
     * @param pageable 페이지네이션 정보
     */
    protected fun <S> paging(
        contentQuery: JPAQuery<S>,
        countQuery: JPAQuery<Long>,
        pageable: Pageable,
    ): Page<S> {
        val content: List<S> = contentQuery.execute(pageable)
        val total: Long = countQuery.execute()
        return PageImpl(content, pageable, total)
    }

    private fun <S> JPAQuery<S>.execute(pageable: Pageable): List<S> {
        return this
            .order(pageable.sort)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
    }

    private fun JPAQuery<Long>.execute(): Long {
        if (this.metadata.groupBy.isEmpty())
            return this.fetchOne() ?: 0L
        return this.fetch().size.toLong()
    }

    fun <S> JPAQuery<S>.order(sort: Sort): JPAQuery<S> {
        if (this.metadata.orderBy.isNotEmpty()) {
            return this
        }
        querydsl.applySorting(sort, this)
        return this
    }
}