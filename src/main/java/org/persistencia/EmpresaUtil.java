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
                empresa.getRecaudado(),
                empresa.getGestionViajes(),
                empresa.getGestionChofer(),
                empresa.getGestionPedidos(),
                empresa.getGestionUsuario(),
                empresa.getCantidadMaximaSolicitudesPorCliente(),
                empresa.getCantidadMaximaChoferesTipo(),
                empresa.getCantidadMaximaSolicitudesPorChofer(),
                empresa.getInformacionAccionarHilos()
        );
        return edto;
    }

    /**
     * Actualiza la instancia única de Empresa con los datos proporcionados por un objeto EmpresaDTO.
     * @param dto el objeto EmpresaDTO que contiene los datos para actualizar la Empresa.
     */
    public static void creoEmpresa(EmpresaDTO dto) {
        Empresa e = Empresa.getInstance();
        e.setRecaudado(dto.getRecaudado());
        e.setGestionViajes(dto.getGestionViajes());
        e.setGestionChofer(dto.getGestionChofer());
        e.setGestionUsuario(dto.getGestionUsuario());
        e.setGestionPedidos(dto.getGestionPedidos());
        e.setCantidadMaximaChoferesTipo(dto.getCantidadMaximaChoferesTipo());
        e.setCantidadMaximaSolicitudesPorCliente(dto.getCantidadMaximaSolicitudesPorCliente());
        e.setCantidadMaximaSolicitudesPorChofer(dto.getCantidadMaximaSolicitudesPorChofer());
        e.setInformacionAccionarHilos(dto.getInformacionAccionarHilos());
        e.notificarCambios(dto.getInformacionAccionarHilos());
    }
}
