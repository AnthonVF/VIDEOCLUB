/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import java.io.File;
import java.io.FileInputStream;
import java.util.Formatter;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.Calendar;

/**
 *
 * @author Anthony Vargas
 * @author Diego Arias
 */
public class Alquiler extends javax.swing.JFrame {
    String barra= File.separator;
    //Buscar en Carpeta de Películas
    String ubicacion1= System.getProperty("user.dir")+barra+"Registros de Películas"+barra;
    String[] descripcion1 = {"Título","Seleccione"};
    DefaultTableModel dtm1 = new DefaultTableModel(null,descripcion1);
    File contenedor1 = new File(ubicacion1);
    File[] registros1 = contenedor1.listFiles();
    
    //Buscar en Carpeta de Clientes
    String ubicacion= System.getProperty("user.dir")+barra+"Registros de Clientes"+barra;
    File contenedor= new File(ubicacion); // 
    File [] registros= contenedor.listFiles(); // cargar todos los archivos de la ubicación
    
    // Crear el archivo para reportes
    String ubicacion2= System.getProperty("user.dir")+barra+"Reporte de Clientes"+barra;
    String[] descripcion2 = {"DNI","Peliculas"};
    String fechaComenzar, fechaTerminar;
    
    /**
     * Creates new form Alquiler
     */
    public Alquiler() {
        initComponents();
        setLocationRelativeTo(null);
        RegistrosTabla();
        mostrarComboDNI();
    }
    public void MostrarDNI(){
        File url=new File(ubicacion+jtxtDNI.getText()+".registros");
        if(jtxtDNI.getText().equals("")){
        JOptionPane.showMessageDialog(rootPane,"Seleccione su DNI para alquilar películas");
        }else{
               if(url.exists()){
                   try {
                       FileInputStream fis =new FileInputStream(url);
                       Properties mostrar=new Properties();
                       mostrar.load(fis);
                       jtxtFechaInicio.setText(mostrar.getProperty("FechaDeInicio"));
                       jtxtFechaDevolucion.setText(mostrar.getProperty("FechadeDevolucion"));
                       jtextDias.setText(mostrar.getProperty("DiasTranscurridos"));
                   }catch (Exception e){
                   }
               }else{
               //JOptionPane.showMessageDialog(rootPane,"ERROR");
               }
        }
    }
    private void mostrarComboDNI(){
        for(int i=0;i<registros.length;i++){
            jComboBoxDNI.addItem(registros[i].getName().replace(".registros",""));
        } 
    }
    private void RegistrosTabla(){
        for(int i = 0;i<registros1.length;i++){
            File url = new File(ubicacion1+registros1[i].getName());
            try {
                FileInputStream fis = new FileInputStream(url);
                Properties mostrar = new Properties();
                mostrar.load(fis);
                String filas [] = {registros1[i].getName().replace(".txt",""),
                mostrar.getProperty("Seleccionar")};
                dtm1.addRow(filas);
            } catch (Exception e) {
            }
        }
        jTablaAlquiler.setModel(dtm1);
        addCheckBox(1,jTablaAlquiler);
    }
    private void ActualizarTabla(){
        registros = contenedor.listFiles();
        dtm1.setRowCount(0);
        RegistrosTabla();
        addCheckBox(1,jTablaAlquiler);
    }
    
    //MÉTODO PARA DEL REPORTE
    private void creacionRegistro(){
        String archivo=jtxtDNI.getText()+".registros"; //Nombre del Archivo 
        File clientes_ubicacion=new File(ubicacion2);
        File clientes_archivo=new File(ubicacion2+archivo);
        String peliculas;
        fechaComenzar = jtxtFechaInicio.getText();
        fechaTerminar = jtxtFechaDevolucion.getText();
        
        String diasTotales = jtextDias.getText();
        double diasTotales1 = Double.parseDouble(diasTotales);
        System.out.println("Número: "+diasTotales1);
        
        try{
            if(clientes_archivo.exists()){
                JOptionPane.showMessageDialog(rootPane,"EL registro ya existe");
            }else{
                clientes_ubicacion.mkdirs(); //Crea la carpeta ""
                Formatter reporte = new Formatter(ubicacion2+archivo); //Crea el Archivo
                reporte.format("%s\r\n%s","DNI: "+jtxtDNI.getText(),"Pelicula/s: ");
                int k=0;
                for(int i=0; i<jTablaAlquiler.getRowCount(); i++){
                    if(IsSelected(i, 1, jTablaAlquiler)){
                        for(int j = 0;j<registros1.length;j++){
                            if(i==j){
                                System.out.println("Pelicula: "+registros1[j].getName().replace(".txt",""));
                                peliculas = registros1[j].getName().replace(".txt","");
                                reporte.format("%s",peliculas+", ");
                                k++;
                            }
                        }
                    }
                }
                double precio = diasTotales1*k*0.45;
                reporte.format("\r\n%s","Número de películas: "+k);
                reporte.format("\r\n%s","Fecha de inicio de Alquiler: "+fechaComenzar);
                reporte.format("\r\n%s","Fecha de fin de Alquiler: "+fechaTerminar);
                reporte.format("\r\n%s","Número de días transcurridos: "+diasTotales);
                reporte.format("\r\n%s","Precio: $"+precio);
                reporte.close();
                JOptionPane.showMessageDialog(rootPane, "El precio total a pagar es: $"+precio);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(rootPane, "ERROR: El Archivo no pudo ser creado");
        }
    }
    
    private void addCheckBox(int column, JTable jTablaAlquilar){
        TableColumn tc = jTablaAlquilar.getColumnModel().getColumn(column);
        tc.setCellEditor(jTablaAlquilar.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(jTablaAlquilar.getDefaultRenderer(Boolean.class));
    }
    
    private boolean IsSelected(int row, int column, JTable jTablaAlquilar){
        return jTablaAlquilar.getValueAt(row, column) != null;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaAlquiler = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLDNI = new javax.swing.JLabel();
        jtxtDNI = new javax.swing.JTextField();
        jLFechaInicio = new javax.swing.JLabel();
        jtxtFechaInicio = new javax.swing.JTextField();
        jLFechaFinal = new javax.swing.JLabel();
        jtxtFechaDevolucion = new javax.swing.JTextField();
        jBtnConfirmar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jBtnRetroceder = new javax.swing.JButton();
        jLDias2 = new javax.swing.JLabel();
        jtextDias = new javax.swing.JTextField();
        jComboBoxDNI = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(195, 32, 37));

        jTablaAlquiler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablaAlquiler.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTablaAlquilerAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(jTablaAlquiler);

        jLabel1.setFont(new java.awt.Font("Niagara Solid", 0, 36)); // NOI18N
        jLabel1.setText("Seleccione los títulos a alquilar:");

        jLDNI.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLDNI.setForeground(new java.awt.Color(255, 255, 255));
        jLDNI.setText("DNI:");

        jtxtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDNIActionPerformed(evt);
            }
        });

        jLFechaInicio.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLFechaInicio.setForeground(new java.awt.Color(255, 255, 255));
        jLFechaInicio.setText("fecha inicio:");

        jLFechaFinal.setBackground(new java.awt.Color(255, 255, 255));
        jLFechaFinal.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLFechaFinal.setForeground(new java.awt.Color(255, 255, 255));
        jLFechaFinal.setText("fecha final:");

        jBtnConfirmar.setText("Confirmar");
        jBtnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConfirmarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        jBtnRetroceder.setText("Regresar");
        jBtnRetroceder.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBtnRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRetrocederActionPerformed(evt);
            }
        });

        jLDias2.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLDias2.setForeground(new java.awt.Color(255, 255, 255));
        jLDias2.setText("Dias :");

        jComboBoxDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDNIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtxtDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                    .addComponent(jtxtFechaInicio)))
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLDNI)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtxtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(jLDias2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtextDias, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(264, 264, 264))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jBtnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addComponent(jBtnRetroceder, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLDNI)
                    .addComponent(jtxtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLDias2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtextDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnConfirmar)
                    .addComponent(jBtnLimpiar)
                    .addComponent(jBtnRetroceder))
                .addContainerGap())
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/alquiler.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDNIActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        creacionRegistro();
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void jComboBoxDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDNIActionPerformed
        String copiar = (String) jComboBoxDNI.getSelectedItem();
        jtxtDNI.setText(copiar);
        MostrarDNI();
    }//GEN-LAST:event_jComboBoxDNIActionPerformed

    private void jBtnRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRetrocederActionPerformed
        Cliente app = new Cliente();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnRetrocederActionPerformed

    private void jTablaAlquilerAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTablaAlquilerAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTablaAlquilerAncestorAdded

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        Alquiler app3 = new Alquiler();
        app3.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(Alquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Alquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Alquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Alquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Alquiler().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConfirmar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JButton jBtnRetroceder;
    private javax.swing.JComboBox<String> jComboBoxDNI;
    private javax.swing.JLabel jLDNI;
    private javax.swing.JLabel jLDias2;
    private javax.swing.JLabel jLFechaFinal;
    private javax.swing.JLabel jLFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablaAlquiler;
    private javax.swing.JTextField jtextDias;
    private javax.swing.JTextField jtxtDNI;
    private javax.swing.JTextField jtxtFechaDevolucion;
    private javax.swing.JTextField jtxtFechaInicio;
    // End of variables declaration//GEN-END:variables
}