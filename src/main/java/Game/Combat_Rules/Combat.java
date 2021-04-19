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
public class Combat {
//Constants que indiquen possibles accions de combat

    public static final int ATTACK = 0;
    public static final int DEFENSE = 1;
    public static final int CHEATING = 2;
    public static final int MANEUVER = 3;

    /**
     * Given the code of a strategy, it converts it to a text.
     *
     * @param action Strategy code
     * @return Associated text
     */
    public String strategyToText(int action) {
        switch (action) {
            case ATTACK:
                return "ATTACK";
            case DEFENSE:
                return "DEFENSE";
            case CHEATING:
                return "CHEATING";
            case MANEUVER:
                return "MANEUVER";
        }
        return "UNKNOWN";
    }

    /**
     * It obtains a grade of success according to the action chosen by the fighter.
     *
     * @param fig Fighter doing the action.
     * @param accio Selected action
     * @return Degree of success of the action
     */
    public int calculateSuccessRate(int[] fig, int accio) {
        Fighter fighter = new Fighter();
        switch (accio) {
            case ATTACK:
            case CHEATING:
                return fighter.throwAttack(fig);
            default:
                return fighter.throwDefense(fig);
        }
    }

    /**
     * Resolve a round of actions between two fighters, according to the
     * individual strategies of each.
     *
     * @param player Player Status
     * @param playerAction Strategy chosen by the Player
     * @param opp Opponent Status
     * @param oppAction Strategy chosen by the Adversary.
     */
    public void solveStrategy(int[] player, int playerAction, int[] opp, int oppAction) {
        int playerSuccess = calculateSuccessRate(player, playerAction);
        int oppSuccess = calculateSuccessRate(opp, oppAction);
        Fighter fighter = new Fighter();
        if ((playerAction == ATTACK) && (oppAction == ATTACK)) {
//Player and Opponent: DAMAGED
            fighter.damage(player, oppSuccess);
            fighter.damage(opp, playerSuccess);
        } else if ((playerAction == ATTACK) && (oppAction == DEFENSE)) {
//Opponent: HEAL
            fighter.heal(opp, oppSuccess);
        } else if ((playerAction == ATTACK) && (oppAction == CHEATING)) {
//Opponent: DAMAGE
            fighter.damage(opp, playerSuccess);
        } else if ((playerAction == ATTACK) && (oppAction == MANEUVER)) {
//Opponent: DAMAGE
            fighter.damage(opp, playerSuccess);
        } else if ((playerAction == DEFENSE) && (oppAction == ATTACK)) {
//Player: HEAL
            fighter.heal(player, playerSuccess);
        } else if ((playerAction == DEFENSE) && (oppAction == DEFENSE)) {
//Player and Opponent: HEAL
            fighter.heal(opp, oppSuccess);
            fighter.heal(player, playerSuccess);
        } else if ((playerAction == DEFENSE) && (oppAction == CHEATING)) {
//Player: DAMAGE x2
            fighter.damage(player, oppSuccess * 2);
        } else if ((playerAction == DEFENSE) && (oppAction == MANEUVER)) {
//Player: PENALIZED
            fighter.penalize(player, oppSuccess);
        } else if ((playerAction == CHEATING) && (oppAction == ATTACK)) {
//Player: DAMAGE
            fighter.damage(player, oppSuccess);
        } else if ((playerAction == CHEATING) && (oppAction == DEFENSE)) {
//Opponent: DAMAGE x2
            fighter.damage(opp, playerSuccess * 2);
        } else if ((playerAction == CHEATING) && (oppAction == CHEATING)) {
//Player and Opponent: DAMAGE
            fighter.damage(player, oppSuccess);
            fighter.damage(opp, playerSuccess);
        } else if ((playerAction == CHEATING) && (oppAction == MANEUVER)) {
//Player: PENALIZED
            fighter.penalize(player, oppSuccess);
        } else if ((playerAction == MANEUVER) && (oppAction == ATTACK)) {
//Player: DAMAGE
            fighter.damage(player, oppSuccess);
        } else if ((playerAction == MANEUVER) && (oppAction == DEFENSE)) {
//Opponent: PENALIZED
            fighter.penalize(opp, playerSuccess);
        } else if ((playerAction == MANEUVER) && (oppAction == CHEATING)) {
//Opponent: PENALIZED
            fighter.penalize(opp, playerSuccess);
        } else if ((playerAction == MANEUVER) && (oppAction == MANEUVER)) {
//Player and Opponent: PENALIZED
            fighter.penalize(opp, playerSuccess);
            fighter.penalize(player, oppSuccess);
        } else {
//CAN'T HAPPEN
        }
    }
}
