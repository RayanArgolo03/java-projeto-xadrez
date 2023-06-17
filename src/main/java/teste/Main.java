package teste;

import xadrez.PartidaXadrez;

public class Main {
    
    public static void main(String[] args) {
        
        
        UITabuleiro ui = new UITabuleiro();
        
        ui.imprimirTabuleiro(new PartidaXadrez().getTabuleiro());
        
        }
    }
    
    
    
