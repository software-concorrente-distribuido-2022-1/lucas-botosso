public class ThreadSimples implements Runnable
{
    public void run() 
    {
        for (int i = 1; i <= 100; i++) 
        {
            try 
            {
                Thread.sleep(100);
                System.out.println(i);
            } catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
}