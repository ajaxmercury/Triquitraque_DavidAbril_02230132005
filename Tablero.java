import java.util.Arrays;

public class Tablero {
    private char[][] tablero;

    public Tablero(int tamaño) {
        tablero = new char[tamaño][tamaño];
        for (char[] fila : tablero) {
            Arrays.fill(fila, '-');
        }
    }

    public void marcarCasilla(int fila, int columna, char jugador) {
        tablero[fila][columna] = jugador;
    }

    public void reiniciarTablero() {
        for (char[] fila : tablero) {
            Arrays.fill(fila, '-');
        }
    }

    public char[][] getTablero() {
        return tablero;
    }
}
