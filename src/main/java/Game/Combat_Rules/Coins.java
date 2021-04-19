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

public class Coins {

    /**
     * Returns randomized true or false
     *
     * @param numCoins Number of coins throwed.
     * @return Number of face coin obtained.
     */
    public int coinTry(int numCoins) {
        Random rnd = new Random();
        int numFaceCoin = 0;
        for (int i = 0; i < numCoins; i++) {
            boolean tirada = rnd.nextBoolean();
            if (tirada) {
                numFaceCoin++;
            }
        }
        return numFaceCoin;
    }
}
