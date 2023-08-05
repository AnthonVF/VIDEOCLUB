/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Anthony Vargas
 */
public class Moderadores extends javax.swing.JFrame {
    String barra= File.separator;
    String ubicacion= System.getProperty("user.dir")+barra+"Registros de Películas"+barra;
    String[] descripcion = {"Título","Productora","Lanzamiento","Director/a","Nacionalidad-Director/a","Sinopsis","Reparto"};
    DefaultTableModel dtm = new DefaultTableModel(null,descripcion);
    File contenedor = new File(ubicacion);
    File[] registros = contenedor.listFiles();
    /**
     * Creates new form Moderadores
     */
    public Moderadores() {
        initComponents();
        setLocationRelativeTo(null);
        RegistrosTabla();
    }
    /*public void Mostrar(){
        File url=new File(ubicacion+jTxtTitulo.getText()+".txt");
        if(jTxtTitulo.getText().equals("")){
        JOptionPane.showMessageDialog(rootPane,"Seleccione el Título para mostrar su información");
        }else{
            if(url.exists()){
                try {
                    FileInputStream fis =new FileInputStream(url);
                    Properties mostrar=new Properties();
                    mostrar.load(fis);
                    jTxtProductora.setText(mostrar.getProperty("Productora"));
                    jTxtAño.setText(mostrar.getProperty("Año"));
                    jTxtDirección.setText(mostrar.getProperty("Director/a"));
                    jTxtNacionalidad.setText(mostrar.getProperty("Nacionalidad Director/a"));
                    jTxtSinopsis.setText(mostrar.getProperty("Sinopsis"));
                }catch (Exception e){
                }
            }else{
            JOptionPane.showMessageDialog(rootPane,"Película ya Registrada");
            }
        }
    }*/
    private void RegistrosTabla(){
        for(int i = 0;i<registros.length;i++){
            File url = new File(ubicacion+registros[i].getName());
            try {
                FileInputStream fis = new FileInputStream(url);
                Properties mostrar = new Properties();
                mostrar.load(fis);
                String filas [] = {registros[i].getName().replace(".txt",""),
                mostrar.getProperty("Productora"),mostrar.getProperty("Lanzamiento"),
                mostrar.getProperty("Director/a"),mostrar.getProperty("Nacionalidad-Director/a"),
                mostrar.getProperty("Sinopsis"),mostrar.getProperty("Reparto")};
                dtm.addRow(filas);
            } catch (Exception e) {
            }
        }
        jTablaCatalogo.setModel(dtm);
    }
    private void ActualizarTabla(){
        registros = contenedor.listFiles();
        dtm.setRowCount(0);
        RegistrosTabla();
    }
    private void Guardar(){ //Método Guardar Películas
        String archivo=jTxtTitulo.getText()+".txt"; //Nombre del Archivo 
        File Guardar_ubicacion=new File(ubicacion);
        File Guardar_archivo=new File(ubicacion+archivo);
        if(jTxtTitulo.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"No ha sido ingresado el Título"); //Mensaje de alerta en caso de que no ingrese nada
        }else {
            try {
                if(Guardar_archivo.exists()){
                JOptionPane.showMessageDialog(rootPane,"La Película ya existe");
            }else{
                     Guardar_ubicacion.mkdirs(); //Crea la carpeta 
                     Formatter guardar= new Formatter(ubicacion+archivo); //Crea el Archivo 
                     guardar.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s","Título:"+jTxtTitulo.getText(),
                     "Productora:"+jTxtProductora.getText(),
                     "Lanzamiento:"+jTxtAño.getText(),
                     "Director/a:"+jTxtDirección.getText(),
                     "Nacionalidad-Director/a:"+jTxtNacionalidad.getText(),
                     "Sinopsis:"+jTxtSinopsis.getText(),
                     "Reparto:"+jTxtReparto.getText());     
                guardar.close();
                JOptionPane.showMessageDialog(rootPane, "Archivo Creado");
                ActualizarTabla();
            }     
            }catch (Exception e){
                JOptionPane.showMessageDialog(rootPane, "ERROR: El Archivo no pudo ser creado");
            }  
        }
    }
    private void Eliminar(){
    File url= new File(ubicacion+jTxtTitulo.getText()+".txt");
    String btns []= {"Eliminar","Cancelar"};
    if(jTxtTitulo.getText().equals("")){
        JOptionPane.showMessageDialog(rootPane,"Escriba el título de la Película para eliminar el registro");
    }else{
        if(url.exists()){
            try{
                FileInputStream cerrar = new FileInputStream(url);
                cerrar.close();
                System.gc();
                int seguro = JOptionPane.showOptionDialog(rootPane,
                        "¿Estas seguro de eliminar la Película?: "+jTxtTitulo.getText(),
                        "Eliminacion", 0, 0, null, btns, null);
                if(seguro == JOptionPane.YES_OPTION){
                    url.delete();
                    JOptionPane.showMessageDialog(rootPane, "Película Eliminada");
                    ActualizarTabla();
                }  
            }catch (Exception e){ 
            }
            }else{
            JOptionPane.showMessageDialog(rootPane, "Esa película no existe");
            }
        }
    }
    private void Editar(){
        File url=new File(ubicacion+jTxtTitulo.getText()+".txt");
        if(jTxtTitulo.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Indique el Título para EDITAR los datos de la Película");
        }else{
            if(url.exists()){  
            try{
                FileWriter permite_escrito =new FileWriter(ubicacion+jTxtTitulo.getText()+".txt");
                String Titulo="Título:";
                String Productora="Productora:";
                String Año="Lanzamiento:";
                String Direccion="Director/a:";
                String Nacionalidad="Nacionalidad-Director/a:";
                String Sinopsis="Sinopsis:";
                String Reparto="Reparto:";
                PrintWriter guardar = new PrintWriter(permite_escrito);
                guardar.println(Productora+jTxtProductora.getText());
                guardar.println(Año+jTxtAño.getText());
                guardar.println(Direccion+jTxtDirección.getText());
                guardar.println(Nacionalidad+jTxtNacionalidad.getText());
                guardar.println(Sinopsis+jTxtSinopsis.getText());
                guardar.println(Sinopsis+jTxtReparto.getText());
                permite_escrito.close();
                JOptionPane.showMessageDialog(rootPane,"Edición Guardada Correctamente");  
                ActualizarTabla();
                }catch(Exception e){
                     JOptionPane.showMessageDialog(rootPane,"Error"+e);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"No existe un  registro con ese Título");
                JOptionPane.showMessageDialog(rootPane,"Verifique que el Título sea correcto");
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTxtTitulo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTxtProductora = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTxtAño = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtDirección = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTxtNacionalidad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaCatalogo = new javax.swing.JTable();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtSinopsis = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTxtReparto = new javax.swing.JTextArea();
        jBtnEliminar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jBtnReportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(2, 195, 252));

        jLabel1.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel1.setText("Título:");

        jTxtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtTituloActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel2.setText("Productora:");

        jLabel3.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel3.setText("Año:");

        jLabel4.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel4.setText("Director/a:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel5.setText("Nacionalidad:");

        jTablaCatalogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTablaCatalogo);

        jBtnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setText("Editar");
        jBtnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel6.setText("Sinopsis:");

        jTxtSinopsis.setColumns(20);
        jTxtSinopsis.setLineWrap(true);
        jTxtSinopsis.setRows(5);
        jScrollPane1.setViewportView(jTxtSinopsis);

        jLabel7.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 14)); // NOI18N
        jLabel7.setText("Reparto:");

        jTxtReparto.setColumns(20);
        jTxtReparto.setLineWrap(true);
        jTxtReparto.setRows(5);
        jScrollPane3.setViewportView(jTxtReparto);

        jBtnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar al Menú");
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jBtnReportes.setBackground(new java.awt.Color(255, 255, 255));
        jBtnReportes.setText("Reportes");
        jBtnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReportesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTxtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1)))
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtProductora, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtAño, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtDirección, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTxtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTxtProductora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTxtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtDirección, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnGuardar)
                        .addComponent(jBtnEditar)
                        .addComponent(jBtnEliminar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6)
                        .addGap(222, 222, 222))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(jBtnReportes))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        Editar();
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        Interfaz regresarInterfaz = new Interfaz();
        regresarInterfaz.setVisible(true);
        this.dispose(); // para que se cierra cuando la otra se abra
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void jBtnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReportesActionPerformed
        Reporte ventanaReporte = new Reporte();
        ventanaReporte.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnReportesActionPerformed

    private void jTxtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtTituloActionPerformed

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
            java.util.logging.Logger.getLogger(Moderadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Moderadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Moderadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Moderadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Moderadores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnReportes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTablaCatalogo;
    private javax.swing.JTextField jTxtAño;
    private javax.swing.JTextField jTxtDirección;
    private javax.swing.JTextField jTxtNacionalidad;
    private javax.swing.JTextField jTxtProductora;
    private javax.swing.JTextArea jTxtReparto;
    private javax.swing.JTextArea jTxtSinopsis;
    private javax.swing.JTextField jTxtTitulo;
    // End of variables declaration//GEN-END:variables
}
