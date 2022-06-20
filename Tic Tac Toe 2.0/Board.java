import java.util.*;


public class Board {
    

    // boardSize is the size of the board
    private static int boardSize;

    // boardMap is a hashmap of key = board coordinate (row, col), and value = player char representation
    // note: board domain is [1, playerNum]
    private static LinkedHashMap<List<Integer>, Character> boardMap;




    
    
    /**
     * The constructor of Board object, has one parameter of player numbers so the board size can be determined
     * @param playerNum is the number of players
     */
    public Board(int playerNum)
    {
        Board.boardSize = playerNum;
        boardMap = new LinkedHashMap<List<Integer>, Character>();
        for(int i = 1; i <= playerNum; ++i)
		{
		    for(int j = 1; j <= playerNum; ++j)
		    {
                Board.placeMove(i, j, '-');
		    }
		}
    } 








    /**
     * this method will get the character stored at that coordinate
     * @param row
     * @param col
     * @return char
     */
    public static char getBoard(int row, int col)
    {
        List<Integer> pos = new ArrayList<Integer>();
        pos.add(row);
        pos.add(col);
        if(Board.boardMap.containsKey(pos))
        {
            return boardMap.get(pos);
        }
        return '-';
    }
    



    /**
     * will get the size of the board
     * @return int
     */
    public static int getBoardSize()
    {
        return Board.boardSize;
    }
    





    /**
     * a method that will check if the board is full
     * @return boolean
     */
    public static boolean isFull()
    {
        int size = Board.getBoardSize();
        for(int i = 1; i <= size; ++i) 
        {
            for(int j = 1; j <= size; ++j)
            {
                char spot = Board.getBoard(i, j);
                if(spot == '-') return false;
            }
        }
        return true;
    }






    
    /**
     * this is a private helper method that will place the player on board
     * @param row
     * @param col
     * @param player
     */
    public static void placeMove(int row, int col, char player)
    {
        List<Integer> pos = new ArrayList<Integer>();
        pos.add(row);
        pos.add(col);
        Board.boardMap.put(pos, player);
    }




    /**
     * void type method that will print the board
     */
    public void printBoard()
    {
        System.out.println("(top-> down: row), (left-> right: column)");
        for(int i = 0; i <= Board.getBoardSize(); ++i)
        {
            for(int j = 0; j <= Board.getBoardSize(); ++j)
            {
                if(i == 0 && j == 0) 
                {
                    System.out.print(" |");
                    continue;
                }

                if(i == 0 && j != 0)
                {
                    System.out.print(j + "|");
                    continue;
                }

                if(j == 0 && i != 0)
                {
                    System.out.print(i + "|");
                    continue;
                }
                char c = Board.getBoard(i, j);
                System.out.print(c + "|");
            }
            System.out.println();
            String line = "";
            for(int j = 0; j <= 2 * Board.getBoardSize() + 3; ++j)
            {
                line += "-";
            }
            System.out.println(line);
        }
    }

}
