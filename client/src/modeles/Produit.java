// Modèle pour représenter un produit
package modeles;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Produit implements Serializable {
    private Long id;
    private String nom;
    private Long categorieId;
    private int quantite;
    private BigDecimal prixUnitaire;
    private String statut;
    private Timestamp dateCreation;

    public Produit() {}

    public Produit(Long id, String nom, Long categorieId, int quantite, BigDecimal prixUnitaire, String statut, Timestamp dateCreation) {
        this.id = id;
        this.nom = nom;
        this.categorieId = categorieId;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.statut = statut;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
}
