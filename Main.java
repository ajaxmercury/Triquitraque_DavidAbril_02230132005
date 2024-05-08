import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JColorChooser;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {
    private static Juego juego;
    private static JButton[][] botones;

    public static void main(String[] args) {
        
        String nombreJugador1 = JOptionPane.showInputDialog(null, "El primer juador se llama:");
        Color colorJugador1 = JColorChooser.showDialog(null, "Cual es su color favorito jugador?", Color.RED);
        String nombreJugador2 = JOptionPane.showInputDialog(null, "Y por el otro lado tenemos a:");
        Color colorJugador2 = JColorChooser.showDialog(null, "Y su color favorito es:", Color.BLUE);

        
        Jugador jugador1 = new Jugador(nombreJugador1, colorJugador1);
        Jugador jugador2 = new Jugador(nombreJugador2, colorJugador2);

        
        String[] opciones = {"3x3", "4x4", "5x5"};
        int seleccion = JOptionPane.showOptionDialog(null, "Que tan grande lo quiere ( ͡° ͜ʖ ͡°) :", "Triki", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        
        switch (seleccion) {
            case 0:
                juego = new Juego(jugador1, jugador2, 3);
                break;
            case 1:
                juego = new Juego(jugador1, jugador2, 4);
                break;
            case 2:
                juego = new Juego(jugador1, jugador2, 5);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida. Seleccionando tablero de tamaño 3x3 por defecto.");
                juego = new Juego(jugador1, jugador2, 3);
                break;
        }

        
        int tamañoTablero = juego.getTablero().length;
        JFrame ventana = new JFrame("TRIQUITRAQUELAS");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(300, 300);
        ventana.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(tamañoTablero, tamañoTablero));
        botones = new JButton[tamañoTablero][tamañoTablero];

        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                JButton boton = new JButton("-");
                boton.addActionListener(new BotonListener(i, j));
                panel.add(boton);
                botones[i][j] = boton;
            }
        }

        ventana.add(panel);
        ventana.setVisible(true);
    }

    private static class BotonListener implements ActionListener {
        private int fila;
        private int columna;

        public BotonListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        public void actionPerformed(ActionEvent e) {
            if (juego.marcarCasilla(fila, columna)) {
                botones[fila][columna].setBackground(juego.getJugadorActual().getColorFicha());

                int estado = juego.getEstadoJuego();
                if (estado == Juego.GANADOR) {
                    JOptionPane.showMessageDialog(null, "GANO EL PARCERO " + juego.getJugadorActual().getNombre() );
                    reiniciarJuego();
                } else if (estado == Juego.EMPATE) {
                    JOptionPane.showMessageDialog(null, "JUEMADRE EMPATARON");
                    reiniciarJuego();
                } else {
                    juego.cambiarTurno();
                }
            }
        }
    }

    private static void reiniciarJuego() {
        juego.reiniciarJuego();
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones[i].length; j++) {
                botones[i][j].setText("-");
            }
        }
    }
}
