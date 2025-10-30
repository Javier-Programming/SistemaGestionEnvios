import com.logiexpress.model.*;
import com.logiexpress.service.GestorEnvios;
import com.logiexpress.enums.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Instanciar el gestor de envíos
        GestorEnvios gestor = new GestorEnvios();
        // Scanner para entrada de usuario
        try (Scanner sc = new Scanner(System.in)) {
            // Datos de prueba
            gestor.agregarEnvio(new EnvioTerrestre("Sincelejo", "Medellín", 200, Prioridad.NORMAL, 400));
            gestor.agregarEnvio(new EnvioAereo("Sincelejo", "Bogotá", 100, Prioridad.EXPRESS, false));
            gestor.agregarEnvio(new EnvioMaritimo("Coveñas", "La Paz - Bolivia", 1500, "La Paz - Bolivia"));
            // Variable para opción del menú
            int opcion;
            // Bucle principal
            do {
                // Menú
                System.out.println("\n--- MENU LOGIEXPRESS ---");
                System.out.println("1. Nuevo envío");
                System.out.println("2. Listar envíos");
                System.out.println("3. Buscar envío por ID");
                System.out.println("4. Ver costo total");
                System.out.println("5. Generar reporte");
                System.out.println("6. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                // Procesar opción
                switch (opcion) {
                    // Nuevo envío
                    case 1 -> {
                        System.out.println("Tipo de envío (1-Terrestre, 2-Aéreo, 3-Maritimo): ");
                        int tipoEnvio = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Origen: ");
                        String origen = sc.nextLine();
                        System.out.print("Destino: ");
                        String destino = sc.nextLine();
                        System.out.print("Peso (kg): ");
                        double peso = sc.nextDouble();
                        sc.nextLine();

                        // Crear y agregar el envío según el tipo
                        switch (tipoEnvio) {
                            // Envio Terrestre
                            case 1 -> {
                                System.out.print("Prioridad (1-NORMAL, 2-EXPRESS): ");
                                int priori = sc.nextInt();
                                sc.nextLine();
                                Prioridad prioridad;
                                if (priori == 1) {
                                    prioridad = Prioridad.NORMAL;
                                } else {
                                    prioridad = Prioridad.EXPRESS;
                                }
                                System.out.print("Distancia (km): ");
                                double distancia = sc.nextDouble();
                                sc.nextLine();
                                gestor.agregarEnvio(new EnvioTerrestre(origen, destino, peso, prioridad, distancia));
                            }
                            // Envio Aéreo
                            case 2 -> {
                                System.out.print("Prioridad (1-NORMAL, 2-EXPRESS): ");
                                int priori = sc.nextInt();
                                sc.nextLine();
                                Prioridad prioridad;
                                if (priori == 1) {
                                    prioridad = Prioridad.NORMAL;
                                } else {
                                    prioridad = Prioridad.EXPRESS;
                                }
                                System.out.print("¿Es internacional?: ");
                                boolean esInternacional = sc.nextBoolean();
                                sc.nextLine();
                                gestor.agregarEnvio(new EnvioAereo(origen, destino, peso, prioridad, esInternacional));
                            }
                            // Envio Marítimo
                            case 3 -> {
                                System.out.print("Puerto destino: ");
                                String puertoDestino = sc.nextLine();
                                gestor.agregarEnvio(new EnvioMaritimo(origen, destino, peso, puertoDestino));
                            }
                            default -> System.out.println("Tipo de envío incorrecto.");
                        }
                    }
                    // Listar envíos
                    case 2 -> gestor.listarTodosLosEnvios();
                    // Buscar envío por ID
                    case 3 -> {
                        System.out.print("Ingrese ID: ");
                        String id = sc.nextLine();
                        Envio envioEncontrado = gestor.buscarEnvioPorId(id);
                        if (envioEncontrado != null) {
                            System.out.println(envioEncontrado);
                        } else {
                            System.out.println("Envío no encontrado.");
                        }
                    }
                    // Ver costo total
                    case 4 -> System.out.printf("Costo total: $%.2f%n", gestor.calcularCostoTotal());
                    // Generar reporte
                    case 5 -> gestor.generarReporte();
                    // Salir
                    case 6 -> System.out.println("Saliendo...");
                    // Opción incorrecta
                    default -> System.out.println("Opción incorrecta.");
                }
            } while (opcion != 6);
        }
    }
}
