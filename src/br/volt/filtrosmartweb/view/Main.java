/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.volt.filtrosmartweb.view;

import br.volt.filtrosmartweb.model.FiltroSmartWeb;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ulana
 */
public class Main extends javax.swing.JFrame {
Color verde = new Color(0,255,102);
Color vermelho = Color.red;
Color cinza = Color.gray;
Boolean loop = false;
FiltroSmartWeb equipamento;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        enableComponents(false);
        
        initListeners();
        
        
        
        showTomada1.setBackground(cinza);
        showTomada2.setBackground(cinza);
        showTomada3.setBackground(cinza);
        showTomada4.setBackground(cinza);
        showTomada5.setBackground(cinza);
        showTomada6.setBackground(cinza);
        showTomada7.setBackground(cinza);
        showTomada8.setBackground(cinza);
        showTomada9.setBackground(cinza);
        showTomada10.setBackground(cinza);
        
        updateInfo();

        
    }
    
    
    
    private void enableComponents(boolean status){
        
        Component ether[] = panelEhernet.getComponents();
    for (Component ether1 : ether) {
        ether1.setEnabled(status);
    }
        
        ether = panelNamePort.getComponents();
        for(Component ether1:ether){
            ether1.setEnabled(status);
        }
        
        userField.setEnabled(!status);
        passField.setEnabled(!status);
        
        showTomada1.setBackground(cinza);
        showTomada2.setBackground(cinza);
        showTomada3.setBackground(cinza);
        showTomada4.setBackground(cinza);
        showTomada5.setBackground(cinza);
        showTomada6.setBackground(cinza);
        showTomada7.setBackground(cinza);
        showTomada8.setBackground(cinza);
        showTomada9.setBackground(cinza);
        showTomada10.setBackground(cinza);
    }
    
    private void initListeners(){
        
        cbNomePorta.addItemListener((ItemEvent e) -> {
            namePortField.setText(equipamento.getAc_nome(Integer.parseInt((String)cbNomePorta.getSelectedItem())));
        });
        
        btRenamePort.addActionListener((ActionEvent e) -> {
            if(cbNomePorta.getSelectedIndex()>0){
                
                equipamento.controlTomada(Integer.parseInt((String)cbNomePorta.getSelectedItem()), 2, namePortField.getText());
                
                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!!");
                
            }else JOptionPane.showMessageDialog(null, "Selecione uma porta!");
        });
        btGetEther.addActionListener((ActionEvent e) -> {
            retrieveEtherConfig();
        });
        
        btSaveEthernet.addActionListener((ActionEvent e) -> {
            loop=false;
            
            int resp = equipamento.configEthernet(cbDhcp.isSelected(), hostname.getText(),
                    ip.getText(),
                    gateway.getText(),
                    mask.getText(),
                    dns1.getText(),
                    dns2.getText());
            
            System.out.println("Resposta: "+resp);
            if(resp == 200){
                ipField.setText(equipamento.getDevip());
            }else if(resp == 401) JOptionPane.showMessageDialog(null,"Falha de autenticação na requisição");
            
            btConnect.setText("Conectar");
            //userField.setEnabled(true);
            //passField.setEnabled(true);
            enableComponents(false);
        });
        
        
        showTomada1.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(1, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada2.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(2, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada3.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(3, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada4.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(4, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada5.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(5, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada6.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(6, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada7.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(7, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada8.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(8, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada9.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(9, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        showTomada10.addActionListener((ActionEvent e) -> {
            if(loop)
                controlTomada(10, 0);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        
        sw1.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(1, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        sw2.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(2, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        sw3.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(3, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        sw4.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(4, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        sw5.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(5, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        sw6.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(6, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        sw7.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(7, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        sw8.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(8, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        sw9.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(9, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        sw10.addActionListener((ActionEvent e) -> {
            if(loop) controlTomada(10, 1);
            else JOptionPane.showMessageDialog(null, "Equipamento desconectado!!");
        });
        
        
        
    }
    
    private void updateInfo(){
        
    }
    
    private void startMonitor(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();
        btConnect = new javax.swing.JButton();
        showTomada1 = new javax.swing.JButton();
        showTomada2 = new javax.swing.JButton();
        showTomada3 = new javax.swing.JButton();
        showTomada4 = new javax.swing.JButton();
        showTomada5 = new javax.swing.JButton();
        showTomada6 = new javax.swing.JButton();
        showTomada7 = new javax.swing.JButton();
        showTomada8 = new javax.swing.JButton();
        showTomada9 = new javax.swing.JButton();
        showTomada10 = new javax.swing.JButton();
        uptimeField = new javax.swing.JTextField();
        tempfield = new javax.swing.JTextField();
        dataHoraField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        nome1 = new javax.swing.JTextField();
        nome2 = new javax.swing.JTextField();
        nome3 = new javax.swing.JTextField();
        nome4 = new javax.swing.JTextField();
        nome5 = new javax.swing.JTextField();
        nome6 = new javax.swing.JTextField();
        nome7 = new javax.swing.JTextField();
        nome8 = new javax.swing.JTextField();
        nome9 = new javax.swing.JTextField();
        nome10 = new javax.swing.JTextField();
        sw1 = new javax.swing.JCheckBox();
        sw5 = new javax.swing.JCheckBox();
        sw7 = new javax.swing.JCheckBox();
        sw8 = new javax.swing.JCheckBox();
        sw9 = new javax.swing.JCheckBox();
        sw10 = new javax.swing.JCheckBox();
        sw3 = new javax.swing.JCheckBox();
        sw6 = new javax.swing.JCheckBox();
        sw4 = new javax.swing.JCheckBox();
        sw2 = new javax.swing.JCheckBox();
        panelEhernet = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        gateway = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbDhcp = new javax.swing.JCheckBox();
        dns1 = new javax.swing.JTextField();
        btSaveEthernet = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dns2 = new javax.swing.JTextField();
        ip = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        hostname = new javax.swing.JTextField();
        mask = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btGetEther = new javax.swing.JButton();
        panelNamePort = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cbNomePorta = new javax.swing.JComboBox<>();
        namePortField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btRenamePort = new javax.swing.JButton();

        jCheckBox3.setText("Habilitada");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuário:");

        userField.setColumns(8);
        userField.setText("admin");

        jLabel2.setText("Senha:  ");

        passField.setColumns(8);
        passField.setText("voltvolt");

        btConnect.setText("Conectar");
        btConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConnectActionPerformed(evt);
            }
        });

        showTomada1.setText("TOMADA 1");

        showTomada2.setText("TOMADA 2");

        showTomada3.setText("TOMADA 3");

        showTomada4.setText("TOMADA 4");

        showTomada5.setText("TOMADA 5");

        showTomada6.setText("TOMADA 6");

        showTomada7.setText("TOMADA 7");

        showTomada8.setText("TOMADA 8");

        showTomada9.setText("TOMADA 9");

        showTomada10.setText("TOMADA10");

        uptimeField.setEditable(false);
        uptimeField.setColumns(8);

        tempfield.setEditable(false);
        tempfield.setColumns(8);

        dataHoraField.setEditable(false);
        dataHoraField.setColumns(12);

        jLabel3.setText("Uptime:");

        jLabel4.setText("Temperatura: ");

        jLabel5.setText("Data e Hora: ");

        ipField.setColumns(10);
        ipField.setText("192.168.0.90");

        jLabel6.setText("IP:");

        nome1.setEditable(false);
        nome1.setColumns(6);

        nome2.setEditable(false);
        nome2.setColumns(6);

        nome3.setEditable(false);
        nome3.setColumns(6);

        nome4.setEditable(false);
        nome4.setColumns(6);

        nome5.setEditable(false);
        nome5.setColumns(6);

        nome6.setEditable(false);
        nome6.setColumns(6);

        nome7.setEditable(false);
        nome7.setColumns(6);

        nome8.setEditable(false);
        nome8.setColumns(6);

        nome9.setEditable(false);
        nome9.setColumns(6);

        nome10.setEditable(false);
        nome10.setColumns(6);

        sw1.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw1.setText("Desabilitada");

        sw5.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw5.setText("Habilitada");

        sw7.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw7.setText("Habilitada");

        sw8.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw8.setText("Habilitada");

        sw9.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw9.setText("Habilitada");

        sw10.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw10.setText("Habilitada");

        sw3.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw3.setText("Habilitada");

        sw6.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw6.setText("Habilitada");

        sw4.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw4.setText("Habilitada");

        sw2.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        sw2.setText("Habilitada");

        panelEhernet.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Gateway");

        gateway.setColumns(10);

        jLabel8.setText("DNS Primário");

        cbDhcp.setText("DHCP");

        dns1.setColumns(10);

        btSaveEthernet.setText("Salvar");

        jLabel9.setText("DNS Secundário");

        jLabel10.setText("IP");

        dns2.setColumns(10);

        ip.setColumns(10);

        jLabel11.setText("Hostname");

        jLabel12.setText("Máscara de rede");

        hostname.setColumns(12);

        mask.setColumns(10);

        jLabel13.setText("Configuração de Rede");

        btGetEther.setText("Atualizar info");

        javax.swing.GroupLayout panelEhernetLayout = new javax.swing.GroupLayout(panelEhernet);
        panelEhernet.setLayout(panelEhernetLayout);
        panelEhernetLayout.setHorizontalGroup(
            panelEhernetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEhernetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEhernetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSaveEthernet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelEhernetLayout.createSequentialGroup()
                        .addGroup(panelEhernetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDhcp)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btGetEther))
                    .addGroup(panelEhernetLayout.createSequentialGroup()
                        .addGroup(panelEhernetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(mask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(hostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEhernetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(gateway, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(dns1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(dns2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelEhernetLayout.setVerticalGroup(
            panelEhernetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEhernetLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelEhernetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelEhernetLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(8, 8, 8)
                        .addComponent(cbDhcp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGap(5, 5, 5)
                        .addComponent(hostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addGap(0, 0, 0)
                        .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)
                        .addComponent(mask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEhernetLayout.createSequentialGroup()
                        .addComponent(btGetEther)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(0, 0, 0)
                        .addComponent(gateway, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(0, 0, 0)
                        .addComponent(dns1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(0, 0, 0)
                        .addComponent(dns2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSaveEthernet)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelNamePort.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setText("Renomear porta");

        cbNomePorta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        namePortField.setColumns(10);

        jLabel15.setText("Porta");

        jLabel16.setText("Nome");

        btRenamePort.setText("Salvar");

        javax.swing.GroupLayout panelNamePortLayout = new javax.swing.GroupLayout(panelNamePort);
        panelNamePort.setLayout(panelNamePortLayout);
        panelNamePortLayout.setHorizontalGroup(
            panelNamePortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNamePortLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNamePortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btRenamePort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelNamePortLayout.createSequentialGroup()
                        .addGroup(panelNamePortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNamePortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel14)
                                .addGroup(panelNamePortLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbNomePorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelNamePortLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(namePortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelNamePortLayout.setVerticalGroup(
            panelNamePortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNamePortLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNamePortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNomePorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNamePortLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namePortField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(btRenamePort)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTomada1)
                            .addComponent(nome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sw1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sw2)
                            .addComponent(showTomada2)
                            .addComponent(nome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sw3)
                            .addComponent(showTomada3)
                            .addComponent(nome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTomada4)
                            .addComponent(nome4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sw4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTomada5)
                            .addComponent(nome5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sw5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTomada6)
                            .addComponent(nome6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sw6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTomada7)
                            .addComponent(nome7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sw7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTomada8)
                            .addComponent(nome8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sw8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTomada9)
                            .addComponent(nome9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sw9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showTomada10)
                            .addComponent(nome10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sw10)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(48, 48, 48)
                                                .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btConnect))
                                    .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dataHoraField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(uptimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tempfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(204, 204, 204)
                        .addComponent(panelNamePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelEhernet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btConnect))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(uptimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tempfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(dataHoraField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelEhernet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelNamePort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showTomada1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showTomada10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sw1)
                    .addComponent(sw5)
                    .addComponent(sw7)
                    .addComponent(sw8)
                    .addComponent(sw9)
                    .addComponent(sw10)
                    .addComponent(sw3)
                    .addComponent(sw6)
                    .addComponent(sw4)
                    .addComponent(sw2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConnectActionPerformed
        
        equipamento = new FiltroSmartWeb(userField.getText(),passField.getText(),ipField.getText());
        int resp = equipamento.HTTPGETEquipInfo();
        
        
        
        if(resp == 200){
            
            if(!loop){ 
                //System.out.println(resp);
                System.out.println("logado!!");
                //userField.setEnabled(false);
                //passField.setEnabled(false);
                enableComponents(true);
                //System.out.println(resp[1]);
                

                
                equipamento.HTTPGETEquipInfo();
                loop = true;
                btConnect.setText("Desconectar");
                retrieveEtherConfig();
                loopInfo();
            
            }else{
                loop=false;
                btConnect.setText("Conectar");
                //userField.setEnabled(true);
                //passField.setEnabled(true);
                enableComponents(false);
            }
        }else if(resp>=400 && resp < 405){
            System.out.println(resp);
            System.out.println("Falha na autenticação. Código: "+resp);
            
            
        }else if(resp == 500){
           JOptionPane.showMessageDialog(null, "Equipamento não encontrado!!");
        }else System.out.println("Erro desconhecido. Código: "+resp);
    }//GEN-LAST:event_btConnectActionPerformed

    private void loopInfo(){
        new Thread(() -> {
            while(loop){
                equipamento.HTTPGETEquipInfo();
                //System.out.println(jo);
                showTomada1.setBackground(equipamento.getAc(1) == 2?cinza:equipamento.getAc(1) == 1 ? verde:vermelho);
                showTomada2.setBackground(equipamento.getAc(2) == 2?cinza:equipamento.getAc(2) == 1 ? verde:vermelho);
                showTomada3.setBackground(equipamento.getAc(3) == 2?cinza:equipamento.getAc(3) == 1 ? verde:vermelho);
                showTomada4.setBackground(equipamento.getAc(4) == 2?cinza:equipamento.getAc(4) == 1 ? verde:vermelho);
                showTomada5.setBackground(equipamento.getAc(5) == 2?cinza:equipamento.getAc(5) == 1 ? verde:vermelho);
                showTomada6.setBackground(equipamento.getAc(6) == 2?cinza:equipamento.getAc(6) == 1 ? verde:vermelho);
                showTomada7.setBackground(equipamento.getAc(7) == 2?cinza:equipamento.getAc(7) == 1 ? verde:vermelho);
                showTomada8.setBackground(equipamento.getAc(8) == 2?cinza:equipamento.getAc(8) == 1 ? verde:vermelho);
                showTomada9.setBackground(equipamento.getAc(9) == 2?cinza:equipamento.getAc(9) == 1 ? verde:vermelho);
                showTomada10.setBackground(equipamento.getAc(10) == 2?cinza:equipamento.getAc(10) == 1 ? verde:vermelho);
                
                sw1.setText(equipamento.getAc(1) == 2?"DESABILITADA":"HABILITADA");
                sw1.setSelected(equipamento.getAc(1) != 2);
                nome1.setText(equipamento.getAc_nome(1));
                
                
                
                sw2.setText(equipamento.getAc(2) == 2?"DESABILITADA":"HABILITADA");
                sw2.setSelected(equipamento.getAc(2) != 2);
                nome2.setText(equipamento.getAc_nome(2));
                
                sw3.setText(equipamento.getAc(3) == 2?"DESABILITADA":"HABILITADA");
                sw3.setSelected(equipamento.getAc(3) != 2);
                nome3.setText(equipamento.getAc_nome(3));
                
                sw4.setText(equipamento.getAc(4) == 2?"DESABILITADA":"HABILITADA");
                sw4.setSelected(equipamento.getAc(4) != 2);
                nome4.setText(equipamento.getAc_nome(4));
                
                sw5.setText(equipamento.getAc(5) == 2?"DESABILITADA":"HABILITADA");
                sw5.setSelected(equipamento.getAc(5) != 2);
                nome5.setText(equipamento.getAc_nome(5));
                
                sw6.setText(equipamento.getAc(6) == 2?"DESABILITADA":"HABILITADA");
                sw6.setSelected(equipamento.getAc(6) != 2);
                nome6.setText(equipamento.getAc_nome(6));
                
                sw7.setText(equipamento.getAc(7) == 2?"DESABILITADA":"HABILITADA");
                sw7.setSelected(equipamento.getAc(7) != 2);
                nome7.setText(equipamento.getAc_nome(7));
                
                sw8.setText(equipamento.getAc(8) == 2?"DESABILITADA":"HABILITADA");
                sw8.setSelected(equipamento.getAc(8) != 2);
                nome8.setText(equipamento.getAc_nome(8));
                
                sw9.setText(equipamento.getAc(9) == 2?"DESABILITADA":"HABILITADA");
                sw9.setSelected(equipamento.getAc(9) != 2);
                nome9.setText(equipamento.getAc_nome(9));
                
                sw10.setText(equipamento.getAc(10) == 2?"DESABILITADA":"HABILITADA");
                sw10.setSelected(equipamento.getAc(10) != 2);
                nome10.setText(equipamento.getAc_nome(10));
                
                uptimeField.setText(equipamento.getUptime());
                tempfield.setText(equipamento.getTemp()+ " ºC");
                
                dataHoraField.setText(equipamento.getDate()+" - "+equipamento.getTime());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
            }
        }).start();
    }
    
    private void retrieveEtherConfig(){
        
        hostname.setText(equipamento.getDevhost());
        ip.setText(equipamento.getDevip());
        mask.setText(equipamento.getDevmask());
        gateway.setText(equipamento.getDevgtw());
        dns1.setText(equipamento.getDevdns1());
        dns2.setText(equipamento.getDevdns2());
        cbDhcp.setSelected(equipamento.getDevdhcp().equals(true));
    }
    
    private void controlTomada(int tomada, int op){
        
       int resp = equipamento.controlTomada(tomada, op, null);
       System.out.println("Resposta: "+resp);
        
        
        
        

        
    }
    
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(()-> {
                new Main().setVisible(true);
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConnect;
    private javax.swing.JButton btGetEther;
    private javax.swing.JButton btRenamePort;
    private javax.swing.JButton btSaveEthernet;
    private javax.swing.JCheckBox cbDhcp;
    private javax.swing.JComboBox<String> cbNomePorta;
    private javax.swing.JTextField dataHoraField;
    private javax.swing.JTextField dns1;
    private javax.swing.JTextField dns2;
    private javax.swing.JTextField gateway;
    private javax.swing.JTextField hostname;
    private javax.swing.JTextField ip;
    private javax.swing.JTextField ipField;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField mask;
    private javax.swing.JTextField namePortField;
    private javax.swing.JTextField nome1;
    private javax.swing.JTextField nome10;
    private javax.swing.JTextField nome2;
    private javax.swing.JTextField nome3;
    private javax.swing.JTextField nome4;
    private javax.swing.JTextField nome5;
    private javax.swing.JTextField nome6;
    private javax.swing.JTextField nome7;
    private javax.swing.JTextField nome8;
    private javax.swing.JTextField nome9;
    private javax.swing.JPanel panelEhernet;
    private javax.swing.JPanel panelNamePort;
    private javax.swing.JPasswordField passField;
    private javax.swing.JButton showTomada1;
    private javax.swing.JButton showTomada10;
    private javax.swing.JButton showTomada2;
    private javax.swing.JButton showTomada3;
    private javax.swing.JButton showTomada4;
    private javax.swing.JButton showTomada5;
    private javax.swing.JButton showTomada6;
    private javax.swing.JButton showTomada7;
    private javax.swing.JButton showTomada8;
    private javax.swing.JButton showTomada9;
    private javax.swing.JCheckBox sw1;
    private javax.swing.JCheckBox sw10;
    private javax.swing.JCheckBox sw2;
    private javax.swing.JCheckBox sw3;
    private javax.swing.JCheckBox sw4;
    private javax.swing.JCheckBox sw5;
    private javax.swing.JCheckBox sw6;
    private javax.swing.JCheckBox sw7;
    private javax.swing.JCheckBox sw8;
    private javax.swing.JCheckBox sw9;
    private javax.swing.JTextField tempfield;
    private javax.swing.JTextField uptimeField;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
