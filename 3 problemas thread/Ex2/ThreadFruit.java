public class ThreadFruit extends Thread{
    private String fruitName;

    public ThreadFruit(String fruitName){
        this.fruitName = fruitName;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            System.out.println(this.fruitName);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                break;
            }
        }
    }
}