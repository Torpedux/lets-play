# Let's Play - RESTful API

Cette application est une API RESTful sécurisée développée avec Spring Boot et MongoDB, permettant la gestion d'utilisateurs et de produits e-commerce.

## 🚀 Lancement du projet
1. Assurez-vous d'avoir **MongoDB** en cours d'exécution sur le port par défaut (`mongodb://localhost:27017`).
2. Lancez l'application via votre IDE ou avec Maven : `./mvnw spring-boot:run`
3. Un utilisateur Administrateur est automatiquement généré au démarrage pour faciliter les tests.

## 🔐 Identifiants par défaut (Admin)
* **Email :** admin@letsplay.com
* **Mot de passe :** Admin123!

## 📡 Endpoints de l'API

### Authentification (Public)
* `POST /auth/register` : Créer un nouveau compte (Le rôle par défaut est USER).
* `POST /auth/login` : Se connecter et récupérer le token JWT.

### Produits
* `GET /products` : Voir tous les produits (Public).
* `POST /products` : Créer un produit (Authentification requise. Assigne automatiquement l'ID de l'utilisateur connecté).
* `PUT /products/{id}` : Modifier un produit (Réservé au propriétaire du produit ou à un Admin).
* `DELETE /products/{id}` : Supprimer un produit (Réservé au propriétaire ou à un Admin).

### Utilisateurs
* `GET /users` : Voir la liste des utilisateurs (Réservé au rôle Admin).

## 🛠️ Architecture
L'API respecte les standards REST et utilise un `@ControllerAdvice` global pour intercepter les exceptions et garantir que l'application ne renvoie jamais d'erreurs 500 brutes, mais des réponses JSON formatées (400, 401, 403, 404). Les mots de passe sont hachés avec BCrypt.