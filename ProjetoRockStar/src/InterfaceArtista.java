import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class InterfaceArtista implements Serializable {

        private JFrame janelaArtista;
        private JPanel painelTitulo, painelPesquisar, painelMenu, painelMusicas, painelEstatisticas, painelAdicionarMusica,
                painelEditarDados, painelAlbum;
        private JLabel titulo, pesquisa, pesquisaAlbum, ordenarMusicas, tituloMusica, ano, duracao, genero, custo, estado, username,
                pesquisaTitulo, alterarTitulo, alterarPreco, alterarEstado, nomeAlbum, generoAlbum, anoAlbum,
                totalUtilizadores, totalMusicas, valorTotalColecao, valorTotalVendas, musicaMaisGravada, musicaMaisComprada,
                totalAlbumGenero ;
        private JButton botaoPesquisar, botaoMusicas, botaoEstatisticas, criarMusica, editarDados, adicionarMusica,
                pesquisarMusica, guardarAlteracao, listarMusicas, menuAlbum, criarAlbum, criarListaMusicas, adicionarMusicaAlbum;
        private JTextField caixaTituloMusica, caixaAno, caixaDuracao, caixaGenero, caixaCusto, inserirTitulo,
                caixaAlterarTitulo, caixaAltearPreco, caixaNomeAlbum, caixaGeneroAlgum, caixaAnoAlbum, caixaTextoAdicionarMusicaAlbum,
                caixaTotalUtilizadores, caixaTotalMusicas, caixaValorTotalColecao, caixaValorTotalVendas, caixaMusicaMaisGravada,
                caixaMusicaMaisComprada, campoPesquisa;
        private JRadioButton botaoAscendente, botaoDescendente, estadoAtivo, estadoInativo, estadoAtivo1, estadoInativo1;
        private JComboBox caixaPesquisarMusica, caixaPesquisarALbum, ordenarPor;
        private JTextArea areaPesquisa, listaMusicasAlbum, listaAlbumGenero;
        private ButtonGroup botaoEstado, botaoEstado1;
        private JTable tabelaListaMusicas, listaAlbumGenero1;

        public InterfaceArtista(){
            // criar janela
            janelaArtista = new JFrame();

            //Criar Painel Pesquisar -------------------------------------------------
            painelPesquisar = new JPanel();
            painelPesquisar.setBackground(new Color(225,145,102));
            painelPesquisar.setBounds(340,200,780,500);
            painelPesquisar.setLayout(null);

            //Criar Componentes do Painel Pesquisar

            //JLabel
            pesquisa = new JLabel("PESQUISAR MÚSICA POR: ");
            pesquisa.setBounds(250, 10, 150, 40);
            pesquisaAlbum = new JLabel("PESQUISAR ÁLBUM POR:");
            pesquisaAlbum.setBounds(250, 10, 150, 40);
            pesquisaAlbum.setVisible(false);
            ordenarMusicas = new JLabel("ORDENAR POR:");
            ordenarMusicas.setBounds(450,10,150,40);

            //JButton
            JButton okPesquisa = new JButton("OK");
            okPesquisa.setBounds(710,450,60,40);

            //JComboBox
            caixaPesquisarMusica = new JComboBox<>();
            caixaPesquisarMusica.addItem("TÍTULO");
            caixaPesquisarMusica.addItem("ÁLBUM");
            caixaPesquisarMusica.setBounds(250,60, 150,40);
            caixaPesquisarALbum = new JComboBox<>();
            caixaPesquisarALbum.addItem("TÍTULO");
            caixaPesquisarALbum.addItem("GÉNERO");
            caixaPesquisarALbum.setBounds(250, 60, 150, 40);
            caixaPesquisarALbum.setVisible(false);
            ordenarPor = new JComboBox<>();
            ordenarPor.setBounds(450, 60, 150, 40);
            ordenarPor.addItem("TÍTULO");
            ordenarPor.addItem("DURAÇÃO");

            //JTable
            tabelaListaMusicas = new JTable();
            tabelaListaMusicas.setBounds(50,200,800,200);
            JScrollPane scrollPane = new JScrollPane(tabelaListaMusicas);

            //JRadioButton
            botaoAscendente = new JRadioButton("Ascendente");
            botaoAscendente.setBounds(650, 30, 100, 20);
            botaoAscendente.setBackground(null);
            botaoAscendente.setSelected(true);
            botaoDescendente = new JRadioButton("Descendente");
            botaoDescendente.setBounds(650,60,100,20);
            botaoDescendente.setBackground(null);
            JRadioButton selectMusica = new JRadioButton("Pesquisar Música");
            selectMusica.setBounds(50, 30, 200, 20);
            selectMusica.setBackground(null);
            selectMusica.setSelected(true);
            selectMusica.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    caixaPesquisarALbum.setVisible(!selectMusica.isSelected());
                    pesquisaAlbum.setVisible(!selectMusica.isSelected());
                    caixaPesquisarMusica.setVisible(selectMusica.isSelected());
                    pesquisa.setVisible(selectMusica.isSelected());
                }
            });
            JRadioButton selectAlbum = new JRadioButton("Pesquisar Álbum");
            selectAlbum.setBounds(50, 60, 200, 20);
            selectAlbum.setBackground(null);
            selectAlbum.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    caixaPesquisarMusica.setVisible(!selectAlbum.isSelected());
                    pesquisa.setVisible(!selectAlbum.isSelected());
                    caixaPesquisarALbum.setVisible(selectAlbum.isSelected());
                    pesquisaAlbum.setVisible(selectAlbum.isSelected());
                }
            });

            //ButtonGroup
            botaoEstado = new ButtonGroup();
            botaoEstado.add(botaoAscendente);
            botaoEstado.add(botaoDescendente);
            ButtonGroup botaoPesquisaMouA = new ButtonGroup();
            botaoPesquisaMouA.add(selectAlbum);
            botaoPesquisaMouA.add(selectMusica);

            campoPesquisa = new JTextField("PESQUISA");
            campoPesquisa.setBounds(50,120,150,40);


            //Adicionar Componentes ao Painel Pesquisar
            painelPesquisar.add(caixaPesquisarMusica);
            painelPesquisar.add(pesquisa);
            painelPesquisar.add(pesquisaAlbum);
            painelPesquisar.add(caixaPesquisarALbum);
            painelPesquisar.add(ordenarMusicas);
            painelPesquisar.add(ordenarPor);
            painelPesquisar.add(botaoDescendente);
            painelPesquisar.add(botaoAscendente);
            painelPesquisar.add(campoPesquisa);
            painelPesquisar.add(tabelaListaMusicas);
            painelPesquisar.add(scrollPane, BorderLayout.CENTER);
            painelPesquisar.add(okPesquisa);
            painelPesquisar.add(selectMusica);
            painelPesquisar.add(selectAlbum);



            //Criar Painel Musicas

            painelMusicas = new JPanel();
            painelMusicas.setBackground(new Color(225,145,102));
            painelMusicas.setBounds(340,200,780,500);
            painelMusicas.setLayout(null);

            //Criar componentes do Painel Musicas

            criarMusica = new JButton("CRIAR MÚSICA"); criarMusica.setBounds(50,20,180, 40);
            editarDados = new JButton("EDITAR DADOS MUSICA"); editarDados.setBounds(250, 20, 180,40);
            menuAlbum = new JButton("ÁLBUM"); menuAlbum.setBounds(450,20,180,40);


            //Adicionar componentes ao painel Musicas

            painelMusicas.add(criarMusica);
            painelMusicas.add(editarDados);
            painelMusicas.add(menuAlbum);


            //Criar painel Adicionar Musicas
            painelAdicionarMusica = new JPanel();
            painelAdicionarMusica.setBackground(new Color(225,145,102));
            painelAdicionarMusica.setBounds(340,200,780,500);
            painelAdicionarMusica.setLayout(null);

            //Criar Componentes ao painel

            tituloMusica = new JLabel("TÍTULO");
            tituloMusica.setBounds(200, 50, 180,40);
            ano = new JLabel("DATA");
            ano.setBounds(200, 100, 180,40);
            duracao = new JLabel("DURAÇÃO");
            duracao.setBounds(200, 150, 180, 40);
            genero = new JLabel("GENERO");
            genero.setBounds(200, 200, 180,40);
            custo = new JLabel("PREÇO");
            custo.setBounds(200, 250,180,40);
            estado = new JLabel("ESTADO");
            estado.setBounds(200,300,180,40);
            caixaTituloMusica = new JTextField();
            caixaTituloMusica.setBounds(400, 50, 180, 40);
            caixaAno = new JTextField("Ano");
            caixaAno.setBounds(400, 100, 80,40);
            JTextField caixaMes=new JTextField("Mês");
            caixaMes.setBounds(490,100,40,40);
            JTextField caixaDia=new JTextField("Dia");
            caixaDia.setBounds(540,100,40,40);
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
                    /*String titulo=;
                    int ano=;
                    int mes=;
                    int dia=;
                    double duracao=;
                    String genero=;
                    boolean estado=;
                    double preco=;*/


                }
            });

            ButtonGroup ativoOuInativo = new ButtonGroup();
            ativoOuInativo.add(estadoAtivo);
            ativoOuInativo.add(estadoInativo);

            //Adicionar componentes ao painel Adicionar Musicas
            painelAdicionarMusica.add(tituloMusica);
            painelAdicionarMusica.add(ano);
            painelAdicionarMusica.add(duracao);
            painelAdicionarMusica.add(genero);
            painelAdicionarMusica.add(custo);
            painelAdicionarMusica.add(estado);
            painelAdicionarMusica.add(caixaTituloMusica);
            painelAdicionarMusica.add(caixaAno);
            painelAdicionarMusica.add(caixaMes);
            painelAdicionarMusica.add(caixaDia);
            painelAdicionarMusica.add(caixaDuracao);
            painelAdicionarMusica.add(caixaGenero);
            painelAdicionarMusica.add(caixaCusto);
            painelAdicionarMusica.add(estadoAtivo);
            painelAdicionarMusica.add(estadoInativo);
            painelAdicionarMusica.add(adicionarMusica);

            //Criar Painel Editar Dados
            painelEditarDados = new JPanel();
            painelEditarDados.setBackground(new Color(225,145,102));
            painelEditarDados.setBounds(340,200,780,500);
            painelEditarDados.setLayout(null);

            //Criar componentes do painel Editar Dados
            pesquisaTitulo = new JLabel("PESQUISA MÚSICA POR TITULO");
            pesquisaTitulo.setBounds(50,10,200,40);
            inserirTitulo = new JTextField("INSERIR TÍTULO");
            inserirTitulo.setBounds(250,10,180,40);
            pesquisarMusica = new JButton("PESQUISAR");
            pesquisarMusica.setBounds(430,10,180,40);
            areaPesquisa =new JTextArea("RESULTADOS DA PESQUISA");
            areaPesquisa.setBounds(50,60, 400,400);
            alterarTitulo = new JLabel("NOVO TÍTULO");
            alterarTitulo.setBounds(500, 60, 180, 40);
            caixaAlterarTitulo = new JTextField(); caixaAlterarTitulo.setBounds(500,100,180,40);
            alterarPreco =  new JLabel("NOVO PREÇO"); alterarPreco.setBounds(500,150,180,40);
            caixaAltearPreco = new JTextField(); caixaAltearPreco.setBounds(500,200,180,40);
            alterarEstado = new JLabel("NOVO ESTADO"); alterarEstado.setBounds(500,250,180,40);
            estadoAtivo1 = new JRadioButton("ATIVO"); estadoAtivo1.setBackground(null);
            estadoAtivo1.setBounds(500, 300, 100,40);
            estadoInativo1 = new JRadioButton("INATIVO"); estadoInativo1.setBackground(null);
            estadoInativo1.setBounds(600,300,100,40 );
            botaoEstado1 = new ButtonGroup(); botaoEstado1.add(estadoAtivo1); botaoEstado1.add(estadoInativo1);
            guardarAlteracao = new JButton("GUARDAR ALTERAÇÕES");
            guardarAlteracao.setBounds(500,350,180,40);
            listarMusicas = new JButton("LISTAR MÚSICAS"); listarMusicas.setBounds(610,10,180,40);


            //Adicionar componentes ao painel
            painelEditarDados.add(pesquisaTitulo);painelEditarDados.add(inserirTitulo);painelEditarDados.add(pesquisarMusica);
            painelEditarDados.add(areaPesquisa);painelEditarDados.add(alterarTitulo);
            painelEditarDados.add(alterarTitulo); painelEditarDados.add(caixaAlterarTitulo);
            painelEditarDados.add(alterarPreco); painelEditarDados.add(caixaAltearPreco);
            painelEditarDados.add(estadoAtivo1); painelEditarDados.add(estadoInativo1);
            painelEditarDados.add(alterarEstado); painelEditarDados.add(guardarAlteracao);
            painelEditarDados.add(listarMusicas);

            // Criar Painel Criar Album
            painelAlbum = new JPanel();
            painelAlbum.setBackground(new Color(225,145,102));
            painelAlbum.setBounds(340,200,780,500);
            painelAlbum.setLayout(null);

            //Criar componenentes do Painel
            criarAlbum = new JButton("CRIAR ÁLBUM");
            criarAlbum.setBounds(50,20,180,40);
            nomeAlbum = new JLabel("NOME DO ÁLBUM");
            nomeAlbum.setBounds(50, 100,180,40);
            caixaNomeAlbum = new JTextField();
            caixaNomeAlbum.setBounds(50, 150, 180,40);
            generoAlbum = new JLabel("GÉNERO");
            generoAlbum.setBounds(50,200,180,40);
            caixaGeneroAlgum = new JTextField();
            caixaGeneroAlgum.setBounds(50,250,180,40);
            anoAlbum = new JLabel("ANO LANÇAMENTO");
            anoAlbum.setBounds(50, 300,180,40);
            caixaAnoAlbum = new JTextField();
            caixaAnoAlbum.setBounds(50, 350,180,40);
            criarListaMusicas = new JButton("LISTAR MÚSICAS");
            criarListaMusicas.setBounds(300, 20, 200, 30);

            listaMusicasAlbum = new JTextArea(); listaMusicasAlbum.setBounds(300, 80, 200,300);
            adicionarMusicaAlbum = new JButton("ADICIONAR MUSICA ÁLBUM");
            adicionarMusicaAlbum.setBounds(550, 80, 220,40);
            caixaTextoAdicionarMusicaAlbum = new JTextField("Nome do Álbum");
            caixaTextoAdicionarMusicaAlbum.setBounds(550,20,220,30);

            //Adicionar componentes ao Painel
            painelAlbum.add(nomeAlbum); painelAlbum.add(caixaNomeAlbum);
            painelAlbum.add(generoAlbum); painelAlbum.add(caixaGeneroAlgum);
            painelAlbum.add(anoAlbum); painelAlbum.add(caixaAnoAlbum);
            painelAlbum.add(criarAlbum); painelAlbum.add(criarListaMusicas);
            painelAlbum.add(listaMusicasAlbum); painelAlbum.add(caixaTextoAdicionarMusicaAlbum);
            painelAlbum.add(adicionarMusicaAlbum);


            //Criar painel Estatisticas-------------------------------------------------------
            painelEstatisticas = new JPanel();
            painelEstatisticas.setBackground(new Color(225,145,102));
            painelEstatisticas.setBounds(340,200,780,500);
            painelEstatisticas.setLayout(null);

            //Criar componentes do painel Estatísticas
            totalUtilizadores = new JLabel("TOTAL UTILIZADORES");
            totalUtilizadores.setBounds(50,50,180,30);
            caixaTotalUtilizadores = new JTextField(); caixaTotalUtilizadores.setBounds(250,50,180,30);
            totalMusicas = new JLabel("TOTAL MUSICAS");
            totalMusicas.setBounds(50,100,180,30);
            caixaTotalMusicas = new JTextField(); caixaTotalMusicas.setBounds(250,100,180,30);
            valorTotalColecao = new JLabel("VALOR TOTAL COLEÇÃO");
            valorTotalColecao.setBounds(50,150,180,30);
            caixaValorTotalColecao = new JTextField(); caixaValorTotalColecao.setBounds(250,150,180,30);
            valorTotalVendas = new JLabel("VALOR TOTAL DE VENDAS");
            valorTotalVendas.setBounds(50,200,180,30);
            caixaValorTotalVendas = new JTextField(); caixaValorTotalVendas.setBounds(250,200,180,30);
            caixaValorTotalVendas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    caixaValorTotalVendas.setText(String.valueOf(valorTotalVendas));
                }
            });
            musicaMaisGravada = new JLabel("MUSICA MAIS GRAVADA");
            musicaMaisGravada.setBounds(50,250,180,30);
            caixaMusicaMaisGravada = new JTextField(); caixaMusicaMaisGravada.setBounds(250,250,180,30);
            musicaMaisComprada = new JLabel("MUSICA MAIS COMPRADA");
            musicaMaisComprada.setBounds(50,300,180,30);
            caixaMusicaMaisComprada = new JTextField(); caixaMusicaMaisComprada.setBounds(250,300,180,30);
            totalAlbumGenero = new JLabel("TOTAL ÁLBUNS POR GÉNERO");
            totalAlbumGenero.setBounds(500, 50, 180,30);
           Artista va = new Artista();
            va.criarAlbum("Rock1", "Rock");
            va.criarAlbum("Rock2", "Pimba");
            va.criarAlbum("Rock2", "Pimba");
            va.criarAlbum("Rock3", "Jazz");
            String [][] dados = va.matrizTotalAlbuns();
            // Criar a tabela com os dados e colunas
            String [] colunas = {"Género", "Total álbuns"};
            listaAlbumGenero1 = new JTable(dados, colunas);
            listaAlbumGenero1.setBounds(500,100, 250,230);
            JScrollPane tabela1 = new JScrollPane(listaAlbumGenero);




            //Adicionar componentes do painel Estatísticas
            painelEstatisticas.add(totalUtilizadores);painelEstatisticas.add(totalMusicas);
            painelEstatisticas.add(valorTotalColecao);painelEstatisticas.add(valorTotalVendas);
            painelEstatisticas.add(musicaMaisGravada); painelEstatisticas.add(musicaMaisComprada);
            painelEstatisticas.add(caixaTotalUtilizadores); painelEstatisticas.add(caixaTotalMusicas);
            painelEstatisticas.add(caixaValorTotalColecao); painelEstatisticas.add(caixaValorTotalVendas);
            painelEstatisticas.add(caixaMusicaMaisGravada); painelEstatisticas.add(caixaMusicaMaisComprada);
            painelEstatisticas.add(totalAlbumGenero); painelEstatisticas.add(listaAlbumGenero);
            painelEstatisticas.add(tabela1);

            //Criar painel fixo Titulo  ----------------------------------------------
            painelTitulo = new JPanel();
            painelTitulo.setBackground(new Color(225,145,102));
            painelTitulo.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
            titulo = new JLabel("ROCKSTAR.INC");
            titulo.setFont(new Font("Magneto", Font.BOLD, 80));
            painelTitulo.add(titulo);

            //Criar Painel Fixo Menu  ----------------------------------------------------
            painelMenu = new JPanel();
            painelMenu.setBackground(new Color(225,145,102));
            painelMenu.setLayout(null);

            //Criar componentes do painel Menu Fixo--------------------------------------

            botaoPesquisar = new JButton("PESQUISAR");
            botaoPesquisar.setBounds(70,100, 250,100);
            botaoPesquisar.setFont(new Font("Arial", Font.BOLD, 20));
            botaoMusicas = new JButton("MÚSICAS");
            botaoMusicas.setBounds(70,250,250,100);
            botaoMusicas.setFont(new Font("Arial", Font.BOLD, 20));
            botaoEstatisticas = new JButton("ESTATÍSTICAS");
            botaoEstatisticas.setBounds(70,400, 250,100);
            botaoEstatisticas.setFont(new Font("Arial", Font.BOLD, 20));
            username = new JLabel("USERNAME");
            username.setBounds(70, 70, 250, 20);

            //Adicional componentes ao painel
            painelMenu.add(botaoPesquisar);
            painelMenu.add(botaoMusicas);
            painelMenu.add(botaoEstatisticas);
            painelMenu.add(username);


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


            //Caracteristicas janela
            janelaArtista.setSize(1200, 800);
            janelaArtista.setLocation(100, 100);
            janelaArtista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janelaArtista.setLocationRelativeTo(null);
            janelaArtista.setResizable(false);
            janelaArtista.setVisible(true);
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

}
