import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class Solution {


    public static void main(String[] args) throws IOException {
        long timeStart = System.currentTimeMillis();
        TestFileCreator.createTextFile();
        readFileToConsole(TestFileCreator.textFile);
        System.out.println("Общее время ввода-вывода(мс): " + (System.currentTimeMillis() - timeStart));
        System.out.println("Размер файла: " + TestFileCreator.textFile.length());
    }

    public static void main2(String[] args) throws IOException {
        TestFileCreator.populateTestFiles();
        writeFilesToFile(TestFileCreator.listFiles,5);
    }

    public static void main1(String[] args) throws IOException {
        readWriteFileToByteArray(TestFileCreator.listFiles.get(0));
    }

    //Задание 1
    public static void readWriteFileToByteArray(File file) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(Files.readAllBytes(file.toPath()));
        ByteArrayOutputStream bous = new ByteArrayOutputStream();

        while(bais.available() > 0){
            bous.write(bais.read());
        }
        System.out.println(Arrays.toString(bous.toByteArray()));
    }

    //Задание 2
    public static void writeFilesToFile(List<File> files, int numberOfFileToReadFrom){
        System.out.println("Задание 2: запись данных из пяти файлов с данными в один файл.");
        int cnt = numberOfFileToReadFrom;
        long sizeFileBefore = files.get(cnt).length();
        Vector<FileInputStream> listFIS = new Vector<>();

        for (int i = 0; i < cnt; i++) {
            try {
                System.out.println("Загрузка файла в массив потоков FileInputStream:" + files.get(i).getName()
                        + "размер " + files.get(i).length()+" байт.");
                listFIS.add(new FileInputStream(files.get(i)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Загрузка массива в SequenceInputStream.");
        SequenceInputStream seq = new SequenceInputStream(listFIS.elements());

        FileOutputStream fos = null;
        try {
            System.out.println("Создание потока FileOutputStream для файла " + files.get(5).getName() +
                    ", размер файла - "
                    + files.get(5).length() + "байт.");
            fos = new FileOutputStream(files.get(5),true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int data = 0;
        try {
            data = seq.read();

        while(data != -1){
            fos.write(data);
            data = seq.read();
        }
            System.out.println("Запись байтов массива SequenceInputStream в файл " + files.get(5).getName() + ".");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            for(FileInputStream e: listFIS){
                e.close();
            }
            fos.close();
            seq.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        long sizeFileAfter = files.get(cnt).length();
        System.out.println("Размер файла до записи: "+ sizeFileBefore +
                " байт. Размер файла после записи: " + sizeFileAfter+" байт." );
    }

    //Задание 3
    public static void readFileToConsole(File file){
        int pointer = 0;
        int counter = 0;
        RandomAccessFile raf = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try{
            raf = new RandomAccessFile(file,"r");
            fis = new FileInputStream(raf.getFD());
            bis = new BufferedInputStream(fis);

            raf.seek(pointer);
            while(counter < 1800){
                byte[] b = new byte[1800];
                int charByte = bis.read(b);
                if(charByte == -1) {
                    return;
                }
                counter++;

                if(counter == 1799) {
                    System.out.println(new String(b));
                    pointer = pointer + 1799;
                    bis = new BufferedInputStream(fis);
                    raf.seek(pointer);
                    counter = 0;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
                fis.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
