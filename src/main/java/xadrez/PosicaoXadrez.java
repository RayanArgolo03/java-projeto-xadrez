package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {

    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {

        if (coluna < 'a' || coluna > 'h') {
            throw new XadrezException("Erro ao iniciar coordenadas: Coluna de A a H!");
        }

        if (linha < 1 || linha > 8) {
            throw new XadrezException("Erro ao iniciar coordenadass: Linha de 1 a 8!");
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

    //Converte para posição genérica
    protected  Posicao paraPosicaoGenerica() {
        return new Posicao(8 - linha, coluna - 'a');
    }

    //Converte para posição xadrez
    protected static PosicaoXadrez paraPosicaoXadrez(Posicao posicao) {
        return new PosicaoXadrez((char) ('a' + posicao.getColuna()), 8 - posicao.getLinha());
    }

}
