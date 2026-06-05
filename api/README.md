# Let's Play - RESTful API

API RESTful sécurisée développée avec Spring Boot et MongoDB pour la gestion d'utilisateurs et de produits e-commerce.

## 🧩 Technologies utilisées
* Java avec Spring Boot
* Spring Web pour les endpoints REST
* Spring Data MongoDB pour la base de données
* Spring Security + JWT pour l'authentification et l'autorisation
* BCrypt pour le hachage des mots de passe
* Maven pour la compilation et la gestion des dépendances

## 📁 Structure principale
* `src/main/java` : code source principal
  * `controller` : endpoints REST
  * `service` : logique métier
  * `repository` : accès à MongoDB
  * `model` : entités et DTO
  * `security` : configuration JWT et règles de sécurité
  * `exception` : gestion globale des erreurs
* `src/main/resources` : configuration de l'application

## 🚀 Comment lancer le projet
1. Assure-toi que MongoDB est démarré localement sur le port par défaut : `mongodb://localhost:27017`
2. Ouvre un terminal dans `lets-play/api`
3. Exécute :
   * `./mvnw spring-boot:run` sur Linux/macOS
   * `./mvnw.cmd spring-boot:run` sur Windows
4. L'API démarre sur `http://localhost:8080`

> Un compte administrateur est généré automatiquement au démarrage pour faciliter les tests.

## 🔐 Identifiants par défaut (Admin)
* **Email :** `admin@letsplay.com`
* **Mot de passe :** `Admin123!`

## 📡 Endpoints disponibles
### Authentification
* `POST /auth/register` : créer un nouveau compte (rôle USER par défaut)
* `POST /auth/login` : se connecter et récupérer un token JWT

### Produits
* `GET /products` : lister tous les produits (public)
* `POST /products` : créer un produit (authentification requise)
* `PUT /products/{id}` : modifier un produit (propriétaire ou admin uniquement)
* `DELETE /products/{id}` : supprimer un produit (propriétaire ou admin uniquement)

### Utilisateurs
* `GET /users` : lister tous les utilisateurs (réservé au rôle ADMIN)

## 🛡️ Sécurité et gestion d'erreurs
* JWT pour sécuriser les routes protégées
* Rôles `USER` et `ADMIN` pour contrôler l'accès
* `@ControllerAdvice` global pour renvoyer des réponses JSON cohérentes en cas d'erreur
* Validation des requêtes et gestion centralisée des exceptions

## 💡 Notes importantes
* Les produits sont automatiquement liés à l'utilisateur connecté lors de la création
* Les opérations de mise à jour et de suppression sont vérifiées pour empêcher l'accès non autorisé
* Le projet est extensible pour ajouter facilement de nouveaux endpoints et fonctionnalités
