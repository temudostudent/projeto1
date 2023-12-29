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
    private JFrame janelaCliente, janelaPlaylists;
    private JPanel painelPesquisarCliente, painelTituloCliente, painelMenu, painelPlayList, painelCarrinho;
    private JLabel atributoPesquisarLegenda, tituloCliente, username, ordenarMusicasCliente, listacompras, valorTotalPagar, saldoCliente;
    private JButton botaoPesquisar, botaoPlayList, botaoCarrinho, adicionarCarrinho, adicionarPlayList, adicionarRating,
            criarPlaylistPreenchida, removerPlayList, alterarVisibilidade, criarNovaPlayList, removerMusicaPlayList,
            ordenarPesquisa, okPesquisa, removerMusicaCarrinho, carregarSaldo, finalizarPagamento, botaoAtualizarPlaylists,
            botaoLogout;
    private JComboBox atributoPesquisa, ordenarMusicaPor, caixaListarPlayLists;
    private JRadioButton botaoAscendenteCliente, botaoDescendenteCliente, botaoTodasAsMusicas, botaoParaPesquisarMusicas;
    private ButtonGroup botaoOrdem, grupoPesquisa;
    private JTextField caixaTextoPesquisa, mostrarValorPagar, mostrarSaldoCliente, valorACarregar;
    private JTable tabelaResultadoPesquisa, listaMusicasPlayList, listaMusicasCarrinho;


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

                String selecao = (String) atributoPesquisa.getSelectedItem();

                titulosDasColunasTabela(listarMusicas);

                if ("TÍTULO".equals(selecao)){
                    listaM = app.rockstar.listaMusicasDeTitulo(pesquisa);

                    // Adicionar os elementos do ArrayList à tabela
                    adicionarElementosTabela(listaM,listarMusicas);
                } else if ("ARTISTA".equals(selecao)) {
                    listaM = app.rockstar.listaMusicasDeArtista(pesquisa);

                    adicionarElementosTabela(listaM,listarMusicas);
                }
                tabelaResultadoPesquisa.setModel(listarMusicas);
            }
        });

        //RATING----------------------------------------------------------
        JFrame rating = new JFrame("Rating");
        JLabel lblResult = new JLabel("Resultado: ");
        JButton okR=new JButton("Avaliar");

        adicionarRating = new JButton("ADICIONAR RATING");
        adicionarRating.setBounds(540,450,200,40);
        adicionarRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int indexMusicaSelect = tabelaResultadoPesquisa.getSelectedRow();
                String valorTituloMusica = (String) tabelaResultadoPesquisa.getValueAt(indexMusicaSelect, 0);
                Musica object = app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                if (object!=null){
                    rating.setVisible(true);
                }else  JOptionPane.showMessageDialog(null, "Nenhuma música selecionada");
            }
        });

        rating.setResizable(false);
        rating.setSize(400, 200);
        rating.setLocationRelativeTo(null);

        rating.setVisible(false);


        lblResult.setBounds(150, 100, 200, 25);
        rating.add(lblResult);

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
                lblResult.setText("Resultado: " + resultado[0]);
            }
        });
        okR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexMusicaSelect = tabelaResultadoPesquisa.getSelectedRow();
                String valorTituloMusica = (String) tabelaResultadoPesquisa.getValueAt(indexMusicaSelect, 0);
                Musica object = app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                object.addRating(resultado[0]);

                JOptionPane.showMessageDialog(null, "Rating Alterado com Sucesso.");

                rating.setVisible(false);
            }
        });
        //FINAL DO RATING----------------------------------------


        adicionarPlayList = new JButton("ADICIONAR A PLAYLIST");
        adicionarPlayList.setBounds(310,450,200,40);
        adicionarPlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaDasPlaylists(true);
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
                    cliente.compra.adicionarMusica((MusicaPaga) object);
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

                if (botaoTodasAsMusicas.isSelected()) {

                    listaM.addAll(app.rockstar.getMusicas());

                    if ("TÍTULO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasCrescentePorTitulo(listaM);
                    } else if ("TÍTULO".equals(selecao) && botaoDescendenteCliente.isSelected()) {
                        app.rockstar.ordendarMusicasDecrescentePorTitulo(listaM);
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
                        app.rockstar.ordendarMusicasDecrescentePorTitulo(listaM);
                    } else if ("GÉNERO".equals(selecao) && botaoAscendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasCrescentePorGenero(listaM);
                    } else if ("GÉNERO".equals(selecao) && botaoDescendenteCliente.isSelected()) {
                        app.rockstar.ordenarMusicasCrescentePorGenero(listaM);
                    }
                }
                titulosDasColunasTabela(listarItems);

                adicionarElementosTabela(listaM,listarItems);

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
        tabelaResultadoPesquisa.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        tabelaResultadoPesquisa.setPreferredScrollableViewportSize(tabelaResultadoPesquisa.getPreferredSize());

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

        //Criar painel PlayList------------------------------------------------------------------------
        painelPlayList = new JPanel();
        painelPlayList.setBackground(new Color(255,178,102));
        painelPlayList.setBounds(340, 200, 780, 500);
        painelPlayList.setLayout(null);

        //Criar Componentes painel PlayList
        caixaListarPlayLists = new JComboBox<>();
        caixaListarPlayLists.setBounds(50,0,400,40);
        listaMusicasPlayList = new JTable();
        listaMusicasPlayList.setBounds(50,50,400,380);
        botaoAtualizarPlaylists = new JButton("ATUALIZAR PLAYLISTS");
        botaoAtualizarPlaylists.setBounds(500,0,220,40);
        caixaListarPlayLists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém o índice da playlist selecionada
                PlayList playlistSelecionada = (PlayList) caixaListarPlayLists.getSelectedItem();

                // Verifica se alguma playlist está selecionada
                if (playlistSelecionada != null) {
                    // Obtém as músicas da playlist
                    ArrayList<Musica> musicas = playlistSelecionada.getMusicas();

                    // Cria um modelo de tabela para as músicas
                    DefaultTableModel modeloTabela = new DefaultTableModel();
                    modeloTabela.addColumn("Nome da Música");

                    // Adiciona as músicas ao modelo de tabela
                    for (Musica musica : musicas) {
                        modeloTabela.addRow(new Object[]{musica.getTitulo()});
                    }

                    // Configura o modelo na tabela
                    listaMusicasPlayList.setModel(modeloTabela);
                }

            }
        });

        botaoAtualizarPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                    // Ver as playLists do Cliente
                    ArrayList<PlayList> playList = cliente.verPlayListCliente();

                    // Crie um array de objetos PlayList
                    PlayList[] playlistsArray = playList.toArray(new PlayList[0]);

                    DefaultComboBoxModel<PlayList> model = new DefaultComboBoxModel<>(playlistsArray);
                    caixaListarPlayLists.setModel(model);
                }
        });
        removerPlayList = new JButton("REMOVER PLAYLIST");
        removerPlayList.setBounds(500, 100, 220,40);
        removerPlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém o índice da playlist selecionada
                PlayList playlistSelecionada = (PlayList) caixaListarPlayLists.getSelectedItem();

                // Verifica se alguma playlist está selecionada
                if (playlistSelecionada != null) {
                    cliente.removerPlaylist(playlistSelecionada);

                    //Obter o modelo da JComboBox
                    DefaultComboBoxModel<PlayList> model = (DefaultComboBoxModel<PlayList>) caixaListarPlayLists.getModel();

                    //Remover a playlist do modelo da JComBox
                    model.removeElement(playlistSelecionada);

                    JOptionPane.showMessageDialog(null, "PlayList removida com sucesso.");

                } else {
                    JOptionPane.showMessageDialog( null, "Nenhuma playlist selecionada para remover.");

                }

            }
        });
        alterarVisibilidade = new JButton("ALTERAR VISIBILIDADE");
        alterarVisibilidade.setBounds(500,150,220,40);
        alterarVisibilidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a playlist selecionada
                int indiceSelecionado = caixaListarPlayLists.getSelectedIndex();


                // Verifica se alguma playlist está selecionada
                if (indiceSelecionado != -1) {
                    // Remove a playlist da lista da JComboBox
                    PlayList playlistSelecionada = (PlayList) caixaListarPlayLists.getSelectedItem();


                    //Altera a visibilidade da playList na JComboBox
                    boolean novaVisibilidade = !playlistSelecionada.isVisibilidade(); // Inverte a visibilidade
                    playlistSelecionada.setVisibilidade(novaVisibilidade);
                    atualizaListaPlayList();


                    JOptionPane.showMessageDialog(null, "Visibilidade alterada  com sucesso.");

                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma playlist selecionada para alterar.");

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

                if (nomeNovaPlaylist != null && !nomeNovaPlaylist.isEmpty()) {
                    // Cria a nova playlist no cliente com visibilidade true
                    cliente.criarPlaylist(nomeNovaPlaylist, true);

                    // Atualiza a  JComboBox
                    atualizaListaPlayList();
                } else {
                    // Nome da playlist não fornecido ou cancelado pelo usuário
                    // Adicione a lógica apropriada para lidar com essa situação
                }
            }
        });
        removerMusicaPlayList = new JButton("REMOVER MÚSICA PLAYLIST");
        removerMusicaPlayList.setBounds(500, 250, 220,40);
        criarPlaylistPreenchida = new JButton("CRIAR PLAYLIST PREENCHIDA");
        criarPlaylistPreenchida.setBounds(500,300,220,40);

        criarPlaylistPreenchida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                String genero = JOptionPane.showInputDialog(frame, "Indique o género da nova playlist:");
                String tamanho = JOptionPane.showInputDialog(frame, "Indique o tamanho da nova playlist:");
                if (tamanho != null && !tamanho.isEmpty()) {
                        // Tenta converter a string para um inteiro
                        int tamanho1 = Integer.parseInt(tamanho);


                        //  Cria a playlist com genero e tamanho pretendido.
                    cliente.criarPlayListGenero(genero, tamanho1, app.getRockstar().getMusicas());

                } else {

                }
            }
        });

        //Adicionar componentes ao painel
        painelPlayList.add(caixaListarPlayLists);painelPlayList.add(listaMusicasPlayList);
        painelPlayList.add(removerPlayList);painelPlayList.add(alterarVisibilidade);
        painelPlayList.add(criarNovaPlayList);painelPlayList.add(removerMusicaPlayList);
        painelPlayList.add(botaoAtualizarPlaylists); painelPlayList.add(criarPlaylistPreenchida);

        //Criar painel Carrinho  ------------------------------------------------------------------------
        painelCarrinho = new JPanel();
        painelCarrinho.setBackground(new Color(255,178,102));
        painelCarrinho.setBounds(340, 200, 780, 500);
        painelCarrinho.setLayout(null);


        //Criar componentes do Painel Carrinho

        //JLabel
        listacompras = new JLabel ("LISTA DE MUSICAS DENTRO DO CARRINHO");
        listacompras.setBounds(50,0,280,40);
        saldoCliente = new JLabel("SALDO");
        saldoCliente.setBounds(550,200, 250, 40);

        //JTabel
        listaMusicasCarrinho = new JTable();
        listaMusicasCarrinho.setBounds(50, 50,250,300);

        //JButton
        removerMusicaCarrinho = new JButton("REMOVER MÚSICA CARRINHO");
        removerMusicaCarrinho.setBounds(400, 50, 250,40);
        removerMusicaCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexMusicaSelect = listaMusicasCarrinho.getSelectedRow();
                String valorTituloMusica = (String) listaMusicasCarrinho.getValueAt(indexMusicaSelect, 0);
                MusicaPaga object = (MusicaPaga) app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                if (object!=null){
                    cliente.compra.removerMusica(object);
                    tabelaCarrinho();
                }else  JOptionPane.showMessageDialog(null, "Nenhuma música selecionada");
            }
        });
        carregarSaldo = new JButton("CARREGAR SALDO");
        carregarSaldo.setBounds(400,100,250,40);
        carregarSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double valor= Double.parseDouble(valorACarregar.getText());
                cliente.alterarSaldo(valor);
                mostrarSaldoCliente.setText(String.format("%.2f",cliente.getSaldo()) + " €");
            }
        });
        finalizarPagamento = new JButton("FINALIZAR PAGAMENTO");
        finalizarPagamento.setBounds(400,150,250,40);
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
        valorACarregar = new JTextField("€ a carregar");
        valorACarregar.setBounds(660,100,100,40);


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
        painelCarrinho.add(valorACarregar);


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
        botaoPlayList.setBounds(70, 250, 250, 100);
        botaoPlayList.setFont(new Font("Arial", Font.BOLD, 20));
        botaoCarrinho = new JButton("CARRINHO");
        botaoCarrinho.setBounds(70, 400, 250, 100);
        botaoCarrinho.setFont(new Font("Arial", Font.BOLD, 20));
        username = new JLabel("Bem Vindo " + cliente.getUsername());
        username.setBounds(70, 70, 250, 20);
        username = new JLabel("Olá " + cliente.getUsername());
        username.setBounds(70, 75, 100, 20);
        botaoLogout = new JButton("LOGOUT");
        botaoLogout.setBounds(70,460,250,30);

        botaoLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        botaoPlayList.addActionListener(e->trocarPainel(painelPlayList));
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
    private void atualizaListaPlayList() {
        // Ver as playLists do Cliente

        ArrayList<PlayList> playList = cliente.verPlayListCliente();

        // Cria um array de objetos PlayList
        PlayList[] playlistsArray = playList.toArray(new PlayList[0]);

        DefaultComboBoxModel<PlayList> model = new DefaultComboBoxModel<>(playlistsArray);
        caixaListarPlayLists.setModel(model);
    }
    private void adicionarElementosTabela(ArrayList<Musica> lista,DefaultTableModel modelo){
        for (Musica musica : lista) {
            if (musica instanceof MusicaPaga) {
                modelo.addRow(new Object[]{musica.getTitulo(), musica.getNomeArtista(),musica.getDataCriacao(), musica.getDuracao(),
                        musica.getGenero(), musica.tipoEstado(), ((MusicaPaga) musica).getPreco(), musica.getRatingMedia()});
            }else {
                modelo.addRow(new Object[]{musica.getTitulo(), musica.getNomeArtista(),musica.getDataCriacao(), musica.getDuracao(),
                        musica.getGenero(), musica.tipoEstado(), "0", musica.getRatingMedia()});
            }
        }
    }
    private void titulosDasColunasTabela(DefaultTableModel modelo){
        // Adicionar uma coluna à tabela
        modelo.addColumn("TÍTULO");
        modelo.addColumn("ARTISTA");
        modelo.addColumn("DATA");
        modelo.addColumn("DURAÇÃO");
        modelo.addColumn("GÉNERO");
        modelo.addColumn("ESTADO");
        modelo.addColumn("PREÇO");
        modelo.addColumn("RATING");

        // Adicionar os títulos das colunas Na primeira linha
        modelo.addRow(new Object[]{"TÍTULO","ARTISTA","DATA","DURAÇÃO","GÉNERO","ESTADO","PREÇO","RATING"});
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

    private void janelaDasPlaylists(boolean visivel) {

        janelaPlaylists = new JFrame();
        JTable tabPlaylists = new JTable();

        DefaultTableModel listarPlaylists = new DefaultTableModel();
        ArrayList<PlayList> listaP = cliente.verPlayListCliente();

        listarPlaylists.addColumn("Título");
        listarPlaylists.addColumn("Tamanho");

        listarPlaylists.addRow(new Object[]{"Título","Tamanho"});

        for (PlayList p : listaP) {
            listarPlaylists.addRow(new Object[]{p.getNome(), p.getNumMusicas()});
        }

        tabPlaylists.setModel(listarPlaylists);

        JButton okButtonPl = new JButton("ADICIONAR");

        janelaPlaylists.add(tabPlaylists,BorderLayout.CENTER);
        janelaPlaylists.add(okButtonPl,BorderLayout.SOUTH);
        janelaPlaylists.pack();
        janelaPlaylists.setLocationRelativeTo(null);
        janelaPlaylists.setResizable(false);
        janelaPlaylists.setVisible(visivel);

        okButtonPl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int indexMusicaSelect = tabelaResultadoPesquisa.getSelectedRow();
                String valorTituloMusica = (String) tabelaResultadoPesquisa.getValueAt(indexMusicaSelect, 0);
                Musica object = app.rockstar.pesquisaObjetoTitulo(valorTituloMusica);

                int indexPlaylistSelect = tabPlaylists.getSelectedRow();
                String tituloPlaylist = (String) tabPlaylists.getValueAt(indexPlaylistSelect, 0);

                if (cliente.existeMusica(tituloPlaylist, object)){
                    JOptionPane.showMessageDialog(null, "Música já existe na " + tituloPlaylist);
                }else{
                    cliente.adicionarMusica(tituloPlaylist,object);
                    JOptionPane.showMessageDialog(null, "Música adicionada à playlist " + tituloPlaylist);
                }

                janelaPlaylists.setVisible(false);
            }
        });
    }
}


