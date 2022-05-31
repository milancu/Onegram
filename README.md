# Lokální vývoj

1. Běž do rootu projektu a spusť
   - S ELK službama: `docker compose up`
   - Bez ELK: `docker compose -f docker-compose-noelastic.yml up`
2. Spusť main User modulu a main Post modulu
3. Při zapnutí se samy nainicializují testovací data
4. Pro obejití přihlášení lze poslat jako JWT token řetězec "admin".


# Použité technologie a kde je najít v projektu

- **Docker** - Pro lokální vývoj jsem používali docker-compose (v rootu projektu)
- **Graphql** - Obě dvě microservices vystavují pro FE GraphQL API (resources/graphql, graphql package v User a Post)
- **REST** - Pro komunikaci mezi mikroservisama. (User/rest, Post/rest)
- **Azure Blob Storage** - použili jsme pro ukládání obrázků, FE dostává pak link na místo v Azure Storage (Post/service/FileService)
- **MongoDB** - (Post/model, Post/repository)
- **Scheduling** - Pravidelné mazáni cache. (Post/service/CacheService)
- **Hazelcast** - Autentikaci v systému provádí User mikroservisa. Pokud chce Post mikroservisa zjistit, kdo posílá dotaz, tak se ji musí zeptat. Výsledek tohoto dotazu jsme se rozhodli zacachovat. (Post/rest/UserMicroserviceImpl, metoda fetchUserFromUserMicroservice)
- **Interceptors** - Logujem příchozí dotazy (Post/interceptor, User/interceptor)
- **OAUth2** - Aplikace využívá google login. (User/security)
- **Websocket** - Milan dopiš.
- **Kafka** - Milan dopiš.
- **ELK** - Milan dopiš

# Použité design patterny
- **Simple factory** - Vytváření objektů pro testování. (User/test/environment/Generator, Post/test/environment/Generator)
- **Builder** - Pomocí @Builder anotace z Lombok. (Post/service/SysteminitializerImpl)
- **Composite** - Model Post mikroservisy je strom (Příspěvek -> komenty -> subkomenty). Pro každý uzel tohoto stromu je potřeba jednotný interface pro vytvoření/smazání liku. (Post/model/interfaces/Likeable).
- **Dependency Injection** - Spring.

# Deployment

User mikroservisu jsme s Postgre nasadili na Heroku. Zbytek včetně FE není nasazený. Po přihlášení lze z URL vytáhnout JWT token a provolat API User mikroservisy. [Odkaz na Heroku](http://nss-onegram.herokuapp.com/).

# Co jsme zkusili použít ale nedotáhli do konce, protože se nám to nepovedlo

Původně jsme chtěli jako vstupní bod pro aplikaci použít API Gateway pattern. To se nám ale nakonec nepodařilo a nedodělali jsme to. Chtěli jsme pro to použít Spring Cloud Gateway.

Nejsme si jistí, jestli máme spravně nakonfigurovaný ELK. Milanovi funguje, ostatní si myslíme, že nám se možná chová aplikace divně, protože máme pomalé počítače. (např. nam v lozích vypisuje info o tom, že operace s elasticem zabírají příliš mnoho času)
