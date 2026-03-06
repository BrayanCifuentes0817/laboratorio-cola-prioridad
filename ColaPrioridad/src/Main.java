import java.util.Comparator;
import java.util.PriorityQueue;


class Ticket{
    int id;
    String cliente;
    int prioridad;
    long timestamp;
    String descripcion;

    public Ticket( int id, String cliente,int prioridad,  long timestamp, String descripcion ) {
        this.descripcion = descripcion;
        this.timestamp = timestamp;
        this.prioridad = prioridad;
        this.cliente = cliente;
        this.id = id;
    }

    @Override
    public String toString(){
        return "Cliente: " + cliente + ", Prioridad: " + prioridad + ", Descripción: " + descripcion;
    }

}

class SupportCenter {

    private PriorityQueue<Ticket> queue;

    public SupportCenter() {

        Comparator<Ticket> comparator = (a, b) -> {
            if (a.prioridad != b.prioridad) {
                return Integer.compare(b.prioridad, a.prioridad); // prioridad DESC
            }
            return Long.compare(a.timestamp, b.timestamp); // timestamp ASC
        };

        queue = new PriorityQueue<>(comparator);
    }

    public void addTicket(Ticket t) {
        queue.add(t);
    }

    public Ticket nextTicket() {
        return queue.peek();
    }

    public Ticket attendTicket() {
        return queue.poll();
    }

    public int pendingCount() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {

        SupportCenter center = new SupportCenter();

        center.addTicket(new Ticket(1, "Ana", 3, 100, "No puedo iniciar sesión"));
        center.addTicket(new Ticket(2, "Luis", 5, 120, "Sistema caído"));
        center.addTicket(new Ticket(3, "Marta", 5, 110, "Error de pagos"));
        center.addTicket(new Ticket(4, "Pedro", 2, 130, "Consulta general"));
        center.addTicket(new Ticket(5, "Sofía", 3, 90, "Cambio de contraseña"));

        System.out.println("Orden de atención");

        while(!center.isEmpty()){
            System.out.println("Atendiendo = " + center.attendTicket());
        }
    }
}