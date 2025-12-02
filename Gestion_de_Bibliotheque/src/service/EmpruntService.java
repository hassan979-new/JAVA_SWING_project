/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.EmpruntDao;
import entities.Emprunt;
import entities.Livre;
import entities.Membre;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class EmpruntService {

    private EmpruntDao dao = new EmpruntDao();

    public Emprunt empruntLivre(Emprunt e) throws Exception {
        dao.insert(e);
        return e;
    }

    public boolean retourLivre(Emprunt e) throws Exception {
        return dao.delete(e.getId());
    }

    public List<Livre> livresEmpruntes() throws Exception {
        List<Emprunt> listEmprunts = dao.findAll();
        List<Livre> listLivres = new ArrayList<Livre>();
        for (Emprunt e : listEmprunts) {
            listLivres.add(e.getLivre());
        }
        return listLivres;
    }

    public List<Livre> LivresEnRetard() throws Exception {
        Date now = new Date();
        List<Emprunt> listEmprunts = dao.findAll();
        List<Livre> listLivres = new ArrayList<>();
        for (Emprunt e : listEmprunts) {
            System.out.println("Null member in Emprunt ID: " + e.getId());
            if (e.getDateRetour().before(now)) {
                listLivres.add(e.getLivre());
            }
        }
        return listLivres;
    }

    public List<Emprunt> membreHistorique(Membre m) throws Exception {
        if (m == null) {
            return new ArrayList<>();
        }
        List<Emprunt> listEmprunts = dao.findAll();
        List<Emprunt> listMembreHistoire = new ArrayList<>();
        for (Emprunt e : listEmprunts) {
            if (e != null && e.getMembre() != null && e.getMembre().getId() == m.getId()) {
                listMembreHistoire.add(e);
            }
        }
        return listMembreHistoire;
    }

    public List<Emprunt> listEmrunt() throws Exception {
        return dao.findAll();
    }
}
