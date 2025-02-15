package br.edu.ifpr.pgua.eic.tads.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.models.Disciplina;

public class DisciplinaDAO {

    private Connection connection;

    // Construtor para inicializar a conexão
    public DisciplinaDAO() {
        try {
            this.connection = FabricaConexao.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados.");
        }
    }

    public void cadastrarDisciplina(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina (nome, descricao) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setString(2, disciplina.getDescricao());
            stmt.executeUpdate();
            System.out.println("Disciplina cadastrada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }

    public List<Disciplina> listarDisciplinas() {
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setNome(rs.getString("nome"));
                disciplina.setDescricao(rs.getString("descricao"));
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }
        return disciplinas;
    }

    public void excluirDisciplina(int id) {
        String sql = "DELETE FROM disciplina WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Disciplina excluída com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir disciplina: " + e.getMessage());
        }
    }
}