package connect;

public interface ConnectFourView {
  void addController(ConnectFourController paramConnectFourController) throws IllegalArgumentException;
  
  void setBoardButtons(Player[][] paramArrayOfPlayer) throws IllegalArgumentException;
  
  void setGameState(String paramString) throws IllegalArgumentException;
}


