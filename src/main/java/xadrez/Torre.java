package xadrez;

import tabuleiro.Tabuleiro;

public class Torre extends PecaXadrez {

    public Torre(Cor cor) {
        super(cor);
    }

    @Override
    public String toString() {
        return "T";
    }

}
