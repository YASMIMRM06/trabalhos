package br.edu.ifpr.pgua.eic.tads.controllers;

import io.javalin.Javalin;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.dao.DisciplinaDAO;
import br.edu.ifpr.pgua.eic.tads.models.Disciplina;

public class DisciplinaController {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaController() {
        this.disciplinaDAO = new DisciplinaDAO();
    }

    public void registrarRotas(Javalin app) {
        app.post("/disciplinas", ctx -> {
            Disciplina disciplina = ctx.bodyAsClass(Disciplina.class);
            disciplinaDAO.cadastrarDisciplina(disciplina);
            ctx.status(201).json(disciplina);
        });

        app.get("/disciplinas", ctx -> {
            List<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();
            ctx.json(disciplinas);
        });

        // app.delete("/disciplinas/:id", ctx -> {
        //     int id = Integer.parseInt(ctx.pathParam("id"));
        //     disciplinaDAO.excluirDisciplina(id);
        //     ctx.status(204);
        // });
    }
}