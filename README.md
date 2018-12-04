# E-wallet

## Building the application

### Pre-requisite
* Java 8
* Node 

1. Navigate to the root direcoty `wallet`.
2. On Linux or Mac, run `./gradlw clean build`. For windows `gradlew.bat clean build`. This will build the application.
3. Once the build is done, a uber jar will be created in `build/libs`.

## Running the application
### Pre-requisite
* Postgresql DB(v11)

We can run the application by using `java -jar build/libs/wallet-0.0.1-SNAPSHOT.jar`.

It will start the embedded Tomcat server on port `8080`.

The APIs can be tested using Swagger which is available at `http://localhost:8080/swagger-ui.html`.

## Future Improvements and Known issues

1. The dropdown in the UI is not rendering sometimes on first load. At this time, refreshing page is the solution.
2. The Passbook details are not paginated. We can make it paginated.
3. For now, I have not consider the security (specifically the access control system). We need to implement it.
