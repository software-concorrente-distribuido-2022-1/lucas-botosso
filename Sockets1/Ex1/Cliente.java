import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException {

		final int portaDefault = 80;
		int porta = portaDefault;
		Socket sock = null;
		Scanner sc = new Scanner(System.in);
		PrintWriter saida = null;
		BufferedReader entrada = null;
		String nomeHost = null;
		String nome = null;
		String cargo = null;
		double salario = 0.0;
		String mensagem = null;
		String linhaResposta = null;

		if ((args.length == 1) || (args.length == 2)) {
			nomeHost = args[0];
			if (args.length == 2) {
				porta = Integer.parseInt(args[1]);
			}
		} else {
			System.out.println("\n\nUso Correto: Cliente <NomeDoHost> [porta]\n\n");
			System.exit(1);
		}

		try {
			sock = new Socket(nomeHost, porta);
			saida = new PrintWriter(sock.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("\n\nHost nao encontrado!\n");
			System.out.println("nUso Correto: Cliente <NomeDoHost> [porta]\n\n");
			System.exit(1);
		} catch (java.io.IOException e) {
			System.err.println("\n\nConexao com Host nao pode ser estabelecida.\n");
			System.out.println("\nUso Correto: Cliente <NomeDoHost> [porta]\n\n");
			System.exit(1);
		}

		System.out.println("Digite o nome:");
		nome = sc.nextLine();
		System.out.println("Digite o cargo:");
		cargo = sc.nextLine();
		System.out.println("Digite o salario:");
		salario = sc.nextDouble();
		mensagem = nome + "," + cargo + "," + String.valueOf(salario);

		saida.println(mensagem);

		System.out.println("\nResposta do Host:\n");
		linhaResposta = entrada.readLine();
		while (linhaResposta != null) {
			System.out.println(linhaResposta);
			linhaResposta = entrada.readLine();
		}

		sc.close();
		saida.close();
		entrada.close();
		sock.close();
	}
}