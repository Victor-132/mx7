package GUI;
import DAL.Conexao;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Orcamento extends javax.swing.JInternalFrame {

    String pecas;
    int quantidade = 0;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public Orcamento() throws ClassNotFoundException {
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
            tblPecas.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void confirmar(){
        lstPecas.addItem(pecas+" "+txtQuantidade.getText()+"x");
    }
    
    public void cancelar(){
        txtCodPeca.setText("");
        txtDescricao.setText("");
        txtValorUn.setText("");
        txtEstoque.setText("");
        txtQuantidade.setText("0");
    }
    
    public void removerPeca(){
        lstPecas.removeItem(lstPecas.getSelectedItem());
        quantidade = Integer.parseInt(txtQuantidade.getText());
        float resultado = Float.parseFloat(txtValorPecas.getText())-(quantidade*Float.parseFloat(txtValorUn.getText()));
        String valor = Float.toString(resultado);
        txtValorPecas.setText(valor);
        resultado = Float.parseFloat(txtValorTotal.getText())-(quantidade*Float.parseFloat(txtValorUn.getText()));
        valor = Float.toString(resultado);
        txtValorTotal.setText(valor);
    }
    
    public void cancelarOrcamento(){
        txtCodPeca.setText("");
        txtCodCliente.setText("");
        txtCodMoto.setText("");
        txtDescricao.setText("");
        txtValorUn.setText("");
        txtEstoque.setText("");
        txtQuantidade.setText("0");
        txtValorPecas.setText("0.0");
        txtValorMO.setText("0.0");
        txtValorTotal.setText("0.0");
        txtServico.setText("");
        lstPecas.removeAllItems();
    }
    
    public String relPecas(){
        int numero = lstPecas.getItemCount();
        int i;
        String nome = "";
        for(i=0;i<numero;i++){
            nome = nome + " - " + lstPecas.getItemAt(i);
        }
        pecas = nome;
        return pecas;
    }
    
    public void finalizarOrcamento(){
        String sql = "Insert into Orcamento(codMoto, codCliente, relPecas, servico, valorPecas, valorMO, valorTotal, pago) values(?, ?, ?, ?, ?, ?, ?, ?)";
        relPecas();
        try{
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(txtCodMoto.getText()));
            pst.setString(2, txtCodCliente.getText());
            pst.setString(3, pecas);
            pst.setString(4, txtServico.getText());
            pst.setFloat(5, Float.parseFloat(txtValorPecas.getText()));
            pst.setFloat(6, Float.parseFloat(txtValorMO.getText()));
            pst.setFloat(7, Float.parseFloat(txtValorTotal.getText()));
            pst.setString(8, txtPago.getText());
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Orçamento finalizado!","Orçamento",JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }
    
    public void valorPecas(){
        quantidade = Integer.parseInt(txtQuantidade.getText());
        float resultado = Float.parseFloat(txtValorPecas.getText())+Float.parseFloat(txtValorUn.getText())*quantidade;
        String valor = Float.toString(resultado);
        txtValorPecas.setText(valor);
    }
    
    public void valorTotal(){
        float resultado = Float.parseFloat(txtValorPecas.getText())+Float.parseFloat(txtValorMO.getText());
        String valor = Float.toString(resultado);
        txtValorTotal.setText(valor);
    }
    
    public void aQuantidade(){
        quantidade = Integer.parseInt(txtQuantidade.getText());
        int estoque = Integer.parseInt(txtEstoque.getText());
        if ((estoque > 0)&&(quantidade != estoque)){
            quantidade += 1;
        }
        String valorQ = Integer.toString(quantidade);
        txtQuantidade.setText(valorQ);
    }
    
    public void dQuantidade(){
        quantidade = Integer.parseInt(txtQuantidade.getText());
        int estoque = Integer.parseInt(txtEstoque.getText());
        if (quantidade >= 0){
            quantidade -= 1;
        }
        String valorQ = Integer.toString(quantidade);
        txtQuantidade.setText(valorQ);
    }
    
    public String selecionarPeca(){
        int seleciona = tblPecas.getSelectedRow();
        txtCodPeca.setText(tblPecas.getModel().getValueAt(seleciona, 0).toString());
        pecas = tblPecas.getModel().getValueAt(seleciona, 1).toString();
        txtValorUn.setText(tblPecas.getModel().getValueAt(seleciona, 2).toString());
        txtEstoque.setText(tblPecas.getModel().getValueAt(seleciona, 3).toString());
        txtDescricao.setText(tblPecas.getModel().getValueAt(seleciona, 4).toString());
        return pecas;
    }

    public void listarPecas(){
        String sql = "Select * from Estoque order by codPeca Asc";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tblPecas.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException error){
            JOptionPane.showMessageDialog(null, error);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtBusca = new javax.swing.JTextField();
        lblBusca = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPecas = new javax.swing.JTable();
        lblCodCliente = new javax.swing.JLabel();
        txtCodCliente = new javax.swing.JTextField();
        lblRelacaoPecas = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtCodPeca = new javax.swing.JTextField();
        lblDescricao = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        lblValorUn = new javax.swing.JLabel();
        txtValorUn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnDQuantidade = new javax.swing.JButton();
        txtQuantidade = new javax.swing.JTextField();
        btnAQuantidade = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblValorPecas = new javax.swing.JLabel();
        lblValorMO = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        btnFOrcamento = new javax.swing.JButton();
        btnCOrcamento = new javax.swing.JButton();
        btnRPeca = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtServico = new javax.swing.JTextArea();
        lblServico = new javax.swing.JLabel();
        lstPecas = new javax.swing.JComboBox<>();
        lblEstoque = new javax.swing.JLabel();
        txtEstoque = new javax.swing.JTextField();
        txtCodMoto = new javax.swing.JTextField();
        lblCodMoto = new javax.swing.JLabel();
        lblPago = new javax.swing.JLabel();
        txtS = new javax.swing.JRadioButton();
        txtN = new javax.swing.JRadioButton();
        txtValorPecas = new javax.swing.JTextField();
        txtValorMO = new javax.swing.JTextField();
        txtValorTotal = new javax.swing.JTextField();
        txtPago = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Orçamento");

        txtBusca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        lblBusca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblBusca.setText("Buscar");

        tblPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblPecas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPecas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPecasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPecas);

        lblCodCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodCliente.setText("Código do cliente:");

        txtCodCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodClienteActionPerformed(evt);
            }
        });

        lblRelacaoPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblRelacaoPecas.setText("Relação de peças:");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Código da peça:");

        txtCodPeca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodPeca.setEnabled(false);
        txtCodPeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodPecaActionPerformed(evt);
            }
        });

        lblDescricao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDescricao.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDescricao.setRows(5);
        txtDescricao.setEnabled(false);
        jScrollPane3.setViewportView(txtDescricao);

        lblValorUn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblValorUn.setText("Valor unitário:");

        txtValorUn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtValorUn.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Quantidade:");

        btnDQuantidade.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDQuantidade.setText("-");
        btnDQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDQuantidadeActionPerformed(evt);
            }
        });

        txtQuantidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtQuantidade.setText("0");

        btnAQuantidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAQuantidade.setText("+");
        btnAQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAQuantidadeActionPerformed(evt);
            }
        });

        btnConfirmar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblValorPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblValorPecas.setText("Valor total de peças:");

        lblValorMO.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblValorMO.setText("Valor mão de obra:");

        lblValorTotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblValorTotal.setText("Valor total:");

        btnFOrcamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnFOrcamento.setText("Finalizar orçamento");
        btnFOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFOrcamentoActionPerformed(evt);
            }
        });

        btnCOrcamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCOrcamento.setText("Cancelar orçamento");
        btnCOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCOrcamentoActionPerformed(evt);
            }
        });

        btnRPeca.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRPeca.setText("Remover peça");
        btnRPeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRPecaActionPerformed(evt);
            }
        });

        txtServico.setColumns(20);
        txtServico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtServico.setRows(5);
        jScrollPane4.setViewportView(txtServico);

        lblServico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblServico.setText("Serviço:");

        lstPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblEstoque.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblEstoque.setText("Quantidade em estoque:");

        txtEstoque.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtEstoque.setEnabled(false);

        txtCodMoto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lblCodMoto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblCodMoto.setText("Código da moto:");

        lblPago.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblPago.setText("Pago:");

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

        txtValorPecas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtValorPecas.setText("0");

        txtValorMO.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtValorMO.setText("0");

        txtValorTotal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtValorTotal.setText("0");

        txtPago.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPago.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(txtBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBusca)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblCodMoto)
                                        .addGap(35, 35, 35)
                                        .addComponent(txtCodMoto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(lblCodCliente))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtCodPeca, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(79, 79, 79)
                                                .addComponent(lblDescricao)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(251, 251, 251)
                                        .addComponent(btnConfirmar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnDQuantidade)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(1, 1, 1)
                                                .addComponent(btnAQuantidade)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCancelar))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblValorUn)
                                                    .addComponent(lblEstoque))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGap(4, 4, 4)
                                                        .addComponent(txtValorUn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblValorPecas)
                                        .addComponent(lblValorMO)
                                        .addComponent(lblValorTotal))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                        .addComponent(txtValorPecas)
                                        .addComponent(txtValorMO))
                                    .addGap(95, 95, 95)
                                    .addComponent(lblServico)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblRelacaoPecas)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lstPecas, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblPago)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtS)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtN)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnRPeca)
                                    .addGap(212, 212, 212)
                                    .addComponent(btnFOrcamento)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCOrcamento))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAQuantidade, btnDQuantidade});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnConfirmar});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCOrcamento, btnFOrcamento, btnRPeca});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblValorUn)
                                .addComponent(txtValorUn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnConfirmar))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEstoque)
                                .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(btnDQuantidade)
                                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAQuantidade)
                                .addComponent(btnCancelar)))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblDescricao)
                            .addComponent(txtCodPeca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodMoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodMoto))))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblValorPecas)
                        .addComponent(lblServico))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtValorPecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValorMO)
                            .addComponent(txtValorMO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblValorTotal)
                            .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblRelacaoPecas)
                                    .addComponent(lstPecas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPago)
                                    .addComponent(txtS)
                                    .addComponent(txtN)
                                    .addComponent(txtPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFOrcamento)
                    .addComponent(btnCOrcamento)
                    .addComponent(btnRPeca))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAQuantidade, btnDQuantidade});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnConfirmar});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCOrcamento, btnFOrcamento, btnRPeca});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        pesquisarPeca();
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void txtCodClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodClienteActionPerformed

    private void txtCodPecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodPecaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodPecaActionPerformed

    private void btnCOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCOrcamentoActionPerformed
        cancelarOrcamento();
    }//GEN-LAST:event_btnCOrcamentoActionPerformed

    private void tblPecasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPecasMouseClicked
        selecionarPeca();
    }//GEN-LAST:event_tblPecasMouseClicked

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        confirmar();
        valorPecas();
        valorTotal();
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAQuantidadeActionPerformed
        aQuantidade();
    }//GEN-LAST:event_btnAQuantidadeActionPerformed

    private void btnDQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDQuantidadeActionPerformed
        dQuantidade();
    }//GEN-LAST:event_btnDQuantidadeActionPerformed

    private void btnRPecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRPecaActionPerformed
        removerPeca();
    }//GEN-LAST:event_btnRPecaActionPerformed

    private void btnFOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFOrcamentoActionPerformed
        finalizarOrcamento();
    }//GEN-LAST:event_btnFOrcamentoActionPerformed

    private void txtSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSActionPerformed
        txtPago.setText("S");
    }//GEN-LAST:event_txtSActionPerformed

    private void txtNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNActionPerformed
        txtPago.setText("N");
    }//GEN-LAST:event_txtNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAQuantidade;
    private javax.swing.JButton btnCOrcamento;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JButton btnDQuantidade;
    private javax.swing.JButton btnFOrcamento;
    private javax.swing.JButton btnRPeca;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblBusca;
    private javax.swing.JLabel lblCodCliente;
    private javax.swing.JLabel lblCodMoto;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblEstoque;
    private javax.swing.JLabel lblPago;
    private javax.swing.JLabel lblRelacaoPecas;
    private javax.swing.JLabel lblServico;
    private javax.swing.JLabel lblValorMO;
    private javax.swing.JLabel lblValorPecas;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JLabel lblValorUn;
    private javax.swing.JComboBox<String> lstPecas;
    private javax.swing.JTable tblPecas;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodCliente;
    private javax.swing.JTextField txtCodMoto;
    private javax.swing.JTextField txtCodPeca;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JRadioButton txtN;
    private javax.swing.JTextField txtPago;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JRadioButton txtS;
    private javax.swing.JTextArea txtServico;
    private javax.swing.JTextField txtValorMO;
    private javax.swing.JTextField txtValorPecas;
    private javax.swing.JTextField txtValorTotal;
    private javax.swing.JTextField txtValorUn;
    // End of variables declaration//GEN-END:variables
}
