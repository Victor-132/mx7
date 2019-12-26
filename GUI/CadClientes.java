package GUI;
import DAL.Conexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class CadClientes extends javax.swing.JInternalFrame {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public CadClientes() throws ClassNotFoundException {
        initComponents();
        con = Conexao.conectar();
        listarClientes();
    }
    
    public void pesquisarClientes(){
        String sql = "Select * from Clientes where nome like ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtBusca.getText() + "%");
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void cadastrarClientes(){
        String sql = "Insert into Clientes(nome, cpf, telefone, endereco, bairro, cidade, estado) values (?,?,?,?,?,?,?)";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCpf.getText());
            pst.setString(3, txtTelefone.getText());
            pst.setString(4, txtRua.getText());
            pst.setString(5, txtBairro.getText());
            pst.setString(6, txtCidade.getText());
            pst.setString(7, txtEstado.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Cliente cadastrado!","Cadastrar",JOptionPane.INFORMATION_MESSAGE);
            listarClientes();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void editarClientes(){
        String sql = "Update Clientes set nome = ?, cpf = ?, telefone = ?, endereco = ?, bairro = ?, cidade = ?, estado =? where codCliente = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtCpf.getText());
            pst.setString(3, txtTelefone.getText());
            pst.setString(4, txtRua.getText());
            pst.setString(5, txtBairro.getText());
            pst.setString(6, txtCidade.getText());
            pst.setString(7, txtEstado.getText());
            pst.setInt(8, Integer.parseInt(txtCodCliente.getText()));
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro atualizado!","Editar",JOptionPane.INFORMATION_MESSAGE);
            listarClientes();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void excluirCliente(){
        String sql = "Delete from Clientes where codCliente = ?";
        
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodCliente.getText()));
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastro excluído!","Excluir",JOptionPane.INFORMATION_MESSAGE);
            listarClientes();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void limparDados(){
        txtCodCliente.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtRua.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
    }
    
    public void selecionarClientes(){
        int seleciona = tblClientes.getSelectedRow();
        txtCodCliente.setText(tblClientes.getModel().getValueAt(seleciona, 0).toString());
        txtNome.setText(tblClientes.getModel().getValueAt(seleciona, 1).toString());
        txtCpf.setText(tblClientes.getModel().getValueAt(seleciona, 2).toString());
        txtTelefone.setText(tblClientes.getModel().getValueAt(seleciona, 3).toString());
        txtRua.setText(tblClientes.getModel().getValueAt(seleciona, 4).toString());
        txtBairro.setText(tblClientes.getModel().getValueAt(seleciona, 5).toString());
        txtCidade.setText(tblClientes.getModel().getValueAt(seleciona, 6).toString());
        txtEstado.setText(tblClientes.getModel().getValueAt(seleciona, 7).toString());
    }
    
    public void listarClientes(){
        String sql = "Select * from Clientes order by codCliente Asc";
        
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        lblCodCliente = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        lblRua = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        lblCidade = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        txtBusca = new javax.swing.JTextField();
        lblBusca = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de clientes");

        tblClientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        lblCodCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodCliente.setText("Código Cliente:");

        txtCodCliente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCodCliente.setEnabled(false);
        txtCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodClienteActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblNome.setText("Nome:");

        txtNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lblTelefone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTelefone.setText("Telefone:");

        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblRua.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblRua.setText("Rua:");

        txtRua.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblBairro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblBairro.setText("Bairro:");

        txtBairro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblCidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCidade.setText("Cidade:");

        txtCidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblEstado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblEstado.setText("Estado:");

        txtEstado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

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
        txtBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscaActionPerformed(evt);
            }
        });
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        lblBusca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblBusca.setText("Buscar");

        lblCpf.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCpf.setText("CPF:");

        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblRua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblBairro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCadastrar)
                                .addGap(132, 132, 132)
                                .addComponent(btnEditar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCodCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNome)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblCpf)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCpf)
                        .addGap(18, 18, 18)
                        .addComponent(lblTelefone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBusca)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCadastrar, btnEditar, btnExcluir, btnLimpar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodCliente)
                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefone)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCpf)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRua)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBairro)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCidade)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado)
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCadastrar, btnEditar, btnExcluir, btnLimpar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodClienteActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparDados();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluirCliente();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscaActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        cadastrarClientes();
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        pesquisarClientes();
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        selecionarClientes();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editarClientes();
    }//GEN-LAST:event_btnEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblBusca;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCodCliente;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRua;
    private javax.swing.JFormattedTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
