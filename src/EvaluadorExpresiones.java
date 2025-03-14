import java.util.Stack;

public class EvaluadorExpresiones {

    // Método para verificar si los paréntesis están balanceados
    public static boolean verificarParentesis(String expresion) {
      // Utilizamos un Stack para seguir el patrón Last In First Out (LIFO)
      // https://www.geeksforgeeks.org/stack-class-in-java/
      Stack<Character> pila = new Stack<>();
      // https://www.geeksforgeeks.org/java-string-tochararray-example/
      for (char c : expresion.toCharArray()) {
          if (c == '(') {
              pila.push(c);
          } else if (c == ')') {
              if (pila.isEmpty() || pila.pop() != '(') {
                  return false;
              }
          }
      }

      return pila.isEmpty();
    }

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
        Stack<Character> pila = new Stack<>();
        
        for (char c : expresion.toCharArray()) {
            // Si es un número, se concatena para crear un número más grande
            if (Character.isDigit(c)) {
                resultado.append(c);
            } else if (c == '(') {
                // Si encontramos un paréntesis, agregamos el número a la pila
                pila.push(c);
            } else if (c == ')') {
                
                while (!pila.isEmpty() && pila.peek() != '(') {
                    resultado.append(pila.pop());
                }
                pila.pop(); // Elimina '('
            } else { // Es un operador
                while (!pila.isEmpty() && precedencia(pila.peek()) >= precedencia(c)) {
                    resultado.append(pila.pop());
                }
                pila.push(c);
            }
        }
        while (!pila.isEmpty()) {
            resultado.append(pila.pop());
        }
        return resultado.toString();
    }

    // Método para evaluar una expresión postfija
    public static int evaluarPostfija(String expresion) {
        Stack<Integer> pila = new Stack<>();
        
        for (char c : expresion.toCharArray()) {
            if (Character.isDigit(c)) { // Si es un número
              pila.push(Character.getNumericValue(c));
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
