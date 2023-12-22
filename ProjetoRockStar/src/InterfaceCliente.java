import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class InterfaceCliente implements Serializable {
    private JFrame janelaCliente;
    private JPanel painelPesquisarCliente, painelTituloCliente, painelMenu, painelPlayList, painelCarrinho;
    private JLabel atributoPesquisarLegenda, tituloCliente, username, ordenarMusicasCliente, listacompras, valorTotalPagar, saldoCliente;
    private JButton botaoPesquisar, botaoPlayList, botaoCarrinho, adicionarCarrinho, adicionarPlayList,
            removerPlayList, alterarVisibilidade, criarNovaPlayList, removerMusicaPlayList,
            removerMusicaCarrinho, carregarSaldo, finalizarPagamento;
    private JComboBox atributoPesquisa, ordenarMusicaPor, caixaListarPlayLists;
    private JRadioButton botaoAscendenteCliente, botaoDescendenteCliente, botaoTodasAsMusicas, botaoParaPesquisarMusicas;
    private ButtonGroup botaoOrdem, grupoPesquisa;
    private JTextField caixaTextoPesquisa, mostrarValorPagar, mostrarSaldoCliente;
    private JTable tabelaResultadoPesquisa, listaMusicasPlayList, listaMusicasCarrinho;

    public InterfaceCliente() {

        Aplicacao app=new Aplicacao();

        //Criar janela
        janelaCliente = new JFrame();

        //Criar Painel Pesquisar --------------------------------------------------------------
        painelPesquisarCliente = new JPanel();
        painelPesquisarCliente.setBackground(new Color(225, 145, 102));
        painelPesquisarCliente.setBounds(340, 200, 780, 500);
        painelPesquisarCliente.setLayout(null);

        //Criar Componentes do Painel Pesquisar

        //JLabel
        ordenarMusicasCliente = new JLabel("ORDENAR POR:");
        ordenarMusicasCliente.setBounds(450,10,150,40);
        atributoPesquisarLegenda = new JLabel("PESQUISAR POR:");
        atributoPesquisarLegenda.setBounds(250,10,150,40);
        atributoPesquisarLegenda.setVisible(false);


        //JTextField
        caixaTextoPesquisa = new JTextField("pesquisa");
        caixaTextoPesquisa.setBounds(50,120,180,40);
        caixaTextoPesquisa.setVisible(false);

        //JButton
        adicionarPlayList = new JButton("ADICIONAR A PLAYLIST");
        adicionarPlayList.setBounds(410,450,200,40);
        adicionarCarrinho = new JButton("ADICIONAR AO CARRINHO");
        adicionarCarrinho.setBounds(180,450 ,200,40);
        JButton ordenarPesquisa = new JButton("ORDENAR");
        ordenarPesquisa.setBounds(650,100,100,30);
        ordenarPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel listarItems = new DefaultTableModel();
                String selecao = (String) ordenarMusicaPor.getSelectedItem();
                ArrayList<Musica> listaM = new ArrayList<>();
                listaM.addAll(app.getMusicas());

                if (botaoTodasAsMusicas.isSelected()){

                    if ("TÍTULO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        app.ordenarMusicasCrescentePorTitulo(listaM);
                    } else if ("TÍTULO".equals(selecao) && botaoDescendenteCliente.isSelected()) {
                        app.ordendarMusicasDecrescentePorTitulo(listaM);
                    } else if ("GÉNERO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        app.ordenarMusicasCrescentePorGenero(listaM);
                    } else if ("GÉNERO".equals(selecao) && botaoDescendenteCliente.isSelected()){
                        app.ordenarMusicasDecrescentePorGenero(listaM);
                    }

                } else if (botaoParaPesquisarMusicas.isSelected()) {

                    if ("TÍTULO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        //app.ordenarAlbunsCrescentePorTitulo(listaA);
                    } else if ("TÍTULO".equals(selecao) && botaoDescendenteCliente.isSelected()) {
                        //app.ordendarAlbunsDecrescentePorTitulo(listaA);
                    } else if ("GÉNERO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        //app.ordenarAlbunsCrescentePorGenero(listaA);
                    } else if ("GÉNERO".equals(selecao) && botaoDescendenteCliente.isSelected()){
                        //app.ordenarAlbunsDecrescentePorGenero(listaA);
                    }

                }
                // Adicionar uma coluna à tabela
                listarItems.addColumn("TÍTULO");
                listarItems.addColumn("DATA");
                listarItems.addColumn("DURACAO");
                listarItems.addColumn("GENERO");
                listarItems.addColumn("ESTADO");
                listarItems.addColumn("PREÇO (€)");

                // Adicionar os títulos das colunas Na primeira linha
                listarItems.addRow(new Object[]{"TÍTULO", "DATA", "DURACAO", "GENERO", "ESTADO","PREÇO (€)"});

                // Adicionar os elementos do ArrayList à tabela
                for (Musica musica : listaM) {
                    if (musica instanceof MusicaPaga) {
                        listarItems.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                musica.getGenero(), musica.getEstado(), ((MusicaPaga) musica).getPreco()});
                    }else {
                        listarItems.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                musica.getGenero(), musica.getEstado(), "0"});
                    }
                }

                tabelaResultadoPesquisa.setModel(listarItems);

            }
        });


        //JComboBox
        ordenarMusicaPor = new JComboBox<>();
        ordenarMusicaPor.setBounds(450, 60, 150, 40);
        ordenarMusicaPor.addItem("TÍTULO");
        ordenarMusicaPor.addItem("GÉNERO");
        atributoPesquisa = new JComboBox<>();
        atributoPesquisa.setBounds(250, 60, 150, 40);
        atributoPesquisa.addItem("TÍTULO");
        atributoPesquisa.addItem("ARTISTA");
        atributoPesquisa.setVisible(false);

        //JTable
        tabelaResultadoPesquisa = new JTable();
        tabelaResultadoPesquisa.setBounds(50,200,800,200);
        JScrollPane scrollPane = new JScrollPane(tabelaResultadoPesquisa, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //JRadioButton
        botaoTodasAsMusicas = new JRadioButton("Ver todas as músicas");
        botaoTodasAsMusicas.setBounds(50, 15, 150, 40);
        botaoTodasAsMusicas.setBackground(null);
        botaoTodasAsMusicas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                caixaTextoPesquisa.setVisible(!botaoTodasAsMusicas.isSelected());
                atributoPesquisa.setVisible(!botaoTodasAsMusicas.isSelected());
                atributoPesquisarLegenda.setVisible(!botaoTodasAsMusicas.isSelected());

                DefaultTableModel listarMusicas = new DefaultTableModel();
                ArrayList<Musica> listaM = new ArrayList<>();
                listaM = app.getMusicas();


                // Adicionar uma coluna à tabela
                listarMusicas.addColumn("TÍTULO");
                listarMusicas.addColumn("DATA");
                listarMusicas.addColumn("DURACAO");
                listarMusicas.addColumn("GENERO");
                listarMusicas.addColumn("ESTADO");
                listarMusicas.addColumn("PREÇO (€)");

                // Adicionar os títulos das colunas Na primeira linha
                listarMusicas.addRow(new Object[]{"TÍTULO", "DATA", "DURACAO", "GENERO", "ESTADO", "PREÇO (€)"});

                // Adicionar os elementos do ArrayList à tabela
                for (Musica musica : listaM) {
                    if (musica instanceof MusicaPaga) {
                        listarMusicas.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                musica.getGenero(), musica.getEstado(), ((MusicaPaga) musica).getPreco()});
                    }else {
                        listarMusicas.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                musica.getGenero(), musica.getEstado(), "0"});
                    }
                }
                tabelaResultadoPesquisa.setModel(listarMusicas);
            }
        });

        botaoParaPesquisarMusicas = new JRadioButton("Pesquisar música");
        botaoParaPesquisarMusicas.setBounds(50,70,150,40);
        botaoParaPesquisarMusicas.setBackground(null);
        botaoParaPesquisarMusicas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caixaTextoPesquisa.setVisible(botaoParaPesquisarMusicas.isSelected());
                atributoPesquisa.setVisible(botaoParaPesquisarMusicas.isSelected());
                atributoPesquisarLegenda.setVisible(botaoParaPesquisarMusicas.isSelected());

            }
        });
        botaoAscendenteCliente = new JRadioButton("Ascendente");
        botaoAscendenteCliente.setBounds(650, 15, 100, 20);
        botaoAscendenteCliente.setBackground(null);
        botaoAscendenteCliente.setSelected(true);
        botaoDescendenteCliente = new JRadioButton("Descendente");
        botaoDescendenteCliente.setBounds(650,45,130,20);
        botaoDescendenteCliente.setBackground(null);

        //ButtonGroup
        grupoPesquisa = new ButtonGroup();
        grupoPesquisa.add(botaoTodasAsMusicas);
        grupoPesquisa.add(botaoParaPesquisarMusicas);
        botaoOrdem = new ButtonGroup();
        botaoOrdem.add(botaoAscendenteCliente);
        botaoOrdem.add(botaoDescendenteCliente);

        //Adicionar Componentes ao Painel Pesquisar

        painelPesquisarCliente.add(ordenarMusicasCliente);
        painelPesquisarCliente.add(ordenarMusicaPor);
        painelPesquisarCliente.add(botaoDescendenteCliente);
        painelPesquisarCliente.add(botaoAscendenteCliente);
        painelPesquisarCliente.add(tabelaResultadoPesquisa);
        painelPesquisarCliente.add(scrollPane, BorderLayout.CENTER);
        painelPesquisarCliente.add(ordenarPesquisa);
        painelPesquisarCliente.add(botaoParaPesquisarMusicas);
        painelPesquisarCliente.add(botaoTodasAsMusicas);
        painelPesquisarCliente.add(adicionarCarrinho);
        painelPesquisarCliente.add(adicionarPlayList);
        painelPesquisarCliente.add(caixaTextoPesquisa);
        painelPesquisarCliente.add(atributoPesquisa);
        painelPesquisarCliente.add(atributoPesquisarLegenda);

        //Criar painel PlayList------------------------------------------------------------------------
        painelPlayList = new JPanel();
        painelPlayList.setBackground(new Color(225, 145, 102));
        painelPlayList.setBounds(340, 200, 780, 500);
        painelPlayList.setLayout(null);

        //Criar Componentes painel PlayList
        caixaListarPlayLists = new JComboBox<>();
        caixaListarPlayLists.setBounds(50,0,400,40);
        listaMusicasPlayList = new JTable();
        listaMusicasPlayList.setBounds(50,50,400,380);
        removerPlayList = new JButton("REMOVER PLAYLIST");
        removerPlayList.setBounds(500, 50, 220,40);
        alterarVisibilidade = new JButton("ALTERAR VISIBILIDADE");
        alterarVisibilidade.setBounds(500,100,220,40);
        criarNovaPlayList = new JButton("CRIAR NOVA PLAYLIST");
        criarNovaPlayList.setBounds(500, 150, 220,40);
        removerMusicaPlayList = new JButton("REMOVER MÚSICA PLAYLIST");
        removerMusicaPlayList.setBounds(500, 200, 220,40);

        //Adicionar componentes ao painel
        painelPlayList.add(caixaListarPlayLists);
        painelPlayList.add(listaMusicasPlayList);
        painelPlayList.add(removerPlayList);
        painelPlayList.add(alterarVisibilidade);
        painelPlayList.add(criarNovaPlayList);
        painelPlayList.add(removerMusicaPlayList);

        //Criar painel Carrinho  ------------------------------------------------------------------------
        painelCarrinho = new JPanel();
        painelCarrinho.setBackground(new Color(225, 145, 102));
        painelCarrinho.setBounds(340, 200, 780, 500);
        painelCarrinho.setLayout(null);


        //Criar componentes do Painel Carrinho
        listacompras = new JLabel ("LISTA DE MUSICAS DENTRO DO CARRINHO");
        listacompras.setBounds(50,0,280,40);
        listaMusicasCarrinho = new JTable();
        listaMusicasCarrinho.setBounds(50, 50,250,300);
        removerMusicaCarrinho = new JButton("REMOVER MÚSICA CARRINHO");
        removerMusicaCarrinho.setBounds(400, 50, 250,40);
        carregarSaldo = new JButton("CARREGAR SALDO");
        carregarSaldo.setBounds(400,100,250,40);
        finalizarPagamento = new JButton("FINALIZAR PAGAMENTO");
        finalizarPagamento.setBounds(400,150,250,40);
        valorTotalPagar = new JLabel("VALOR TOTAL");
        valorTotalPagar.setBounds(400, 200, 250,40);
        mostrarValorPagar = new JTextField();
        mostrarValorPagar.setBounds(400,250,100,40);
        mostrarValorPagar.setEditable(false);
        mostrarSaldoCliente = new JTextField();
        mostrarSaldoCliente.setBounds(550,250,100,40);
        mostrarSaldoCliente.setEditable(false);
        saldoCliente = new JLabel("SALDO");
        saldoCliente.setBounds(550,200, 250, 40);



        //Adicionar Componentes ao Carrinho
        painelCarrinho.add(listacompras);
        painelCarrinho.add(listacompras);
        painelCarrinho.add(listaMusicasCarrinho);
        painelCarrinho.add(removerMusicaCarrinho);
        painelCarrinho.add(carregarSaldo);
        painelCarrinho.add(finalizarPagamento);
        painelCarrinho.add(valorTotalPagar);
        painelCarrinho.add(mostrarValorPagar);
        painelCarrinho.add(mostrarSaldoCliente);
        painelCarrinho.add(saldoCliente);


        //Criar painel fixo Titulo  ----------------------------------------------
        painelTituloCliente = new JPanel();
        painelTituloCliente.setBackground(new Color(225, 145, 102));
        tituloCliente = new JLabel("ROCKSTAR.INC");
        tituloCliente.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.BOLD, 80));
        painelTituloCliente.add(tituloCliente);

        //Criar Painel Fixo Menu  ----------------------------------------------------
        painelMenu = new JPanel();
        painelMenu.setBackground(new Color(225, 145, 102));
        painelMenu.setLayout(null);

        //Criar componentes do painel Menu
        botaoPesquisar = new JButton("PESQUISAR");
        botaoPesquisar.setBounds(70, 100, 250, 100);
        botaoPesquisar.setFont(new Font("Arial", Font.BOLD, 20));
        botaoPlayList = new JButton("PLAYLISTS");
        botaoPlayList.setBounds(70, 250, 250, 100);
        botaoPlayList.setFont(new Font("Arial", Font.BOLD, 20));
        botaoCarrinho = new JButton("CARRINHO");
        botaoCarrinho.setBounds(70, 400, 250, 100);
        botaoCarrinho.setFont(new Font("Arial", Font.BOLD, 20));
        username = new JLabel("USERNAME");
        username.setBounds(70, 70, 250, 20);

        //Adicional componentes ao painel
        painelMenu.add(botaoPesquisar);
        painelMenu.add(botaoPlayList);
        painelMenu.add(botaoCarrinho);
        painelMenu.add(username);

        //Adicionar painéis à janela
        janelaCliente.add(painelTituloCliente, BorderLayout.NORTH);
        janelaCliente.add(painelPesquisarCliente);
        janelaCliente.add(painelPlayList);
        painelPlayList.setVisible(false);
        janelaCliente.add(painelCarrinho);
        painelCarrinho.setVisible(false);
        janelaCliente.add(painelMenu);


        //Trocar de painéis
        botaoPlayList.addActionListener(e->trocarPainel(painelPlayList));
        botaoPesquisar.addActionListener(e->trocarPainel(painelPesquisarCliente));
        botaoCarrinho.addActionListener(e->trocarPainel(painelCarrinho));

        //Caracteristicas janela
        janelaCliente.setSize(1200, 800);
        janelaCliente.setLocation(100, 100);
        janelaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaCliente.setLocationRelativeTo(null);
        janelaCliente.setResizable(false);
        janelaCliente.setVisible(true);

    }

    //Método para efetuar a troca de painéis

    private void trocarPainel(JPanel novoPainel) {
        painelTituloCliente.setVisible(true);
        painelMenu.setVisible(true);
        painelPesquisarCliente.setVisible(false);
        painelPlayList.setVisible(false);
        painelCarrinho.setVisible(false);

        novoPainel.setVisible(true);
    }

}

