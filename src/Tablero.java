import java.util.ArrayList;

public class Tablero {
    private final ArrayList<FichaDomino> fichas;
    public Tablero() {
        fichas = new ArrayList<>();
    }

    public void colocarFicha(FichaDomino ficha) {
        fichas.addLast(ficha);
    }

    public FichaDomino obtenerUltimaFicha() {
        return fichas.getLast();
    }

    public void mostrarTablero() {
        fichas.forEach(System.out::println);
    }
}