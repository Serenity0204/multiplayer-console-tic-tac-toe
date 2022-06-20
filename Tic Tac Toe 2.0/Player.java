
import java.util.*;

public class Player {

    // turns is a queue used for switching turns between players 
    private static Queue<Character> turns = new LinkedList<Character>();


    // valid is a hashset used to checked duplications for player creation
    private static HashSet<Character> valid = new HashSet<Character>();





    /**
     * addPlayer will add player into the turns
     * @param representation is the name of the player 
     */
    public static void addPlayer(char representation)
    {
        Player.turns.add(representation);
    }





    /**
     * addPlayer is a boolean returned method that will add a player to the game
     * @param representation is the name of the player
     * @return a boolean to tell if the player creation process succeeds or not
     */
    public static boolean canAddPlayer(char representation)
    {
        if(representation == '-') return false;
        if(valid.contains(representation)) return false;
        valid.add(representation);
        return true;
    }







    /**
     * public static void helper method that will switch to next turn
     */
    public static void nextTurn()
    {
        char temp = Player.turns.poll();
        Player.turns.add(temp);
    }





    /**
     * public static char method that will return the current player
     * @return current player
     */
    public static char whosTurn()
    {
        return Player.turns.peek();
    }


}
