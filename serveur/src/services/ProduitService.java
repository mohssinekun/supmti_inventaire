package services;

import modeles.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProduitService extends Remote {
    List<Produit> getAllProduits() throws RemoteException;
    Produit getProduitById(Long id) throws RemoteException;
    List<Produit> searchProduitsByName(String name) throws RemoteException;
    void createProduit(Produit produit) throws RemoteException;
    void updateProduit(Produit produit) throws RemoteException;
    void deleteProduit(Long id) throws RemoteException;
}
