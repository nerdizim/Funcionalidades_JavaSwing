package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Sites;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Tutu
 */
public class SitesDAO {

    private Connection con;

    public SitesDAO() {
        this.con = new ConnectionFactory().getConnection();

    }

    //Metodo cadastrarCliente
    public void cadastrarSites(Sites obj) {
        try {
            //1 passo - criar o comando sql
            String sql = "insert into sites(site,endereco,descricao,produto,preco)"
                    + " values(?,?,?,?,?)";

            //2 passo- conectar ao banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setString(1, obj.getSite());
            stmt.setString(2, obj.getEndereco());
            stmt.setString(3, obj.getDescricao());
            stmt.setString(4, obj.getProduto());
            stmt.setDouble(5, obj.getPreco());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Site cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro aqui!!!: " + e);
        }

    }
    
    
    public List<Sites> listarSites() {
        try {
            //1 passo - criar a lista
            List<Sites> lista = new ArrayList<>();

            //2 passo - criar o sql, organizar e executar.
            String sql = "select * from sites";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Sites obj = new Sites();

                obj.setSite(rs.getString("site"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setProduto(rs.getString("produto"));
                obj.setPreco(rs.getDouble("preco"));
                

                lista.add(obj);

            }
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Este erro está acontecendo!!! " + e);
            System.err.println(e);
            return null;
        }
    }

}
