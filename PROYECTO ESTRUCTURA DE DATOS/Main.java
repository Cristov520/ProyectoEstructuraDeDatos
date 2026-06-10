import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);
    public static ListaEnlazada listaEnlazada = new ListaEnlazada();

    public static void main(String[] args) {
        // 10 viajes
        Viaje.cargarViajes();
        menu();
    }

    private static void menu() {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("==================================================");
            System.out.println("              EASY TRAVEL ✈");
            System.out.println("         Sistema de Gestión de Viajes");
            System.out.println("==================================================");
            System.out.println("  Cristopher Robin Vázquez Alarcón  (ZS24015570)");
            System.out.println("  Juan Carlos Altamirano Martínez   (ZS24015561)");
            System.out.println("==================================================\n");
            System.out.println("1.- Administración de viajes \n2.-Reservaciones \n3.- Reportes \n4.- Salir");
            System.out.print("Selecciona una opcion: ");
            try {
                opcion = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }
            switch (opcion) {
                case 1 -> moduloAdministracionViajes();
                case 2 -> moduloReservaciones();
                case 3 -> moduloReportes();
                case 4 -> {
                    input.close();
                    return;
                }
                default -> System.out.println("Error, ingresa una opcion valida");
            }

        }

    }

    // Modulos principales
    public static void moduloAdministracionViajes() {
        int num = 0;
        while (num != 4) {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println("                 AGENCIA DE VIAJES \"EASY TRAVEL\"");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("  Integrantes:");
            System.out.println("  • Cristopher Robin Vázquez Alarcón   (ZS24015570)");
            System.out.println("  • Juan Carlos Altamirano Martínez    (ZS24015561)");
            System.out.println();
            System.out.println("--------------------------------------------------------------");
            System.out.println("                 ADMINISTRACIÓN DE VIAJES");
            System.out.println("--------------------------------------------------------------");
            System.out.println();
            System.out.println("  1. Agregar viaje");
            System.out.println("  2. Modificar viaje");
            System.out.println("  3. Eliminar viaje");
            System.out.println("  4. Regresar al menú principal");
            System.out.println();
            System.out.print("Seleccione una opción: ");

            try {
                num = Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                num = 0;
            }

            switch (num) {
                case 1 -> listaEnlazada.insertarFinal(Viaje.crearViaje());
                case 2 -> modificarViajes();
                case 3 -> {

                    int id = 0;
                    do {
                        System.out.println("Ingrese el id del viaje a eliminar:");
                        try {
                            id = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            id = -1;
                            System.out.println("Error, ingrese un id valido");
                        }
                    } while (id <= 0);
                    Viaje viaje = listaEnlazada.obtener(id);

                    if (viaje == null) {
                        System.out.println("No existe un viaje con ese ID");
                    } else {
                        listaEnlazada.eliminarViaje(id);
                        System.out.println("Viaje eliminado correctamente");
                    }
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Error, ingresa una opcion valida");

            }
        }

    }

    public static void moduloReservaciones() {
        int num = 0;

        while (num != 4) {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println("                 AGENCIA DE VIAJES \"EASY TRAVEL\"");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("  Integrantes:");
            System.out.println("  • Cristopher Robin Vázquez Alarcón   (ZS24015570)");
            System.out.println("  • Juan Carlos Altamirano Martínez    (ZS24015561)");
            System.out.println();
            System.out.println("--------------------------------------------------------------");
            System.out.println("                 MODULO DE RESERVACIONES");
            System.out.println("--------------------------------------------------------------");
            System.out.println();
            System.out.println("  1. Crear reservacion");
            System.out.println("  2. Modificar reservacion");
            System.out.println("  3. Eliminar reservacion");
            System.out.println("  4. Regresar al menú principal");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            try {
                num = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                num = 0;
            }

            switch (num) {
                case 1 -> Viaje.hacerRecerva(Viaje.buscarXId());
                case 2 -> Viaje.modificarReserva(Viaje.buscarXId());
                case 3 -> Viaje.eliminarReserva(Viaje.buscarXId());
                case 4 -> {
                    return;
                }
                default -> System.out.println("Error, ingresa una opcion valida");
            }

        }
    }

    public static void moduloReportes() {
        int num = 0;

        while (num != 3) {
            System.out.println();
            System.out.println("==============================================================");
            System.out.println("                 AGENCIA DE VIAJES \"EASY TRAVEL\"");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("  Integrantes:");
            System.out.println("  • Cristopher Robin Vázquez Alarcón   (ZS24015570)");
            System.out.println("  • Juan Carlos Altamirano Martínez    (ZS24015561)");
            System.out.println();
            System.out.println("--------------------------------------------------------------");
            System.out.println("                 MODULO DE REPORTES");
            System.out.println("--------------------------------------------------------------");
            System.out.println();
            System.out.println("  1. Catalogo de viajes");
            System.out.println("  2. Recervacion de cada viaje");
            System.out.println("  3. Regresar al menú principal");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            try {
                num = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                num = 0;
            }
            switch (num) {
                case 1 -> listaEnlazada.imprimirViajes();
                case 2 -> Viaje.buscarXId().mostrarReservas();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Error, ingresa una opcion valida");
            }
        }

    }

    // Menus secundarios
    public static void modificarViajes() {
        int num = 0;

        while (num != 6) {
            System.out.println("======== Modificar viaje ========");
            System.out.println("1.- Modificar medio de transporte");
            System.out.println("2.- Modificar capacidad de pasajeros");
            System.out.println("3.- Modificar destino");
            System.out.println("4.- Modificar fecha de salida");
            System.out.println("5.- Modificar fecha de llegada");
            System.out.println("6.- Regresar");
            System.out.print("Ingrese una opcion: ");
            try {
                num = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                num = 0;
            }
            Viaje v = Viaje.buscarXId();
            if (v != null) {

                switch (num) {
                    case 1 -> Viaje.modificarmedioTransporte(v);
                    case 2 -> Viaje.modificarcapacidad(v);
                    case 3 -> Viaje.modificarDestino(v);
                    case 4 -> Viaje.modificarFechaSalida(v);
                    case 5 -> Viaje.modificarFechaLlegada(v);
                    case 6 -> {
                        return;
                    }
                    default -> System.out.println("Error, ingresa una opcion valida");

                }

            } else {
                System.out.println("Error, no existe un viaje con ese id");
            }

        }

    }

}
