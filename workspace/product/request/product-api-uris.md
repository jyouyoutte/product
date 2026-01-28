# Product API URIs

Ce fichier répertorie les URI exposées par le contrôleur `ProductController` et donne des exemples d'appels.

Contexte
- Contrôleur : `com.jyo.product.controller.ProductController`
- Préfixe : `@RequestMapping("/api/v1/products")`
- Le contrôleur expose des endpoints réactifs (Flux/Mono) via Spring WebFlux.

Base URL (local)
- http://localhost:8080

Endpoints CRUD

1) Lister tous les produits
- Méthode : GET
- URI relative : /api/v1/products
- URI complète : http://localhost:8080/api/v1/products
- Description : renvoie un flux (Flux<Product>) de produits.

Exemple curl :

  curl -v -H "Accept: application/json" http://localhost:8080/api/v1/products

2) Récupérer un produit par id
- Méthode : GET
- URI relative : /api/v1/products/{id}
- URI complète : http://localhost:8080/api/v1/products/{id}
- Réponses possibles : 200 OK + Product, 404 Not Found

Exemple curl :

  curl -v -H "Accept: application/json" http://localhost:8080/api/v1/products/60f6a2b3e4f1a2d3e4b56789

3) Créer un produit
- Méthode : POST
- URI relative : /api/v1/products
- URI complète : http://localhost:8080/api/v1/products
- Description : crée un produit. Le serveur ignore l'id envoyé (id réinitialisé à null) et renvoie 201 Created avec l'en-tête Location pointant vers le nouvel objet.

Exemple de corps JSON :

{
  "name": "Chaise",
  "price": 49.99
}

Exemple curl :

  curl -v -X POST -H "Content-Type: application/json" -d '{"name":"Chaise","price":49.99}' http://localhost:8080/api/v1/products

4) Remplacer un produit (idempotent)
- Méthode : PUT
- URI relative : /api/v1/products/{id}
- URI complète : http://localhost:8080/api/v1/products/{id}
- Description : remplace entièrement le produit à l'id donné. Si l'objet n'existe pas, renvoie 404.

Exemple de corps JSON :

{
  "name": "Chaise Confort",
  "price": 59.99
}

Exemple curl :

  curl -v -X PUT -H "Content-Type: application/json" -d '{"name":"Chaise Confort","price":59.99}' http://localhost:8080/api/v1/products/60f6a2b3e4f1a2d3e4b56789

5) Mise à jour partielle (PATCH)
- Méthode : PATCH
- URI relative : /api/v1/products/{id}
- URI complète : http://localhost:8080/api/v1/products/{id}
- Description : met à jour uniquement les champs non-null envoyés dans le corps. Renvoie 200 OK avec le produit mis à jour ou 404 si l'id n'existe pas.

Exemples de corps JSON (seulement le champ à changer) :

- Mettre à jour le nom seulement :
  { "name": "Chaise Luxe" }

- Mettre à jour le prix seulement :
  { "price": 79.90 }

Exemple curl :

  curl -v -X PATCH -H "Content-Type: application/json" -d '{"price":79.90}' http://localhost:8080/api/v1/products/60f6a2b3e4f1a2d3e4b56789

6) Supprimer un produit
- Méthode : DELETE
- URI relative : /api/v1/products/{id}
- URI complète : http://localhost:8080/api/v1/products/{id}
- Description : supprime le produit. Réponses possibles : 204 No Content (succès), 404 Not Found (si l'id n'existe pas).

Exemple curl :

  curl -v -X DELETE http://localhost:8080/api/v1/products/60f6a2b3e4f1a2d3e4b56789

Bonnes pratiques de nommage et conventions appliquées
- Ressource au pluriel : `/products` pour la collection.
- Identifiant en segment : `/products/{id}` pour les opérations sur une ressource.
- Verbes HTTP correctement utilisés : GET (lecture), POST (création), PUT (remplacement idempotent), PATCH (mise à jour partielle), DELETE (suppression).
- Réponses HTTP : 200 OK, 201 Created + Location, 204 No Content, 404 Not Found selon le cas.

Notes
- Si le serveur utilise un port différent, remplacer `8080` par le port correct (voir `application.yaml`).
- Le contrôleur utilise Spring WebFlux (retourne `Flux` / `Mono`). Les clients peuvent consommer en `application/json` ou `application/stream+json` si un stream est souhaité.

Fichier maintenu automatiquement par un assistant — pour toute modification du contrôleur, mettre à jour ce document.
