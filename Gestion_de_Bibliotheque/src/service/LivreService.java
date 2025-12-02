/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.EmpruntDao;
import dao.LivreDao;
import entities.Emprunt;
import entities.Livre;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author HP
 */
public class LivreService {

    private final LivreDao dao = new LivreDao();
    private EmpruntDao emDao = new EmpruntDao();

    public Livre getLivre(Livre l) throws Exception {
        return dao.findById(l.getId());
    }

    public List<Livre> listLivres() throws Exception {
        return dao.findAll();
    }

    public Livre creatLivre(Livre l) throws Exception {
        dao.insert(l);
        return l;
    }

    public boolean updateLivre(Livre l) throws Exception {
        return dao.update(l);
    }

    public boolean deleteLivre(Livre l) throws Exception {
        List<Emprunt> emprunts = emDao.findAll();
        for(Emprunt e : emprunts){
            if(e.getLivre().getId() == l.getId()){
                throw new Exception("Impossible de supprimer ce livre: il est emprunte!");
            }
        }
        return dao.delete(l.getId());
    }

    public List<Livre> filtreGenre(String genre) throws Exception {
        List<Livre> livres = dao.findAll();
        List<Livre> livresFiltree = new ArrayList<>();
        for (Livre l : livres) {
            if (l.getGenre().equals(genre)) {
                livresFiltree.add(l);
            }
        }
        return livresFiltree;
    }

    public List<Livre> filtreAuteur(String auteur) throws Exception {
        List<Livre> livres = dao.findAll();
        List<Livre> livresFiltree = new ArrayList<>();
        for (Livre l : livres) {
            if (l.getAuteur().equals(auteur)) {
                livresFiltree.add(l);
            }
        }
        return livresFiltree;
    }
}
