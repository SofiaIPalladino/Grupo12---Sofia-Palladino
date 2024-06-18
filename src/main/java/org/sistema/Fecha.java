package org.sistema;

import java.util.Date;

/**
 * Clase que se encarga de proporcionar un método estático llamado setFecha
 * que crea y devuelve un objeto de tipo Date con la fecha especificada.
 */
public class Fecha {
    private int dia;
    private int mes;
    private int anio;


    public static Date setFecha(int dia, int mes, int anio) {
        return new Date(anio-1900,mes-1,dia);
    }
}
