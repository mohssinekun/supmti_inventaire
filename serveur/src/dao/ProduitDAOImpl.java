// Implementation for ProduitDAO
package dao;

import db.DatabaseConnection;
import modeles.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOImpl implements ProduitDAO {
    private final Connection connection;

    public ProduitDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM produit";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produits.add(mapProduit(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public Produit getProduitById(Long id) {
        String query = "SELECT * FROM produit WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapProduit(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Produit> searchProduitsByName(String name) {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM produit WHERE nom LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    produits.add(mapProduit(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public void createProduit(Produit produit) {
        String query = "INSERT INTO produit (nom, categorie_id, quantite, prix_unitaire, statut) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, produit.getNom());
            stmt.setLong(2, produit.getCategorieId());
            stmt.setInt(3, produit.getQuantite());
            stmt.setBigDecimal(4, produit.getPrixUnitaire());
            stmt.setString(5, produit.getStatut());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduit(Produit produit) {
        String query = "UPDATE produit SET nom = ?, categorie_id = ?, quantite = ?, prix_unitaire = ?, statut = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, produit.getNom());
            stmt.setLong(2, produit.getCategorieId());
            stmt.setInt(3, produit.getQuantite());
            stmt.setBigDecimal(4, produit.getPrixUnitaire());
            stmt.setString(5, produit.getStatut());
            stmt.setLong(6, produit.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduit(Long id) {
        String query = "DELETE FROM produit WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Produit mapProduit(ResultSet rs) throws SQLException {
        return new Produit(
                rs.getLong("id"),
                rs.getString("nom"),
                rs.getLong("categorie_id"),
                rs.getInt("quantite"),
                rs.getBigDecimal("prix_unitaire"),
                rs.getString("statut"),
                rs.getTimestamp("date_creation")
        );
    }
}