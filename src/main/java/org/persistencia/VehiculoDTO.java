package org.persistencia;

import org.vehiculo.Vehiculo;

import java.io.Serializable;

public class VehiculoDTO implements Serializable {
    private String numpatente;
    private String tipo;

    public VehiculoDTO(){
    }

    public VehiculoDTO(Vehiculo vehiculo) {
        this.numpatente=vehiculo.getNumpatente();
        this.tipo= vehiculo.getTipo();
    }



    public String getNumpatente() {
        return numpatente;
    }

    public void setNumpatente(String numpatente) {
        this.numpatente = numpatente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
