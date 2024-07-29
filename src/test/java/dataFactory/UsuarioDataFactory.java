package dataFactory;

import pojo.UsuarioPojo;

public class UsuarioFactory {
    public static UsuarioPojo logarComUsuarioPadrao()
    {
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("edmarnunes");
        usuario.setUsuarioSenha("123456");
        return usuario;
    }
}
