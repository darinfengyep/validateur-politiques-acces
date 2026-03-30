# Validateur de politiques d'accès

Application Java Spring Boot de gestion des habilitations avec workflow métier complet.

## Fonctionnalités clés
- création et analyse automatique des demandes d'accès
- statuts métier : `EN_ATTENTE`, `APPROUVEE`, `REFUSEE`, `A_REVOIR`
- validation manuelle par un administrateur
- commentaire de décision et traçabilité du valideur
- historique persistant en base H2
- onglet utilisateurs avec filtres
- tableau de bord simple des demandes
- authentification et rôles `ADMIN` / `ANALYSTE`

## Comptes de démonstration
- `admin` / `admin123`
- `analyste` / `analyste123`

## Lancement
```bash
mvn clean install
mvn spring-boot:run
```

Puis ouvrir :
- `http://localhost:8080/connexion`
- `http://localhost:8080/h2-console`

## Endpoints principaux
- `POST /api/acces/valider`
- `POST /api/acces/decider/{id}`
- `GET /api/acces/historique`
- `GET /api/acces/utilisateurs`
- `GET /api/acces/tableau-bord`
