package services;

import modeles.Categorie;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CategorieService extends Remote {
    List<Categorie> getAllCategories() throws RemoteException;
    Categorie getCategorieById(Long id) throws RemoteException;
    void createCategorie(Categorie categorie) throws RemoteException;
    void updateCategorie(Categorie categorie) throws RemoteException;
    void deleteCategorie(Long id) throws RemoteException;
}

