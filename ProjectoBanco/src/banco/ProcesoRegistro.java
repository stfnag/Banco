package banco;
import java.io.*;
import java.time.*;

// 4.actualizacion: 5 min
// 3.retiro: 4 min
// 2.deposito: 3 min
// 1.pago de servicios: 2 min
// 0.consulta: 1.5 min
public class ProcesoRegistro {
    PilaR conjuntoProceso[] = new PilaR[5];

    public ProcesoRegistro() {
        for (int i = 0; i <= 4; i++) {
            PilaR proceso = new PilaR();
            conjuntoProceso[i] = proceso;
        }             
    }
    void insertarData(Persona data){
        switch (data.getSolicitud()){
            case "consulta":
                conjuntoProceso[0].insertar(data);
            break;
            case "pago":
                conjuntoProceso[1].insertar(data);
            break;
            case "deposito":
                conjuntoProceso[2].insertar(data);
            break;
            case "retiro":
                conjuntoProceso[3].insertar(data);
            break;
            case "actualizacion":
                conjuntoProceso[4].insertar(data);
            break;
        }
    }
    void eliminarArchivo(){
        File eliminar = new File("Taquilla.txt");
        eliminar.deleteOnExit();      
    }
    void asignacionPrimario() throws IOException{
        LocalDate fechaTaquilla = LocalDate.now().minusDays(1);
        File verificacion = new File("Taquilla.txt");
            if(verificacion.exists()){
                String archivoPasado = "Taquilla " + fechaTaquilla.toString() + ".txt";
                verificacion.renameTo(new File ("OperacionesRegistradas",archivoPasado));
                this.eliminarArchivo();
                this.alcacenamientoSecundario();
            }else{
                this.alcacenamientoSecundario();
            }
            
    }
    void alcacenamientoSecundario() throws IOException{
        for (int i = 0; i <= 4; i++) {
            conjuntoProceso[i].guardarRegistro();
        }
    }
}
