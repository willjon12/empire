/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Conexao;
import model.Titulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor
 */
public class TituloDAO {

    private Connection con;

    private final String SQLINSERT = "INSERT INTO titulo(doc,tipo,idclifor,clifor,descos,qtdparcela,vlrtotal,formpag,vlrparcela,"
            + "vlrpago,dtv,dtb,dte,vlrbaixa,baixado)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private final String SQLSELECTBYCODIGO = "SELECT *"
            + " FROM titulo"
            + " WHERE id=? "
            + " ORDER BY doc";

    private final String SQLSELECTBYNOME = "SELECT *"
            + " FROM titulo"
            + " WHERE doc LIKE ? ";

    private final String SQLSELECT = "SELECT id FROM titulo "
            + " WHERE idclifor = ? "
            + " ORDER BY doc";

    private final String SQLSELECTOS = "SELECT id FROM titulo "
            + " WHERE idclifor = ? "
            + "AND doc = ?"
            + " ORDER BY doc";

    private final String SQLUPDATE = "UPDATE titulo "
            + "SET dtb=?,"
            + " vlrbaixa=?,"
            + " baixado=?"
            + " WHERE id=?";

    private final String SQLDELETE = "DELETE FROM titulo"
            + " WHERE doc = ?"
            + " AND idclifor = ?";

    private PreparedStatement paInsert, sqlSelectByCodigo, sqlSelect, sqlUpdate, sqlDelete, 
            sqlSelectBycodigodoc, sqlSelectos,sqlSelecttsPesq;

    public TituloDAO() {

        con = Conexao.getConnection();
        try {
            paInsert = con.prepareStatement(SQLINSERT);
            sqlSelectByCodigo = con.prepareStatement(SQLSELECTBYCODIGO);
            sqlSelectBycodigodoc = con.prepareStatement(SQLSELECTBYNOME);
            sqlSelect = con.prepareStatement(SQLSELECT);
            sqlUpdate = con.prepareStatement(SQLUPDATE);
            sqlDelete = con.prepareStatement(SQLDELETE);
            sqlSelectos = con.prepareStatement(SQLSELECTOS);
          

        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean salvar(Titulo t) {
        try {

            paInsert.setInt(1, t.getDoc());
            paInsert.setString(2, t.getTipo());
            paInsert.setInt(3, t.getIdclifor());
            paInsert.setString(4, t.getClifor());
            paInsert.setString(5, t.getDescos());
            paInsert.setString(6, t.getQtdparcela());
            paInsert.setDouble(7, t.getVlrtotal());
            paInsert.setString(8, t.getFormpag());
            paInsert.setDouble(9, t.getVlrparcela());
            paInsert.setDouble(10, t.getVlrpago());
            paInsert.setString(11, t.getDtv());
            if (t.getDtb().isEmpty()) {
                paInsert.setNull(12, 1);
            } else {
                paInsert.setString(12, t.getDtb());
            }
            paInsert.setString(13, t.getDte());
            paInsert.setDouble(14, t.getVlrbaixa());
            paInsert.setBoolean(15, t.isBaixado());

            paInsert.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Titulo> listar(int codp) {
        List<Titulo> titulos = new ArrayList<>();
        try {
            sqlSelect.setInt(1, codp);
            ResultSet rs = sqlSelect.executeQuery();
            while (rs.next()) {
                titulos.add(getTituloById(rs.getInt("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return titulos;
    }

    public List<Titulo> listartos(int codp, int codos) {
        List<Titulo> titulos = new ArrayList<>();
        try {
            sqlSelectos.setInt(1, codp);
            sqlSelectos.setInt(2, codos);
            ResultSet rs = sqlSelectos.executeQuery();
            while (rs.next()) {
                titulos.add(getTituloById(rs.getInt("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return titulos;
    }

    public List<Titulo> listartitulo(String titulo) {
        List<Titulo> titulos = new ArrayList<>();
        try {
            sqlSelectBycodigodoc.setString(1, titulo);

            ResultSet rs = sqlSelectBycodigodoc.executeQuery();
            while (rs.next()) {
                titulos.add(getTituloById(rs.getInt("id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return titulos;
    }
     public List<Titulo> pesqts(String tipo, int codpessoa, int baixado, String dtei, String dtef, 
            String dtvi, String dtvf, String descobs,String dtbi,String dtbf) {
        List<Titulo> ts = new ArrayList<>();
        try {
            String SQLSELECTTSPESQ = "";
            SQLSELECTTSPESQ  = "SELECT * FROM titulo WHERE id > 0";
            SQLSELECTTSPESQ += " AND tipo LIKE '"+tipo+"'";
            SQLSELECTTSPESQ += " AND baixado = "+baixado;
            SQLSELECTTSPESQ += " AND dte between '"+dtei+"' AND '"+dtef+"'";
            SQLSELECTTSPESQ += " AND dtv between '"+dtvi+"' AND '"+dtvf+"'";
            
            if(!dtbi.isEmpty() && !dtbf.isEmpty()){
                SQLSELECTTSPESQ += " AND dtb between '"+dtbi+"' AND '"+dtbf+"'";
            }
            
            if (!descobs.isEmpty()) {
                SQLSELECTTSPESQ += " AND descos LIKE '%"+descobs+"%'";
            }
            if (codpessoa != 0) {
                SQLSELECTTSPESQ += " AND idclifor LIKE " + codpessoa;
            }

            sqlSelecttsPesq = con.prepareStatement(SQLSELECTTSPESQ);
            ResultSet rs = sqlSelecttsPesq.executeQuery();
            while (rs.next()) {
                ts.add(getTituloById((rs.getInt("id"))));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ts;
    }

    public Titulo getTituloById(int codtitulo) {

        Titulo t = new Titulo();
        try {
            sqlSelectByCodigo.setInt(1, codtitulo);

            ResultSet rs = sqlSelectByCodigo.executeQuery();

            if (rs.next()) {
                t.setId(rs.getInt("id"));
                t.setDoc(rs.getInt("doc"));
                t.setTipo(rs.getString("tipo"));
                t.setIdclifor(rs.getInt("idclifor"));
                t.setClifor(rs.getString("clifor"));
                t.setDescos(rs.getString("descos"));
                t.setQtdparcela(rs.getString("qtdparcela"));
                t.setVlrtotal(rs.getDouble("vlrtotal"));
                t.setFormpag(rs.getString("formpag"));
                t.setVlrparcela(rs.getDouble("vlrparcela"));
                t.setVlrpago(rs.getDouble("vlrpago"));
                t.setDtv(rs.getString("dtv"));
                t.setDtb(rs.getString("dtb"));
                t.setDte(rs.getString("dte"));
                t.setVlrbaixa(rs.getDouble("vlrbaixa"));
                t.setBaixado(rs.getBoolean("baixado"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t;

    }        

    public boolean alterar(Titulo titulo) {
        try {
            sqlUpdate.setString(1, titulo.getDtb());
            sqlUpdate.setDouble(2, titulo.getVlrbaixa());
            sqlUpdate.setBoolean(3, titulo.isBaixado());
            sqlUpdate.setInt(4, titulo.getId());
            sqlUpdate.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean excluir(int doc,int idclifor) {

        try {
            sqlDelete.setInt(1, doc);
            sqlDelete.setInt(2, idclifor);
            sqlDelete.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TituloDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}

