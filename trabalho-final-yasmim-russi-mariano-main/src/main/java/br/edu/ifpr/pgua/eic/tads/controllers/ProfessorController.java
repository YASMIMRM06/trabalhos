package br.edu.ifpr.pgua.eic.tads.controllers;

import io.javalin.Javalin;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.dao.ProfessorDAO;
import br.edu.ifpr.pgua.eic.tads.models.Professor;

public class ProfessorController {
    private ProfessorDAO professorDAO;

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
    }

    public void registrarRotas(Javalin app) {
        app.post("/professores", ctx -> {
            Professor professor = ctx.bodyAsClass(Professor.class);
            professorDAO.cadastrarProfessor(professor);
            ctx.status(201).json(professor);
        });

        app.get("/professores", ctx -> {
            List<Professor> professores = professorDAO.listarProfessores();
            ctx.json(professores);
        });

        // app.delete("/professores/:id", ctx -> {
        //     int id = Integer.parseInt(ctx.pathParam("id"));
        //     professorDAO.excluirProfessor(id);
        //     ctx.status(204);
        // });
    }
}