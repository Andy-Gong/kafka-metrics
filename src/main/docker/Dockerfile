FROM java:8u72-jdk

VOLUME /tmp

COPY target/kafka-client-metrics-1.0-SNAPSHOT.jar /app.jar
COPY logback.xml /logback.xml

RUN mkdir /clogs

ENTRYPOINT [ "java", "-jar", "app.jar" ]

EXPOSE 8000 8001
