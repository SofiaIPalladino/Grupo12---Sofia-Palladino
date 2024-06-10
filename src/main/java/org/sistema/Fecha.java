package org.sistema;

import java.util.Date;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public static Date setFecha(int dia, int mes, int anio) {
        return new Date(anio-1900,mes-1,dia);
    }
}
