package Game;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author lucas.creis2
 */
public class Run {
    //[0] = vida, [1] = Ataque, [2] = Defesa, [3] = inteligencia, [4] = MaxVida
    //jogador vetor com suas habilidades
    static float[] jogador = {100f,3f,3f,5f, 100f};
    static boolean defendendoJogador = false;
    static float[] armaEquipada;
    
    //Inimigo UM !
    static float[] inimigoUm = {100f,3f,3f,2f, 100f};
    static boolean defendendoInimigoUm = false;
    
    //Inimigo DOIS !
    static float [] inimigoDois = { 100f,3f,3f,2f,100f};
    static boolean defendendoInimigoDois = false;
    
    //Inimigo TRES !
    static float []inimigoTres = {100f,3f,3f,2f,100f};
    static boolean defendendoInimigoTres = false;
    
    //Inimigo Quatro !
    static float []inimigoQuatro = {100f,3f,3f,2f,100f};
    static boolean defendendoInimigoQuatro = false;
    
    //Inimigo CINCO !
    static float []inimigoCinco = {100f,3f,3f,2f,100f};
    static boolean defendendoInimigoCinco = false;
    
    //Inimigo SEIS !
    static float []inimigoSeis = {100f,3f,3f,2f,100f};
    static boolean defendendoInimigoSeis = false;
    
    //Inimigo SETE !
    static float []inimigoSete = {100f,3f,3f,2f,100f};
    static boolean defendendoInimigoSete = false;
    
    // BOOOSSSSSS !!!!!!!!!!
    static float []boss = {100f,3f,3f,2f,100f};
    static boolean defendendo_boss = false;
    
    //Armas
    static float[] machado = {5,4,2,1,5};
    static float[] facao = {};
    
    static Scanner entrada = new Scanner(System.in);
    
    //Salva a ultima cena
    static int cena = 0;
    
    // retorna a vida do jogador
    public static float getVida(){
        return statusGeralPlayer()[0];
    }
    
    // retorna o ataque do jogador
    public static float getAtaque(){
        return statusGeralPlayer()[1];
    }
    
    // retorna a defesa do jogador
    public static float getDefesa(){
        return statusGeralPlayer()[2];
    }
    
    // retorna a inteligencia do jogador
    public static float getInteligencia(){
        return statusGeralPlayer()[3];
    }
    
    // retorna a vida MAXIMA do jogador
    public static float getMaxVida(){
        return statusGeralPlayer()[4];
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
    
    public static float[] statusGeralPlayer(){
        if(armaEquipada != null){
            float[] aux = new float[jogador.length];
            for (int i = 0; i < jogador.length; i++) {
                aux[i] =  jogador[i] + armaEquipada[i];
            }
            return aux;
        }
        else{
            return jogador;
        }
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
    
    public static void esperarTempo(float valor){
        int aux =(int) (valor * 1000);
        try {
            Thread.sleep(aux);
        } catch (InterruptedException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    static void lutar(float[] inimigo, boolean inimigoDef){
         //Luta
        boolean lutando = true;
        //True Player, False Inimgo
        boolean turno = true;
        //
        inimigo[0] = 100;
        Random r = new Random();
        while(lutando){
            barraDeStatus(inimigo);
            int leitor;
            if(turno){
                do{
                System.out.println("Sua Vez: 1- para Atacar, 2- para Defender");
                leitor = entrada.nextInt();
                    if(leitor == 1){
                    //Ataca
                        System.out.println("Atacando");
                        ataque(statusGeralPlayer(), inimigo, inimigoDef );
                    }else if(leitor == 2){
                    //Defende
                        System.out.println("Defendendo");
                        defendendoJogador = true;
                    }
                }while(leitor != 1 && leitor != 2);
                turno =! turno;
            }else{
                System.out.println("Vez do Adiversario");
                esperarTempo(1);
                int resultado = r.nextInt(100);
                if(resultado < 24){
                    leitor = 2;
                }else{
                    leitor = 1;
                }
                if(leitor == 1){
                //Ataca
                    System.out.println("Adiversario Atacou");
                    ataque(inimigo,statusGeralPlayer(), inimigoDef);
                }else if(leitor == 2){
                //Defende
                    System.out.println("Adiversario Defendeu");
                    inimigoDef = true;
                }
                turno =! turno;
            }
            defendendoJogador = false;
            inimigoDef = false;
            if(getVida() <= 0){
                lutando = false;
                System.out.println(mensagemDerrota());
            }else if(inimigo[0] <= 0){
                lutando= false;
                System.out.println(mensagemVitoria());
                setVida(50);
                setAtaque(1);
                setDefesa(1);
                setInteligencia(1);
                personagem();
                
            }
            esperarTempo(2);
        }//Fim Luta
    }

    static String mensagemDerrota(){
        List palavras = new ArrayList();
        palavras.add("Voce foi derrotado");
        palavras.add("");
        Collections.shuffle(palavras);
        return ""+ palavras.get(0);
    }
    
    static String mensagemVitoria(){
        List palavras = new ArrayList();
        palavras.add("Essa foi por pouco");
        palavras.add("");
        Collections.shuffle(palavras);
        return ""+ palavras.get(0);
    }

    //Descricao do personagem e suas habilidades
    static void personagem(){
        System.out.println("Habilidades");
        System.out.println("------------------------");
        System.out.printf("| Vida: %14s%2s\n",getVida()," |");
        System.out.printf("| Ataque: %10s%4s\n",getAtaque()," |");
        System.out.printf("| Defesa: %10s%4s\n",getDefesa()," |");
        System.out.printf("| Inteligência: %4s%4s\n",getInteligencia()," |");
        System.out.println("------------------------");
       
    }
     
    static void barraDeStatus(float[] inimigo){
        esperarTempo(1);
        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.println("-----------------------------------");
        System.out.printf("| %22s%2s%5s%2s\n","jogador","|","Inimigo","|");
        System.out.println("-----------------------------------");
        System.out.printf("| Vida: %16s%2s%6s%3s\n",statusGeralPlayer()[0],"|", inimigo[0],
                " |");
        System.out.printf("| Ataque: %14s%2s%5s%4s\n",statusGeralPlayer()[1],"|",
                inimigo[1]," |");
        System.out.printf("| Defesa: %14s%2s%5s%4s\n",statusGeralPlayer()[2],"|",
                inimigo[2]," |");
        System.out.printf("| Inteligência: %8s%2s%5s%4s\n",statusGeralPlayer()[3],
                "|",inimigo[3]," |");
        System.out.println("-----------------------------------");
    }

    public static void armar(float[] armas, String nomeArma){
        
        System.out.println("\n--------------------------");
        System.out.printf("| %18s%6s\n",nomeArma,"|");
        System.out.println("--------------------------");
        System.out.printf("| Vida: %14s%4s\n",armas[0]," |");
        System.out.printf("| Ataque: %12s%4s\n",armas[1]," |");
        System.out.printf("| Defesa: %12s%4s\n",armas[2]," |");
        System.out.printf("| Inteligência: %6s%4s\n",armas[3]," |");
        System.out.println("--------------------------");
    }

    //Capitulo 1
    public static void inicioHistoria(){
        System.out.println("A história começa...");
        personagem();
        //armar();
        inicioCapituloUm();
        inicioCapituloDois();
        inicioCapituloTres();
        
    }
    

    //         INICIO!!!!!!!!!!!!
    
    //Ato 1 - The Fall (A Queda)
    public static void inicioCapituloUm(){
        cena = 1;
        String escolha;
        do{ 
            System.out.println("Caminho Inicio");
            System.out.println("Qual caminho deseja seguir? ");
            System.out.println("A) Investigar a casa");
            System.out.println("B) Ir para a estrada");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           investigarCasa();
       }else{
           irParaEstrada();
       }
    }
    
    // 1.
    public static void investigarCasa(){
        cena = 2;
        String escolha;
        do{ 
            System.out.println("Caminho investigarCasa");
            System.out.println("Você deseja pegar o machado? ");
            armar(machado, "Machado");
            System.out.println("A) Sim");
            System.out.println("B) Não");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           armaEquipada = machado;
           pegaMachado();
       }else{
           naoMachado();
       }
    }
    
//    // to doido pra deletar essa bosta;
//    public static void dentroCasa(){
//        cena = 3;
//        System.out.println("");
//    }
    
    // 1 - 1.
    public static void pegaMachado(){
        System.out.println("Voce pega o machado e ganha atributos de ataque");
        
        personagem();
        //barraDeStatus(inimigoUm);
        decisaoEstrada();
    }
    
    // 1 - 2.
    public static void naoMachado(){
        System.out.println("Voce nao pega o machado e nao recebe atributos de ataque.");
        decisaoEstrada();
    }
    
    // 2.
    public static void irParaEstrada(){
        cena = 3;
        String escolha;
        do{ 
            System.out.println("Caminho decisao casa ou estrada");
            System.out.println("Qual caminho deseja seguir? ");
            System.out.println("A) Correr para a Casa");
            System.out.println("B) Correr para a Estrada");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           correrParaCasa();
       }else{
           correrPelaEstrada();
       }
    }
    
    // 2 - 1.
    public static void correrParaCasa(){
        cena = 4;
        System.out.println("Correr para a Casa");
        System.out.println("Acha e pega um machado");
        System.out.println("Luta");
        lutar(inimigoUm, defendendoInimigoUm);
        decisaoEstrada();
    }
    
    // 2 - 2.
    public static void correrPelaEstrada(){
        cena = 5;
        System.out.println("Correr para a Estrada");
        System.out.println("Acha um graveto");
        System.out.println("Luta");
        lutar(inimigoUm, defendendoInimigoUm);
        decisaoEstrada();
    }
    
    // 3
    public static void decisaoEstrada(){
        cena = 6;
        String escolha;
        do{ 
            System.out.println("Caminho Decisao Estrada");
            System.out.println("Qual lado deseja ir? ");
            System.out.println("A) Direita");
            System.out.println("B) Esquerda");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           direitoEstrada();
       }else{
           esquerdoEstrada();
       }
    }
    
    //CAMINHO DIREITO DA ESTRADA---------------------------
    
    // 3 - 1.
    public static void direitoEstrada(){
        cena = 7;
        System.out.println("Direito estrada - Floresta");
        String escolha;
        do{ 
            System.out.println("uma néveoa pela manha cobre a floresta ouve barulhos");
            System.out.println("o que deseja fazer? ");
            System.out.println("A) Investigar");
            System.out.println("B) Seguir em frente");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           investigarArbusto();
       }else{
           acharRio();
           
       }
    }
    
    // 3 - 1 - 1.
    public static void acharRio(){
        cena = 8;
        System.out.println("Achando o Rio");
        String escolha;
        do{ 
            System.out.println("acha o rio");
            System.out.println("o que deseja fazer? ");
            System.out.println("A) Atravessar");
            System.out.println("B) Seguir o caminho do rio por onde esta");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           System.out.println("cai e ele segue  a corrente do rio");
           chegarCidade();
       }else{
           System.out.println("acha um barco de alguem");
           chegarCidade();
       }
    }
    
    // 3 - 1 - 2.
    public static void investigarArbusto(){
        cena = 9;
        System.out.println("Investigando o arbusto");
        System.out.println("Luta");
        lutar(inimigoDois, defendendoInimigoDois);
        System.out.println("Ganhando vai para o rio");
        acharRio();
    }
    
    //-------------------------------------------------------
    
    
    //CAMINHO ESQUERDO DA ESTRADA --------------------------------------------;
    // 3 - 2.
    public static void esquerdoEstrada(){
        cena = 10;
        System.out.println("Esquerdo estrada - Montanha");
        
        String escolha;
        do{ 
            System.out.println("em direcao à montanha encontra um  \n" +
                                "vilarejo onde escuta um barulho m uma casa");
            System.out.println("para qual casa deseja ir? ");
            System.out.println("A) Com Barulho");
            System.out.println("B) Sem Barulho");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
            casaBarulho();
       }else{
            casaSemBarulho();
       }
    }
    
    // 3 - 2 - 1.
    public static void casaBarulho(){
        cena = 11;
        System.out.println("Casa com barulho");
        System.out.println("Dentro da casa acha suprimentos e \n"
                + "decide ir na outra casa");
        casaSemBarulho();
    }
    
    // 3 - 2 - 2.
    public static void casaSemBarulho(){
        cena = 12;
        System.out.println("Casa Sem Barulho");
        esperarTempo(3);
        System.out.println("Acha  um corpo e é atacado pelo povo da montanha");
        esperarTempo(2);
        System.out.println("escapa  e encontra um homem numa carruagem");
        esperarTempo(3);
        System.out.println("atacados por um bicho e corre para a cidade");
        chegarCidade();
    }
    
    //----------------------------------------------------
    
//    //Apagar depois hm(?);
//    public static void esperarCasa(){
//        cena = 14;
////        String escolha;
////        do{ 
////            System.out.println("Espera em casa");
////            System.out.println("Amanhece");
////            decisaoEstrada();
////            escolha = entrada.next();
////        }while(!validaentrada(escolha));
////       if(escolha.equalsIgnoreCase("A")){
////           
////       }else{
////           
////       }
//    }
    
    // 4.
    public static void chegarCidade(){
        cena = 13;
        System.out.println("Chegando na cidade");
    }
    //-------------------------------------------
    
    

    
    
    //ATO  2 - The Vision (A Visao)
    
    //4.
    public static void inicioCapituloDois(){
        cena = 14;
        esperarTempo(1.5f);
        System.out.println("\n\n\n\n\n\n\n");
        String escolha;
        do{ 
            System.out.println("Caminho Inicio Personagem 2");
            System.out.println("Acorda e vê um corpo entre os fenos(pers1)");
            System.out.println("O que deseja fazer? ");
            System.out.println("A) Investigar o corpo");
            System.out.println("B) Buscar ajuda na casa da frente");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           investigarCorpo();
       }else{
           buscarAjuda();
       }
    }
  
    // 4 - 1. 
    public static void investigarCorpo(){
        cena = 15;
        System.out.println("Escuta barulhos na casa e vai lá");
        System.out.println("quando chega proximo ve alguem fugindo pela\n"
                + " estrada e logo em seguida sendo morto");
        System.out.println("");
        levarClarao();
    }
    
    // 4 - 2.
    public static void buscarAjuda(){
        cena = 16;
        System.out.println("Encontra pessoas");
        System.out.println("resumo de uma luta e ele é pego e amarrado\n"
                + " (dialogos)");
        String escolha;
        do{ 
            System.out.println("caminho casa ");
            System.out.println("Escolher frase");
            System.out.println("O que vai dizer? ");
            System.out.println("A) blablabla");
            System.out.println("B) nanana");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
            ajudarPersonagem();
       }else{
            esconderCasa();
       }
    }
    
    // 4 - 2 - 1.
    public static void ajudarPersonagem(){
        cena = 17;
        System.out.println("todos vão juntos");
        levarClarao();
    }
    
    // 4 - 2 - 2.
    public static void esconderCasa(){
        cena = 18;
        System.out.println("Personagens da casa se escondem");
        levarClarao();
    }

    // 5.
    public static void levarClarao(){
        cena = 19;
        System.out.println("Leva um clarão e é levado");
        System.out.println("Visão do personagem 2 do laboratorio");
        System.out.println("conta um pouco da historia do laboratorio\n"
                + " e ve o pessoal daquela casa sofrendo os experimentos");
        System.out.println("az o teste em alguem nao da certo prende numa \n"
                + "jaula pra depois transformar em animal, ai vira um urso e zn"
                + "começa a quebrar as coisas, os ajudantes vão atrás, estãozn"
                + " presos mas depois se soltam(talvez o urso)");
        String escolha;
        do{ 
            System.out.println("caminho laboratorio ");
            System.out.println("Escolher direção pra sair");
            System.out.println("Pra onde vai? ");
            System.out.println("A) Direita");
            System.out.println("B) Esquerda");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           direitaLaboratorio();
       }else{
          esquerdaLaboratorio();
       }
    }
 
    // 5 - 2.
    public static void direitaLaboratorio(){
        cena = 20;
        System.out.println("Luta com uns guardas");
        System.out.println("Criar esses personagns para a luta e colocar a luta");
        sairLaboratorio();
    }

    // 5 - 1.
    public static void esquerdaLaboratorio(){
        cena = 21;
        System.out.println("urso quebra a parede e pega os guardas sem luta");
        sairLaboratorio();
    }

    // 6.
    public static void sairLaboratorio(){
        cena = 22;
        System.out.println("passa por uma sala e acha um mapa holografico com\n"
                + " a nave e contexto++");
        System.out.println("familia -1, foge e vai para a saída da cidade");
        System.out.println("ve o portao aberto começando a se fechar olha pro\n"
                + " lado ve que pegaram o lobo e corre ");
        System.out.println("");
    }
    //-----------------------------------------------------------------------------
 
    
    
    
    
    
    
    // Ato 3 - A Hope (Uma Esperança)
    // 7.
    public static void inicioCapituloTres(){
        cena = 23;
        System.out.println("ATO 3");
        String escolha;
        do{ 
            System.out.println("Escolha um personagem para continuar ");
            System.out.println("A) Thomas Thompson");
            System.out.println("B) Anthony Burton ");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
          thomasThompson();
       }else{
          anthonyBurton();
       }
    }
    
    
    
    
    //Caminho final Thomas
    // 7 - 1.
    public static void thomasThompson(){
        cena = 24;
        System.out.println("Anthony te leva com ele, os dois param num posto e conversam");
        System.out.println("Sao atacados por dois inimgos enviados por E.T.zinho");
        lutar(inimigoTres, defendendoInimigoTres);
        pantano();
    }
    
    // 7 - 1 - 2.
    public static void pantano(){
        cena = 25;
        String escolha;
        System.out.println("Caminham pelo pântano ");
        do{ 
            System.out.println("Vão pelo pantano e acham uma cabana com ruidos estranhos");
            System.out.println("O que vai fazer? ");
            System.out.println("A) Ir até a cabana");
            System.out.println("B) Seguir em frente");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
          entrarCabana();
       }else{
          naoCabana();
       }
    }
    
    // 7 - 1 - 2 - 1.
    public static void entrarCabana(){
        cena = 26;
         String escolha;
        do{ 
            System.out.println("Entram e nao encontram nada alem de aves... e uma katana");
            System.out.println("Deseja pegar a katana?");
            System.out.println("A) Sim");
            System.out.println("B) Não");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
          achamMonstro();
       }else{
          achamMonstro();
       }
    }
    
    // 7 - 1 - 2 - 2.
    public static void naoCabana(){
        cena = 27;
            System.out.println("Seguem em frente e conversam, mas são atacados por mosntros");
            lutar(inimigoQuatro, defendendoInimigoQuatro);
            achamMonstro();
    }
    
    // 8.
    public static void achamMonstro(){
        cena = 28;
        System.out.println("Fogem");
        achamBoss();
    }
    
    // 8 - 2.
    public static void achamBoss(){
        cena = 29;
        System.out.println("Avistam a plantação e correm ate la");
        System.out.println("Mas aparece o etz e vc batalha com ele e o Anthony concerta a nave");
        lutar(boss, defendendo_boss);
        decisaoFinalT();
    }
    
    // 8 - 2 - 2.
    public static void decisaoFinalT(){
        cena = 30;
        String escolha;
        do{ 
            System.out.println("voce sai vitorioso mas precebe que a nave so cabe um");
            System.out.println("O que deseja fazer?");
            System.out.println("A) Ficar");
            System.out.println("B) Ir a nave");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
          finalFicarT();
       }else{
          finalIrT();
       }
    }
    
    // 8 - 2 - 2 - 1.
    public static void finalFicarT(){
        cena = 31;
        System.out.println("Voce fica e enfrenta o etz para dar tempo do seu amigo fugir");
        System.out.println("Fim....");
    }
    
    // 8 - 2 - 2 - 2.
    public static void finalIrT(){
        cena = 32;
        System.out.println("Voce vai na nave enquanto seu amigo fica para dar tempo de voce fugir");
        System.out.println("Voce ve seu amigo indo em direcao ao etz");
        System.out.println("fim...");
    }
    
    
    
    
    //Caminho Final Anthony
    // 7 - 2
    public static void anthonyBurton(){
        cena = 33;
        System.out.println("Ambos se encontram você o leva ");
        System.out.println("Param num posto de gasolina abandonado e conversam.");
        System.out.println("Decidem ir buscar a nave no celeiro, mas de repente\n"
                + " são atacados por dois inimigos enviado pelo E.Tzinho.");
        lutar(inimigoSeis, defendendoInimigoSeis);
        System.out.println("Decidem seguir um atalho (pântano) para chegar \n"
                + "no celeiro, onde está a nave.");
        pantano2();
    }
    // 7 - 2 
    public static void pantano2(){
        cena = 34;
        String escolha;
        System.out.println("Caminham pelo pântano ");
        do{ 
            System.out.println("Vão pelo pantano e acham uma cabana com ruidos estranhos");
            System.out.println("O que vai fazer? ");
            System.out.println("A) Ir até a cabana");
            System.out.println("B) Seguir em frente");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
          entrarCabana2();
       }else{
          naoCabana2();
       }
    }
    // 7 - 1 - 2 - 1.
    public static void entrarCabana2(){
        cena = 35;
         String escolha;
        do{ 
            System.out.println("Entram e nao encontram nada alem de aves... e uma katana");
            System.out.println("E também acham armas, pé de cabra katana e saem da cabana");
            System.out.println("Deseja pegar a katana?");
            System.out.println("A) Sim");
            System.out.println("B) Não");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
            achamMonstro2();
       }else{
            achamMonstro2();
       }
    }

   // 7 - 1 - 2 - 2.
    public static void naoCabana2(){
        cena = 36;
            System.out.println("Seguem em frente e conversam, mas são atacados por mosntros");
            lutar(inimigoQuatro, defendendoInimigoQuatro);
            System.out.println("Conversam");
            achamMonstro2();
    }
    // 8.
    public static void achamMonstro2(){
        cena = 37;
        System.out.println("Fogem");
        achamBoss2();
    }
    
    // 8 - 2.
    public static void achamBoss2(){
        cena = 38;
        System.out.println("Escaparam do mosntro");
        System.out.println("Finalmente chegam na plantação de trigo  e avistam o celeiro. ");
        System.out.println("");
        System.out.println("Avistam a plantação e correm ate la");
        System.out.println("você conserta a nave e Thomas luta com ele");
        lutar(boss, defendendo_boss);
        decisaoFinalA();
    }
     // 8 - 2 - 2.
    public static void decisaoFinalA(){
        cena = 39;
        String escolha;
        do{ 
            System.out.println("Você arruma a nave e percebe que não há combustível e potência para dois tripulantes. ");
            System.out.println("O que deseja fazer?");
            System.out.println("A) Ficar");
            System.out.println("B) Ir a nave");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
          finalFicarA();
       }else{
          finalIrA();
       }
    }
    // 8 - 2 - 2 - 1.
    public static void finalFicarA(){
        cena = 40;
        System.out.println("Você vê a nave partindo e fica cara a cara com o vilão...");
        System.out.println("Fim....");
    }
    
    // 8 - 2 - 2 - 2.
    public static void finalIrA(){
        cena = 41;
        System.out.println("Você escapa com a nave que segue o trajeto para casa...");
        System.out.println("fim...");
    }
    public static void FinalFicarDois(){
        cena = 42;
        System.out.println("Ambos deixam a nave e vão ir de frente para o vilão...");
        System.out.println("Fim...");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void creditos(){
        System.out.println("Infinity Odissey foi desenvovido por:\n\n");
        System.out.println(" - Gabriel\n");
        System.out.println(" - Lucas\n");
        System.out.println(" - Rogerio Sartori Lucon\n");
        System.out.println("Versao: 0.0.15\n");
        System.out.println("Enter Para voltar ao menu");
        entrada.next();
    }
    
    public static boolean validaentrada(String entrada){
        if(entrada.equalsIgnoreCase("A") || entrada.equalsIgnoreCase("B")){
            return true;
        }else if(entrada.equalsIgnoreCase("exit") 
                || entrada.equalsIgnoreCase("sair")){
            //Chamar opçao de salvar
            Object[] options = { "Salvar e sair", "Sair sem salvar"}; 
            int aux = JOptionPane.showOptionDialog(null,
                    "Deseja sair do jogo?", "Deseja sair do jogo?",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            //Fecha o programa
            if(aux == 0){
                System.out.println(aux);
                salvar();
                System.exit(0);
            }else if(aux == 1){
                System.out.println(aux);
                System.exit(0);
            }
            return false;
        }else if(entrada.equalsIgnoreCase("salvar")){
            salvar();
            return false;
        }else if(entrada.equalsIgnoreCase("carregar")){
            carregar();
            chamaCena(cena);
            return false;
        }else{
            return false;
        }    
    }
    
    public static void esperar(int linhas, boolean delay){
        for (int i = 0; i < linhas; i++) {
            if(delay){
                esperarTempo(0.2f);
            }
            System.out.println("");
        }
    }
    
    public static boolean carregar(){
        try {
            FileReader fr = new FileReader("save.txt");
            BufferedReader bf = new BufferedReader(fr);
            String txtStatus = bf.readLine();
            txtStatus = txtStatus.replace('[', ' ').replace(']', ' ').replace(" ", "");
            String[] aux = txtStatus.split(",");
            for (int i = 0; i < aux.length; i++) {
                jogador[i] = Float.parseFloat(aux[i]);
            }
            cena = Integer.parseInt(bf.readLine());

            fr.close();
            bf.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static void salvar(){
        FileWriter arq;
        try {
            arq = new FileWriter("save.txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            String txtStatus = Arrays.toString(jogador);
            gravarArq.println(txtStatus);
            gravarArq.println(cena);
            
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void chamaCena(int valor){
        switch(valor){
            case 1:
                inicioCapituloUm();
                break;
            case 2:
                investigarCasa();
                break;
            case 3:
                irParaEstrada();
                break;
            case 4:
                correrParaCasa();
                break;
            case 5:
                correrPelaEstrada();
                break;
            case 6:
                decisaoEstrada();
                break;
            case 7:
                direitoEstrada();
                break;
            case 8:
                acharRio();
                break;
            case 9:
                investigarArbusto();
                break;
            case 10:
                esquerdoEstrada();
                break;
            case 11:
                casaBarulho();
                break;
            case 12:
                casaSemBarulho();
                break;
            case 13:
                chegarCidade();
                break;
            case 14:
                inicioCapituloDois();
                break;
            case 15:
                investigarCorpo();
                break;
            case 16:
                buscarAjuda();
                break;
            case 17:
                ajudarPersonagem();
                break;
            case 18:
                esconderCasa();
                break;
            case 19:
                levarClarao();
                break;
            case 20:
                direitaLaboratorio();
                break;
            case 21:
                esquerdaLaboratorio();
                break;
            case 22:
                sairLaboratorio();
                break;
            case 23:
                inicioCapituloTres();
                break;
            case 24:
                thomasThompson();
                break;
            case 25:
                pantano();
                break;
            case 26:
                entrarCabana();
                break;
            case 27:
                naoCabana();
                break;
            case 28:
                achamMonstro();
                break;
            case 29:
                achamBoss();
                break;
            case 30:
                decisaoFinalT();
                break;
            case 31:
                finalFicarT();
                break;
            case 32:
                finalIrT();
                break;
            case 33:
                anthonyBurton();
                break;
            case 34:
                pantano2();
                break;
            case 35:
                entrarCabana2();
                break;
            case 36:
                naoCabana2();
                break;
            case 37:
                achamMonstro2();
                break;
            case 38:
                achamBoss2();
                break;
            case 39:
                decisaoFinalA();
                break;
            case 40:
                finalFicarA();
                break;
            case 41:
                finalIrA();
                break;
            case 42:
                FinalFicarDois();
                break;
        }
    }
    
    public static void instrucao(){
        System.out.println("intrucao do jogo:");
        
        System.out.println("Digite 'Sair/Exit' para sair do jogo");
        System.out.println("Digite 'Carregar' para carregar ultimo save");
        System.out.println("Digite 'Save' para salvar o jogo");
        
        System.out.println("Enter Para voltar ao menu");
        entrada.next();
    }
    
    public static void main(String[] args) {
        //teste
        System.out.print("Carregando: ");
        for(int i = 0; i <10; i++){
            System.out.print("|");
            esperarTempo(0.3f);
        }
        //lutar(inimigoUm, defendendoInimigoUm);
        //armar(machado,"Machado");
        //statusGeralPlayer();
        //barraDeStatus(inimigoUm);
        esperar(5, true);
        // Imagem Inicial
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
        // Menu Inicial
//        System.out.println("Entre qualquer valor para continuar");
//        entrada.next();

        esperar(10, true);
        
        boolean flag = true;
        while(flag){
            System.out.println("|___________________________________________|");
            System.out.printf("|------------- %10s%20s\n", "","-------------|");
            System.out.printf("|------------- %10s%20s\n", "MENU","-------------|");
            System.out.printf("|------------- %10s%20s\n", "","-------------|");
            System.out.println("|___________________________________________|");
            System.out.printf("|------------- %10s%20s\n", "","-------------|");
            System.out.printf("|------------- %4s%6s%20s\n", "1 -", "Novo Jogo","-------------|");
            System.out.printf("|------------- %4s%6s%20s\n", "2 -", "Carregar Jogo","-------------|");
            System.out.printf("|------------- %4s%6s%16s\n", "3 -", "Instruçoes","-------------|");
            System.out.printf("|------------- %4s%6s%19s\n", "4 -", "Credito","-------------|");
            System.out.printf("|------------- %4s%6s%20s\n", "5 -", "Sair","-------------|");
            System.out.printf("|------------- %10s%20s\n", "","-------------|");
            System.out.println("|-------------------------------------------|");
            System.out.println("|-------------------------------------------|");
            int escolha = entrada.nextInt();
            switch(escolha){
                case 1:
                    esperar(10, false);
                    inicioHistoria();
                    break;
                case 2:
                    if(carregar()){
                        chamaCena(cena);
                    }else{
                        inicioHistoria();
                    }
                    break;
                case 3:
                    instrucao();
                    break;
                case 4:
                    creditos();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }       
        inicioHistoria();
        //correrCasa();
        System.out.println("");      
        System.out.println("   _____             _   _                               \n" +
"  / ____|           | | (_)                              \n" +
" | |      ___  _ __ | |_ _ _ __  _   _  __ _             \n" +
" | |     / _ \\| '_ \\| __| | '_ \\| | | |/ _` |            \n" +
" | |____| (_) | | | | |_| | | | | |_| | (_| |  _   _   _ \n" +
"  \\_____|\\___/|_| |_|\\__|_|_| |_|\\__,_|\\__,_| (_) (_) (_)\n" +
"                                                         ");
        System.out.println("");
    }
    
}
