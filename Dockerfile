FROM amazoncorretto:17-alpine AS builder

COPY . /

ARG SERVICE_NAME

RUN ./gradlew clean :app:${SERVICE_NAME}:bootJar

FROM amazoncorretto:17-alpine AS runtime

ARG SERVICE_NAME
ARG PROFILE

COPY --from=builder /app/${SERVICE_NAME}/build/libs/${SERVICE_NAME}-*.jar ${SERVICE_NAME}.jar

ENV SERVICE_NAME=${SERVICE_NAME}
ENV PROFILE=${PROFILE}

ENTRYPOINT ["sh", "-c", "java -jar -Dspring.profiles.active=${PROFILE} ${SERVICE_NAME}.jar"]
EXPOSE 8080