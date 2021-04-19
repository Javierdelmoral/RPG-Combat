/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.Combat_Rules;

/**
 *
 * @author Javier
 */
import java.util.Random;

public class Beasts {
//Name identifier 

    private String[] names = {"Adventurer",
        "Dwarf", "Kobold",
        "Orc", "Elf",
        "Witch", "Ogre",
        "Knight", "Troll",
        "Mage", "Hidra"};

    //ARRAY FORMAT:
    //NAME - LEVEL - POINTS - LIFE - Max LIFE - ATTACK - Max ATACK - DEFENSE - Max DEFENSE
//pLAYER: ID = 0
    private int[] player = {0, 1, 0, 30, 50, 4, 9, 8, 12};
//Possible opponents in the game
    private int[][] opponents = {
        {1, 1, 25, 8, 8, 3, 3, 3, 3},
        {2, 1, 30, 10, 10, 4, 4, 2, 2},
        {3, 2, 35, 12, 12, 4, 4, 3, 3},
        {4, 2, 40, 14, 14, 3, 3, 4, 4},
        {5, 3, 45, 15, 15, 3, 3, 5, 5},
        {6, 3, 50, 16, 16, 5, 5, 2, 2},
        {7, 4, 55, 15, 15, 4, 4, 4, 4},
        {8, 4, 60, 18, 18, 3, 3, 5, 5},
        {9, 5, 70, 22, 22, 4, 4, 6, 6},
        {10, 5, 80, 30, 30, 8, 8, 2, 2}
    };
//Initialization using a global variable
    private final Fighter fighter = new Fighter();

    /**
     * Generates a new player
     *
     * @return An array with the data of an initial player
     */
    public int[] generatePlayer() {
        fighter.renew(player);
        return player;
    }

    /**
     * Given a name, it generates the corresponding opponent. If this name does
     * not exist, is generated at random.
     *
     * @param oppName Name of the opponent to get
     * @return The fighter with that name, or null if it doesn't exist
     */
    public int[] searchOpponent(String oppName) {
        for (int i = 0; i < opponents.length; i++) {
            int id = fighter.readId(opponents[i]);
            String name = translateID_toName(id);
            if (name.equalsIgnoreCase(oppName)) {
                fighter.renew(opponents[i]);
                return opponents[i];
            }
        }
        return null;
    }

    /**
     * Given a level, it generates random opponent. At least at the same level.
     *
     * @param level Level close to that of the opponent to obtain
     * @return An opponent
     */
    public int[] chooseRandomOpponent(int level) {
        Random rnd = new Random();
        int[] opponent = null;
        boolean search = true;
        while (search) {
            int i = rnd.nextInt(opponents.length);
            opponent = opponents[i];
            int nivellAdv = fighter.readLvl(opponent);
            int dif = level - nivellAdv;
            if ((dif >= -1) && (dif <= 1)) {
                search = false;
            }
        }
//Renew fighter, ready to fight
        fighter.renew(opponent);
        return opponent;
    }

    /**
     * Transform a fighter identifier into your name.
     *
     * @param id Identifier
     * @return The text string with the name.
     */
    public String translateID_toName(int id) {
        if ((id >= 0) && (id < names.length)) {
            return names[id];
        }
        return "UNKNOWN";
    }

    public void showOpponents() {
        int i = 1;
        for (String name : names) {
            System.out.println("OPPONENT " + (i) + ". " + name);
            i++;
        }
    }
}
