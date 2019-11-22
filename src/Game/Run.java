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
    //Salva a ultima cena
    static int cena = 0;
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
    public static void esperarTempo(float valor){
        int aux =(int) (valor * 1000);
        try {
            Thread.sleep(aux);
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
        esperarTempo(2);
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
    public static void armar(){
        System.out.printf("| %21s%5s\n","Nome da Arma","|");
        System.out.println("------------------------");
        System.out.printf("| Vida: %14s%2s\n",getVida()," |");
        System.out.printf("| Ataque: %10s%4s\n",getAtaque()," |");
        System.out.printf("| Defesa: %10s%4s\n",getDefesa()," |");
        System.out.printf("| Inteligência: %4s%4s\n",getInteligencia()," |");
        System.out.println("------------------------");
    }
    /**
     * 
     */
    //Capitulo 1
    public static void inicioHistoria(){
        System.out.println("A história começa...");
        personagem();
        armar();
        inicioCapituloUm();
        inicioCapituloDois();
    }
    
    /**
     * 
     */
    //1.0
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
    
    //1.1
    public static void investigarCasa(){
        cena = 2;
        String escolha;
        do{ 
            System.out.println("Caminho investigarCasa");
            System.out.println("Você deseja pegar o machado? ");
            System.out.println("A) Sim");
            System.out.println("B) Não");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           decisaoEstrada();
       }else{
           decisaoEstrada();
       }
    }
    
    //1.1.1
    public static void dentroCasa(){
        cena = 3;
        
    }
    //1.1.1.1 
    public static void decisaoEstrada(){
        cena = 4;
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
    //Caminho direito estrada;
    public static void direitoEstrada(){
        cena = 5;
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
    public static void acharRio(){
        cena = 6;
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
    public static void chegarCidade(){
        cena = 7;
        System.out.println("Chegando na cidade");
    }
    public static void investigarArbusto(){
        cena = 8;
        System.out.println("Investigando o arbusto");
        System.out.println("Luta");
        System.out.println("Ganhando vai para o rio");
    }
    
    //Caminho esquerdo estrada;
    public static void esquerdoEstrada(){
        cena = 9;
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
    
    public static void casaBarulho(){
        cena = 10;
        System.out.println("Casa com barulho");
        System.out.println("Dentro da casa acha suprimentos e \n"
                + "decide ir na outra casa");
        casaSemBarulho();
    }
    public static void casaSemBarulho(){
        cena = 11;
        System.out.println("Casa Sem Barulho");
        esperarTempo(3);
        System.out.println("Acha  um corpo e é atacado pelo povo da montanha");
        esperarTempo(2);
        System.out.println("escapa  e encontra um homem numa carruagem");
        esperarTempo(3);
        System.out.println("atacados por um bicho e corre para a cidade");
        chegarCidade();
    }
    //1.2
    public static void irParaEstrada(){
        cena = 12;
        String escolha;
        do{ 
            System.out.println("Caminho decisao casa ou estrada");
            System.out.println("Qual caminho deseja seguir? ");
            System.out.println("A) Correr para a Casa");
            System.out.println("B) Correr para a Estrada");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           correrCasa();
       }else{
           correrEstrada();
       }
    }
    
    //1.2.1.1//luta
    public static void correrCasa(){
        cena = 13;
        System.out.println("Correr para a Casa");
        System.out.println("Acha e pega um machado");
        System.out.println("Luta");
        lutar(inimigoUm, defendendoInimigoUm);
    }
    //1.2.1.2
    public static void esperarCasa(){
        cena = 14;
        String escolha;
        do{ 
            System.out.println("Espera em casa");
            System.out.println("Amanhece");
            decisaoEstrada();
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           
       }else{
           
       }
    }
    //1.2.1.2 
    public static void correrEstrada(){
        cena = 15;
        System.out.println("Correr para a Estrada");
        System.out.println("Acha um pau");
        System.out.println("Luta");
        lutar(inimigoUm, defendendoInimigoUm);
        decisaoEstrada();
    }
    
    /**
     * 
     */
    static void lutar(float[] inimigo, boolean inimigoDef){
         //Luta
        boolean lutando = true;
        //True Player, False Inimgo
        boolean turno = true;
        //
        inimigo[0] = 100;
        Random r = new Random();
        while(lutando){
            barraDeEstatos(inimigo);
            int leitor;
            if(turno){
                do{
                System.out.println("Sua Vez: 1- para Atacar, 2- para Defender");
                leitor = entrada.nextInt();
                    if(leitor == 1){
                    //Ataca
                        System.out.println("Atacando");
                        ataque(jogador, inimigo, inimigoDef );
                    }else if(leitor == 2){
                    //Defende
                        System.out.println("Defendendo");
                        defendendoJogador = true;
                    }
                }while(leitor != 1 && leitor != 2);
                turno =! turno;
            }else{
                System.out.println("Vez do Adiversario");
                esperarTempo(2);
                int resultado = r.nextInt(100);
                if(resultado < 24){
                    leitor = 2;
                }else{
                    leitor = 1;
                }
                if(leitor == 1){
                //Ataca
                    System.out.println("Adiversario Atacou");
                    ataque(inimigo,jogador, inimigoDef);
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
                System.out.println("Voce è um Perdedor, Comece de novo");
            }else if(inimigo[0] <= 0){
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
            esperarTempo(2);
        }//Fim Luta
    }
    //15 Cenas
    
    
    
    
    
    
    
    
    
    
    //Capitulo 2
    /**
     * 
     * @param args 
     */
    public static void inicioCapituloDois(){
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
    /**
     * 
     * @param args 
     */
    public static void investigarCorpo(){
        System.out.println("Escuta barulhos na casa e vai lá");
        System.out.println("quando chega proximo ve alguem fugindo pela\n"
                + " estrada e logo em seguida sendo morto");
        System.out.println("");
        levarPrisioneiros();
    }
    
    /**
     * 
     * @param args 
     */
    public static void buscarAjuda(){
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
    /**
     * 
     */
    public static void ajudarPersonagem(){
        System.out.println("todos vão juntos");
        levarPrisioneiros();
    }
    
    /**
     * 
     */
    public static void esconderCasa(){
        System.out.println("Personagens da casa se escondem");
        levarPrisioneiros();
    }
    /**
     * 
     */
    public static void levarPrisioneiros(){
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
    /**
     * 
     */
    public static void direitaLaboratorio(){
        System.out.println("Luta com uns guardas");
        System.out.println("Criar esses personagns para a luta e colocar a luta");
        sairLaboratorio();
    }
    /**
     * 
     */
    public static void esquerdaLaboratorio(){
        System.out.println("urso quebra a parede e pega os guardas sem luta");
        sairLaboratorio();
    }
    /**
     * 
     */
    public static void sairLaboratorio(){
        System.out.println("passa por uma sala e acha um mapa holografico com\n"
                + " a nave e contexto++");
        System.out.println("familia -1, foge e vai para a saída da cidade");
        System.out.println("ve o portao aberto começando a se fechar olha pro\n"
                + " lado ve que pegaram o lobo e corre ");
        System.out.println("");
        portao();
    }
    /**
     * 
     */
    public static void portao(){
        System.out.println("ATO 3");
    }
    
    public static void instrucao(){
        System.out.println("intrucao do jogo");
        
        System.out.println("Enter Para voltar ao menu");
        entrada.next();
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
            System.exit(0);
            return false;
        }else{
            return false;
        }    
    }
    
    public static void espacar(int linhas, boolean delay){
        for (int i = 0; i < linhas; i++) {
            if(delay){
                esperarTempo(0.2f);
            }
            System.out.println("");
        }
    }
    /**
     * 
     * @param args 
     */
    public static void main(String[] args) {
        //teste
        System.out.print("Carregando: ");
        for(int i = 0; i <10; i++){
            System.out.print("|");
            esperarTempo(0.3f);
        }
        espacar(10, true);
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
        System.out.println("Entre qualquer valor para continuar");
        entrada.next();
        espacar(10, true);
        
        boolean flag = true;
        while(flag){
            System.out.println("|-------------------------------------------|");
            System.out.println("|-------------------------------------------|");
            System.out.printf("|------------- %10s%20s\n", "","-------------|");
            System.out.printf("|------------- %10s%20s\n", "MENU","-------------|");
            System.out.printf("|------------- %10s%20s\n", "","-------------|");
            System.out.println("--------------------------------------------|");
            System.out.printf("|------------- %10s%20s\n", "","-------------|");
            System.out.printf("|------------- %4s%6s%20s\n", "1 -", "Jogar","-------------|");
            System.out.printf("|------------- %4s%6s%16s\n", "2 -", "Instruçoes","-------------|");
            System.out.printf("|------------- %4s%6s%19s\n", "3 -", "Credito","-------------|");
            System.out.printf("|------------- %4s%6s%20s\n", "4 -", "Sair","-------------|");
            System.out.printf("|------------- %10s%20s\n", "","-------------|");
            System.out.println("|-------------------------------------------|");
            System.out.println("|-------------------------------------------|");
            int escolha = entrada.nextInt();
            switch(escolha){
                case 1:
                    espacar(10, false);
                    inicioHistoria();
                    break;
                case 2:
                    instrucao();
                    break;
                case 3:
                    creditos();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
       
        inicioHistoria();
        //correrCasa();
        System.out.println("");
       
    }
    
}
