import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Player;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Player player = new Player();
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        post("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String playerName = request.queryParams("playerName");
            player.setName(playerName);
            model.put("player", player);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
