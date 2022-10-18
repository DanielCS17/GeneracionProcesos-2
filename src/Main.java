import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuantos blocs de notas quieres abrir?: ");
        int winNumber = sc.nextInt();

        while (winNumber < 0){
            System.out.println("ERROR! No se permiten números negativos \n");
            System.out.print("¿Cuantos blocs de notas quieres abrir?: ");
            winNumber = sc.nextInt();
        }
        if (winNumber == 0) {
            System.out.println("AVISO! ha introducido 0, por lo que no se abrirán Notepads \n");
        }

        ProcessBuilder process = new ProcessBuilder();
        if (System.getProperty("os.name").startsWith("Windows")) {
            process.command("cmd.exe", "/c", "notepad");
        } else process.command("firefox");

        try {

            int processNumber = 0;
            long initTime = 0;

            while (processNumber < winNumber) {
                Process loopProcess = process.start();
                initTime = System.currentTimeMillis();
                loopProcess.waitFor(500, TimeUnit.MILLISECONDS);
                System.out.println("\nHora de inicio del proceso " + (processNumber + 1) + " -> " + LocalDateTime.now());
                System.out.println("Duración -> " + (System.currentTimeMillis() - initTime) + " ms.");
                System.out.println("Hora de finalización del proceso " + (processNumber + 1) + " -> " + LocalDateTime.now() + "\n");
                loopProcess.destroyForcibly();
                processNumber += 1;
            }
            System.out.println("\n¡Los " + winNumber +  " procesos han finalizado correctamente!");

        } catch (IOException | InterruptedException exception) {
            System.out.println("ERROR! Parámetros de entrada incorrectos.");
        }
    }
}