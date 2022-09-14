import java.util.ArrayList;

public class BuscaParalela {

	public static int parallelSearch(int x, int[] A, int numThreads) {
		ArrayList<BuscaThread> threads = new ArrayList<BuscaThread>();
		int tamanhoThread = A.length / numThreads;
		int indice;
		for (int i = 0; i < numThreads; i++) {
			BuscaThread bt = new BuscaThread(0 + (i * tamanhoThread), (0 + ((i + 1) * tamanhoThread)) - 1, x, A);
			bt.start();
			threads.add(bt);
		}

		for (BuscaThread bt : threads) {
			try {
				bt.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for (BuscaThread bt : threads) {
			indice = bt.getIndice();
			if (indice != -1) {
				return indice;
			}
		}

		return -1;
	}
}