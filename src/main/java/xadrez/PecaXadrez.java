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

    //Verifica se há peça oponente na posição
    protected boolean temPecaOponente(Posicao posicao) {

        PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
        return p != null && p.getCor() != cor;
    }
    
    //Incrementa movimento da peça
    protected void incrementarMovimento(){
        countMovimento++;
    }
    
    //Decrementa movimento da peça
    protected void decrementarMovimento(){
        countMovimento--;
    }
}
