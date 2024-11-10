# 📚 Gestion de Formation Professionnelle

API REST pour la gestion d'une plateforme de formation professionnelle, développée avec Spring Boot. Ce projet digitalise la gestion des formations, apprenants, formateurs, et sessions de formation pour un centre de formation, offrant des fonctionnalités CRUD complètes et sécurisées.

## 📋 Table des matières
- [Contexte](#contexte)
- [Fonctionnalités](#fonctionnalités)
- [Architecture](#architecture)
- [Technologies et Outils](#technologies-et-outils)
- [Installation et Configuration](#installation-et-configuration)
- [Utilisation](#utilisation)
- [Exigences Techniques](#exigences-techniques)


---

## 📜 Contexte

Les entreprises modernes nécessitent des **APIs REST robustes et évolutives** pour intégrer leurs systèmes internes et échanger avec des applications tierces. Ce projet répond aux besoins d'un centre de formation pour gérer numériquement les informations et processus associés aux **formations, apprenants, formateurs, et sessions**.

---

## ✨ Fonctionnalités

- **Gestion des Apprenants** : inscription, modification, suppression, consultation d'un ou plusieurs apprenants.
- **Gestion des Formateurs** : création, modification, suppression, consultation d'un ou plusieurs formateurs.
- **Gestion des Formations** : planification, modification, suppression, consultation d'une ou plusieurs formations.
- **Gestion des Classes** : création, modification, suppression, consultation d'une ou plusieurs classes.

**Structure des Entités :**
- **Apprenant** : nom, prénom, email, niveau.
- **Formateur** : nom, prénom, email, spécialité.
- **Classe** : nom, numSalle.
- **Formation** : titre, niveau, prerequis, capaciteMin, capaciteMax, dateDebut, dateFin, apprenants, statut (PLANIFIÉE, EN_COURS, TERMINÉE, ANNULÉE).

---

## 🏗️ Architecture

Cette application suit une architecture **multi-couches** :
1. **Entités** : Modèles de données avec annotations JPA et validation.
2. **Repositories** : Interfaces étendant `JpaRepository` pour l'interaction avec la base de données.
3. **Services** : Logique métier.
4. **Contrôleurs** : API REST pour manipuler les données via des endpoints.
5. **Exceptions** : Gestion des erreurs et exceptions.
6. **Tests** : Tests unitaires et d'intégration.

---

## 🛠️ Technologies et Outils

- **Backend** : Java 8, Spring Boot, Spring Data JPA, Spring Validation.
- **Base de données** :
  - H2 (environnement de développement)
  - PostgreSQL (environnement de production)
- **Outils de documentation** : Swagger
- **Gestion de projet** : JIRA, Git, Maven, Lombok, Spring Boot DevTools
- **Tests et couverture** : JUnit, Mockito, JaCoCo, SonarLint

---

## ⚙️ Installation et Configuration

### Prérequis
- Java 8+
- Maven
- PostgreSQL (pour l'environnement de production)

### Étapes d'installation
1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/asmaabarj/Gestion_de_Formations.git
   cd Gestion_de_Formations

### 2. Configurer la base de données 🛢️
   - **Profil de développement** : H2 (configuration par défaut pour les tests rapides).
   - **Profil de production** : Configurez `application-prod.properties` pour utiliser PostgreSQL avec les paramètres de connexion appropriés.

3. **Lancer l'application 🚀** :
   - **Développement** :
     ```bash
     mvn spring-boot:run -Dspring-boot.run.profiles=dev
     ```
   - **Production** :
     ```bash
     mvn spring-boot:run -Dspring-boot.run.profiles=prod
     ```

---

## 📐 Utilisation

### Accéder aux Endpoints 🌐
Les principales routes disponibles sont :

- **Apprenants** :
  - `GET /apprenants` : Liste des apprenants
  - `POST /apprenants` : Ajouter un apprenant
  - `PUT /apprenants/{id}` : Modifier un apprenant
  - `DELETE /apprenants/{id}` : Supprimer un apprenant

- **Formateurs** :
  - `GET /formateurs` : Liste des formateurs
  - `POST /formateurs` : Ajouter un formateur
  - `PUT /formateurs/{id}` : Modifier un formateur
  - `DELETE /formateurs/{id}` : Supprimer un formateur

- **Formations** :
  - `GET /formations` : Liste des formations
  - `POST /formations` : Ajouter une formation
  - `PUT /formations/{id}` : Modifier une formation
  - `DELETE /formations/{id}` : Supprimer une formation

- **Classes** :
  - `GET /classes` : Liste des classes
  - `POST /classes` : Ajouter une classe
  - `PUT /classes/{id}` : Modifier une classe
  - `DELETE /classes/{id}` : Supprimer une classe

### Tester l'API 🧪
Utilisez **Postman** ou un outil similaire pour tester les endpoints. Une documentation Swagger est également disponible et accessible via `/swagger-ui.html`.
http://localhost:8081/swagger-ui/index.html#/
---

## 📝 Exigences Techniques

- **IoC et DI** : Gestion via annotations Spring (aucun fichier XML).
- **Validation** : Utilisation des annotations `@Valid`, `@NotNull`, etc.
- **Design Patterns** : Application des patrons Repository et Singleton.
- **Logging** : Système de logging intégré avec LOGGER.
- **Fonctionnalités Java 8** : Utilisation des `Stream`, `Lambda`, `Optional`, `Java Time API`.

### Bonnes Pratiques REST 🚦
- Organisation des endpoints CRUD avec des méthodes HTTP (GET, POST, PUT, DELETE).
- Codes HTTP appropriés (200 pour OK, 201 pour création, 404 pour non trouvé, 500 pour erreur serveur).

### planification 🕰️
https://asmaabarj5.atlassian.net/jira/software/projects/GDF/boards/232
---

