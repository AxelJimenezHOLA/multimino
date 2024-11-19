public class FichaDomino extends Ficha {

    public FichaDomino(int valorA, int valorB) {
        this.valorA = valorA;
        this.valorB = valorB;
        this.orientacion = Orientacion.VERTICAL;
    }

    public void intercambiarValores() {
        int aux = valorA;
        valorA = valorB;
        valorB = aux;
    }

    public int sumarValores() {
        return valorA + valorB;
    }

    public String mostrarSimple() {
        return String.format("%s-%s", valorA, valorB);
    }

    @Override
    public String toString() {
        return orientacion == Orientacion.VERTICAL ?
                String.format("---%n|%d|%n---%n|%d|%n---", valorA, valorB) :
                String.format("[%d|%d]", valorA, valorB);
    }

    @Override
    public void rotateRight() {
        orientacion = orientacion.cambiar();
        if (orientacion == Orientacion.VERTICAL) {
            intercambiarValores();
        }
    }

    @Override
    public void rotateLeft() {
        orientacion = orientacion.cambiar();
        if (orientacion == Orientacion.HORIZONTAL) {
            intercambiarValores();
        }
    }

    @Override
    public int compareTo(Ficha otraFicha) {
        return Integer.compare(this.sumarValores(), otraFicha.sumarValores());
    }
}