/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Emprunt;
import entities.Livre;
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
public class EmpruntDao implements IDao<Emprunt> {
    
    private final LivreDao lvrDao = new LivreDao();
    private final MembreDao mbrDao = new MembreDao();
    
    @Override
    public boolean insert(Emprunt o) throws Exception {
        String sql = "INSERT INTO Emprunt(livre_id,membre_id,dateEmprunt, dateRetour) VALUES (?,?,?,?)";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setInt(1, o.getLivre().getId());
            ps.setInt(2, o.getMembre().getId());
            ps.setDate(3, new java.sql.Date(o.getDateEmprunt().getTime()));
            ps.setDate(4, new java.sql.Date(o.getDateRetour().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean update(Emprunt o) throws Exception {
        String sql = "UPDATE Emprunt SET livre_id=?, membre_id=?, dateEmprunt=?,dateRetour=? WHERE id = ?";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setInt(1, o.getLivre().getId());
            ps.setInt(2, o.getMembre().getId());
            ps.setDate(3, new java.sql.Date(o.getDateEmprunt().getTime()));
            ps.setDate(4, new java.sql.Date(o.getDateRetour().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(Emprunt o) throws Exception {
        String sql = "DELETE FROM Emprunt WHERE livre_id = ? AND membre_id = ? AND dateEmprunt = ?";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setInt(1, o.getLivre().getId());
            ps.setInt(2, o.getMembre().getId());
            ps.setDate(3, new java.sql.Date(o.getDateEmprunt().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    @Override
    public List<Emprunt> findAll() throws Exception {
        String sql = "SELECT * FROM Emprunt";
        List<Emprunt> list = new ArrayList<Emprunt>();
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Livre l = lvrDao.findById(new Livre(rs.getInt("livre_id"), "", "", "", null));
                Membre m = mbrDao.findById(new Membre(rs.getInt("membre_id"), "", "", null));
                list.add(new Emprunt(l, m, rs.getDate("dateEmprunt"), rs.getDate("dateRetour")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    @Override
    public Emprunt findById(Emprunt e) throws Exception {
        String sql = "SELECT * FROM Emprunt WHERE livre_id = ? AND membre_id = ? AND dateEmprunt = ?";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setInt(1, e.getLivre().getId());
            ps.setInt(2, e.getMembre().getId());
            ps.setDate(3, new java.sql.Date(e.getDateEmprunt().getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Livre l = lvrDao.findById(new Livre(rs.getInt("livre_id"), "", "", "", null));
                Membre m = mbrDao.findById(new Membre(rs.getInt("membre_id"), "", "", null));
                return new Emprunt(l, m, rs.getDate("dateEmprunt"), rs.getDate("dateRetour"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return null;
    }
    
}
