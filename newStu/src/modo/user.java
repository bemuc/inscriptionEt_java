/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modo;

/**
 *
 * @author venom
 */
public class user {
    private String nom,prenom,Username,motdepas,nomProfil;
    private int idprofil,iduser;

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
   
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setMotdepas(String motdepas) {
        this.motdepas = motdepas;
    }

    public void setNomProfil(String nomProfil) {
        this.nomProfil = nomProfil;
    }

    public void setIdprofil(int idprofil) {
        this.idprofil = idprofil;
    }

    public int getIduser() {
        return iduser;
    }
    
    
    
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return Username;
    }

    public String getMotdepas() {
        return motdepas;
    }

    public String getNomProfil() {
        return nomProfil;
    }

    public int getIdprofil() {
        return idprofil;
    }

    
    
    
}
