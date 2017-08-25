import org.fluttercode.datafactory.impl.DataFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestFileCreator {

    public static File testFolder;
    public static List<File> listFiles = new ArrayList<>();
    public static File textFile;


    static{
        testFolder = new File("TestFolder");
        testFolder.mkdirs();
        for (int i = 0; i < 6 ; i++) {
            listFiles.add(new File(testFolder,i+1+".txt"));
            try {
                listFiles.get(i).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void populateTestFiles(){

        for (int i = 0; i < 5 ; i++) {
            try {
                FileOutputStream fos = new FileOutputStream(listFiles.get(i), true);
                for (int j = 0; j <100 ; j++) {
                    fos.write(1);
                }
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createTextFile() throws IOException {
        textFile = new File(testFolder,"TestTextFile.txt");
        textFile.createNewFile();

        PrintWriter pw = null;
        pw = new PrintWriter(textFile);

        DataFactory df = new DataFactory();
        for (int i = 0; i < 5000 ; i++) {
            for (int j = 0; j < 200 ; j++) {
                pw.write(df.getName() + " ");
                if(j%7 == 0) pw.println();
            }
            pw.write("\nКонец страницы №"+i+"\n");
        }
        System.out.println("Есть ошибки при создании тестового файла с записями: " + pw.checkError());
        pw.close();
    }
}
