package xadrez;

import tabuleiro.Peca;


public class PecaXadrez extends Peca {

    private Cor cor;

    public PecaXadrez(Cor cor) {
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

}
