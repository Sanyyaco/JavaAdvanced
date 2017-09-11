public class MainClass {

    @BeforeSuite
    public void start(){
        System.out.println("Начало.");
    }

//    @BeforeSuite
//    public void start2(){
//        System.out.println("Начало 2.");
//    }

    @Test
    public void method5(){
        System.out.println("Вызван метод № 5 с аннотацией 'Тест'.");
    }

    @Test(priority = 1)
    public void method1(){
        System.out.println("Вызван метод № 1 с аннотацией 'Тест'.");
    }

    @Test(priority = 4)
    public void method4(){
        System.out.println("Вызван метод № 4 с аннотацией 'Тест'.");
    }

    @Test(priority = 3)
    public void method3(){
        System.out.println("Вызван метод № 3 с аннотацией 'Тест'.");
    }


    @Test(priority = 2)
    public void method2(){
        System.out.println("Вызван метод № 2 с аннотацией 'Тест'.");
    }

    @AfterSuite
    public void finish(){
        System.out.println("Конец.");
    }
}
