package br.edu.ifpr.pgua.eic.tads.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.models.Escola;

public class EscolaDAO {

    private Connection connection;

    public EscolaDAO() {
        try {
            this.connection = FabricaConexao.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados.");
        }
    }

    public void cadastrarEscola(Escola escola) {
        String sql = "INSERT INTO escola (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, escola.getNome());
            stmt.setString(2, escola.getEndereco());
            stmt.setString(3, escola.getTelefone());
            stmt.setString(4, escola.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar escola: " + e.getMessage());
        }
    }

    public List<Escola> listarEscolas() {
        List<Escola> escolas = new ArrayList<>();
        String sql = "SELECT * FROM escola";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Escola escola = new Escola();
                escola.setNome(rs.getString("nome"));
                escola.setEndereco(rs.getString("endereco"));
                escola.setTelefone(rs.getString("telefone"));
                escola.setEmail(rs.getString("email"));
                escolas.add(escola);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar escolas: " + e.getMessage());
        }
        return escolas;
    }

    public void excluirEscola(int id) {
        String sql = "DELETE FROM escola WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir escola: " + e.getMessage());
        }
    }
}