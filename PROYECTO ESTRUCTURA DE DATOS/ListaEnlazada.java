public class ListaEnlazada {
    private Nodo cabeza;
    private int tamanio = 0;

    private class Nodo {
        public Viaje valor;
        public Nodo siguiente = null;

        public Nodo(Viaje viaje) {
            this.valor = viaje;
        }
    }

    public int getTamanio() {
        return tamanio;
    }

    public void insertarInicio(Viaje viaje) {
        Nodo nodo = new Nodo(viaje);
        nodo.siguiente = cabeza;
        cabeza = nodo;
        tamanio++;
    }

    public void insertarFinal(Viaje viaje) {
        Nodo nodo = new Nodo(viaje);
        Nodo puntero = cabeza;

        if (cabeza == null) {
            cabeza = nodo;
        } else {

            while (puntero.siguiente != null) {
                puntero = puntero.siguiente;
            }
            puntero.siguiente = nodo;
        }
        tamanio++;
    }

    public Viaje obtener(int id) {
        Nodo puntero = cabeza;

        while (puntero != null) {
            if (puntero.valor.getId() == id) {
                return puntero.valor;
            }

            puntero = puntero.siguiente;
        }

        return null;
    }

    public void eliminarInicio() {

        if (cabeza != null) {
            Nodo primer = cabeza;
            cabeza = cabeza.siguiente;
            primer.siguiente = null;
            tamanio--;
        }
    }

    public void eliminarFinal() {
        if (cabeza != null) {
            if (cabeza.siguiente == null) {
                cabeza = null;
                return;
            }

            Nodo puntero = cabeza;

            while (puntero.siguiente.siguiente != null) {
                puntero = puntero.siguiente;

            }
            puntero.siguiente = null;
            tamanio--;
        }
    }

    public void eliminarViaje(int id) {
        if (cabeza == null)
            return;

        if (cabeza.valor.getId() == id) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return;
        }

        Nodo actual = cabeza;

        while (actual.siguiente != null) {
            if (actual.siguiente.valor.getId() == id) {
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
                return;
            }

            actual = actual.siguiente;
        }
    }

    public void imprimirViajes() {
        if (cabeza == null) {
            System.out.println("No hay viajes.");
            return;
        }

        System.out.printf(
                "%-5s %-12s %-12s %-12s %-15s %-20s %-20s %-20s%n",
                "ID",
                "Capacidad",
                "Reservas",
                "Disponibles",
                "Transporte",
                "Destino",
                "Salida",
                "Llegada");

        Nodo puntero = cabeza;

        while (puntero != null) {
            Viaje v = puntero.valor;

            int disponibles = v.getCapacidad() - v.getReservas().size();

            System.out.printf(
                    "%-5d %-12d %-12d %-12d %-15s %-20s %-20s %-20s%n",
                    v.getId(),
                    v.getCapacidad(),
                    v.getReservas().size(),
                    disponibles,
                    v.getMedioTransporte(),
                    v.getDestino(),
                    v.fechaFormato(v.getFechaSalida()),
                    v.fechaFormato(v.getFechaLlegada()));

            puntero = puntero.siguiente;
        }
    }
}
