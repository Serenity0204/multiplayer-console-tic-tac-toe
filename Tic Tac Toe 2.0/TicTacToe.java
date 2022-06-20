
import java.util.Scanner;


public class TicTacToe {
    public static void main(String[] args)
    {
        TicTacToe.init();
    }




    /**
     * A method that will clear the console
     */
    private static void clearScreen() 
    {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 







    /**
     * init of the game
     */
    private static void init()
    {
        Scanner scnr = new Scanner(System.in);
        boolean ok = false;
        int playerCount = 0;
        int winningRow = 0;

        // this block is taking user input for the number of players
        while(!ok)
        {   
            System.out.print("How many players? [3 -> 10] inclusive: ");
            String input = scnr.next();
            try
            {
                playerCount = Integer.parseInt(input);
                if(playerCount <= 10 && playerCount >= 3)
                {
                    ok = true;
                }
                else
                {
                    System.out.println("Number out of range, please enter again.");
                } 
            }
            catch(NumberFormatException ne)
            {
                System.out.println("Input is not a number, try again");
            }
        }
        System.out.println();
        
        
        // this block is taking user input for player creation 
        int count = 0;
        while(count < playerCount)
        {
            System.out.print("Enter the player's representation: ");
            char representation = scnr.next().charAt(0);
            boolean canAdd = Player.canAddPlayer(representation);
            if(!canAdd)
            {
                System.out.println("The user name is already taken or invalid, please try another one.");
                continue;
            }
            Player.addPlayer(representation);
            count++;
        }   
        System.out.println();

        ok = false;
        while(!ok)
        {
            System.out.print("Decide the winning row: [3 -> " + (playerCount + 1) + "] inclusive: ");
            String input = scnr.next();
            try
            {
                winningRow = Integer.parseInt(input);
                if(winningRow >= 3 && winningRow <= (playerCount + 1))
                {
                    ok = true;
                }
                else
                {
                    System.out.println("The winning row number is invalid, please try again.");
                }
            }
            catch(NumberFormatException ne)
            {
                System.out.println("Input is not a number, try again");
            }
        }

        TicTacToe.clearScreen();
        Board myGameBoard = new Board(playerCount + 1);
        GameLogic.setWinningRow(winningRow);
        

        // defualt winner
        char winner = '-';

        while(!Board.isFull())
        {
            char player = Player.whosTurn();
            boolean breaker = false;
            int row = 0, col = 0;
            while(!breaker)
            {
                myGameBoard.printBoard();
                System.out.println("It's " + player + "'s turn, please enter a valid (row, col) coordinate: ");
                System.out.print("row: ");
                String rowInput = scnr.next();
                System.out.print("col: ");
                String colInput = scnr.next();
                try
                {
                    row = Integer.parseInt(rowInput);
                    col = Integer.parseInt(colInput);
                    boolean isValidMove = GameLogic.validMove(row, col);
                    if(!isValidMove)
                    {
                        System.out.println("It's not a valid move, please try again!");
                        continue;
                    }
                
                    Board.placeMove(row, col, player);
                    breaker = true;
                }
                catch(NumberFormatException ne)
                {
                    System.out.println("It's not a valid coordinate, please enter again!");
                    continue;
                }


            }
            
            boolean hasWon = GameLogic.checkWin(row, col);
            if(hasWon)
            {
                winner = player;
                break;
            }
            Player.nextTurn();
            TicTacToe.clearScreen();
        }

        myGameBoard.printBoard();
        if(winner != '-')
        {
            System.out.println("Winning row: " + winningRow);
            System.out.println("The winner is: " + winner);
        }
        if(winner == '-')
        {
            System.out.println("No one wins! ");
        }


        scnr.close();
    }

    
}
