import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class TesteJanelaProj {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JPanel painelTitulo,painelInicial,painelRegistar,painelLogin;
        JLabel rockstarTxt, usernameLegendaL, passLegendaL, usernameLegendaR, passLegendaR, pinL, pinR;
        JButton botaoRegistar1,botaoRegistar2,botaoLogin1,botaoLogin2;

        GestaoApp gestaoApp = new GestaoApp();
        gestaoApp.run();

        //Criar Frame
        JFrame f = new JFrame("Rockstar Inc");
        JFrame fPin = new JFrame("PIN");

        //Criar Paineis
        painelTitulo = new JPanel();
        painelTitulo.setBackground(new Color(255,178,102));
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));

        painelInicial = new JPanel();
        painelInicial.setBackground(new Color(255,178,102));
        painelInicial.setLayout(null);

        painelRegistar = new JPanel();
        painelRegistar.setBackground(new Color(255,178,102));
        painelRegistar.setLayout(null);

        painelLogin = new JPanel();
        painelLogin.setBackground(new Color(255,178,102));
        painelLogin.setLayout(null);

        JPanel painelPIN = new JPanel();
        painelPIN.setLayout(new BorderLayout());

        //Texto
        rockstarTxt = new JLabel("RockStar Inc");
        rockstarTxt.setFont(new Font("Magneto",Font.BOLD,120));

        usernameLegendaL = new JLabel("Username");
        usernameLegendaL.setFont(new Font("Calibri",Font.BOLD,15));
        usernameLegendaL.setBounds(440,135,100,30);

        passLegendaL = new JLabel("Password");
        passLegendaL.setFont(new Font("Calibri",Font.BOLD,15));
        passLegendaL.setBounds(695,135,100,30);

        usernameLegendaR = new JLabel("Username");
        usernameLegendaR.setFont(new Font("Calibri",Font.BOLD,15));
        usernameLegendaR.setBounds(440,105,100,30);

        passLegendaR = new JLabel("Password");
        passLegendaR.setFont(new Font("Calibri",Font.BOLD,15));
        passLegendaR.setBounds(695,105,100,30);

        pinL = new JLabel("PIN");
        pinL.setFont(new Font("Calibri",Font.BOLD,15));

        pinR = new JLabel("PIN");
        pinR.setFont(new Font("Calibri",Font.BOLD,15));
        pinR.setBounds(590,300,100,30);
        pinR.setVisible(false);

        //Botões
        botaoRegistar1=new JButton("Registar");
        botaoRegistar1.setFont(new Font("Calibri",Font.BOLD,30));
        botaoRegistar1.setBounds(350,300,200,80);

        botaoLogin1=new JButton("Login");
        botaoLogin1.setFont(new Font("Calibri",Font.BOLD,30));
        botaoLogin1.setBounds(650,300,200,80);

        botaoRegistar2=new JButton("Registar");
        botaoRegistar2.setFont(new Font("Calibri",Font.BOLD,30));
        botaoRegistar2.setBounds(500,360,200,80);

        botaoLogin2 = new JButton("Login");
        botaoLogin2.setFont(new Font("Calibri",Font.BOLD,30));
        botaoLogin2.setBounds(500,360,200,80);

        JButton okPIN = new JButton("OK");
        okPIN.setFont(new Font("Calibri",Font.BOLD,10));

        JButton randomPIN = new JButton("Gerar PIN");
        randomPIN.setFont(new Font("Calibri",Font.BOLD,10));
        randomPIN.setBounds(560,210,80,30);
        randomPIN.setVisible(false);

        JRadioButton rbotaoArtista = new JRadioButton("Artista",false);
        rbotaoArtista.setBounds(445,170,100,30);
        rbotaoArtista.setContentAreaFilled(false);
        rbotaoArtista.setBorderPainted(false);

        JRadioButton rbotaoUser = new JRadioButton("Usuário",false);
        rbotaoUser.setBounds(690,170,100,30);
        rbotaoUser.setContentAreaFilled(false);
        rbotaoUser.setBorderPainted(false);
        rbotaoUser.setSelected(true);

        ButtonGroup grupoBotoesRegistar = new ButtonGroup();
        grupoBotoesRegistar.add(rbotaoArtista);
        grupoBotoesRegistar.add(rbotaoUser);

        JButton voltar = new JButton("Voltar ao início");
        voltar.setFont(new Font("Calibri",Font.BOLD,15));
        voltar.setBounds(1000,420,150,80);


        //Caixas de texto

        JTextField cxUsernameL = new JTextField();
        cxUsernameL.setBounds(400,100,150,30);

        JPasswordField cxPassL = new JPasswordField();
        cxPassL.setBounds(650,100,150,30);

        JTextField cxUsernameR = new JTextField();
        cxUsernameR.setBounds(400,70,150,30);

        JPasswordField cxPassR = new JPasswordField();
        cxPassR.setBounds(650,70,150,30);

        JTextField cxPinL = new JTextField();
        cxPinL.setBounds(550,300,150,30);

        JTextField cxPinR = new JTextField();
        cxPinR.setBounds(550,260,100,30);
        cxPinR.setVisible(false);

        //----------------------------------------------------------------------------------------------

        //Painel Título

        painelTitulo.add(rockstarTxt);

        //----------------------------------------------------------------------------------------------

        //Painel Inicial

        //Adicionar botões ao painel
        painelInicial.add(botaoRegistar1);
        botaoRegistar1.addActionListener(new ActionListener() {     //Segue para o painel de Registrar
            @Override
            public void actionPerformed(ActionEvent e) {
                f.remove(painelInicial);
                f.add(painelRegistar);
                f.repaint();
                f.revalidate();
            }
        });

        painelInicial.add(botaoLogin1);

        botaoLogin1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        botaoLogin1.addActionListener(new ActionListener() {        //Segue para o painel de Login
            @Override
            public void actionPerformed(ActionEvent e) {
                f.remove(painelInicial);
                f.add(painelLogin);
                f.repaint();
                f.revalidate();
            }
        });


        //----------------------------------------------------------------------------------------------

        //Painel Login

        //Adicionar componentes
        painelLogin.add(cxUsernameL);
        painelLogin.add(cxPassL);
        painelLogin.add(botaoLogin2);
        botaoLogin2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = cxUsernameL.getText();
                String password = cxPassL.getText();

                if (gestaoApp.rockstar.tipoUtilizador(username) == 1) {
                    Cliente c = gestaoApp.rockstar.loginCliente(username, password);
                    GestaoApp gestaoApp1 = new GestaoApp();
                    if(c != null){
                        new InterfaceCliente(c, gestaoApp1);
                    }

                } else if (gestaoApp.rockstar.tipoUtilizador(username) == 2) {
                    if (gestaoApp.rockstar.loginArtista(username, password)) {
                        fPin.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Utilizador não existe", "",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        painelLogin.add(usernameLegendaL);
        painelLogin.add(passLegendaL);


        painelLogin.add(voltar);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.remove(painelLogin);
                f.add(painelInicial);
                f.repaint();
                f.revalidate();
            }
        });

        //----------------------------------------------------------------------------------------------

        //Painel Registar

        //Adicionar componentes
        painelRegistar.add(cxUsernameR);
        painelRegistar.add(cxPassR);
        painelRegistar.add(botaoRegistar2);
        botaoRegistar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=cxUsernameR.getText();
                String password=cxPassR.getText();
                if (rbotaoUser.isSelected()){
                    Cliente c = gestaoApp.rockstar.registarCliente(username,password);

                }else {
                    String pin= cxPinR.getText();
                    gestaoApp.rockstar.registarArtista(username,password,pin);
                }
                cxUsernameR.setText("");
                cxPassR.setText("");
                f.remove(painelRegistar);
                f.add(painelLogin);
                f.repaint();
                f.revalidate();
            }
        });
        painelRegistar.add(usernameLegendaR);
        painelRegistar.add(passLegendaR);
        painelRegistar.add(rbotaoArtista);
        rbotaoArtista.addActionListener(new ActionListener() {  //Aparece PIN
            @Override
            public void actionPerformed(ActionEvent e) {
                pinR.setVisible(rbotaoArtista.isSelected());
                cxPinR.setVisible(rbotaoArtista.isSelected());
                randomPIN.setVisible(rbotaoArtista.isSelected());
            }
        });
        painelRegistar.add(rbotaoUser);
        rbotaoUser.addActionListener(new ActionListener() { //Desaparece PIN
            @Override
            public void actionPerformed(ActionEvent e) {
                pinR.setVisible(!rbotaoUser.isSelected());
                cxPinR.setVisible(!rbotaoUser.isSelected());
                randomPIN.setVisible(!rbotaoUser.isSelected());
            }
        });
        painelRegistar.add(pinR);
        painelRegistar.add(cxPinR);
        painelRegistar.add(randomPIN);
        randomPIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cxPinR.setText(gestaoApp.rockstar.gerarPin());
            }
        });

        //----------------------------------------------------------------------------------------------

        //Adicionar paineis/botões às frames
        f.add(painelTitulo,BorderLayout.NORTH);
        f.add(painelInicial);

        fPin.add(pinL,BorderLayout.WEST);
        fPin.add(cxPinL,BorderLayout.CENTER);
        fPin.add(okPIN,BorderLayout.SOUTH);

        okPIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pin = cxPinL.getText();
                String username = cxUsernameL.getText();
                gestaoApp.rockstar.verificarPINArtista(username, pin);
            }
        });

        //Configurar frames
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1200,800);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Salva os dados ao fechar a janela
                GestaoApp gestaoApp = new GestaoApp();
                gestaoApp.atualizaficheiro(gestaoApp.rockstar.getClientes(), gestaoApp.rockstar.getArtistas(),
                        gestaoApp.rockstar.getMusicas(), gestaoApp.rockstar.getPlaylists());
            }
        });


        fPin.pack();
        fPin.setLocationRelativeTo(null);
        fPin.setResizable(false);
        fPin.setVisible(false);


    }
}
