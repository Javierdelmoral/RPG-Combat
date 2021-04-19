/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Combat;

/**
 *
 * @author Javier
 */
import Game.Combat_Rules.Beasts;
import Game.Combat_Rules.Combat;
import Game.Combat_Rules.Fighter;
import Game.Combat_Interface.User_Input;
import Game.Combat_Interface.Screen_Output;

public class Start_Combat {

    public static final int MAX_COMBAT = 10;
    private User_Input input = new User_Input();
    private Screen_Output output = new Screen_Output();
    private Fighter fighter = new Fighter();
    private Combat combat = new Combat();
    private Beasts beasts = new Beasts();

    public static void main(String[] args) {
        Start_Combat program = new Start_Combat();
        program.start();
    }

    public void start() {
        output.showWelcome();
        int[] player = beasts.generatePlayer();
        int numCombat = 0;
        boolean play = true;
        while (play) {
            numCombat++;
//Before each fight the player is restored
            fighter.restore(player);
//Start combat
            System.out.println("*** COMBAT " + numCombat);
            System.out.print("\nCurrent player status: ");
            output.showFighter(player);
            System.out.println("\n**************************");
//You get the opponent
            int[] opponent = input.chooseOpponent(fighter.readLvl(player));
//Combat
            combat(player, opponent);
//End
            play = endCombat(player, opponent);
            if (numCombat == MAX_COMBAT) {
                System.out.println("\nYou have survived all the fightings. Congratulations!!");
                play = false;
            }
        }
        System.out.print("\nFinal player status:");
        output.showFighter(player);
    }

    /**
     * Solve all rounds of a fight.
     *
     * @param player Player status
     * @param opponent Status of the opponent
     */
    public void combat(int[] player, int[] opponent) {
        boolean fight = true;
        int numRound = 0;
        while (fight) {
            numRound++;
            if (numRound % 5 == 0) {
//In multiple rounds of five the attack and defense are restored
                fighter.restore(player);
                fighter.restore(opponent);
            }
            System.out.println("\n--- ROUND " + numRound);
            output.showVersus(player, opponent);
            System.out.println("\n--------------------------");
            int playerAction = input.askStrategy();
            int oppAction = fighter.throwRandomStrategy(opponent);
            System.out.print("\nYou chose " + combat.strategyToText(playerAction));
            System.out.println(" and your enemy chose " + combat.strategyToText(oppAction));
            combat.solveStrategy(player, playerAction, opponent, oppAction);
            if (fighter.isDead(player) || fighter.isDead(opponent)) {
                fight = false;
            }
        }
    }

    /**
     * Solve the end of the fight.
     *
     * @param player Player status
     * @param opponent Status of the opponent
     * @returns Whether the player should keep playing (true) or not (false)
     */
    public boolean endCombat(int[] player, int[] opponent) {
        if (fighter.isDead(player)) {
//Has perdut (Nota: tambe inclou el cas que tots dos moren alhora)
            System.out.println("\nYou have been defeated ... :-(");
            return false;
        }
        System.out.println("\nYOU WON THE FIGHT:-)");
        boolean pujarNivell = fighter.awardPoints(player, opponent);
        if (pujarNivell) {
            System.out.println("\nYou have leveled up!!");
            fighter.LevelUp(player);
        }
        return true;
    }
}
