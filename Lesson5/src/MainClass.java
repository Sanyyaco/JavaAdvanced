import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {

    public static final int CARS_COUNT = 4;
    public static final CountDownLatch cdl2 = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch cdl1 = new CountDownLatch(CARS_COUNT);

    public static final CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length ; i++) {
            cars[i] = new Car(race, 20 + (int)(Math.random()*10));
        }

        for (int i = 0; i < cars.length ; i++) {
            new Thread(cars[i]).start();
        }


        try {
            cdl1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            cdl2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");





    }
}
