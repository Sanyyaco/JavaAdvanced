public class Solution3 {

    private static Object printMon = new Object();
    private static Object scanMon = new Object();
    private static int scanPage = 0;
    private static int printPage = 0;

    public static void main(String[] args) {
            while(true){
                printPage();
                scanPage();
            }
    }


    public static void printPage(){
        synchronized (printMon){
            System.out.println("0тпечатано " + (++printPage) + " страниц(ы)." );
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void scanPage(){
        synchronized (scanMon){
            System.out.println("Отсканировано "+ (++scanPage) + " страниц(ы).");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
