package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao) {

        PecaXadrez px = (PecaXadrez) tabuleiro.getPeca(posicao);

        return px == null || px.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {

        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Em cima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A baixo
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A direita
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A noroeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A nordeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A sudoeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A sudeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        return matriz;
    }

}
