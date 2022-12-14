FROM openjdk:17-oracle
EXPOSE 8081
ADD build/libs/Conditional_spring_boot_DZ-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","myapp.jar"]