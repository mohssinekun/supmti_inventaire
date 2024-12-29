package services;


import modeles.Categorie;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interface pour définir les services liés à la gestion des catégories
// Cette interface est destinée à être utilisée dans une architecture RMI (Remote Method Invocation).
// L'interface CategorieService étend Remote, ce qui signifie que ses méthodes peuvent être appelées à distance.
public interface CategorieService extends Remote {

    // Méthode pour récupérer toutes les catégories.
    // Retourne une liste d'objets Categorie.
    // Peut lever une RemoteException en cas de problème de communication à distance.
    List<Categorie> getAllCategories() throws RemoteException;

    // Méthode pour récupérer une catégorie par son identifiant.
    // Retourne un objet Categorie correspondant à l'id donné.
    // Peut lever une RemoteException.
    Categorie getCategorieById(Long id) throws RemoteException;

    // Méthode pour créer une nouvelle catégorie.
    // Prend un objet Categorie en paramètre.
    // Peut lever une RemoteException.
    void createCategorie(Categorie categorie) throws RemoteException;

    // Méthode pour mettre à jour une catégorie existante.
    // Prend un objet Categorie mis à jour en paramètre.
    // Peut lever une RemoteException.
    void updateCategorie(Categorie categorie) throws RemoteException;

    // Méthode pour supprimer une catégorie par son identifiant.
    // Prend un identifiant de type Long en paramètre.
    // Peut lever une RemoteException.
    void deleteCategorie(Long id) throws RemoteException;
}


