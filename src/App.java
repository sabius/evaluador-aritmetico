import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una expresión aritmética: ");
        String expresion = scanner.nextLine();
        scanner.close();

        // Verificar si los paréntesis están balanceados
        // Requerimiento 1 del enunciado
        if (!EvaluadorExpresiones.verificarParentesis(expresion)) {
            System.out.println("Error: Paréntesis desbalanceados.");
            return;
        }

        // Convertir la expresión a notación postfija
        String postfija = EvaluadorExpresiones.infijaAPostfija(expresion);
        System.out.println("Notación Postfija: " + postfija);
        System.out.println("Resultado: " + EvaluadorExpresiones.evaluarPostfija(postfija));
    }
}
