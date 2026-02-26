# 多阶段构建：第一阶段构建 iit 依赖库
# 这个 Dockerfile 需要从项目根目录构建
# 构建命令：docker build -f iit-ws/Dockerfile -t iit-ws .

FROM maven:3.9-eclipse-temurin-21-alpine AS iit-builder
WORKDIR /build

# 复制 iit 项目并安装到本地 Maven 仓库
COPY iit/pom.xml iit/
COPY iit/src iit/src/
RUN cd iit && mvn clean install -DskipTests

# 多阶段构建：第二阶段构建 iit-ws
FROM maven:3.9-eclipse-temurin-21-alpine AS ws-builder
WORKDIR /build

# 复制本地 Maven 仓库（包含 iit 依赖）
COPY --from=iit-builder /root/.m2 /root/.m2

# 复制 iit-ws 项目文件
COPY iit-ws/pom.xml .
COPY iit-ws/src ./src/
COPY iit-ws/mvnw .
COPY iit-ws/.mvn ./.mvn/

# 构建项目
RUN mvn clean package -DskipTests

# 运行阶段：使用轻量级 JRE 镜像
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# 从构建阶段复制生成的 jar 文件
COPY --from=ws-builder /build/target/*.jar app.jar

# 暴露端口
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]
