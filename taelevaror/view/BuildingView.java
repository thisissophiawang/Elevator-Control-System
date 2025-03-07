/*     */ package view;
/*     */ 
/*     */ import controller.BuildingControllerInterface;
/*     */ import elevator.ElevatorReport;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.CardLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextField;
/*     */ import scanerzus.Request;
/*     */ 
/*     */ public class BuildingView
/*     */   extends JFrame
/*     */   implements BuildingViewInterface {
/*     */   private BuildingControllerInterface controller;
/*  29 */   private final CardLayout cardLayout = new CardLayout();
/*  30 */   private final JPanel welcomePage = new JPanel();
/*  31 */   private final JPanel buildingPage = new JPanel();
/*     */   
/*     */   private JComboBox<Integer> chooseFloorNumber;
/*     */   
/*     */   private JComboBox<Integer> chooseElevatorNumber;
/*     */   
/*     */   private JComboBox<Integer> chooseCapacityNumber;
/*     */   private JButton createBuildingButton;
/*     */   private JLabel elevatorStateLabel;
/*     */   private JButton switchElevatorSystemButton;
/*     */   private JLabel[][] elevatorLabels;
/*     */   private JComboBox<Integer> chooseStartFloor;
/*     */   private JComboBox<Integer> chooseEndFloor;
/*     */   private JButton addRequestButton;
/*     */   private JLabel upRequestsLabel;
/*     */   private JLabel downRequestsLabel;
/*     */   private JLabel[] elevatorRequestsLabels;
/*     */   private JTextField addStepNumber;
/*     */   private JButton stepButton;
/*     */   private ActionListener addRequestButtonListener;
/*     */   private ActionListener stepButtonListener;
/*     */   
/*     */   public BuildingView() {
/*  54 */     super("Building Elevator Simulation");
/*     */     
/*  56 */     setLocation(200, 200);
/*  57 */     setDefaultCloseOperation(3);
/*     */     
/*  59 */     setLayout(this.cardLayout);
/*     */     
/*  61 */     createWelcomePage();
/*  62 */     add(this.welcomePage, "Welcome Page");
/*     */     
/*  64 */     pack();
/*  65 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createWelcomePage() {
/*  70 */     this.welcomePage.setLayout(new BoxLayout(this.welcomePage, 1));
/*     */     
/*  72 */     JPanel welcomePanel = new JPanel();
/*  73 */     welcomePanel.setLayout(new FlowLayout());
/*  74 */     JLabel welcomeLabel = new JLabel("Welcome to the Building Elevator Simulation!");
/*  75 */     welcomePanel.add(welcomeLabel);
/*  76 */     this.welcomePage.add(welcomePanel);
/*     */     
/*  78 */     JPanel creationBuildingPanel = new JPanel();
/*  79 */     creationBuildingPanel.setLayout(new FlowLayout());
/*     */     
/*  81 */     JLabel floorNumber = new JLabel();
/*  82 */     floorNumber.setText("Floor Number");
/*  83 */     floorNumber.setVisible(true);
/*  84 */     creationBuildingPanel.add(floorNumber);
/*  85 */     this.chooseFloorNumber = new JComboBox<>();
/*  86 */     for (int i = 3; i <= 9; i++) {
/*  87 */       this.chooseFloorNumber.addItem(Integer.valueOf(i));
/*     */     }
/*  89 */     creationBuildingPanel.add(this.chooseFloorNumber);
/*     */     
/*  91 */     JLabel elevatorNumber = new JLabel();
/*  92 */     elevatorNumber.setText("Elevator Number");
/*  93 */     elevatorNumber.setVisible(true);
/*  94 */     creationBuildingPanel.add(elevatorNumber);
/*  95 */     this.chooseElevatorNumber = new JComboBox<>();
/*  96 */     for (int j = 1; j <= 9; j++) {
/*  97 */       this.chooseElevatorNumber.addItem(Integer.valueOf(j));
/*     */     }
/*  99 */     creationBuildingPanel.add(this.chooseElevatorNumber);
/*     */     
/* 101 */     JLabel capacityNumber = new JLabel();
/* 102 */     capacityNumber.setText("Capacity Number");
/* 103 */     capacityNumber.setVisible(true);
/* 104 */     creationBuildingPanel.add(capacityNumber);
/* 105 */     this.chooseCapacityNumber = new JComboBox<>();
/* 106 */     for (int k = 3; k <= 20; k++) {
/* 107 */       this.chooseCapacityNumber.addItem(Integer.valueOf(k));
/*     */     }
/* 109 */     creationBuildingPanel.add(this.chooseCapacityNumber);
/*     */     
/* 111 */     this.welcomePage.add(creationBuildingPanel);
/*     */     
/* 113 */     JPanel createBuildingPanel = new JPanel();
/* 114 */     createBuildingPanel.setLayout(new FlowLayout());
/* 115 */     this.createBuildingButton = new JButton("Create Building");
/* 116 */     createBuildingPanel.add(this.createBuildingButton);
/* 117 */     this.welcomePage.add(createBuildingPanel);
/*     */     
/* 119 */     this.welcomePage.setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addController(BuildingControllerInterface controller) {
/* 124 */     this.controller = controller;
/*     */     
/* 126 */     AtomicReference<Integer> selectedFloorNumber = new AtomicReference<>((Integer)this.chooseFloorNumber.getSelectedItem());
/*     */     
/* 128 */     AtomicReference<Integer> selectedElevatorNumber = new AtomicReference<>((Integer)this.chooseElevatorNumber.getSelectedItem());
/*     */     
/* 130 */     AtomicReference<Integer> selectedCapacityNumber = new AtomicReference<>((Integer)this.chooseCapacityNumber.getSelectedItem());
/* 131 */     this.chooseFloorNumber.addActionListener(e -> selectedFloorNumber.set((Integer)this.chooseFloorNumber.getSelectedItem()));
/*     */     
/* 133 */     this.chooseElevatorNumber.addActionListener(e -> selectedElevatorNumber.set((Integer)this.chooseElevatorNumber.getSelectedItem()));
/*     */     
/* 135 */     this.chooseCapacityNumber.addActionListener(e -> selectedCapacityNumber.set((Integer)this.chooseCapacityNumber.getSelectedItem()));
/*     */     
/* 137 */     this.createBuildingButton.addActionListener(e -> controller.createElevatorSystem(selectedFloorNumber.get(), selectedElevatorNumber.get(), selectedCapacityNumber.get()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createBuildingView(Integer floorNumber, Integer elevatorNumber, Integer capacityNumber, ElevatorReport[] elevatorReports) {
/* 146 */     this.elevatorLabels = new JLabel[floorNumber.intValue()][elevatorNumber.intValue()];
/* 147 */     this.buildingPage.setLayout(new BorderLayout());
/*     */     
/* 149 */     JPanel upperPanel = new JPanel(new FlowLayout());
/* 150 */     this.elevatorStateLabel = new JLabel("System Status: Out of Service; ");
/* 151 */     upperPanel.add(this.elevatorStateLabel);
/*     */     
/* 153 */     JLabel infoLabel = new JLabel("Floor Number: " + floorNumber + ", Elevator Number: " + elevatorNumber + ", Capacity Number: " + capacityNumber);
/*     */ 
/*     */     
/* 156 */     upperPanel.add(infoLabel);
/*     */     
/* 158 */     this.switchElevatorSystemButton = new JButton("Start Elevator System");
/* 159 */     upperPanel.add(this.switchElevatorSystemButton);
/*     */     
/* 161 */     JPanel midPanel = new JPanel(new BorderLayout());
/* 162 */     JPanel midUp = getUpJpanel(elevatorNumber, elevatorReports);
/* 163 */     JPanel midDown = getjPanel(floorNumber, elevatorNumber);
/* 164 */     midPanel.add(midUp, "North");
/* 165 */     midPanel.add(midDown, "Center");
/* 166 */     JPanel lowerPanel = new JPanel(new FlowLayout());
/* 167 */     JLabel startFloorLabel = new JLabel("Start Floor");
/* 168 */     JLabel endFloorLabel = new JLabel("End Floor");
/* 169 */     JLabel enterStepNumber = new JLabel("Enter Step Number:");
/* 170 */     this.chooseStartFloor = new JComboBox<>();
/* 171 */     this.chooseEndFloor = new JComboBox<>();
/* 172 */     this.addRequestButton = new JButton("Add Request");
/* 173 */     this.addRequestButton.setVisible(true);
/* 174 */     this.addStepNumber = new JTextField();
/* 175 */     this.addStepNumber.setPreferredSize(new Dimension(40, 20));
/*     */     
/* 177 */     this.stepButton = new JButton("Step");
/* 178 */     this.stepButton.setVisible(true);
/* 179 */     JButton returnButton = new JButton("Return to Welcome Page");
/* 180 */     returnButton.setVisible(true);
/* 181 */     lowerPanel.add(startFloorLabel);
/* 182 */     lowerPanel.add(this.chooseStartFloor);
/* 183 */     lowerPanel.add(endFloorLabel);
/* 184 */     lowerPanel.add(this.chooseEndFloor);
/* 185 */     lowerPanel.add(this.addRequestButton);
/* 186 */     lowerPanel.add(enterStepNumber);
/* 187 */     lowerPanel.add(this.addStepNumber);
/* 188 */     lowerPanel.add(this.stepButton);
/* 189 */     lowerPanel.add(returnButton);
/*     */     
/* 191 */     this.buildingPage.add(upperPanel, "North");
/* 192 */     this.buildingPage.add(midPanel, "Center");
/* 193 */     this.buildingPage.add(lowerPanel, "South");
/*     */     
/* 195 */     add(this.buildingPage, "Building Page");
/* 196 */     this.cardLayout.show(getContentPane(), "Building Page");
/* 197 */     pack();
/*     */     
/* 199 */     returnButton.addActionListener(e -> {
/*     */           this.buildingPage.removeAll();
/*     */           
/*     */           this.cardLayout.show(getContentPane(), "Welcome Page");
/*     */           pack();
/*     */         });
/* 205 */     this.switchElevatorSystemButton.addActionListener(e -> this.controller.switchElevatorSystem());
/*     */   }
/*     */ 
/*     */   
/*     */   public void switchElevatorSystemView(String status) {
/* 210 */     if ("running".equals(status)) {
/* 211 */       this.elevatorStateLabel.setText("System Status: Running; ");
/* 212 */       this.switchElevatorSystemButton.setText("Stop Elevator System");
/*     */     } 
/* 214 */     if ("stopping".equals(status)) {
/* 215 */       this.elevatorStateLabel.setText("System Status: Stopping; ");
/* 216 */       this.switchElevatorSystemButton.setText("Not Available");
/* 217 */       this.switchElevatorSystemButton.setEnabled(false);
/*     */     } 
/* 219 */     if ("outOfService".equals(status)) {
/* 220 */       this.elevatorStateLabel.setText("System Status: Out of Service; ");
/* 221 */       this.switchElevatorSystemButton.setText("Start Elevator System");
/* 222 */       this.switchElevatorSystemButton.setEnabled(true);
/*     */     } 
/* 224 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateElevators(ElevatorReport[] elevatorReports) {
/* 229 */     for (int i = 0; i < elevatorReports.length; i++) {
/* 230 */       ElevatorReport elevatorReport = elevatorReports[i];
/* 231 */       this.elevatorRequestsLabels[i].setText("Elevator " + i + 1 + ": " + elevatorReport.toString());
/* 232 */       for (int j = 0; j < (elevatorReport.getFloorRequests()).length; j++) {
/* 233 */         if (elevatorReport.getCurrentFloor() == j) {
/* 234 */           this.elevatorLabels[j][i].setText("E" + i + 1);
/* 235 */           this.elevatorLabels[j][i].setOpaque(true);
/* 236 */           this.elevatorLabels[j][i].setBackground(new Color(135, 206, 235));
/* 237 */           this.elevatorLabels[j][i].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
/*     */         } else {
/* 239 */           this.elevatorLabels[j][i].setText("");
/* 240 */           this.elevatorLabels[j][i].setOpaque(false);
/* 241 */           this.elevatorLabels[j][i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
/*     */         } 
/*     */       } 
/*     */     } 
/* 245 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   public void activateRequests(int numFloors) {
/* 250 */     for (int i = 0; i < numFloors; i++) {
/* 251 */       this.chooseStartFloor.addItem(Integer.valueOf(i));
/* 252 */       this.chooseEndFloor.addItem(Integer.valueOf(i));
/*     */     } 
/*     */     
/* 255 */     AtomicReference<Integer> selectedStartFloor = new AtomicReference<>((Integer)this.chooseStartFloor.getSelectedItem());
/*     */     
/* 257 */     AtomicReference<Integer> selectedEndFloor = new AtomicReference<>((Integer)this.chooseEndFloor.getSelectedItem());
/* 258 */     this.chooseStartFloor.addActionListener(e -> selectedStartFloor.set((Integer)this.chooseStartFloor.getSelectedItem()));
/*     */     
/* 260 */     this.chooseEndFloor.addActionListener(e -> selectedEndFloor.set((Integer)this.chooseEndFloor.getSelectedItem()));
/*     */     
/* 262 */     this.addRequestButtonListener = (e -> this.controller.addRequest(selectedStartFloor.get(), selectedEndFloor.get()));
/*     */     
/* 264 */     this.addRequestButton.addActionListener(this.addRequestButtonListener);
/* 265 */     this.stepButtonListener = (e -> {
/*     */         String input = this.addStepNumber.getText();
/*     */         if (input != null && !input.isEmpty()) {
/*     */           for (int i = 0; i < Integer.parseInt(input); i++) {
/*     */             this.controller.step();
/*     */             this.addStepNumber.setText("");
/*     */           } 
/*     */         } else {
/*     */           this.controller.step();
/*     */         } 
/*     */       });
/* 276 */     this.stepButton.addActionListener(this.stepButtonListener);
/* 277 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRequestView(List<Request> upRequests, List<Request> downRequests) {
/* 282 */     this.upRequestsLabel.setText("Up Requests[" + upRequests.size() + "]: " + upRequests);
/* 283 */     this.downRequestsLabel.setText("Down Requests[" + downRequests.size() + "]: " + downRequests);
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableRequests() {
/* 288 */     this.chooseStartFloor.removeAllItems();
/* 289 */     this.chooseEndFloor.removeAllItems();
/* 290 */     this.addRequestButton.removeActionListener(this.addRequestButtonListener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void disablestep() {
/* 295 */     this.stepButton.removeActionListener(this.stepButtonListener);
/*     */   }
/*     */   
/*     */   private JPanel getjPanel(Integer floorNumber, Integer elevatorNumber) {
/* 299 */     JPanel midPanel = new JPanel(new GridLayout(floorNumber.intValue() + 1, 10, 5, 5));
/*     */     
/* 301 */     midPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
/* 302 */     for (int i = 0; i < floorNumber.intValue(); i++) {
/* 303 */       JLabel floorLabel = new JLabel("F" + floorNumber.intValue() - i - 1);
/* 304 */       floorLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
/* 305 */       floorLabel.setHorizontalAlignment(0);
/* 306 */       midPanel.add(floorLabel); int k;
/* 307 */       for (k = 0; k < elevatorNumber.intValue(); k++) {
/* 308 */         this.elevatorLabels[floorNumber.intValue() - i - 1][k] = new JLabel("");
/* 309 */         this.elevatorLabels[floorNumber.intValue() - i - 1][k].setBorder(
/* 310 */             BorderFactory.createLineBorder(Color.GRAY, 1));
/* 311 */         this.elevatorLabels[floorNumber.intValue() - i - 1][k].setHorizontalAlignment(0);
/* 312 */         midPanel.add(this.elevatorLabels[floorNumber.intValue() - i - 1][k]);
/* 313 */         if (i == floorNumber.intValue() - 1) {
/* 314 */           this.elevatorLabels[floorNumber.intValue() - i - 1][k].setText("E" + k + 1);
/* 315 */           this.elevatorLabels[floorNumber.intValue() - i - 1][k].setOpaque(true);
/* 316 */           this.elevatorLabels[floorNumber.intValue() - i - 1][k].setBackground(new Color(135, 206, 235));
/* 317 */           this.elevatorLabels[floorNumber.intValue() - i - 1][k].setBorder(
/* 318 */               BorderFactory.createLineBorder(Color.RED, 2));
/*     */         } 
/*     */       } 
/* 321 */       for (k = elevatorNumber.intValue(); k < 9; k++) {
/* 322 */         JLabel jLabel = new JLabel("");
/* 323 */         jLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
/* 324 */         midPanel.add(jLabel);
/*     */       } 
/*     */     } 
/* 327 */     JLabel emptyLabel = new JLabel("");
/* 328 */     emptyLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
/* 329 */     midPanel.add(emptyLabel); int j;
/* 330 */     for (j = 0; j < elevatorNumber.intValue(); j++) {
/* 331 */       JLabel elevatorLabel = new JLabel("Elevator " + j + 1);
/* 332 */       elevatorLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
/* 333 */       elevatorLabel.setHorizontalAlignment(0);
/* 334 */       midPanel.add(elevatorLabel);
/*     */     } 
/* 336 */     for (j = elevatorNumber.intValue(); j < 9; j++) {
/* 337 */       JLabel emptyLabel1 = new JLabel("");
    emptyLabel1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    midPanel.add(emptyLabel1);
   } 
   return midPanel;
  }
  
   private JPanel getUpJpanel(Integer elevatorNumber, ElevatorReport[] elevatorReports) {
   JPanel upPanel = new JPanel(new GridLayout(elevatorNumber.intValue() + 2, 1, 5, 5));
    
     upPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
     this.upRequestsLabel = new JLabel("Up Requests[0]:");
     this.downRequestsLabel = new JLabel("Down Requests[0]:");
     upPanel.add(this.upRequestsLabel);
     upPanel.add(this.downRequestsLabel);
     this.elevatorRequestsLabels = new JLabel[elevatorNumber.intValue()];
     for (int i = 0; i < elevatorNumber.intValue(); i++) {
     this.elevatorRequestsLabels[i] = new JLabel("Elevator " + i + 1 + ": " + elevatorReports[i]
    .toString());
  upPanel.add(this.elevatorRequestsLabels[i]);
  } 
  return upPanel;
  }
 }


