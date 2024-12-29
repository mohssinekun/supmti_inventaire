// DAO for Produit
package dao;

import modeles.Produit;

import java.util.List;

public interface ProduitDAO {
    List<Produit> getAllProduits();
    Produit getProduitById(Long id);
    List<Produit> searchProduitsByName(String name);
    void createProduit(Produit produit);
    void updateProduit(Produit produit);
    void deleteProduit(Long id);
}