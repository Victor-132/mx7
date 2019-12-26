package GUI;
import DAL.Conexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Estoque extends javax.swing.JInternalFrame {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public Estoque() throws ClassNotFoundException {
        initComponents();
        con = Conexao.conectar();
        listarPecas();
    }
    
    public void pesquisarPeca(){
        String sql = "Select * from Estoque where nome like ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtBusca.getText()+"%");
            rs = pst.executeQuery();
            tblEstoque.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void cadastrarPeca(){
        String sql = "Insert into Estoque (nome, quantidade, descricao, valor) values(?,?,?,?)";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtPeca.getText());
            pst.setInt(2, Integer.parseInt(txtQuantidade.getText()));
            pst.setString(3, txtDescricao.getText());
            pst.setFloat(4, Float.parseFloat(txtValor.getText()));
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Peça cadastrada!","Cadastrar",JOptionPane.INFORMATION_MESSAGE);
            listarPecas();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void editarPeca(){
        String sql = "Update Estoque set nome = ?,valor = ?, quantidade = ?, descricao = ?  where codPeca = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setString(1, txtPeca.getText());
            pst.setFloat(2, Float.parseFloat(txtValor.getText()));
            pst.setInt(3, Integer.parseInt(txtQuantidade.getText()));
            pst.setString(4, txtDescricao.getText());
            pst.setInt(5, Integer.parseInt(txtCodPeca.getText()));
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastro atualizado!","Editar",JOptionPane.INFORMATION_MESSAGE);
            listarPecas();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void excluirPeca(){
        String sql = "Delete from Estoque where codPeca = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodPeca.getText()));
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastro excluido!","Excluir",JOptionPane.INFORMATION_MESSAGE);
            listarPecas();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void limparDados(){
        txtCodPeca.setText("");
        txtPeca.setText("");
        txtQuantidade.setText("0");
        txtDescricao.setText("");
        txtValor.setText("");
    }
    
    public void selecionarPeca(){
        int seleciona = tblEstoque.getSelectedRow();
        txtCodPeca.setText(tblEstoque.getModel().getValueAt(seleciona, 0).toString());
        txtPeca.setText(tblEstoque.getModel().getValueAt(seleciona, 1).toString());
        txtValor.setText(tblEstoque.getModel().getValueAt(seleciona, 2).toString());
        txtQuantidade.setText(tblEstoque.getModel().getValueAt(seleciona, 3).toString());
        txtDescricao.setText(tblEstoque.getModel().getValueAt(seleciona, 4).toString());
    }
    
    public void listarPecas(){
        String sql = "Select * from Estoque order by codPeca Asc";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tblEstoque.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void aQuantidade(){
        int resultado =Integer.parseInt(txtQuantidade.getText()); 
        resultado += 1;
        String quantidade = Integer.toString(resultado);
        txtQuantidade.setText(quantidade);
    }
    
    public void dQuantidade(){
        int resultado = Integer.parseInt(txtQuantidade.getText()); 
        if(resultado > 0){
            resultado -= 1;
        }
        String quantidade = Integer.toString(resultado);
        txtQuantidade.setText(quantidade);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstoque = new javax.swing.JTable();
        txtBusca = new javax.swing.JTextField();
        lblBusca = new javax.swing.JLabel();
        lblCodPeca = new javax.swing.JLabel();
        txtCodPeca = new javax.swing.JTextField();
        lblPeca = new javax.swing.JLabel();
        txtPeca = new javax.swing.JTextField();
        lblQuantidade = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        txtValor = new javax.swing.JTextField();
        lblValor = new javax.swing.JLabel();
        bntCadastar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnDQuantidade = new javax.swing.JButton();
        btnAQuantidade = new javax.swing.JButton();
        txtQuantidade = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Estoque");

        tblEstoque.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblEstoque.setModel(new javax.swing.table.DefaultTableModel(
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
        tblEstoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEstoqueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEstoque);

        txtBusca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        lblBusca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblBusca.setText("Buscar");

        lblCodPeca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodPeca.setText("Código da peça:");

        txtCodPeca.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtCodPeca.setEnabled(false);

        lblPeca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblPeca.setText("Peça:");

        txtPeca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPecaActionPerformed(evt);
            }
        });

        lblQuantidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblQuantidade.setText("Quantidade:");

        lblDescricao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDescricao.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane2.setViewportView(txtDescricao);

        txtValor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblValor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblValor.setText("Valor unitário:");

        bntCadastar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bntCadastar.setText("Cadastar");
        bntCadastar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCadastarActionPerformed(evt);
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

        btnDQuantidade.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDQuantidade.setText("-");
        btnDQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDQuantidadeActionPerformed(evt);
            }
        });

        btnAQuantidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAQuantidade.setText("+");
        btnAQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAQuantidadeActionPerformed(evt);
            }
        });

        txtQuantidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtQuantidade.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(txtBusca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBusca)
                .addGap(47, 47, 47))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCodPeca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodPeca, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPeca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPeca, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDQuantidade)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAQuantidade))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntCadastar)
                        .addGap(123, 123, 123)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpar)))
                .addGap(54, 54, 54))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bntCadastar, btnEditar, btnExcluir, btnLimpar});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAQuantidade, btnDQuantidade});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodPeca)
                    .addComponent(txtCodPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPeca)
                    .addComponent(lblQuantidade)
                    .addComponent(txtPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDQuantidade)
                    .addComponent(btnAQuantidade)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricao)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblValor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntCadastar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExcluir)
                        .addComponent(btnLimpar))
                    .addComponent(btnEditar))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bntCadastar, btnEditar, btnExcluir, btnLimpar});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAQuantidade, btnDQuantidade});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPecaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPecaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editarPeca();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        pesquisarPeca();
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void bntCadastarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCadastarActionPerformed
        cadastrarPeca();
    }//GEN-LAST:event_bntCadastarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluirPeca();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparDados();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tblEstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEstoqueMouseClicked
        selecionarPeca();
    }//GEN-LAST:event_tblEstoqueMouseClicked

    private void btnAQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAQuantidadeActionPerformed
        aQuantidade();
    }//GEN-LAST:event_btnAQuantidadeActionPerformed

    private void btnDQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDQuantidadeActionPerformed
        dQuantidade();
    }//GEN-LAST:event_btnDQuantidadeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCadastar;
    private javax.swing.JButton btnAQuantidade;
    private javax.swing.JButton btnDQuantidade;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBusca;
    private javax.swing.JLabel lblCodPeca;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblPeca;
    private javax.swing.JLabel lblQuantidade;
    private javax.swing.JLabel lblValor;
    private javax.swing.JTable tblEstoque;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodPeca;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtPeca;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
