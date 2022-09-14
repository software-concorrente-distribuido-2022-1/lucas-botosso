import java.util.ArrayList;
import java.util.List;

public class Corrida {
    private List<Lebre> lebres = new ArrayList<>();

    public synchronized void finish(Lebre lebre) {
        this.lebre.add(lebre);
    }

    public List<Lebre> getLebres() {
        return lebres;
    }

    public static void main(String[] args) throws InterruptedException {
        Corrida corrida = new Corrida();

        var t1 = new Lebre(1, corrida);
        var t2= new Lebre(2, corrida);
        var t3 = new Lebre(3, corrida);
        var t4 = new Lebre(4, corrida);
        var t5= new Lebre(5, corrida);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        var count = 1;
        System.out.println("VENCEDORA: Lebre " + corrida.getLebre().get(0).getLebreId());
        System.out.println("------------ COLOCAÇÃO -------------");
        for (var lebre : corrida.getLebres()) {
            System.out.println(count + "o lugar: Lebre " + lebre.getLebreId() + ". Quantidade de passos: " + lebre.getTotalSteps());
            count++;
        }

    }


}