package org.persistencia;

import org.sistema.Empresa;

public class EmpresaUtil {

    public static EmpresaDTO creoDTO() {
        Empresa empresa =Empresa.getInstance();
        EmpresaDTO edto= new EmpresaDTO(
                empresa.getClientes(),
                empresa.getUsuarios(),
                empresa.getChoferes(),
                empresa.getChoferesEnUso(),
                empresa.getVehiculos(),
                empresa.getVehiculosEnUso(),
                empresa.getViajes(),
                empresa.getPedidos(),
                empresa.getRecaudado()
        );
        return edto;
    }

    public static void creoEmpresa(EmpresaDTO dto) {
        Empresa e = Empresa.getInstance();
        e.setClientes(dto.getClientes());
        e.setUsuarios(dto.getUsuarios());
        e.setChoferes(dto.getChoferes());
        e.setChoferesEnUso(dto.getChoferesEnUso());
        e.setVehiculos(dto.getVehiculos());
        e.setVehiculosEnUso(dto.getVehiculosEnUso());
        e.setViajes(dto.getViajes());
        e.setPedidos(dto.getPedidos());
        e.setRecaudado(dto.getRecaudado());
    }
}
