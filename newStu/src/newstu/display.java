package newstu;
import modo.*;
import controle.*;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author venom
 */
public class display extends javax.swing.JFrame {
          // declaration des objets
    private PreparedStatement ptm = null;
    private ResultSet rs = null;
    private controle.DbConnection conn = null;
    private Connection con =null;
    private String profil;
    Statement stm;
    private  DefaultTableModel model=null;
    private DefaultTableModel model1=null;
    private final int ETAT = 1;
    private final int tetat = 0;
    public ImageIcon Format = null;
    int s =0;
    byte[] photo =null ;
    static String test;
    ArrayList<user> lui = null;
    ArrayList<profil> loi = null;
   // login lo = new login();
    public int id ;

    /**
     * Creates new form display
     */
    public display(int i) {
        this.id = i;
        this.model = new DefaultTableModel();
        this.model1 = new DefaultTableModel();
        initComponents();
        AfficherPersonnne();
        loadprofil();
        
       // removeAll();
        insertion.setVisible(true);
        
        Affichage.setVisible(false);
        setLocationRelativeTo(null);
    }

    private display() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //declaration des methodes
    
    public void ActuUser()
    {
         try {
                model1.setRowCount(0);
                con = DbConnection.getConnection();
                stm = con.createStatement();
                rs = stm.executeQuery("select iduser,nom,prenom,Username,nomProfil,motdepas from users,profil where users.idprofil = profil.idprofil and tetat = 1");
                
                while(rs.next()){
                     model1.addRow(new Object []{rs.getString("iduser"),rs.getString("nom"),rs.getString("prenom"),rs.getString("Username"),rs.getString("nomProfil"),rs.getString("motdepas")});

                                    }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Probleme d'affichage du tableau ! " + e.getLocalizedMessage());
                    }
                       jTable2.setModel(model1); 
    }
    
    public void afficherUser()
    {
        model1.addColumn("Id");
        model1.addColumn("Nom");
        model1.addColumn("Prenom");
        model1.addColumn("Username");
        model1.addColumn("Nom du Profil");
        model1.addColumn("Mot de pas");
        
        ArrayList<user> rist = new ArrayList();
        rist = factor.AfficherUser();
        
        for(user pri:rist)
        {
           model1.addRow(new Object[]{
                    
                 pri.getIduser(),
                  pri.getNom(),
                  pri.getPrenom(),
                  pri.getUsername(),
                  pri.getNomProfil(),
                  pri.getMotdepas(),
                   
        });
           jTable2.setModel(model1);
    }
    }
    
    public void loadprofil()
    {
       loi = new ArrayList();
        loi = factor.displayP();
        
        for(profil pro:loi)
        {
            cbprofil.addItem(pro.getNomProfil());
        }
    }
    public void modif()
    {
     if(!txtnom.getText().equals("")&&(!txtprenom.getText().equals(""))&&(!txtdate.getText().equals(""))&&(!txtacademique.getText().equals(""))/*&&(!txtpasseport.getText().equals(""))*/&&(!txtnaissance.getText().equals(""))&&(!txtresidence.getText().equals(""))&&(!txtpere.getText().equals(""))&&(!txtmere.getText().equals(""))&&(!adressep.getText().equals("")))
         {
            try{
                
               con = DbConnection.getConnection();
               
 String Requete;
     Requete = "UPDATE etudiant SET nom =?,prenom= ?,date= ? ,sexe =?,Lnaissance=?,Lresidence=?,faculte =?,academique =?, pere= ?, mere=?,foto=?,adresseparents=? WHERE matricule ='"+matricule.getText()+"' ";
           ptm=con.prepareStatement(Requete);
               ptm.setString(1, txtnom.getText());
               ptm.setString(2, txtprenom.getText());
               ptm.setString(3, txtdate.getText());
               ptm.setString(4, cbsexe.getSelectedItem().toString());
               ptm.setString(5, txtnaissance.getText());
               ptm.setString(6, txtresidence.getText());
               ptm.setString(7, cbfaculte.getSelectedItem().toString());
               ptm.setString(8, txtacademique.getText());
               ptm.setString(9, txtpere.getText());
               ptm.setString(10, txtmere.getText());
               ptm.setString(11,jtxtfoto.getText());
               ptm.setString(12,adressep.getText());

           ptm.executeUpdate();                
                  JOptionPane.showMessageDialog(null, "Modification effectuée!!!");
                    ActualiserEtudiantDesc();
                 }catch(HeadlessException | SQLException exe)
                 {
                     System.err.println(exe);
                 
                 }         
             }
          }
    
    
    public String combo(){
                    switch (cb.getSelectedIndex()) {
                        case 0: return "matricule";
                        case 1: return "nom";
                        case 2: return "prenom";
                        case 3: return "date";
                        case 4: return "sexe";
                        case 5: return "Lnaissance";
                        case 6: return "Lresidence";
                        case 7: return "academique";
                        case 8: return "faculte";
                        case 9: return "pere";
                        case 10: return "mere";
                        case 11: return "adresseparents";
                        case 12: return "dateInsertion";
                        case 13: return "etudiant.iduser";
                        case 14: return "users.nom";
                }
                return "";
}
 
    
    
    public void recupUser(int i)
    {
            Uid.setText(model1.getValueAt(i,0).toString());
            Unom.setText(model1.getValueAt(i, 1).toString());
            Uprenom.setText(model1.getValueAt(i, 2).toString());
            Uuser.setText(model1.getValueAt(i, 3).toString());
            cbprofil.setSelectedItem(model1.getValueAt(i,4).toString());
            Upassword.setText(model1.getValueAt(i, 5).toString());
            

    }
    
    
    public void recuprer(String i){
                        try {
ArrayList<student> lise = new ArrayList();
lise = factor.AfficherPers(i);
for(student u:lise)
{
                                matricule.setText(i);
                                txtnom.setText(u.getNom());
                                txtprenom.setText(u.getPrenom());
                                txtdate.setText(u.getDate());
                                cbsexe.setSelectedItem(u.getSexe());
                                 jtxtfoto.setText(u.getFoto());
                                
                
                String img = u.getFoto(); 
                ImageIcon image1 = new ImageIcon(img);
                Image image2 = image1.getImage().getScaledInstance(jLabel16.getWidth(), jLabel16.getHeight()
                 , Image.SCALE_SMOOTH);  
                ImageIcon image3 = new ImageIcon(image2);
                jLabel16.setIcon(image3);
         
                                txtnaissance.setText(u.getLnaissance());
                                txtresidence.setText(u.getLresidence());
                                txtacademique.setText(u.getAcademique());
                                cbfaculte.setSelectedItem(u.getFaculte());
                                txtpere.setText(u.getPere());
                                txtmere.setText(u.getMere());
                                adressep.setText(u.getAdresseparents());
                                agent.setText(u.getNomB());
}
                    } catch (Exception e) {
                         JOptionPane.showMessageDialog(null, "Probleme lors du clic sur la ligne du tableau ! " + e.getLocalizedMessage());
                    }
                }
    
    public void supUser()
    {
        if(!Uid.getText().equals(""))
        {
            try{
                con = DbConnection.getConnection();
                  stm=con.createStatement();
                  stm.executeUpdate("UPDATE users SET  	tetat = '"+tetat+"' WHERE iduser ="+Uid.getText());
                  ActuUser();
                  viderUser();
                  JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès!!!");
            }catch(SQLException | HeadlessException exe)
                 {
                  JOptionPane.showMessageDialog(null, "Veuillez sélectionner l'enregistrement que vous voulez supprimer!!!");   
                 }
        }
    }
      public void Supp()
    {
     
       if(!matricule.getText().equals(""));
       {
 
              try{
                  con = DbConnection.getConnection();
                  stm=con.createStatement();
                  stm.executeUpdate("UPDATE etudiant SET etat = '" +ETAT+ "' WHERE matricule="+matricule.getText());
                  ActualiserEtudiantDesc();
                  vider();
                  JOptionPane.showMessageDialog(null, "Suppression effectuée avec succès!!!");
                 }catch(SQLException | HeadlessException exe)
                 {
                  JOptionPane.showMessageDialog(null, "Veuillez sélectionner l'enregistrement que vous voulez supprimer!!!");   
                 }         
             }
          }
                   // Methode pour vider
      
      public void viderUser()
      {
          try{
              Uid.setText("");
              Unom.setText("");
              Uprenom.setText("");
              Uuser.setText("");
              Upassword.setText("");
              
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Probleme de vider les zones de saisi ! " + e.getLocalizedMessage());
        }
      }
    public void vider(){
            try {
               matricule.setText("");
               txtnom.setText("");
               txtprenom.setText("");
               txtdate.setText("");
               jtxtfoto.setText("");
               txtnaissance.setText("");
               txtacademique.setText("");
               txtresidence.setText("");
               txtpere.setText("");
               txtmere.setText("");
               adressep.setText("");
               jLabel16.setIcon(Format);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Probleme de vider les zones de saisi ! " + e.getLocalizedMessage());
        }
    }
                public void ActualiserEtudiantDesc(){
                       try {
                           model.setRowCount(0);
                            con = DbConnection.getConnection();
                            stm = con.createStatement();
                            rs = stm.executeQuery("select matricule,etudiant.nom as NomE,etudiant.prenom as prE,date,sexe,Lnaissance,Lresidence,academique,faculte,pere,mere,adresseparents,dateInsertion,users.iduser as idAgent,users.nom as NomAgent from etudiant,users where etudiant.iduser = users.iduser and etat = 0  order by matricule asc");
                while(rs.next()){
                                        
                                             model.addRow(new Object []{rs.getString("matricule"),rs.getString("NomE"),rs.getString("prE"),rs.getString("date"),rs.getString("sexe"),rs.getString("Lnaissance"),rs.getString("Lresidence"),rs.getString("academique"),rs.getString("faculte"),rs.getString("pere"),rs.getString("mere"),rs.getString("adresseparents"),rs.getString("dateInsertion"),rs.getString("idAgent"),rs.getString("NomAgent")});
//                    // total.setText(rs.getString("tot"));
                                    }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Probleme d'affichage du tableau ! " + e.getLocalizedMessage());
                    }
                       jTable1.setModel(model);
                }
               
                
                
public void AfficherPersonnne(){
        
            model.addColumn("ID");
            model.addColumn("Nom");
            model.addColumn("Prenom");
            model.addColumn("Date naissance");
            model.addColumn("Genre");
            model.addColumn("Lieu de naissance");
            model.addColumn("Lieu de residence");
            model.addColumn("Annee academique");
            model.addColumn("Faculte");
            model.addColumn("Pere");
            model.addColumn("Mere");
            model.addColumn("Adresse Parent");
            model.addColumn("Date Insertion");
            model.addColumn("Id Agent");
            model.addColumn("Nom Agent");
            
            ArrayList<studentAf> lust = new ArrayList();
            lust = factor.AfficherPersonnne();
            
            for(studentAf u:lust){
            
             model.addRow(new Object[]{
                    
                  u.getMatricule(),
                  u.getNom(),
                  u.getPrenom(),
                  u.getDate(),
                  u.getSexe(),
                  u.getLnaissance(),
                  u.getLresidence(),
                  u.getAcademique(),
                  u.getFaculte(),
                  u.getPere(),
                  u.getMere(),
                  u.getAdresseparents(),
                  u.getDateInsertion(),
                  u.getIduser(),
                  u.getNomA(),
                  
             });
            
          
            jTable1.setModel(model);
        }
        }
                


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtfoto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        panel_centre = new javax.swing.JPanel();
        Affichage = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cb = new javax.swing.JComboBox();
        txtrecherche = new javax.swing.JTextField();
        total = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        insertion = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtnom = new javax.swing.JTextField();
        txtprenom = new javax.swing.JTextField();
        txtdate = new javax.swing.JTextField();
        txtresidence = new javax.swing.JTextField();
        txtnaissance = new javax.swing.JTextField();
        txtpere = new javax.swing.JTextField();
        txtmere = new javax.swing.JTextField();
        adressep = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        cbsexe = new javax.swing.JComboBox();
        cbfaculte = new javax.swing.JComboBox();
        txtacademique = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel16 = new javax.swing.JLabel();
        jtxtfoto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        matricule = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        agent = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        Ajouter = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        Unom = new javax.swing.JTextField();
        Uprenom = new javax.swing.JTextField();
        Uuser = new javax.swing.JTextField();
        Upassword = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        cbprofil = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        Uid = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        insert = new javax.swing.JMenu();
        disply = new javax.swing.JMenu();
        Ajout = new javax.swing.JMenu();
        Tools = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        panel_centre.setLayout(new java.awt.CardLayout());

        Affichage.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                AffichageComponentResized(evt);
            }
        });

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        cb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "matricule", "nom", "prenom", "date", "sexe", "lieu de naissance", "lieu de residence", "annee academique", "faculte", "pere", "mere", "adresse des paresnts", "date d'insertion", "id user", "nom user" }));
        cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionPerformed(evt);
            }
        });

        txtrecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrechercheActionPerformed(evt);
            }
        });
        txtrecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrechercheKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtrechercheKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrechercheKeyTyped(evt);
            }
        });

        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Faire une recherche");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel17.setText("LISTE DES ETUDIANTS");

        jButton8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton8.setText("Actualiser");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Total des recherches");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel25.setText("Hi");

        javax.swing.GroupLayout AffichageLayout = new javax.swing.GroupLayout(Affichage);
        Affichage.setLayout(AffichageLayout);
        AffichageLayout.setHorizontalGroup(
            AffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AffichageLayout.createSequentialGroup()
                .addGroup(AffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AffichageLayout.createSequentialGroup()
                        .addGap(604, 604, 604)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(276, 276, 276))
                    .addGroup(AffichageLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel15)
                        .addGap(46, 46, 46)
                        .addComponent(cb, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(txtrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AffichageLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(347, 347, 347)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addGap(73, 73, 73))
            .addGroup(AffichageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGap(36, 36, 36))
        );
        AffichageLayout.setVerticalGroup(
            AffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AffichageLayout.createSequentialGroup()
                .addGroup(AffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AffichageLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(jLabel25))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AffichageLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AffichageLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(AffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(31, 31, 31)
                .addGroup(AffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel_centre.add(Affichage, "card2");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setText("Inscription");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Nom*");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Prenom*");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Annee academique*");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Date de naissance*");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Sexe*");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Lieu de residence*");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Lieu de naissance*");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Nom du Pere*");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Faculte*");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Lieu de residence des parents*");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Nom de la Mere*");

        jButton1.setText("Ajouter ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtdate.setText("A - M - J");

        txtnaissance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnaissanceActionPerformed(evt);
            }
        });

        jButton2.setText("Supprimer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Modifier");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        cbsexe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculin", "Feminin" }));

        cbfaculte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BST", "BAM" }));

        txtacademique.setText("2020 - 2021 ");

        jButton4.setText("Ajouter une photo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDesktopPane1.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jtxtfoto.setEditable(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("ID");

        matricule.setEditable(false);

        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel26.setText("Hi");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel31.setText("L'agent");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbsexe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtresidence, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtacademique, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(txtnaissance, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(txtpere, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbfaculte, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtprenom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtnom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(adressep, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(32, 32, 32)
                                .addComponent(jButton2)
                                .addGap(29, 29, 29)
                                .addComponent(jToggleButton1))
                            .addComponent(txtmere, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDesktopPane1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jtxtfoto)
                                        .addGap(45, 45, 45))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jButton7)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(159, 159, 159))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(agent, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(matricule, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addComponent(jtxtfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbsexe, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtresidence, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtnaissance, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtacademique, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbfaculte, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtpere, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jDesktopPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtmere, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adressep, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(agent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jToggleButton1)
                    .addComponent(jButton7))
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout insertionLayout = new javax.swing.GroupLayout(insertion);
        insertion.setLayout(insertionLayout);
        insertionLayout.setHorizontalGroup(
            insertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insertionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        insertionLayout.setVerticalGroup(
            insertionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel_centre.add(insertion, "card3");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("Nom");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Prenom");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setText("Username");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setText("Profil");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Password");

        jToggleButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jToggleButton2.setText("Ajouter");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        Uprenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UprenomActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel24.setText("Ajouter un Utilisateur");

        jButton9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton9.setText("Supprimer");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel28.setText("Hi");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Actualiser");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Uid.setEditable(false);

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel30.setText("ID");

        javax.swing.GroupLayout AjouterLayout = new javax.swing.GroupLayout(Ajouter);
        Ajouter.setLayout(AjouterLayout);
        AjouterLayout.setHorizontalGroup(
            AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AjouterLayout.createSequentialGroup()
                .addGap(408, 408, 408)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
            .addGroup(AjouterLayout.createSequentialGroup()
                .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AjouterLayout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jToggleButton2)
                        .addGap(63, 63, 63)
                        .addComponent(jButton9))
                    .addGroup(AjouterLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19)
                            .addComponent(jLabel30))
                        .addGap(110, 110, 110)
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Uid)
                            .addComponent(Unom)
                            .addComponent(Uprenom)
                            .addComponent(Uuser)
                            .addComponent(Upassword)
                            .addComponent(cbprofil, 0, 154, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(AjouterLayout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AjouterLayout.setVerticalGroup(
            AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AjouterLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(jLabel28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AjouterLayout.createSequentialGroup()
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Uid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(Unom, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(Uprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(Uuser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cbprofil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(Upassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(AjouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jToggleButton2)
                            .addComponent(jButton9)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton3)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        panel_centre.add(Ajouter, "card4");

        jPanel1.add(panel_centre);

        insert.setText("Insertion");
        insert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                insertClick(evt);
            }
        });
        jMenuBar1.add(insert);

        disply.setText("Affichage");
        disply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                affclk(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                displyMouseReleased(evt);
            }
        });
        disply.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                displyMenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
                displyMenuKeyReleased(evt);
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenuBar1.add(disply);

        Ajout.setText("Ajouter utilisateur");
        Ajout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AjoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(Ajout);

        Tools.setText("Tools");

        jMenuItem1.setText("Quitter");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuItem1.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenuItem1MenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        Tools.add(jMenuItem1);

        jMenuItem2.setText("Deconnecter");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem2MousePressed(evt);
            }
        });
        Tools.add(jMenuItem2);

        jMenuBar1.add(Tools);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertClick
        // TODO add your handling code here:
        if(evt.getSource() == insert )
        {
         insertion.setVisible(true);
         Affichage.setVisible(false);
        }
    }//GEN-LAST:event_insertClick

    private void affclk(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_affclk
        // TODO add your handling code here:
        if(evt.getSource() == disply)
        {
            Affichage.setVisible(true);
            insertion.setVisible(false);
            
        }
         
    }//GEN-LAST:event_affclk

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();  //filename = variable for photo
        jtxtfoto.setText(filename);
        Image getAbsolutePath = null;

        ImageIcon iconFoto = new ImageIcon(filename);
        Image Foto = iconFoto.getImage();

        //size of photo width= 120, height=120
        Image modImag = Foto.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
        iconFoto = new ImageIcon(modImag);
        jLabel16.setIcon(iconFoto);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        try{
           // agent.setText();
        student f = new student();
         if(!txtnom.getText().equals("")&&(!txtprenom.getText().equals(""))&&(!txtdate.getText().equals(""))&&(!txtacademique.getText().equals(""))/*&&(!txtpasseport.getText().equals(""))*/&&(!txtnaissance.getText().equals(""))&&(!txtresidence.getText().equals(""))&&(!txtpere.getText().equals(""))&&(!txtmere.getText().equals(""))&&(!adressep.getText().equals("")))
         {
        f.setNom(txtnom.getText().toUpperCase());
        f.setPrenom(txtprenom.getText());
        f.setDate(txtdate.getText());
        f.setSexe(cbsexe.getSelectedItem().toString().toLowerCase());
        f.setFoto(txtfoto.getText());
        f.setLresidence(txtresidence.getText());
        f.setLnaissance(txtnaissance.getText());
        f.setAcademique(txtacademique.getText());
        f.setFaculte(cbfaculte.getSelectedItem().toString());
        f.setPere(txtpere.getText());
        f.setMere(txtmere.getText());
        f.setAdresseparents(adressep.getText());
        f.setIduser(id);

        factor.insert(f);
        ActualiserEtudiantDesc();
        Affichage.setVisible(true);
                
                insertion.setVisible(false);
                jButton1.setVisible(false);
         }
           else
               {
                        JOptionPane.showMessageDialog(null," erreur insertion verifier si tout les champs sont bien rempli");
                }
        }catch(HeadlessException e){
            
            JOptionPane.showMessageDialog(null,"erreur textuelle","Erreur textuelle", JOptionPane.ERROR);
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnaissanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnaissanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnaissanceActionPerformed

    private void txtrechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrechercheActionPerformed

    private void txtrechercheKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercheKeyPressed
       
    }//GEN-LAST:event_txtrechercheKeyPressed

    private void txtrechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercheKeyReleased
              try {
            if(txtrecherche.getText().equals("")){
                total.setText("");

            }else{
               model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prenom", "Date", "Genre","Lieu de naissance", "Lieu de residence", "Annee academique", "Faculte", "Pere","Mere","Adresse Parents","Date Insertion","Id user","Nom user"});
                boolean test = true;
                model.setRowCount(0);
                stm = con.createStatement();
                rs = stm.executeQuery("SELECT count(*) as tot  FROM etudiant,users WHERE etudiant.iduser = users.iduser and etat = 0 AND "+ combo() +" LIKE '" +txtrecherche.getText()+"%' ");

                while(rs.next()){

                    test = false;

                    // model.addRow(new Object []{rs.getString("matricule"),rs.getString("nom"),rs.getString("prenom"),rs.getString("date"),rs.getString("sexe"),rs.getString("foto"),rs.getString("Lnaissance"),rs.getString("Lresidence"),rs.getString("academique"),rs.getString("faculte"),rs.getString("pere"),rs.getString("mere")});
                    total.setText(rs.getString("tot"));
                }
                if(test){
                    total.setText("");
                    JOptionPane.showMessageDialog(null,"Desolez il n'y a pas des resultats sur votre recherche");
                }
                jTable1.setModel(model);
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,"erreur lors de la recherche" + e.getLocalizedMessage());
        }

        ActualiserEtudiantDesc();
            try {
            if(txtrecherche.getText().equals("")){
  total.setText("");
            }else{
               model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prenom", "Date", "Genre","Lieu de naissance", "Lieu de residence", "Annee academique", "Faculte", "Pere","Mere","Adresse Parents","Date Insertion","Id user","Nom user"});
                boolean test = true;
                model.setRowCount(0);
                stm = con.createStatement();
                rs = stm.executeQuery("SELECT matricule,etudiant.nom as nom,etudiant.prenom as prenom,date,sexe,Lnaissance,Lresidence,academique,faculte,pere,mere,adresseparents,dateInsertion,etudiant.iduser,users.nom as no FROM etudiant,users WHERE etudiant.iduser = users.iduser and etat = 0 AND "+ combo() +" LIKE '" +txtrecherche.getText()+"%' ");
                while(rs.next()){

                    test = false;
                    model.addRow(new Object []{rs.getString("matricule"),rs.getString("nom"),rs.getString("prenom"),rs.getString("date"),rs.getString("sexe"),rs.getString("Lnaissance"),rs.getString("Lresidence"),rs.getString("academique"),rs.getString("faculte"),rs.getString("pere"),rs.getString("mere"),rs.getString("adresseparents"),rs.getString("dateInsertion"),rs.getString("iduser"),rs.getString("no")});
                    // total.setText(rs.getString("tot"));
                }
                if(test){
                    total.setText("");
                    JOptionPane.showMessageDialog(null,"Desolez il n'y a pas des resultats sur votre recherche");
                }
                jTable1.setModel(model);
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,"erreur lors de la recherche" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_txtrechercheKeyReleased

    private void txtrechercheKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrechercheKeyTyped
          try {
            if(txtrecherche.getText().equals("")){
  total.setText("");
            }else{
               model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prenom", "Date", "Genre","Lieu de naissance", "Lieu de residence", "Annee academique", "Faculte", "Pere","Mere","Adresse Parents","Date Insertion","Id user","Nom user"});
                boolean test = true;
                model.setRowCount(0);
                stm = con.createStatement();
                rs = stm.executeQuery("SELECT matricule,etudiant.nom as nom,etudiant.prenom as prenom,date,sexe,Lnaissance,Lresidence,academique,faculte,pere,mere,adresseparents,dateInsertion,etudiant.iduser,users.nom as no FROM etudiant,users WHERE etudiant.iduser = users.iduser and etat = 0 AND "+ combo() +" LIKE '" +txtrecherche.getText()+"%' ");

                while(rs.next()){

                    test = false;
//             

                    model.addRow(new Object []{rs.getString("matricule"),rs.getString("nom"),rs.getString("prenom"),rs.getString("date"),rs.getString("sexe"),rs.getString("Lnaissance"),rs.getString("Lresidence"),rs.getString("academique"),rs.getString("faculte"),rs.getString("pere"),rs.getString("mere"),rs.getString("adresseparents"),rs.getString("dateInsertion"),rs.getString("iduser"),rs.getString("no")});
                    // total.setText(rs.getString("tot"));
                }
                if(test){
                    total.setText("");
                    JOptionPane.showMessageDialog(null,"Desolez il n'y a pas des resultats sur votre recherche");
                }
                jTable1.setModel(model);
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,"erreur lors de la recherche" + e.getLocalizedMessage());
        }
                   try {
            if(txtrecherche.getText().equals("")){
                
  total.setText("");
            }else{
               model.setColumnIdentifiers(new String[]{"ID", "Nom", "Prenom", "Date", "Genre","Lieu de naissance", "Lieu de residence", "Annee academique", "Faculte", "Pere","Mere","Adresse Parents","Date Insertion","Id user","Nom user"});
                boolean test = true;
                model.setRowCount(0);
                stm = con.createStatement();
                rs = stm.executeQuery("SELECT count(*) as tot  FROM etudiant,users WHERE etudiant.iduser = users.iduser and etat = 0 AND "+ combo() +" LIKE '" +txtrecherche.getText()+"%' ");

                while(rs.next()){

                    test = false;

                    // model.addRow(new Object []{rs.getString("matricule"),rs.getString("nom"),rs.getString("prenom"),rs.getString("date"),rs.getString("sexe"),rs.getString("foto"),rs.getString("Lnaissance"),rs.getString("Lresidence"),rs.getString("academique"),rs.getString("faculte"),rs.getString("pere"),rs.getString("mere")});
                    total.setText(rs.getString("tot"));
                }
                if(test){
                    total.setText("");
                    JOptionPane.showMessageDialog(null,"Desolez il n'y a pas des resultats sur votre recherche");
                }
                jTable1.setModel(model);
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null,"erreur lors de la recherche" + e.getLocalizedMessage());
        }

        ActualiserEtudiantDesc();


    }//GEN-LAST:event_txtrechercheKeyTyped

    private void AffichageComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_AffichageComponentResized
        // TODO add your handling code here:
    }//GEN-LAST:event_AffichageComponentResized

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         try {
                int i = jTable1.getSelectedRow();
                String v = model.getValueAt(i,0).toString();
                
                Affichage.setVisible(false);
                recuprer(v);
                insertion.setVisible(true);
                jButton1.setVisible(false);
                
 
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"erreur de replissage" + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        Supp();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        vider();
        jButton1.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ActualiserEtudiantDesc();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbActionPerformed

    private void UprenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UprenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UprenomActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        supUser();
        ActuUser();
        viderUser();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void AjoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AjoutMouseClicked
        // TODO add your handling code here:
        if(evt.getSource() == Ajout)
        {
            Ajouter.setVisible(true);
            Affichage.setVisible(false);
            insertion.setVisible(false);
            
        }
    }//GEN-LAST:event_AjoutMouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        try{
            modif();
            ActualiserEtudiantDesc();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erreur de modification" + e.getLocalizedMessage());
        }

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenuItem1MenuKeyPressed
        // TODO add your handling code here:
        
        this.dispose();
        this.hide();
        
    }//GEN-LAST:event_jMenuItem1MenuKeyPressed

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void jMenuItem2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MousePressed
        // TODO add your handling code here:
        this.dispose();
        login l = new login();
        l.setVisible(true);
    }//GEN-LAST:event_jMenuItem2MousePressed

    private void displyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_displyMouseReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_displyMouseReleased

    private void displyMenuKeyReleased(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_displyMenuKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_displyMenuKeyReleased

    private void displyMenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_displyMenuKeyPressed
        // TODO add your handling code here:
        if(evt.getSource() == disply)
        {
            Affichage.setVisible(true);
            insertion.setVisible(false);
            
        }
    }//GEN-LAST:event_displyMenuKeyPressed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        try{
        user f = new user();
         if(!Unom.getText().equals("")&&(!Uprenom.getText().equals(""))&&(!Uuser.getText().equals(""))&&(!Upassword.getText().equals("")))
         {
        f.setNom(Unom.getText().toUpperCase());
        f.setPrenom(Uprenom.getText());
        f.setUsername(Uuser.getText());
        f.setMotdepas(Upassword.getText());
        int n=cbprofil.getSelectedIndex();
        f.setIdprofil(loi.get(n).getIdprofil());
        factor.insertUser(f);
         }
           else
               {
                JOptionPane.showMessageDialog(null,"erreur insertion verifier si tout les champs sont bien rempli");
                }
        }catch(HeadlessException e){
            
            JOptionPane.showMessageDialog(null,"erreur textuelle","Erreur textuelle", JOptionPane.ERROR);
        }
        ActuUser();
        
        
        
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int o = jTable2.getSelectedRow();
        recupUser(o);
        
        
        
        
        
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ActuUser();
        viderUser();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new display().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Affichage;
    public javax.swing.JMenu Ajout;
    private javax.swing.JPanel Ajouter;
    private javax.swing.JMenu Tools;
    private javax.swing.JTextField Uid;
    private javax.swing.JTextField Unom;
    private javax.swing.JTextField Upassword;
    private javax.swing.JTextField Uprenom;
    private javax.swing.JTextField Uuser;
    private javax.swing.JTextField adressep;
    public javax.swing.JTextField agent;
    private javax.swing.JComboBox cb;
    private javax.swing.JComboBox cbfaculte;
    private javax.swing.JComboBox cbprofil;
    private javax.swing.JComboBox cbsexe;
    private javax.swing.JMenu disply;
    private javax.swing.JMenu insert;
    private javax.swing.JPanel insertion;
    private javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JTextField jtxtfoto;
    private javax.swing.JTextField matricule;
    private javax.swing.JPanel panel_centre;
    private javax.swing.JTextField total;
    private javax.swing.JTextField txtacademique;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtfoto;
    private javax.swing.JTextField txtmere;
    private javax.swing.JTextField txtnaissance;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtpere;
    private javax.swing.JTextField txtprenom;
    private javax.swing.JTextField txtrecherche;
    private javax.swing.JTextField txtresidence;
    // End of variables declaration//GEN-END:variables
}
