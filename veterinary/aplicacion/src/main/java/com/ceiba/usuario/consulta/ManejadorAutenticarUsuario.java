package com.ceiba.usuario.consulta;

import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.comando.fabrica.FabricaUsuario;
import com.ceiba.usuario.modelo.dto.DtoUsuario;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.dao.DaoUsuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorAutenticarUsuario {

    private final DaoUsuario daoUsuario;
    private final FabricaUsuario fabricaUsuario;

    public ManejadorAutenticarUsuario(DaoUsuario daoUsuario,
                                      FabricaUsuario fabricaUsuario){
        this.daoUsuario = daoUsuario;
        this.fabricaUsuario = fabricaUsuario;
    }

    public DtoUsuario ejecutar(ComandoUsuario comandoUsuario){
        Usuario usuario = this.fabricaUsuario.autenticar(comandoUsuario);
        return this.daoUsuario.autenticarUsuario(usuario);
    }
}
