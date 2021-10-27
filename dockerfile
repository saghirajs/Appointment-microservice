From java:8
Expose 8090
ADD target/appointment-0.0.1-SNAPSHOT.jar appointment.jar
ENTRYPOINT [ "java", "-jar", "appointment.jar"]