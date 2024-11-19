public class FichaTriomino extends FichaDomino {
    private int valorC;

    public FichaTriomino(int valorA, int valorB, int valorC) {
        super(valorA, valorB);
        this.valorC = valorC;
    }

    public int getValorC() {
        return valorC;
    }

    @Override
    public int sumarValores() {
        return valorA + valorB + valorC;
    }

    @Override
    public String mostrarSimple() {
        return String.format("%d-%d-%d", valorA, valorB, valorC);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (orientacion) {
            case VERTICAL -> {
                sb.append(String.format(" /%d\\ %n", valorC));
                sb.append(String.format("/%d_%d\\", valorA, valorB));
            }
            case HORIZONTAL -> {
                sb.append(String.format("\\%d‾%d/%n", valorA, valorC));
                sb.append(String.format(" \\%d/ ", valorB));
            }
        }
        return sb.toString();
    }

    @Override
    public void rotateRight() {
        if (orientacion == Orientacion.HORIZONTAL) {
            int temp = valorA;
            valorA = valorB;
            valorB = valorC;
            valorC = temp;
        }
        orientacion = orientacion.cambiar();
    }

    @Override
    public void rotateLeft() {
        if (orientacion == Orientacion.VERTICAL) {
            int temp = valorA;
            valorA = valorC;
            valorC = valorB;
            valorB = temp;
        }
        orientacion = orientacion.cambiar();
    }
}