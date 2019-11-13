package Game;
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public static void direitoEstrada(){
        System.out.println("Direito estrada");
    }
    public static void esquerdoEstrada(){
        System.out.println("Esquerdo estrada");
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
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                decisaoEstrada();
            }
            try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
                }
        }//Fim Luta
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
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
                }
        }//Fim Luta
    }
    public static void main(String[] args) {
       System.out.println("");
       inicioHistoria();
        //correrCasa();
       
       
    }
    
}
