import java.util.Random;

public class Lebre extends Thread {

    private final Integer lebreId;
    private final Corrida corrida;
    private Integer totalSteps = 0;

    public Lebre(final Integer id, final Corrida corrida) {
        this.lebreId = id;
        this.corrida = corrida;
    }

    public Integer getCorridaId() {
        return lebreId;
    }

    public Integer getTotalSteps() {
        return totalSteps;
    }

    @Override
    public void run() {
        int meters = 0;
        while (meters < 20) {
            int jump = new Random().nextInt(3) + 1;
            meters += jump;
            this.totalSteps++;
            System.out.println("Lebre " + corridaId + " pulou " + jump + " metros");
            yield();
        }
        corrida.finish(this);
    }

}