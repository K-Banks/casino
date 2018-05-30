import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import models.Blackjack;
import models.Player;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Player player = new Player();
        Blackjack newGame = new Blackjack();

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

        get("/blackjack", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            newGame.gameReset();
            player.setHasBet(false);
            player.setCurrentBet(0);
            boolean gameOver = false;
            model.put("player", player);
            model.put("gameOver", gameOver);
            return new ModelAndView(model, "blackjack.hbs");
        }, new HandlebarsTemplateEngine());

        post("/blackjack", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("player", player);
            boolean dealerBust = false;
            boolean playerBust =false;
            boolean playerWin = false;
            boolean gameOver = false;
            boolean invalidBet = false;
            Integer playerValue = 0;
            Integer dealerValue = 0;

            if(player.isHasBet()){
                if (request.queryParams("playerAction").equals("hit")) {
                    newGame.dealCard(newGame.generateRandomNumber(), "Player");
                    String dealerCard = newGame.getDealerHand().get(1);
                    model.put("dealerCard", dealerCard);
                    playerBust = newGame.checkBust("Player");
                    if (playerBust) {
                        player.changeMoney(-player.getCurrentBet());
                        gameOver = true;
                    }
                } else {
                    while(newGame.evaluateHand("dealer") < 17){
                        newGame.dealCard(newGame.generateRandomNumber(), "dealer");
                        dealerBust = newGame.checkBust("dealer");
                    }
                    gameOver=true;
                    if(dealerBust){
                        player.changeMoney(player.getCurrentBet());
                    }else{
                        String checkWin = newGame.checkWin("Player");
                        if(checkWin.equals("win")){
                            player.changeMoney(player.getCurrentBet());
                            playerWin = true;
                        }else {
                            player.changeMoney(-player.getCurrentBet());
                        }
                    }

                }
                model.put("playerWin", playerWin);
                model.put("playerBust", playerBust);
                model.put("dealerBust", dealerBust);
            }else {
                Integer playerBet= Integer.parseInt(request.queryParams("playerBet"));
                if(playerBet > player.getMoney()){
                    invalidBet=true;
                }else{
                    player.setCurrentBet(playerBet);
                    player.setHasBet(true);
                    newGame.dealCard(newGame.generateRandomNumber(), "Player");
                    newGame.dealCard(newGame.generateRandomNumber(), "dealer");
                    newGame.dealCard(newGame.generateRandomNumber(), "Player");
                    newGame.dealCard(newGame.generateRandomNumber(), "dealer");
                    boolean checkBlackjack = newGame.checkBlackjack("Player");
                    if (checkBlackjack) {
                        player.changeMoney(player.getCurrentBet());
                        gameOver = true;
                    }
                    String dealerCard = newGame.getDealerHand().get(1);
                    model.put("dealerCard", dealerCard);
                    model.put("checkBlackjack", checkBlackjack);
                }
                model.put("invalidBet",invalidBet);
            }
            playerValue = newGame.evaluateHand("Player");
            dealerValue = newGame.evaluateHand("dealer");
            model.put("playerValue", playerValue);
            model.put("dealerValue", dealerValue);
            model.put("game", newGame);
            model.put("gameOver", gameOver);
            return new ModelAndView(model, "blackjack.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
