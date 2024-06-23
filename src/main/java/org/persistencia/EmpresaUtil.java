package org.persistencia;

import org.sistema.Empresa;

public class EmpresaUtil {

    /**
     * Crea un objeto EmpresaDTO a partir de la instancia única de Empresa.
     * @return un objeto EmpresaDTO con los datos de la Empresa.
     */

    public static EmpresaDTO creoDTO() {
        Empresa empresa =Empresa.getInstance();
        EmpresaDTO edto= new EmpresaDTO(
                empresa.getClientes(),
                empresa.getUsuarios(),
                empresa.getViajes(),
                empresa.getRecaudado()
        );
        return edto;
    }

    /**
     * Actualiza la instancia única de Empresa con los datos proporcionados por un objeto EmpresaDTO.
     * @param dto el objeto EmpresaDTO que contiene los datos para actualizar la Empresa.
     */
    public static void creoEmpresa(EmpresaDTO dto) {
        Empresa e = Empresa.getInstance();
        e.setClientes(dto.getClientes());
        e.setUsuarios(dto.getUsuarios());
        e.setRecaudado(dto.getRecaudado());
    }
}
