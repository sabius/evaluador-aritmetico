import java.util.Scanner;
import java.util.Stack;

public class EvaluadorExpresiones {

    // Método para obtener la precedencia de los operadores
    private static int precedencia(char operador) {
        switch (operador) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            default: return -1;
        }
    }

    // Método para convertir una expresión infija a postfija
    public static String infijaAPostfija(String expresion) {
        StringBuilder resultado = new StringBuilder();
        Stack<Character> pilaOperadores = new Stack<>();
        
        for (char c : expresion.toCharArray()) {
            if (Character.isDigit(c)) {
                resultado.append(c);
            } else { // Es un operador
                while (!pilaOperadores.isEmpty() && precedencia(pilaOperadores.peek()) >= precedencia(c)) {
                    resultado.append(pilaOperadores.pop());
                }
                pilaOperadores.push(c);
            }
        }
        while (!pilaOperadores.isEmpty()) {
            resultado.append(pilaOperadores.pop());
        }
        return resultado.toString();
    }

    // Método para evaluar una expresión postfija
    public static int evaluarPostfija(String expresion) {
        Stack<Integer> pila = new Stack<>();
        
        for (char c : expresion.toCharArray()) {
            if (Character.isDigit(c)) {
                pila.push(c - '0'); // Convierte el char en número
            } else { // Es un operador
                int b = pila.pop();
                int a = pila.pop();
                switch (c) {
                    case '+': pila.push(a + b); break;
                    case '-': pila.push(a - b); break;
                    case '*': pila.push(a * b); break;
                    case '/': pila.push(a / b); break;
                }
            }
        }
        return pila.pop();
    }
}