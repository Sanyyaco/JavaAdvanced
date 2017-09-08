import java.util.concurrent.BrokenBarrierException;

public class Car implements Runnable {

    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT ++;
        this.name = "Участник №" + CARS_COUNT;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(this.name + " готовиться.");
        try {
            Thread.sleep(500 + (int)(Math.random()*800));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + " готов.");

        MainClass.cdl1.countDown();

        try {
            MainClass.cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < race.getStages().size() ; i++) {
            race.getStages().get(i).go(this);
        }

        MainClass.cdl2.countDown();
    }
}
