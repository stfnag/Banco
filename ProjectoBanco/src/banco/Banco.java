package banco;
import java.io.*;
import java.time.*;

// 5.actualizacion: 5 min
// 4.retiro: 4 min
// 3.deposito: 3 min
// 2.pago de servicios: 2 min
// 1.consulta: 1.5 min

public class Banco {

    
    public static void main(String[] args) throws IOException {
        ProcesoRegistro procesoregistro = new ProcesoRegistro();
        LocalTime hora = LocalTime.of(8, 0);
        LocalTime horaCierre = LocalTime.of(16, 0);
        Cola cola  = new Cola(); 
        cola.cargarDoc();

        
        Integer orden = 1;
        while(hora.isBefore(horaCierre)){
            if (cola.isEmpty()) {
                System.out.println("Todos los clientes fueron atendidos, restan horas de trabajo: " + Math.abs(horaCierre.minusHours(hora.getHour()).getHour()));
                break;
            }
            Nodo ciudadano;
                if (orden <= 1){
                   ciudadano = cola.decencolar();
                }else {
                   ciudadano = cola.decencolar();
                }
                if (orden > 5 ) {
                    orden = 1;
                }

                switch (ciudadano.getCliente().getSolicitud().toLowerCase()){
                case "consulta":
                    hora = hora.plusMinutes(1).plusSeconds(50);
                break;
                case "pago":
                    hora = hora.plusMinutes(2);
                break;
                case "deposito":
                    hora = hora.plusMinutes(3);
                break;
                case "retiro":
                    hora = hora.plusMinutes(4);
                case "actualizacion":
                    hora = hora.plusMinutes(5);
                break;
            }
            
            procesoregistro.insertarData(ciudadano.getCliente());
            orden++;
        }
        procesoregistro.asignacionPrimario();
        cola.guardarPendientes();
    }
}

        
        
        
        
        
         
                 
        
    
        
    
