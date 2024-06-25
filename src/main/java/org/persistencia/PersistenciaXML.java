package org.persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

/**
 * Clase que implementa la persistencia de datos en formato XML.
 */
public class PersistenciaXML implements IPersistencia<Serializable> {
    private XMLEncoder xmlEncoder;
    private XMLDecoder xmlDecoder;
    private ObjectOutputStream objectOutput;
    private ObjectInputStream objectInput;


    /**
     * Abre un archivo de entrada para leer datos en formato XML.
     *
     * @param nombre nombre del archivo de entrada
     * @throws IOException si ocurre un error al abrir el archivo
     */
    @Override
    public void abrirInput(String nombre) throws IOException {
        this.objectInput = new ObjectInputStream(new FileInputStream(nombre));
    }

    /**
     * Abre un archivo de salida para escribir datos en formato XML.
     *
     * @param nombre nombre del archivo de salida
     * @throws IOException si ocurre un error al abrir el archivo
     */
    @Override
    public void abrirOutput(String nombre) throws IOException {
        this.objectOutput = new ObjectOutputStream(new FileOutputStream(nombre));
    }

    /**
     * Cierra el archivo de salida.
     *
     * @throws IOException si ocurre un error al cerrar el archivo
     */
    @Override
    public void cerrarOutput() throws IOException {
        if (this.objectOutput != null)
            this.objectOutput.close();
    }

    /**
     * Cierra el archivo de entrada.
     *
     * @throws IOException si ocurre un error al cerrar el archivo
     */

    @Override
    public void cerrarInput() throws IOException {
        if (this.objectInput != null)
            this.objectInput.close();
    }

    /**
     * Escribe un objeto en el archivo de salida en formato XML.
     *
     * @param objeto objeto a escribir
     * @throws IOException si ocurre un error al escribir el objeto
     */
    @Override
    public void escribir(Serializable objeto) throws IOException{
        if (objectOutput != null)
            objectOutput.writeObject(objeto);
    }
    /**
     * Lee un objeto del archivo de entrada en formato XML.
     *
     * @return objeto leído
     * @throws IOException            si ocurre un error al leer el objeto
     * @throws ClassNotFoundException si la clase del objeto no se encuentra
     */
    @Override
    public Serializable leer() throws IOException, ClassNotFoundException {
        Serializable objecto = null;
        if (objectInput != null)
            objecto = (Serializable) objectInput.readObject();
        return objecto;
    }
}