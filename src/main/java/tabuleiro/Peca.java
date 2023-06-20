package tabuleiro;

import xadrez.Cor;

public abstract class Peca {
    
    protected Posicao posicao;
    protected Tabuleiro tabuleiro;

    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public abstract boolean[][] movimentosPossiveis();

    public boolean movimentoPossivel(Posicao posicao) {

        return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];

    }

    public boolean existeMovimentoPossivel() {
      
        boolean[][] movimentos = movimentosPossiveis();

        for (int i = 0; i < movimentos.length; i++) {
            for (int j = 0; j < movimentos[i].length; j++) {
                
                if (movimentos[i][j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
