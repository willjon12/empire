/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Conexao;
import model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAO {

    private Connection con;

    private final String SQLINSERT = " INSERT INTO pessoa(pfisica, cpf, celular, nascimento,nome,"
            + "apelido,sexo,endereco,enumero,bairro,"
            + "complemento,cep,cidade,email,uf,datacadastro,"
            + "registrogeral, emissorRG,telefone,obs)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

    private final String SQLSELECTBYCODIGO = "SELECT *"
            + " FROM pessoa"
            + " WHERE cod=? "
            + " ORDER BY nome";

    private final String SQLSELECTBYNOME = "SELECT *"
            + " FROM pessoa"
            + " WHERE nome LIKE ? "
            + " ORDER BY nome";
    
    private final String SQLSELECTBYCPF = "SELECT *"
            + " FROM pessoa"
            + " WHERE cpf LIKE ? ";
            

    private final String SQLSELECT = "SELECT cod FROM pessoa"
            + " ORDER BY nome";

    private final String SQLUPDATE = " UPDATE pessoa"
            + "   SET pfisica =?,"
            + "       cpf = ?, "
            + "       celular = ?,"
            + "       nascimento =?,"
            + "       nome = ?,"
            + "       apelido = ?,"
            + "       sexo = ?,"
            + "       endereco = ?,"
            + "       enumero = ?,"
            + "       bairro = ?,"
            + "       complemento = ?,"
            + "       cep = ?,"
            + "       cidade = ?,"
            + "       email = ?,"
            + "       uf = ?,"
            + "       datacadastro = ?,"
            + "       registrogeral = ?,"
            + "       emissorRG = ?,"
            + "       telefone = ?,"
            + "       obs = ?"
            + " WHERE cod = ?";
    private final String SQLDELETE = "DELETE FROM pessoa"
            + " WHERE cod = ?";

    private PreparedStatement paInsert, sqlSelectByCodigo, sqlSelect, sqlUpdate, sqlDelete, sqlSelectByNome, sqlSelectByCpf;

    public PessoaDAO() {

        con = Conexao.getConnection();
        try {
            paInsert = con.prepareStatement(SQLINSERT);
            sqlSelectByCodigo = con.prepareStatement(SQLSELECTBYCODIGO);
            sqlSelect = con.prepareStatement(SQLSELECT);
            sqlUpdate = con.prepareStatement(SQLUPDATE);
            sqlDelete = con.prepareStatement(SQLDELETE);
            sqlSelectByNome = con.prepareStatement(SQLSELECTBYNOME);
            sqlSelectByCpf = con.prepareStatement(SQLSELECTBYCPF);
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean salvar(Pessoa pessoa) {
        try {
            paInsert.setBoolean(1, pessoa.isPfisica());
            paInsert.setString(2, pessoa.getCPF());
            paInsert.setString(3, pessoa.getCelular());
            paInsert.setString(4, pessoa.getNascimento());
            paInsert.setString(5, pessoa.getNome());
            paInsert.setString(6, pessoa.getApelido());
            paInsert.setString(7, pessoa.getSexo());
            paInsert.setString(8, pessoa.getEndereco());
            paInsert.setString(9, pessoa.getEnumero());
            paInsert.setString(10, pessoa.getBairro());
            paInsert.setString(11, pessoa.getComplemento());
            paInsert.setString(12, pessoa.getCEP());
            paInsert.setString(13, pessoa.getCidade());
            paInsert.setString(14, pessoa.getEmail());
            paInsert.setString(15, pessoa.getUF());
            paInsert.setString(16, pessoa.getDatacadastro());
            paInsert.setString(17, pessoa.getRG());
            paInsert.setString(18, pessoa.getEmissorRG());
            paInsert.setString(19, pessoa.getTelefone());
            paInsert.setString(20, pessoa.getOBS());
            paInsert.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public List<Pessoa> listar() {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            ResultSet rs = sqlSelect.executeQuery();
            while (rs.next()) {
                pessoas.add(getPessoaById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoas;
    }

    public List<Pessoa> listar2(String pessoa) {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            sqlSelectByNome.setString(1,  "%"+pessoa+"%");

            ResultSet rs = sqlSelectByNome.executeQuery();
            while (rs.next()) {
                pessoas.add(getPessoaById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoas;
    }

    public List<Pessoa> listarcpf(String cpf) {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            sqlSelectByCpf.setString(1,  cpf + "%");

            ResultSet rs = sqlSelectByCpf.executeQuery();
            while (rs.next()) {
                pessoas.add(getPessoaById(rs.getInt("cod")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoas;
    }

    public Pessoa getPessoaById(int codpessoa) {

        Pessoa p = new Pessoa();
        try {
            sqlSelectByCodigo.setInt(1, codpessoa);

            ResultSet rs = sqlSelectByCodigo.executeQuery();

            if (rs.next()) {
                p.setCodpessoa(rs.getInt("cod"));
                p.setCPF(rs.getString("cpf"));
                p.setCelular(rs.getString("celular"));
                p.setNome(rs.getString("nome"));
                p.setApelido(rs.getString("apelido"));
                p.setBairro(rs.getString("bairro"));
                p.setCEP(rs.getString("CEP"));
                p.setCidade(rs.getString("cidade"));
                p.setComplemento(rs.getString("complemento"));
                if ((rs.getDate("datacadastro")) == null) {
                    p.setDatacadastro("");
                } else {
                    p.setDatacadastro(rs.getDate("datacadastro").toString());
                }
                p.setEmail(rs.getString("email"));
                p.setEmissorRG(rs.getString("emissorrg"));
                p.setEndereco(rs.getString("endereco"));
                p.setEnumero(rs.getString("enumero"));
                p.setRG(rs.getString("registrogeral"));

                if ((rs.getString("nascimento")) == null) {
                    p.setNascimento("");
                } else {
                    p.setNascimento(rs.getString("nascimento"));
                }
                p.setOBS(rs.getString("OBS"));
                p.setPfisica(rs.getBoolean("pfisica"));
                p.setTelefone(rs.getString("telefone"));
                p.setUF(rs.getString("UF"));
                p.setSexo(rs.getString("sexo"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }

    public boolean alterar(Pessoa pessoa) {
        try {
            sqlUpdate.setBoolean(1, pessoa.isPfisica());
            sqlUpdate.setString(2, pessoa.getCPF());
            sqlUpdate.setString(3, pessoa.getCelular());
            sqlUpdate.setString(4, pessoa.getNascimento());
            sqlUpdate.setString(5, pessoa.getNome());
            sqlUpdate.setString(6, pessoa.getApelido());
            sqlUpdate.setString(7, pessoa.getSexo());
            sqlUpdate.setString(8, pessoa.getEndereco());
            sqlUpdate.setString(9, pessoa.getEnumero());
            sqlUpdate.setString(10, pessoa.getBairro());
            sqlUpdate.setString(11, pessoa.getComplemento());
            sqlUpdate.setString(12, pessoa.getCEP());
            sqlUpdate.setString(13, pessoa.getCidade());
            sqlUpdate.setString(14, pessoa.getEmail());
            sqlUpdate.setString(15, pessoa.getUF());
            sqlUpdate.setString(16, pessoa.getDatacadastro());
            sqlUpdate.setString(17, pessoa.getRG());
            sqlUpdate.setString(18, pessoa.getEmissorRG());
            sqlUpdate.setString(19, pessoa.getTelefone());
            sqlUpdate.setString(20, pessoa.getOBS());
            sqlUpdate.setInt(21, pessoa.getCodpessoa());
            sqlUpdate.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean excluir(int idpessoa) {

        try {
            sqlDelete.setInt(1, idpessoa);
            sqlDelete.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}
