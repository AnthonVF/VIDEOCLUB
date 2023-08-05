/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class Acceso extends javax.swing.JFrame {

    /**
     * Creates new form Acceso
     */
    public Acceso() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        Contraseña = new javax.swing.JPasswordField();
        btnRegresar = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        jLabel1.setText("ACCESO A MODERADOR");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 22));

        lblUsuario.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        lblUsuario.setText("Usuario:");
        bg.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 90, -1));

        txtusuario.setForeground(new java.awt.Color(204, 204, 204));
        txtusuario.setText("Ingrese el Usuario");
        txtusuario.setToolTipText("");
        txtusuario.setBorder(null);
        bg.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 180, -1));

        lblContraseña.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        lblContraseña.setText("Contraseña: ");
        bg.add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        Contraseña.setForeground(new java.awt.Color(204, 204, 204));
        Contraseña.setText("Ingrese la contraseña");
        Contraseña.setBorder(null);
        Contraseña.setCaretColor(new java.awt.Color(153, 153, 153));
        Contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContraseñaActionPerformed(evt);
            }
        });
        bg.add(Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 179, -1));

        btnRegresar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        bg.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        btnEntrar.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEntrarMouseClicked(evt);
            }
        });
        bg.add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/x.jpg"))); // NOI18N
        jLabel2.setText("imagen");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 220, 250));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user.png"))); // NOI18N
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 40, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/admin.png"))); // NOI18N
        jLabel4.setText("jLabel4");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 40));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 220, 10));
        bg.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 220, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseClicked
        
        // VARIABLES
        String Usuario = "Admin";
        String marca = "poli2021";
        
        String Contra = new String(Contraseña.getPassword());
        
        if(txtusuario.getText().equalsIgnoreCase(Usuario)&&Contra.equals(marca)){
            Moderadores accesoModerador = new Moderadores();
            accesoModerador.setVisible(true);
            this.dispose(); // para que se cierra cuando la otra se abra
        }else{
            if(txtusuario.getText().equals("")&&Contra.equals("")){
                JOptionPane.showMessageDialog(this, "Ingrese usuario y contraseña");
            }else{
                JOptionPane.showMessageDialog(this, "Usuario / Constraseña incorrecta");
            }
        }
    }//GEN-LAST:event_btnEntrarMouseClicked

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Interfaz regresarInterfaz = new Interfaz();
        regresarInterfaz.setVisible(true);
        this.dispose(); // para que se cierra cuando la otra se abra
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void ContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContraseñaActionPerformed

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
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Acceso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Acceso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}