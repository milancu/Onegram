
# Lokální vývoj

1. Stáhni si [Docker](https://docs.docker.com/desktop/windows/install/)
2. Běž do projektové složky (složky ve které je docker-compose.yml) v terminálu a napiš `docker compose up`
3. pgadmin
  -  localhost:5050
  -  username: admin
  -  password: password
4. mongoexpress
  - localhost:8081
  - username: admin
  - password: password
5. GraphQL playground
  - localhost:9090/playground anebo localhost:1010/playground

# Deploy na heroku
heroku deploy:jar User/target/User-0.0.1-SNAPSHOT.jar --app nss-onegram
