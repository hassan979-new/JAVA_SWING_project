/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Emprunt {

    private int id;
    private Livre livre;
    private Membre membre;
    private Date dateEmprunt;
    private Date dateRetour;

    public Emprunt(Livre livre, Membre membre, Date dateEmprunt, Date dateRetour) {
        this.livre = livre;
        this.membre = membre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Emprunt(int id, Livre livre, Membre membre, Date dateEmprunt, Date dateRetour) {
        this.id = id;
        this.livre = livre;
        this.membre = membre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public int getId() {
        return id;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    
}
