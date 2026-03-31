#  Validateur de politiques d’accès

Application Java Spring Boot permettant de gérer, valider et auditer les demandes d’accès à des ressources sensibles selon des règles métier.

---

##  Objectif

Ce projet simule un système utilisé en entreprise pour :
- contrôler les habilitations des utilisateurs
- sécuriser les accès aux applications sensibles
- automatiser et tracer les décisions d’accès

---

##  Fonctionnalités principales

###  Authentification & rôles
- Authentification avec Spring Security
- Gestion des rôles :
  - ADMIN
  - ANALYSTE
- Accès sécurisé selon les permissions

---

###  Workflow métier
- Création de demande d’accès
- Statuts :
  - EN_ATTENTE
  - APPROUVEE
  - REFUSEE
  - A_REVOIR
- Validation automatique et manuelle
- Ajout de commentaires
- Traçabilité du valideur

---

###  Tableau de bord
- Nombre de demandes
- Répartition par statut

---

###  Audit & historique
- Historique complet
- Journal des actions (audit)
- Traçabilité utilisateur + date

---

###  Recherche & filtres
- Filtrage par :
  - département
  - type d’utilisateur
  - application
  - statut
- Pagination

---

###  Export
- Export CSV des données

---

##  API REST

| Méthode | Endpoint | Description |
|--------|--------|-------------|
| GET | /api/health | Vérifier API |
| POST | /api/acces/valider | Validation automatique |
| POST | /api/acces/decider/{id} | Décision manuelle |
| GET | /api/acces/historique | Historique |
| GET | /api/acces/audit | Audit |
| GET | /api/acces/tableau-bord | Statistiques |
| GET | /api/acces/historique/export/csv | Export CSV |

---

## 🏗️ Stack technique

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- Maven

Architecture :
- controller
- service
- repository
- entity

---

##  Tests

- Tests unitaires (services)
- Tests API

---

##  Installation

### 1. Cloner
```bash
git clone https://github.com/darinfengyep/validateur-politiques-acces.git
cd validateur-politiques-acces
