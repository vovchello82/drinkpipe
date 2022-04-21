#Packer
FROM --platform=$BUILDPLATFORM eclipse-temurin:11.0.14.1_1-jdk-focal as builder
USER nobody
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

#Deploy
FROM eclipse-temurin:11.0.14.1_1-jre-focal
EXPOSE 8080
USER nobody
WORKDIR /app
#COPY ssl/ ./
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./
ENTRYPOINT ["java", "-Xms128m", "-Xmx128m", "-Duser.timezone=UTC", "-Djava.security.egd=file:/dev/./urandom", "-Duser.language=DE", "-Dspring.jmx.enabled=false", "org.springframework.boot.loader.JarLauncher"]
