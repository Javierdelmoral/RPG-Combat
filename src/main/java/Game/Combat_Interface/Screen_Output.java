/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Combat_Interface;

/**
 *
 * @author Javier
 */
import Game.Combat_Rules.Beasts;
import Game.Combat_Rules.Fighter;

public class Screen_Output {

    /**
     * Displays the game start message on the screen
     *
     */
    public void showWelcome() {
        System.out.println("Welcome to the Combat Game");
        System.out.println("===========================");
        System.out.println("Cleverly choose your strategy to survive ...");
    }

    /**
     * Shows the status of a fighter.
     *
     * @param fig Fighter to display
     */
    public void showFighter(int[] fig) {
        Fighter fighter = new Fighter();
        Beasts beasts = new Beasts();
        int id = fighter.readId(fig);
        String nom = beasts.translateID_toName(id);
        System.out.print(nom);
        System.out.print("\nLevel: " + fighter.readLvl(fig));
        System.out.print(" (Points: " + fighter.readPoints(fig) + ")");
        System.out.print("\tLIFE: " + fighter.readLife(fig));
        System.out.print(" (" + fighter.readMaxLife(fig) + ")");
        System.out.print("\tATTACK: " + fighter.readAttack(fig));
        System.out.print(" (" + fighter.readMaxAttack(fig) + ")");
        System.out.print("\tDEFENSE: " + fighter.readDefense(fig));
        System.out.print(" (" + fighter.readMaxDefense(fig) + ")");
    }

    /**
     * Shows the current status of the player against his opponent.
     *
     * @param player Player
     * @param opponent Opponent
     */
    public void showVersus(int[] player, int[] opponent) {
        System.out.print("PLAYER: ");
        showFighter(player);
        System.out.println("\n\nVS");
        System.out.print("\nOPPONENT: ");
        showFighter(opponent);
    }
}
