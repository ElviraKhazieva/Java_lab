public class Main {
    public static void main(String[] args) {
        /*
        Сделать проверки:

        1) Одна задача - один поток
        2) Две задачи - один поток (поочередно выполнить каждую)
        3) Три задачи - три потока (каждый поток выполняет свою задачу)
        4) Четыре задачи - три потока (три потока выполняют три задачи, четвертая задача выполняется первым свободным)
         */
        ThreadPool threadPool = new ThreadPool(3);
        threadPool.submit(() -> {
            for (int i = 0; i < 30; i++) {
                        System.out.println(Thread.currentThread().getName() + " " + "A");
                    }
        });
        threadPool.submit(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "B");
            }
        });

        threadPool.submit(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "C");
            }
        });
        threadPool.submit(() -> {
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName() + " " + "D");
            }
        });
    }
}
