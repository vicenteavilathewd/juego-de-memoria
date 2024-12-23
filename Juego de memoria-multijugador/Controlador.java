import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Controlador {
    public Tablero tablero;
    public Jugador jugador1;
    public Jugador jugador2; 
    public boolean contraNPC;
    public Scanner scanner;
    public Queue<String> historialMovimientos; // Cola para almacenar historial de movimientos

    public Controlador() {
        this.tablero = new Tablero();
        this.scanner = new Scanner(System.in);
        this.historialMovimientos = new LinkedList<>(); // Inicializa la cola
        inicializarJuego();
    }

    private void inicializarJuego() {
        System.out.println("¡Bienvenido al juego de memoria!");
        System.out.println("¿Quieres jugar contra un NPC o contra otro jugador?");
        System.out.println("1. Contra un NPC");
        System.out.println("2. Contra otro jugador");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        contraNPC = (opcion == 1);

        System.out.print("Ingresa el nombre del Jugador 1: ");
        String nombreJugador1 = scanner.nextLine();
        jugador1 = new Jugador(nombreJugador1);

        if (contraNPC) {
            jugador2 = new Jugador("NPC");
        } else {
            System.out.print("Ingresa el nombre del Jugador 2: ");
            String nombreJugador2 = scanner.nextLine();
            jugador2 = new Jugador(nombreJugador2);
        }

        iniciarJuego();
    }

    public void iniciarJuego() {
        tablero.mezclarCartas();
        System.out.println("¡Comienza el juego!");
        tablero.mostrarTableroOculto();

        boolean turnoJugador1 = true;
        while (!tablero.juegoTerminado()) {
            if (turnoJugador1) {
                System.out.println("Turno de: " + jugador1.getNombre());
                jugarTurno(jugador1);
            } else {
                System.out.println("Turno de: " + jugador2.getNombre());
                if (contraNPC) {
                    jugarTurnoNPC(jugador2);
                } else {
                    jugarTurno(jugador2);
                }
            }
            turnoJugador1 = !turnoJugador1;
        }

        mostrarResultado();
    }

    private void jugarTurno(Jugador jugador) {
        System.out.print("Selecciona la primera posición (0-7): ");
        int pos1 = scanner.nextInt();
        System.out.print("Selecciona la segunda posición (0-7): ");
        int pos2 = scanner.nextInt();

        tablero.mostrarCartasTemporalmente(pos1, pos2);

        if (tablero.verificarPareja(pos1, pos2)) {
            System.out.println("¡Correcto! Encontraste una pareja.");
            jugador.incrementarPuntos(1);
            historialMovimientos.add(jugador.getNombre() + " encontró una pareja en " + pos1 + " y " + pos2);
        } else {
            System.out.println("Incorrecto. Las cartas no coinciden.");
        }

        tablero.mostrarTableroOculto();
    }

    private void jugarTurnoNPC(Jugador npc) {
        System.out.println(npc.getNombre() + " está pensando...");
        int pos1, pos2;
        do {
            pos1 = (int) (Math.random() * 8);
            pos2 = (int) (Math.random() * 8);
        } while (pos1 == pos2 || tablero.verificarPareja(pos1, pos2));

        tablero.mostrarCartasTemporalmente(pos1, pos2);

        if (tablero.verificarPareja(pos1, pos2)) {
            System.out.println(npc.getNombre() + " encontró una pareja.");
            npc.incrementarPuntos(1);
            historialMovimientos.add(npc.getNombre() + " encontró una pareja en " + pos1 + " y " + pos2);
        } else {
            System.out.println(npc.getNombre() + " no encontró una pareja.");
        }

        tablero.mostrarTableroOculto();
    }
    private void mostrarResultado() {
        System.out.println("¡El juego ha terminado!");
        System.out.println("Puntaje de " + jugador1.getNombre() + ": " + jugador1.getPuntos());
        System.out.println("Puntaje de " + jugador2.getNombre() + ": " + jugador2.getPuntos());

        if (jugador1.getPuntos() > jugador2.getPuntos()) {
            System.out.println("¡" + jugador1.getNombre() + " es el ganador!");
        } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
            System.out.println("¡" + jugador2.getNombre() + " es el ganador!");
        } else {
            System.out.println("¡Es un empate!");
        }

        System.out.println("Historial de movimientos:");
        while (!historialMovimientos.isEmpty()) {
            System.out.println(historialMovimientos.poll());
        }
    }
}