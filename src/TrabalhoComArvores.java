import com.opencsv.CSVReader;
//import pom.xml; 

import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TrabalhoComArvores {
    public static void main(String[] args) {

        long tempoInicial;//variável para iniciar o tempo em ms
        long tempoFinal;// variável pra finalizar o tempo em ms

        System.out.println("execução iniciada");
        tempoInicial = System.currentTimeMillis();//inicia o tempo

        Arvore arvorePassword = new Arvore();
        Arvore arvoreLength = new Arvore();
        Arvore arvoreDate = new Arvore();

    
        //path dos arquivos

            //path e nome do arquivo original para começar a execução
        String arquivocsv = "passwords.csv";

            //path e nome do arquivo final, gerado ao terminar a execução
        String ArquivoSenhasCSV = "arquivos/PasswordOrdenada.csv";
        String ArquivoLengthCSV = "arquivos/LengthOrdenada.csv";
        String ArquivoDateCSV = "arquivos/DateOrdenada.csv";


        CSVReader reader = null;
        try {
            //abre o csv
            reader = new CSVReader(new FileReader(arquivocsv));
            String[] nextLine; //armazena linhas do CSV

            nextLine = reader.readNext(); //pula a primeira linha para evitar erro

            //Lê uma linha por vez e adiciona à fila na posição ja ordenada
            while ((nextLine = reader.readNext()) != null) {
                arvorePassword.inserirPassword(new Dado(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[1].length(), nextLine[3]));
                arvoreLength.inserirLength(new Dado(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[1].length(), nextLine[3]));
                arvoreDate.inserirDate(new Dado(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[1].length(), nextLine[3]));
            }
        }
        catch (FileNotFoundException e){// exceções 
          System.out.println("Arquivo não encontrado: \n" + e.getMessage());// exceções 
        }catch (ArrayIndexOutOfBoundsException e){// exceções 
            System.out.println("Indice não encontrado: \n" + e.getMessage());// exceções 
        }catch( IOException e){// exceções 
            System.out.println("IO Erro: \n" + e.getMessage());// exceções 
        }finally {
          if(reader != null){
              try{
                reader.close();//fecha o csv
                }catch(IOException e){
                  System.out.println("IO Erro: \n" + e.getMessage());// exceções
                }
            }
        }

        try {
            arvorePassword.criaCSV(ArquivoSenhasCSV);
            arvoreLength.criaCSV(ArquivoLengthCSV);
            arvoreDate.criaCSV(ArquivoDateCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tempoFinal = System.currentTimeMillis()-tempoInicial;

        System.out.println("execução terminada em: "+ tempoFinal+" ms");

    }
    public static void mergeSort(Arvore entrada,String[] vetor2, int[] vetor, String[] vetor3) {
	System.out.println("começando o merge: ");
		int tamanhoarray = vetor.length;
        int dividirarray = tamanhoarray / 2;
        int arraydaesquerdaindice[] = new int[dividirarray];
        int arraydadireitaindice[] = new int[tamanhoarray - dividirarray];
        String arraydaesquerdasenha[] = new String[dividirarray];
        String arraydadireitasenha[] = new String[tamanhoarray - dividirarray];
        int arraydaesquerda[] = new int[dividirarray];
        int arraydadireita[] = new int[tamanhoarray - dividirarray];
        String arraydaesquerdadata[] = new String[dividirarray];
        String arraydadireitadata[] = new String[tamanhoarray - dividirarray];
        if (tamanhoarray < 2) {
            return;
        }
        for (int i = 0; i < dividirarray; i++) {
            arraydaesquerdaindice[i] = vetor0[i];
            arraydaesquerdasenha[i] = vetor2[i];
            arraydaesquerda[i] = vetor[i];
            arraydaesquerdadata[i] = vetor3[i];
        }
        for (int j = dividirarray; j < tamanhoarray; j++) {
            arraydadireitaindice[j-dividirarray] =vetor0[j];
            arraydadireitasenha[j-dividirarray] = vetor2[j];
            arraydadireita[j - dividirarray] = vetor[j];
            arraydadireitadata[j-dividirarray]=vetor3[j];
        }
        mergeSort(arraydaesquerdaindice, arraydaesquerdasenha, arraydaesquerda,arraydaesquerdadata);
        mergeSort(arraydadireitaindice,arraydadireitasenha,arraydadireita,arraydadireitadata);
        juntandomerge(vetor0,arraydaesquerdaindice,arraydadireitaindice,
        vetor2,arraydaesquerdasenha,arraydadireitasenha,
        vetor, arraydaesquerda, arraydadireita,
        vetor3,arraydaesquerdadata,arraydadireitadata);
        System.out.println("terminando o merge");
	}
	//algoritmo juntando o merge (junta os arrays criados)
	public static void juntandomerge(int[]vetor0,int[] arraydaesquerdaindice,int[]arraydadireitaindice ,
    String[] vetor2,String arraydaesquerdasenha[],String arraydadireitasenha[],
    int entrada[], int arraydaesquerda[], int arraydadireita[], 
    String[] vetor3,String arraydaesquerdadata[],String arraydadireitadata[]) {
        int tamanhoarrayesquerda = arraydaesquerda.length;
        int tamanhoarraydireita = arraydadireita.length;
        int a = 0;
        int b = 0;
        int c = 0;
        while (a < tamanhoarrayesquerda && b < tamanhoarraydireita) {
            if ((arraydaesquerda[a]) >= (arraydadireita[b])) {
                vetor0[c]=arraydaesquerdaindice[a];
                vetor2[c] = arraydaesquerdasenha[a];
                entrada[c] = arraydaesquerda[a];
                vetor3[c] = arraydaesquerdadata[a];
                a = a + 1;
            } else {
                vetor0[c] = arraydadireitaindice[b];
                vetor2[c] = arraydadireitasenha[b];
                entrada[c] = arraydadireita[b];
                vetor3[c] = arraydadireitadata[b];
                b = b + 1;
            }
            c = c + 1;
        }
        while (a < tamanhoarrayesquerda) {
            vetor0[c] = arraydaesquerdaindice[a];
            vetor2[c] = arraydaesquerdasenha[a];
            entrada[c] = arraydaesquerda[a];
            vetor3[c] = arraydaesquerdadata[a];
            a = a + 1;
            c = c + 1;
        }
        while (b < tamanhoarraydireita) {
            vetor0[c] = arraydadireitaindice[b];
            vetor2[c] = arraydadireitasenha[b];
            entrada[c] = arraydadireita[b];
            vetor3[c] = arraydadireitadata[b];
            b = b + 1;
            c = c + 1;
        }

    }
}