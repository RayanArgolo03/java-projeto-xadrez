package xadrez;

import tabuleiro.Posicao;
import xadrez.exception.XadrezException;

public class PosicaoXadrez {

    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {

        if (coluna < 'a' || coluna > 'h') {
            throw new XadrezException("Erro ao iniciar posição: Coluna inválida!");
        }

        if (linha < 1 || linha > 8) {
            throw new XadrezException("Erro ao iniciar posição: Linha inválida");
        }

        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    @Override
    public String toString() {
        return "(" + coluna + linha + ")";
    }

    protected Posicao paraPosicaoGenerica() {
        return new Posicao(8 - linha, coluna - 'a');
    }

    protected PosicaoXadrez paraPosicaoXadrez(Posicao posicao) {
        return new PosicaoXadrez((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());
    }

}
