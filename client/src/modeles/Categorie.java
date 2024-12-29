// Modèle pour représenter une catégorie
package modeles;

import java.io.Serializable;

// La classe Categorie implémente l'interface Serializable
// pour permettre la sérialisation des objets de cette classe.
// Cela peut être utile pour enregistrer ou transmettre les objets.
public class Categorie implements Serializable {

    // Identifiant unique de la catégorie
    private Long id;

    // Libellé de la catégorie
    private String libelle;

    // Constructeur sans paramètres
    public Categorie() {}

    // Constructeur avec paramètres
    public Categorie(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}