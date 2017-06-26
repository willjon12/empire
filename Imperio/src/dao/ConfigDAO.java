/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexao;
import model.Config;

/**
 *
 * @author Igor
 */
public class ConfigDAO {

    private Connection con;

    private final String SQLINSERT = "INSERT INTO config(dav) values (?)";

    private final String SQLSELECTBYCODIGO = "SELECT *"
            + " FROM titulos"
            + " WHERE codigo=? "
            + " ORDER BY nometitulo";

    private final String SQLSELECTBYNOME = "SELECT cod, usuario,senha,pessoas,usuarios,tiutos,apagar,areceber,os"
            + " FROM usuario"
            + " WHERE usuario LIKE ? ";

    private final String SQLSELECT = "SELECT *"
            + " FROM config"
            + " WHERE idconfig=?";

    private final String SQLSELECT2 = "SELECT *"
            + " FROM config";
            
    private final String SQLSELECTBYUSER2 = "SELECT cod, usuario,senha,pessoas,usuarios,tiutos,apagar,areceber,os"
            + " FROM usuario"
            + " WHERE usuario = ?";
    private final String SQLUPDATE = "UPDATE config "
            + " SET dav=?"
            + " WHERE idconfig=?";
    private final String SQLDELETE = "DELETE FROM titulos"
            + " WHERE codigo = ?";

    private PreparedStatement paInsert, sqlSelectByCodigo, sqlSelect, sqlUpdate, sqlDelete, sqlSelectByNome,sqlSelect2;

    public ConfigDAO() {

        con = Conexao.getConnection();
        try {
            paInsert = con.prepareStatement(SQLINSERT);
            sqlSelectByCodigo = con.prepareStatement(SQLSELECTBYCODIGO);
            sqlSelectByNome = con.prepareStatement(SQLSELECTBYNOME);
            sqlSelect = con.prepareStatement(SQLSELECT);
            sqlUpdate = con.prepareStatement(SQLUPDATE);
            sqlDelete = con.prepareStatement(SQLDELETE);
            sqlSelect2 = con.prepareStatement(SQLSELECT2);
            

        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean editar(Config c) {
        try {

            sqlUpdate.setInt(1, c.getDav());
            sqlUpdate.setInt(2, c.getIdconfig());

            sqlUpdate.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Config getconfigById(int id) {

        Config c = new Config();
        try {
            sqlSelect.setInt(1, id);

            ResultSet rs = sqlSelect.executeQuery();

            if (rs.next()) {
                c.setIdconfig(rs.getInt("idconfig"));
                c.setDav(rs.getInt("dav"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;

    }

    public List<Config> listarconfig() {
        List<Config> c = new ArrayList<>();
        try {
         
            ResultSet rs = sqlSelect2.executeQuery();
            while (rs.next()) {
                c.add(getconfigById(rs.getInt("idconfig")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
   
    
    
}
