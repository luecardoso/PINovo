package Game;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lucas.creis2
 */
public class Run {
    //jogador vetor com suas habilidades
    static float[] jogador = {100f,3f,3f,5f, 100f};
    static boolean defendendoJogador = false;
    static float[] inimigoUm = {100f,3f,3f,2f, 100f};
    static boolean defendendoInimigoUm = false;
    static Scanner entrada = new Scanner(System.in);
    
    //[0] = vida, [1] = Ataque, [2] = Defesa, [3] = inteligencia, [4] = MaxVida
    // retorna a vida do jogador
    public static float getVida(){
        return jogador[0];
    }
    
    // retorna o ataque do jogador
    public static float getAtaque(){
        return jogador[1];
    }
    
    // retorna a defesa do jogador
    public static float getDefesa(){
        return jogador[2];
    }
    
    // retorna a inteligencia do jogador
    public static float getInteligencia(){
        return jogador[3];
    }
    
    // retorna a vida MAXIMA do jogador
    public static float getMaxVida(){
        return jogador[4];
    }
    
    //incrementar 
    public static void setVida(float valor){
        jogador[0] += valor;
        if(jogador[0] > jogador[4]){
            jogador[0] = jogador[4];
        }
    }
    
    
    public static void setAtaque(float valor){
        jogador[1] += valor;    
    }
    
    
    public static void setDefesa(float valor){
        jogador[2] += valor; 
    }
    
    
    public static void setInteligencia(float valor){
        jogador[3] += valor;
    }
    
    
    public static void ataque(float[] atacante, float[] atacado, boolean def){
        Random r = new Random();
        int aux = r.nextInt(12);

        if( aux < 3 - atacante[3] / 4){
            //Errou
        }else if(aux > 12 - atacante[3] / 4){
            //Critico
            acertado(atacado, def, atacante[1] + atacante[1] * 0.5f);
        }else{
            //Acerto Normal
            acertado(atacado, def,atacante[1]);
        }
    }
    
    public static void acertado(float[] atacado, boolean def, float ataque){
        //Se defendendo Usa o poder total de defesa
        if(def){
            if(ataque > atacado[2]){
                atacado[0] -= (ataque - atacado[2]) * 8;
            }
        //Se nao usa so 50% da defesa
        }else{
            if(ataque > atacado[2] / 2){
                atacado[0] -= (ataque - atacado[2]/2) * 8;
            }
        }
    }
    
    /**
     * 
     * @param inimigo 
     */
    public static void esperarTempo(int valor){
        try {
            Thread.sleep(valor);
        } catch (InterruptedException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     */
    static String mandarMensagem(){
        List palavras = new ArrayList();
        palavras.add("Voce foi derrotado");
        palavras.add("");
        Collections.shuffle(palavras);
        return ""+ palavras.get(0);
    }
    
    /**
     * Descricao do personagem e suas habilidades
     */
    static void personagem(){
        System.out.println("Habilidades");
        System.out.println("------------------------");
        System.out.printf("| Vida: %14s%2s\n",getVida()," |");
        System.out.printf("| Ataque: %10s%4s\n",getAtaque()," |");
        System.out.printf("| Defesa: %10s%4s\n",getDefesa()," |");
        System.out.printf("| Inteligência: %4s%4s\n",getInteligencia()," |");
        System.out.println("------------------------");
       
    }
     
    static void barraDeEstatos(float[] inimigo){
        esperarTempo(2000);
        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.println("---------------------------------");
        System.out.printf("| %21s%2s%5s%2s\n","jogador","|","Inimigo","|");
        System.out.printf("| Vida: %14s%3s%6s%3s\n",getVida(),"|", inimigo[0],
                " |");
        System.out.printf("| Ataque: %10s%5s%5s%4s\n",getAtaque(),"|",
                inimigo[1]," |");
        System.out.printf("| Defesa: %10s%5s%5s%4s\n",getDefesa(),"|",
                inimigo[2]," |");
        System.out.printf("| Inteligência: %4s%5s%5s%4s\n",getInteligencia(),
                "|",inimigo[3]," |");
        System.out.println("---------------------------------");
    }
    
    /**
     * 
     */
    public static void inicioHistoria(){
        System.out.println("A história começa...");
        personagem();
        inicio();
    }
    
    /**
     * 
     */
    //1.0
    public static void inicio(){
        String escolha;
        do{ 
            System.out.println("Caminho Inicio");
            System.out.println("Qual caminho deseja seguir? ");
            System.out.println("A) Investigar a casa");
            System.out.println("B) Ir para a estrada");
            escolha = entrada.next();
        }while(!escolha.equalsIgnoreCase("A")&&!escolha.equalsIgnoreCase("B"));
       if(escolha.equalsIgnoreCase("A")){
           investigarCasa();
       }else{
           irParaEstrada();
       }
    }
    
    //1.1
    public static void investigarCasa(){
       String escolha;
        do{ 
            System.out.println("Caminho investigarCasa");
            System.out.println("Você deseja pegar o machado? ");
            System.out.println("A) Sim");
            System.out.println("B) Não");
            escolha = entrada.next();
        }while(!escolha.equalsIgnoreCase("A")&&!escolha.equalsIgnoreCase("B"));
       if(escolha.equalsIgnoreCase("A")){
           decisaoEstrada();
       }else{
           decisaoEstrada();
       }
    }
    
    //1.1.1
    public static void dentroCasa(){
        
    }
    //1.1.1.1 
    public static void decisaoEstrada(){
        String escolha;
        do{ 
            System.out.println("Caminho Decisao Estrada");
            System.out.println("Qual lado deseja ir? ");
            System.out.println("A) Direita");
            System.out.println("B) Esquerda");
            escolha = entrada.next();
        }while(!escolha.equalsIgnoreCase("A")&&!escolha.equalsIgnoreCase("B"));
       if(escolha.equalsIgnoreCase("A")){
           direitoEstrada();
       }else{
           esquerdoEstrada();
       }
    }
    //Caminho direito estrada;
    public static void direitoEstrada(){
        System.out.println("Direito estrada - Floresta");
        String escolha;
        do{ 
            System.out.println("uma néveoa pela manha cobre a floresta ouve barulhos");
            System.out.println("o que deseja fazer? ");
            System.out.println("A) Investigar");
            System.out.println("B) Seguir em frente");
            escolha = entrada.next();
        }while(!escolha.equalsIgnoreCase("A")&&!escolha.equalsIgnoreCase("B"));
       if(escolha.equalsIgnoreCase("A")){
           investigarArbusto();
       }else{
           acharRio();
           
       }
    }
    public static void acharRio(){
        System.out.println("Achando o Rio");
        String escolha;
        do{ 
            System.out.println("acha o rio");
            System.out.println("o que deseja fazer? ");
            System.out.println("A) Atravessar");
            System.out.println("B) Seguir o caminho do rio por onde esta");
            escolha = entrada.next();
        }while(!escolha.equalsIgnoreCase("A")&&!escolha.equalsIgnoreCase("B"));
       if(escolha.equalsIgnoreCase("A")){
           System.out.println("cai e ele segue  a corrente do rio");
           chegarCidade();
       }else{
           System.out.println("acha um barco de alguem");
           chegarCidade();
       }
    }
    public static void chegarCidade(){
        System.out.println("Chegando na cidade");
    }
    public static void investigarArbusto(){
        System.out.println("Investigando o arbusto");
        System.out.println("Luta");
        System.out.println("Ganhando vai para o rio");
    }
    
    //Caminho esquerdo estrada;
    public static void esquerdoEstrada(){
        System.out.println("Esquerdo estrada - Montanha");
        
        String escolha;
        do{ 
            System.out.println("em direcao à montanha encontra um  \n" +
                                "vilarejo onde escuta um barulho m uma casa");
            System.out.println("para qual casa deseja ir? ");
            System.out.println("A) Com Barulho");
            System.out.println("B) Sem Barulho");
            escolha = entrada.next();
        }while(!escolha.equalsIgnoreCase("A")&&!escolha.equalsIgnoreCase("B"));
       if(escolha.equalsIgnoreCase("A")){
          casaBarulho();
       }else{
           casaSemBarulho();
       }
    }
    
    public static void casaBarulho(){
        System.out.println("Casa com barulho");
        System.out.println("Dentro da casa acha suprimentos e \n"
                + "decide ir na outra casa");
        casaSemBarulho();
    }
    public static void casaSemBarulho(){
        System.out.println("Casa Sem Barulho");
        System.out.println("Acha  um corpo e é atacado pelo povo da montanha");
        System.out.println("escapa  e encontra um homem numa carruagem");
        System.out.println("atacados por um bicho e corre para a cidade");
        chegarCidade();
    }
    //1.2
    public static void irParaEstrada(){
        String escolha;
        do{ 
            System.out.println("Caminho decisao casa ou estrada");
            System.out.println("Qual caminho deseja seguir? ");
            System.out.println("A) Correr para a Casa");
            System.out.println("B) Correr para a Estrada");
            escolha = entrada.next();
        }while(!escolha.equalsIgnoreCase("A")&&!escolha.equalsIgnoreCase("B"));
       if(escolha.equalsIgnoreCase("A")){
           correrCasa();
       }else{
           correrEstrada();
       }
    }
    
    //1.2.1.1//luta
    public static void correrCasa(){
        System.out.println("Correr para a Casa");
        System.out.println("Acha e pega um machado");
        System.out.println("Luta");
        lutar();
    }
    //1.2.1.2
    public static void esperarCasa(){
        String escolha;
        do{ 
            System.out.println("Espera em casa");
            System.out.println("Amanhece");
            decisaoEstrada();
            escolha = entrada.next();
        }while(!escolha.equalsIgnoreCase("A")&&!escolha.equalsIgnoreCase("B"));
       if(escolha.equalsIgnoreCase("A")){
           
       }else{
           
       }
    }
    //1.2.1.2 
    public static void correrEstrada(){
            System.out.println("Correr para a Estrada");
            System.out.println("Acha um pau");
            System.out.println("Luta");
        
       lutar();
    }
    
    /**
     * 
     */
    static void lutar(){
         //Luta
        boolean lutando = true;
        //True Player, False Inimgo
        boolean turno = true;
        //
        inimigoUm[0] = 100;
        Random r = new Random();
        while(lutando){
            barraDeEstatos(inimigoUm);
            int leitor;
            if(turno){
                do{
                System.out.println("Sua Vez: 1- para Atacar, 2- para Defender");
                leitor = entrada.nextInt();
                    if(leitor == 1){
                    //Ataca
                        System.out.println("Atacando");
                        ataque(jogador, inimigoUm, defendendoInimigoUm );
                    }else if(leitor == 2){
                    //Defende
                        System.out.println("Defendendo");
                        defendendoJogador = true;
                    }
                }while(leitor != 1 && leitor != 2);
                turno =! turno;
            }else{
                System.out.println("Vez do Adiversario");
                esperarTempo(2000);
                int resultado = r.nextInt(100);
                if(resultado < 24){
                    leitor = 2;
                }else{
                    leitor = 1;
                }
                if(leitor == 1){
                //Ataca
                    System.out.println("Adiversario Atacou");
                    ataque(inimigoUm,jogador, defendendoJogador);
                }else if(leitor == 2){
                //Defende
                    System.out.println("Adiversario Defendeu");
                    defendendoInimigoUm = true;
                }
                turno =! turno;
            }
            defendendoJogador = false;
            defendendoInimigoUm = false;
            if(getVida() <= 0){
                lutando = false;
                System.out.println("Voce è um Perdedor, Comece de novo");
            }else if(inimigoUm[0] <= 0){
                lutando= false;
                System.out.println("Voce lutou bravamente e venceu e seu orulho"
                        + " fez com que sua vida regenerace 50 pontos, "
                        + "alem de ficar mais forte");
                setVida(50);
                setAtaque(1);
                setDefesa(1);
                setInteligencia(1);
                personagem();
                direitoEstrada();
            }
            esperarTempo(2000);
        }//Fim Luta
    }
    
    public static void main(String[] args) {
       System.out.println(" "
               + " _____        __ _       _ _         \n" +
            " |_   _|      / _(_)     (_) |        \n" +
            "   | |  _ __ | |_ _ _ __  _| |_ _   _ \n" +
            "   | | | '_ \\|  _| | '_ \\| | __| | | |\n" +
            "  _| |_| | | | | | | | | | | |_| |_| |\n" +
            " |_____|_| |_|_| |_|_| |_|_|\\__|\\__, |\n" +
            "  / __ \\    | |                  __/ |\n" +
            " | |  | | __| |_ ___ ___  ___ _ |___/ \n" +
            " | |  | |/ _` | / __/ __|/ _ \\ | | |  \n" +
            " | |__| | (_| | \\__ \\__ \\  __/ |_| |  \n" +
            "  \\____/ \\__,_|_|___/___/\\___|\\__, |  \n" +
            "                               __/ |  \n" +
            "                              |___/   ");
       inicioHistoria();
        //correrCasa();
        System.out.println("");
       
    }
    
}
