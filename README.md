# Gestion de Pharmacie Simple

**Description** : Une application Java Spring Boot conçue pour gérer efficacement les opérations d'une pharmacie. Elle inclut la gestion des produits, des ventes, des achats, des clients, des fournisseurs, ainsi que des fonctionnalités de recherche, pagination, et filtres avancés.

## Technologies utilisées
- **Java 17**
- **Spring Boot avec Thymeleaf** : Pour le développement côté backend et frontend.
- **Maven** : Gestionnaire de dépendances et de build.
- **PostgreSQL** : Base de données relationnelle.

## Fonctionnalités principales

### Non CRUD :
- Gestion des catégories de données de base telles que :
  - Type de Substance
  - Type d'Agent
  - Agent Pathogène
  - Domaine, Unité, Classe, Type Produit, Forme, Tranche d'Âge, Genre

### CRUD standard avec pagination :
- Substance
  - Agent Substance
- Symptôme
  - Substance Symptôme
- Lot, Fournisseurs, Clients
- Achats et détails d'achat
- Ventes et détails de vente
- Mouvement de Stock et détails des mouvements

### CRUD avec filtre de recherche, pagination, et détails :
- Maladies et Symptômes associés
- Laboratoire et Produits
- Produits avec leurs propriétés :
  - Substances associées
  - Symptômes associés
  - Contre-indications
  - Tranches d'âge
  - Genres

### Autres gestions :
- Filtres par mois et année pour les produits conseillés ou en sélection.
- Liste des clients ayant fait une vente à une date donnée.
- Liste des vendeurs avec leurs commissions sur une période donnée.
- État des commissions selon le genre des vendeurs.

## Installation

### Prérequis
- Java 17
- Maven
- PostgreSQL installé et configuré

### Étapes
1. Clonez le repository :
   ```bash
   git clone <https://github.com/TsantaRandriamihary/PharmaStock/>
   ```
2. Configurez le fichier `application.properties` pour inclure les paramètres PostgreSQL :
   ```env
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/nom_de_la_base
   SPRING_DATASOURCE_USERNAME=votre_nom_utilisateur
   SPRING_DATASOURCE_PASSWORD=votre_mot_de_passe
   ```
3. Installez les dépendances :
   ```bash
   mvn install
   ```
4. Démarrez l'application :
   ```bash
   mvn spring-boot:run
   ```

## Utilisation
1. Accédez à l'application via votre navigateur à l'adresse `http://localhost:8080`.
2. Naviguez entre les différentes sections pour gérer les données.

## Auteur
- **Tsanta**
- Email : tsantarandriamihary@gmail.com

---

 
