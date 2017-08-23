package HW;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    final static String ERROR_MESSAGE = "\nНеверная последовательность ввода команд.\n" +
            "Пример консольной комманды для получения цены:" +
            "/цена товар545\n" + "" +
            "Пример консольной команды для изменения цены товара:" +
            "/сменитьцену товар10 10000\n" +
            "Пример консольной команды для вывода товаров в заданном ценовом диапазоне: " +
            "/товарыпоцене 100 600";
    final static String HELLO_MESSAGE = "Программа для учета товаров.\n" +
            "Пример консольной комманды для получения цены:" +
            "/цена товар545;\n" + "" +
            "Пример консольной команды для изменения цены товара:" +
            "/сменитьцену товар10 10000;\n" +
            "Пример консольной команды для вывода товаров в заданном ценовом диапазоне: " +
            "/товарыпоцене 100 600.";
    final static String INPUT_MESSAGE = "\nВведите команду:";

    final static String COMMAND_1 = "/цена";
    final static String COMMAND_2 = "/сменитьцену";
    final static String COMMAND_3 = "/товарыпоцене";
    final static String EXIT_COMMAND ="/выход";

    public static void main(String[] args) {

        DataBase db = new DataBase("DBHW.db");
        db.connect();
        db.initTable();
        db.populateTable();

        Scanner scanner = new Scanner(System.in);
        System.out.println(HELLO_MESSAGE);

        while (true){
            System.out.println(INPUT_MESSAGE);
            String[] s = scanner.nextLine().split(" ");
            String command = new String(s[0]);

            switch (command){

                case COMMAND_1:
                    if (s.length>2) System.out.println(ERROR_MESSAGE);
                    String commodityName = new String (s[1]);
                    db.getCommodityPrice(s[1]);
                    break;

                case COMMAND_2:
                    if (s.length>3) System.out.println(ERROR_MESSAGE);
                    commodityName = new String (s[1]);
                    Integer commodityPrice = 0;
                    try{
                        commodityPrice = Integer.parseInt(s[2]);
                    }catch (NumberFormatException e){
                        System.out.println(ERROR_MESSAGE);
                        break;
                    }
                    db.changeCommodityPrice(commodityName , commodityPrice);
                    break;

                case COMMAND_3:
                    if (s.length>3) System.out.println(ERROR_MESSAGE);
                    commodityPrice = 0;
                    Integer commodityPrice2 = 0;
                    try{
                        commodityPrice = Integer.parseInt(s[1]);
                        commodityPrice2 = Integer.parseInt(s[2]);
                    }catch (NumberFormatException e){
                        System.out.println(ERROR_MESSAGE);
                        break;
                    }
                    db.getCommoditiesByPriceRange(commodityPrice,commodityPrice2);
                    break;

                case EXIT_COMMAND:
                    System.out.println("\nПрограмма завершена.");
                    db.disconnect();
                    return;

                default:
                    System.out.println(ERROR_MESSAGE);
            }
        }
    }
}
