
package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modo.*;

/**
 *
 * @author venom
 */
public class factor {
    private static final Connection conn = DbConnection.getConnection();
     private static Statement stm ;
    private static PreparedStatement pstm = null;
    private static ResultSet rs = null;
    private static DefaultTableModel model;
    
    
    // inserer un etudiant
    public static void insert(student s){
        try{

             pstm = conn.prepareStatement("INSERT INTO etudiant(nom,prenom,date,sexe,foto,Lnaissance,Lresidence,academique,faculte,pere,mere,adresseparents,iduser) values "
                                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?); ");
                          
             pstm.setString(1,s.getNom());
                            pstm.setString(2,s.getPrenom());
                            pstm.setString(3,s.getDate());
                            pstm.setString(4,s.getSexe());
                            pstm.setString(5,s.getFoto());
                            pstm.setString(6,s.getLnaissance());
                            pstm.setString(7,s.getLresidence());
                            pstm.setString(8,s.getAcademique());
                            pstm.setString(9,s.getFaculte());
                            pstm.setString(10,s.getPere());
                            pstm.setString(11,s.getMere());
                            pstm.setString(12,s.getAdresseparents());
                            pstm.setInt(13,s.getIduser());
                        
                            pstm.executeUpdate();
                            pstm.close();
                            
                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(null,"Probleme d'insertion. Verifier si tout les champs sont bien saisie"+e.getLocalizedMessage());
                            System.err.print(e);
                            
                        }
    }
    
    
    // 
    public static void afficher(int s){
        try{
            pstm = conn.prepareStatement("select * from etudiant where matricule = ? ");
            pstm.setInt(1,s);
        }catch(SQLException e){
            System.err.print(e);
        }
    }
    
    
    // afficher un etudiant dans le formulaire d'insertion pour modifier ou supprimer
    public static  ArrayList<studentAf> AfficherPersonnne()
        {
            ArrayList<studentAf> list = new ArrayList();
            studentAf stu = null;

            try{
                stm = conn.createStatement();
                rs = stm.executeQuery("select matricule,etudiant.nom as NomE,etudiant.prenom as prE,date,sexe,Lnaissance,Lresidence,academique,faculte,pere,mere,adresseparents,dateInsertion,users.iduser as idAgent,users.nom as NomAgent from etudiant,users where etudiant.iduser = users.iduser and etat = 0  order by matricule asc");
                while(rs.next()){
                    stu = new studentAf();
                    stu.setMatricule(rs.getInt("matricule"));
                    stu.setNom(rs.getString("NomE"));
                    stu.setPrenom(rs.getString("prE"));
                    stu.setDate(rs.getString("date"));
                    stu.setSexe(rs.getString("sexe"));
                    stu.setLnaissance(rs.getString("Lnaissance"));
                    stu.setLresidence(rs.getString("Lresidence"));
                    stu.setAcademique(rs.getString("academique"));
                    stu.setFaculte(rs.getString("faculte"));
                    stu.setPere(rs.getString("pere"));
                    stu.setMere(rs.getString("mere"));
                    stu.setAdresseparents(rs.getString("adresseparents"));
                    stu.setDateInsertion(rs.getString("dateInsertion"));
                    stu.setIduser(rs.getInt("idAgent"));
                    stu.setNomA(rs.getString("NomAgent"));
                    
                    list.add(stu);
                    
                    
                    
                    
                    
                 //   model.addRow(new Object []{rs.getString("matricule"),rs.getString("nom"),rs.getString("prenom"),rs.getString("date"),rs.getString("sexe"),rs.getString("foto"),rs.getString("Lnaissance"),rs.getString("Lresidence"),rs.getString("academique"),rs.getString("faculte"),rs.getString("pere"),rs.getString("mere")});
                }
            }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Probleme d'affichage"+e.getLocalizedMessage());
            }
          //  jTable1.setModel(model);
            return list;
        }
    
    // 
    public static  ArrayList<student> AfficherPers(String i)
        {
            ArrayList<student> list = new ArrayList();
            student stu = null;

            try{
                stm = conn.createStatement();
                rs = stm.executeQuery("select matricule,etudiant.nom as NoE,etudiant.prenom as proE,date,sexe,foto,Lnaissance,Lresidence,academique,faculte,pere,mere,adresseparents,users.nom as Nome from etudiant,users where etudiant.iduser = users.iduser and matricule = '"+ i +"'");
               while(rs.next()){
                    stu = new student();
                    stu.setMatricule(rs.getInt("matricule"));
                    stu.setNom(rs.getString("Noe"));
                    stu.setPrenom(rs.getString("proE"));
                    stu.setDate(rs.getString("date"));
                    stu.setSexe(rs.getString("sexe"));
                    stu.setFoto(rs.getString("foto"));
                    stu.setLnaissance(rs.getString("Lnaissance"));
                    stu.setLresidence(rs.getString("Lresidence"));
                    stu.setAcademique(rs.getString("academique"));
                    stu.setFaculte(rs.getString("faculte"));
                    stu.setPere(rs.getString("pere"));
                    stu.setMere(rs.getString("mere"));
                    stu.setAdresseparents(rs.getString("adresseparents"));
                    stu.setNomB(rs.getString("Nome"));
                    //stu.setDateInsertion(rs.getString("dateInsertion"));
                    
                    list.add(stu);
                    
                    
                    
                    
                    
                }
            }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Probleme d'affichage"+e.getLocalizedMessage());
            }
       
            return list;
        }
    
    
    // inserer un utilisateur
    public static void insertUser(user us)
    {
       try{

             pstm = conn.prepareStatement("INSERT INTO users(nom,prenom,Username,idprofil,motdepas) values "
                                    + "(?,?,?,?,?); ");
                          
                            pstm.setString(1,us.getNom());
                            pstm.setString(2,us.getPrenom());
                            pstm.setString(3,us.getUsername());
                            pstm.setInt(4,us.getIdprofil());
                            pstm.setString(5,us.getMotdepas());
                            
                        
                            pstm.executeUpdate();
                            pstm.close();
                            
                        }catch(SQLException e){
                            System.err.print(e);
                            
                        } 
    }
    
    
    // afficher un utilisateur dans le tableau
    
    public static ArrayList<user> AfficherUser()
    {
        ArrayList<user> klit = new ArrayList();
        user tu = new user();
        
        try{
                stm = conn.createStatement();
                rs = stm.executeQuery("select iduser,nom,prenom,Username,nomProfil,motdepas from users,profil where users.idprofil = profil.idprofil and tetat = 1");
               while(rs.next()){
                    tu = new user();
                    tu.setIduser(rs.getInt("iduser"));
                    tu.setNom(rs.getString("nom"));
                    tu.setPrenom(rs.getString("prenom"));
                    tu.setUsername(rs.getString("Username"));
                    tu.setNomProfil(rs.getString("nomProfil"));
                    tu.setMotdepas(rs.getString("motdepas"));
                    
                    
                    klit.add(tu);
            }
            }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Probleme d'affichage"+e.getLocalizedMessage());
            }
        
        return klit;
    }
    public static ArrayList<user> AfficherUsers(int i)
    {
        ArrayList<user> klit = new ArrayList();
        user tu = new user();
        
        try{
                stm = conn.createStatement();
                rs = stm.executeQuery("select iduser,nom,prenom,Username,nomProfil,motdepas from users,profil where users.idprofil = profil.idprofil and iduser = '"+i+"'");
               while(rs.next()){
                    tu = new user();
                    tu.setIduser(rs.getInt("iduser"));
                    tu.setNom(rs.getString("nom"));
                    tu.setPrenom(rs.getString("prenom"));
                    tu.setUsername(rs.getString("Username"));
                    tu.setNomProfil(rs.getString("nomProfil"));
                    tu.setMotdepas(rs.getString("motdepas"));
                    
                    
                    klit.add(tu);
            }
            }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Probleme d'affichage"+e.getLocalizedMessage());
            }
        
        return klit;
    }
    
    public static ArrayList<profil> displayP()
    {
        ArrayList met = new ArrayList();
        profil py = null;
        try {
                            
                                stm = conn.createStatement();
                                rs = stm.executeQuery("SELECT * FROM profil ORDER BY idprofil asc");
                                while(rs.next()){
                                    py = new profil();
                                    py.setIdprofil(rs.getInt("idprofil"));
                                    py.setNomProfil(rs.getString("nomProfil"));
                                    met.add(py);
                            
                                }
                                
                                    
                        } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null, "Probleme d'affichage du tableau ! " + e.getLocalizedMessage());
                        }
        return met;
    }
    
}
