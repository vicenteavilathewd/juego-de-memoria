import java.util.ArrayList;
import java.util.Collections;

public class Tablero {
    private ArrayList<Carta> cartas;
    private boolean[][] descubiertas; // Matriz para saber si las cartas están descubiertas
    protected int[][] intentos; // Matriz para registrar los intentos por posición
    protected boolean[][] parejasEncontradas; // Matriz para registrar parejas encontradas

    public Tablero() {
        cartas = new ArrayList<>();
        descubiertas = new boolean[2][4];
        intentos = new int[2][4];
        parejasEncontradas = new boolean[2][4];
        inicializarCartas();
        mezclarCartas();
    }

    private void inicializarCartas() {
        String[] valores = {"A", "B", "C", "D", "A", "B", "C", "D"};
        for (String valor : valores) {
            cartas.add(new Carta(valor));
        }
    }

    public void mezclarCartas() {
        Collections.shuffle(cartas);
    }

    public void mostrarTableroOculto() {
        int index = 0;
        for (int i = 0; i < descubiertas.length; i++) {
            for (int j = 0; j < descubiertas[i].length; j++) {
                if (descubiertas[i][j]) {
                    System.out.print(cartas.get(index).getValor() + " ");
                } else {
                    System.out.print("* ");
                }
                index++;
            }
            System.out.println();
        }
    }

    public void mostrarCartasTemporalmente(int pos1, int pos2) {
        if (pos1 < 0 || pos1 >= cartas.size() || pos2 < 0 || pos2 >= cartas.size()) {
            System.out.println("Posiciones fuera de rango.");
            return;
        }

        int index = 0;
        for (int i = 0; i < descubiertas.length; i++) {
            for (int j = 0; j < descubiertas[i].length; j++) {
                if (index == pos1 || index == pos2 || descubiertas[i][j]) {
                    System.out.print(cartas.get(index).getValor() + " ");
                } else {
                    System.out.print("* ");
                }
                index++;
            }
            System.out.println();
        }

        // Registro de intentos
        intentos[pos1 / 4][pos1 % 4]++;
        intentos[pos2 / 4][pos2 % 4]++;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarPareja(int pos1, int pos2) {
        if (cartas.get(pos1).getValor().equals(cartas.get(pos2).getValor()) && pos1 != pos2) {
            descubiertas[pos1 / 4][pos1 % 4] = true;
            descubiertas[pos2 / 4][pos2 % 4] = true;
            parejasEncontradas[pos1 / 4][pos1 % 4] = true;
            parejasEncontradas[pos2 / 4][pos2 % 4] = true;
            return true;
        }
        return false;
    }

    public boolean juegoTerminado() {
        for (boolean[] fila : descubiertas) {
            for (boolean descubierta : fila) {
                if (!descubierta) {
                    return false;
                }
            }
        }
        return true;
    }
}