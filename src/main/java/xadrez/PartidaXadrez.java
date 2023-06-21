package xadrez;

import java.util.*;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.exception.XadrezException;

public class PartidaXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;

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

    private void colocarPecaXadrez(char coluna, int linha, PecaXadrez pecaXadrez) {
        tabuleiro.colocarPeca(pecaXadrez, new PosicaoXadrez(coluna, linha).paraPosicaoGenerica());
        pecasNoTabuleiro.add(pecaXadrez);
    }

    //Iniciar Layout
    public void iniciarLayout() {
        colocarPecaXadrez('b', 6, new Torre(Cor.VERMELHO, this.tabuleiro));
        colocarPecaXadrez('b', 5, new Rei(Cor.AZUL, this.tabuleiro));
        colocarPecaXadrez('b', 4, new Rei(Cor.AZUL, this.tabuleiro));
    }

    private void posicaoInicioValida(Posicao px1) {

        if (!tabuleiro.temPeca(px1)) {
            throw new XadrezException("Erro ao executar movimento: Sem piece nas coordenadas");

        }

        if (jogadorAtual != ((PecaXadrez) tabuleiro.getPeca(px1)).getCor()) {
            throw new XadrezException("Erro ao executar movimento: A piece escolhida não é sua!");
        }

        if (!tabuleiro.getPeca(px1).existeMovimentoPossivel()) {
            throw new XadrezException("Erro ao executar movimento: Não existe movimento possível para a piece escolhida!");
        }

    }

    private void posicaoFinalValida(Posicao px1, Posicao px2) {

        if (!getTabuleiro().getPeca(px1).movimentoPossivel(px2)) {
            throw new XadrezException("Erro ao executar movimento: A piece escolhida não pode se mover para a coordenada de destino!");
        }

    }

    private void proximoTurno() {
        this.turno++;

        jogadorAtual = (jogadorAtual == Cor.AZUL) ? Cor.VERMELHO : Cor.AZUL;
    }

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoXadrez) {

        Posicao posicao = posicaoXadrez.paraPosicaoGenerica();
        posicaoInicioValida(posicao);

        return tabuleiro.getPeca(posicao).movimentosPossiveis();
    }

    private Peca mover(Posicao px1, Posicao px2) {

        Peca p = tabuleiro.removerPeca(px1);
        Peca pecaCapturada = tabuleiro.removerPeca(px2);

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        tabuleiro.colocarPeca(p, px2);

        return pecaCapturada;
    }

    public PecaXadrez executarMovimento(PosicaoXadrez atual, PosicaoXadrez destino) {

        Posicao px1 = atual.paraPosicaoGenerica();
        Posicao px2 = destino.paraPosicaoGenerica();

        posicaoInicioValida(px1);
        posicaoFinalValida(px1, px2);

        Peca pecaCapturada = mover(px1, px2);

        proximoTurno();
        return (PecaXadrez) pecaCapturada;
    }

}
