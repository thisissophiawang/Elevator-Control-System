package connect;

public interface ConnectFourModel {
  void initializeBoard();
  
  void makeMove(int paramInt) throws IllegalArgumentException;
  
  Player getTurn();
  
  boolean isGameOver();
  
  Player getWinner();
  
  void resetBoard();
  
  Player[][] getBoardState();
}
