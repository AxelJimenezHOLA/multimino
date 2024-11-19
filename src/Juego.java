import java.util.ArrayList;

public class Juego {
    private final Grupo grupo;
    private final Tablero tablero;
    private final Pozo pozo;

    public Juego() {
        ScannerHandler entrada = new ScannerHandler();
        grupo = new Grupo();
        grupo.nombrarJugadores(entrada);
        tablero = new Tablero();
        pozo = new Pozo();
    }

    public void jugar() {
        darFichasIniciales();
        determinarJugadorInicial();
        while(!seTerminoJuego()) {
            jugarTurno();
            grupo.cambiarTurno();
        }
        determinarGanador();
        System.out.printf("El ganador del juego es %s.%n",grupo.getGanador());
    }

    private void darFichasIniciales() {
        grupo.getJugadores().forEach(jugador -> agregarFichasAJugador(10, jugador));
    }

    private void agregarFichasAJugador(int cantidad, Jugador jugador) {
        for (int i = 0; i < cantidad; i++) {
            jugador.agregarFicha(pozo.retirarFicha());
        }
    }

    private void determinarJugadorInicial() {
        FichaDomino[] fichasIniciales = pozo.obtenerFichasIniciales();
        int[] valores = new int[2];

        do {
            for (int i = 0; i < 2; i++) {
                fichasIniciales = pozo.obtenerFichasIniciales();
                valores[i] = fichasIniciales[i].sumarValores();
                System.out.printf("El jugador %s saca una ficha con valor de %s.%n",grupo.getJugadores().get(i), valores[i]);
            }

            if (valores[0] > valores[1]) {
                tablero.colocarFicha(fichasIniciales[0]);
                pozo.retirarFicha(fichasIniciales[0]);
                System.out.printf("¡El jugador %s inicia el juego!%n",grupo.getActual());
            } else if (valores[1] > valores[0]) {
                grupo.cambiarTurno();
                tablero.colocarFicha(fichasIniciales[1]);
                pozo.retirarFicha(fichasIniciales[1]);
                System.out.printf("¡El jugador %s inicia el juego!%n",grupo.getActual());
            }
            if (valores[0] == valores[1]) {
                System.out.println("Ha habido un empate con la ficha inicial, ¡se vuelven a sacar fichas!");
            }
        } while (valores[0] == valores[1]);
    }

    public void jugarTurno() {
        Jugador actual = grupo.getActual();
        if (!puedeJugar(actual)) {
            if (pozo.tieneFichas()){
                System.out.printf("El jugador %s no tiene jugadas disponibles. Agarra dos fichas y salta su turno.%n",actual);
                agregarFichasAJugador(2, actual);
            } else {
                System.out.println("Ya no hay fichas en el pozo.");
            }
            return;
        }

        FichaDomino fichaSeleccionada;
        boolean colocable;

        do {
            tablero.mostrarTablero();
            fichaSeleccionada = seleccionarFichaParaJugar(actual);
            colocable = esFichaJugable(fichaSeleccionada);
            if (colocable) {
                tablero.colocarFicha(fichaSeleccionada);
                actual.agregarPuntos(fichaSeleccionada.sumarValores());
            } else {
                System.out.println("Movimiento inválido. Seleccione otra ficha.");
                actual.agregarFicha(fichaSeleccionada);
            }
        } while (!colocable);
    }

    private FichaDomino seleccionarFichaParaJugar(Jugador jugador) {
        FichaDomino fichaSeleccionada = null;
        int opcionFicha;
        ScannerHandler entrada = new ScannerHandler();
        ArrayList<FichaDomino> fichasJugables = jugador.getFichas();
        boolean jugadaValida;
        do {
            for (int i = 0; i < fichasJugables.size(); i++) {
                FichaDomino ficha = fichasJugables.get(i);
                System.out.printf("%d. %s%n", (i + 1), ficha.mostrarSimple());
            }

            opcionFicha =
                    entrada.recibirEntero("Jugador %s, selecciona la ficha que quieras jugar: ".formatted(jugador));
            opcionFicha -= 1;
            if (opcionFicha >= 0 && opcionFicha < fichasJugables.size()) {
                fichaSeleccionada = jugador.retirarFicha(opcionFicha);
                jugadaValida = true;
            } else {
                System.out.println("Error: ficha no disponible");
                jugadaValida = false;
            }
        } while (!jugadaValida);
        return fichaSeleccionada;
    }

    private boolean puedeJugar(Jugador jugador) {
        return jugador.getFichas().stream().anyMatch(this::esFichaJugable);
    }

    private boolean esFichaJugable(FichaDomino fichaParaJugar) {
        FichaDomino ultimaFichaJugada = tablero.obtenerUltimaFicha();

        if (ultimaFichaJugada instanceof FichaTriomino) {
            if (fichaParaJugar instanceof FichaTriomino) {
                if (ultimaFichaJugada.getOrientacion() == Orientacion.VERTICAL) {
                    // Ultima ficha: Triomino
                    // Ficha a jugar : Triomino
                    // Orientacion ultima ficha: Arriba
                    if (fichaParaJugar.getOrientacion() == Orientacion.VERTICAL) {
                        fichaParaJugar.rotateRight();
                    }
                    for (int i = 0; i < 4; i++) {
                        if (ultimaFichaJugada.getValorA() == fichaParaJugar.getValorA() &&
                                ultimaFichaJugada.getValorB() == ((FichaTriomino) fichaParaJugar).getValorC()) {
                            return true;
                        }
                        fichaParaJugar.rotateRight();
                        fichaParaJugar.rotateRight();
                    }
                } else if (ultimaFichaJugada.getOrientacion() == Orientacion.HORIZONTAL) {
                    // Ultima ficha: Triomino
                    // Ficha a jugar : Triomino
                    // Orientacion ultima ficha: Abajo
                    if (fichaParaJugar.getOrientacion() == Orientacion.HORIZONTAL) {
                        fichaParaJugar.rotateRight();
                    }
                    for (int i = 0; i < 4; i++) {
                        if (ultimaFichaJugada.getValorB() == ((FichaTriomino) fichaParaJugar).getValorC()) {
                            return true;
                        }
                        fichaParaJugar.rotateRight();
                        fichaParaJugar.rotateRight();
                    }
                }
            } else {
                // Ultima ficha: Triomino
                // Ficha a jugar : Domino
                // Orientacion ultima ficha: Abajo
                if (ultimaFichaJugada.getOrientacion() == Orientacion.HORIZONTAL) {
                    if (ultimaFichaJugada.getValorB() == fichaParaJugar.getValorA()) {
                        return true;
                    }
                    fichaParaJugar.intercambiarValores();
                    return ultimaFichaJugada.getValorB() == fichaParaJugar.getValorA();
                }
            }
        } else {
            if (fichaParaJugar instanceof FichaTriomino) {
                // Ultima ficha: Domino
                // Ficha a jugar : Triomino
                if (fichaParaJugar.getOrientacion() == Orientacion.HORIZONTAL) {
                    fichaParaJugar.rotateRight();
                }
                for (int i = 0; i < 4; i++) {
                    if (ultimaFichaJugada.getValorB() == ((FichaTriomino) fichaParaJugar).getValorC()) {
                        return true;
                    }
                    fichaParaJugar.rotateRight();
                    fichaParaJugar.rotateRight();
                }
            } else {
                // Ultima ficha: Domino
                // Ficha a jugar : Domino
                if (ultimaFichaJugada.getValorB() == fichaParaJugar.getValorA()) {
                    return true;
                }
                fichaParaJugar.intercambiarValores();
                return ultimaFichaJugada.getValorB() == fichaParaJugar.getValorA();
            }
        }
        return false;
    }

    private boolean seTerminoJuego() {
        return hayJugadorSinFichas() || !hayJugadasDisponibles();
    }

    private boolean hayJugadorSinFichas() {
        return grupo.getJugadores().stream().anyMatch(jugador -> jugador.getFichas().isEmpty());
    }

    private boolean hayJugadasDisponibles() {
        return pozo.tieneFichas() || grupo.getJugadores().stream().anyMatch(this::puedeJugar);
    }

    private void determinarGanador() {
        int puntuacionMayor = Integer.MIN_VALUE;
        Jugador ganador = null;
        for (Jugador jugador : grupo.getJugadores()) {
            if (jugador.getPuntuacion() > puntuacionMayor) {
                puntuacionMayor = jugador.getPuntuacion();
                ganador = jugador;
            }
        }
        grupo.setGanador(ganador);
    }
}