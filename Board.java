import java.util.Arrays;

class Board
{
    private char[][] board;

    //initializing the board
    public Board(int size)
    {
        board = new char[size][size];
        initializeBoard();
    }

    //board with empty spaces
    private void initializeBoard()
    {
        for (char[] chars : board)
            Arrays.fill(chars, ' ');
    }

    //prints the present state of the board
    public void printBoard()
    {
        //column numbers
        System.out.print("    ");
        for (int i = 0; i < board.length; i++)
            System.out.print(i + "   ");
        System.out.println();

        for (int i = 0; i < board.length; i++)
        {
            //row numbers
            System.out.print((i) + " | ");

            for (int j = 0; j < board[i].length; j++)
                //stuff inside cells with borders
                System.out.print(board[i][j] + " | ");
            System.out.println();

            //horizontal borders
            System.out.print("  +---");
            for (int j = 1; j < board[i].length; j++)
                System.out.print("+---");
            System.out.println("+");
        }
    }

    //checks if a cell is occupied
    public boolean isCellOccupied(int row, int col)
    {
        return board[row][col] != ' ';
    }

    //puts a player's piece on the board
    public void placePiece(int row, int col, char piece)
    {
        board[row][col] = piece;
    }

    //board is full or not
    public boolean isBoardFull()
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    //checks win
    public boolean checkWin(int row, int col, char piece, int inARow)
    {
        return checkRow(row, piece, inARow) || checkColumn(col, piece, inARow) || checkDiagonals(row, col, piece, inARow);
    }
    //checks if the same pieces are there in a row
    private boolean checkRow(int row, char piece, int inARow)
    {
        int count = 0;
        for (int j = 0; j < board[row].length; j++)
        {
            if (board[row][j] == piece)
            {
                count++;
                if (count == inARow)
                    return true;
            }
            else
                count = 0;
        }
        return false;
    }

    //checks if the same pieces are there in a column
    private boolean checkColumn(int col, char piece, int inARow)
    {
        int count = 0;
        for (int i = 0; i < board.length; i++)
        {
            if (board[i][col] == piece)
            {
                count++;
                if (count == inARow)
                    return true;
            }
            else
                count = 0;
        }
        return false;
    }

    //checks if any diagonals have the same pieces together
    private boolean checkDiagonals(int row, int col, char piece, int inARow)
    {
        return checkMainDiagonal(row, col, piece, inARow) || checkDiagonal2(row, col, piece, inARow);
    }

    //checks if the main diagonal checks out
    private boolean checkMainDiagonal(int row, int col, char piece, int inARow)
    {
        int count = 0;
        int i = row - 1;
        int j = col - 1;
        while (i >= 0 && j >= 0 && board[i][j] == piece)
        {
            count++;
            i--;
            j--;
        }
        i = row + 1;
        j = col + 1;
        while (i < board.length && j < board[row].length && board[i][j] == piece)
        {
            count++;
            i++;
            j++;
        }
        return count >= inARow-1;
    }

    //checks if the reverse diagonal checks out
    private boolean checkDiagonal2(int row, int col, char piece, int inARow)
    {
        int count = 0;
        int i = row - 1;
        int j = col + 1;
        while (i >= 0 && j < board[row].length && board[i][j] == piece)
        {
            count++;
            i--;
            j++;
        }
        i = row + 1;
        j = col - 1;
        while (i < board.length && j >= 0 && board[i][j] == piece)
        {
            count++;
            i++;
            j--;
        }
        return count >= inARow-1;
    }

    //gets the size of the board
    public int getSize()
    {
        return board.length;
    }
}
