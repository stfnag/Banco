package banco;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


public class Cola {
    
    private Nodo inicio, finalc;
    
    
    void encolar(Persona personaCli){
        Nodo nodo = new Nodo();
        nodo.setCliente(personaCli);
        
        if (inicio == null) {
            this.inicio = this.finalc = nodo;
        }else{
            this.finalc.setSiguiente(nodo);
            this.finalc = this.finalc.getSiguiente();
        }
    }
    
    public Cola() throws IOException {
        this.inicio = null;
        this.finalc = null;   
        //this.cargarDoc();
    }
 
    Nodo decencolar(){
        if(this.isEmpty()){
            System.out.println("No hay nadie en la cola");
            return null;
        }else{
            Nodo temp = this.inicio;
            this.inicio = this.inicio.getSiguiente();
            temp.setSiguiente(null);
                System.out.println(temp.getCliente().toString());

            return temp;
        }

    }
    Nodo decencolarPri() throws IOException {
        Cola aux = new Cola();
        Nodo casopri = new Nodo();
        while(!this.isEmpty()){
            if(this.inicio.getCliente().isPrioridad() && casopri.getCliente() == null){
                casopri = this.inicio;
                this.decencolar();
            }else{
                Nodo temp1 = this.decencolar();
                aux.encolar(temp1.getCliente());
            }
        }
        this.inicio = aux.inicio;
        this.finalc = aux.finalc;
        if(casopri.getCliente() == null){
            System.out.println("no prioridad");
            return this.decencolar();
        }
        return casopri;
    }
    boolean isEmpty(){
        return inicio == null;
    }
    void leectura(Scanner doc){
        while(doc.hasNextLine()){
            String [] datos = doc.nextLine().split(";");
            // nombre, apellido, edad, cedula
            Persona cliente = new Persona();
            cliente.setNombre(datos[0]);
            cliente.setApellido(datos[1]);
            cliente.setCedula(datos[2]);
            cliente.setSolicitud(datos[3]);
            cliente.setPrioridad(Boolean.parseBoolean(datos[4]));
            this.encolar(cliente);
        }
    }
    void cargarDoc() throws IOException{
        File pendienteClientes = new File("clientesPendientes.txt");
        Scanner nuevosclientes = new Scanner(new File("clientes.txt"));
        if(pendienteClientes.exists()){
            Scanner pendientes = new Scanner(new File("clientesPendientes.txt"));
            this.leectura(pendientes);
            this.leectura(nuevosclientes);
        }else{
            this.leectura(nuevosclientes);
        }
        
        //pendienteClientes.deleteOnExit();
    }
        
    void guardarPendientes() throws IOException {

        String archivo = "clientesPendientes.txt";
        while(!this.isEmpty()) {
            Persona cliente = this.decencolar().getCliente();
                try ( FileWriter fw = new FileWriter(archivo, true);  BufferedWriter bw = new BufferedWriter(fw);  PrintWriter out = new PrintWriter(bw)) {
                    out.println(cliente.toString());
                } catch (IOException e) {
                //exception handling left as an exercise for the reader
                }
           // }
        }
    }
}

    

