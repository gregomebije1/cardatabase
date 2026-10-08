gradlew bootRun


docker compose up -d
docker logs -f dev-postgres
docker exec -it dev-postgres psql -U postgres -d cardabase
docker compose stop
docker compose down n-v