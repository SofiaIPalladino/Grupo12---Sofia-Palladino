package org.persistencia;
import java.io.IOException;
import java.io.Serializable;

/**
 * Interfaz para la persistencia de objetos en archivos.
 * Utiliza genéricos para permitir que los métodos trabajen con diferentes tipos de objetos.
 *
 * @param <E> el tipo de objeto a persistir.
 */

public interface IPersistencia <E>
{
    void abrirInput(String nombre) throws IOException;

    void abrirOutput(String nombre) throws IOException;

    void cerrarOutput() throws IOException;

    void cerrarInput() throws IOException;

    void escribir(E objecto) throws IOException;

    E leer() throws IOException, ClassNotFoundException;
}
