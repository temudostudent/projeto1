import javax.swing.*;
import java.awt.*;

public class InterfaceCliente {
    private JFrame janelaCliente;
    private JPanel painelPesquisarCliente, painelTituloCliente, painelMenu, painelPlayList, painelCarrinho;
    private JLabel tituloCliente, username, pesquisaCliente, pesquisaPlaylist, pesquisacliente1,
            ordenarMusicasCliente, listacompras, valorTotalPagar, saldoCliente;
    private JButton botaoPesquisar, botaoPlayList, botaoCarrinho, adicionarCarrinho, adicionarPlayList,
            removerPlayList, alterarVisibilidade, criarNovaPlayList, removerMusicaPlayList,
            removerMusicaCarrinho, carregarSaldo, finalizarPagamento;
    private JComboBox caixaPesquisarCliente, caixaPesquisarPlayList, ordenarPorCliente, caixaListarPlayLists;
    private JRadioButton botaoAscendenteCliente, botaoDescendenteCliente;
    private ButtonGroup botaoEstado1;
    private JTextField caixaTextoPesquisa, mostrarValorPagar, mostrarSaldoCliente;
    private JTable tabelaResultadoPesquisa, listaMusicasPlayList, listaMusicasCarrinho;

    public InterfaceCliente() {

        //Criar janela
        janelaCliente = new JFrame();

        //Criar Painel Pesquisar --------------------------------------------------------------
        painelPesquisarCliente = new JPanel();
        painelPesquisarCliente.setBackground(new Color(225, 145, 102));
        painelPesquisarCliente.setBounds(340, 200, 780, 500);
        painelPesquisarCliente.setLayout(null);

        //Criar componentes do Painel Pesquisar
        pesquisacliente1 = new JLabel("PESQUISAR POR:");
        pesquisacliente1.setBounds(50,60, 180,40);
        caixaPesquisarCliente = new JComboBox<>();
        caixaPesquisarCliente.addItem("MÚSICA");
        caixaPesquisarCliente.addItem("PLAYLIST");
        caixaPesquisarCliente.setBounds(50,60, 180,40);
        pesquisaCliente = new JLabel("PESQUISAR POR: ");
        pesquisaCliente.setBounds(50, 10, 150, 40);
        pesquisaPlaylist = new JLabel("PESQUISAR PLAYLIST POR:");
        pesquisaPlaylist.setBounds(250, 10, 180, 40);
        caixaPesquisarPlayList = new JComboBox<>();
        caixaPesquisarPlayList.addItem("TÍTULO");
        caixaPesquisarPlayList.addItem("ARTISTA");
        caixaPesquisarPlayList.setBounds(250, 60, 180, 40);
        ordenarMusicasCliente = new JLabel("ORDENAR POR:");
        ordenarMusicasCliente.setBounds(450,10,150,40);
        ordenarPorCliente = new JComboBox<>();
        ordenarPorCliente.setBounds(450, 60, 150, 40);
        ordenarPorCliente.addItem("TÍTULO");
        ordenarPorCliente.addItem("DURAÇÃO");
        botaoAscendenteCliente = new JRadioButton("Ascendente");
        botaoAscendenteCliente.setBounds(650, 50, 100, 20);
        botaoAscendenteCliente.setBackground(null);
        botaoDescendenteCliente = new JRadioButton("Descendente");
        botaoDescendenteCliente.setBounds(650,80,100,20);
        botaoDescendenteCliente.setBackground(null);
        botaoEstado1 = new ButtonGroup();
        botaoEstado1.add(botaoAscendenteCliente);
        botaoEstado1.add(botaoDescendenteCliente);
        caixaTextoPesquisa = new JTextField();
        caixaTextoPesquisa.setBounds(50,120,180,40);
        tabelaResultadoPesquisa = new JTable();
        tabelaResultadoPesquisa.setBounds(50,170, 600,180);
        adicionarCarrinho = new JButton("ADICIONAR AO CARRINHO");
        adicionarCarrinho.setBounds(80,400 ,200,40);
        adicionarPlayList = new JButton("ADICIONAR A PLAYLIST");
        adicionarPlayList.setBounds(350,400,200,40);

        //Adicionar componentes ao painel Pesquisar
        painelPesquisarCliente.add(caixaPesquisarCliente);painelPesquisarCliente.add(pesquisaPlaylist);
        painelPesquisarCliente.add(caixaPesquisarPlayList);painelPesquisarCliente.add(pesquisaCliente);
        painelPesquisarCliente.add(ordenarMusicasCliente); painelPesquisarCliente.add(ordenarPorCliente);
        painelPesquisarCliente.add(botaoAscendenteCliente); painelPesquisarCliente.add(botaoDescendenteCliente);
        painelPesquisarCliente.add(caixaTextoPesquisa); painelPesquisarCliente.add(tabelaResultadoPesquisa);
        painelPesquisarCliente.add(adicionarCarrinho); painelPesquisarCliente.add(adicionarPlayList);

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

