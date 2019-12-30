package com.jobsity.challenge.services;


import com.jobsity.challenge.constants.BowlingConstants;
import com.jobsity.challenge.controller.GameController;
import com.jobsity.challenge.controller.IGameController;
import com.jobsity.challenge.exception.BreakRuleBowlingException;
import com.jobsity.challenge.model.Bowler;
import com.jobsity.challenge.model.lambdas.CalculateScore;
import com.jobsity.challenge.model.Frame;
import com.jobsity.challenge.model.Game;
import com.jobsity.challenge.model.PinFall;
import com.jobsity.challenge.model.Result;
import com.jobsity.challenge.model.Roll;
import com.jobsity.challenge.model.RollType;
import com.jobsity.challenge.validators.BowlingValidator;

import java.util.ArrayList;
import java.util.List;

public class BowlingService implements IBowlingService {
    private IGameController gameController;
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


    @Override
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

    @Override
    public List<Game> getGames() {
        return games;
    }

    @Override
    public void calculateScores() throws BreakRuleBowlingException {
        for (Game game : games) {
            BowlingValidator.validateRollsByGame(game);
        }
        games.stream().forEach(game -> {

            //Calculate Pinfall scores
            for (Frame frame : game.getFrames()) {
                CalculateScore<PinFall> calculateScorePinfall = pinfall -> {
                    Integer sum = pinfall.getRolls().stream().mapToInt(Roll::getKnockedDownPins).reduce((a, b) -> a + b).orElse(0);
                    return sum;
                };
                frame.getPinFall().calculateScore(calculateScorePinfall);
            }
            //calculate frame scores
            for (int i = 0; i < game.getFrames().size(); i++) {
                final int index = i;
                CalculateScore<Frame> calculateScoreFrame = f -> {
                    int scoreLastFrame = f.getName() == BowlingConstants.FIRST_FRAME ? 0 : game.getFrames().get(index - 1).getScoreFrame();
                    if (f.getPinFall().getRolls().get(0).getRollType() == RollType.STRIKE) {
                        return scoreLastFrame + f.getPinFall().getScorePinfall() + getNextRolls(game, game.getFrames().get(index).getName(), 2);
                    }
                    if (f.getPinFall().getRolls().get(1).getRollType() == RollType.SPARE) {
                        return scoreLastFrame + f.getPinFall().getScorePinfall() + getNextRolls(game, game.getFrames().get(index).getName(), 1);
                    }
                    return scoreLastFrame + f.getPinFall().getScorePinfall();


                };
                game.getFrames().get(index).calculateScore(calculateScoreFrame);

            }
            ;
        });
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

    @Override
    public void cleanGames() {
        this.games = new ArrayList<>();
    }
}
