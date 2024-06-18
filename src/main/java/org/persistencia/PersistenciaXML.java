package org.persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Clase que implementa la persistencia de datos en formato XML.
 */
public class PersistenciaXML implements IPersistencia
{
    private XMLEncoder xmlEncoder;
    private XMLDecoder xmlDecoder;
    private FileOutputStream fileoutput;
    private FileInputStream fileinput;


    /**
     * Abre un archivo de entrada para leer datos en formato XML.
     *
     * @param nombre nombre del archivo de entrada
     * @throws IOException si ocurre un error al abrir el archivo
     */
    @Override
    public void abrirInput(String nombre) throws IOException
    {
        fileinput = new FileInputStream(nombre);
        xmlDecoder = new XMLDecoder(fileinput);

    }

    /**
     * Abre un archivo de salida para escribir datos en formato XML.
     *
     * @param nombre nombre del archivo de salida
     * @throws IOException si ocurre un error al abrir el archivo
     */
    @Override
    public void abrirOutput(String nombre) throws IOException
    {
        fileoutput = new FileOutputStream(nombre);
        xmlEncoder = new XMLEncoder(fileoutput);


    }

    /**
     * Cierra el archivo de salida.
     *
     * @throws IOException si ocurre un error al cerrar el archivo
     */
    @Override
    public void cerrarOutput() throws IOException
    {
        this.xmlEncoder.close();
    }

    /**
     * Cierra el archivo de entrada.
     *
     * @throws IOException si ocurre un error al cerrar el archivo
     */

    @Override
    public void cerrarInput() throws IOException
    {
        this.xmlDecoder.close();
    }

    /**
     * Escribe un objeto en el archivo de salida en formato XML.
     *
     * @param objeto objeto a escribir
     * @throws IOException si ocurre un error al escribir el objeto
     */
    @Override
    public void escribir(Object objeto) throws IOException
    {
        xmlEncoder.writeObject(objeto);

    }

    /**
     * Lee un objeto del archivo de entrada en formato XML.
     *
     * @return objeto leído
     * @throws IOException si ocurre un error al leer el objeto
     * @throws ClassNotFoundException si la clase del objeto no se encuentra
     */
    @Override
    public Object leer() throws IOException, ClassNotFoundException
    {
        Object objecto = null;
        if (xmlDecoder != null)
            objecto = (Serializable) xmlDecoder.readObject();

        return objecto;
    }
}