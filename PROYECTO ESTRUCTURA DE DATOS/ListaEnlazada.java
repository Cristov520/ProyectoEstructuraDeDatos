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

    public void eliminarViaje(int i) {
        if (cabeza != null) {
            if (i == 0) {
                Nodo primer = cabeza;
                cabeza = cabeza.siguiente;
                primer.siguiente = null;
                tamanio--;
            } else if (i < tamanio) {
                Nodo puntero = cabeza;
                int contador = 0;

                while (contador < (i - 1)) {
                    puntero = puntero.siguiente;
                    contador++;
                }
                Nodo temp = puntero.siguiente;
                puntero.siguiente = temp.siguiente;
                temp.siguiente = null;
                tamanio--;
            }
        }
    }

    public void imprimirViajes() {
    if (cabeza == null) {
        System.out.println("No hay viajes.");
        return;
    }

    System.out.printf(
        "%-5s %-15s %-20s %-15s %-20s %-20s %-20s%n",
        "ID",
        "Capacidad",
        "Comprados",
        "Transporte",
        "Destino",
        "Salida",
        "Llegada"
    );

    Nodo puntero = cabeza;

    while (puntero != null) {
        Viaje v = puntero.valor;

        System.out.printf(
            "%-5d %-15d %-20d %-15s %-20s %-20s %-20s%n",
            v.getId(),
            v.getCapacidad(),
            v.getReservas().size(),
            v.getMedioTransporte(),
            v.getDestino(),
            v.fechaFormato(v.getFechaSalida()),
            v.fechaFormato(v.getFechaLlegada())
        );

        puntero = puntero.siguiente;
    }
}


}
