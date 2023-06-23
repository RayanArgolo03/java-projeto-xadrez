package xadrez;

import xadrez.pecas.Rei;
import xadrez.pecas.Torre;
import java.util.*;
import java.util.stream.Collectors;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;

public class PartidaXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean check = false;
    private boolean checkMate = false;
    private PecaXadrez vulneravelEnPassant;
    private PecaXadrez promocao;

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    //Inicia um tabuleiro
    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        this.turno = 1;
        this.jogadorAtual = Cor.AZUL;
        iniciarLayout();
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean getCheck() {
        return check;

    }

    public boolean getCheckMate() {
        return checkMate;
    }

    public PecaXadrez getVulneravelEnPassant() {
        return vulneravelEnPassant;
    }

    public PecaXadrez getPromocao() {
        return promocao;
    }

    //Pegar peças de xadrez pro jogo
    public PecaXadrez[][] getPecas() {

        PecaXadrez[][] pecas = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];

        //Downcasting das peças 
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                pecas[i][j] = (PecaXadrez) tabuleiro.getPeca(i, j);
            }
        }

        return pecas;
    }

    //Colocar peça no xadrez
    private void colocarPecaXadrez(char coluna, int linha, PecaXadrez pecaXadrez) {
        tabuleiro.colocarPeca(pecaXadrez, new PosicaoXadrez(coluna, linha).paraPosicaoGenerica());
        pecasNoTabuleiro.add(pecaXadrez);
    }

    //Verificar quem é o oponente
    private Cor oponente(Cor cor) {

        if (cor.equals(Cor.AZUL)) {
            return Cor.VERMELHO;
        } else {
            return Cor.AZUL;
        }
    }

    //Verificar movimentos Rei
    private PecaXadrez rei(Cor cor) {

        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor().equals(cor)).collect(Collectors.toList());

        for (Peca p : lista) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }

        throw new IllegalStateException("Erro ao acessar Rei: Não existe o rei da cor " + cor + " no tabuleiro");
    }

    //Verificar se deu check mate
    private boolean testeCheckMate(Cor cor) {

        if (!testeCheck(cor)) {
            return false;
        }

        List<Peca> pecas = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor().equals(cor)).collect(Collectors.toList());

        for (Peca peca : pecas) {

            boolean matriz[][] = peca.movimentosPossiveis();

            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {

                    if (matriz[i][j]) {

                        Posicao origem = ((PecaXadrez) peca).getPosicaoXadrez().paraPosicaoGenerica();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = mover(origem, destino);
                        boolean testeCheck = testeCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);

                        if (!testeCheck) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    //Verificar possível check
    private boolean testeCheck(Cor cor) {

        Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicaoGenerica();
        List<Peca> pecasOponentes = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor().equals(oponente(cor))).collect(Collectors.toList());

        for (Peca pecaOponente : pecasOponentes) {
            boolean[][] matriz = pecaOponente.movimentosPossiveis();

            if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    //Iniciar Layout
    public void iniciarLayout() {

        colocarPecaXadrez('a', 1, new Torre(Cor.AZUL, this.tabuleiro));
        colocarPecaXadrez('b', 1, new Cavalo(Cor.AZUL, this.tabuleiro));
        colocarPecaXadrez('c', 1, new Bispo(Cor.AZUL, this.tabuleiro));
        colocarPecaXadrez('d', 1, new Rainha(Cor.AZUL, this.tabuleiro));
        colocarPecaXadrez('e', 1, new Rei(Cor.AZUL, this.tabuleiro, this));
        colocarPecaXadrez('f', 1, new Bispo(Cor.AZUL, this.tabuleiro));
        colocarPecaXadrez('g', 1, new Cavalo(Cor.AZUL, this.tabuleiro));
        colocarPecaXadrez('h', 1, new Torre(Cor.AZUL, this.tabuleiro));
        colocarPecaXadrez('a', 2, new Peao(Cor.AZUL, this.tabuleiro, this));
        colocarPecaXadrez('b', 2, new Peao(Cor.AZUL, this.tabuleiro, this));
        colocarPecaXadrez('c', 2, new Peao(Cor.AZUL, this.tabuleiro, this));
        colocarPecaXadrez('d', 2, new Peao(Cor.AZUL, this.tabuleiro, this));
        colocarPecaXadrez('e', 2, new Peao(Cor.AZUL, this.tabuleiro, this));
        colocarPecaXadrez('f', 2, new Peao(Cor.AZUL, this.tabuleiro, this));
        colocarPecaXadrez('g', 2, new Peao(Cor.AZUL, this.tabuleiro, this));
        colocarPecaXadrez('h', 2, new Peao(Cor.AZUL, this.tabuleiro, this));

        colocarPecaXadrez('a', 8, new Torre(Cor.VERMELHO, this.tabuleiro));
        colocarPecaXadrez('b', 8, new Cavalo(Cor.VERMELHO, this.tabuleiro));
        colocarPecaXadrez('c', 8, new Bispo(Cor.VERMELHO, this.tabuleiro));
        colocarPecaXadrez('d', 8, new Rainha(Cor.VERMELHO, this.tabuleiro));
        colocarPecaXadrez('e', 8, new Rei(Cor.VERMELHO, this.tabuleiro, this));
        colocarPecaXadrez('f', 8, new Bispo(Cor.VERMELHO, this.tabuleiro));
        colocarPecaXadrez('g', 8, new Cavalo(Cor.VERMELHO, this.tabuleiro));
        colocarPecaXadrez('h', 8, new Torre(Cor.VERMELHO, this.tabuleiro));
        colocarPecaXadrez('a', 7, new Peao(Cor.VERMELHO, this.tabuleiro, this));
        colocarPecaXadrez('b', 7, new Peao(Cor.VERMELHO, this.tabuleiro, this));
        colocarPecaXadrez('c', 7, new Peao(Cor.VERMELHO, this.tabuleiro, this));
        colocarPecaXadrez('d', 7, new Peao(Cor.VERMELHO, this.tabuleiro, this));
        colocarPecaXadrez('e', 7, new Peao(Cor.VERMELHO, this.tabuleiro, this));
        colocarPecaXadrez('f', 7, new Peao(Cor.VERMELHO, this.tabuleiro, this));
        colocarPecaXadrez('g', 7, new Peao(Cor.VERMELHO, this.tabuleiro, this));
        colocarPecaXadrez('h', 7, new Peao(Cor.VERMELHO, this.tabuleiro, this));
    }

    //Verificar posiçao de inicio
    private void posicaoInicioValida(Posicao px1) {

        if (!tabuleiro.temPeca(px1)) {
            throw new XadrezException("Erro ao executar movimento: Coordenada vazia");

        }

        if (jogadorAtual != ((PecaXadrez) tabuleiro.getPeca(px1)).getCor()) {
            throw new XadrezException("Erro ao executar movimento: Escolha algo que pertence a ti!");
        }

        if (!tabuleiro.getPeca(px1).existeMovimentoPossivel()) {
            throw new XadrezException("Erro ao executar movimento: Sem movimentos!");
        }

    }

    private void posicaoFinalValida(Posicao px1, Posicao px2) {

        if (!getTabuleiro().getPeca(px1).movimentoPossivel(px2)) {
            throw new XadrezException("Erro ao executar movimento: Movimento impossivel!");
        }

    }

    //Pular turno
    private void proximoTurno() {
        this.turno++;

        jogadorAtual = (jogadorAtual == Cor.AZUL) ? Cor.VERMELHO : Cor.AZUL;
    }

    //Movimentos possiveis na partida
    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoXadrez) {

        Posicao posicao = posicaoXadrez.paraPosicaoGenerica();
        posicaoInicioValida(posicao);

        return tabuleiro.getPeca(posicao).movimentosPossiveis();
    }

    //Mover peça
    private Peca mover(Posicao origem, Posicao destino) {

        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        p.incrementarMovimento();
        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(p, destino);

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        //Movimento Roque - Rei
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
            tabuleiro.colocarPeca(torre, destinoTorre);
            torre.incrementarMovimento();
        }

        //Movimento Roque - Rainha
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
            tabuleiro.colocarPeca(torre, destinoTorre);
            torre.incrementarMovimento();
        }

        //Movimento En Passant
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {

                Posicao posicaoPeao;
                if (p.getCor() == Cor.AZUL) {
                    posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
                } else {
                    posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }

                pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
                pecasCapturadas.add(pecaCapturada);
                pecasNoTabuleiro.remove(pecaCapturada);
            }
        }

        return pecaCapturada;
    }

    //Desfazer movimento
    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {

        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
        p.decrementarMovimento();
        tabuleiro.colocarPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.colocarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }

        //Movimento Roque - Rei
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
            tabuleiro.colocarPeca(torre, origem);
            torre.decrementarMovimento();
        }

        //Movimento Roque - Rainha
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
            tabuleiro.colocarPeca(torre, origemTorre);
            torre.decrementarMovimento();
        }

        //Movimento En Passant
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == vulneravelEnPassant) {

                PecaXadrez peao = (PecaXadrez) tabuleiro.removerPeca(destino);
                Posicao posicaoPeao;
                if (p.getCor() == Cor.AZUL) {
                    posicaoPeao = new Posicao(3, destino.getColuna());
                } else {
                    posicaoPeao = new Posicao(4, destino.getColuna());
                }

                tabuleiro.colocarPeca(peao, posicaoPeao);
            }
        }
    }

    //Executar movimento
    public PecaXadrez executarMovimento(PosicaoXadrez origem, PosicaoXadrez destino) {

        Posicao origemPosicao = origem.paraPosicaoGenerica();
        Posicao destinoPosicao = destino.paraPosicaoGenerica();

        posicaoInicioValida(origemPosicao);
        posicaoFinalValida(origemPosicao, destinoPosicao);

        Peca pecaCapturada = mover(origemPosicao, destinoPosicao);

        if (testeCheck(jogadorAtual)) {
            desfazerMovimento(origemPosicao, destinoPosicao, pecaCapturada);
            throw new XadrezException("Erro ao mover: Se colocou em Check!");
        }

        PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.getPeca(destinoPosicao);

        //Movimento Promoção
        promocao = null;
        if (pecaMovida instanceof Peao) {

            if ((pecaMovida.getCor() == Cor.AZUL && destinoPosicao.getLinha() == 0) || pecaMovida.getCor() == Cor.VERMELHO && destinoPosicao.getLinha() == 7) {
                promocao = (PecaXadrez) tabuleiro.getPeca(destinoPosicao);
                promocao = trocarPecaPromovida("Q");
            }

        }

        check = (testeCheck(oponente(jogadorAtual)));

        if (testeCheckMate(oponente(jogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }

        //Movimento Especial - En Passant
        if (pecaMovida instanceof Peao && (destinoPosicao.getLinha() == origemPosicao.getLinha() - 2 || destinoPosicao.getLinha() == origemPosicao.getLinha() + 2)) {
            vulneravelEnPassant = pecaMovida;
        } else {
            vulneravelEnPassant = null;
        }

        return (PecaXadrez) pecaCapturada;
    }

    //Trocar peça promovida por escolhida
    public PecaXadrez trocarPecaPromovida(String tipo) {

        if (promocao == null) {
            throw new IllegalStateException("Erro ao executar jogada promotion: Sem possibilidade disso!");

        }
        if (!tipo.equalsIgnoreCase("B") && !tipo.equalsIgnoreCase("C") && !tipo.equalsIgnoreCase("T") && !tipo.equalsIgnoreCase("Q")) {
            return promocao;
        }

        Posicao posicao = promocao.getPosicaoXadrez().paraPosicaoGenerica();
        Peca p = tabuleiro.removerPeca(posicao);
        pecasNoTabuleiro.remove(p);

        PecaXadrez novaPeca = novaPeca(tipo, promocao.getCor());
        tabuleiro.colocarPeca(novaPeca, posicao);

        pecasNoTabuleiro.add(novaPeca);
        return novaPeca;

    }

    //Verificar qual peça será gerada
    private PecaXadrez novaPeca(String tipo, Cor cor) {

        if (tipo.equals("B")) {
            return new Bispo(cor, tabuleiro);
        }

        if (tipo.equals("C")) {
            return new Cavalo(cor, tabuleiro);
        }

        if (tipo.equals("Q")) {
            return new Rainha(cor, tabuleiro);
        }

        return new Torre(cor, tabuleiro);

    }

}
