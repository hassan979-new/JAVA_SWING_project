/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Membre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnexionSingleton;

/**
 *
 * @author HP
 */
public class MembreDao implements IDao<Membre> {

    @Override
    public boolean insert(Membre o) throws Exception {
        String sql = "INSERT INTO Membre(nom,email,dateInscription) VALUES (?,?,?)";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setString(1, o.getNom());
            ps.setString(2, o.getEmail());
            ps.setDate(3, new java.sql.Date(o.getDateInscription().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Membre o) throws Exception {
        String sql = "UPDATE Membre SET nom=?, email=?, dateInscription=? WHERE id = ?";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setString(1, o.getNom());
            ps.setString(2, o.getEmail());
            ps.setDate(3, new java.sql.Date(o.getDateInscription().getTime()));
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM Membre WHERE id = ?";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Membre> findAll() throws Exception {
        String sql = "SELECT * FROM Membre";
        List<Membre> list = new ArrayList<Membre>();
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("email"), rs.getDate("dateInscription")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Membre findById(int id) throws Exception {
        String sql = "SELECT * FROM Membre WHERE id = ?";
        try(PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Membre(rs.getInt("id"), rs.getString("nom"), rs.getString("email"), rs.getDate("DateInscription"));
            }
        }
        return null;
    }

}
