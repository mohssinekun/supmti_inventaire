// DAO for Categorie
package dao;

import modeles.Categorie;

import java.util.List;

public interface CategorieDAO {
    List<Categorie> getAllCategories();
    Categorie getCategorieById(Long id);
    void createCategorie(Categorie categorie);
    void updateCategorie(Categorie categorie);
    void deleteCategorie(Long id);
}