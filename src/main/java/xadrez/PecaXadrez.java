package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca {

    private Cor cor;
    private int countMovimento;
    
    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
    
    public int getCountMovimento() {
        return countMovimento;
    }

    public PosicaoXadrez getPosicaoXadrez() {
        return PosicaoXadrez.paraPosicaoXadrez(posicao);
    }

    protected boolean temPecaOponente(Posicao posicao) {

        PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
        return p != null && p.getCor() != cor;
    }
    
    
    protected void incrementarMovimento(){
        countMovimento++;
    }
    
    protected void decrementarMovimento(){
        countMovimento--;
    }
}
