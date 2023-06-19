package teste;

import xadrez.PartidaXadrez;

public class Main {

    public static void main(String[] args) {
        
        UITabuleiro tabuleiro = new UITabuleiro();
        tabuleiro.imprimirTabuleiro(new PartidaXadrez().getTabuleiro());
    }
}
