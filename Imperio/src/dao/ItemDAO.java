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

import dao.*;
import javax.swing.JOptionPane;
import model.Item;

public class ItemDAO {

    private Connection con;

    private final String SQLINSERT = " INSERT INTO produto (descricao, datacad,custo,venda,lucro,UN )"
            + " VALUES(?, ?, ?, ?, ?, ?) ";

    private final String SQLSELECTBYCODIGO = "SELECT *"
            + " FROM produto"
            + " WHERE cod=? "
            + " ORDER BY descricao";

    private final String SQLSELECTBYNOME = "SELECT *"
            + " FROM produto"
            + " WHERE descricao LIKE ? ";

    private final String SQLSELECT = "SELECT *"
            + " FROM produto"
            + " ORDER BY descricao";
    private final String SQLSELECTBYUSER = "SELECT cod, usuario,senha,pessoas,usuarios,produtos,apagar,areceber,os"
            + " FROM usuario"
            + " WHERE usuario = ?"
            + " AND "
            + "  senha = ?";
    private final String SQLSELECTBYUSER2 = "SELECT cod, usuario,senha,pessoas,usuarios,produtos,apagar,areceber,os"
            + " FROM usuario"
            + " WHERE usuario = ?";
    private final String SQLUPDATE = "UPDATE produto"
            + " SET descricao = ?,"
            + "       datacad = ?,"
            + "       custo = ?,"
            + "       venda = ?,"
            + "       lucro = ?,"
            + "       un = ? "
            + " WHERE cod = ?";
    private final String SQLDELETE = "DELETE FROM produto"
            + " WHERE cod = ?";

    private PreparedStatement paInsert, sqlSelectByCodigo, sqlSelect, sqlUpdate, sqlDelete, sqlSelectByNome, sqlSelectByUser,sqlSelectByUser2;

    public ItemDAO() {

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
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean salvar(Item prod) {
        try {
            paInsert.setString(1, prod.getDescricao());
            paInsert.setString(2, prod.getDatacad());
            paInsert.setDouble(3, prod.getCusto());
            paInsert.setDouble(4, prod.getVenda());
            paInsert.setDouble(5, prod.getLucro());
            paInsert.setString(6, prod.getUN());


            paInsert.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Item> listar() {
        List<Item> produtos = new ArrayList<>();
        try {

            ResultSet rs = sqlSelect.executeQuery();
            while (rs.next()) {
                produtos.add(getProdutoById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }

    public List<Item> listar2(String produto) {
        List<Item> produtos = new ArrayList<>();
        try {
            sqlSelectByNome.setString(1, "%"+produto+"%");

            ResultSet rs = sqlSelectByNome.executeQuery();
            while (rs.next()) {
                produtos.add(getProdutoById((rs.getInt("cod"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }

    public Item getProdutoById(int codProduto) {

        Item prod = new Item();
        try {
            sqlSelectByCodigo.setInt(1, codProduto);

            ResultSet rs = sqlSelectByCodigo.executeQuery();

            if (rs.next()) {
                prod.setCodproduto(rs.getInt("cod"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setDatacad(rs.getString("datacad"));
                prod.setCusto(rs.getDouble("custo"));
                prod.setVenda(rs.getDouble("venda"));
                prod.setLucro(rs.getDouble("lucro"));
                prod.setUN(rs.getString("un"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prod;

    }

    public boolean alterar(Item item) {
        try {
            sqlUpdate.setString(1, item.getDescricao());
            sqlUpdate.setString(2, item.getDatacad());
            sqlUpdate.setDouble(3, item.getCusto());
            sqlUpdate.setDouble(4, item.getVenda());
            sqlUpdate.setDouble(5, item.getLucro());
            sqlUpdate.setString(6, item.getUN());
            sqlUpdate.setInt(7, item.getCodproduto());

            sqlUpdate.executeUpdate();
               return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
         return false;
        }

       
    }

    public boolean excluir(int idprod) {

        try {
            sqlDelete.setInt(1, idprod);
            sqlDelete.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
        }

        
    }

  


    

}
