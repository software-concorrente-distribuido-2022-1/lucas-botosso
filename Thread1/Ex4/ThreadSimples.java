public class ThreadSimples 
{
    static void mensagem(String messagem) 
    {
        
        String nomeThread = Thread.currentThread().getName();
        
        System.out.println(nomeThread + " " + messagem);
    }

    
    private static class Loop implements Runnable 
    {
        
        public void run() 
        {
            
            String info[] = 
            {
                    "Java",
                    "é uma boa linguagem.",
                    "Com threads,",
                    "é melhor ainda."
            };
            
            try 
            {
                
                for (int i = 0; i < info.length; i++) 
                {
                    
                    Thread.sleep(4000);
                    
                    mensagem(info[i]);
                }
            
            } catch (InterruptedException e) 
            {
                
                mensagem("Nada feito!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException 
    {
        long paciencia = 1000 * 60 * 60;
        if (args.length > 0) 
        {
            try 
            {
                paciencia = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) 
            {
                System.err.println("Argumento deve ser um inteiro.");
                System.exit(1);
            }
        }
        mensagem("Iniciando a thread Loop");
        long inicio = System.currentTimeMillis();
        Thread t = new Thread(new Loop());
        t.start();
        mensagem("Esperando que a thread Loop termine");
        while (t.isAlive()) 
        {
            mensagem("Ainda esperando...");
            t.join(1000);
            if (((System.currentTimeMillis() - inicio) > paciencia) && t.isAlive()) 
            {
                mensagem("Cansado de esperar!");
                t.interrupt();
                t.join();
            }
        }
        mensagem("Finalmente!");
    }
}