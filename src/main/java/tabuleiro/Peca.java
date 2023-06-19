package tabuleiro;

import xadrez.Cor;

public class Peca {

    private Cor cor;
    protected Posicao posicao;
    
    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    public Cor getCor() {
        return cor;
    }

    
    
}
