# 使用JDK 17作为基础镜像
FROM openjdk:17-jdk-alpine

# 设置工作目录
WORKDIR /app

# 复制项目的JAR文件到工作目录
COPY grpc-spring-boot-helloworld-0.0.1-SNAPSHOT.jar /app/app.jar

# 暴露应用程序的端口（如果需要）
EXPOSE 8080

# 运行Spring Boot应用程序
ENTRYPOINT ["java", "-jar", "app.jar"]