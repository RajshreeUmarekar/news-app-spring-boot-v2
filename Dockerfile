FROM openjdk:17
EXPOSE 8080
ADD target/news-app-jenkins-docker-backend.jar news-app-jenkins-docker-backend.jar
ENTRYPOINT ["java", "-jar", "/news-app-jenkins-docker-backend.jar"]