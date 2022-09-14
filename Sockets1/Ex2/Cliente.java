import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {

        final int portaDefault = 80; 

        String nomeHost = null;

        String nome = null;
        String sexo = null;
        String idade = null;

        int porta = portaDefault;
        Socket sock = null;

        PrintWriter saida = null; 
        BufferedReader entrada = null;
        String linhaResposta = null; 


        if ((args.length == 4) || (args.length == 5)) {
            nomeHost = args[0];
            nome = args[1];
            sexo = args[2];
            idade = args[3];
            if (args.length == 5) {
                porta = Integer.parseInt(args[4]);
            }
            
        } else { // Fornecimento erroneo dos argumentos
            System.out.println("\n\nUso Correto: Cliente NomeDoHost Nome Sexo Idade [porta]\n\n");
            System.exit(1);
        }

        try {
            sock = new Socket(nomeHost, porta);
            

            saida = new PrintWriter(sock.getOutputStream(), true);          
            

            entrada = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("\n\nHost nao encontrado!\n");
            System.out.println("\nUso: Cliente NomeDoHost mensagem [porta]\n\n");
            System.exit(1);
        } catch (java.io.IOException e) {
            System.err.println("\n\nConexao com Host nao pode ser estabelecida.\n");
            System.out.println("\nUso: Cliente NomeDoHost mensagem [porta]\n\n");
            System.exit(1);
        }

        

        saida.println(nome);
        saida.println(sexo);
        saida.println(idade);

        

        System.out.println("\nResposta do Host:\n");
        linhaResposta = entrada.readLine();
        while (linhaResposta != null) {
            System.out.println(linhaResposta);
            linhaResposta = entrada.readLine();
        }

        

        sock.close();
    }
}