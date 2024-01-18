package quest.flo;

public class Main {
    public static void main(String[] args) {
        try {
            Calculator calc = new Calculator();
            System.out.println("Your bmi is: " + calc.getBmi().toString());
            System.out.println(calc.judgeUser());

            calc = new Calculator((float) 70, (float) 1.75);
            System.out.println("Your bmi is: " + calc.getBmi().toString());
            System.out.println(calc.judgeUser());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}