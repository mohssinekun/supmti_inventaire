# Introduction

Le système de gestion d'inventaire est conçu pour permettre aux entreprises de gérer efficacement leurs produits en stock.
Le projet consiste à développer un système de gestion d'inventaire permettant aux utilisateurs distants d'accéder aux fonctionnalités principales de gestion des produits via une application Java utilisant RMI (Remote Method Invocation). L'objectif est d'assurer un accès à distance aux données d'inventaire, tout en permettant des opérations CRUD (Create, Read, Update, Delete) sur les produits.

# Pré-requis

Pour exécuter ce projet, vous devez installer et configurer les outils suivants :

## 1. Java
- **Version requise :** Java 23
  - Assurez-vous que Java Development Kit (JDK) 23 est installé.
  - Vérifiez la version installée avec la commande suivante :
    ```bash
    java -version
    ```
  - Si vous n'avez pas encore installé Java, téléchargez-le depuis [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/).
 
  ## 2. MySQL
- **Version requise :** MySQL 8.0.26
  - Installez MySQL Server et MySQL Workbench si nécessaire.
  - Vérifiez la version installée avec la commande suivante :
    ```bash
    mysql --version
    ```
  - Téléchargez MySQL depuis [MySQL Downloads](https://dev.mysql.com/downloads/).

  ## 3. Configuration de l'environnement
- Ajoutez Java et MySQL à votre **PATH** si ce n'est pas déjà fait :
  - **Java :** Assurez-vous que le dossier `bin` de Java est ajouté au PATH.
  - **MySQL :** Vérifiez que `mysql` peut être exécuté depuis la ligne de commande.

### 1. Base de données MySQL :
- Créez une base de données avec le nom requis par l'application (`inventaire`).
- Les scripts SQL seront automatiquement initialiser des l'application start.