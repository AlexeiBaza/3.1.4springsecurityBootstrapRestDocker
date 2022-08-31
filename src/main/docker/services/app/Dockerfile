FROM adoptopenjdk:11-jre-hotspot
WORKDIR /usr/local/app
ADD springbootdemo-0.0.1-SNAPSHOT.jar app.jar
CMD java -jar ./app.jar

#FROM adoptopenjdk:11-jre-hotspot
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} application.jar
#ENTRYPOINT ["java", "-jar", "application.jar"]

