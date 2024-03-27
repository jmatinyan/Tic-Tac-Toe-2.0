import java.util.Scanner;
class Player
{
    private char piece;

    //initializes the player with a unique char
    public Player(char piece)
    {
        this.piece = piece;
    }

    //gets the player's piece
    public char getPiece()
    {
        return piece;
    }

    //tells the player to move, shows the coordinates
    public int[] makeMove()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + piece + ", enter row and column (separated by space): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new int[]{row, col};
    }
}