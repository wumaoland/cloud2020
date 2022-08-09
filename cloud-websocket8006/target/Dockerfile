FROM java:8
COPY *.jar /app.jar
CMD ["--server.port=8006"]
EXPOSE 8006
ENTRYPOINT ["java","-jar","/app.jar"]