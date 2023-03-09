import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("input:");
            String str;

            str = sc.nextLine();

            String result = MyCalc.myCalc(str);

            System.out.println("output:");
            System.out.println(result);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}