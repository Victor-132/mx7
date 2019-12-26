package GUI;

import DAL.Conexao;

import javax.swing.JOptionPane;

import java.sql.*;

import java.util.logging.Level;

import java.util.logging.Logger;


public class Login extends javax.swing.JFrame {

    Connection con = null;

    PreparedStatement pst = null;

    ResultSet rs = null;

    
    public Login() throws ClassNotFoundException {

        initComponents();

        setLocationRelativeTo(null);

        con = Conexao.conectar();

    }

    
    public void logar(){

        	String sql = "Select * from Usuarios where usuario = ? and senha = ?";

        
        try{

	            pst = con.prepareStatement(sql);

            		pst.setString(1, txtUsuario.getText());

            pst.setString(2, txtSenha.getText());

            rs = pst.executeQuery();

            
            if(rs.next()){

                Home hm = new Home();

                hm.setVisible(true);

                dispose();

            }else{

                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");

            }

        }catch(SQLException error){

            JOptionPane.showMessageDialog(null, error);

        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents

    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;


        lblTitulo = new javax.swing.JLabel();

        lblUsuario = new javax.swing.JLabel();

        lblSenha = new javax.swing.JLabel();

        txtUsuario = new javax.swing.JTextField();

        txtSenha = new javax.swing.JPasswordField();

        btnEntrar = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setTitle("Login - Mx7 Oficina");

        getContentPane().setLayout(new java.awt.GridBagLayout());


        lblTitulo.setFont(new java.awt.Font("Arial", 3, 24));
 // NOI18N

        lblTitulo.setText("Mx7 Team - Oficina");

        gridBagConstraints = new java.awt.GridBagConstraints();
 
        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 0;

        gridBagConstraints.gridwidth = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(20, 30, 20, 30);

        getContentPane().add(lblTitulo, gridBagConstraints);


        lblUsuario.setFont(new java.awt.Font("Arial", 0, 12));
 // NOI18N

        lblUsuario.setText("Usuário:");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 5);

        getContentPane().add(lblUsuario, gridBagConstraints);


        lblSenha.setFont(new java.awt.Font("Arial", 0, 12));
 // NOI18N

        lblSenha.setText("Senha:");

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 0;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 5);

        getContentPane().add(lblSenha, gridBagConstraints);


        txtUsuario.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                txtUsuarioActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 1;

        gridBagConstraints.ipadx = 162;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 30);

        getContentPane().add(txtUsuario, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 2;

        gridBagConstraints.ipadx = 162;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 30);

        getContentPane().add(txtSenha, gridBagConstraints);


        btnEntrar.setFont(new java.awt.Font("Arial", 1, 14));
 // NOI18N

        btnEntrar.setText("Entrar");

        btnEntrar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {

                btnEntrarActionPerformed(evt);

            }

        });

        gridBagConstraints = new java.awt.GridBagConstraints();

        gridBagConstraints.gridx = 1;

        gridBagConstraints.gridy = 3;

        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;

        gridBagConstraints.insets = new java.awt.Insets(5, 93, 10, 30);

        getContentPane().add(btnEntrar, gridBagConstraints);


        pack();

    }// </editor-fold>//GEN-END:initComponents


    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed

        // TODO add your handling code here:

    }//GEN-LAST:event_txtUsuarioActionPerformed


    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed

        logar();

    }//GEN-LAST:event_btnEntrarActionPerformed


    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    new Login().setVisible(true);

                } catch (ClassNotFoundException ex) {

                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

                }

            }
 
       });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JButton btnEntrar;

    private javax.swing.JLabel lblSenha;

    private javax.swing.JLabel lblTitulo;

    private javax.swing.JLabel lblUsuario;

    private javax.swing.JPasswordField txtSenha;

    private javax.swing.JTextField txtUsuario;

    // End of variables declaration//GEN-END:variables
}
