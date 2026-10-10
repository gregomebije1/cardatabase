gradlew bootRun


docker compose up -d
docker logs -f dev-postgres
docker exec -it dev-postgres psql -U postgres -d cardabase
docker compose stop
docker compose down n-v


./gradlew test

# Run all tests in a specific class
./gradlew test --tests "com.gregomebije.cardatabase.CarControllerTest"

# Run a single specific test method
./gradlew test --tests "com.gregomebije.cardatabase.CarControllerTest.testGetAllCars"

# Run all tests inside a specific package
./gradlew test --tests "com.gregomebije.cardatabase.service.*"


# Force execution without cleaning the build directory
./gradlew test --rerun

# Alternative: Clean the project cache and then test
./gradlew clean test

# View Test Logs in the Console

./gradlew test --info

# Continue Testing on Failure
./gradlew test --continue
