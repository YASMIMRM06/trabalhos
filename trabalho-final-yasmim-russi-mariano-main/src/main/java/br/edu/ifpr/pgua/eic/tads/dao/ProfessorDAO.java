package br.edu.ifpr.pgua.eic.tads.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.models.Professor;

public class ProfessorDAO {

    private Connection connection;

    public ProfessorDAO() {
        try {
            this.connection = FabricaConexao.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados.");
        }
    }

    public void cadastrarProfessor(Professor professor) {
        String sql = "INSERT INTO professor (nome, cpf, endereco, telefone, email, disciplina_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getCpf());
            stmt.setString(3, professor.getEndereco());
            stmt.setString(4, professor.getTelefone());
            stmt.setString(5, professor.getEmail());
            stmt.setInt(6, professor.getDisciplinaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    public List<Professor> listarProfessores() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setNome(rs.getString("nome"));
                professor.setCpf(rs.getString("cpf"));
                professor.setEndereco(rs.getString("endereco"));
                professor.setTelefone(rs.getString("telefone"));
                professor.setEmail(rs.getString("email"));
                professor.setDisciplinaId(rs.getInt("disciplina_id"));
                professores.add(professor);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar professores: " + e.getMessage());
        }
        return professores;
    }

    public void excluirProfessor(int id) throws SQLException {
        String sql = "DELETE FROM professores WHERE id = ?";
        try (Connection conn = connection;
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
