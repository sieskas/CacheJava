# Image de base pour l'application
FROM openjdk:8

# Copie du fichier JAR de l'application dans l'image
COPY target/ProjectCache-0.0.1-SNAPSHOT.jar /app/ProjectCache-0.0.1-SNAPSHOT.jar

# Définition des variables d'environnement pour l'application
ENV SPRING_PROFILES_ACTIVE=production
ENV SERVER_PORT=8080

# Exposition du port sur lequel l'application doit écouter
EXPOSE 8080

# Définition de la commande d'exécution pour l'application
CMD ["java", "-jar", "/app/ProjectCache-0.0.1-SNAPSHOT.jar"]

#docker build -t my-application:1.0 .

# docker run --ip 192.168.0.2  -p 8080:8080 docker.io/library/my-application:1.0
 #docker run --ip 192.168.0.3 -p 8081:8080 docker.io/library/my-application:1.0