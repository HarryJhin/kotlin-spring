# build-logic Project

`build-logic`은 `kotlin-spring` 프로젝트의 자식 프로젝트로,
`kotlin-spring`의 공통 컨벤션을 정의하고, 빌드 스크립트를 관리하는 프로젝트입니다.

## 시작하기

### build-logic 프로젝트의 역할

`build-logic` 프로젝트는 `kotlin-spring` 프로젝트의 공통 컨벤션을 정의하고, 빌드 스크립트를 관리하는 프로젝트입니다.

### 왜 공통 컨벤션 플러그인이 필요한가?

Spring 멀티 모듈 프로젝트는 여러 모듈이 있을 것이고 그 중 몇몇 모듈은 비슷한 레이어에 속할 것입니다.
이러한 모듈들을 구성할 때 필연적으로 중복되는 설정이 발생합니다.
이러한 중복을 방지하고, 모듈을 구성할 때 일관성을 유지하기 위해 공통 컨벤션 플러그인을 작성하고 적용합니다.

### buildSrc에 작성해도 되는 것 아닌가?

`buildSrc`의 가장 큰 문제는 버전 카탈로그를 사용할 수 없다는 것입니다.
그리고 `buildSrc`에서 외부 플러그인이나 의존성을 추가하는 플러그인을 만들 경우 의존성을 모두 직접 명세해야 합니다.
이러면 공통화의 의미가 없어지기 때문에 `buildSrc`에 작성하지 않고 별도의 프로젝트로 분리합니다.

## 공통 컨벤션 플러그인 작성 및 적용

1. [`settings.gradle.kts`](./settings.gradle.kts) 파일에서 `kotlin-spring` 프로젝트의 [버전 카탈로그](../gradle/libs.versions.toml) 정보를 가져온다.
    ```kotlin
    dependencyResolutionManagement {
        // ...
        versionCatalogs {
            create("libs") {
                from(files("../gradle/libs.versions.toml"))
            }
        }
    }
    
    // ...
    ```
2. [`build.gradle.kts(:build-logic:convention)`](./convention/build.gradle.kts)에서 바이너리 플러그인 작성에 필요한 플러그인 및 의존성 추가
    ```kotlin
    plugins {
        `kotlin-dsl`
    }
    
    dependencies {
        compileOnly(libs.kotlin.gradle.plugin)
    }
    
    kotlin {
        jvmToolchain(17)
    }
    ```
3. `./src/main/kotlin` 디렉토리에서 공통 컨벤션 플러그인 작성 및 [`build.gradle.kts(:build-logic:convention)`](./convention/build.gradle.kts) 파일에 플러그인 선언
    ```kotlin
    gradlePlugin {
        plugins {
            register("kotlin-spring-app") {
                id = "kotlin.spring.app"
                implementationClass = "KotlinSpringAppPlugin"
            }
            register("kotlin-spring-library") {
                id = "kotlin.spring.library"
                implementationClass = "KotlinSpringLibraryPlugin"
            }
            register("kotlin-spring-test") {
                id = "kotlin.spring.test"
                implementationClass = "KotlinSpringTestPlugin"
            }
        }
    }
    ```
4. 선언한 플러그인을 [버전 카탈로그](../gradle/libs.versions.toml)에 추가
    ```toml
    [plugins]
    # Plugins defined by this project
    kotlin-spring-app = { id = "kotlin.spring.app" }
    kotlin-spring-library = { id = "kotlin.spring.library" }
    kotlin-spring-test = { id = "kotlin.spring.test" }
    ```
