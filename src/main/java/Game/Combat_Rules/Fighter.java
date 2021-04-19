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

public class Fighter {
//Format
//NAME - LEVEL - POINTS - LIFE - Max LIFE - ATTACK - Max ATACK - DEFENSE - Max DEFENSE

    public final static int NAME = 0;
    public final static int LEVEL = 1;
    public final static int POINTS = 2;
    public final static int LIFE = 3;
    public final static int MAX_LIFE = 4;
    public final static int ATTACK = 5;
    public final static int MAX_ATTACK = 6;
    public final static int DEFENSE = 7;
    public final static int MAX_DEFENSE = 8;

    /**
     * Inflicts damage to a fighter, subtracting life points, up to a minimum of
     * 0
     *
     * @param fighter Fighter receiving damage
     * @param points Lost life points
     */
    public void damage(int[] fighter, int points) {
        if (fighter[LIFE] > points) {
            fighter[LIFE] = fighter[LIFE] - points;
        } else {
            fighter[LIFE] = 0;
        }
    }

    /**
     * Heals a fighter, who recovers life points. It can never exceed max level.
     *
     * @param fighter Fighter to heal
     * @param points Heal points
     */
    public void heal(int[] fighter, int points) {
        fighter[LIFE] = fighter[LIFE] + points;
        if (fighter[LIFE] > fighter[MAX_LIFE]) {
            fighter[LIFE] = fighter[MAX_LIFE];
        }
    }

    /**
     * Apply a penalty to the fighter. It is done randomly between the value of
     * attack and the one of defense. You are subtracted one point, up to a
     * minimum value of 1.
     *
     * @param fighter Lluitador a penalitzar
     * @param grade Grade of penalty
     */
    public void penalize(int[] fighter, int grade) {
//choose which skill to penalize
        Random rnd = new Random();
        int penalty = ATTACK;
        if (rnd.nextBoolean()) {
            penalty = DEFENSE;
        }
//It is penalized. Low minimum up to 1
        fighter[penalty] -= grade;
        if (fighter[penalty] < 1) {
            fighter[penalty] = 1;
        }
    }

    /**
     * Renew a fighter, canceling all penalties and damage.
     *
     * @param fighter Fighter to renew
     */
    public void renew(int[] fighter) {
        restore(fighter);
        fighter[LIFE] = fighter[MAX_LIFE];
    }

    /**
     * Restores the fighter's attack and defense values ​​to the original
     * values.
     *
     * @param fighter Fighter to restore
     */
    public void restore(int[] fighter) {
        fighter[ATTACK] = fighter[MAX_ATTACK];
        fighter[DEFENSE] = fighter[MAX_DEFENSE];
    }

    /**
     * Solve the awarding of points to the adventurer by defeating an opponent.
     * The amount of points depends on the difference in levels between the two.
     * If it is earn enough points, be warned if you need to level up.
     *
     * @param adventurer Adventurer
     * @param opponent Defeated opponent
     * @return If level up (every 100 points)
     */
    public boolean awardPoints(int[] adventurer, int[] opponent) {
//Calculate multiplier
        float multiplier = 0;
        int numMultiplier = opponent[LEVEL] - adventurer[LEVEL] + 2;
        for (int i = 0; i < numMultiplier; i++) {
            multiplier += 0.5;
        }
//Final points to award
        int oppponentPoints = readPoints(opponent);
        int awardedPoints = Math.round(oppponentPoints * multiplier);
//Level up?
        adventurer[POINTS] += awardedPoints;
        int newLvl = 1 + adventurer[POINTS] / 100;
        if (newLvl > adventurer[LEVEL]) {
            return true;
        }
        return false;
    }

    /**
     * Solve an increase of one level, randomly increasing attack or defense and
     * two maximum life points. In addition, the fighter heals completely.
     *
     * @param fighter Fighter who rises in level.
     */
    public void LevelUp(int[] fighter) {
        fighter[LEVEL]++;
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
//Increase attack
            fighter[MAX_ATTACK]++;
        } else {
//Increase defense
            fighter[MAX_DEFENSE]++;
        }
        fighter[MAX_LIFE] += 2;
//Renew fighter
        renew(fighter);
    }

    //Methods of action linked to the state of the fighter
    /**
     * Resolves an attack roll of a fighter. As many coins are thrown as the its
     * attack value.
     *
     * @param fighter Fighter who makes the roll
     * @return The number of faces obtained
     */
    public int throwAttack(int[] fighter) {
        Coins coins = new Coins();
        return coins.coinTry(fighter[ATTACK]);
    }

    /**
     * Solve a defensive throw from a fighter. As many coins are thrown as its
     * defensive value.
     *
     * @param fighter Fighter who rolls
     * @return The number of faces obtained
     */
    public int throwDefense(int[] fighter) {
        Coins coins = new Coins();
        return coins.coinTry(fighter[DEFENSE]);
    }

    /**
     * Given a fighter, choose at random which strategy to use in a round of
     * combat.
     *
     * @param fighter Fighter who chooses action
     * @return Selected action
     */
    public int throwRandomStrategy(int[] fighter) {
        Random rnd = new Random();
        int defenseLimit = 3;
//If little life left, he defends 50% of the blows
        if (fighter[LIFE] < 2) {
            defenseLimit = 1;
        }
        int accio = rnd.nextInt(10);
        if ((accio >= 0) && (accio < defenseLimit)) {
            return Combat.ATTACK;
        } else if ((defenseLimit >= 3) && (accio < 6)) {
            return Combat.DEFENSE;
        } else if ((accio >= 6) && (accio < 8)) {
            return Combat.CHEATING;
        } else {
            return Combat.MANEUVER;
        }
    }

    //Methods to facilitate data reading
    /**
     * Identifier a fighter. Using a method makes reading easier of the code,
     * rather than accessing positions in an array.
     *
     * @param fighter Fighter whose identifier you want to read
     * @return Fighter ID
     */
    public int readId(int[] fighter) {
        return fighter[NAME];
    }

    /**
     * It says what the fighter level is. Using a method makes reading easier of
     * the code, rather than accessing positions in an array.
     *
     * @param fighter Fighter who wants to read the level
     * @return
     */
    public int readLvl(int[] fighter) {
        return fighter[LEVEL];
    }

    /**
     * It says what points the fighter is worth. Using a method makes it easier
     * to read the code, rather than accessing positions in an array.
     *
     * @param fighter Fighter whose points you want to read
     * @return
     */
    public int readPoints(int[] fighter) {
        return fighter[POINTS];
    }

    /**
     * It says what life the fighter has. Using a method makes it easier to read
     * the code, rather than accessing positions in an array.
     *
     * @param fighter Fighter whose life you want to read
     * @return Vida
     */
    public int readLife(int[] fighter) {
        return fighter[LIFE];
    }

    /**
     * It says what maximum life the fighter has. Using a method makes reading
     * easier of the code, rather than accessing positions in an array.
     *
     * @param fighter Fighter who wants to read the maximum life
     * @return Vida
     */
    public int readMaxLife(int[] fighter) {
        return fighter[MAX_LIFE];
    }

    /**
     * He says what attack the fighter has. Using a method makes it easier to
     * read the code, rather than accessing positions in an array.
     *
     * @param fighter Fighter who wants to read the attack
     * @return Degree of attack
     */
    public int readAttack(int[] fighter) {
        return fighter[ATTACK];
    }

    public int readMaxAttack(int[] fighter) {
        return fighter[MAX_ATTACK];
    }

    /**
     * It says what defense the fighter has. Using a method makes it easier to
     * read the code, rather than accessing positions in an array.
     *
     * @param fighter Fighter whose defense you want to read
     * @return Degree of defense
     */
    public int readDefense(int[] fighter) {
        return fighter[DEFENSE];
    }

    public int readMaxDefense(int[] fighter) {
        return fighter[MAX_DEFENSE];
    }

    /**
     * It says whether a fighter is dead or not. That is, if their life points
     * are 0 right now. Using a method makes it easier to read the code, more
     * than access positions in an array.
     *
     * @param fighter Fighter to check
     * @return Whether considered dead (true) or not (false)
     */
    public boolean isDead(int[] fighter) {
        return (fighter[LIFE] == 0);
    }
}
