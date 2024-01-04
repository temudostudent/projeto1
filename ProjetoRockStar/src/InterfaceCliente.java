import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class InterfaceCliente implements Serializable {
    private Cliente cliente;
    private GestaoApp app;
    private ArrayList<Musica> listaM;
    private JFrame janelaCliente, janelaPlaylists, janelaCarregarSaldo;
    private JPanel painelPesquisarCliente, painelTituloCliente, painelMenu, painelPlayList, painelCarrinho;
    private JLabel atributoPesquisarLegenda, tituloCliente, username, ordenarMusicasCliente, listacompras,
            valorTotalPagar, saldoCliente, nomePlaylist;
    private JButton botaoPesquisar, botaoPlayList, botaoCarrinho, adicionarCarrinho, adicionarPlayList, adicionarRating,
            criarPlaylistPreenchida, removerPlayList, alterarVisibilidade, criarNovaPlayList, verMusicasPlayListSelecionada,
            ordenarPesquisa, okPesquisa, removerMusicaCarrinho, carregarSaldo, finalizarPagamento, minhasPlayLists,
            botaoLogout, todasPlayLists;
    private JComboBox atributoPesquisa, ordenarMusicaPor;
    private JRadioButton botaoAscendenteCliente, botaoDescendenteCliente, botaoTodasAsMusicas,
            botaoParaPesquisarMusicas, botaoMinhasMusicas;
    private ButtonGroup botaoOrdem, grupoPesquisa;
    private JTextField caixaTextoPesquisa, mostrarValorPagar, mostrarSaldoCliente, valorACarregar;
    private JTable tabelaResultadoPesquisa, listaMusicasPlayList, listaMusicasCarrinho;
    private JScrollPane scroljListarMusicas, listaPlaylist;


    public void run(){
        try{
            InterfaceCliente janela = new InterfaceCliente(cliente, app);
            janelaCliente.setVisible(true);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public InterfaceCliente(Cliente c, GestaoApp app) {
        this.cliente = c;
        this.app = app;
        initialize();
    }

    protected void initialize(){

        //Criar janela
        janelaCliente = new JFrame();

        //Criar Painel Pesquisar --------------------------------------------------------------
        painelPesquisarCliente = new JPanel();
        painelPesquisarCliente.setBackground(new Color(255,178,102));
        painelPesquisarCliente.setBounds(340, 200, 780, 500);
        painelPesquisarCliente.setLayout(null);

        //Criar Componentes do Painel Pesquisar

        //JTable
        tabelaResultadoPesquisa = new JTable();
        scroljListarMusicas = new JScrollPane(tabelaResultadoPesquisa);
        scroljListarMusicas.setBounds(40,180,610,200);

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
        okPesquisa = new JButton("PESQUISAR");
        okPesquisa.setBounds(250,120,120,40);
        okPesquisa.setVisible(false);
        okPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel listarMusicas = new DefaultTableModel();
                String pesquisa = caixaTextoPesquisa.getText();
                listaM = new ArrayList<>();
                tabelaResultadoPesquisa.setModel(listarMusicas);
                scroljListarMusicas.setViewportView(tabelaResultadoPesquisa);
                tabelaResultadoPesquisa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                String selecao = (String) atributoPesquisa.getSelectedItem();

                titulosDasColunasTabela(listarMusicas);


                if ("TÍTULO".equals(selecao)){
                        listaM = app.rockstar.listaMusicasDeTitulo(pesquisa);

                        if(listaM.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nenhuma música encontrada com o título: " + pesquisa);
                        }else {

                            // Adicionar os elementos do ArrayList à tabela
                            adicionarElementosTabela(listaM, listarMusicas);
                        }
                } else if ("ARTISTA".equals(selecao)) {

                    listaM = app.rockstar.listaMusicasDeArtista(pesquisa);

                    if(listaM.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhuma música encontrada do Artista: " + pesquisa);
                    }else {

                        // Adicionar os elementos do ArrayList à tabela
                        adicionarElementosTabela(listaM, listarMusicas);
                    }

                }else {


                }

            }
        });

        //RATING----------------------------------------------------------
        JFrame rating = new JFrame("Rating");
        JLabel resultadoInst = new JLabel("Resultado: ");
        JButton okR=new JButton("Avaliar");

        adicionarRating = new JButton("ADICIONAR RATING");
        adicionarRating.setBounds(540,450,200,40);
        adicionarRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int indexMusicaSelect = tabelaResultadoPesquisa.getSelectedRow();

                if (indexMusicaSelect != -1) {
                    String valorTituloMusica = (String) tabelaResultadoPesquisa.getValueAt(indexMusicaSelect, 0);
                    Musica object = app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                    if (object != null) {
                        rating.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar a música");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma música");
                }
            }
        });

        rating.setResizable(false);
        rating.setSize(400, 200);
        rating.setLocationRelativeTo(null);
        rating.setVisible(false);


        resultadoInst.setBounds(150, 100, 200, 25);
        rating.add(resultadoInst);

        JSlider sldResult = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        sldResult.setBounds(50, 100, 200, 25);
        sldResult.setMajorTickSpacing(1);
        sldResult.setMinorTickSpacing(1);
        sldResult.setPaintTicks(true);
        sldResult.setPaintLabels(true);
        rating.add(sldResult);
        rating.add(okR,BorderLayout.SOUTH);

        final int[] resultado = new int[1];

        sldResult.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                resultado[0] = (int) source.getValue();
                resultadoInst.setText("Resultado: " + resultado[0]);
            }
        });
        okR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexMusicaSelect = tabelaResultadoPesquisa.getSelectedRow();

                if (indexMusicaSelect != -1) {
                    String valorTituloMusica = (String) tabelaResultadoPesquisa.getValueAt(indexMusicaSelect, 0);
                    Musica object = app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);
                    int numeroSelecionado = resultado[0];

                    if (object.usuarioTemRating(cliente.getUsername())) {
                        object.adicionarRatingMusica(cliente.getUsername(), numeroSelecionado);
                    }else {
                        object.adicionarRatingMusica(cliente.getUsername(), numeroSelecionado);
                    }

                    JOptionPane.showMessageDialog(null, "Rating Alterado com Sucesso.");

                    rating.setVisible(false);
                }
            }
        });
        //FINAL DO RATING----------------------------------------

        adicionarPlayList = new JButton("ADICIONAR A PLAYLIST");
        adicionarPlayList.setBounds(310,450,200,40);
        adicionarPlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int indexMusicaSelect = tabelaResultadoPesquisa.getSelectedRow();
                String valorTituloMusica = (String) tabelaResultadoPesquisa.getValueAt(indexMusicaSelect, 0);
                Musica m = app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                if (cliente.estaMusicaJaExiste(m)){
                    janelaDasPlaylists(true,false);
                }else{
                    if(cliente.verPlayListCliente().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Não tem playlists criadas.Por favor crie uma nova");
                    } else if (m instanceof MusicaPaga && ((MusicaPaga)m).getPreco()!=0) {
                        JOptionPane.showMessageDialog(null, "Esta música tem um custo, adicione ao seu carrinho de compras para a adquirir");
                    } else {
                        janelaDasPlaylists(true,false);
                    }
                }
            }
        });
        adicionarCarrinho = new JButton("ADICIONAR AO CARRINHO");
        adicionarCarrinho.setBounds(80,450 ,200,40);
        adicionarCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cliente.compra==null){
                    cliente.abrirCompra();
                }

                int indexMusicaSelect = tabelaResultadoPesquisa.getSelectedRow();
                String valorTituloMusica = (String) tabelaResultadoPesquisa.getValueAt(indexMusicaSelect, 0);
                Musica object = app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                if (object instanceof MusicaPaga && ((MusicaPaga) object).getPreco()>0){
                    if (!cliente.estaMusicaJaExiste(object)){
                        cliente.compra.adicionarMusica((MusicaPaga) object);
                    }else JOptionPane.showMessageDialog( null, "A música que selecionou já foi comprada");
                }else JOptionPane.showMessageDialog( null, "A música que selecionou é gratuita");

                tabelaCarrinho();

            }
        });
        ordenarPesquisa = new JButton("ORDENAR");
        ordenarPesquisa.setBounds(650,100,100,30);
        ordenarPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel listarItems = new DefaultTableModel();
                String selecao = (String) ordenarMusicaPor.getSelectedItem();
                listaM = new ArrayList<>();

                tabelaResultadoPesquisa.setModel(listarItems);
                scroljListarMusicas.setViewportView(tabelaResultadoPesquisa);
                tabelaResultadoPesquisa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                listaM.addAll(app.rockstar.getMusicas());

                if (botaoTodasAsMusicas.isSelected()) {

                    if ("TÍTULO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasCrescentePorTitulo(listaM);
                    } else if ("TÍTULO".equals(selecao) && botaoDescendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasDecrescentePorTitulo(listaM);
                    } else if ("GÉNERO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasCrescentePorGenero(listaM);
                    } else if ("GÉNERO".equals(selecao) && botaoDescendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasDecrescentePorGenero(listaM);
                    }

                } else if (botaoParaPesquisarMusicas.isSelected()) {

                    String pesquisa = caixaTextoPesquisa.getText();

                    String selecaoAtributo = (String) atributoPesquisa.getSelectedItem();

                    if ("TÍTULO".equals(selecaoAtributo)){
                        listaM = app.rockstar.listaMusicasDeTitulo(pesquisa);

                    } else if ("ARTISTA".equals(selecaoAtributo)) {
                        listaM = app.rockstar.listaMusicasDeArtista(pesquisa);
                    }

                    if ("TÍTULO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasCrescentePorTitulo(listaM);
                    } else if ("TÍTULO".equals(selecao) && botaoDescendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasDecrescentePorTitulo(listaM);
                    } else if ("GÉNERO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasCrescentePorGenero(listaM);
                    } else if ("GÉNERO".equals(selecao) && botaoDescendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasDecrescentePorGenero(listaM);
                    }
                }
                titulosDasColunasTabela(listarItems);

                adicionarElementosTabela(listaM,listarItems);


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
                okPesquisa.setVisible(!botaoTodasAsMusicas.isSelected());

                DefaultTableModel listarMusicas = new DefaultTableModel();
                listaM = new ArrayList<>();
                listaM = app.rockstar.getMusicas();


                titulosDasColunasTabela(listarMusicas);

                adicionarElementosTabela(listaM,listarMusicas);
                tabelaResultadoPesquisa.setModel(listarMusicas);
                scroljListarMusicas.setViewportView(tabelaResultadoPesquisa);
                tabelaResultadoPesquisa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            }
        });

        botaoMinhasMusicas = new JRadioButton("Minhas músicas");
        botaoMinhasMusicas.setBounds(50,45,150,40);
        botaoMinhasMusicas.setBackground(null);

        botaoMinhasMusicas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel listarMusicas = new DefaultTableModel();
                ArrayList<MusicaPaga> listaMPagas = new ArrayList<>();
                listaMPagas = cliente.getMusicasCompradas();

                if(listaMPagas != null && !listaMPagas.isEmpty()) {

                    titulosDasColunasTabela(listarMusicas);

                    for (MusicaPaga musica : listaMPagas) {
                        listarMusicas.addRow(new Object[]{musica.getTitulo(), musica.getNomeArtista(), musica.getDataCriacao(), musica.getDuracao(),
                                musica.getGenero(), musica.tipoEstado(), ((MusicaPaga) musica).getPreco() + " € ", musica.getRatingMedia()});
                    }
                    tabelaResultadoPesquisa.setModel(listarMusicas);
                    scroljListarMusicas.setViewportView(tabelaResultadoPesquisa);
                    tabelaResultadoPesquisa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                }else{
                    JOptionPane.showMessageDialog(null, "Ainda não tem nenhuma música comprada");
                }


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
                okPesquisa.setVisible(botaoParaPesquisarMusicas.isSelected());

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
        grupoPesquisa.add(botaoMinhasMusicas);

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
        painelPesquisarCliente.add(ordenarPesquisa);
        painelPesquisarCliente.add(botaoParaPesquisarMusicas);
        painelPesquisarCliente.add(botaoTodasAsMusicas);
        painelPesquisarCliente.add(adicionarCarrinho);
        painelPesquisarCliente.add(adicionarPlayList);
        painelPesquisarCliente.add(caixaTextoPesquisa);
        painelPesquisarCliente.add(atributoPesquisa);
        painelPesquisarCliente.add(atributoPesquisarLegenda);
        painelPesquisarCliente.add(adicionarRating);
        painelPesquisarCliente.add(okPesquisa);
        painelPesquisarCliente.add(scroljListarMusicas);
        painelPesquisarCliente.add(botaoMinhasMusicas);

        //Criar painel PlayList------------------------------------------------------------------------
        painelPlayList = new JPanel();
        painelPlayList.setBackground(new Color(255,178,102));
        painelPlayList.setBounds(340, 200, 780, 500);
        painelPlayList.setLayout(null);

        //Criar Componentes painel PlayList

        nomePlaylist = new JLabel();
        nomePlaylist.setBounds(50,60,400,30);
        listaMusicasPlayList = new JTable();
        listaPlaylist = new JScrollPane(listaMusicasPlayList);
        listaPlaylist.setBounds(50,80,400,250);
        minhasPlayLists = new JButton("MINHAS PLAYLISTS");
        minhasPlayLists.setBounds(50,0,220,40);
        todasPlayLists = new JButton("TODAS PLAYLISTS");
        todasPlayLists.setBounds(280,0,220,40);

        todasPlayLists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<PlayList> playList = app.rockstar.getPlaylists();

                DefaultTableModel modeloTabela = new DefaultTableModel();

                modeloTabela.addColumn("Nome");
                modeloTabela.addColumn("Visibilidade");


                listaMusicasPlayList.setModel(modeloTabela);
                listaPlaylist.setViewportView(listaMusicasPlayList);


                for (PlayList play : playList) {
                    if(play.isVisibilidade())
                    modeloTabela.addRow(new Object[]{play.getNome(), play.getVisibilidade()});
                }

                removerPlayList.setVisible(false);
                alterarVisibilidade.setVisible(false);
                criarNovaPlayList.setVisible(false);
                verMusicasPlayListSelecionada.setVisible(false);
                criarPlaylistPreenchida.setVisible(false);
            }
        });
        minhasPlayLists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                atualizarListaPlayList();
                removerPlayList.setVisible(true);
                alterarVisibilidade.setVisible(true);
                criarNovaPlayList.setVisible(true);
                verMusicasPlayListSelecionada.setVisible(true);
                criarPlaylistPreenchida.setVisible(true);


            }
        });


        removerPlayList = new JButton("REMOVER PLAYLIST");
        removerPlayList.setBounds(500, 100, 220,40);
        removerPlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém o índice da playlist selecionada

                int linhaSelecionada = listaMusicasPlayList.getSelectedRow();

                if(linhaSelecionada != -1) {

                    String nome = (String) listaMusicasPlayList.getValueAt(linhaSelecionada, 0);
                    PlayList playlistSelecionada = cliente.pesquisaPlaylistTitulo(nome);

                    // Verifica se alguma playlist está selecionada
                    if (playlistSelecionada != null) {

                        cliente.getPlaylists().remove(linhaSelecionada);
                        app.rockstar.removerPlayListID(playlistSelecionada.getIdPlaylist());

                        atualizarListaPlayList();

                        JOptionPane.showMessageDialog(null, "PlayList  " + playlistSelecionada.getNome() +  "   removida com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhuma playlist selecionada para remover.");
                    }
                }

            }
        });
        alterarVisibilidade = new JButton("ALTERAR VISIBILIDADE");
        alterarVisibilidade.setBounds(500,150,220,40);
        alterarVisibilidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = listaMusicasPlayList.getSelectedRow();

                if(linhaSelecionada != -1) {

                    String nome = (String) listaMusicasPlayList.getValueAt(linhaSelecionada, 0);
                    PlayList playlistSelecionada = cliente.pesquisaPlaylistTitulo(nome);
                    playlistSelecionada.getIdPlaylist();


                    // Verifica se alguma playlist está selecionada
                    if (playlistSelecionada != null) {

                        //Altera a visibilidade da playList na JComboBox
                        boolean novaVisibilidade = !playlistSelecionada.isVisibilidade(); // Inverte a visibilidade
                        playlistSelecionada.setVisibilidade(novaVisibilidade);
                        atualizarListaPlayList();


                        JOptionPane.showMessageDialog(null, "Visibilidade alterada  com sucesso.");

                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhuma playlist selecionada para alterar.");

                    }
                }
            }
        });
        criarNovaPlayList = new JButton("CRIAR NOVA PLAYLIST");
        criarNovaPlayList.setBounds(500, 200, 220,40);
        criarNovaPlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre uma caixa de texto para inserir o nome da nova playlist
                JFrame frame = new JFrame();
                String nomeNovaPlaylist = JOptionPane.showInputDialog(frame, "Indique o nome da nova playlist:");

                if ( !nomeNovaPlaylist.isEmpty()) {

                    //Verifica se já existe alguma playList com o mesmo nome

                    boolean ehPossivel = cliente.verificarNomePlaylist(nomeNovaPlaylist);

                    if(ehPossivel) {
                        // Cria a nova playlist no cliente com visibilidade true

                        cliente.adicionarPlayList(cliente.criarPlaylist(nomeNovaPlaylist, true));
                        app.rockstar.adicionarPlayList(cliente.criarPlaylist(nomeNovaPlaylist, true));
                        atualizarListaPlayList();

                    }else{
                        JOptionPane.showMessageDialog(null, "Titulo playlist já existe.");
                    }
                } else {
                    // Nome da playlist não fornecido ou cancelado pelo usuário
                    // Adicione a lógica apropriada para lidar com essa situação
                }
            }
        });
        verMusicasPlayListSelecionada = new JButton("VER MÚSICAS PLAYLIST");
        verMusicasPlayListSelecionada.setBounds(500, 250, 220,40);

        verMusicasPlayListSelecionada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = listaMusicasPlayList.getSelectedRow();

                if (linhaSelecionada != -1) {

                    String nome = (String) listaMusicasPlayList.getValueAt(linhaSelecionada, 0);
                    PlayList playlistSelecionada = cliente.pesquisaPlaylistTitulo(nome);

                    janelaMusicaPlayLis(playlistSelecionada);
                }
            }

        });


        criarPlaylistPreenchida = new JButton("CRIAR PLAYLIST PREENCHIDA");
        criarPlaylistPreenchida.setBounds(500,300,220,40);

        criarPlaylistPreenchida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaDadosNovaPlaylist();
            }
        });



        //Adicionar componentes ao painel
        painelPlayList.add(listaMusicasPlayList);
        painelPlayList.add(removerPlayList);painelPlayList.add(alterarVisibilidade);
        painelPlayList.add(criarNovaPlayList);painelPlayList.add(verMusicasPlayListSelecionada);
        painelPlayList.add(minhasPlayLists); painelPlayList.add(criarPlaylistPreenchida);
        painelPlayList.add(listaPlaylist); painelPlayList.add(nomePlaylist); painelPlayList.add(todasPlayLists);

        //Criar painel Carrinho  ------------------------------------------------------------------------
        painelCarrinho = new JPanel();
        painelCarrinho.setBackground(new Color(255,178,102));
        painelCarrinho.setBounds(340, 200, 780, 500);
        painelCarrinho.setLayout(null);


        //Criar componentes do Painel Carrinho

        //JLabel
        listacompras = new JLabel ("CARRINHO DE COMPRAS");
        listacompras.setBounds(50,0,280,40);
        saldoCliente = new JLabel("SALDO");
        saldoCliente.setBounds(550,200, 250, 40);

        //JTabel
        listaMusicasCarrinho = new JTable();
        listaMusicasCarrinho.setBounds(50, 50,250,300);

        //JButton
        removerMusicaCarrinho = new JButton("REMOVER MÚSICA DO CARRINHO");
        removerMusicaCarrinho.setBounds(400, 50, 250,40);
        removerMusicaCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int indexMusicaSelect = listaMusicasCarrinho.getSelectedRow();

                if (indexMusicaSelect != -1) {
                    String valorTituloMusica = (String) listaMusicasCarrinho.getModel().getValueAt(indexMusicaSelect, 0);
                    MusicaPaga m = (MusicaPaga) app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                    if (m!=null){
                        cliente.compra.removerMusica(indexMusicaSelect-1);
                        tabelaCarrinho();
                    }
                }else  JOptionPane.showMessageDialog(null, "Nenhuma música selecionada");
            }
        });

        carregarSaldo = new JButton("CARREGAR SALDO");
        carregarSaldo.setBounds(400,100,250,40);
        carregarSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaCarregarSaldo(true);
            }
        });
        finalizarPagamento = new JButton("FINALIZAR PAGAMENTO");
        finalizarPagamento.setBounds(400,150,250,40);
        finalizarPagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cliente.compra == null){
                    JOptionPane.showMessageDialog(null, "Carrinho de compras vazio");
                }else {
                    double valorAPagar= cliente.compra.totalCarrinhoCliente();
                    if (cliente.getSaldo()<valorAPagar){
                        JOptionPane.showMessageDialog(null, "Dinheiro insuficiente para a compra");
                    } else if (cliente.compra.getCarrinho().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Carrinho vazio");
                    } else{
                        //abre janela das playlists
                        janelaDasPlaylists(true,true);
                    }
                }
            }
        });
        valorTotalPagar = new JLabel("VALOR TOTAL");
        valorTotalPagar.setBounds(400, 200, 250,40);

        //JTextField
        mostrarValorPagar = new JTextField("0.00 €");
        mostrarValorPagar.setBounds(400,250,100,40);
        mostrarValorPagar.setEditable(false);
        if (cliente.compra!=null){
            mostrarValorPagar.setText(String.format("%.2f",cliente.compra.totalCarrinhoCliente()) + " €");
        }
        mostrarSaldoCliente = new JTextField(String.format("%.2f",cliente.getSaldo()) + " €");
        mostrarSaldoCliente.setBounds(550,250,100,40);
        mostrarSaldoCliente.setEditable(false);



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
        painelTituloCliente.setBackground(new Color(255,178,102));
        painelTituloCliente.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        tituloCliente = new JLabel("ROCKSTAR.INC");
        tituloCliente.setFont(new Font("Magneto", Font.BOLD, 80));
        painelTituloCliente.add(tituloCliente);

        //Criar Painel Fixo Menu  ----------------------------------------------------
        painelMenu = new JPanel();
        painelMenu.setBackground(new Color(255,178,102));
        painelMenu.setLayout(null);

        //Criar componentes do painel Menu
        botaoPesquisar = new JButton("PESQUISAR");
        botaoPesquisar.setBounds(70, 100, 250, 100);
        botaoPesquisar.setFont(new Font("Arial", Font.BOLD, 20));
        botaoPlayList = new JButton("PLAYLISTS");
        botaoPlayList.setBounds(70, 220, 250, 100);
        botaoPlayList.setFont(new Font("Arial", Font.BOLD, 20));
        botaoCarrinho = new JButton("CARRINHO");
        botaoCarrinho.setBounds(70, 340, 250, 100);
        botaoCarrinho.setFont(new Font("Arial", Font.BOLD, 20));
        username = new JLabel("Bem Vindo " + cliente.getUsername());
        username.setBounds(70, 70, 250, 20);
        botaoLogout = new JButton("LOGOUT");
        botaoLogout.setBounds(70,460,250,30);

        botaoLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.atualizaficheiro(app.rockstar.getClientes(), app.rockstar.getArtistas(),
                        app.rockstar.getMusicas(), app.rockstar.getPlaylists(), app.rockstar.getCompras());
                janelaCliente.setVisible(false);

                try {
                    JanelaInicial run = new JanelaInicial();
                } catch (IOException es) {
                    throw new RuntimeException(es);
                } catch (ClassNotFoundException es) {
                    throw new RuntimeException(es);
                }
            }
        });

        //Adicional componentes ao painel
        painelMenu.add(botaoPesquisar);
        painelMenu.add(botaoPlayList);
        painelMenu.add(botaoCarrinho);
        painelMenu.add(username); painelMenu.add(botaoLogout);

        //Adicionar painéis à janela
        janelaCliente.add(painelTituloCliente, BorderLayout.NORTH);
        janelaCliente.add(painelPesquisarCliente);
        janelaCliente.add(painelPlayList);
        painelPlayList.setVisible(false);
        janelaCliente.add(painelCarrinho);
        painelCarrinho.setVisible(false);
        janelaCliente.add(painelMenu);


        //Trocar de painéis
        botaoPlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trocarPainel(painelPlayList);
                atualizarListaPlayList();
            }
        });

        botaoPesquisar.addActionListener(e->trocarPainel(painelPesquisarCliente));
        botaoCarrinho.addActionListener(e->trocarPainel(painelCarrinho));
        botaoCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelaCarrinho();
            }
        });
        janelaCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Salva os dados ao fechar a janela
                app.atualizaficheiro(app.rockstar.getClientes(), app.rockstar.getArtistas(),
                        app.rockstar.getMusicas(), app.rockstar.getPlaylists(), app.rockstar.getCompras());
            }
        });

        botaoLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.atualizaficheiro(app.rockstar.getClientes(), app.rockstar.getArtistas(),
                        app.rockstar.getMusicas(), app.rockstar.getPlaylists(), app.rockstar.getCompras());
            }
        });

        //Caracteristicas janela
        janelaCliente.setSize(1200, 800);
        janelaCliente.setLocation(100, 100);
        janelaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaCliente.setLocationRelativeTo(null);
        janelaCliente.setResizable(false);
        janelaCliente.setVisible(false);
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

    private void adicionarElementosTabela(ArrayList<Musica> lista,DefaultTableModel modelo){
        for (Musica musica : lista) {
            if (musica instanceof MusicaPaga) {
                modelo.addRow(new Object[]{musica.getTitulo(), musica.getNomeArtista(),musica.getDataCriacao(), musica.getDuracao(),
                        musica.getGenero(), musica.tipoEstado(), ((MusicaPaga) musica).getPreco() + " € ", musica.getRatingMedia()});
            }else {
                modelo.addRow(new Object[]{musica.getTitulo(), musica.getNomeArtista(),musica.getDataCriacao(), musica.getDuracao(),
                        musica.getGenero(), musica.tipoEstado(), "GRATIS", musica.getRatingMedia()});
            }
        }
    }
    private void titulosDasColunasTabela(DefaultTableModel modelo){
        // Adicionar uma coluna à tabela e respetivos titulos
        modelo.addColumn("TÍTULO");
        modelo.addColumn("ARTISTA");
        modelo.addColumn("DATA");
        modelo.addColumn("DURAÇÃO");
        modelo.addColumn("GÉNERO");
        modelo.addColumn("ESTADO");
        modelo.addColumn("PREÇO");
        modelo.addColumn("RATING");

    }

    private void tabelaCarrinho(){
        DefaultTableModel listarCarrinho = new DefaultTableModel();

        if (cliente.compra!=null){
            ArrayList<MusicaPaga> meuCarrinho = cliente.compra.getCarrinho();

            listarCarrinho.addColumn("TÍTULO");
            listarCarrinho.addColumn("PREÇO");

            listarCarrinho.addRow(new Object[]{"TÍTULO","PREÇO"});

            for (MusicaPaga m : meuCarrinho) {
                listarCarrinho.addRow(new Object[]{m.getTitulo(), m.getPreco() + " €"});
            }

            listaMusicasCarrinho.setModel(listarCarrinho);
        }

        if (cliente.compra!=null){
            mostrarValorPagar.setText(String.format("%.2f",cliente.compra.totalCarrinhoCliente()) + " €");
        }
    }

    private void janelaDasPlaylists(boolean visivel,boolean variasMusicas) {

        janelaPlaylists = new JFrame();
        JTable tabPlaylists = new JTable();

        DefaultTableModel listarPlaylists = new DefaultTableModel();
        ArrayList<PlayList> listaP = cliente.verPlayListCliente();

        listarPlaylists.addColumn("Título da Playlist");
        listarPlaylists.addColumn("Número de músicas");

        for (PlayList p : listaP) {
            listarPlaylists.addRow(new Object[]{p.getNome(), p.getNumMusicas()});
        }

        tabPlaylists.setModel(listarPlaylists);

        JButton okButtonPl = new JButton("ADICIONAR");
        JButton cancelar = new JButton("CANCELAR");

        //Layout para organizar a tabela e os botoes

        janelaPlaylists.setLayout(new BorderLayout());
        janelaPlaylists.add(new JScrollPane(tabPlaylists), BorderLayout.CENTER);

        //Jpanel para organizar os botoes
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(okButtonPl);
        painelBotoes.add(cancelar);

        janelaPlaylists.add(painelBotoes, BorderLayout.SOUTH);

        janelaPlaylists.pack();
        janelaPlaylists.setLocationRelativeTo(null);
        janelaPlaylists.setResizable(false);
        janelaPlaylists.setVisible(visivel);
        janelaPlaylists.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        okButtonPl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PlayList p=null;
                String tituloPlaylist=new String();

                int indexPlaylistSelect = tabPlaylists.getSelectedRow();
                if (indexPlaylistSelect != -1){
                    tituloPlaylist = (String) tabPlaylists.getValueAt(indexPlaylistSelect, 0);
                    p = cliente.pesquisaPlaylistTitulo(tituloPlaylist);
                }else JOptionPane.showMessageDialog(null, "Erro ao obter playlist. ");

                //Adicionar 1 música à playlist
                if (!variasMusicas) {

                    int indexMusicaSelect = tabelaResultadoPesquisa.getSelectedRow();

                    if (indexMusicaSelect != -1) {
                        String valorTituloMusica = (String) tabelaResultadoPesquisa.getValueAt(indexMusicaSelect, 0);
                        Musica m = app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                        if (m != null && p != null) {
                            if (!p.estaMusicaJaExiste(m)) {
                                cliente.adicionarMusica(p, m);
                                JOptionPane.showMessageDialog(null, "Música adicionada à playlist " + tituloPlaylist);
                            } else {
                                JOptionPane.showMessageDialog(null, "Música já existe na " + tituloPlaylist);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao obter música");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione uma música e uma playlist");
                    }
                //Adicionar várias músicas à playlist
                }else{
                    ArrayList<Musica> carrinho = new ArrayList<>();
                    carrinho.addAll(cliente.compra.getCarrinho());
                    if (p!= null && !p.musicasJaExistem(carrinho)){
                        //Adiciona todas as músicas à playlist selecionada
                        p.getMusicas().addAll(cliente.compra.getCarrinho());
                        //Adiciona todas as músicas do carrinho à lista de músicas compradas
                        cliente.getMusicasCompradas().addAll(cliente.compra.getCarrinho());
                        JOptionPane.showMessageDialog(null, "Músicas adicionadas à playlist " + p.getNome());
                        double valorAPagar= cliente.compra.totalCarrinhoCliente();
                        //altera o saldo
                        cliente.alterarSaldo(-valorAPagar);
                        //atualiza a caixa que mostra o saldo
                        mostrarSaldoCliente.setText(String.format("%.2f",cliente.getSaldo()) + " €");
                        //adiciona saldo aos artistas
                        app.rockstar.pagarArtistas(cliente.compra.getCarrinho());
                        //abre nova compra
                        cliente.abrirCompra();
                        //atualiza tabela do carrinho
                        tabelaCarrinho();

                        JOptionPane.showMessageDialog(null, "Compra efetuada com sucesso");
                    }
                }
                janelaPlaylists.setVisible(false);
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPlaylists.setVisible(false);
            }
        });
    }
    public void janelaCarregarSaldo(boolean visivel){
        janelaCarregarSaldo = new JFrame("Carregamento Saldo");
        valorACarregar = new JTextField("€ a carregar");
        JButton okCarregamento = new JButton("Carregar Saldo");
        okCarregamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valor= Double.parseDouble(valorACarregar.getText());
                if (valor>0 && valor<1000){
                    cliente.alterarSaldo(valor);
                    mostrarSaldoCliente.setText(String.format("%.2f",cliente.getSaldo()) + " €");
                    janelaCarregarSaldo.setVisible(false);
                }else JOptionPane.showMessageDialog(null, "Valor inválido");

            }
        });

        janelaCarregarSaldo.add(valorACarregar,BorderLayout.CENTER);
        janelaCarregarSaldo.add(okCarregamento,BorderLayout.SOUTH);

        janelaCarregarSaldo.pack();
        janelaCarregarSaldo.setLocationRelativeTo(null);
        janelaCarregarSaldo.setResizable(false);
        janelaCarregarSaldo.setVisible(visivel);
    }
    //Cria uma janela auxiliar para o utilizador inserir os dados da nova playlist previamente preenchida
    private void janelaDadosNovaPlaylist() {

        //Cria nova janela e configura
        JFrame janelaPlaylist = new JFrame();
        janelaPlaylist.setSize(300, 200);
        janelaPlaylist.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaPlaylist.setLocationRelativeTo(null);
        janelaPlaylist.setVisible(true);

        // Criação dos componentes
        JTextField nomePlaylist = new JTextField();
        JTextField generoPlaylist = new JTextField();
        JTextField numeroMusicas = new JTextField();
        JButton botaoOk = new JButton("OK");
        JButton botaoCancelar = new JButton("CANCELAR");

        // Adiciona os componentes ao layout
        janelaPlaylist.setLayout(new GridLayout(4, 2));
        janelaPlaylist.add(new JLabel("Nome da Playlist:"));
        janelaPlaylist.add(nomePlaylist);
        janelaPlaylist.add(new JLabel("Género:"));
        janelaPlaylist.add(generoPlaylist);
        janelaPlaylist.add(new JLabel("Número de Músicas:"));
        janelaPlaylist.add(numeroMusicas);
        janelaPlaylist.add(botaoOk);
        janelaPlaylist.add(botaoCancelar);


            botaoOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = nomePlaylist.getText();
                String genero = generoPlaylist.getText();
                String  tamanho = numeroMusicas.getText();
                if(titulo.isEmpty() || genero.isEmpty() || tamanho.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Por favor preencha todos os campos");
                }else{
                    try {
                        int tamanho1 = Integer.parseInt(tamanho);
                        ArrayList<Musica> musicasGenero = cliente.listaMusicaGenero(genero, app.rockstar.musicasGratuitasGenero(genero));

                        if (musicasGenero.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Não há músicas disponíveis do género " + genero,
                                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        } else {

                            //Verifica se o tamanho desejado é maior que o numero de musicas do genero solicitado
                            if (tamanho1 > musicasGenero.size()) {
                                //Envia mensagem ao tutilizador
                                JOptionPane.showMessageDialog(null, "Apenas existem " + (musicasGenero.size() )+ " musicas do género " +
                                        genero, "Aviso", JOptionPane.INFORMATION_MESSAGE);
                                System.out.println(musicasGenero.size());
                            } else {

                                cliente.adicionarPlayList(cliente.criarPlayListGenero(titulo, genero, tamanho1, musicasGenero));
                                app.rockstar.adicionarPlayList(cliente.criarPlayListGenero(titulo, genero, tamanho1, musicasGenero));
                                JOptionPane.showMessageDialog(null, "Playlist criada com sucesso");


                                janelaPlaylist.setVisible(false);
                                atualizarListaPlayList();
                            }
                        }
                        }catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(null, "Numeros de musicas deve ser um valor inteiro");
                        }

                }
            }

        });
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPlaylist.setVisible(false);
            }
        });
    }

    public void janelaMusicaPlayLis(PlayList p){
        JFrame janelaMusicas = new JFrame();
        JButton botaoOk = new JButton("OK");
        JButton botaoRemover = new JButton("REMOVER MUSICA");
        janelaMusicas.add(botaoRemover); janelaMusicas.add(botaoOk);
        janelaMusicas.setLocationRelativeTo(null);
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoOk);
        janelaMusicas.add(painelBotoes, BorderLayout.SOUTH);


        janelaMusicas.setSize(400,300);
        janelaMusicas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ArrayList<Musica> musicas = p.getMusicas();

        //Cria a tabela modelo
        DefaultTableModel modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Músicas");
        JTable tabelaMusicas = new JTable(modeloTabela);
        JScrollPane srollTabelaMusicas = new JScrollPane(tabelaMusicas);
        janelaMusicas.add(srollTabelaMusicas);


        if(!musicas.isEmpty()) {
            //Adiciona as músicas a tabela
            for (Musica m : musicas) {
                modeloTabela.addRow(new Object[]{m.getTitulo()});
            }
            //Cria a tabela


            janelaMusicas.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "PlayList vazia.");
        }

       botaoOk.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               janelaMusicas.setVisible(false);
           }
       });

       botaoRemover.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int linhaSelecionada = tabelaMusicas.getSelectedRow();
               if(linhaSelecionada != -1){
                   String nomeMusica = (String) tabelaMusicas.getValueAt(linhaSelecionada, 0);
                   p.removeMusica(linhaSelecionada);
                   JOptionPane.showMessageDialog(null, "Música Removida com Sucesso");
                   janelaMusicas.setVisible(false);

               }
           }

       });


    }
    public void atualizarListaPlayList(){
        // Ver as playLists do Cliente
        ArrayList<PlayList> playList = cliente.verPlayListCliente();

        DefaultTableModel modeloTabela = new DefaultTableModel();

        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Visibilidade");


        listaMusicasPlayList.setModel(modeloTabela);
        listaPlaylist.setViewportView(listaMusicasPlayList);


        for (PlayList play : playList) {
            modeloTabela.addRow(new Object[]{play.getNome(), play.getVisibilidade()});

        }


    }


}


