package connect;

public class SwingConnectFourController implements ConnectFourController {
  private final ConnectFourModel model;
  
  private final ConnectFourView view;
  
  public SwingConnectFourController(ConnectFourView view, ConnectFourModel model) {
    this.view = view;
    this.model = model;
  }
  
  public void playGame() {
    this.view.addController(this);
    this.view.setBoardButtons(this.model.getBoardState());
  }
  
  public void dropPiece(int column) {
    this.model.makeMove(column);
    this.view.setBoardButtons(this.model.getBoardState());
    if (this.model.isGameOver()) {
      if (this.model.getWinner() == null) {
        this.view.setGameState("It's a tie! Game over.");
      } else {
        this.view.setGameState(this.model.getWinner().name() + " wins! Game over.");
      } 
    } else {
      this.view.setGameState(this.model.getTurn().toString() + "'s turn.");
    } 
  }
  
  public void quitGame() {
    System.exit(0);
  }
  
  public void startGame() {
    this.model.resetBoard();
    this.view.setBoardButtons(this.model.getBoardState());
    this.view.setGameState("Welcome to Connect Four! Red player goes first.");
  }
}