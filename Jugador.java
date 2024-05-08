import java.awt.Color;

public class Jugador {
    private String nombre;
    private Color colorFicha;

    public Jugador(String nombre, Color colorFicha) {
        this.nombre = nombre;
        this.colorFicha = colorFicha;
    }

    public String getNombre() {
        return nombre;
    }

    public Color getColorFicha() {
        return colorFicha;
    }
}
