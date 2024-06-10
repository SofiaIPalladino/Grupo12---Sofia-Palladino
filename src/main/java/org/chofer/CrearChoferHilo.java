package org.chofer;

import org.excepciones.NoChoferException;

public class CrearChoferHilo implements Runnable {
    private Chofer chofer;

    public CrearChoferHilo(Chofer chofer){
        this.chofer=chofer;
    }

    public void run() {
        try {
            chofer.buscaViajeChofer();
        } catch (NoChoferException e) {
            throw new RuntimeException(e);
        }
    }

}
