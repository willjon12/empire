/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Conexao;
import model.Os;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Item;

public class OsDAO {

    private Connection con;

    private final String SQLINSERT = " INSERT INTO os(dta,usuario,descricao,status,codp,pessoa,"
            + "subtotal,desconto,total,obs,dtg,dtf)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

    private final String IOSINSERT = " INSERT INTO itemos(codos,descricao,un,quantidade,vlruni,vlrtotal)"
            + " VALUES(?, ?, ?, ?, ?, ?)";

    private final String SQLSELECTBYCODIGO = "SELECT *"
            + " FROM os"
            + " WHERE cod=? "
            + " ORDER BY status";

    private final String SQLSELECTBYNOME = "SELECT *"
            + " FROM pessoa"
            + " WHERE nome LIKE ? "
            + " ORDER BY nome";

    private final String SQLSELECTIOSBYID = "SELECT *"
            + " FROM itemos"
            + " WHERE id=?";

    private final String SQLSELECTOS = "SELECT *"
            + " FROM OS"
            + " ORDER BY status ";

    private final String SQLSELECTIOS = "SELECT *"
            + " FROM itemos "
            + " WHERE codos=?";

    private final String SQLSELECT = "SELECT LAST_INSERT_ID()";

    private final String SQLSELECTG = "SELECT * FROM os "
            + "WHERE dtg between ? AND ? "
            + "ORDER by dtg";

    private final String SQLUPDATEOS = " UPDATE os"
            + "   SET dta =?,"
            + "       usuario = ?, "
            + "       descricao = ?,"
            + "       status =?,"
            + "       codp = ?,"
            + "       pessoa = ?,"
            + "       subtotal = ?,"
            + "       desconto = ?,"
            + "       total = ?,"
            + "       obs = ?,"
            + "       dtg = ?,"
            + "       dtf = ?"
            + " WHERE cod = ?";

    private final String SQLUPDATEOSI = " UPDATE itemos"
            + "   SET descricao = ?,"
            + "       un = ?,"
            + "       quantidade =?,"
            + "       vlruni = ?,"
            + "       vlrtotal = ?"
            + " WHERE id = ?";

    private final String SQLDELETEIOS = "DELETE FROM itemos"
            + " WHERE id = ?";

    private PreparedStatement paInsert, iosInsert, sqlSelectByCodigo, sqlSelect, sqlSelectos, sqlUpdateos, sqlDeleteios,
            sqlSelectByNome, sqlSelectios, sqlSelectiosbyId, sqlSelectosPesq, sqlUpdateosi, sqlSelectG;

    public OsDAO() {

        con = Conexao.getConnection();
        try {
            paInsert = con.prepareStatement(SQLINSERT);
            iosInsert = con.prepareStatement(IOSINSERT);
            sqlSelectByCodigo = con.prepareStatement(SQLSELECTBYCODIGO);
            sqlSelect = con.prepareStatement(SQLSELECT);
            sqlUpdateos = con.prepareStatement(SQLUPDATEOS);
            sqlDeleteios = con.prepareStatement(SQLDELETEIOS);
            sqlSelectiosbyId = con.prepareStatement(SQLSELECTIOSBYID);
            sqlSelectos = con.prepareStatement(SQLSELECTOS);
            sqlSelectios = con.prepareStatement(SQLSELECTIOS);
            //   sqlSelectosPesq = con.prepareStatement(SQLSELECTOSPESQ);
            sqlUpdateosi = con.prepareStatement(SQLUPDATEOSI);
            sqlSelectG = con.prepareStatement(SQLSELECTG);

        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean salvar(Os os) {
        try {
            paInsert.setString(1, os.getDta());
            paInsert.setString(2, os.getUseros());
            paInsert.setString(3, os.getDescricao());
            paInsert.setString(4, os.getStatus());
            paInsert.setInt(5, os.getCodclifor());
            paInsert.setString(6, os.getClifor());
            paInsert.setDouble(7, os.getSubtotal());
            paInsert.setDouble(8, os.getDesconto());
            paInsert.setDouble(9, os.getTotal());
            paInsert.setString(10, os.getObs());
            if (os.getDtg().isEmpty()) {
                paInsert.setNull(11, 1);
            } else {
                paInsert.setString(11, os.getDtg());
            }
            if (os.getDtf().isEmpty()) {
                paInsert.setNull(12, 1);
            } else {
                paInsert.setString(12, os.getDtf());
            }

            paInsert.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean salvaritemos(Item i) {
        try {
            iosInsert.setInt(1, i.getCodos());
            iosInsert.setString(2, i.getDescricao());
            iosInsert.setString(3, i.getUN());
            iosInsert.setDouble(4, i.getQtd());
            iosInsert.setDouble(5, i.getVenda());
            iosInsert.setDouble(6, i.getTotalitem());

            iosInsert.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Item lastid() {
        Item i = new Item();
        try {
            ResultSet rs = sqlSelect.executeQuery();
            while (rs.next()) {

                i.setCodos(rs.getInt("LAST_INSERT_ID()"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public List<Os> listar() {
        List<Os> os = new ArrayList<>();
        try {
            ResultSet rs = sqlSelectos.executeQuery();
            while (rs.next()) {
                os.add(getOsById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return os;
    }

    public List<Os> pesqos(String descricao, int codpessoa, String status, String dtai, String dtaf, 
            String dtfi, String dtff, int codos) {
        List<Os> os = new ArrayList<>();
        try {
            String SQLSELECTOSPESQ = "";
            SQLSELECTOSPESQ  = "SELECT * FROM os WHERE cod >= 1";
            SQLSELECTOSPESQ += " AND status LIKE '%"+status+"%'";
            SQLSELECTOSPESQ += " AND dta between '"+dtai+"' AND '"+dtaf+"'";
            
            
            if (codos != 0) {
                SQLSELECTOSPESQ += " AND cod LIKE " + codos;
            }
            if (!descricao.isEmpty()) {
                SQLSELECTOSPESQ += " AND descricao LIKE '%"+descricao+"%'";
            }
            if (codpessoa != 0) {
                SQLSELECTOSPESQ += " AND codp LIKE " + codpessoa;
            }
            if (!dtfi.isEmpty() && !dtff.isEmpty()){
                SQLSELECTOSPESQ += " AND dtf between '"+dtfi+"' AND '"+dtff+"'";
                
            }
            
            sqlSelectosPesq = con.prepareStatement(SQLSELECTOSPESQ);
            ResultSet rs = sqlSelectosPesq.executeQuery();
            while (rs.next()) {
                os.add(getOsById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return os;
    }

    public List<Os> listarosg(String dtgi, String dtgf) {
        List<Os> os = new ArrayList<>();
        try {
            sqlSelectG.setString(1, dtgi);
            sqlSelectG.setString(2, dtgf);

            ResultSet rs = sqlSelectG.executeQuery();
            while (rs.next()) {
                os.add(getOsById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return os;
    }

    public List<Item> listarios(int codos) {
        List<Item> i = new ArrayList<>();
        try {

            sqlSelectios.setInt(1, codos);
            ResultSet rs = sqlSelectios.executeQuery();
            while (rs.next()) {
                i.add(getitemByIdos(rs.getInt("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }

    public Item getitemByIdos(int id) {

        Item i = new Item();
        try {
            sqlSelectiosbyId.setInt(1, id);

            ResultSet rs = sqlSelectiosbyId.executeQuery();

            if (rs.next()) {
                i.setCodproduto(rs.getInt("id"));
                i.setDescricao(rs.getString("descricao"));
                i.setUN(rs.getString("un"));
                i.setQtd(rs.getDouble("quantidade"));
                i.setVenda(rs.getDouble("vlruni"));
                i.setTotalitem(rs.getDouble("vlrtotal"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return i;

    }

    public List<Os> listardescri(String desc) {
        List<Os> os = new ArrayList<>();
        try {
            sqlSelectByNome.setString(1, "%" + desc + "%");

            ResultSet rs = sqlSelectByNome.executeQuery();
            while (rs.next()) {
                os.add(getOsById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return os;
    }

    public Os getOsById(int codos) {

        Os o = new Os();
        try {
            sqlSelectByCodigo.setInt(1, codos);

            ResultSet rs = sqlSelectByCodigo.executeQuery();

            if (rs.next()) {
                o.setCod(rs.getInt("cod"));
                o.setDescricao(rs.getString("descricao"));
                o.setDta(rs.getString("dta"));
                o.setDtf(rs.getString("dtf"));
                o.setDtg(rs.getString("dtg"));
                o.setStatus(rs.getString("status"));
                o.setUseros(rs.getString("usuario"));
                o.setCodclifor(rs.getInt("codp"));
                o.setClifor(rs.getString("pessoa"));
                o.setObs(rs.getString("obs"));
                o.setTotal(rs.getDouble("total"));
                o.setDesconto(rs.getDouble("desconto"));
                o.setSubtotal(rs.getDouble("subtotal"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return o;

    }

    public boolean alterarOs(Os os) {
        try {
            sqlUpdateos.setString(1, os.getDta());
            sqlUpdateos.setString(2, os.getUseros());
            sqlUpdateos.setString(3, os.getDescricao());
            sqlUpdateos.setString(4, os.getStatus());
            sqlUpdateos.setInt(5, os.getCodclifor());
            sqlUpdateos.setString(6, os.getClifor());
            sqlUpdateos.setDouble(7, os.getSubtotal());
            sqlUpdateos.setDouble(8, os.getDesconto());
            sqlUpdateos.setDouble(9, os.getTotal());
            sqlUpdateos.setString(10, os.getObs());
            if (os.getDtg().isEmpty()) {
                sqlUpdateos.setNull(11, 1);
            } else {
                sqlUpdateos.setString(11, os.getDtg());
            }

            if (os.getDtf().isEmpty()) {
                sqlUpdateos.setNull(12, 1);
            } else {
                sqlUpdateos.setString(12, os.getDtf());
            }

            sqlUpdateos.setInt(13, os.getCod());

            sqlUpdateos.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean alterarIos(Item i) {
        try {
            sqlUpdateosi.setString(1, i.getDescricao());
            sqlUpdateosi.setString(2, i.getUN());
            sqlUpdateosi.setDouble(3, i.getQtd());
            sqlUpdateosi.setDouble(4, i.getVenda());
            sqlUpdateosi.setDouble(5, i.getTotalitem());
            sqlUpdateosi.setInt(6, i.getId());

            sqlUpdateosi.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean excluir(int idios) {

        try {
            sqlDeleteios.setInt(1, idios);
            sqlDeleteios.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
