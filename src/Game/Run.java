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
    static float[] inimigoUm = {50f,3f,3f,1f, 50f};
    static boolean defendendoInimigoUm = false;
    
    //Inimigo DOIS !
    static float [] inimigoDois = { 75f,3f,3f,2f,75f};
    static boolean defendendoInimigoDois = false;
    
    //Inimigo TRES !
    static float []inimigoTres = {100f,4f,4f,3f,100f};
    static boolean defendendoInimigoTres = false;
    
    //Inimigo Quatro !
    static float []inimigoQuatro = {125f,5f,5f,3f,125f};
    static boolean defendendoInimigoQuatro = false;
    
    //Inimigo CINCO !
    static float []inimigoCinco = {150f,6f,5f,4f,150f};
    static boolean defendendoInimigoCinco = false;
    
    //Inimigo SEIS !
    static float []inimigoSeis = {175f,7f,6f,5f,175f};
    static boolean defendendoInimigoSeis = false;
    //---------------------------------
    //Inimigo SETE !
    static float []inimigoSete = {100f,3f,3f,2f,100f};
    static boolean defendendoInimigoSete = false;
    
    //Inimigo OITO !
    static float []inimigoOito = {100f,3f,3f,2f,100f};
    static boolean defendendoInimigoOito = false;
    
    //Inimigo NOVE !
    static float []inimigoNove = {100f,3f,3f,2f,100f};
    static boolean defendendoInimigoNove = false;
    
    // BOOOSSSSSS !!!!!!!!!!
    static float []boss = {100f,3f,3f,2f,100f};
    static boolean defendendo_boss = false;
    
    //Armas
    //[0] = vida, [1] = Ataque, [2] = Defesa, [3] = inteligencia, [4] = MaxVida
    static float[] machado = {5,4,2,2,5};
    static float[] facao = {3,3,2,4,3};
    static float[] enxada = {4,4,7,6,4};
    static float[] chaveGrifo = {7,6,1,2,7}; 
    static float[] graveto = {1,1,1,1,1};
    static String nomeArma;
    
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
    
    //incrementar atributos
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
        //[3] = inteligencia
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
                System.out.println("Sua Vez:\n 1- para Atacar,\n 2- para Defender");
                System.out.print(" = ");
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
                System.out.println("Você ganhou uns atributos por derrotá-lo.\n");
                setVida(50);
                setAtaque(1);
                setDefesa(1);
                setInteligencia(1);
                personagem();
                
            }
            esperarTempo(1);
        }//Fim Luta
    }

    static String mensagemDerrota(){
        List palavras = new ArrayList();
        palavras.add("Voce foi derrotado");
        palavras.add("Não foi dessa vez :/ ");
        palavras.add("x_x");
        palavras.add("");
        Collections.shuffle(palavras);
        return ""+ palavras.get(0);
    }
    
    static String mensagemVitoria(){
        List palavras = new ArrayList();
        palavras.add("Essa foi por pouco");
        palavras.add("Você conseguiu!");
        palavras.add("Ufa >~<");
        Collections.shuffle(palavras);
        return ""+ palavras.get(0);
    }

    //Descricao do personagem e suas habilidades
    static void personagem(){
        System.out.println("Habilidades do seu personagem: ");
        System.out.println("------------------------");
        System.out.printf("| Vida: %14s%2s\n",getVida()," |");
        System.out.printf("| Ataque: %10s%4s\n",getAtaque()," |");
        System.out.printf("| Defesa: %10s%4s\n",getDefesa()," |");
        System.out.printf("| Inteligência: %4s%4s\n",getInteligencia()," |");
        System.out.println("------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        esperarTempo(3.5f);
    }
     
    static void barraDeStatus(float[] inimigo){
        esperarTempo(1);
        System.out.println("\n\n\n");
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
        
        System.out.println("Você é livre para fazer suas escolhas, mas é \n"
                         + "prisioneiro das consequências");
        System.out.printf("%44s ", "- Pablo Neruda");
        System.out.println("");
        esperarTempo(4f);
        System.out.println("");
        System.out.println("");
        personagem();
        //armar();
        inicioCapituloUm();
        inicioCapituloDois();
        inicioCapituloTres();
        
    }
    

    //         INICIO!!!!!!!!!!!!
    
    //Ato 1 - The Fall (A Queda)
    public static void inicioCapituloUm(){
        esperarTempo(1);
        cena = 1;
        String escolha;
        System.out.println("... Você acorda. Olha em volta. Sua visão se embaça,\n"
                + " mas aos poucos ela se recupera. E então percebe que está em \n"
                + " um celeiro deitado numa.... Não se sabe o que, mas algo que se \n"
                + "assemelha a uma cama ou cápsula que está quebrada. Logo em \n"
                + "seguida você sai do celeiro e olha para o horizonte, a terra \n"
                + "está coberta de plantação de trigos dando uma coloração ama- \n"
                + "relada do campo, porém, há algo de errado com este lugar você\n"
                + " não sabe ao certo o que exatamente mas sente que algo está \n"
                + "fora do padrão. É então que avista uma casa grande e aparen- \n"
                + "temente vazia e mais à frente uma saída para estrada. ");
        do{ 
            System.out.println("");
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
        esperarTempo(1);
        cena = 2;
        String escolha;
        System.out.println("Você entra na casa e nota que está vazia. Não se\n"
                    + " vê nada além de móveis empoeirados.  Então caminha pela \n"
                    + "casa à procura de ajuda, mas não se vê ou ouve nada. A \n"
                    + "casa se assemelha uma fazenda do interior, grande, espa- \n"
                    + "çosa e fácil de se perder. É quando então você tropeça \n"
                    + "num objeto.  É um machado, levemente rachado.");
        do{ 
            System.out.println("");
            System.out.println("Você deseja pegar o machado? ");
            armar(machado, "Machado");
            System.out.println("A) Sim");
            System.out.println("B) Não");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           armaEquipada = machado;
           nomeArma = "Machado";
           pegaMachado();
       }else{
           naoMachado();
       }
    }
   
    // 1 - 1.
    public static void pegaMachado(){
        esperarTempo(1);
        System.out.println("Após pegar o machado você continua a investigar a \n"
                + "casa.  Depois de um certo período de tempo você sai dela e \n"
                + "vai em direção a estrada. Você olha para a direita e vê umas \n"
                + "estrada longa com poucas árvores, porém avista um pequeno \n"
                + "ponto no final dela. E então olha para esquerda e vê algo que\n"
                + " assemelha à uma floresta densa e uma carniça apodrecendo de \n"
                + "algum animal morto a pouco tempo.");
        
        personagem();
        //barraDeStatus(inimigoUm);
        decisaoEstrada();
    }
    
    // 1 - 2.
    public static void naoMachado(){
        esperarTempo(1);
        System.out.println("Seguindo os caminhos da casa você percebe que não \n"
                + "há nada nela, além da bagunça em que se encontra. E então re- \n"
                + "solve ir para fora. Você sai e olha ao seu redor, a direita \n"
                + "se vê uma estrada longa com poucas árvores, porém avista uma \n"
                + "montanha no final dela. E então olha para esquerda e vê algo \n"
                + "que assemelha à uma floresta densa...");
        decisaoEstrada();
    }
    
    // 2.
    public static void irParaEstrada(){
        esperarTempo(1);
        cena = 3;
        String escolha;
            System.out.println("Você segue pela estrada observa o cenário da-\n"
                    + "quele lugar. Ainda meio confuso e com a visão um pouco \n"
                    + "embaçada, enxerga ao longo do horizonte árvores que se \n"
                    + "assemelham a uma floresta densa, e, ao colocar os olhos \n"
                    + "de volta a estrada observa uma silhueta de um animal co-\n"
                    + "mendo algo que não se sabe ao certo o que era. Ele parece\n"
                    + " estar faminto e brutalmente devora o que está em sua \n"
                    + "frente. Aos poucos você tenta se aproximar, mas ele ouve \n"
                    + "os seus passos e começa a te encarar. Seu semblante de-\n"
                    + "monstra gradualmente uma expressão de fúria. Vocês se en-\n"
                    + "caram por um breve período. Você não entende o porquê \n"
                    + "aquela reação e começa a se afastar.");
            System.out.println("");
            System.out.println("O animal começa a andar em sua direção. E cada \n"
                    + "vez mais ele aumenta os passos. A criatura te observa tão\n"
                    + "fixamente que voce tropeça e nessa hora ele parte pra cima\n"
                    + "de voce e então você corre");
        do{ 
            System.out.println("");
            System.out.println("Qual caminho deseja correr? ");
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
        esperarTempo(1);
        cena = 4;
        System.out.println("Desesperadamente você entra na casa e tranca. Logo \n"
                + "menos ele está empurrando a porta tentando derrubá-la. Com \n"
                + "medo de que ele consiga, você procura um lugar para se esconder.\n"
                + "Olhando ao redor dessa casa grande, não se encontra ninguém e \n"
                + "só se depara com móveis empoeirados. Você ouve o barulho de um \n"
                + "vidro se quebrar e corre para abrir qualquer porta a sua frente.\n"
                + "Tenta uma, nada. A segunda sem sucesso, você quebra a maçaneta.\n"
                + "Finalmente na terceira você consegue abrir e dá de cara com o \n"
                + "que seria um porão. Você acende as luzes e olha a sombra da \n"
                + "criatura por baixo da porta. Então procura algo para tentar se\n"
                + " defender. Você encontra uns porta-retratos antigos de uma \n"
                + "família que parecia morar ali. Caixas e mais caixas por todo \n"
                + "o canto. Você vasculha nelas e acha uma caixa com ferramentas\n"
                + " antigas nas quais se encontram: um machado uma Chave Grifo");
        String escolha;
        do{ 
            System.out.println(""); 
            esperarTempo(1);
            armar(machado, "Machado");
            esperarTempo(1);
            armar(chaveGrifo, "Chave Grifo");
            esperarTempo(1);
            System.out.println("Qual arma pegar?");
            System.out.println("A) Machado");
            System.out.println("B) Chave Grifo");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
                armaEquipada = machado;
                nomeArma = "Machado";      
       }else {
                armaEquipada = chaveGrifo;
                nomeArma = "Chave Grifo";
       }
        System.out.println("Você pegou "+nomeArma);
        System.out.println("O animal quebra a porta e parte em sua direção.");
        System.out.println("Vocês começam a lutar");
        lutar(inimigoUm, defendendoInimigoUm);
        decisaoEstrada();
    }
    
    // 2 - 2.
    public static void correrPelaEstrada(){
        esperarTempo(1);
        cena = 5;
        System.out.println("Correr para a Estrada");
        System.out.println("Acha um graveto");
        armar(graveto, "Graveto");
        armaEquipada = graveto;
        System.out.println("Luta");
        lutar(inimigoUm, defendendoInimigoUm);
        System.out.println("Após matar aquela criatura, você se levanta e olha ao\n"
                         + "seu redor e vê a estrada, a direita se vê uma estrada\n"
                        + "longa com poucas árvores, porém avista uma montanha no\n"
                        + "final dela. E então olha para esquerda e vê algo que \n"
                        + "assemelha à uma floresta densa...");
        decisaoEstrada();
    }
    
    // 3
    public static void decisaoEstrada(){
        esperarTempo(1);
        cena = 6;
        String escolha;
        System.out.println("");
        do{ 
            System.out.println("");
            System.out.println("Para qual lado deseja ir? ");
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
        esperarTempo(1);
        cena = 7;
        System.out.println("Você caminha em direção a floresta. A cada passo\n"
                    + " que dá uma nevóa se intensifica, dificultando sua visão\n"
                    + "Aos poucos o ambiente fica tomado pela névoa, onde só o \n"
                    + "que se vê são silhuetas do que estava aos eu redor. Você \n"
                    + "continua caminhando. Até que em um momento você ouve um \n"
                + "barulho vindo de algum lugar perto de você.");
        System.out.printf("- shttyr -");
        System.out.println("Com o tempo o som se intensifica e de canto de olho \n"
                + "você observa um arbsuto se mexer.");

        String escolha;
        do{ 
            System.out.println("");
            System.out.println("o que deseja fazer? ");
            System.out.println("A) Investigar");
            System.out.println("B) Seguir em frente");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           investigarArbusto();
       }else{
            System.out.println("Seguindo em frente você percebe que está sem rumo\n"
                   + "e perdido. As coisas ainda não fazem sentido. Até que você\n"
                   + "ouve um som de água e resolve ir atrás. É um rio. ");

           acharRio();
           
       }
    }
    
    // 3 - 1 - 1.
    public static void acharRio(){
        esperarTempo(1);
        cena = 8;
        System.out.println("Após encontrar o rio você se agacha e bebe a \n"
                    + "agúa.A corrente deste rio está forte O dia está acabando\n"
                    + " e a névoa se esmaece dando espaço para a escuridão, que \n"
                    + "lentamente cobre o mundo. Continuando a caminhar você avista\n"
                    + " um pequeno barco no canto do rio. Ao se aproximar, percebe\n"
                    + " que ele está sujo e com furos, aparentemente não confiável.\n"
                    + " De repente você ouve um barulho alto vindo de trás. ");

        String escolha;
        do{ 
            System.out.println("o que deseja fazer? ");
            System.out.println("A) Atravessar");
            System.out.println("B) Entrar no barco");
            escolha = entrada.next();
        }while(!validaentrada(escolha));
       if(escolha.equalsIgnoreCase("A")){
           System.out.println("");
           System.out.println("Então você opta por atravessar o rio. A corrente\n"
                   + "forte dificulta sua passagem. Você chega quase na metade.\n"
                   + "Até que a corrente lhe atinge brutalmente, assim te levando\n"
                   + "corrente abaixo. Você consegue se segurar em umas rochar \n"
                   + "por um momento, até que você ser acertado por um tronco velho\n"
                   + "e logo em seguida desmaiando.");
           
           chegarCidade();
       }else{
           System.out.println("Você empurra o barco. O som se aproxima cada vez\n"
                   + "mais. Então você entra no barco e segue o rio. Descendo o\n"
                   + "rio Você sente algo batendo no barco, de repente surge uma\n"
                   + " criatura do rio. Ele te ataca, se agarrando no seu rosto,\n"
                   + "você o joga para longe mas a critura não sai do barco."); 
           System.out.println("");
           System.out.println("LUTA");
           lutar(inimigoTres, defendendoInimigoTres);
           System.out.println("");
           System.out.println("Exausto você acaba adormecendo. Quando acorda já\n"
                   + " está de manhã e o mundo que viu antes parece ter desapare-\n"
                   + "cido, o sol raiava e a vista estava mais clara. Você desce\n"
                   + " do barco e percebe que há algo de estranho atrás das árvo-\n"
                   + "res. Então você passa por elas e olha uma doma enorme rode-\n"
                   + "ado por um campo livre. E percebe que existe algo dentro \n"
                   + "desta doma... é uma cidade.");
           chegarCidade();
       }
    }
    
    // 3 - 1 - 2.
    public static void investigarArbusto(){
        cena = 9;
        System.out.println("Você se aproxima dos arbustos, onde pareçe estar se \n"
                + "mexendo cada vez mais. Atravessando-o você não avista nada \n"
                + "além de árvores cobertas pela névoa. Quando de repente você \n"
                + "é empurrado para longe por alguma coisa. Essa coisa se asse-\n"
                + "melha à um javali, porém maior e mais robusto. Este animal \n"
                + "não é normal há algo de errado com ele, mas antes que você \n"
                + "possa analisar-lo primeiro, a coisa corre em sua direção,\n"
                + "assim o atacando.");
        
        System.out.println("Luta");
        lutar(inimigoDois, defendendoInimigoDois);
        System.out.println("Após finalmente matá-lo, você recupera as forças e \n"
                + "continua em frente. Pouco tempo depois você encontra um rio.");
        acharRio();

    }
    
    //-------------------------------------------------------
    
    
    //CAMINHO ESQUERDO DA ESTRADA --------------------------------------------;
    // 3 - 2.
    public static void esquerdoEstrada(){
        cena = 10;
        System.out.println("Você começa a andar em direção a uma montanha.\n"
                + "Ao se aproximar percebe que há um vilarejo por perto e \n"
                + "segue até chegar nele. O vilarejo está rodeado de árvores\n"
                + "e o clima de pôr do sol dá um ar de traquilidade.\n"
                + "Você chega no vilarejo no inicio da noite."
                + "Continua caminhando observando o local. Um lugar silencioso,\n"
                + "a principio. Casas e trailer por perto. Você busca por ajuda\n"
                + "mas ninguem responde. Bate em uma das casas. Nada. Você então\n"
                + "decide entrar em uma delas. Inicialmente a casa está vazia,\n"
                + "cheia de poeira e mesmo o local iluminado não se encontra \n"
                + "nenhuma pessoa. Abandonado. Você houve um barulho em uma casa\n"
                + "lá fora.");
        String escolha;
        do{ 
            System.out.println("");
            System.out.println("O que deseja fazer?");
            System.out.println("A) Investigar o barulho");
            System.out.println("B) Permanecer");
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
        System.out.println("");
        System.out.println("Toc,Toc!");
        System.out.println("Nenhuma movimentação.");
        System.out.println("Toc,Toc!");
        System.out.println("Nada. Você decide forçar a entrada. No mesmo instante\n"
                + "o som acaba. A casa se assemelha a outra sem nenhuma pessoa.\n"
                + "Porta-retratos e os moveis organizados. Parece que alguém vivia\n"
                + "aqui. Você dá uma olhada pela casa e não encontra nada de \n"
                + "relevante que te faça lembrar do que está acontecendo. A luz\n"
                + "da casa em que estava se apaga. E você volta para ver se en-\n"
                + "contra alguém.");
        
        casaSemBarulho();
    }
    
    // 3 - 2 - 2.
    public static void casaSemBarulho(){
        cena = 12;
        System.out.println("Uma das janelas se quebra. Você encontra um corpo jo-\n"
                + "gado no chão. Ótimo uma pessoa! Você se aporxima para ver\n"
                + "se ela está bem. Logo menos você se depara com um grupo de pes-\n"
                + "soas te atacando. Elas parecem irritadas com algo. Você é acer-\n"
                + "tado na cabeça por um objeto, desmaiando.");
        System.out.println("/////////////////////////////////////");
        System.out.println("");
        System.out.println("");
        esperarTempo(2);
        System.out.println("Após um tempo você acorda.");
        esperarTempo(1);
        System.out.println("Você: Quem são vocês???");
        esperarTempo(2);
        System.out.printf("%50s :Estranho", "...");
        esperarTempo(1);
        System.out.printf("\n%50s :Estranho"," Você é um deles?");
        esperarTempo(2);
        System.out.println("\nVocê: Um deles? De quem você está falando??");
        esperarTempo(2);
        System.out.printf("\n%50s :Estranho","Estamos fugindo Deles. Eles são perigosos!");
        esperarTempo(2);
        System.out.println("\n\n Houve-se um barulho de carro se aproximando.\n"
                + "Essa 'família' um tanto como apavorada vão investigar eles.\n"
                + "Aproveitando a oportunidade você tenta se soltar. Houve uns\n"
                + "tiros de espingarda e gritos de desespero. Um seguido do \n"
                + "outro. Os gritos encerram. Seu coração começa a acelerar na\n"
                + "medida em que houve passos em sua direção. Você se solta e\n"
                + "se esconde. Olha para a porta e vê um homem parado na frente\n"
                + "dela. O homem caminha em busca de algo. Você, ao perceber que\n"
                + "ele está de costas, tenta fugir pela janela. Você faz um gru-\n"
                + "nhindo de dor ao se cortar nos cacos de vidros restantes na \n"
                + "janela.");
        esperarTempo(2);
        System.out.printf("%43s :Homem Misterioso", "Quem está ai?");
        esperarTempo(1);
        System.out.printf("%43s :Homem Misterioso", "Calma, não se preocupe.");
        System.out.println("");
        System.out.printf("%43s :Homem Misterioso", "Eu não sou como Eles.");
        esperarTempo(2);
        System.out.println("");
        
        System.out.println("Você: ...\n");
        System.out.println("Jogado no chão você avista outras pessoas na casa \n"
                + "ao lado. Se levanta avistando um carro e, assim, começa a cor-\n"
                + "rer em direção a ele em busca de fugir dessas pessoas. Você \n"
                + "entra no carro tentando dar partida.Tenta uma, nada. Tenta\n"
                + "a segunda, nada. Enquanto isso os homens estranhos começam \n"
                + "a ir em sua direção disparando tiros na busca de impedi-lo\n"
                + "Tenta a terceira e finalmente consegue ligar. Eles conseguem \n"
                + "quebar o vidro de tras do carro e você acelera loucamente \n"
                + "seguindo uma direção desconhecida. ");
        System.out.println("Se afastando daquelas pessoas você fica mais alivi-\n"
                + "ado. Até que de repente você avista algo no meio da estrada\n"
                + "e vira bruscamente, desviando daquilo, assim, perdendo o con-\n"
                + "trole e batendo o carro numa árvore, consequentemente desmai-\n"
                + "ando...");
        System.out.println("Acordando você se sente confuso e desnorteado. Você \n"
                + "volta para a estrada e segue chaminho. Em um certo ponto você\n"
                + "avista algo atrás das árvores. Atravessando-as avista uma campo\n"
                + "livre onde no meio se encontra uma doma enorme. Você se aproxima\n"
                + "e percebe que existe algo dentro desta doma... é uma cidade.");
        chegarCidade();
    }
    
    //----------------------------------------------------
    

    // 4.
    public static void chegarCidade(){
        cena = 13;
        System.out.println("Se aproximando da doma a cidade fica cada vez mais \n"
                + "nítida. A cidade é incrivelmente grande. Você se aproxima ca-\n"
                + "da vez mais até ficar cara a cara com a doma. Você estende sua\n"
                + "mão para tocá-la quando de repente se ouve um barulho estron-\n"
                + "doso, logo em seguida uma forte rajada de luz lhe atinge, a \n"
                + "doma estava se abrindo até que...");

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
    
    // 8
    public static void FinalFicarDois(){
        cena = 42;
        System.out.println("Ambos deixam a nave e vão ir de frente para o vilão...");
        System.out.println("Fim...");
    }
    
    
    
    
    
    
    public static void creditos(){
        System.out.println("");
        System.out.println("Infinity Odissey é um projeto do 1° Semestre dos a-\n"
                         + "lunos do curso de 'Análise e Desenvolvimento de Sis-\n"
                         + "temas' do Centro Universitário Senac - Santo Amaro e\n"
                         + "foi desenvovido pelos alunos:\n");
        System.out.println(" - Gabriel Enrique\n");
        System.out.println(" - Lucas Cardoso\n");
        System.out.println(" - Rogerio Lucon\n");
        System.out.println("Versao: 1.0.85\n");
        System.out.println("Digite qualquer numero para voltar menu.");
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
    
    public static void espacar(int linhas, boolean delay){
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
            String arma = bf.readLine();
            
            if(!arma.equalsIgnoreCase("NULL")){
                carregaArma(arma);
            }
            fr.close();
            bf.close();
            personagem();
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
            gravarArq.println(nomeArma);
            
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
    public static void carregaArma(String arma){
        switch(arma.toLowerCase()){
            case "machado":
                armaEquipada = machado;
                nomeArma = "machado";
                break;
            case "facao":
                armaEquipada = facao;
                nomeArma = "facao";
                break;
            case "enxada":
                armaEquipada = enxada;
                nomeArma = "enxada";
                break;
            case "chaveGrifo":
                armaEquipada = chaveGrifo;
                nomeArma = "chave grifo";
                break;
            case "graveto":
                armaEquipada = graveto;
                nomeArma = "Graveto";
                break;
        }
    }
    
    public static void instrucao(){
        System.out.println("Intruções do jogo:");
        
        System.out.println("");
        System.out.println("- Digite 'Sair/Exit' para sair do jogo\n");
        System.out.println("- Digite 'Carregar' para carregar ultimo jogo salvo\n");
        System.out.println("- Digite 'Save' para salvar o jogo\n");
        System.out.println("- BATALHAS: Cada oponente tem seu turno, na qual de-\n"
                         + "cidirá se irá defender ou atacar. O dano causado se-\n"
                         + "rá sorteado com base na defesa e inteligência.\n");
        System.out.println("- ESCOLHAS: No jogo haverá perguntas onde você deve-\n"
                         + "rá escolher que caminho seguir, onde cada caminho \n"
                         + "terá consequências diferentes.\n");
        System.out.println("***Aperte qualquer número para voltar ao menu***");
        entrada.next();
    }
    
    public static void main(String[] args) {
        System.out.print("Carregando: ");
        for(int i = 0; i <10; i++){
            System.out.print("█");
            esperarTempo(0.4f);
        }
        System.out.print(" 100%");
        espacar(5, true);
        // Imagem Inicial
        System.out.println("  _____        __ _       _ _         \n" +
            " |_   _|      / _(_)     (_) |        \n" +
            "   | |  _ __ | |_ _ _ __  _| |_ _   _ \n" +
            "   | | | '_ \\|  _| | '_ \\| | __| | | |\n" +
            "  _| |_| | | | | | | | | | | |_| |_| |\n" +
            " |_____|_| |_|_| |_|_| |_|_|\\__|\\__, |\n" +
            "                                 __/ |\n" +
            "                                |___/ ");
        esperarTempo(1);
        System.out.println("   ____      _                          \n" +
            "  / __ \\    | |                         \n" +
            " | |  | | __| |_   _ ___ ___  ___ _   _ \n" +
            " | |  | |/ _` | | | / __/ __|/ _ \\ | | |\n" +
            " | |__| | (_| | |_| \\__ \\__ \\  __/ |_| |\n" +
            "  \\____/ \\__,_|\\__, |___/___/\\___|\\__, |\n" +
            "                __/ |              __/ |\n" +
            "               |___/              |___/ ");

        espacar(5, true);
        
        boolean flag = true;
        while(flag){
            System.out.println("    Bem vindo ao Infinity Odyssey!");
            System.out.println("    Escolha o que deseja fazer");
            System.out.println("______________________________________________");
            System.out.printf("|------------- %11s%21s\n", "","-------------|");
            System.out.printf("|------------- %11s%21s\n", "MENU","-------------|");
            System.out.printf("|------------- %11s%21s\n", "","-------------|");
            System.out.println("|_____________________________________________|");
            System.out.printf("|------------- %4s%6s%19s\n", "1 -", "Novo Jogo","----------|");
            System.out.printf("|------------- %4s%6s%15s\n", "2 -", "Carregar Jogo","----------|");
            System.out.printf("|------------- %4s%6s%18s\n", "3 -", "Instruçoes","----------|");
            System.out.printf("|------------- %4s%6s%21s\n", "4 -", "Credito","----------|");
            System.out.printf("|------------- %4s%6s%22s\n", "5 -", "Sair","----------|");
            System.out.println("_______________________________________________");
            System.out.println("");
            System.out.print(" = ");
            int escolha = entrada.nextInt();
            
            switch(escolha){
                case 1:
                    espacar(10, false);
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
