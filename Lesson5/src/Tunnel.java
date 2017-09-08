public class Tunnel extends Stage {

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        System.out.println(c.getName() + " готовиться к этапу(ждет): " + description);
        System.out.println(c.getName() + " начал этап: " + description);

        try {
            Thread.sleep(length/c.getSpeed()*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
        }
    }
}
