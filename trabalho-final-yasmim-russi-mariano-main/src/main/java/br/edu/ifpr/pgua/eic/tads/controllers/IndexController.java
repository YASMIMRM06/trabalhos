package br.edu.ifpr.pgua.eic.tads.controllers;

import java.util.HashMap;
import java.util.Map;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;


public class IndexController {
    public void registrarRotas(Javalin app) {
        app.get("/", ctx -> {
            ctx.result("Bem-vindo à API de Gestão Escolar!\n" +
                    "Rotas disponíveis:\n" +
                    "- /alunos\n" +
                    "- /professores\n" +
                    "- /escolas\n" +
                    "- /disciplinas");
        });
    }
}