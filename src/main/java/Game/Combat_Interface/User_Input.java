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
import java.util.Scanner;
import Game.Combat_Rules.Beasts;
import Game.Combat_Rules.Combat;

public class User_Input {

    /**
     * Choose the player's opponent according to his answer.
     *
     * @return Text string with the answer
     */
    public int[] chooseOpponent(int level) {
        System.out.println("\nWhat opponent do you want to fight in this fight? ");
        Beasts beasts = new Beasts();
        beasts.showOpponents();
        System.out.println("\nChoose one by its name:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int[] adversari = beasts.searchOpponent(input);
        if (adversari == null) {
            System.out.println("\nThis enemy does not exist. Random one will be chosen.");
            adversari = beasts.chooseRandomOpponent(level);
        }
        return adversari;
    }

    /**
     * Ask the user which strategy want to use, out of four possible.
     *
     * @return Action to be taken, according to the constants of the Combat
     * class.
     */
    public int askStrategy() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWhat strategy do you want to follow this round?");
        System.out.println("[A]ttack\t[D]efense\t[C]heat  \t[M]aneuver");
        System.out.println("\n------------------------------");
        boolean ask = true;
        int action = -1;
        while (ask) {
            System.out.print("\nAction: ");
            String input = sc.nextLine();
            if ("A".equalsIgnoreCase(input)) {
                action = Combat.ATTACK;
                ask = false;
            } else if ("D".equalsIgnoreCase(input)) {
                action = Combat.DEFENSE;
                ask = false;
            } else if ("C".equalsIgnoreCase(input)) {
                action = Combat.CHEATING;
                ask = false;
            } else if ("M".equalsIgnoreCase(input)) {
                action = Combat.MANEUVER;
                ask = false;
            } else {
                System.out.print("\nIncorrect action...");
            }
        }
        return action;
    }
}
