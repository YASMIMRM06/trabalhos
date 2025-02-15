package br.edu.ifpr.pgua.eic.tads;

import br.edu.ifpr.pgua.eic.tads.controllers.AlunoController;
import br.edu.ifpr.pgua.eic.tads.controllers.DisciplinaController;
import br.edu.ifpr.pgua.eic.tads.controllers.EscolaController;
import br.edu.ifpr.pgua.eic.tads.controllers.IndexController;
import br.edu.ifpr.pgua.eic.tads.controllers.ProfessorController;
import br.edu.ifpr.pgua.eic.tads.dao.FabricaConexao;
import br.edu.ifpr.pgua.eic.tads.utils.JavalinUtils;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = JavalinUtils.makeApp(7070);

        AlunoController alunoController = new AlunoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        EscolaController escolaController = new EscolaController();
        ProfessorController professorController = new ProfessorController();
        IndexController indexController = new IndexController();


        alunoController.registrarRotas(app);
        disciplinaController.registrarRotas(app);
        escolaController.registrarRotas(app);
        professorController.registrarRotas(app);
        indexController.registrarRotas(app);
    }
}