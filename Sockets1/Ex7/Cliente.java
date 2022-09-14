import java.io.*; 
import java.net.*; 

public class Cliente {
    public static void main(String[] args) throws IOException {

        

        final int portaDefault = 80; 

        String nomeHost = null; 

        String idade = null;
        String tempoServico = null;

        int porta = portaDefault; 

        Socket sock = null; 

        PrintWriter saida = null;
        BufferedReader entrada = null;
        String linhaResposta = null; 
        

        if ((args.length == 3) || (args.length == 4)) {
            nomeHost = args[0]; 
            idade = args[1];
            tempoServico = args[2];
            if (args.length == 4) {
                porta = Integer.parseInt(args[3]);
            }
            
        } else { 
            System.out.println("\n\nUso Correto: Cliente NomeDoHost Idade TempoServico [porta]\n\n");
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

        

        saida.println(idade);
        saida.println(tempoServico);

        

        System.out.println("\nResposta do Host:\n");
        linhaResposta = entrada.readLine();
        while (linhaResposta != null) {
            System.out.println(linhaResposta);
            linhaResposta = entrada.readLine();
        }

        

        sock.close();
    }
}