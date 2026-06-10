public class Persona {
    private int id;
    private String nombre;
    private String numeroTelefono;
    private static int idAutoIncrement = 1;

    public Persona(String nombre, String numeroTelefono) {
        this.id = idAutoIncrement;
        idAutoIncrement++;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public static Persona crearPersona() {
        System.out.println("======== Registrar persona ========");
        System.out.println("El id de la persona es: " + idAutoIncrement);
        System.out.print("Ingresa el nombre: ");
        String nombre = Main.input.nextLine();
        System.out.println("Ingresa el numero de telefono: ");
        String numero = Main.input.nextLine();

        return new Persona(nombre, numero);

    }

    

}
