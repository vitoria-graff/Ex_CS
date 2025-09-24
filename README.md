# Sistema de Escola (Microserviços Alunos e Disciplinas)

Dois microserviços Spring Boot 3 (Java 21):
- microservico-alunos (porta 8081)
- microservico-disciplinas (porta 8082)

Banco: PostgreSQL (porta 5432) via docker-compose. Testes usam H2 em memória.

## Tecnologias
- Spring Boot (Web, Validation, Data JPA)
- PostgreSQL / H2 (testes)
- OpenAPI + springdoc (Swagger UI)
- Maven
- Docker / Docker Compose

## Executar com Docker
```bash
docker compose up --build
```
Acessos após subir:
- Swagger Alunos: http://localhost:8081/swagger-ui.html
- Swagger Disciplinas: http://localhost:8082/swagger-ui.html
- Docs JSON: /v3/api-docs

Parar:
```bash
docker compose down -v
```

## Executar local (sem Docker)
Em cada microserviço:
```bash
cd microservico-alunos
mvn spring-boot:run
# Em outro terminal
dcd .. && cd microservico-disciplinas
mvn spring-boot:run
```
Necessário PostgreSQL local (ou ajuste para usar H2) e variáveis conforme application.yml / application-docker.yml.

## Endpoints principais
### Alunos (porta 8081)
POST /alunos  (body: {"matricula","nome"})
GET  /alunos?nome=trecho
GET  /alunos/{matricula}

### Disciplinas (porta 8082)
POST /disciplinas (body: {"codigo","nome","horario"})
GET  /disciplinas?nome=trecho&codigo=ABC&horario=A

Ver modelos completos no Swagger UI.

## Testes
```bash
mvn test
```
Relatórios: target/surefire-reports/.

## Build jar
```bash
mvn clean package
```
Artefatos: target/microservico-*-0.0.1-SNAPSHOT.jar

## Geração de código OpenAPI
O plugin openapi-generator roda em generate-sources e gera interfaces (interfaceOnly=true). Definições: src/main/resources/openapi.yaml.

## Estrutura resumida
- microservico-alunos
  - src/main/resources/openapi.yaml
- microservico-disciplinas
  - src/main/resources/openapi.yaml
- docker-compose.yml

## Variáveis relevantes (docker)
SPRING_PROFILES_ACTIVE=docker (usa application-docker.yml para apontar para o Postgres do compose).

## Licença
Uso educacional.
