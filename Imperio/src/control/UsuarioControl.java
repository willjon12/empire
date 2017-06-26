/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dao.UsuarioDAO;
import model.Usuario;
import java.util.List;

/**
 *
 * @author will
 */
public class UsuarioControl {

    private UsuarioDAO usuarioDAO;

    public UsuarioControl() {
        usuarioDAO = new UsuarioDAO();
    }

    public boolean salvar(String nome, String Senha, boolean pessoas, boolean usuarios,
            boolean produtos, boolean apagar, boolean areceber, boolean os) {
        boolean retorno;

        Usuario u = new Usuario();
        u.setNome(nome);
        u.setSenha(Senha);
        u.setPessoas(pessoas);
        u.setUsuarios(usuarios);
        u.setProdutos(produtos);
        u.setApagar(apagar);
        u.setAreceber(areceber);
        u.setOs(os);

        retorno = usuarioDAO.salvar(u);

        return retorno;
    }

    public List<Usuario> listar() {
        return usuarioDAO.listar();

    }

    public List<Usuario> listar2(String usuario) {
        return usuarioDAO.listar2(usuario);

    }

    public Usuario getUsuarioById(int codpessoa) {
        return usuarioDAO.getUsuarioById(codpessoa);

    }
     public Usuario getUsuarioByUser(String usuario,String senha) {
        return usuarioDAO.getUsuarioByUser(usuario,senha);

    }
     public Usuario getUsuarioByUser2(String usuario) {
        return usuarioDAO.getUsuarioByUser2(usuario);

    }

    public boolean alterar(int codigo, String nome, String senha, boolean pessoas, boolean usuarios,
            boolean produtos, boolean apagar, boolean areceber, boolean os) {
        boolean retorno;
        Usuario u = new Usuario();
        u.setCodusuario(codigo);
        u.setNome(nome);
        u.setSenha(senha);
        u.setPessoas(pessoas);
        u.setUsuarios(usuarios);
        u.setProdutos(produtos);
        u.setApagar(apagar);
        u.setAreceber(areceber);
        u.setOs(os);

        retorno = usuarioDAO.alterar(u);
        return retorno;
    }

    public boolean excluir(int codigo) {

        usuarioDAO.excluir(codigo);
        return true;
    }

}
