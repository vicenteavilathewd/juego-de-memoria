import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class Espectador extends Persona implements Animador {
    protected Stack<String> comentarios; // Pila para comentarios realizados
    protected Queue<String> ánimos; // Cola para ánimos dados

    public Espectador(String nombre) {
        super(nombre);
        this.comentarios = new Stack<>();
        this.ánimos = new LinkedList<>();
    }

    @Override
    public void animar() {
        String ánimo = "¡Ánimo, puedes hacerlo!";
        ánimos.add(ánimo);
        System.out.println(getNombre() + " dice: " + ánimo);
    }

    protected void agregarComentario(String comentario) {
        comentarios.push(comentario);
    }

    public void mostrarComentarios() {
        System.out.println("Comentarios realizados por " + getNombre() + ":");
        while (!comentarios.isEmpty()) {
            System.out.println(comentarios.pop());
        }
    }
}