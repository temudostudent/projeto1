import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;


public class InterfaceArtista implements Serializable {

    private Artista artista;
    private GestaoApp app;
    private JFrame janelaArtista;
        private JPanel painelTitulo, painelPesquisar, painelMenu, painelMusicas, painelEstatisticas, painelAdicionarMusica,
                painelEditarDados, painelAlbum;
        private JLabel titulo, ordenarMusicas, tituloMusica, duracao, genero, custo, estado, username1,
                pesquisaTitulo, alterarTitulo, nomeAlbum, generoAlbum,
                totalUtilizadores, totalMusicas, valorTotalColecao, valorTotalVendas, musicaMaisGravada, musicaMaisComprada,
                totalAlbumGenero ;
        private JButton botaoPesquisar, botaoMusicas, botaoEstatisticas, criarMusica, editarDados, adicionarMusica,
                pesquisarMusica, guardarAlteracao, listarMusicas, menuAlbum, criarAlbum, criarListaMusicas,
                adicionarMusicaAlbum, botaorefresh, historicoPreco, botaoLogout;
        private JTextField caixaTituloMusica, caixaDuracao, caixaGenero, caixaCusto,
                caixaAlteracao, caixaNomeAlbum, caixaGeneroAlgum,
                caixaTotalUtilizadores, caixaTotalMusicas, caixaValorTotalColecao, caixaValorTotalVendas, caixaMusicaMaisGravada,
                caixaMusicaMaisComprada,  inserirTitulo;
        private JRadioButton botaoAscendente, botaoDescendente, estadoAtivo, estadoInativo, estadoAtivo1, estadoInativo1,
                botaoListaMusicas, botaoParaPesquisarMusicas, alterarTituloMusica, alterarPreco1, alterarEstado;
        private JComboBox caixaPesquisarMusica, ordenarPor;
        private ButtonGroup botaoEstado, botaoEstado1, grupoPesquisa, botaogeral;
        private JTable tabelaListaMusicasPesquisar, tabelaListaMusicas, listaAlbumGenero1, listaMusicasAlbum, listaAlbuns;
        private JScrollPane scrollListarMusicas, scrollListarlistarItems, scrollListarAlbuns, scrollListarMusicasAlbum,
                tabelaEstatisticas;


        public void run(){
            try{
                InterfaceArtista janela = new InterfaceArtista(artista, app);
                janelaArtista.setVisible(true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public InterfaceArtista(Artista a, GestaoApp app) {
            this.artista = a;
            this.app = app;
            initialize();
        }

        protected void initialize(){
            // criar janela
            janelaArtista = new JFrame();

            //Criar Painel Pesquisar -------------------------------------------------
            painelPesquisar = new JPanel();
            painelPesquisar.setBackground(new Color(255,178,102));
            painelPesquisar.setBounds(340,200,780,500);
            painelPesquisar.setLayout(null);

            //Criar Componentes do Painel Pesquisar

            //JTable e JScrollPane
            tabelaListaMusicasPesquisar = new JTable();

            scrollListarlistarItems = new JScrollPane(tabelaListaMusicasPesquisar);
            scrollListarlistarItems.setBounds(40,180,700,200);


            //JLabel
            ordenarMusicas = new JLabel("ORDENAR POR:");
            ordenarMusicas.setBounds(350,10,150,40);

            //JButton
            JButton okPesquisa = new JButton("OK");
            okPesquisa.setBounds(710,450,60,40);
            okPesquisa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel listarItems = new DefaultTableModel();
                    String selecao = (String) ordenarPor.getSelectedItem();

                    tabelaListaMusicasPesquisar.setModel(listarItems);
                    scrollListarlistarItems.setViewportView(tabelaListaMusicasPesquisar);
                    tabelaListaMusicas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                    if (botaoListaMusicas.isSelected()){
                        ArrayList<Musica> listaM = new ArrayList<>();
                        listaM.addAll(artista.getMusicas());

                        if ("TÍTULO".equals(selecao) && botaoAscendente.isSelected()) {
                            app.rockstar.ordenarMusicasCrescentePorTitulo(listaM);
                        } else if ("TÍTULO".equals(selecao) && botaoDescendente.isSelected()) {
                            app.rockstar.ordenarMusicasDecrescentePorTitulo(listaM);
                        } else if ("GÉNERO".equals(selecao) && botaoAscendente.isSelected()) {
                            app.rockstar.ordenarMusicasCrescentePorGenero(listaM);
                        } else if ("GÉNERO".equals(selecao) && botaoDescendente.isSelected()){
                            app.rockstar.ordenarMusicasDecrescentePorGenero(listaM);
                        }
                        // Adicionar uma coluna à tabela e respetivos titulos

                        listarItems.addColumn("TÍTULO");
                        listarItems.addColumn("DATA");
                        listarItems.addColumn("DURACAO");
                        listarItems.addColumn("GENERO");
                        listarItems.addColumn("ESTADO");
                        listarItems.addColumn("PREÇO (€)");
                        listarItems.addColumn("RATING");


                        // Adicionar os elementos do ArrayList à tabela
                        for (Musica musica : listaM) {
                            if (musica instanceof MusicaPaga) {
                                listarItems.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                        musica.getGenero(), musica.tipoEstado(), ((MusicaPaga) musica).getPreco(), musica.getRatingMedia()});
                            }else {
                                listarItems.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                        musica.getGenero(), musica.tipoEstado(), "0" , musica.getRatingMedia()});
                            }
                        }


                    } else if (botaoParaPesquisarMusicas.isSelected()) {
                        ArrayList<Album> listaA = new ArrayList<>();
                        listaA.addAll(artista.getAlbuns());

                            if ("TÍTULO".equals(selecao) && botaoAscendente.isSelected()) {
                                app.rockstar.ordenarAlbunsCrescentePorTitulo(listaA);
                            } else if ("TÍTULO".equals(selecao) && botaoDescendente.isSelected()) {
                                app.rockstar.ordenarAlbunsDecrescentePorTitulo(listaA);
                            } else if ("GÉNERO".equals(selecao) && botaoAscendente.isSelected()) {
                                app.rockstar.ordenarAlbunsCrescentePorGenero(listaA);
                            } else if ("GÉNERO".equals(selecao) && botaoDescendente.isSelected()){
                                app.rockstar.ordenarAlbunsDecrescentePorGenero(listaA);
                            }

                        // Adicionar as coluna à tabela e respetivos titulos
                        listarItems.addColumn("TÍTULO");
                        listarItems.addColumn("DATA");
                        listarItems.addColumn("GÉNERO");

                        // Adicionar os elementos do ArrayList à tabela
                        for (Album a : listaA) {
                            listarItems.addRow(new Object[]{a.getNome(), a.getDataCriacao(), a.getGenero()});
                        }
                    }
                }
            });


            //JComboBox
            ordenarPor = new JComboBox<>();
            ordenarPor.setBounds(350, 60, 150, 40);
            ordenarPor.addItem("TÍTULO");
            ordenarPor.addItem("GÉNERO");


            //JRadioButton
            botaoListaMusicas = new JRadioButton("Ver todas as minhas músicas");
            botaoListaMusicas.setBounds(50, 20, 220, 40);
            botaoListaMusicas.setBackground(null);
            botaoListaMusicas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel listarMusicas = new DefaultTableModel();
                    ArrayList<Musica> listaM = new ArrayList<>();
                    listaM = artista.getMusicas();


                    // Adicionar as coluna à tabela e respetivos titulos
                    listarMusicas.addColumn("TÍTULO");
                    listarMusicas.addColumn("DATA");
                    listarMusicas.addColumn("DURACAO");
                    listarMusicas.addColumn("GENERO");
                    listarMusicas.addColumn("ESTADO");
                    listarMusicas.addColumn("PREÇO (€)");
                    listarMusicas.addColumn("RATING");

                    tabelaListaMusicasPesquisar.setModel(listarMusicas);
                    scrollListarlistarItems.setViewportView(tabelaListaMusicasPesquisar);


                    // Adicionar os elementos do ArrayList à tabela
                    for (Musica musica : listaM) {
                        if (musica instanceof MusicaPaga) {
                            listarMusicas.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                    musica.getGenero(), musica.tipoEstado(), ((MusicaPaga) musica).getPreco(), musica.getRatingMedia()});
                        }else {
                            listarMusicas.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                    musica.getGenero(), musica.tipoEstado(), "0" , musica.getRatingMedia()});
                        }
                    }

                }
            });

            botaoParaPesquisarMusicas = new JRadioButton("Ver todos os meus álbuns");
            botaoParaPesquisarMusicas.setBounds(50,70,220,40);
            botaoParaPesquisarMusicas.setBackground(null);
            botaoParaPesquisarMusicas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel listarAlbuns = new DefaultTableModel();
                    ArrayList<Album> listaA = artista.getAlbuns();

                    // Adicionar as coluna à tabela e respetivos titulos
                    listarAlbuns.addColumn("TÍTULO");
                    listarAlbuns.addColumn("DATA");
                    listarAlbuns.addColumn("GÉNERO");



                    // Adicionar os elementos do ArrayList à tabela
                    for (Album a : listaA) {
                        listarAlbuns.addRow(new Object[]{a.getNome(), a.getDataCriacao(), a.getGenero()});
                    }
                    tabelaListaMusicasPesquisar.setModel(listarAlbuns);
                    scrollListarlistarItems.setViewportView(tabelaListaMusicasPesquisar);
                }
            });
            botaoAscendente = new JRadioButton("Ascendente");
            botaoAscendente.setBounds(550, 35, 100, 20);
            botaoAscendente.setBackground(null);
            botaoAscendente.setSelected(true);
            botaoDescendente = new JRadioButton("Descendente");
            botaoDescendente.setBounds(550,65,130,20);
            botaoDescendente.setBackground(null);

            //ButtonGroup
            grupoPesquisa = new ButtonGroup();
            grupoPesquisa.add(botaoListaMusicas);
            grupoPesquisa.add(botaoParaPesquisarMusicas);
            botaoEstado = new ButtonGroup();
            botaoEstado.add(botaoAscendente);
            botaoEstado.add(botaoDescendente);

            //Adicionar Componentes ao Painel Pesquisar

            painelPesquisar.add(ordenarMusicas);
            painelPesquisar.add(ordenarPor);
            painelPesquisar.add(botaoDescendente);
            painelPesquisar.add(botaoAscendente);
            painelPesquisar.add(tabelaListaMusicasPesquisar);
            painelPesquisar.add(okPesquisa);
            painelPesquisar.add(botaoParaPesquisarMusicas);
            painelPesquisar.add(botaoListaMusicas);
            painelPesquisar.add(scrollListarlistarItems);

            //Criar Painel Musicas
            painelMusicas = new JPanel();
            painelMusicas.setBackground(new Color(255,178,102));
            painelMusicas.setBounds(340,200,780,500);
            painelMusicas.setLayout(null);

            //Criar componentes do Painel Musicas
            criarMusica = new JButton("CRIAR MÚSICA"); criarMusica.setBounds(50,20,180, 40);
            editarDados = new JButton("EDITAR DADOS"); editarDados.setBounds(250, 20, 180,40);
            menuAlbum = new JButton("ÁLBUM"); menuAlbum.setBounds(450,20,180,40);

            //Adicionar componentes ao painel Musicas
            painelMusicas.add(criarMusica);
            painelMusicas.add(editarDados);
            painelMusicas.add(menuAlbum);


            //Criar painel Adicionar Musicas
            painelAdicionarMusica = new JPanel();
            painelAdicionarMusica.setBackground(new Color(255,178,102));
            painelAdicionarMusica.setBounds(340,200,780,500);
            painelAdicionarMusica.setLayout(null);

            //Criar Componentes ao painel

            tituloMusica = new JLabel("TÍTULO");
            tituloMusica.setBounds(200, 100, 180,40);
            duracao = new JLabel("DURAÇÃO");
            duracao.setBounds(200, 150, 180, 40);
            genero = new JLabel("GÉNERO");
            genero.setBounds(200, 200, 180,40);
            custo = new JLabel("PREÇO");
            custo.setBounds(200, 250,180,40);
            estado = new JLabel("ESTADO");
            estado.setBounds(200,300,180,40);
            caixaTituloMusica = new JTextField();
            caixaTituloMusica.setBounds(400, 100, 180, 40);
            caixaDuracao = new JTextField();
            caixaDuracao.setBounds(400,150,180,40);
            caixaGenero = new JTextField();
            caixaGenero.setBounds(400,200,180,40);
            caixaCusto = new JTextField();
            caixaCusto.setBounds(400,250,180,40);
            estadoAtivo = new JRadioButton("ATIVO");
            estadoAtivo.setBackground(null); estadoAtivo.setBounds(400, 300, 100,40);
            estadoAtivo.setSelected(true);
            estadoInativo = new JRadioButton("INATIVO");
            estadoInativo.setBounds(500, 300, 100 ,40);
            estadoInativo.setBackground(null);
            adicionarMusica = new JButton("ADICIONAR MÚSICA");
            adicionarMusica.setBounds(400, 350, 180, 40);
            adicionarMusica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String titulo=caixaTituloMusica.getText();
                    String duracao= caixaDuracao.getText();
                    String genero=caixaGenero.getText();
                    boolean estadoMusica = true;

                    if (estadoInativo.isSelected()){
                        estadoMusica = false;
                    }

                    String preco = caixaCusto.getText();
                    try{
                        //Verifica se os campos estao preenchidos
                        if(!titulo.isEmpty() && !duracao.isEmpty() && !genero.isEmpty() && !preco.isEmpty()){
                            double duracao1 = Double.parseDouble(duracao);
                            double preco1 = Double.parseDouble(preco);
                            Musica m = artista.novaMusica(titulo,duracao1,genero,estadoMusica,preco1);


                            if(m != null ) {
                                artista.addMusica(m);
                                if (m.getEstado()) {
                                    app.rockstar.adicionarMusica(m);
                                }
                            }
                            caixaTituloMusica.setText("");
                            caixaDuracao.setText("");
                            caixaGenero.setText("");
                            caixaCusto.setText("");

                        }else{
                            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
                        }
                    }catch (NumberFormatException ex) {
                        // Caso os campos numéricas não sejam preenchidos em conformidade, trata a exceção
                        JOptionPane.showMessageDialog(null, "Dados inválidos. Tente novamente");
                    }
                }
            });

            ButtonGroup ativoOuInativo = new ButtonGroup();
            ativoOuInativo.add(estadoAtivo);
            ativoOuInativo.add(estadoInativo);

            //Adicionar componentes ao painel Adicionar Musicas
            painelAdicionarMusica.add(tituloMusica);
            painelAdicionarMusica.add(duracao);
            painelAdicionarMusica.add(genero);
            painelAdicionarMusica.add(custo);
            painelAdicionarMusica.add(estado);
            painelAdicionarMusica.add(caixaTituloMusica);
            painelAdicionarMusica.add(caixaDuracao);
            painelAdicionarMusica.add(caixaGenero);
            painelAdicionarMusica.add(caixaCusto);
            painelAdicionarMusica.add(estadoAtivo);
            painelAdicionarMusica.add(estadoInativo);
            painelAdicionarMusica.add(adicionarMusica);

            //Criar Painel Editar Dados
            painelEditarDados = new JPanel();
            painelEditarDados.setBackground(new Color(255,178,102));
            painelEditarDados.setBounds(340,200,780,500);
            painelEditarDados.setLayout(null);

            //Criar componentes do painel Editar Dados

            caixaPesquisarMusica = new JComboBox<>();
            caixaPesquisarMusica.addItem("TÍTULO");
            caixaPesquisarMusica.addItem("GÉNERO");
            caixaPesquisarMusica.addItem("ÁLBUM");
            caixaPesquisarMusica.setBounds(50,40, 150,30);
            pesquisaTitulo = new JLabel("PESQUISA MÚSICA POR:");
            pesquisaTitulo.setBounds(50,10,150,20);
            inserirTitulo = new JTextField();
            inserirTitulo.setBounds(250,10,150,40);
            pesquisarMusica = new JButton("PESQUISAR");
            pesquisarMusica.setBounds(430,10,150,40);
            tabelaListaMusicas = new JTable();
            scrollListarMusicas = new JScrollPane(tabelaListaMusicas);
            scrollListarMusicas.setBounds(30,80,470,300);
            pesquisarMusica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //Obter a seleção da JComboBox
                    String selecao = (String) caixaPesquisarMusica.getSelectedItem();

                    tabelaListaMusicas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    scrollListarMusicas.setViewportView(tabelaListaMusicas);

                    // Verificar qual item foi selecionado
                    if ("TÍTULO".equals(selecao)) {
                        String titulo = inserirTitulo.getText();
                        DefaultTableModel listaMusicas = new DefaultTableModel();
                        ArrayList<Musica> lista = artista.pesquisarMusica(titulo);

                        titulosDasColunasTabela(listaMusicas);

                        adicionarElementosTabela(lista, listaMusicas);

                        tabelaListaMusicas.setModel(listaMusicas);


                    } else if ("GÉNERO".equals(selecao)) {
                        String genero = inserirTitulo.getText();
                        DefaultTableModel listaMusicas = new DefaultTableModel();
                        ArrayList<Musica> lista = artista.pesquisarMusicaPorGenero(genero);

                        titulosDasColunasTabela(listaMusicas);
                        adicionarElementosTabela(lista, listaMusicas);
                        tabelaListaMusicas.setModel(listaMusicas);


                    } else if("ÁLBUM".equals(selecao)){
                        String tituloAlbum = inserirTitulo.getText();
                        DefaultTableModel listaMusicas = new DefaultTableModel();
                        ArrayList<Musica> lista = artista.pesquisaMusicaAlbum(tituloAlbum);

                        titulosDasColunasTabela(listaMusicas);
                        adicionarElementosTabela(lista, listaMusicas);
                        tabelaListaMusicas.setModel(listaMusicas);



                    }else{
                        JOptionPane.showMessageDialog(null, " Música não encontrada!");
                    }

                }
            });

            alterarTitulo = new JLabel("O QUE PRETENDE ALTERAR?");
            alterarTitulo.setBounds(550, 60, 180, 40);
            alterarTituloMusica = new JRadioButton("TÍTULO"); alterarTituloMusica.setBackground(null);
            alterarTituloMusica.setBounds(550,100,180,40);
            alterarPreco1 =  new JRadioButton("PREÇO"); alterarPreco1.setBounds(550,150,180,40);
            alterarPreco1.setBackground(null);
            alterarEstado = new JRadioButton("ESTADO"); alterarEstado.setBounds(550,250,180,40);
            alterarEstado.setBackground(null);
            caixaAlteracao = new JTextField(); caixaAlteracao.setBounds(550,200,180,40);
            estadoAtivo1 = new JRadioButton("ATIVO"); estadoAtivo1.setBackground(null);
            estadoAtivo1.setBounds(550, 300, 100,40);
            estadoInativo1 = new JRadioButton("INATIVO"); estadoInativo1.setBackground(null);
            estadoInativo1.setBounds(650,300,100,40 );
            botaoEstado1 = new ButtonGroup(); botaoEstado1.add(estadoAtivo1); botaoEstado1.add(estadoInativo1);
            guardarAlteracao = new JButton("GUARDAR ALTERAÇÕES");
            guardarAlteracao.setBounds(550,350,180,40);
            botaogeral = new ButtonGroup(); botaogeral.add(alterarTituloMusica); botaogeral.add(alterarPreco1);
            botaogeral.add(alterarEstado);
            historicoPreco = new JButton("HISTÓRICO DE PREÇO");
            historicoPreco.setBounds(550,400,180,40);
            historicoPreco.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int linhaSelecionada = tabelaListaMusicas.getSelectedRow();
                    if (linhaSelecionada >= 0) {
                        String tituloMusica = (String) tabelaListaMusicas.getValueAt(linhaSelecionada, 0);
                        Musica object = artista.pesquisaObjetoPorTitulo(tituloMusica);
                        if (object instanceof MusicaPaga) {

                            // Converter o Map para um DefaultTableModel
                            DefaultTableModel listaPrecos = new DefaultTableModel();
                            listaPrecos.addRow(new Object[]{"Data", "Preço"});


                            //Criar um Map para armazenar os valores
                            Map<LocalDateTime, Double> historicoPreco;
                            historicoPreco = ((MusicaPaga) object).getHistoricoPreco();
                            DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                            for (Map.Entry<LocalDateTime, Double> entry : historicoPreco.entrySet()) {
                                Object[] rowData = {formatoData.format(entry.getKey()), entry.getValue() + " € "};
                                listaPrecos.addRow(rowData);
                            }

                            //Adicionar o modelo de tabela à tabela já existente
                            tabelaListaMusicas.setModel(listaPrecos);
                        }
                    }
                }
            });
            tabelaListaMusicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int linhaSelecionada = tabelaListaMusicas.getSelectedRow();
                        if (linhaSelecionada >= 0) {
                            // Faça algo com a linha selecionada, se necessário
                        }
                    }
                }
            });

            guardarAlteracao.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int linhaSelecionada = tabelaListaMusicas.getSelectedRow();
                    if (linhaSelecionada >= 0) {

                        String TituloMusica = (String) tabelaListaMusicas.getValueAt(linhaSelecionada, 0);
                        Musica object = artista.pesquisaObjetoPorTitulo(TituloMusica);

                        if (!caixaAlteracao.equals(null)) {

                            if (alterarTituloMusica.isSelected()) {
                                if (!caixaAlteracao.equals(null)) {
                                    object.setTitulo(caixaAlteracao.getText());
                                    app.rockstar.atualizarTituloMusica(object.getIdMusica(),caixaAlteracao.getText());
                                    JOptionPane.showMessageDialog(null, "Título Alterado com Sucesso.");
                                    atualizarTabelaMusicas1();
                                }else {
                                    JOptionPane.showMessageDialog(null, "Música não encontrada!");
                                }

                            }else if (alterarEstado.isSelected()) {
                                if (estadoAtivo1.isSelected() && !object.getEstado()) {
                                    object.setEstado(true);
                                    app.rockstar.adicionarMusica(object);
                                    JOptionPane.showMessageDialog(null, "Estado Alterado com Sucesso.");
                                    atualizarTabelaMusicas1();
                                } else if(estadoInativo1.isSelected() && object.getEstado()){
                                    object.setEstado(false);
                                    app.rockstar.inativarMusica(object.getIdMusica());
                                    JOptionPane.showMessageDialog(null, "Estado Alterado com Sucesso.");
                                    atualizarTabelaMusicas1();
                                }
                            }
                            else if(alterarPreco1.isSelected()){
                                if(object instanceof MusicaPaga){
                                    ((MusicaPaga) object).setPreco(Double.parseDouble(caixaAlteracao.getText()));
                                    app.rockstar.atualizarPrecoMusica(object.getIdMusica(),Double.parseDouble(caixaAlteracao.getText()));
                                    JOptionPane.showMessageDialog(null, "Preço alterado com Sucesso.");
                                    atualizarTabelaMusicas();

                                }else{
                                    MusicaPaga musicaPaga = new MusicaPaga(object.getTitulo(), object.getOriginalDataCriacao(),
                                            object.getNomeArtista(), object.getDuracao(), object.getGenero(),
                                            object.getEstado(), Double.parseDouble(caixaAlteracao.getText()), object.getIdMusica()
                                            ,object.getRating());
                                    artista.getMusicas().set(linhaSelecionada,musicaPaga);
                                    app.rockstar.getMusicas().set(app.rockstar.encontrarPosicao(object.getIdMusica()),musicaPaga);
                                    JOptionPane.showMessageDialog(null, "Preço alterado com Sucesso.");
                                    atualizarTabelaMusicas();
                                }
                            }
                            System.out.println(object);

                        } else {
                            JOptionPane.showMessageDialog(null, "Preencha o campo!");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
                    }
                }
            });

            listarMusicas = new JButton("LISTAR MÚSICAS");
            listarMusicas.setBounds(610,10,150,40);

            listarMusicas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tabelaListaMusicas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    atualizarTabelaMusicas1();
                }
            });

            //Adicionar componentes ao painel
            painelEditarDados.add(pesquisaTitulo);painelEditarDados.add(inserirTitulo);
            painelEditarDados.add(alterarTitulo); painelEditarDados.add(alterarTitulo);
            painelEditarDados.add(alterarPreco1); painelEditarDados.add(caixaAlteracao);
            painelEditarDados.add(estadoAtivo1); painelEditarDados.add(estadoInativo1);
            painelEditarDados.add(alterarEstado); painelEditarDados.add(guardarAlteracao);
            painelEditarDados.add(listarMusicas); painelEditarDados.add(scrollListarMusicas);
            painelEditarDados.add(caixaPesquisarMusica); painelEditarDados.add(pesquisarMusica);
            painelEditarDados.add(alterarTituloMusica); painelEditarDados.add(historicoPreco);
            painelEditarDados.add(tabelaListaMusicas);


            // Criar Painel Criar Album
            painelAlbum = new JPanel();
            painelAlbum.setBackground(new Color(255,178,102));
            painelAlbum.setBounds(340,200,780,500);
            painelAlbum.setLayout(null);

            //Criar componenentes do Painel
            criarAlbum = new JButton("CRIAR ÁLBUM");
            criarAlbum.setBounds(50,20,180,40);
            listaAlbuns = new JTable();

            listaMusicasAlbum = new JTable();
            scrollListarAlbuns = new JScrollPane(listaMusicasAlbum);
            scrollListarAlbuns.setBounds(300, 80, 200,300);
            scrollListarMusicasAlbum = new JScrollPane(listaAlbuns);
            scrollListarMusicasAlbum.setBounds(550,80,200,300);
            criarAlbum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String titulo = caixaNomeAlbum.getText();
                    String genero = caixaGeneroAlgum.getText();
                    if(!titulo.equals("") || !genero.equals("")) {
                        artista.criarAlbum(titulo, genero);
                        JOptionPane.showMessageDialog(null, "Álbum criado com sucesso!", "", JOptionPane.INFORMATION_MESSAGE);
                        caixaNomeAlbum.setText("");
                        caixaGeneroAlgum.setText("");
                    }else{
                        JOptionPane.showMessageDialog(null, "Por favor preencha os campos!");
                    }
                }
            });
            nomeAlbum = new JLabel("NOME DO ÁLBUM");
            nomeAlbum.setBounds(50, 100,180,40);
            caixaNomeAlbum = new JTextField();
            caixaNomeAlbum.setBounds(50, 150, 180,40);
            generoAlbum = new JLabel("GÉNERO");
            generoAlbum.setBounds(50,200,180,40);
            caixaGeneroAlgum = new JTextField();
            caixaGeneroAlgum.setBounds(50,250,180,40);
            criarListaMusicas = new JButton("LISTAR MÚSICAS");
            criarListaMusicas.setBounds(300, 20, 200, 30);
            criarListaMusicas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel listaMusicasPAlbum = new DefaultTableModel();
                    ArrayList<Musica> listaMusicasPainelAlbum = artista.getMusicas();

                    // Adicionar uma coluna à tabela
                    listaMusicasPAlbum.addColumn("Título");


                    // Adicionar os elementos do ArrayList à tabela
                    for (Musica musica : listaMusicasPainelAlbum) {
                        listaMusicasPAlbum.addRow(new Object[]{musica.getTitulo()});
                    }
                    listaMusicasAlbum.setModel(listaMusicasPAlbum);
                    scrollListarAlbuns.setViewportView(listaMusicasAlbum);
                }
            });


            listaMusicasAlbum.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int linhaSelecionada = listaMusicasAlbum.getSelectedRow();
                        if (linhaSelecionada != -1) {
                            // Obtendo os dados da linha selecionada
                            Object titulo = listaMusicasAlbum.getValueAt(linhaSelecionada, 0);

                            // Exibindo os dados (você pode fazer o que quiser com eles)
                            System.out.println("Título: " + titulo);
                        }
                    }
                }
            });

            // Adicionando um ouvinte de clique do mouse à tabela
            listaMusicasAlbum.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        int column = target.getSelectedColumn();
                    }
                }
            });


            listaAlbuns.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int linhaSelecionada = listaAlbuns.getSelectedRow();
                    }
                }
            });

            // Adicionando um ouvinte de clique do mouse à tabela
            listaAlbuns.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow();
                        int column = target.getSelectedColumn();
                    }
                }
            });

            JButton criarListaAlbuns = new JButton("LISTAR ÁLBUNS");
            criarListaAlbuns.setBounds(550,20,200,30);
            criarListaAlbuns.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    DefaultTableModel listaAlbunsModel = new DefaultTableModel();
                    // Adicionar uma coluna à tabela
                    listaAlbunsModel.addColumn("TÍTULO");
                    listaAlbunsModel.addColumn("GÉNERO");
                    listaAlbuns.setModel(listaAlbunsModel);
                    scrollListarMusicasAlbum.setViewportView(listaAlbuns);

                    ArrayList<Album> listaAlbumPainelAlbum = artista.getAlbuns();

                    // Adicionar os elementos do ArrayList à tabela
                    for (Album album : listaAlbumPainelAlbum) {
                        listaAlbunsModel.addRow(new Object[]{album.getNome(), album.getGenero()});
                    }

                }
            });


            adicionarMusicaAlbum = new JButton("ADICIONAR MÚSICA AO ÁLBUM");
            adicionarMusicaAlbum.setBounds(410, 400, 220,40);
            adicionarMusicaAlbum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int indexAlbum = listaAlbuns.getSelectedRow();
                    int indexMusicaSelect = listaMusicasAlbum.getSelectedRow();
                    if(indexMusicaSelect != -1 && indexAlbum != -1) {

                        artista.addMusicaAAlbumPelosIndexes(indexAlbum, indexMusicaSelect);
                    }else{
                        JOptionPane.showMessageDialog(null, "Selecione uma linha válida!");
                    }
                }
            });

            //Adicionar componentes ao Painel
            painelAlbum.add(nomeAlbum); painelAlbum.add(caixaNomeAlbum);
            painelAlbum.add(generoAlbum); painelAlbum.add(caixaGeneroAlgum);
            painelAlbum.add(criarAlbum); painelAlbum.add(criarListaMusicas); painelAlbum.add(listaMusicasAlbum);
            painelAlbum.add(criarListaAlbuns); painelAlbum.add(adicionarMusicaAlbum);
            painelAlbum.add(listaAlbuns); painelAlbum.add(scrollListarAlbuns);
            painelAlbum.add(scrollListarMusicasAlbum); painelAlbum.add(listaAlbuns);


            //Criar painel Estatisticas-------------------------------------------------------
            painelEstatisticas = new JPanel();
            painelEstatisticas.setBackground(new Color(255,178,102));
            painelEstatisticas.setBounds(340,200,780,500);
            painelEstatisticas.setLayout(null);

            //Criar componentes do painel Estatísticas
            totalUtilizadores = new JLabel("TOTAL UTILIZADORES");
            totalUtilizadores.setBounds(50,50,180,30);
            caixaTotalUtilizadores = new JTextField(); caixaTotalUtilizadores.setBounds(250,50,180,30);
            caixaTotalUtilizadores.setEditable(false);
            totalMusicas = new JLabel("TOTAL MÚSICAS");
            totalMusicas.setBounds(50,100,180,30);
            caixaTotalMusicas = new JTextField(); caixaTotalMusicas.setBounds(250,100,180,30);
            caixaTotalMusicas.setEditable(false);
            valorTotalColecao = new JLabel("VALOR TOTAL COLEÇÃO");
            valorTotalColecao.setBounds(50,150,180,30);
            caixaValorTotalColecao = new JTextField(); caixaValorTotalColecao.setBounds(250,150,180,30);
            caixaValorTotalColecao.setEditable(false);
            valorTotalVendas = new JLabel("VALOR TOTAL DE VENDAS");
            valorTotalVendas.setBounds(50,200,180,30);
            caixaValorTotalVendas = new JTextField(); caixaValorTotalVendas.setBounds(250,200,180,30);
            caixaValorTotalVendas.setEditable(false);
            botaorefresh = new JButton("ATUALIZAR");
            botaorefresh.setBounds(400,400,200,40);
            listaAlbumGenero1 = new JTable();
            tabelaEstatisticas = new JScrollPane(listaAlbumGenero1);
            tabelaEstatisticas.setBounds(500,100,300,150);

            botaorefresh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel listaMusicas = new DefaultTableModel();
                    String [][] dados = artista.matrizTotalAlbuns();
                    String [] colunas = {"Género", "Total álbuns"};


                    // Adicionar os elementos do ArrayList à tabela
                    for (String coluna : colunas) {
                        listaMusicas.addColumn(coluna);
                    }

                    // Adicionar os elementos do array à tabela
                    for (int i = 0; i < dados.length; i++) {
                        listaMusicas.addRow(dados[i]);
                    }

                    listaAlbumGenero1.setModel(listaMusicas);
                    caixaTotalUtilizadores.setText(String.valueOf(app.rockstar.totalUtilizadores(artista)));
                    caixaTotalMusicas.setText(String.valueOf(artista.totalMusicas()));
                    caixaValorTotalColecao.setText(String.format("%.2f",artista.valorTotalColecao()) + " €");
                    caixaValorTotalVendas.setText(String.format("%.2f",artista.getSaldo()) + " €");
                    caixaMusicaMaisGravada.setText(app.rockstar.musicaMaisAdicionada(artista));
                    caixaMusicaMaisComprada.setText(app.rockstar.musicaMaisComprada(artista));
                    tabelaEstatisticas.setViewportView(listaAlbumGenero1);

                }
            });
            musicaMaisGravada = new JLabel("MÚSICA MAIS GRAVADA");
            musicaMaisGravada.setBounds(50,250,180,30);
            caixaMusicaMaisGravada = new JTextField(); caixaMusicaMaisGravada.setBounds(250,250,180,30);
            caixaMusicaMaisGravada.setEditable(false);
            musicaMaisComprada = new JLabel("MÚSICA MAIS COMPRADA");
            musicaMaisComprada.setBounds(50,300,180,30);
            caixaMusicaMaisComprada = new JTextField(); caixaMusicaMaisComprada.setBounds(250,300,180,30);
            caixaMusicaMaisComprada.setEditable(false);
            totalAlbumGenero = new JLabel("TOTAL ÁLBUNS POR GÉNERO");
            totalAlbumGenero.setBounds(500, 50, 180,30);


            //Adicionar componentes do painel Estatísticas
            painelEstatisticas.add(totalUtilizadores);painelEstatisticas.add(totalMusicas);
            painelEstatisticas.add(valorTotalColecao);painelEstatisticas.add(valorTotalVendas);
            painelEstatisticas.add(musicaMaisGravada); painelEstatisticas.add(musicaMaisComprada);
            painelEstatisticas.add(caixaTotalUtilizadores); painelEstatisticas.add(caixaTotalMusicas);
            painelEstatisticas.add(caixaValorTotalColecao); painelEstatisticas.add(caixaValorTotalVendas);
            painelEstatisticas.add(caixaMusicaMaisGravada); painelEstatisticas.add(caixaMusicaMaisComprada);
            painelEstatisticas.add(totalAlbumGenero); painelEstatisticas.add(listaAlbumGenero1);
            painelEstatisticas.add(botaorefresh); painelEstatisticas.add(tabelaEstatisticas);

            //Criar painel fixo Titulo  ----------------------------------------------
            painelTitulo = new JPanel();
            painelTitulo.setBackground(new Color(255,178,102));
            painelTitulo.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
            titulo = new JLabel("ROCKSTAR INC.");
            titulo.setFont(new Font("Magneto", Font.BOLD, 80));
            painelTitulo.add(titulo);

            //Criar Painel Fixo Menu  ----------------------------------------------------
            painelMenu = new JPanel();
            painelMenu.setBackground(new Color(255,178,102));
            painelMenu.setLayout(null);

            //Criar componentes do painel Menu Fixo--------------------------------------
            botaoPesquisar = new JButton("PESQUISAR");
            botaoPesquisar.setBounds(70,100, 250,100);
            botaoPesquisar.setFont(new Font("Arial", Font.BOLD, 20));
            botaoMusicas = new JButton("MÚSICAS");
            botaoMusicas.setBounds(70,220,250,100);
            botaoMusicas.setFont(new Font("Arial", Font.BOLD, 20));
            botaoEstatisticas = new JButton("ESTATÍSTICAS");
            botaoEstatisticas.setBounds(70,340, 250,100);
            botaoEstatisticas.setFont(new Font("Arial", Font.BOLD, 20));
            username1 =new JLabel("Bem vindo " + artista.getUsername());
            username1.setFont(new Font("Arial", Font.BOLD, 16));
            username1.setBounds(70,75,400,20);
            botaoLogout = new JButton("LOGOUT");
            botaoLogout.setBounds(70,460,250,30);

            botaoLogout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.atualizaficheiro(app.rockstar.getClientes(), app.rockstar.getArtistas(),
                            app.rockstar.getMusicas(), app.rockstar.getPlaylists(), app.rockstar.getCompras());
                    janelaArtista.setVisible(false);

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
            painelMenu.add(botaoMusicas);
            painelMenu.add(botaoEstatisticas);
            painelMenu.add(username1);
            painelMenu.add(botaoLogout);


            //Adicionar à janela os paineis
            janelaArtista.add(painelTitulo, BorderLayout.NORTH);
            janelaArtista.add(painelPesquisar);
            janelaArtista.add(painelMusicas);
            painelMusicas.setVisible(false);
            janelaArtista.add(painelAdicionarMusica);
            painelAdicionarMusica.setVisible(false);
            janelaArtista.add(painelEditarDados);
            painelEditarDados.setVisible(false);
            janelaArtista.add(painelAlbum);
            painelAlbum.setVisible(false);
            janelaArtista.add(painelEstatisticas);
            painelEstatisticas.setVisible(false);
            janelaArtista.add(painelMenu);

            botaoMusicas.addActionListener(e -> trocarPainel(painelMusicas));
            botaoPesquisar.addActionListener(e->trocarPainel(painelPesquisar));
            criarMusica.addActionListener(e->trocarPainel(painelAdicionarMusica));
            editarDados.addActionListener(e->trocarPainel(painelEditarDados));
            menuAlbum.addActionListener(e->trocarPainel(painelAlbum));
            botaoEstatisticas.addActionListener(e-> trocarPainel(painelEstatisticas));

            janelaArtista.addWindowListener(new WindowAdapter() {
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
            janelaArtista.setSize(1200, 800);
            janelaArtista.setLocation(100, 100);
            janelaArtista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janelaArtista.setLocationRelativeTo(null);
            janelaArtista.setResizable(false);
            janelaArtista.setVisible(false);
        }

        //Método para efetuar a troca de painéis

        private void trocarPainel(JPanel novoPainel) {
            painelTitulo.setVisible(true);
            painelMenu.setVisible(true);
            painelPesquisar.setVisible(false);
            painelMusicas.setVisible(false);
            painelEstatisticas.setVisible(false);
            painelAdicionarMusica.setVisible(false);
            painelEditarDados.setVisible(false);
            painelAlbum.setVisible(false);

            novoPainel.setVisible(true);

        }

        public void atualizarTabelaMusicas (){

                    DefaultTableModel listaMusicas = new DefaultTableModel();
                    ArrayList<Musica> lista = new ArrayList<>();
                    lista = artista.getMusicas();

                    titulosDasColunasTabela(listaMusicas);

                    // Adicionar os elementos do ArrayList à tabela
                    for (Musica musica : lista) {
                        if(musica instanceof MusicaPaga) {
                            listaMusicas.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                    musica.getGenero(), musica.getEstado(), ((MusicaPaga) musica).getPreco(), musica.getRatingMedia()});
                        }else{
                            listaMusicas.addRow(new Object[]{musica.getTitulo(), musica.getDataCriacao(), musica.getDuracao(),
                                    musica.getGenero(), musica.tipoEstado(), "0" , musica.getRatingMedia()});
                        }
                    }
                    tabelaListaMusicas.setModel(listaMusicas);
        }

        public void atualizarTabelaMusicas1 () {

        DefaultTableModel listaMusicas = new DefaultTableModel();
            listaMusicas.addColumn("TÍTULO");
            listaMusicas.addColumn("DATA");
            listaMusicas.addColumn("DURACAO");
            listaMusicas.addColumn("GENERO");
            listaMusicas.addColumn("ESTADO");
            listaMusicas.addColumn("PREÇO (€)");
            ArrayList<Musica> lista = artista.getMusicas();

        // Adicionar os elementos do ArrayList à tabela
        adicionarElementosTabela(lista, listaMusicas);
        tabelaListaMusicas.setModel(listaMusicas);
        scrollListarMusicas.setViewportView(tabelaListaMusicas);
    }

        private void titulosDasColunasTabela(DefaultTableModel modelo){
            // Cria e adiciona os títulos das colunas
        modelo.addColumn("TÍTULO");
        modelo.addColumn("DATA");
        modelo.addColumn("DURACAO");
        modelo.addColumn("GENERO");
        modelo.addColumn("ESTADO");
        modelo.addColumn("PREÇO (€)");
        modelo.addColumn("RATING");

    }

    private void adicionarElementosTabela(ArrayList<Musica> lista,DefaultTableModel modelo){
        for (Musica musica : lista) {
            if (musica instanceof MusicaPaga) {
                modelo.addRow(new Object[]{musica.getTitulo(),musica.getDataCriacao(), musica.getDuracao(),
                        musica.getGenero(), ((MusicaPaga) musica).tipoEstado(), ((MusicaPaga) musica).getPreco(), musica.getRatingMedia()});
            }else {
                modelo.addRow(new Object[]{musica.getTitulo(),musica.getDataCriacao(), musica.getDuracao(),
                        musica.getGenero(), musica.tipoEstado(), "0" , musica.getRatingMedia()});
            }
        }
    }

}
