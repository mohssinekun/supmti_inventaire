// Implementation for CategorieDAO
package dao;


import db.DatabaseConnection;
import modeles.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOImpl implements CategorieDAO {
    private final Connection connection;

    public CategorieDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public List<Categorie> getAllCategories() {
        List<Categorie> categories = new ArrayList<>();
        String query = "SELECT * FROM categorie";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categories.add(new Categorie(rs.getLong("id"), rs.getString("libelle")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Categorie getCategorieById(Long id) {
        String query = "SELECT * FROM categorie WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Categorie(rs.getLong("id"), rs.getString("libelle"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createCategorie(Categorie categorie) {
        String query = "INSERT INTO categorie (libelle) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, categorie.getLibelle());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategorie(Categorie categorie) {
        String query = "UPDATE categorie SET libelle = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, categorie.getLibelle());
            stmt.setLong(2, categorie.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategorie(Long id) {
        String query = "DELETE FROM categorie WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
