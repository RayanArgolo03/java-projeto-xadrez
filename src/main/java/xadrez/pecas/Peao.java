package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

    private PartidaXadrez partidaXadrez;
    
    public Peao(Cor cor, Tabuleiro tabuleiro, PartidaXadrez partidaXadrez) {
        super(cor, tabuleiro);
        this.partidaXadrez = partidaXadrez;
    }

    //Lógica de movimentos do Peão
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
            
            //Movimento Especial - En Passant Azul
            if (posicao.getLinha() == 3){
                
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoValida(esquerda) && temPecaOponente(esquerda) && getTabuleiro().getPeca(esquerda) == partidaXadrez.getVulneravelEnPassant()){
                    matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoValida(direita) && temPecaOponente(direita) && getTabuleiro().getPeca(direita) == partidaXadrez.getVulneravelEnPassant()){
                    matriz[direita.getLinha() - 1][direita.getColuna()] = true;
                }
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
        
           //Movimento Especial - En Passant Vermelho
            if (posicao.getLinha() == 4){
                
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoValida(esquerda) && temPecaOponente(esquerda) && getTabuleiro().getPeca(esquerda) == partidaXadrez.getVulneravelEnPassant()){
                    matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoValida(direita) && temPecaOponente(direita) && getTabuleiro().getPeca(direita) == partidaXadrez.getVulneravelEnPassant()){
                    matriz[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            } 
            
        }
    
        return matriz;
    }

    @Override
    public String toString() {
        return "P";
    }
    
    

}
