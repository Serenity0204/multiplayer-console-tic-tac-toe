import java.util.*;

public class GameLogic {

    // winning condition
    private static int winningRow;




    /**
     * A higher level method that utilizes lower level helper methods to determine if there's a winner
     * @param row
     * @param col
     * @return boolean indicates that if the game has a winner
     */
    public static boolean checkWin(int row, int col)
    {   
        boolean horizontal = GameLogic.checkHorizontalOrVertical(row, col, true);
        boolean vertical = GameLogic.checkHorizontalOrVertical(row, col, false);
        //boolean botLeftToTopRight = GameLogic.checkDiagonal(row, col, true);
        //boolean topLeftToBotRight = GameLogic.checkDiagonal(row, col, false);
        boolean checkDia = GameLogic.checkDiagonal();
        if(horizontal || vertical || checkDia) return true;
        return false;
    }




     /**
     * a getter method that will get the winning row
     * @return int, winning row
     */
    public static int getWinningRow()
    {
        return GameLogic.winningRow;
    }
    




    /**
     * setter of the winning row
     * @param winningRow
     */
    public static void setWinningRow(int winningRow)
    {
        GameLogic.winningRow = winningRow;
    }







    /**
     * a method validating if the move is valid
     * @param row
     * @param col
     * @return boolean
     */
    public static boolean validMove(int row, int col)
    {
        int boardSize = Board.getBoardSize();
        if(row > boardSize || col > boardSize) return false;
        char coordinate = Board.getBoard(row, col);
        if(coordinate != '-') return false;
        return true;
    }




    /**
     * a method that returns a  boolean indicate if there's a diagonal winner
     * @return boolean
     */
    private static boolean checkDiagonal()
    {
        int size = Board.getBoardSize();
        int winNum = GameLogic.getWinningRow();
        
        
        for(int row = 1; row <= size; ++row)
        {
            int col = 1;

            int right = col;
            int left = row;
            int diff = left - right + 1;

            if(diff < winNum) continue;
            ArrayList<Character> seq = new ArrayList<Character>();

            int count = 1;
            while(count <= diff)
            {
                char spot = Board.getBoard(left, right);
                seq.add(spot);

                count++;
                left--;
                right++;
            }
            boolean matched = GameLogic.matching(seq);
            if(matched) return true;
        }



        for(int col = 2; col <= size; ++col)
        {
            int row = size;
            int left = row;
            int right = col;
            int diff = left - right + 1;
            if(diff < winNum) continue;

            ArrayList<Character> seq = new ArrayList<Character>();
            int count = 1;
            while(count <= diff)
            {
                char spot = Board.getBoard(left, right);
                seq.add(spot);
                count++;
                left--;
                right++;
            }
            boolean matched = GameLogic.matching(seq);
            if(matched) return true;
        }




        int counter = 1;
        for(int row = size; row >= 1; --row)
        {
            int col = 1;
            int left = row;
            int right = col;
            int diff = counter;
            counter++;

            if(diff < winNum) continue;
            ArrayList<Character> seq = new ArrayList<Character>();
            int count = 1;
            while(count <= diff)
            {
                char spot = Board.getBoard(left, right);
                seq.add(spot);
                count++;
                left++;
                right++;
            }
            boolean matched = GameLogic.matching(seq);
            if(matched) return true;
        }

        counter = 1;
        for(int col = size; col >= 1; --col)
        {
            int row = 1;
            int left = row;
            int right = col;
            int diff = counter;
            counter++;
            if(diff < winNum) continue;
            ArrayList<Character> seq = new ArrayList<Character>();
            int count = 1;
            while(count <= diff)
            {
                char spot = Board.getBoard(left, right);
                seq.add(spot);
                count++;
                left++;
                right++;
            }
            boolean matched = GameLogic.matching(seq);
            if(matched) return true;
        }
        return false;
    }












    /**
     * helper method that will check if either horizontal or vertical winning happens on a specific row/col
     * @param pos a versatile variable indicating the row or col number depended on rowOrCol
     * @param colOrRow a boolean varaible to control it's row mode or col mode, true stands for col, false stands for row
     * @return boolean
     */
    private static boolean checkHorizontalOrVertical(int row, int col, boolean colOrRow)
    {   
        int winNum = GameLogic.getWinningRow();
        int size = Board.getBoardSize();
        if(colOrRow)
        {
            for(int i = 1; i <= size - winNum + 1; ++i)
            {
                char target = Board.getBoard(row, i);
                if(target == '-') continue;
                int count = 1;
                for(int j = i + 1; j <= size; ++j)
                {
                    char spot = Board.getBoard(row, j);
                    if(target != spot) break;
                    if(target == spot) count++;
                }
                if(count == winNum) return true;
            }
        }
        
        if(!colOrRow)
        {
            for(int i = 1; i <= size - winNum + 1; ++i)
            {
                char target = Board.getBoard(i, col);
                if(target == '-') continue;
                int count = 1;
                for(int j = i + 1; j <= size; ++j)
                {
                    char spot = Board.getBoard(j, col);
                    if(target != spot) break;
                    ++count;
                    if(count == winNum) return true;
                }
            }
        }
        return false;
    }





    
    /**
     * a matching algorithm that will check if an arraylist has x amount of contiguous same characters
     * x is the winning row
     * @param seq
     * @return boolean
     */
    private static boolean matching(ArrayList<Character> seq)
    {
        int winNum = GameLogic.getWinningRow();
        for(int i = 0; i < seq.size(); ++i)
        {
            int count = 1;
            char target = seq.get(i);
            if(target == '-') continue;
            for(int j = i + 1; j < seq.size(); ++j)
            {
                if(target != seq.get(j)) break;
                count++;
                if(count == winNum) return true;
            }
        }
        return false;
    }
}

