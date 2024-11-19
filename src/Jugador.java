import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private int puntuacion;
    private final ArrayList<Ficha> fichas;

    public Jugador() {
        nombre = "";
        puntuacion = 0;
        fichas = new ArrayList<>();
    }

    public void agregarPuntos(int puntos) {
        puntuacion += puntos;
    }

    public void agregarFicha(Ficha ficha) {
        fichas.add(ficha);
    }

    public Ficha retirarFicha(int indiceFicha) {
        return fichas.remove(indiceFicha);
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}