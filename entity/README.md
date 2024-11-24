# :entity 모듈

`:entity` 모듈은 DB ORM을 위한 모듈 집합입니다.

ORM을 사용하기 위해 채택한 라이브러리는 JPA입니다.

코틀린과 JPA를 사용하면 여러 문제가 발생합니다.
이는 기술적 문제가 아닌, 코틀린과 자바의 차이로 인한 문제입니다.

저는 이 문제를 해결하기 위해 다양한 케이스를 구현해보고, 무엇이 최선인지 고민하였습니다.

이 모듈은 이러한 고민의 결과물입니다.

## 구조

```
:entity
├── :core
└── :member
```

- `:entity:core`: 엔티티를 구현하는 모듈이 공통적으로 사용하는 모듈입니다. [더 알아보기](./core/README.md)
- `:entity:member`: 회원 엔티티를 구현하는 모듈입니다. [더 알아보기](./member/README.md)

## 엔티티 설계 원칙

코툴린과 JPA를 사용하면서 가장 큰 문제는 불변입니다.

JPA는 기본적으로 필드를 final로 선언하지 말라고 권장합니다.
하지만 일부 컬럼은 도메인상 불변인데 애플리케이션에서는 어떻게 처리해야 할까요?

```
org.hibernate.HibernateException: Getter methods of lazy classes cannot be final: io.github.harryjhin.entity.core.ReadEntity#getCreatedAt
```
