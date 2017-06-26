/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Conexao;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class UsuarioDAO {

    private Connection con;

    private final String SQLINSERT = " INSERT INTO usuario (usuario, senha,pessoas,usuarios,produtos,apagar,areceber,os )"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";

    private final String SQLSELECTBYCODIGO = "SELECT cod, usuario, senha,pessoas,usuarios,produtos,apagar,areceber,os"
            + " FROM usuario"
            + " WHERE cod=? "
            + " ORDER BY usuario";

    private final String SQLSELECTBYNOME = "SELECT cod, usuario,senha,pessoas,usuarios,produtos,apagar,areceber,os"
            + " FROM usuario"
            + " WHERE usuario LIKE ? ";

    private final String SQLSELECT = "SELECT cod, usuario,senha,pessoas,usuarios,produtos,apagar,areceber,os "
            + " FROM Usuario"
            + " ORDER BY usuario";
    private final String SQLSELECTBYUSER = "SELECT cod, usuario,senha,pessoas,usuarios,produtos,apagar,areceber,os"
            + " FROM usuario"
            + " WHERE usuario = ?"
            + " AND "
            + "  senha = ?";
    private final String SQLSELECTBYUSER2 = "SELECT cod, usuario,senha,pessoas,usuarios,produtos,apagar,areceber,os"
            + " FROM usuario"
            + " WHERE usuario = ?";
    private final String SQLUPDATE = " UPDATE Usuario"
            + "   SET usuario = ?,"
            + "       senha = ?,"
            + "       pessoas = ?,"
            + "       usuarios = ?,"
            + "       produtos = ?,"
            + "       apagar = ?,"
            + "       areceber = ?,"
            + "       os = ?"
            + " WHERE cod = ?";
    private final String SQLDELETE = "DELETE FROM Usuario"
            + " WHERE cod = ?";

    private PreparedStatement paInsert, sqlSelectByCodigo, sqlSelect, sqlUpdate, sqlDelete, sqlSelectByNome, sqlSelectByUser,sqlSelectByUser2;

    public UsuarioDAO() {

        con = Conexao.getConnection();
        try {
            paInsert = con.prepareStatement(SQLINSERT);
            sqlSelectByCodigo = con.prepareStatement(SQLSELECTBYCODIGO);
            sqlSelectByNome = con.prepareStatement(SQLSELECTBYNOME);
            sqlSelect = con.prepareStatement(SQLSELECT);
            sqlUpdate = con.prepareStatement(SQLUPDATE);
            sqlDelete = con.prepareStatement(SQLDELETE);
            sqlSelectByUser = con.prepareStatement(SQLSELECTBYUSER);
            sqlSelectByUser2 = con.prepareStatement(SQLSELECTBYUSER2);

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean salvar(Usuario Usuario) {
        try {
            paInsert.setString(1, Usuario.getNome());
            paInsert.setString(2, Usuario.getSenha());
            paInsert.setBoolean(3, Usuario.isPessoas());
            paInsert.setBoolean(4, Usuario.isProdutos());
            paInsert.setBoolean(5, Usuario.isUsuarios());
            paInsert.setBoolean(6, Usuario.isApagar());
            paInsert.setBoolean(7, Usuario.isAreceber());
            paInsert.setBoolean(8, Usuario.isOs());

            paInsert.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        try {

            ResultSet rs = sqlSelect.executeQuery();
            while (rs.next()) {
                usuarios.add(getUsuarioById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public List<Usuario> listar2(String usuario) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            sqlSelectByNome.setString(1, "%" + usuario + "%");

            ResultSet rs = sqlSelectByNome.executeQuery();
            while (rs.next()) {
                usuarios.add(getUsuarioById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public Usuario getUsuarioById(int codUsuario) {

        Usuario u = new Usuario();
        try {
            sqlSelectByCodigo.setInt(1, codUsuario);

            ResultSet rs = sqlSelectByCodigo.executeQuery();

            if (rs.next()) {
                u.setCodusuario(rs.getInt("cod"));
                u.setNome(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));
                u.setPessoas(rs.getBoolean("pessoas"));
                u.setUsuarios(rs.getBoolean("usuarios"));
                u.setProdutos(rs.getBoolean("produtos"));
                u.setApagar(rs.getBoolean("apagar"));
                u.setAreceber(rs.getBoolean("areceber"));
                u.setOs(rs.getBoolean("os"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;

    }

    public boolean alterar(Usuario Usuario) {
        try {
            sqlUpdate.setString(1, Usuario.getNome());
            sqlUpdate.setString(2, Usuario.getSenha());
            sqlUpdate.setBoolean(3, Usuario.isPessoas());
            sqlUpdate.setBoolean(4, Usuario.isUsuarios());
            sqlUpdate.setBoolean(5, Usuario.isProdutos());
            sqlUpdate.setBoolean(6, Usuario.isApagar());
            sqlUpdate.setBoolean(7, Usuario.isAreceber());
            sqlUpdate.setBoolean(8, Usuario.isOs());
            sqlUpdate.setInt(9, Usuario.getCodusuario());

            sqlUpdate.executeUpdate();
               return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
         return false;
        }

       
    }

    public boolean excluir(int idUsuario) {

        try {
            sqlDelete.setInt(1, idUsuario);
            sqlDelete.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public Usuario getUsuarioByUser(String usuario, String senha) {

        Usuario u = null;
        try {
            sqlSelectByUser.setString(1, usuario);
            sqlSelectByUser.setString(2, senha);

            ResultSet rs = sqlSelectByUser.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setCodusuario(rs.getInt("cod"));
                u.setNome(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));
                u.setPessoas(rs.getBoolean("pessoas"));
                u.setUsuarios(rs.getBoolean("usuarios"));
                u.setProdutos(rs.getBoolean("produtos"));
                u.setApagar(rs.getBoolean("apagar"));
                u.setAreceber(rs.getBoolean("areceber"));
                u.setOs(rs.getBoolean("os"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;

    }
     public Usuario getUsuarioByUser2(String usuario) {

        Usuario u = null;
        try {
            sqlSelectByUser2.setString(1, usuario);


            ResultSet rs = sqlSelectByUser2.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setNome(rs.getString("usuario"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;

    }

}
