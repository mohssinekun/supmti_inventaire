package services;

import modeles.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interface pour définir les services liés à la gestion des produits
// Cette interface est destinée à être utilisée dans une architecture RMI (Remote Method Invocation).
// L'interface ProduitService étend Remote, ce qui signifie que ses méthodes peuvent être appelées à distance.
public interface ProduitService extends Remote {

    // Méthode pour récupérer tous les produits.
    // Retourne une liste d'objets Produit.
    // Peut lever une RemoteException en cas de problème de communication à distance.
    List<Produit> getAllProduits() throws RemoteException;

    // Méthode pour récupérer un produit par son identifiant.
    // Retourne un objet Produit correspondant à l'id donné.
    // Peut lever une RemoteException.
    Produit getProduitById(Long id) throws RemoteException;

    // Méthode pour rechercher des produits par leur nom.
    // Prend une chaîne de caractères (name) comme paramètre.
    // Retourne une liste de produits dont le nom correspond au critère de recherche.
    // Peut lever une RemoteException.
    List<Produit> searchProduitsByName(String name) throws RemoteException;

    // Méthode pour créer un nouveau produit.
    // Prend un objet Produit en paramètre.
    // Peut lever une RemoteException.
    void createProduit(Produit produit) throws RemoteException;

    // Méthode pour mettre à jour un produit existant.
    // Prend un objet Produit mis à jour en paramètre.
    // Peut lever une RemoteException.
    void updateProduit(Produit produit) throws RemoteException;

    // Méthode pour supprimer un produit par son identifiant.
    // Prend un identifiant de type Long en paramètre.
    // Peut lever une RemoteException.
    void deleteProduit(Long id) throws RemoteException;
}

