package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] movimentosPossiveis() {

        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Andar para cima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());

        while ( getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() -1 );

        }

        if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

        }

        //Para esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);

        while (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);

        }

        if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

        }

        //Para direita
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);

        while ( getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);

        }

        if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

        }

        //Para baixo
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());

        while (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);

        }

        if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

        }

        return matriz;
    }

}
