package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.exception.XadrezException;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    //Inicia um tabuleiro
    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        iniciarLayout();
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
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
    }

    //Iniciar Layout
    public void iniciarLayout() {
        colocarPecaXadrez('b', 6, new Torre(Cor.PRETO));
        colocarPecaXadrez('b', 5, new Rei(Cor.BRANCO));
        colocarPecaXadrez('b', 4, new Rei(Cor.BRANCO));
    }

    private void posicaoInicioValida(Posicao px1) {

        if (!tabuleiro.temPeca(px1)) {
            throw new XadrezException("Erro ao executar movimento: Não existe peça nessa posição!");
        }

    }

    private Peca mover(Posicao px1, Posicao px2) {

        Peca p = tabuleiro.removerPeca(px1);
        Peca capturada = tabuleiro.removerPeca(px2);
        
        tabuleiro.colocarPeca(p, px2);
        
        return capturada;
    }

    public PecaXadrez executarMovimento(PosicaoXadrez atual, PosicaoXadrez destino) {

        Posicao px1 = atual.paraPosicaoGenerica();
        Posicao px2 = destino.paraPosicaoGenerica();

        Peca pecaCapturada = mover(px1, px2);
        return (PecaXadrez) pecaCapturada;
    }

}
