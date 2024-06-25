package org.controladores;

import org.persistencia.PersistirDatos;
import org.sistema.Empresa;
import org.viaje.IViaje;
import org.vista.VentanaSistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ControladorSistema implements ActionListener,Observer {
    private VentanaSistema ventanaSistema;

    public ControladorSistema() {
        this.ventanaSistema = new VentanaSistema(this);
        this.ventanaSistema.setVisible(true);
        Empresa.getInstance().addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        switch (comando) {
            case "Persistir":
                persistirData();
                break;
        }
    }

    private void persistirData() {
        PersistirDatos persistirDatos=new PersistirDatos();
        try {
            persistirDatos.persistoDatos();
            this.ventanaSistema.mostrarMensaje("Se persistieron");
        } catch (IOException e) {
            this.ventanaSistema.mostrarMensaje("No se persistieron los datos");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Empresa && arg instanceof String) {
            String informacion = (String) arg;
            this.ventanaSistema.mostrarInfoHilos(informacion);
        }
    }
}
