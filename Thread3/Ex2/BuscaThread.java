public class BuscaThread extends Thread {
	private int inicio, fim, valor, indice = -1;
	private int[] vetor;

	public BuscaThread(int inicio, int fim, int valor, int[] vetor) {
		this.inicio = inicio;
		this.fim = fim;
		this.valor = valor;
		this.vetor = vetor;
	}

	public void run() {
		for (int i = inicio; i <= fim; i++) {
			if (vetor[i] == valor) {
				indice = i;
				return;
			}
		}
		indice = -1;
	}

	public int getIndice() {
		return indice;
	}
}