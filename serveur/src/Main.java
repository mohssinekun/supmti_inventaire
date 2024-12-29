

import dao.CategorieDAO;
import dao.CategorieDAOImpl;
import dao.ProduitDAO;
import dao.ProduitDAOImpl;
import db.DatabaseConnection;
import modeles.Categorie;
import services.CategorieService;
import services.CategorieServiceImpl;
import services.ProduitService;
import services.ProduitServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            // Create an RMI registry on port 1099 (default)
            LocateRegistry.createRegistry(1099);

            // Initialize DAOs (can be injected via dependency injection)
            ProduitDAO produitDAO = new ProduitDAOImpl(); // Your actual DAO implementation
            CategorieDAO categorieDAO = new CategorieDAOImpl(); // Your actual DAO implementation

            // Create service implementations
            ProduitService produitService = new ProduitServiceImpl(produitDAO);
            CategorieService categorieService = new CategorieServiceImpl(categorieDAO);

            // Bind the services to the RMI registry
            Naming.rebind("ProduitService", produitService);
            Naming.rebind("CategorieService", categorieService);

            System.out.println("RMI server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}