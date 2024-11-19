import java.util.ArrayList;

public class Tablero {
    private final ArrayList<Ficha> fichas;
    public Tablero() {
        fichas = new ArrayList<>();
    }

    public void colocarFicha(Ficha ficha) {
        fichas.addLast(ficha);
    }

    public Ficha obtenerUltimaFicha() {
        return fichas.getLast();
    }

    public void mostrarTablero() {
        fichas.forEach(System.out::println);
    }
}