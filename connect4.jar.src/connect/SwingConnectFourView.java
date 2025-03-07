package connect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SwingConnectFourView extends JFrame implements ConnectFourView {
  private final JLabel gameStateLabel;
  
  private final JButton restartButton;
  
  private final JButton quitButton;
  
  private final JButton[][] boardButtons;
  
  public SwingConnectFourView(String title) {
    super(title);
    setLocation(200, 200);
    JPanel upPanel = new JPanel();
    JPanel midPanel = new JPanel();
    JPanel downPanel = new JPanel();
    setLayout(new BorderLayout());
    add(upPanel, "North");
    add(midPanel, "Center");
    add(downPanel, "South");
    upPanel.setLayout(new FlowLayout());
    midPanel.setLayout(new GridLayout(6, 7));
    downPanel.setLayout(new FlowLayout());
    this.gameStateLabel = new JLabel();
    this.gameStateLabel.setText("Welcome to Connect Four! Red player goes first.");
    this.restartButton = new JButton("Restart Game");
    this.quitButton = new JButton("Exit Game");
    this.boardButtons = new JButton[6][7];
    upPanel.add(this.gameStateLabel);
    downPanel.add(this.restartButton);
    downPanel.add(this.quitButton);
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        this.boardButtons[i][j] = new JButton();
        this.boardButtons[i][j].setOpaque(true);
        this.boardButtons[i][j].setBackground(Color.WHITE);
        this.boardButtons[i][j].setPreferredSize(new Dimension(50, 50));
        this.boardButtons[i][j].setBorder(new LineBorder(Color.BLACK));
        midPanel.add(this.boardButtons[i][j]);
      } 
    } 
    pack();
    setDefaultCloseOperation(3);
    setVisible(true);
  }
  
  public void addController(ConnectFourController controller) throws IllegalArgumentException {
    if (controller == null)
      throw new IllegalArgumentException("Controller cannot be null"); 
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        int col = j;
        this.boardButtons[i][j].addActionListener(e -> controller.dropPiece(col));
      } 
    } 
    this.restartButton.addActionListener(e -> controller.startGame());
    this.quitButton.addActionListener(e -> controller.quitGame());
  }
  
  public void setBoardButtons(Player[][] board) throws IllegalArgumentException {
    if (board == null)
      throw new IllegalArgumentException("Board cannot be null"); 
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        if (board[i][j] == Player.RED) {
          this.boardButtons[i][j].setBackground(Color.RED);
        } else if (board[i][j] == Player.YELLOW) {
          this.boardButtons[i][j].setBackground(Color.YELLOW);
        } else {
          this.boardButtons[i][j].setBackground(Color.WHITE);
        } 
      } 
    } 
  }
  
  public void setGameState(String gameState) throws IllegalArgumentException {
    if (gameState == null)
      throw new IllegalArgumentException("Game state cannot be null"); 
    this.gameStateLabel.setText(gameState);
  }
}
