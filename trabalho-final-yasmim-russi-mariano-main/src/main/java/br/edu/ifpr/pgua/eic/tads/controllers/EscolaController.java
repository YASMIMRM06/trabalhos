package br.edu.ifpr.pgua.eic.tads.controllers;

import io.javalin.Javalin;

import java.sql.SQLException;
import java.util.List;

import br.edu.ifpr.pgua.eic.tads.dao.EscolaDAO;
import br.edu.ifpr.pgua.eic.tads.models.Escola;

public class EscolaController {
    private EscolaDAO escolaDAO;

    public EscolaController() {
        this.escolaDAO = new EscolaDAO();
    }

    public void registrarRotas(Javalin app) {
        app.post("/escolas", ctx -> {
            Escola escola = ctx.bodyAsClass(Escola.class);
            escolaDAO.cadastrarEscola(escola);
            ctx.status(201).json(escola);
        });

        app.get("/escolas", ctx -> {
            List<Escola> escolas = escolaDAO.listarEscolas();
            ctx.json(escolas);
        });

        // app.delete("/escolas/:id", ctx -> {
        //     int id = Integer.parseInt(ctx.pathParam("id"));
        //     escolaDAO.excluirEscola(id);
        //     ctx.status(204);
        // });
    }
}