package services;

import dao.ProduitDAO;
import modeles.Produit;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ProduitServiceImpl extends UnicastRemoteObject implements ProduitService {
    private ProduitDAO produitDAO;

    public ProduitServiceImpl(ProduitDAO produitDAO) throws RemoteException {
        super();
        this.produitDAO = produitDAO;
    }

    @Override
    public List<Produit> getAllProduits() throws RemoteException {
        return produitDAO.getAllProduits();
    }

    @Override
    public Produit getProduitById(Long id) throws RemoteException {
        return produitDAO.getProduitById(id);
    }

    @Override
    public List<Produit> searchProduitsByName(String name) throws RemoteException {
        return produitDAO.searchProduitsByName(name);
    }

    @Override
    public void createProduit(Produit produit) throws RemoteException {
        produitDAO.createProduit(produit);
    }

    @Override
    public void updateProduit(Produit produit) throws RemoteException {
        produitDAO.updateProduit(produit);
    }

    @Override
    public void deleteProduit(Long id) throws RemoteException {
        produitDAO.deleteProduit(id);
    }
}
