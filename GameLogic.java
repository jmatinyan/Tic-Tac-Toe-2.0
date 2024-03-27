class GameLogic
{
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private int inARowToWin;

    //initializes gameLogic object with the parameters
    public GameLogic(int playerCount, char[] playerPieces, int inARowToWin)
    {
        this.board = new Board(playerCount + 1);
        this.players = new Player[playerCount];
        this.inARowToWin = inARowToWin;
        this.currentPlayerIndex = 0;

        //creates player objects, assigns their char
        for (int i = 0; i < playerCount; i++)
            players[i] = new Player(playerPieces[i]);
    }

    //starts the game
    public void startGame()
    {
        while (true)
        {
            board.printBoard();
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("Player " + currentPlayer.getPiece() + "'s turn.");

            int[] move = currentPlayer.makeMove();
            int row = move[0];
            int col = move[1];

            if (isValidMove(move))
            {
                board.placePiece(row, col, currentPlayer.getPiece());
                if (board.checkWin(row, col, currentPlayer.getPiece(), inARowToWin))
                {
                    board.printBoard();
                    System.out.println("Player " + currentPlayer.getPiece() + " wins!");
                    break;
                }

                if (board.isBoardFull())
                {
                    board.printBoard();
                    System.out.println("It's a tie!");
                    break;
                }
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            }
            else
                System.out.println("Invalid move. Try again.");
        }
    }

    //check if the move is valid
    private boolean isValidMove(int[] move)
    {
        int row = move[0];
        int col = move[1];

        //checks if the coordinates are within the board
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize())
            return false;

        //checks if the cell is occupied
        return !board.isCellOccupied(row, col);
    }
}
