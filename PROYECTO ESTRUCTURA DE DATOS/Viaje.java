import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Queue;

public class Viaje {
    private int id;
    private int capacidad; //
    private Queue<Persona> reservas;
    private String medioTransporte;//
    private String destino; //
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private static int idAutoIncrement = 1;
    private final static DateTimeFormatter FORMATO_MEXICO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Viaje(int capacidad, String medioTransporte, String destino, LocalDateTime fechaSalida,
            LocalDateTime fechaLlegada) {
        this.id = idAutoIncrement;
        idAutoIncrement++;
        this.capacidad = capacidad;
        this.reservas = new LinkedList<>();
        this.medioTransporte = medioTransporte;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
    }

    public int getId() {
        return id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Queue<Persona> getReservas() {
        return reservas;
    }

    public String getMedioTransporte() {
        return medioTransporte;
    }

    public void setMedioTransporte(String medioTransporte) {
        this.medioTransporte = medioTransporte;
    }

    public String getDestino() {
        return destino;
    }

    // Devuelve las fechas con un formato dia/mes/año hora:minuto
    public String fechaFormato(LocalDateTime fecha) {
        return fecha.format(FORMATO_MEXICO);
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    @Override
    public String toString() {
        return String.format(
                "%-5d %-15d %-20d %-15s %-20s %-20s %-20s",
                id,
                capacidad,
                reservas.size(),
                medioTransporte,
                destino,
                fechaFormato(fechaSalida),
                fechaFormato(fechaLlegada));
    }

    // Metodo para crear un nuevo viaje
    public static Viaje crearViaje() {
        System.out.println("========Crear viaje========");

        String destino;
        while (true) {
            System.out.print("Ingresa el destino: ");

            destino = Main.input.nextLine().trim();
            if (destino.isEmpty()) {
                System.out.println("Error, debes ingresar un destino valido");
            } else {
                break;
            }
        }

        String medioTransporte;
        while (true) {
            System.out.print("Ingresa el medio de transporte: ");

            medioTransporte = Main.input.nextLine().trim();
            if (medioTransporte.isEmpty()) {
                System.out.println("Error, debes ingresar un medio de transporte valido");
            } else {
                break;
            }
        }

        int capacidad;
        while (true) {
            System.out.print("Ingresa la  capacidad del viaje: ");

            try {
                capacidad = Integer.parseInt(Main.input.nextLine());

                if (capacidad <= 0) {
                    System.out.println("Error, la capacidad debe de ser mayor a 0");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error, tienes que ingresar un numero valido");
            }
        }

        LocalDateTime fechaSalida;
        while (true) {
            try {
                System.out.print("Ingresa la fecha de salida (dd/MM/yyyy HH:mm): ");
                String fecha = Main.input.nextLine().trim();
                fechaSalida = LocalDateTime.parse(fecha, FORMATO_MEXICO);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Error, el formato de fecha es invalido. Usa dd/MM/yyyy HH:mm");
            }
        }

        LocalDateTime fechaLlegada;
        while (true) {
            try {
                System.out.print("Ingresa la fecha de llegada (dd/MM/yyyy HH:mm): ");
                String fecha = Main.input.nextLine().trim();
                fechaLlegada = LocalDateTime.parse(fecha, FORMATO_MEXICO);

                if (!fechaLlegada.isAfter(fechaSalida)) {
                    System.out.println("Error, la fecha de llegada debe ser posterior a la fecha de salida");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Error, el formato de fecha es invalido. Usa dd/MM/yyyy HH:mm");
            }
        }

        return new Viaje(capacidad, medioTransporte, destino, fechaSalida, fechaLlegada);
    }

    // Metodos para modificar los datos de un viaje
    public static void modificarcapacidad(Viaje v) {
        System.out.println("========Modificar Capacidad========");
        System.out.println("La capacidad actual del viaje es: " + v.getCapacidad());

        while (true) {
            System.out.print("Ingrese la nueva capacidad del viaje: ");

            try {
                int newCapacidad = Integer.parseInt(Main.input.nextLine());

                if (newCapacidad <= 0) {
                    System.out.println("Error, la capacidad debe de ser mayor a 0");
                } else {
                    v.setCapacidad(newCapacidad);
                    System.out.println("Nueva capacidad ingresada correctamente");
                    return;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error, tienes que ingresar un numero valido");
            }
        }
    }

    public static void modificarmedioTransporte(Viaje v) {
        System.out.println("======Modificar Medio de Transporte======");
        System.out.println("El medio de transporte actual es: " + v.getMedioTransporte());

        while (true) {
            System.out.print("Ingrese el nuevo medio de transporte: ");

            String nuevo = Main.input.nextLine().trim();
            if (nuevo.isEmpty()) {
                System.out.println("Error, debes ingresar un medio de transporte valido");
            } else {
                v.setMedioTransporte(nuevo);
                System.out.println("Medio de transporte actualizado correctamente");
                return;
            }
        }
    }

    public static void modificarDestino(Viaje v) {
        System.out.println("========Modificar Destino========");
        System.out.println("El destino actual es: " + v.getDestino());

        while (true) {
            System.out.print("Ingrese el nuevo destino: ");

            String nuevo = Main.input.nextLine().trim();
            if (nuevo.isEmpty()) {
                System.out.println("Error, debes ingresar un destino valido");
            } else {
                v.setDestino(nuevo);
                System.out.println("Destino actualizado correctamente");
                return;
            }
        }
    }

    public static void modificarFechaSalida(Viaje v) {
        System.out.println("========Modificar Fecha de Salida (formato dd/MM/yyyy HH:mm)========");
        System.out.println("Fecha de salida actual: " + v.fechaFormato(v.getFechaSalida()));
        while (true) {
            try {
                System.out.print("Ingrese la nueva fecha de salida (dd/MM/yyyy HH:mm): ");
                String fecha = Main.input.nextLine().trim();
                LocalDateTime nuevaSalida = LocalDateTime.parse(fecha, FORMATO_MEXICO);

                if (!v.getFechaLlegada().isAfter(nuevaSalida)) {
                    System.out.println("Error, la fecha de salida debe ser anterior a la fecha de llegada actual");
                } else {
                    v.setFechaSalida(nuevaSalida);
                    System.out.println("Fecha de salida actualizada correctamente");
                    return;

                }

            } catch (DateTimeParseException e) {
                System.out.println("Error, el formato de fecha es invalido. Usa dd/MM/yyyy HH:mm");
            }
        }
    }

    public static void modificarFechaLlegada(Viaje v) {
        System.out.println("========Modificar Fecha de Llegada (formato dd/MM/yyyy HH:mm)========");
        System.out.println("Fecha de llegada actual: " + v.fechaFormato(v.getFechaLlegada()));
        while (true) {
            try {
                System.out.print("Ingrese la nueva fecha de llegada (dd/MM/yyyy HH:mm): ");
                String fecha = Main.input.nextLine().trim();
                LocalDateTime nuevaLlegada = LocalDateTime.parse(fecha, FORMATO_MEXICO);

                if (!nuevaLlegada.isAfter(v.getFechaSalida())) {
                    System.out.println("Error, la fecha de llegada debe ser posterior a la fecha de salida actual");
                } else {
                    v.setFechaLlegada(nuevaLlegada);
                    System.out.println("Fecha de llegada actualizada correctamente");
                    return;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Error, el formato de fecha es invalido. Usa dd/MM/yyyy HH:mm");
            }
        }
    }

    // Metodos para la gestion de las reservas
    public static void hacerRecerva(Viaje v) {
        System.out.println("========Hacer reserva========");

        if (v.getReservas().size() >= v.getCapacidad()) {
            System.out.println("Error, el viaje ya alcanzó la capacidad máxima de reservas");
            return;
        }

        String nombre;
        while (true) {
            System.out.print("Ingrese el nombre del pasajero: ");
            nombre = Main.input.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("Error, debes ingresar un nombre válido");
            } else {
                break;
            }
        }

        String telefono;
        while (true) {
            System.out.print("Ingrese el número de teléfono del pasajero: ");
            telefono = Main.input.nextLine().trim();

            if (telefono.isEmpty()) {
                System.out.println("Error, debes ingresar un número de teléfono válido");
            } else {
                break;
            }
        }

        Persona persona = new Persona(nombre, telefono);
        v.getReservas().add(persona);
        System.out.println("Reserva realizada correctamente. ID de la persona que hizo la reserva: " + persona.getId());
    }

    public static void eliminarReserva(Viaje v) {
        System.out.println("========Eliminar reserva========");

        if (v.getReservas().isEmpty()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        int idReserva;
        while (true) {
            System.out.print("Ingrese el ID de la persona que hizo la reserva: ");

            try {
                idReserva = Integer.parseInt(Main.input.nextLine());

                if (idReserva <= 0) {
                    System.out.println("Error, debes ingresar un ID válido");
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error, tienes que ingresar un número válido");
            }
        }

        Persona personaEliminar = null;

        for (Persona persona : v.getReservas()) {
            if (persona.getId() == idReserva) {
                personaEliminar = persona;
                break;
            }
        }

        if (personaEliminar != null) {
            v.getReservas().remove(personaEliminar);
            System.out.println("Reserva eliminada correctamente");
        } else {
            System.out.println("No se encontró ninguna reserva con ese ID");
        }
    }

    public static void modificarReserva(Viaje v) {
        System.out.println("========Modificar reserva========");

        if (v.getReservas().isEmpty()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        int idReserva;
        while (true) {
            System.out.print("Ingrese el ID de la persona que hizo la reserva: ");

            try {
                idReserva = Integer.parseInt(Main.input.nextLine());

                if (idReserva <= 0) {
                    System.out.println("Error, debes ingresar un ID válido");
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error, tienes que ingresar un número válido");
            }
        }

        Persona personaModificar = null;

        for (Persona persona : v.getReservas()) {
            if (persona.getId() == idReserva) {
                personaModificar = persona;
                break;
            }
        }

        if (personaModificar == null) {
            System.out.println("No se encontró ninguna reserva con ese ID");
            return;
        }

        String nombre;
        while (true) {
            System.out.print("Ingrese el nuevo nombre del pasajero: ");
            nombre = Main.input.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("Error, debes ingresar un nombre válido");
            } else {
                personaModificar.setNombre(nombre);
                break;
            }
        }

        String telefono;
        while (true) {
            System.out.print("Ingrese el nuevo número de teléfono del pasajero: ");
            telefono = Main.input.nextLine().trim();

            if (telefono.isEmpty()) {
                System.out.println("Error, debes ingresar un número de teléfono válido");
            } else {
                personaModificar.setNumeroTelefono(telefono);
                break;
            }
        }

        System.out.println("Reserva modificada correctamente");
    }

    public void mostrarReservas() {
        System.out.println("========Reservas del viaje========");

        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        System.out.printf(
                "%-10s %-30s %-20s%n",
                "ID",
                "Nombre",
                "Telefono");

        for (Persona persona : reservas) {
            System.out.printf(
                    "%-10d %-30s %-20s%n",
                    persona.getId(),
                    persona.getNombre(),
                    persona.getNumeroTelefono());
        }
    }

    public static Viaje buscarXId() {
        System.out.println("======== Buscar viaje ========");
        int n = 0;
        while (n == 0) {
            try {
                System.out.println("Ingrese el id del viaje: ");
                n = Integer.parseInt(Main.input.nextLine());

                if (n <= 0) {
                    System.out.println("Error, ingresa un id valido");
                }
            } catch (Exception e) {
                System.out.println("Error, ingresa un id valido");
            }
        }

        return Main.listaEnlazada.obtener(n);
    }

    // Metodo para crear 10 viajes automaticamente
    public static void cargarViajes() {

        Main.listaEnlazada.insertarFinal(new Viaje(
                40,
                "Autobús",
                "Ciudad de México",
                LocalDateTime.of(2026, 7, 10, 8, 0),
                LocalDateTime.of(2026, 7, 10, 14, 30)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                25,
                "Avión",
                "Cancún",
                LocalDateTime.of(2026, 7, 15, 6, 45),
                LocalDateTime.of(2026, 7, 15, 9, 0)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                30,
                "Tren",
                "Guadalajara",
                LocalDateTime.of(2026, 7, 18, 9, 0),
                LocalDateTime.of(2026, 7, 18, 13, 15)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                50,
                "Autobús",
                "Monterrey",
                LocalDateTime.of(2026, 7, 20, 7, 30),
                LocalDateTime.of(2026, 7, 20, 18, 0)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                20,
                "Avión",
                "Mérida",
                LocalDateTime.of(2026, 7, 22, 10, 15),
                LocalDateTime.of(2026, 7, 22, 12, 10)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                35,
                "Barco",
                "Cozumel",
                LocalDateTime.of(2026, 7, 25, 8, 0),
                LocalDateTime.of(2026, 7, 25, 16, 30)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                28,
                "Autobús",
                "Puebla",
                LocalDateTime.of(2026, 7, 28, 5, 30),
                LocalDateTime.of(2026, 7, 28, 9, 45)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                15,
                "Avión",
                "Tijuana",
                LocalDateTime.of(2026, 8, 2, 11, 0),
                LocalDateTime.of(2026, 8, 2, 14, 20)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                45,
                "Autobús",
                "Veracruz",
                LocalDateTime.of(2026, 8, 5, 6, 0),
                LocalDateTime.of(2026, 8, 5, 12, 0)));

        Main.listaEnlazada.insertarFinal(new Viaje(
                18,
                "Avión",
                "Los Cabos",
                LocalDateTime.of(2026, 8, 10, 7, 15),
                LocalDateTime.of(2026, 8, 10, 10, 50)));
    }
}
