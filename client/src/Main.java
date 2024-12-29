import modeles.Categorie;
import modeles.Produit;
import services.CategorieService;
import services.ProduitService;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProduitService produitService;
    private static CategorieService categorieService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            produitService = (ProduitService) registry.lookup("ProduitService");
            categorieService = (CategorieService) registry.lookup("CategorieService");

            menuPrincipal();
        } catch (Exception e) {
            System.err.println("Erreur de connexion: " + e.toString());
            e.printStackTrace();
        }
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Gestion des Produits");
            System.out.println("2. Gestion des Catégories");
            System.out.println("0. Quitter");
            System.out.print("Votre choix: ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Vider le buffer

            switch (choix) {
                case 1: gestionProduits(); break;
                case 2: gestionCategories(); break;
                case 0: System.exit(0);
                default: System.out.println("Choix invalide!");
            }
        }
    }

    private static void gestionProduits() {
        while (true) {
            System.out.println("\n=== GESTION DES PRODUITS ===");
            System.out.println("1. Afficher tous les produits");
            System.out.println("2. Rechercher un produit par nom");
            System.out.println("3. Ajouter un nouveau produit");
            System.out.println("4. Modifier un produit");
            System.out.println("5. Supprimer un produit");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Vider le buffer

            try {
                switch (choix) {
                    case 1: afficherTousProduits(); break;
                    case 2: rechercherProduitParNom(); break;
                    case 3: ajouterProduit(); break;
                    case 4: modifierProduit(); break;
                    case 5: supprimerProduit(); break;
                    case 0: return;
                    default: System.out.println("Choix invalide!");
                }
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            }
        }
    }

    private static void gestionCategories() {
        while (true) {
            System.out.println("\n=== GESTION DES CATÉGORIES ===");
            System.out.println("1. Afficher toutes les catégories");
            System.out.println("2. Ajouter une nouvelle catégorie");
            System.out.println("3. Modifier une catégorie");
            System.out.println("4. Supprimer une catégorie");
            System.out.println("0. Retour au menu principal");
            System.out.print("Votre choix: ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Vider le buffer

            try {
                switch (choix) {
                    case 1: afficherToutesCategories(); break;
                    case 2: ajouterCategorie(); break;
                    case 3: modifierCategorie(); break;
                    case 4: supprimerCategorie(); break;
                    case 0: return;
                    default: System.out.println("Choix invalide!");
                }
            } catch (Exception e) {
                System.err.println("Erreur: " + e.getMessage());
            }
        }
    }

    // Méthodes pour la gestion des produits
    private static void afficherTousProduits() throws Exception {
        List<Produit> produits = produitService.getAllProduits();

        System.out.println("\n=== LISTE DES PRODUITS ===");
        for (Produit p : produits) {
            Categorie categorie = categorieService.getCategorieById(p.getCategorieId());
            System.out.println("ID: " + p.getId() +
                    " | Nom: " + p.getNom() +
                    " | Prix: " + p.getPrixUnitaire() +
                    " | Catégorie: " + (p.getCategorieId() != null ? categorie.getLibelle() : "N/A"));
        }
    }

    private static void rechercherProduitParNom() throws Exception {
        System.out.print("Entrez le nom à rechercher: ");
        String nom = scanner.nextLine();
        List<Produit> produits = produitService.searchProduitsByName(nom);

        if (produits.isEmpty()) {
            System.out.println("Aucun produit trouvé.");
            return;
        }

        System.out.println("\n=== RÉSULTATS DE LA RECHERCHE ===");
        for (Produit p : produits) {
            Categorie categorie = categorieService.getCategorieById(p.getCategorieId());
            System.out.println("ID: " + p.getId() +
                    " | Nom: " + p.getNom() +
                    " | Prix: " + p.getPrixUnitaire() +
                    " | Catégorie: " + (p.getCategorieId() != null ? categorie.getLibelle() : "N/A"));
        }
    }

    private static void ajouterProduit() throws Exception {
        Produit p = new Produit();
        System.out.print("Nom du produit: ");
        p.setNom(scanner.nextLine());
        System.out.print("Prix du produit: ");
        p.setPrixUnitaire(scanner.nextBigDecimal());
        scanner.nextLine();

        System.out.print("Quantite du produit: ");
        p.setQuantite(scanner.nextInt());
        scanner.nextLine();

        // Afficher les catégories disponibles
        List<Categorie> categories = categorieService.getAllCategories();
        System.out.println("Catégories disponibles:");
        for (Categorie c : categories) {
            System.out.println(c.getId() + " - " + c.getLibelle());
        }
        System.out.print("ID de la catégorie: ");
        Long categorieId = scanner.nextLong();
        p.setCategorieId(categorieId);

        p.setStatut("DISPONIBLE");

        produitService.createProduit(p);
        System.out.println("Produit ajouté avec succès!");
    }

    private static void modifierProduit() throws Exception {
        afficherTousProduits();
        System.out.print("Entrez l'ID du produit à modifier: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Vider le buffer

        Produit p = produitService.getProduitById(id);
        if (p == null) {
            System.out.println("Produit non trouvé!");
            return;
        }

        System.out.print("Nouveau nom (" + p.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) p.setNom(nom);

        System.out.print("Nouveau prix (" + p.getNom() + "): ");
        String prix = scanner.nextLine();
        if (!prix.isEmpty()) p.setPrixUnitaire(BigDecimal.valueOf(Double.parseDouble(prix)));

        System.out.print("Quantite du produit: ");
        p.setQuantite(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Statut du produit: ");
        p.setStatut(scanner.nextLine());
        scanner.nextLine();

        produitService.updateProduit(p);
        System.out.println("Produit modifié avec succès!");
    }

    private static void supprimerProduit() throws Exception {
        afficherTousProduits();
        System.out.print("Entrez l'ID du produit à supprimer: ");
        Long id = scanner.nextLong();

        produitService.deleteProduit(id);
        System.out.println("Produit supprimé avec succès!");
    }

    // Méthodes pour la gestion des catégories
    private static void afficherToutesCategories() throws Exception {
        List<Categorie> categories = categorieService.getAllCategories();
        System.out.println("\n=== LISTE DES CATÉGORIES ===");
        for (Categorie c : categories) {
            System.out.println("ID: " + c.getId() + " | Nom: " + c.getLibelle());
        }
    }

    private static void ajouterCategorie() throws Exception {
        Categorie c = new Categorie();
        System.out.print("Nom de la catégorie: ");
        c.setLibelle(scanner.nextLine());

        categorieService.createCategorie(c);
        System.out.println("Catégorie ajoutée avec succès!");
    }

    private static void modifierCategorie() throws Exception {
        afficherToutesCategories();
        System.out.print("Entrez l'ID de la catégorie à modifier: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Vider le buffer

        Categorie c = categorieService.getCategorieById(id);
        if (c == null) {
            System.out.println("Catégorie non trouvée!");
            return;
        }

        System.out.print("Nouveau nom (" + c.getLibelle() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) c.setLibelle(nom);

        categorieService.updateCategorie(c);
        System.out.println("Catégorie modifiée avec succès!");
    }

    private static void supprimerCategorie() throws Exception {
        afficherToutesCategories();
        System.out.print("Entrez l'ID de la catégorie à supprimer: ");
        Long id = scanner.nextLong();

        categorieService.deleteCategorie(id);
        System.out.println("Catégorie supprimée avec succès!");
    }

    private static Categorie CategorieLibelle(Long id) throws Exception {
        return categorieService.getCategorieById(id);
    }
}