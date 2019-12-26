package GUI;
import DAL.Conexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class CadMotos extends javax.swing.JInternalFrame {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public CadMotos() throws ClassNotFoundException {
        initComponents();
        con = Conexao.conectar();
        listarMotos();
    }
    
    public void pesquisarMoto(){
        String sql = "Select * from Motos where modelo like ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtBusca.getText()+"%");
            rs = pst.executeQuery();
            tblMotos.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);        
        }
    }
    
    public void cadastrarMoto(){
        String sql = "Insert into Motos(codCliente, modelo, ano, cor, observacao) values(?,?,?,?,?)";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodCliente.getText()));
            pst.setString(2, txtModelo.getText());
            pst.setInt(3, Integer.parseInt(txtAno.getText()));
            pst.setString(4, txtCor.getText());
            pst.setString(5, txtObservacao.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Moto cadastrada!","Cadastrar",JOptionPane.INFORMATION_MESSAGE);
            listarMotos();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void editarMoto(){
        String sql = "Update Motos set codCliente = ?, modelo = ?, ano = ?, cor = ?, observacao = ? where codMoto = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodCliente.getText()));
            pst.setString(2, txtModelo.getText());
            pst.setInt(3, Integer.parseInt(txtAno.getText()));
            pst.setString(4, txtCor.getText());
            pst.setString(5, txtObservacao.getText());
            pst.setInt(6, Integer.parseInt(txtCodMoto.getText()));
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro atualizado!","Editar",JOptionPane.INFORMATION_MESSAGE);
            listarMotos();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void excluirMoto(){
        String sql = "Delete from Motos where codMoto = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodMoto.getText()));
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastro excluído!","Excluir",JOptionPane.INFORMATION_MESSAGE);
            listarMotos();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void limparDados(){
        txtCodMoto.setText("");
        txtCodCliente.setText("");
        txtModelo.setText("");
        txtAno.setText("");
        txtCor.setText("");
        txtObservacao.setText("");
    }
    
    public void selecionarMoto(){
        int seleciona = tblMotos.getSelectedRow();
        txtCodMoto.setText(tblMotos.getModel().getValueAt(seleciona, 0).toString());
        txtCodCliente.setText(tblMotos.getModel().getValueAt(seleciona, 1).toString());
        txtModelo.setText(tblMotos.getModel().getValueAt(seleciona, 2).toString());
        txtAno.setText(tblMotos.getModel().getValueAt(seleciona, 3).toString());
        txtCor.setText(tblMotos.getModel().getValueAt(seleciona, 4).toString());
        txtObservacao.setText(tblMotos.getModel().getValueAt(seleciona, 5).toString());
    }
    
    public void listarMotos(){
        String sql = "Select * from Motos order by codMoto Asc";
        
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tblMotos.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBusca = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMotos = new javax.swing.JTable();
        lblCodMoto = new javax.swing.JLabel();
        txtCodMoto = new javax.swing.JTextField();
        lblCodCliente = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        lblModelo = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        lblAno = new javax.swing.JLabel();
        txtAno = new javax.swing.JTextField();
        lblCor = new javax.swing.JLabel();
        txtCor = new javax.swing.JTextField();
        lblObservacao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        btnCadastrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        txtBusca = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de motos");

        lblBusca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblBusca.setText("Buscar");

        tblMotos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblMotos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMotos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMotosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMotos);

        lblCodMoto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodMoto.setText("Código da moto:");

        txtCodMoto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCodMoto.setEnabled(false);

        lblCodCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodCliente.setText("Código do Cliente:");

        txtCodCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblModelo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblModelo.setText("Modelo:");

        txtModelo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });

        lblAno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblAno.setText("Ano:");

        txtAno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnoActionPerformed(evt);
            }
        });

        lblCor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCor.setText("Cor:");

        txtCor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblObservacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblObservacao.setText("Observação:");

        txtObservacao.setColumns(20);
        txtObservacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtObservacao.setRows(5);
        jScrollPane2.setViewportView(txtObservacao);

        btnCadastrar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        txtBusca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBusca)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCodMoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCodCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCadastrar))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnEditar)
                        .addGap(135, 135, 135)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblModelo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(lblCor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblObservacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)))
                .addGap(47, 47, 47))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCadastrar, btnEditar, btnExcluir, btnLimpar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBusca)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodMoto)
                    .addComponent(txtCodMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModelo)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAno)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCor)
                    .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodCliente)
                        .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblObservacao))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpar)
                    .addComponent(btnExcluir)
                    .addComponent(btnEditar)
                    .addComponent(btnCadastrar))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCadastrar, btnEditar, btnExcluir, btnLimpar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnoActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluirMoto();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        cadastrarMoto();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editarMoto();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparDados();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblMotosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMotosMouseClicked
        selecionarMoto();
    }//GEN-LAST:event_tblMotosMouseClicked

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        pesquisarMoto();
    }//GEN-LAST:event_txtBuscaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblBusca;
    private javax.swing.JLabel lblCodCliente;
    private javax.swing.JLabel lblCodMoto;
    private javax.swing.JLabel lblCor;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JTable tblMotos;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtCodMoto;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextArea txtObservacao;
    // End of variables declaration//GEN-END:variables
}
