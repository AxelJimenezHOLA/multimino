import java.util.ArrayList;
import java.util.Random;

public class Pozo {
    private final ArrayList<Ficha> fichas;

    public Pozo() {
        fichas = new ArrayList<>();
        crearFichasDomino();
        crearFichasTriomino();
    }

    private void crearFichasDomino() {
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                fichas.add(new FichaDomino(i, j));
            }
        }
    }

    private void crearFichasTriomino() {
        for (int i = 0; i <= 5; i++) {
            for (int j = i; j <= 5; j++) {
                for (int k = j; k <= 5; k++) {
                    fichas.add(new FichaTriomino(i, j, k));
                }
            }
        }
    }

    public Ficha retirarFicha() {
        return fichas.remove(new Random().nextInt(fichas.size()));
    }

    public void retirarFicha(Ficha ficha) {
        fichas.remove(ficha);
    }

    public boolean tieneFichas() {
        return !fichas.isEmpty();
    }

    public Ficha[] obtenerFichasIniciales() {
        Random rng = new Random();
        Ficha[] fichasIniciales = new Ficha[2];
        fichasIniciales[0] = fichas.get(rng.nextInt(fichas.size()));
        fichasIniciales[1] = fichas.get(rng.nextInt(fichas.size()));
        return fichasIniciales;
    }
}