/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.EmpruntDao;
import dao.MembreDao;
import entities.Emprunt;
import entities.Membre;
import java.util.List;

/**
 *
 * @author HP
 */
public class MembreService {

    private final MembreDao dao = new MembreDao();
    private EmpruntDao emDao = new EmpruntDao();

    public Membre getMembre(Membre m) throws Exception {
        return dao.findById(m.getId());
    }

    public List<Membre> listMembres() throws Exception {
        return dao.findAll();
    }

    public Membre creatMembre(Membre m) throws Exception {
        dao.insert(m);
        return m;
    }

    public boolean updateMembre(Membre m) throws Exception {
        return dao.update(m);
    }

    public boolean deleteMembre(Membre m) throws Exception {
        List<Emprunt> emprunts = emDao.findAll();
        for(Emprunt e : emprunts){
            if(e.getMembre().getId() == m.getId()){
                throw new Exception("Impossible de supprimer ce membre : Il possede un livre!");
            }
        }
        return dao.delete(m.getId());
    }
}
