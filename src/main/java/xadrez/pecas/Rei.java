package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
    
    private PartidaXadrez partidaXadrez;

    public Rei(Cor cor, Tabuleiro tabuleiro,PartidaXadrez partidaXadrez) {
        super(cor, tabuleiro);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return "R";
    }

    private boolean podeMover(Posicao posicao) {

        PecaXadrez px = (PecaXadrez) tabuleiro.getPeca(posicao);

        return px == null || px.getCor() != getCor();
    }
    
    private boolean podeMovimentoRoque(Posicao posicao){
        
        PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getCountMovimento() == 0;
        
    }

    @Override
    public boolean[][] movimentosPossiveis() {

        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        //Em cima
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A baixo
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A esquerda
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A direita
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A noroeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A nordeste
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A sudoeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }

        //A sudeste
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (tabuleiro.posicaoValida(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //Movimento Roque
        if (getCountMovimento() == 0 && !partidaXadrez.getCheck()){
            
            //Roque pelo lado do rei
            Posicao posicaoTorre1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (podeMovimentoRoque(posicaoTorre1)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                
                if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null){
                  matriz[posicaoTorre1.getLinha()][posicao.getColuna() + 2] = true;
                }
            }

            //Roque pelo lado da rainha
            Posicao posicaoTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (podeMovimentoRoque(posicaoTorre2)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                
                if (getTabuleiro().getPeca(p1) == null && getTabuleiro().getPeca(p2) == null && getTabuleiro().getPeca(p3) == null){
                  matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
            
        }
        
        return matriz;
    }

}
