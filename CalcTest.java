import java.util.Scanner;
//Тест для калькулятора формул.
public class CalcTest {
    public static boolean a = true;
    public static void main(String[] args) throws Exception{

        Scanner in = new Scanner(System.in);
        while(true){
            Calc c = new Calc();
            System.out.print("Введите формулу -> ");
            c.compile(in.next().toCharArray());

            System.out.print("\n");
        }
    }
}