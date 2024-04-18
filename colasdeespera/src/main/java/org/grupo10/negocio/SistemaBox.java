package org.grupo10.negocio;

import org.grupo10.modelo.Turno;
import org.grupo10.modelo.dto.TurnoFinalizadoDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SistemaBox {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final String tipo = "Box";
    private static int num = 0;
    private int numeroBox;
    private static  ObjectOutputStream outputStream;
    private static  ObjectInputStream inputStream;
    private Socket socket;

   public SistemaBox (){
       Socket socket = null;
       try {
           socket = new Socket(HOST, PORT);
           outputStream = new ObjectOutputStream(socket.getOutputStream());
           inputStream = new ObjectInputStream(socket.getInputStream());
           this.numeroBox = ++SistemaBox.num;
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }


    public void ejecuccion(){
        try {
            System.out.println(tipo);
            outputStream.writeObject(tipo);
            outputStream.flush();
            while (true) {
                esperandoRespuestaServer();
                Object response = inputStream.readObject();
                System.out.println("Respuesta del servidor: " + response);
                //Aca lo que deberia hacer el box es pedir el siguiente turno para atender (si es que hay)
                //hay que ver si hay que hacer un hilo extra que chequee la cantidad de personas en espera

                Thread.sleep(1000);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void esperandoRespuestaServer(){
        try {
            outputStream.writeObject("Box numero "+ this.numeroBox +" a la espera");
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Turno pedirSiguiente() throws IOException, ClassNotFoundException {
        outputStream.writeObject("Pido siguiente");
        outputStream.flush();
        Object res = inputStream.readObject();
        System.out.println(res);
        Turno siguiente = (Turno) res;
        if(siguiente == null) {
            throw new IOException("No hay clientes esperando");
        }

        siguiente.setBox(this.numeroBox);

        outputStream.writeObject(siguiente);
        outputStream.flush();

        return siguiente;

    }

    public void finalizarTurno(Turno t) throws IOException {
        TurnoFinalizadoDTO turnoFinalizadoDTO = new TurnoFinalizadoDTO(t);

        outputStream.writeObject(turnoFinalizadoDTO);
        outputStream.flush();
    }
}