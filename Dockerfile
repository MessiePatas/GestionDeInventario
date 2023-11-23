FROM eclipse-temurin

COPY /build/libs/Inventario-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "Inventario-0.0.1-SNAPSHOT.jar"]

VOLUME /logs/
