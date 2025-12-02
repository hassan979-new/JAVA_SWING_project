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
public class Livre {

    private int id;
    private String titre;
    private String auteur;
    private String genre;
    private Date anneePublication;

    public Livre(int id, String titre, String auteur, String genre, Date anneePublication) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.anneePublication = anneePublication;
    }

    public Livre(String titre, String auteur, String genre, Date anneePublication) {
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.anneePublication = anneePublication;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(Date anneePublication) {
        this.anneePublication = anneePublication;
    }

    @Override
    public String toString() {
        return titre;
    }

    
}
