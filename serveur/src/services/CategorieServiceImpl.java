package services;

import dao.CategorieDAO;
import modeles.Categorie;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class CategorieServiceImpl extends UnicastRemoteObject implements CategorieService {
    private CategorieDAO categorieDAO;

    public CategorieServiceImpl(CategorieDAO categorieDAO) throws RemoteException {
        super();
        this.categorieDAO = categorieDAO;
    }

    @Override
    public List<Categorie> getAllCategories() throws RemoteException {
        return categorieDAO.getAllCategories();
    }

    @Override
    public Categorie getCategorieById(Long id) throws RemoteException {
        return categorieDAO.getCategorieById(id);
    }

    @Override
    public void createCategorie(Categorie categorie) throws RemoteException {
        categorieDAO.createCategorie(categorie);
    }

    @Override
    public void updateCategorie(Categorie categorie) throws RemoteException {
        categorieDAO.updateCategorie(categorie);
    }

    @Override
    public void deleteCategorie(Long id) throws RemoteException {
        categorieDAO.deleteCategorie(id);
    }
}

