public class Numeroprimo {
    private final Long max;
    private final Long min;
    private Long counter = 0L;

    public Numeroprimo(Long min, Long max) {
        this.max = max;
        this.min = min;
    }

    public void execute () {
        var timeStart = System.currentTimeMillis();
        for(var i = min; i <= max; i++) {
            if (isPrime(i)) {
                System.out.println("Primo encontrado: " + i);
                this.counter++;
            }
        }
        var timeFinal = System.currentTimeMillis() - timeStart;
        System.out.println("Tempo total gasto em ms: " + timeFinal);
        System.out.println("Primos encontrados: " + counter);
    }

    private boolean isPrime(Long number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        var value = Double.valueOf(Math.ceil(Math.sqrt(number))).intValue();
        for (int j = 2; j <= value; j++) {
            if (number % j == 0) {
                return false;
            }
        }
        return true;
    }
}