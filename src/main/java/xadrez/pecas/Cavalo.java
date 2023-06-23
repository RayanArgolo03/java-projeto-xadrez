package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

    public Cavalo(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    //Verifica se pode mover o cavalo
    private boolean podeMover(Posicao posicao) {

        PecaXadrez px = (PecaXadrez) tabuleiro.getPeca(posicao);

        return px == null || px.getCor() != getCor();
    }
    
    //Lógica de movimentos do Cabalo
    @Override
    public boolean[][] movimentosPossiveis() {

        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        return matriz;
    }

    @Override
    public String toString() {
        return "C";
    }

}
