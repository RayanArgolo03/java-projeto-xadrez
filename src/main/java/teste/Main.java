package teste;

import xadrez.XadrezException;
import java.util.*;
import xadrez.*;

public class Main {

    public static void main(String[] args) {
        
        //Partida Principal e Lista de peças capturadas
        PartidaXadrez px = new PartidaXadrez();
        List<PecaXadrez> capturadas = new ArrayList<>();
        
        
        final Scanner sc = new Scanner(System.in);
        

        
        while (!px.getCheckMate()) {

            try {
                UITabuleiro.limparTela();
                UITabuleiro.imprimirPartida(px, capturadas);
                
                System.out.println();

                System.out.print("De:");
                PosicaoXadrez p = UITabuleiro.lerPosicao(sc);

                ///Imprimir movimentos possíveis para a peça escolhida
                boolean[][] movimentosPossiveis = px.movimentosPossiveis(p);
                UITabuleiro.limparTela();

                UITabuleiro.imprimirTabuleiro(px.getPecas(), movimentosPossiveis);

                System.out.println();
                System.out.print("Para ");
                
                //Enter novamente para ler posição
                PosicaoXadrez p1 = UITabuleiro.lerPosicao(sc);

                PecaXadrez pecaCapturada = px.executarMovimento(p, p1);

                //Caso tenha peça capturada, add na lista
                if (pecaCapturada != null) {
                    capturadas.add(pecaCapturada);
                }

                //Caso haja a possibilidade de jogada promoção
                if (px.getPromocao() != null) {

                    System.out.print("Escolha para qual promover: (T/C/B/Q): ");
                    String tipo = sc.next().toUpperCase();

                    //Enquanto a troca não for feita
                    while (!tipo.equals("T") || !tipo.equals("C") || !tipo.equals("B") || !tipo.equals("Q")) {
                         System.out.print("Tipo incorreto! Escolha para qual promover: (T/C/B/Q): ");
                         tipo = sc.next().toUpperCase();
                        
                    }

                    px.trocarPecaPromovida(tipo);
                }
            }
            
            //Exceptions
            catch (XadrezException e) {
                System.out.println();
                System.out.print(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println();
                System.out.print(e.getMessage());

            }

//            
            //Quebra de linha para imprimir exceções
            sc.nextLine();
            sc.nextLine();
        }

        //Caso haja um ganhador, exibe mensagem check
        UITabuleiro.limparTela();
        UITabuleiro.imprimirPartida(px, capturadas);

    }

}
