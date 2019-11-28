# A simple, yet powerful diff generator and viewer utility.

Built with Spring Boot, java-diff-utils and diff2html libraries

### How to Run

- Download the project
- Run `./mvnw package` OR `mvn package`
- Run `java -jar target/diff-v1.jar`  
- Visit http://localhost:8081 in browser

### Change port

- At runtime  `java -jar target/diff-v1.jar --server.port=8084`
- In code: update `server.port` property on application.properties