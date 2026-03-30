# Validateur de politiques d'accès

Projet Java Spring Boot permettant de valider des demandes d'habilitation selon des règles métier simples.

## Fonctionnalités
- Validation d'une demande d'accès via API REST
- Décision automatique : `APPROUVEE`, `REJETEE`, `EN_ATTENTE_VALIDATION`
- Interface web simple pour tester l'application
- Tests unitaires et tests web

## Stack technique
- Java 17
- Spring Boot 3
- Maven
- JUnit 5
- HTML / CSS / JavaScript

## Endpoints
- `GET /api/sante`
- `GET /api/acces/regles`
- `POST /api/acces/valider`

## Lancer le projet
```bash
mvn clean install
mvn spring-boot:run
```

Puis ouvrir :
```text
http://localhost:8080
```

## Mise sur GitHub
```bash
git init
git add .
git commit -m "Initialisation du projet validateur de politiques d'accès"
git branch -M main
git remote add origin <URL_DU_REPO>
git push -u origin main
```
