FROM openjdk:17-oracle

# Kopia pliku JAR do kontenera
ADD target/parking-0.0.1-SNAPSHOT.jar .

EXPOSE 8060

# Uruchomienie aplikacji
CMD java -jar -Dspring.profiles.active=prod, parking-0.0.1-SNAPSHOT.jar
