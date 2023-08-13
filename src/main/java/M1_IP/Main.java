package M1_IP;

public class Main {


    public static void main(String[] args) {

        MainCalculation mainCalculation = new MainCalculation();

        Window window = new Window();

        new Thread(() -> {
            while (! Thread.currentThread().isInterrupted()) {
                Runtime runtime = Runtime.getRuntime();
                long usedMemory = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("Used Memory: " + usedMemory / 1024 / 1024 + " MB");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}
