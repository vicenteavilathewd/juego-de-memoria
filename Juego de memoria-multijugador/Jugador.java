import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class Jugador extends Persona implements Animador {
    private int puntos;
    protected Stack<String> logros; // Pila para logros alcanzados
    protected Queue<String> movimientos; // Cola para registrar movimientos realizados

    public Jugador(String nombre) {
        super(nombre);
        this.puntos = 0;
        this.logros = new Stack<>();
        this.movimientos = new LinkedList<>();
    }

    public int getPuntos() {
        return puntos;
    }

    protected void incrementarPuntos(int puntos) {
        this.puntos += puntos;
        logros.push("Ganó " + puntos + " puntos."); // Agregar logro
    }

    public void registrarMovimiento(String movimiento) {
        movimientos.add(movimiento); // Registrar movimiento en la cola
    }

    public void mostrarMovimientos() {
        System.out.println("Movimientos realizados por " + getNombre() + ":");
        while (!movimientos.isEmpty()) {
            System.out.println(movimientos.poll());
        }
    }

    public void mostrarLogros() {
        System.out.println("Logros alcanzados por " + getNombre() + ":");
        while (!logros.isEmpty()) {
            System.out.println(logros.pop());
        }
    }

    @Override
    public void animar() {
        System.out.println(getNombre() + " dice: ¡Vamos, estoy mejorando!");
    }
}