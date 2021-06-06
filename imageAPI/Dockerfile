FROM openjdk:11.0.8-jre
ENV ACTIVEMQ_HOST=activemq
COPY target/imageAPI-*-jar-with-dependencies.jar /imageAPI.jar
CMD java -jar /imageAPI.jar ${ACTIVEMQ_HOST}