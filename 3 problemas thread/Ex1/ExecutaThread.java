public class ExecutaThread 
{
    public static void main(String args[]) throws InterruptedException 
    {
    ThreadSimples simples = new ThreadSimples();
    Thread thread = new Thread(simples);
    thread.start();
    thread.join();
    System.out.println("Programa finalizado");
    }