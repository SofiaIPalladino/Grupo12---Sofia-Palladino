package org.persistencia;

import java.io.IOException;

public class PersistirDatos {
    IPersistencia persistencia = new PersistenciaXML();

    public void persistoDatos() throws IOException {
        persistencia.abrirOutput("empresa.xml");
        EmpresaDTO edto = EmpresaUtil.creoDTO();
        persistencia.escribir(edto);
        persistencia.cerrarOutput();
    }

    public void levantoDatos() throws IOException, ClassNotFoundException {
        persistencia.abrirInput("empresa.xml");
        EmpresaDTO edto = (EmpresaDTO)persistencia.leer();
        EmpresaUtil.creoEmpresa(edto);
        persistencia.cerrarInput();
    }
}
