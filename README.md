
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


# Použité technologie a kde je najít v projektu
- Docker - Pro lokální vývoj jsem používali docker-compose (v rootu projektu)
- Graphql - Obě dvě microservices vystavují pro FE GraphQL API (resources/graphql, graphql package v User a Post)
- REST - Pro komunikaci mezi mikroservisama. (User/rest, Post/rest)
- Azure Blob Storage - použili jsme pro ukládání obrázků, FE dostává pak link na místo v Azure Storage (Post/service/FileService)
- MongoDB - (Post/model, Post/repository)
- Scheduling - Pravidelné mazáni cache. (Post/service/CacheService)
- Hazelcast - Autentikaci v systému provádí User mikroservisa. Pokud chce Post mikroservisa zjistit, kdo posílá dotaz, tak se ji musí zeptat. Výsledek tohoto dotazu jsme se rozhodli zacachovat. (Post/rest/UserMicroserviceImpl, metoda fetchUserFromUserMicroservice)
- Interceptors - Logujem příchozí dotazy (Post/interceptor, User/interceptor)
- OAUth2 - Aplikace využívá google login. (User/security)
- Websocket - Milan dopiš.
- Kafka - Milan dopiš.
- ELK - Milan dopiš

# Co jsme zkusili použít ale nedotáhli do konce, protože se nám to nepovedlo
Původně jsme chtěli jako vstupní bod pro aplikaci použít API Gateway pattern. To se nám ale nakonec nepodařilo a nedodělali jsme to. Chtěli jsme pro to použít Spring Cloud Gateway.

Nejsme si jistí, jestli máme spravně nakonfigurovaný ELK. Milanovi funguje, ostatní si myslíme, že nám se možná chová aplikace divně, protože máme pomalé počítače. (např. nam v lozích vypisuje info o tom, že operace s elasticem zabírají příliš mnoho času)
