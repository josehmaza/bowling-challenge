package com.jobsity.challenge.services;


import com.jobsity.challenge.controller.GameController;
import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Bowler;
import com.jobsity.challenge.model.Game;
import com.jobsity.challenge.model.Result;
import com.jobsity.challenge.model.Roll;

import java.util.ArrayList;
import java.util.List;

public class BowlingService {
    private GameController gameController;
    private static BowlingService single_instance = null;
    private List<Game> games;

    public BowlingService() {
        games = new ArrayList();
        this.gameController = GameController.getInstance();
    }


    public static BowlingService getInstance() {
        if (single_instance == null)
            single_instance = new BowlingService();

        return single_instance;
    }


    public void addRoll(Result result) throws BreakRuleBowlingException {
        Boolean existsGamePlayer = games.stream().anyMatch(bowler -> bowler.getPlayer().getName().equals(result.getNamePlayer()));
        if (existsGamePlayer) {
            Game gamePlayer = games.stream().filter(game -> game.getPlayer().getName().equals(result.getNamePlayer())).findAny().orElse(null);
            this.gameController.addRoll(result, gamePlayer);
        } else {
            Game game = new Game();
            game.setPlayer(new Bowler(result.getNamePlayer()));
            games.add(game);
            this.gameController.addRoll(result, game);
        }

    }

    public List<Game> getGames() {
        return games;
    }

    public void calculateScores() {

    }

    private int getNextRolls(Game game, int name, int numberOfRolls) {
        //Get the following frames
        int sumNext2Rolls = game.getFrames().stream().filter(f -> {
            return f.getName() > name;
            //Flatennig its rolls
        }).flatMap(frame -> frame.getPinFall().getRolls().stream())
                //Get next numberOfRolls
                .limit(numberOfRolls)
                .mapToInt(Roll::getKnockedDownPins)
                //Sum its knocked down pins
                .reduce((a, b) -> a + b).orElse(0);

        return sumNext2Rolls;
    }
}
