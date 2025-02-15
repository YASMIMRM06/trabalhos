package br.edu.ifpr.pgua.eic.tads.controllers;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.dao.AlunoDAO;
import br.edu.ifpr.pgua.eic.tads.models.Aluno;
import io.javalin.Javalin;

public class AlunoController {
    private AlunoDAO alunoDAO;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
        System.out.println("ta aqui");
    }

    public void registrarRotas(Javalin app) {
        app.post("/alunos", ctx -> {
            Aluno aluno = ctx.bodyAsClass(Aluno.class);
            alunoDAO.cadastrarAluno(aluno);
            ctx.status(201).json(aluno);
        });

        app.get("/alunos", ctx -> {
            List<Aluno> alunos = alunoDAO.listarAlunos();
            ctx.json(alunos);
        });

        // app.delete("/alunos/:id", ctx -> {
        //     int id = Integer.parseInt(ctx.pathParam("id"));
        //     alunoDAO.excluirAluno(id);
        //     ctx.status(204);
        // });
    }
}