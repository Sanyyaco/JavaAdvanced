public class Solution{
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        Solution s = new Solution();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                s.printA();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                s.printB();
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                s.printC();
            }
        };

        t1.start();
        t2.start();
        t3.start();
    }

    public void printA(){
        synchronized (mon){
            try{
                for (int i = 0; i < 3 ; i++) {
                    while (currentLetter != 'A'){
                        mon.wait();
                    }
                    System.out.print('A');
                    currentLetter = 'B';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printB(){
        synchronized (mon){
            try{
                for (int i = 0; i < 3 ; i++) {
                    while (currentLetter != 'B'){
                        mon.wait();
                    }
                    System.out.print('B');
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void printC(){
        synchronized (mon){
            try{
                for (int i = 0; i < 3 ; i++) {
                    while (currentLetter != 'C'){
                        mon.wait();
                    }
                    System.out.print('C');
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }





}