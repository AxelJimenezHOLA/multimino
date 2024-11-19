public abstract class Ficha implements Comparable<Ficha>,Movible {
    protected int valorA;
    protected int valorB;
    protected Orientacion orientacion;

    public abstract int sumarValores();
    public abstract String mostrarSimple();
    public abstract void intercambiarValores();

    public int getValorA() {
        return valorA;
    }

    public int getValorB() {
        return valorB;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }
}