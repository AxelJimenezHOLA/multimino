public enum Orientacion {
    HORIZONTAL, VERTICAL;

    public Orientacion cambiar() {
        Orientacion[] values = Orientacion.values();
        return values[(this.ordinal() + 1) % values.length];
    }
}