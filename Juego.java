import javax.swing.JOptionPane;
import java.awt.Color;

public class Juego {
    private Jugador jugador1;
    private Jugador jugador2;
    private Tablero tablero;
    private char jugadorActual;

    public static final int GANADOR = 1;
    public static final int EMPATE = 0;
    public static final int CONTINUAR = -1;

    public Juego(Jugador jugador1, Jugador jugador2, int tamañoTablero) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        tablero = new Tablero(tamañoTablero);
        jugadorActual = 'X'; 
    }

    public boolean marcarCasilla(int fila, int columna) {
        if (tablero.getTablero()[fila][columna] == '-') {
            tablero.marcarCasilla(fila, columna, jugadorActual);
            return true;
        }
        return false;
    }

    public int getEstadoJuego() {
        if (hayGanador()) {
            return GANADOR;
        }
        if (tableroLleno()) {
            return EMPATE;
        }
        return CONTINUAR;
    }

    public boolean hayGanador() {
        char[][] tab = tablero.getTablero();
        
        for (int i = 0; i < 3; i++) {
            if (tab[i][0] != '-' && tab[i][0] == tab[i][1] && tab[i][0] == tab[i][2]) {
                return true; 
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if (tab[0][i] != '-' && tab[0][i] == tab[1][i] && tab[0][i] == tab[2][i]) {
                return true; 
            }
        }
        
        if (tab[0][0] != '-' && tab[0][0] == tab[1][1] && tab[0][0] == tab[2][2]) {
            return true; 
        }
        if (tab[0][2] != '-' && tab[0][2] == tab[1][1] && tab[0][2] == tab[2][0]) {
            return true; 
        }
        return false;
    }

    public boolean tableroLleno() {
        char[][] tab = tablero.getTablero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tab[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }

    public void reiniciarJuego() {
        tablero.reiniciarTablero();
        jugadorActual = 'X'; 
    }

    public char[][] getTablero() {
        return tablero.getTablero();
    }

    public Jugador getJugadorActual() {
        return (jugadorActual == 'X') ? jugador1 : jugador2;
    }
    
    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }
}
