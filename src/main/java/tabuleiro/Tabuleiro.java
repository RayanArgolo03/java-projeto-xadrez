package tabuleiro;

import tabuleiro.exceptions.TabuleiroException;


public class Tabuleiro {

    private Peca[][] pecas;
    private int linhas;
    private int colunas;

    
    public Tabuleiro(int linhas, int colunas) {
        
        if (linhas < 1){
            throw new TabuleiroException("Erro ao criar tabuleiro: Quantidade de linhas inválida!");
        }
        
        if (colunas < 1){
            throw new TabuleiroException("Erro ao criar tabuleiro: Quantidade de colunas inválida!");
        }
        
        this.pecas = new Peca[linhas][colunas];
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    //Retorna Peça pela linha e Coluna
    public Peca getPeca(int linha, int coluna) {

        if (!posicaoValida(linha, coluna)) {
            throw new TabuleiroException("Erro ao acessar peça: Posição inválida");
        }

        return pecas[linha][coluna];
    }

    //Retorna Peça pela posição no tabuleiro
    public Peca getPeca(Posicao posicao) {

        if (!posicaoValida(posicao)) {
            throw new TabuleiroException("Erro ao acessar peça: Posição inválida!");
        }

        return pecas[posicao.getLinha()][posicao.getColuna()];
    }

    //Coloca peça de xadrez nas peças do tabuleiro
    public void colocarPeca(Peca peca, Posicao posicao) {

        if (temPeca(posicao)) {
            throw new TabuleiroException("Erro ao colocar peça na posição " + posicao + ": Posição já ocupada!");
        }

        peca.posicao = posicao;
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;

    }

    //Verifica se a posição passada é valida por linha x coluna
    private boolean posicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    //Verifica se a posição passada é valida por posição linha x coluna
    public boolean posicaoValida(Posicao posicao) {
        return posicaoValida(posicao.getLinha(), posicao.getColuna());
    }

    //Verifica se a posição passada não tem peça
    public boolean temPeca(Posicao posicao) {
        
        if (!posicaoValida(posicao)) {
            throw new TabuleiroException("Erro ao colocar peça na posição " + posicao + ": Posição inválida!");

        }
        
        return getPeca(posicao) != null;
    }
    
    public Peca removerPeca(Posicao posicao){
        
        if (!posicaoValida(posicao)){
         throw new TabuleiroException("Erro ao remover peça: Posição inválida!");
        }
        
        
        //Caso não tenha peça, anda 
        if (getPeca(posicao) == null){
           return null;
        }
        
        Peca aux = getPeca(posicao);
        aux.posicao = null;
        this.pecas[posicao.getLinha()][posicao.getColuna()] = null;
        return aux;
    }
    

}
