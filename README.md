# Kotlin Spring Boot 애플리케이션

`kotlin-spring` 리포지토리는 'Spring Boot'에서 사용하는 주요 기능을 연습하기 위한 프로젝트입니다.

## 기술 스택

- Spring Boot
- PostgreSQL
- Redis
- Elasticsearch
- Logstash
- Kibana
- Filebeat
- Docker

## 프로젝트 모듈 구조

```
kotlin-spring
├── app
│   ├── api
│   ├── batch
│   └── cms
├── build-logic
│   └── convention
├── domain
│   ├── member
│   └── security
├── infra
│   ├── database
│   ├── elasticsearch
│   └── redis
├── model
│   └── member
├── .gitignore
├── build.gradle.kts
├── docker-compose.yml
├── README.md
└── settings.gradle.kts
```

- [`app`](./app/README.md): Spring Boot 애플리케이션 모듈 디렉토리
- [`build-logic`](./build-logic/README.md): 공통 컨벤션 및 빌드 스크립트 관리 프로젝트
- [`domain`](./domain/README.md): 도메인 모델 디렉토리
- [`infra`](./infra/README.md): 데이터베이스, Elasticsearch, Redis 설정 디렉토리
- [`model`](./model/README.md): 도메인 모델 디렉토리
