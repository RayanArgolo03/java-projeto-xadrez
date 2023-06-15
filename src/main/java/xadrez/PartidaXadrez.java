package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    //Inicia um tabuleiro
    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        iniciarLayout();
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    
    
    //Pegar peças de xadrez pro jogo
    public PecaXadrez[][] getPecas(){
        
        PecaXadrez[][] pecas = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        
        //Downcasting das peças 
        
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                pecas[i][j] = (PecaXadrez) tabuleiro.getPeca(i, j);
            }
        }
        
        return pecas;
    }
    
    
    //Iniciar Layout
    public void iniciarLayout(){
        tabuleiro.colocarPeca(new Rei(Cor.BRANCO), new Posicao(2,1));
    }

}
