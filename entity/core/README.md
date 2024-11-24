# :entity:core 모듈

`:entity:core` 모듈은 엔티티를 구현하는 모듈이 공통적으로 사용하는 모듈입니다.
예를 들어, 생성일시와 생성자는 모든 엔티티에 필요한 속성입니다.
이것을 표현하면 다음과 같습니다.

[`ReadEntity.kt`](./src/main/kotlin/io/github/harryjhin/entity/core/ReadEntity.kt):

```kotlin
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ReadEntity {

    @[Column(name = "created_at") CreatedDate]
    var createdAt: LocalDateTime? = null
        protected set

    @[Column(name = "created_by") CreatedBy]
    var createdBy: Long? = null
        protected set
}
```

- `MappedSuperclass`: JPA에서 상속을 표현하기 위한 어노테이션입니다. 이 어노테이션을 사용하면 해당 클래스는 테이블로 생성되지 않습니다.
- `EntityListeners`: JPA에서 엔티티의 이벤트를 처리하기 위한 어노테이션입니다. 이 어노테이션을 사용하면 해당 클래스의 이벤트를 처리할 수 있습니다.
- `AuditingEntityListener`: JPA에서 제공하는 엔티티 리스너입니다. 이 리스너를 사용하면 엔티티의 생성일시와 생성자를 자동으로 처리할 수 있습니다.
- `CreatedDate`: JPA에서 제공하는 어노테이션입니다. 이 어노테이션을 사용하면 해당 필드가 생성일시를 나타낸다는 것을 표현할 수 있습니다.
- `CreatedBy`: JPA에서 제공하는 어노테이션입니다. 이 어노테이션을 사용하면 해당 필드가 생성자를 나타낸다는 것을 표현할 수 있습니다.

이제 `ReadEntity` 클래스를 상속받아 엔티티를 구현하면, 생성일시와 생성자를 자동으로 처리할 수 있습니다.
