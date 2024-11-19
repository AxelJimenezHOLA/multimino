import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerHandler {
    private final Scanner entrada;

    public ScannerHandler() {
        entrada = new Scanner(System.in);
    }

    public String recibirString(String mensajeEntrada) {
        String string;
        do {
            System.out.print(mensajeEntrada);
            string = entrada.nextLine();
            if (string.isEmpty()) System.out.println("Error: ingrese al menos un carácter.");
        } while (string.isEmpty());
        return string;
    }

    public int recibirEntero(String mensajeEntrada) {
        int integer;
        do {
            System.out.print(mensajeEntrada);
            try {
                integer = entrada.nextInt();
            } catch (InputMismatchException e) {
                entrada.nextLine();
                integer = Integer.MIN_VALUE;
                System.out.println("Error: ingrese números enteros en la entrada.");
            }
        } while (integer == Integer.MIN_VALUE);
        entrada.nextLine();
        return integer;
    }
}