import java.util.Scanner;
public class TicTacToe
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int playerCount;

        //main menu, asks for the number of players
        System.out.println("Welcome to a fancy version of the game Tic-Tac-Toe!!!");
        System.out.println("Pick the number of players (3-10):");
        while (true)
        {
            playerCount = scanner.nextInt();
            if (playerCount >= 3 && playerCount <= 10)
                break;
            else
                System.out.println("Error!\nInvalid number of players.\nPlease enter a number between 3 and 10.");
        }

        //asks the players for their char
        char[] playerPieces = new char[playerCount];
        for (int i = 0; i < playerCount; i++)
        {
            System.out.println("Enter character for player " + (i + 1) + ": ");
            char playerPiece;
            while (true)
            {
                playerPiece = scanner.next().charAt(0);
                if (isUniquePiece(playerPiece, playerPieces, i))
                    break;
                else
                    System.out.println("Piece is occupied. Please enter another character.");
            }
            playerPieces[i] = playerPiece;
        }

        //asks for the number of pieces in a row to win
        System.out.println("How many pieces in a row defines a winner? (3-" + (playerCount + 1) + ")");
        int inARowToWin;
        while (true)
        {
            inARowToWin = scanner.nextInt();
            if (inARowToWin >= 3 && inARowToWin <= playerCount + 1)
                break;
            else
                System.out.println("Error!\nInvalid number of pieces in a row.\nPlease enter a number between 3 and " + (playerCount + 1) + ".");
        }

        //initializes the players
        Player[] players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++)
            players[i] = new Player(playerPieces[i]);

        //starts the game
        GameLogic gameLogic = new GameLogic(playerCount, playerPieces, inARowToWin);
        gameLogic.startGame();
    }

    //checks if a piece is unique within other chosen pieces of players
    private static boolean isUniquePiece(char piece, char[] players, int currentIndex)
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (piece == players[i])
                return false;
        }
        return true;
    }
}