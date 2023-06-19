package xadrez;


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
    
    
    private void colocarPecaXadrez(char coluna, int linha, PecaXadrez pecaXadrez){
        tabuleiro.colocarPeca(pecaXadrez, new PosicaoXadrez(coluna, linha).paraPosicaoGenerica());
    }
    
    //Iniciar Layout
    public void iniciarLayout(){
        colocarPecaXadrez('b', 6, new Torre(Cor.PRETO));
        colocarPecaXadrez('b', 5, new Rei(Cor.BRANCO));
    }

}
