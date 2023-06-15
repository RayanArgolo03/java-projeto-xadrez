package tabuleiro;


import xadrez.PecaXadrez;

public class Tabuleiro {

    private Peca[][] pecas;
    private int linhas;
    private int colunas;

    //As peças do tabuleiro se iniciam com linhas x colunas
    public Tabuleiro(int linhas, int colunas) {
        this.pecas = new Peca[linhas][colunas];
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public Peca[][] getPecas() {
        return pecas;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    //Retorna Peça pela linha e Coluna
    public Peca getPeca(int linha, int coluna) {
        return pecas[linha][coluna];
    }

    //Retorna Peça pela posição no tabuleiro
    public Peca getPeca(Posicao posicao) {
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
    
    //Coloca peça de xadrez nas peças do tabuleiro
    public void colocarPeca(PecaXadrez peca, Posicao posicao){
        
        peca.posicao = posicao;
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        
    }

}
