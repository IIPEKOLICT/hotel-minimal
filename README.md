_hotel-api_

# Spring boot kotlin backend app for hotel project

Task on subject "Mobile Systems Software" in BSUIR

### Backend tech stack

- Kotlin
- Spring Boot
- Hybernate
- Gradle
- PostgreSQL

### Client tech stack

- Kotlin
- Native android
- Gradle
- Coroutines
- Retrofit
- MVVM

[Spring reference](backend/README.md)

### Environment variables

- `HEROKU_API_KEY` used heroku api key for deploy backend (set in repo secrets)
- `HEROKU_EMAIL` used heroku email for deploy backend (set in repo secrets)
- `HEROKU_APP_NAME` used heroku app name for deploy backend (set in repo secrets)
- `PORT` used port by app
- `SPRING_DATASOURCE_URL` db url string
- `SPRING_DATASOURCE_USERNAME` db username
- `SPRING_DATASOURCE_PASSWORD` db password
- `PGSSLMODE` set to 'no-verify' for Heroku

### Setup database

```shell
psql -U postgres
create database hotel_minimal;
\q
```

### Load project

```shell
git clone git@github.com:BSUIR-IIPEKOLICT/hotel-minimal.git
cd hotel-minimal
```

### Start backend locally (needed 17 Java)

```shell
cd server
./gradlew build
./gradlew bootRun
```

### Start backend on heroku command (needed 17 Java)

```shell
java -Dserver.port=$PORT $JAVA_OPTS -jar build/libs/backend.jar
```

### Build APK and AAB files

```shell
chmod +x ./scripts/build_client.sh
./scripts/build_client.sh $CLIENT_NAME
```
