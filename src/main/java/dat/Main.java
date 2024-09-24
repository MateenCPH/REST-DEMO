package dat;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> countries = new ArrayList<>();

    public static void main(String[] args) {
        countries.add("USA");
        countries.add("Canada");
        countries.add("Mexico");


        Javalin app = Javalin.create().start(8080);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/countries", ctx -> ctx.json(countries, String.class));

        app.post("/countries", ctx -> {
            String country = ctx.body();
            countries.add(country);
            ctx.status(201);
            ctx.result(country);
        });

        app.post("/countries/{country}", ctx -> {
            String country = ctx.pathParam("country");
            countries.add(country);
            ctx.status(201);
        });

        app.delete("/countries/{country}", ctx -> {
            String country = ctx.pathParam("country");
            countries.remove(country);
            ctx.status(204);
        });
    }
}