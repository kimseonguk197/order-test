FROM openjdk:11 as stage1

WORKDIR /app

# gradle빌드시에 필요한 파일들을 workdir로 copy
# /app/gradlw파일, gradle폴더, build.gradle파일...
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# /app/build/libs/*.jar 파일 아래 명령어를 통해 생성
RUN chmod 777 ./gradlew
RUN ./gradlew bootJar

FROM openjdk:11

WORKDIR /app

COPY --from=stage1 /app/build/libs/*.jar app.jar

# docker run -d -p 8080:8080 -v C:/Users/Playdata/Desktop/tmp:/tmp order_backend:v1
VOLUME /tmp

# CMD 또는 ENTRYPOINT를 통해 컨테이너 실행
ENTRYPOINT [ "java", "-jar", "app.jar" ]

# docker실행시 db정보를 환경변수로 주입
# docker run -d -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mariadb://host.docker.internal:3306/spring_order -v C:/Users/Playdata/Desktop/tmp:/tmp order_backend:v1