import java.util.ArrayList;

public class Grupo {
    private final ArrayList<Jugador> jugadores;
    private Jugador actual;
    private Jugador ganador;

    public Grupo() {
        jugadores = new ArrayList<>();
        crearJugadores();
        actual = jugadores.getFirst();
        ganador = null;
    }

    private void crearJugadores() {
        for (int i = 0; i < 2; i++) {
            jugadores.add(new Jugador());
        }
    }

    public void nombrarJugadores(ScannerHandler entrada) {
        int i = 1;
        for (Jugador jugador : jugadores) {
            jugador.setNombre(entrada.recibirString("Ingrese el nombre del jugador %d: ".formatted(i++)));
        }
    }

    public void cambiarTurno() {
        actual = (actual == jugadores.getFirst()) ? jugadores.getLast() : jugadores.getFirst();
    }

    public Jugador getActual() {
        return actual;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }
}