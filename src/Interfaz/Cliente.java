/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;
import com.toedter.calendar.JDateChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;//
import java.util.Calendar;//
import java.util.Formatter;
import java.util.Properties;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author diego
 */
public class Cliente extends javax.swing.JFrame {

    String barra= File.separator;
    String ubicacion= System.getProperty("user.dir")+barra+"Registros de Clientes"+barra;
    
    File contenedor= new File(ubicacion);
    File [] registros= contenedor.listFiles();  
    String [] titulos = {"DNI", "Nombre","Direccion","Telefono","FechaDeInicio","FechadeDevolución"};
    DefaultTableModel dtm = new DefaultTableModel (null,titulos);
    
    SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
    String fechaComenzar, fechaTerminar;
    int diasTranscurridos;
    
    private void RegTabla(){
     
          for(int i =0;i<registros.length;i++){
            File url= new File(ubicacion+registros[i].getName());
              try {
                  FileInputStream fis= new FileInputStream(url);
                  Properties mostrar = new Properties();
                  mostrar.load(fis);
                  
                  String filas []={registros[i].getName().replace(".registros", ""), mostrar.getProperty("Nombre"),
                  mostrar.getProperty("Direccion"),mostrar.getProperty("Telefono"),mostrar.getProperty("FechaDeInicio"),
                  mostrar.getProperty("FechadeDevolucion")};
                          
                   dtm.addRow(filas);
                  
              
              } catch (Exception e) {
              }
              
          }
          
          jTableRegistros.setModel(dtm);
    }
    
    private void MostrarJCombo(){
        for(int i=0; i<registros.length;i++){
            jComboBoxDNI.addItem(""+registros[i].getName().replace(".registros", ""));// Sin el getname uestra la ruta pero sigue mostrando eldni.registros por eso usamos el replace para suistiturilo por nada 
        }
    }
    
    private void ActualizarTabla(){
        registros = contenedor.listFiles();
        dtm.setRowCount(0);
        RegTabla();
    }
    
    public Cliente(){   
        initComponents();
        setLocationRelativeTo(null);
        MostrarJCombo();
        RegTabla();
    }
    
    public void calcularDias(JDateChooser FechaInicio, JDateChooser FechaFinal){
        if(FechaInicio.getDate()!=null&&FechaFinal.getDate()!=null){
            Calendar inicio = FechaInicio.getCalendar();
            Calendar termino = FechaFinal.getCalendar();
            int dias =-1;
            
            // Devuelve true cuando el objeto inicio llega antes que el objeto termino dado.
            while(inicio.before(termino)||inicio.equals(termino)){
                dias++;
                inicio.add(Calendar.DATE,1);
            }
            fechaComenzar = fecha.format(FechaInicio.getDate());
            fechaTerminar = fecha.format(FechaFinal.getDate());
            System.out.println("Los dias transcurridos son: "+dias);
            diasTranscurridos = dias;
            System.out.println("Fecha inicio: "+fechaComenzar);
            System.out.println("Fecha final: "+fechaTerminar);
        }else{
            //JOptionPane.showMessageDialog(null, "Selecciona las fechas","",JOptionPane.ERROR_MESSAGE);
        }
    }
   
    private void Clientes() { //Método Registro/Clientes
        String archivo=jtxtDNI.getText()+".registros"; //Nombre del Archivo 
        File clientes_ubicacion=new File(ubicacion);
        File clientes_archivo=new File(ubicacion+archivo);
        calcularDias(FechaInicio, FechaFinal);
        
        if(jtxtDNI.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"No ha registrado su DNI"); //
        }else {
            
            try {
                if(clientes_archivo.exists()){
                JOptionPane.showMessageDialog(rootPane,"EL registro ya existe");
            }else{
                     
                     clientes_ubicacion.mkdirs(); //Crea la carpeta ""
                     Formatter clientes= new Formatter(ubicacion+archivo); //Crea el Archivo 
                     clientes.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s","DNI:"+jtxtDNI.getText(),
                     "Nombre:"+jtxtNombre.getText(),
                     "Direccion:"+jtxtDireccion.getText(),
                     "Telefono:"+jtxtTelefono.getText(),
                     "FechaDeInicio:"+fechaComenzar,
                     "FechadeDevolucion:"+fechaTerminar,
                     "DiasTranscurridos:"+diasTranscurridos);
                      
                clientes.close();
                JOptionPane.showMessageDialog(rootPane, "Archivo Creado");
                // Para solucionar el error que a crear un registro no se actualiza a tiempo real en el JCombo:
                //remover items que hay en el combo
                jComboBoxDNI.removeAllItems();
                registros = contenedor.listFiles(); //Carga los registros en el array/ actualiza nombre archivos
                MostrarJCombo();
                ActualizarTabla();
            }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "ERROR: El Archivo no pudo ser creado");
            }
        
    }
 }
    private void Mostrar(){
        File url=new File(ubicacion+jtxtDNI.getText()+".registros");
        calcularDias(FechaInicio, FechaFinal);
        if(jtxtDNI.getText().equals("")){
        JOptionPane.showMessageDialog(rootPane,"Indique el DNI para MOSTRAR los datos del cliente");
 
        }else{
        if(url.exists()){
            try {
                
                FileInputStream fis =new FileInputStream(url);
                Properties mostrar=new Properties();
                mostrar.load(fis);
                jtxtNombre.setText(mostrar.getProperty("Nombre"));
                jtxtDireccion.setText(mostrar.getProperty("Direccion"));
                jtxtTelefono.setText(mostrar.getProperty("Telefono"));
                jtxtFechaInicio.setText(mostrar.getProperty("FechaDeInicio"));
                jtxtFechaDevolucion.setText(mostrar.getProperty("FechadeDevolucion"));
                jtextDias.setText(mostrar.getProperty("DiasTranscurridos"));
            } catch (Exception e) {
            }
                
                
            

          }else {
            JOptionPane.showMessageDialog(rootPane,"Ese registro no existe" );
        }


          }

}
    private void Editar(){
        File url=new File(ubicacion+jtxtDNI.getText()+".registros");
        calcularDias(FechaInicio, FechaFinal);
        
        if(jtxtDNI.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Indique el DNI para EDITAR los datos del cliente");
            } else{
                 if(url.exists()){
                    
                    
                    
                    
               try {
                   FileWriter permite_escrito =new FileWriter(ubicacion+jtxtDNI.getText()+".registros");
                   String DNI="DNI:";
                   String Nombre="Nombre:";
                   String Direccion="Direccion:";
                   String Teléfono="Telefono:";
                   String FechaDeInicio="FechaDeInicio:";
                   String FechadeDevolución="FechadeDevolucion:";
                   String DiasTransurridos ="DiasTranscurridos";
                   
                   PrintWriter guardar= new PrintWriter(permite_escrito);
                   guardar.println(DNI+jtxtDNI.getText());
                   guardar.println(Nombre+jtxtNombre.getText());
                   guardar.println(Direccion+jtxtDireccion.getText());
                   guardar.println(Teléfono+jtxtTelefono.getText());
                   guardar.println(FechaDeInicio+fechaComenzar);
                   guardar.println(FechadeDevolución+fechaTerminar);
                   guardar.println(DiasTransurridos+diasTranscurridos);
                   permite_escrito.close();
                   JOptionPane.showMessageDialog(rootPane,"Edición Guardada Correctamente");
                   ActualizarTabla();
                   
        } catch (Exception e) {
             JOptionPane.showMessageDialog(rootPane,"Error"+e);
        
        }
        }else{
           JOptionPane.showMessageDialog(rootPane,"No existe un  registro con ese DNI");
            JOptionPane.showMessageDialog(rootPane,"Verifique el DNI si es correcto");
                }
        
    }
      }
    private void Eliminar(){
    File url= new File(ubicacion+jtxtDNI.getText()+".registros");
    String btns []= {"Eliminar","Cancelar"};
    if(jtxtDNI.getText().equals("")){
        JOptionPane.showMessageDialog(rootPane,"Indique cual registro desea eliminar");
    }else{
        if(url.exists()){
            
            try{
                
                FileInputStream cerrar = new FileInputStream(url);
                cerrar.close();
                System.gc();
                
                int seguro = JOptionPane.showOptionDialog(rootPane,
                        "¿Estas seguro de eliminar el registro: "+jtxtDNI.getText(),
                        "Eliminacion", 0, 0, null, btns, null);
               
                if (seguro == JOptionPane.YES_OPTION){
                    
                    
                    url.delete();
                    JOptionPane.showMessageDialog(rootPane, "Registro Eliminado");
                    // para que elimine el registro dentro de jCOMBO
                    jComboBoxDNI.removeItem(jtxtDNI.getText()); //Remueve  solo el DIN
                    ActualizarTabla();
                }
                            
                
            } catch (Exception e){
                
            }
            
        }else{
            JOptionPane.showMessageDialog(rootPane, "Ese archivo no existe");
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
        jLabel2 = new javax.swing.JLabel();
        jLDNI = new javax.swing.JLabel();
        jtxtDNI = new javax.swing.JTextField();
        jLnombre = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jLDireccion = new javax.swing.JLabel();
        jtxtDireccion = new javax.swing.JTextField();
        jLTelefono = new javax.swing.JLabel();
        jtxtTelefono = new javax.swing.JTextField();
        jLFechaInicio = new javax.swing.JLabel();
        jLFechaDevolucion = new javax.swing.JLabel();
        jComboBoxDNI = new javax.swing.JComboBox<>();
        jButtonRegistro = new javax.swing.JButton();
        BotonRegresar = new javax.swing.JButton();
        jButtonMostrarR = new javax.swing.JButton();
        jButtonEditarR = new javax.swing.JButton();
        jButtonEliminarR = new javax.swing.JButton();
        Siguiente = new javax.swing.JButton();
        jButtonBorrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableRegistros = new javax.swing.JTable();
        jtxtFechaInicio = new javax.swing.JTextField();
        jtxtFechaDevolucion = new javax.swing.JTextField();
        jLDias = new javax.swing.JLabel();
        jtextDias = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        jLabel2.setText("øøø  REGISTRO CLIENTES  øøø");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jLDNI.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLDNI.setText("DNI:");
        jPanel1.add(jLDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        jtxtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDNIActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 169, -1));

        jLnombre.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLnombre.setText("Nombre:");
        jPanel1.add(jLnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, -1));

        jtxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 169, -1));

        jLDireccion.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLDireccion.setText("Dirección:");
        jPanel1.add(jLDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, 20));
        jPanel1.add(jtxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 169, -1));

        jLTelefono.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLTelefono.setText("Teléfono:");
        jPanel1.add(jLTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        jtxtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTelefonoActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 169, -1));

        jLFechaInicio.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLFechaInicio.setText("Fecha de inicio de Alquiler:");
        jPanel1.add(jLFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        jLFechaDevolucion.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLFechaDevolucion.setText("Fecha de Devolución:");
        jPanel1.add(jLFechaDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 220, -1));

        jComboBoxDNI.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDNIActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 130, -1));

        jButtonRegistro.setText("Crear Registro");
        jButtonRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistroActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, -1, -1));

        BotonRegresar.setBackground(new java.awt.Color(51, 204, 255));
        BotonRegresar.setFont(new java.awt.Font("Malgun Gothic", 0, 18)); // NOI18N
        BotonRegresar.setForeground(new java.awt.Color(255, 255, 255));
        BotonRegresar.setText("REGRESAR");
        BotonRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BotonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(BotonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 520, 140, 40));

        jButtonMostrarR.setText("Mostrar Registro");
        jButtonMostrarR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonMostrarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarRActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonMostrarR, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, -1));

        jButtonEditarR.setText("Editar Registro");
        jButtonEditarR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonEditarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarRActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEditarR, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, -1, -1));

        jButtonEliminarR.setText("Eliminar Registro");
        jButtonEliminarR.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonEliminarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarRActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEliminarR, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, -1, -1));

        Siguiente.setBackground(new java.awt.Color(204, 0, 0));
        Siguiente.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        Siguiente.setForeground(new java.awt.Color(255, 255, 255));
        Siguiente.setText("ALQUILAR");
        Siguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SiguienteActionPerformed(evt);
            }
        });
        jPanel1.add(Siguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 170, 40));

        jButtonBorrar.setText("Borrar");
        jButtonBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 300, -1, -1));

        jTableRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableRegistros);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 630, 160));
        jPanel1.add(jtxtFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 180, -1));
        jPanel1.add(jtxtFechaDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 180, -1));

        jLDias.setFont(new java.awt.Font("Showcard Gothic", 0, 15)); // NOI18N
        jLDias.setText("Dias :");
        jPanel1.add(jLDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 50, 40));
        jPanel1.add(jtextDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, 100, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/WoYFG3l6XG-compress.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDNIActionPerformed

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreActionPerformed

    private void jtxtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTelefonoActionPerformed

    private void jButtonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistroActionPerformed
        Clientes();
        calcularDias(FechaInicio, FechaFinal);
    }//GEN-LAST:event_jButtonRegistroActionPerformed

    private void BotonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegresarActionPerformed
        Interfaz app = new Interfaz();
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BotonRegresarActionPerformed

    private void jButtonMostrarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarRActionPerformed
        Mostrar();
    }//GEN-LAST:event_jButtonMostrarRActionPerformed

    private void jButtonEditarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarRActionPerformed
        Editar();
    }//GEN-LAST:event_jButtonEditarRActionPerformed

    private void jButtonEliminarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarRActionPerformed
        Eliminar();
    }//GEN-LAST:event_jButtonEliminarRActionPerformed

    private void SiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SiguienteActionPerformed
        Alquiler app2 = new Alquiler();
        app2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_SiguienteActionPerformed

    private void jButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarActionPerformed
        jtxtDNI.setText(null);
        jtxtNombre.setText(null);
        jtxtDireccion.setText(null);
        jtxtTelefono.setText(null);
        FechaInicio.setDate(null);
        FechaFinal.setDate(null);
        jtxtFechaInicio.setText(null);
        jtxtFechaDevolucion.setText(null);
        jtextDias.setText(null);
    }//GEN-LAST:event_jButtonBorrarActionPerformed

    private void jComboBoxDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDNIActionPerformed
        String copiar = (String) jComboBoxDNI.getSelectedItem();
        jtxtDNI.setText(copiar);
        Mostrar();
    }//GEN-LAST:event_jComboBoxDNIActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonRegresar;
    private javax.swing.JButton Siguiente;
    private javax.swing.JButton jButtonBorrar;
    private javax.swing.JButton jButtonEditarR;
    private javax.swing.JButton jButtonEliminarR;
    private javax.swing.JButton jButtonMostrarR;
    private javax.swing.JButton jButtonRegistro;
    private javax.swing.JComboBox<String> jComboBoxDNI;
    private javax.swing.JLabel jLDNI;
    private javax.swing.JLabel jLDias;
    private javax.swing.JLabel jLDireccion;
    private javax.swing.JLabel jLFechaDevolucion;
    private javax.swing.JLabel jLFechaInicio;
    private javax.swing.JLabel jLTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLnombre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableRegistros;
    private javax.swing.JTextField jtextDias;
    private javax.swing.JTextField jtxtDNI;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtFechaDevolucion;
    private javax.swing.JTextField jtxtFechaInicio;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtTelefono;
    // End of variables declaration//GEN-END:variables
}
