package teste;

import xadrez.PartidaXadrez;



public class Main {

    public static void main(String[] args) {
        
        
        PartidaXadrez px = new PartidaXadrez();
        
        UITabuleiro ui = new UITabuleiro();
        ui.imprimirTabuleiro(px.getTabuleiro());
    }

}
