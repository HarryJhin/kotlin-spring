# :infra:database-test module

`:infra:database-test` 모듈은 통합 테스트에서 외부 DB를 사용하지 않고, 인메모리 h2 DB를 사용하기 위한 모듈입니다.

## 테스트 구성 방법

`/src/main/resources` 디렉토리에 [`application.properties`](./src/main/resources/application.properties) 파일을 생성하고,
아래와 같이 작성합니다.

```properties
spring.datasource.url=jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=POSTGRESQL
spring.datasource.username=tester
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
# SQL 로그 보기
spring.jpa.properties.hibernate.show_sql=true
# SQL 포맷팅
spring.jpa.properties.hibernate.format_sql=true
# SQL 코멘트 보기
spring.jpa.properties.hibernate.use_sql_comments=true
```

이제 DB 기능 테스트를 원하는 모듈의 `build.gradle.kts` 파일에 아래와 같이 의존성을 추가합니다.

```kotlin
dependencies {
    testImplementation(projects.infra.databaseTest)
}
```

JPA 관련 테스트만 진행할 경우 다음과 같이 사용할 수 있습니다.

```kotlin
@DataJpaTest
@EnableJpaAuditing
@EntityScan(basePackageClasses = [MemberEntity::class])
@ContextConfiguration(classes = [MemberRepository::class])
@EnableJpaRepositories(basePackageClasses = [MemberRepository::class])
class MemberRepositoryTest @Autowired constructor(
    private val memberRepository: MemberRepository,
) {
    // ...
}
```

- `@DataJpaTest` 어노테이션은 JPA 관련 테스트를 위한 어노테이션입니다.
- `@EnableJpaAuditing` 어노테이션은 JPA Auditing을 사용하기 위한 어노테이션입니다.
- `@EntityScan` 어노테이션은 엔티티 클래스를 스캔하기 위한 어노테이션입니다.
- `@ContextConfiguration` 어노테이션은 테스트 컨텍스트를 설정하기 위한 어노테이션입니다.
- `@EnableJpaRepositories` 어노테이션은 JPA 리포지토리를 사용하기 위한 어노테이션입니다.
