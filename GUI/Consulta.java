package GUI;
import DAL.Conexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Consulta extends javax.swing.JInternalFrame {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public Consulta() throws ClassNotFoundException {
        initComponents();
        con = Conexao.conectar();
        listarOrcamentos();
    }
    
    public void listarOrcamentos(){
        String sql = "Select * from Orcamento order by CodOrcamento";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tblOrcamentos.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void selecionarOrcamento(){
        int seleciona = tblOrcamentos.getSelectedRow();
        txtCodOrcamento.setText(tblOrcamentos.getModel().getValueAt(seleciona, 0).toString());
        txtCodMoto.setText(tblOrcamentos.getModel().getValueAt(seleciona, 1).toString());
        txtCodCliente.setText(tblOrcamentos.getModel().getValueAt(seleciona, 2).toString());
        txtRelPecas.setText(tblOrcamentos.getModel().getValueAt(seleciona, 3).toString());
        txtServico.setText(tblOrcamentos.getModel().getValueAt(seleciona, 4).toString());
        txtValorPecas.setText(tblOrcamentos.getModel().getValueAt(seleciona, 5).toString());
        txtValorMO.setText(tblOrcamentos.getModel().getValueAt(seleciona, 6).toString());
        txtValorTotal.setText(tblOrcamentos.getModel().getValueAt(seleciona, 7).toString());
        txtPago.setText(tblOrcamentos.getModel().getValueAt(seleciona, 8).toString());
    }
    
    public void editar(){
        String sql = "Update Orcamento set codMoto = ?, codCliente = ?, relPecas = ?, servico = ?, valorPecas =?, valorMO = ?, valorTotal = ?,"
                + "pago = ? where codOrcamento = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodMoto.getText()));
            pst.setString(2, txtCodCliente.getText());
            pst.setString(3, txtRelPecas.getText());
            pst.setString(4, txtServico.getText());
            pst.setFloat(5, Float.parseFloat(txtValorPecas.getText()));
            pst.setFloat(6, Float.parseFloat(txtValorMO.getText()));
            pst.setFloat(7, Float.parseFloat(txtValorTotal.getText()));
            pst.setString(8, txtPago.getText());
            pst.setInt(9, Integer.parseInt(txtCodOrcamento.getText()));
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Orçamento editado!","Editar",JOptionPane.INFORMATION_MESSAGE);
            listarOrcamentos();
        }catch(SQLException error){
            JOptionPane.showConfirmDialog(null, error);
        }
    }
    
    public void excluir(){
        String sql = "Delete from Orcamento where codOrcamento = ?";
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodOrcamento.getText()));
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Orçamento excluído!","Excluir",JOptionPane.INFORMATION_MESSAGE);
            listarOrcamentos();
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrcamentos = new javax.swing.JTable();
        lblCodOrcamento = new javax.swing.JLabel();
        txtCodOrcamento = new javax.swing.JTextField();
        lblCodCliente = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        lblCodMoto = new javax.swing.JLabel();
        txtCodMoto = new javax.swing.JTextField();
        lblRelPecas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtRelPecas = new javax.swing.JTextArea();
        lblServico = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtServico = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        txtS = new javax.swing.JRadioButton();
        txtN = new javax.swing.JRadioButton();
        txtPago = new javax.swing.JTextField();
        lblValorPecas = new javax.swing.JLabel();
        txtValorPecas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        txtValorMO = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setTitle("Consulta de orçamentos");

        tblOrcamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblOrcamentos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblOrcamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrcamentosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrcamentos);

        lblCodOrcamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodOrcamento.setText("Código do orçamento:");

        txtCodOrcamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodOrcamento.setEnabled(false);

        lblCodCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodCliente.setText("Código do cliente:");

        txtCodCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblCodMoto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodMoto.setText("Código da moto:");

        txtCodMoto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblRelPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblRelPecas.setText("Relação de peças:");

        txtRelPecas.setColumns(20);
        txtRelPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtRelPecas.setRows(5);
        jScrollPane2.setViewportView(txtRelPecas);

        lblServico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblServico.setText("Serviço:");

        txtServico.setColumns(20);
        txtServico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtServico.setRows(5);
        jScrollPane3.setViewportView(txtServico);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Pago:");

        txtS.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtS.setText("S");
        txtS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSActionPerformed(evt);
            }
        });

        txtN.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtN.setText("N");
        txtN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNActionPerformed(evt);
            }
        });

        txtPago.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPago.setEnabled(false);

        lblValorPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblValorPecas.setText("Valor total de peças:");

        txtValorPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Valor mão de obra:");

        lblValorTotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblValorTotal.setText("Valor total:");

        txtValorTotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtValorMO.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        btnEditar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnImprimir.setText("Imprimir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimir)
                        .addGap(301, 301, 301)
                        .addComponent(btnExcluir))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCodOrcamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodOrcamento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176)
                        .addComponent(lblCodCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCodMoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRelPecas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(lblServico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblValorPecas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorPecas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorMO, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(lblValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnEditar, btnExcluir, btnImprimir});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodOrcamento)
                    .addComponent(txtCodOrcamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodCliente)
                    .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCodMoto)
                    .addComponent(txtCodMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRelPecas)
                    .addComponent(lblServico)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtN)
                        .addComponent(txtS)
                        .addComponent(jLabel1)
                        .addComponent(txtValorMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtValorPecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblValorPecas))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblValorTotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnImprimir))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEditar, btnExcluir, btnImprimir});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblOrcamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrcamentosMouseClicked
        selecionarOrcamento();
    }//GEN-LAST:event_tblOrcamentosMouseClicked

    private void txtSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSActionPerformed
        txtPago.setText("S");
    }//GEN-LAST:event_txtSActionPerformed

    private void txtNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNActionPerformed
        txtPago.setText("N");
    }//GEN-LAST:event_txtNActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCodCliente;
    private javax.swing.JLabel lblCodMoto;
    private javax.swing.JLabel lblCodOrcamento;
    private javax.swing.JLabel lblRelPecas;
    private javax.swing.JLabel lblServico;
    private javax.swing.JLabel lblValorPecas;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTable tblOrcamentos;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtCodMoto;
    private javax.swing.JTextField txtCodOrcamento;
    private javax.swing.JRadioButton txtN;
    private javax.swing.JTextField txtPago;
    private javax.swing.JTextArea txtRelPecas;
    private javax.swing.JRadioButton txtS;
    private javax.swing.JTextArea txtServico;
    private javax.swing.JTextField txtValorMO;
    private javax.swing.JTextField txtValorPecas;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
