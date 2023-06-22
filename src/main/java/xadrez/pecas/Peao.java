package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    public Peao(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);
        
        if (getCor().equals(Cor.AZUL)){
            
            p.setValores(posicao.getLinha() -1, posicao.getColuna());
            if (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
             
            if (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoValida(p2) && !getTabuleiro().temPeca(p2) && getCountMovimento() == 0){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() -1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            
        } else {
        
            p.setValores(posicao.getLinha() +1, posicao.getColuna());
            if (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
             
            if (getTabuleiro().posicaoValida(p) && !getTabuleiro().temPeca(p) && getTabuleiro().posicaoValida(p2) && !getTabuleiro().temPeca(p2) && getCountMovimento() == 0){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoValida(p) && temPecaOponente(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
        
        }
    
        return matriz;
    }

    @Override
    public String toString() {
        return "P";
    }
    
    

}
