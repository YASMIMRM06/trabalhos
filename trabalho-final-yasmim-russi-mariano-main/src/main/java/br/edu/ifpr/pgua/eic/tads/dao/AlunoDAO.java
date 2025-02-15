package br.edu.ifpr.pgua.eic.tads.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.models.Aluno;

public class AlunoDAO {

    private Connection connection;

    // Construtor para inicializar a conexão
    public AlunoDAO() {
        try {
            this.connection = FabricaConexao.getInstance().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao conectar ao banco de dados.");
        }
    }

    public void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, matricula, endereco, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, aluno.getEndereco());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());
            stmt.executeUpdate();
            System.out.println("Aluno cadastrado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        
        String sql = "SELECT * FROM aluno";


        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setEndereco(rs.getString("endereco"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public void excluirAluno(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Aluno excluído com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}