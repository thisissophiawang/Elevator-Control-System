package connect;

public class ConnectFourModelImpl implements ConnectFourModel {
  private Player[][] board;
  
  private Player turn;
  
  private Player winner;
  
  private boolean gameOver;
  
  private final int rows;
  
  private final int columns;
  
  public ConnectFourModelImpl(int rows, int columns) {
    if (rows < 4 || columns < 4)
      throw new IllegalArgumentException("Invalid board size"); 
    this.rows = rows;
    this.columns = columns;
    initializeBoard();
  }
  
  public void initializeBoard() {
    this.board = new Player[this.rows][this.columns];
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++)
        this.board[i][j] = null; 
    } 
    this.turn = Player.RED;
    this.winner = null;
    this.gameOver = false;
  }
  
  public void makeMove(int column) throws IllegalArgumentException {
    if (column < 0 || column >= this.columns)
      throw new IllegalArgumentException("Invalid column"); 
    if (this.gameOver)
      throw new IllegalStateException("Game is over"); 
    if (this.board[0][column] != null)
      throw new IllegalArgumentException("Column is full"); 
    int i;
    for (i = this.rows - 1; i >= 0; i--) {
      if (this.board[i][column] == null) {
        this.board[i][column] = this.turn;
        break;
      } 
    } 
    if (i >= 0 && checkWin(i, column)) {
      this.winner = this.turn;
      this.gameOver = true;
      this.turn = null;
    } else if (checkDraw()) {
      this.gameOver = true;
      this.turn = null;
    } else {
      this.turn = (this.turn == Player.RED) ? Player.YELLOW : Player.RED;
    } 
  }
  
  private boolean checkDraw() {
    for (int i = 0; i < this.columns; i++) {
      if (this.board[0][i] == null)
        return false; 
    } 
    return true;
  }
  
  public Player getTurn() {
    return this.turn;
  }
  
  public boolean isGameOver() {
    return this.gameOver;
  }
  
  public Player getWinner() {
    return this.winner;
  }
  
  public void resetBoard() {
    initializeBoard();
  }
  
  public Player[][] getBoardState() {
    Player[][] copy = new Player[this.rows][this.columns];
    for (int i = 0; i < this.rows; i++)
      System.arraycopy(this.board[i], 0, copy[i], 0, this.columns); 
    return copy;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        String s = (this.board[i][j] == null) ? "[Empty]" : ("[  " + this.board[i][j].getDisplayName() + "  ]");
        sb.append(s);
      } 
      sb.append("\n");
    } 
    sb.delete(sb.length() - 1, sb.length());
    return sb.toString();
  }
  
  private boolean checkWin(int row, int column) {
    return (checkDirection(row, column, 0, 1) >= 4 || 
      checkDirection(row, column, 1, 0) >= 4 || 
      checkDirection(row, column, 1, 1) >= 4 || 
      checkDirection(row, column, 1, -1) >= 4);
  }
  
  private int checkDirection(int row, int column, int rowDelta, int columnDelta) {
    int count = 1;
    count += countInDirection(row, column, rowDelta, columnDelta);
    count += countInDirection(row, column, -rowDelta, -columnDelta);
    return count;
  }
  
  private int countInDirection(int row, int column, int rowDelta, int columnDelta) {
    int count = 0;
    Player player = this.board[row][column];
    int i = row + rowDelta, j = column + columnDelta;
    for (; i >= 0 && i < this.rows && j >= 0 && j < this.columns && this.board[i][j] == player; 
      i += rowDelta, j += columnDelta)
      count++; 
    return count;
  }
}