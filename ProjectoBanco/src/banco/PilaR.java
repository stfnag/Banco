
package banco;
import java.io.*;
import java.time.*;
/**
 *
 * @author Carvajal-Triviño
 */
class NodoP{
    private Persona clienteA; 
    private NodoP down;

    public Persona getClienteA() {
        return clienteA;
    }

    public void setClienteA(Persona clienteA) {
        this.clienteA = clienteA;
    }

    public NodoP getDown() {
        return down;
    }

    public void setDown(NodoP down) {
        this.down = down;
    }
    
}
public class PilaR {
    private NodoP top;

    void insertar(Persona k){
        NodoP nodoP = new NodoP();
        nodoP.setClienteA(k);
        if(top == null){
            top = nodoP;
        }else{
            nodoP.setDown(top);
            this.top = nodoP;
        }
    }
    boolean isEmpty(){
        return this.top == null; 
    }
    NodoP pop(){
        NodoP temp;
        if (this.isEmpty()){
            System.out.println("Pila Vacia");
            return null;
        }else{
            temp = this.top;
            this.top = this.top.getDown();
            temp.setDown(null);
            return temp;
        }
    }
         void guardarRegistro() throws IOException{
         while(!this.isEmpty()){
            NodoP aux = this.top;
            String archivo = "Taquilla.txt";
            try(FileWriter fw = new FileWriter(archivo, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                        out.println(aux.getClienteA().toString());
                    
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
            this.pop();
        }
           
    }
        
        
}

