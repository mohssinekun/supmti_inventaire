// Model for Categorie
package modeles;

import java.io.Serializable;

public class Categorie implements Serializable {
    private Long id;
    private String libelle;

    public Categorie() {}

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