/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import entities.Livre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.SysexMessage;
import util.ConnexionSingleton;

/**
 *
 * @author HP
 */
public class LivreDao implements IDao<Livre> {

    @Override
    public boolean insert(Livre o) throws Exception {
        String sql = "INSERT INTO Livre(titre, auteur, genre, anneePublication) VALUES (?,?,?,?)";
        try (Connection conn = ConnexionSingleton.getInstace().getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getAuteur());
            ps.setString(3, o.getGenre());
            ps.setDate(4, new java.sql.Date(o.getAnneePublication().getTime()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Livre o) throws Exception {
        String sql = "UPDATE Livre SET titre=?, auteur=?, genre=?, anneePublication=? WHERE id=?";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setString(1, o.getTitre());
            ps.setString(2, o.getAuteur());
            ps.setString(3, o.getGenre());
            ps.setDate(4, new java.sql.Date(o.getAnneePublication().getTime()));
            ps.setInt(5, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM Livre WHERE id=?";
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
    public List<Livre> findAll() throws Exception {
        String sql = "SELECT * FROM Livre";
        List<Livre> list = new ArrayList<Livre>();
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("genre"), rs.getDate("anneePublication")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Livre findById(int id) throws Exception {
        String sql = "SELECT * FROM Livre WHERE id = ?";
        try (PreparedStatement ps = ConnexionSingleton.getInstace().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Livre(rs.getInt("id"), rs.getString("titre"), rs.getString("auteur"), rs.getString("genre"), rs.getDate("anneePublication"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
