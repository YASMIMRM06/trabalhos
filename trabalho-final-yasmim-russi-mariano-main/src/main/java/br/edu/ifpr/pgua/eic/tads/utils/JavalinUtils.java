package br.edu.ifpr.pgua.eic.tads.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.template.Configuration;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinFreemarker;
import io.javalin.rendering.template.JavalinPebble;

public class JavalinUtils {
    public static Javalin makeApp(int port) {
        return Javalin.create().start(port);
    }
}

