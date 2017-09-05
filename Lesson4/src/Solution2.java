import java.io.*;

public class Solution2 {
    public static void main(String[] args) throws IOException {
        File textFile = new File("inputFile.txt");
        textFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(textFile,true);
        writeToFile(fos);
    }

    public static void writeToFile(FileOutputStream fos) throws IOException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10 ; i++) {
                    try {
                        fos.write('1');
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }
}
