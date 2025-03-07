package connect;

public class Main {
  public static void main(String[] args) {
    ConnectFourModel model = new ConnectFourModelImpl(6, 7);
    ConnectFourView view = new SwingConnectFourView("Connect 4");
    ConnectFourController controller = new SwingConnectFourController(view, model);
    controller.playGame();
  }
}
