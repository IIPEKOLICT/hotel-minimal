_hotel-minimal_

# Spring boot + native android app

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

### Repository secrets

- `HEROKU_APP_NAME` name of backend Heroku app
- `HEROKU_API_KEY` used Heroku api key for deploy backend
- `HEROKU_EMAIL` used Heroku email for deploy backend
- `KEYSTORE_GIT_REPOSITORY` name of git repository with keystore for mobile client
- `KEYSTORE_ACCESS_TOKEN` token for get access to keystore repository
- `KEYSTORE_PASSWORD` password of used keystore
- `RELEASE_SIGN_KEY_ALIAS` used alias for sign app using keystore
- `RELEASE_SIGN_KEY_PASSWORD` used password for sign app using keystore

### Environment variables

- `PORT` used port by backend
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
git clone git@github.com:IIPEKOLICT/hotel-minimal.git
cd hotel-minimal
```

### Start backend locally (needed 17 Java)

```shell
cd backend
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
./scripts/build_client.sh $CLIENT_NAME-$GIT_TAG_NAME
```
