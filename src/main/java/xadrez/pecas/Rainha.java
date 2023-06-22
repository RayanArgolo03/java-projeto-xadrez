package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

    public Rainha(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public boolean[][] movimentosPossiveis() {

        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Andar para cima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());

        while (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);

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

        while (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

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

        //Noroeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);

        while (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

            p.setValores(p.getLinha() - 1, p.getColuna() - 1);

        }

        if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

        }

        //Nordeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);

        while (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() - 1, p.getColuna() + 1);

        }

        if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

        }

        //Sudeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);

        while (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() + 1, p.getColuna() + 1);

        }

        if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

        }

        //Sudoeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);

        while (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() + 1, p.getColuna() - 1);

        }

        if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {

            matriz[p.getLinha()][p.getColuna()] = true;

        }

        return matriz;
    }

    @Override
    public String toString() {
        return "Q";
    }

}
