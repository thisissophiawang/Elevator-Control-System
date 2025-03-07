/*     */ package building;
/*     */ 
/*     */ import building.enums.ElevatorSystemStatus;
/*     */ import elevator.ElevatorReport;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Container;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.SwingUtilities;
/*     */ import scanerzus.Request;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SwingBuildingView
/*     */   extends JFrame
/*     */   implements BuildingView
/*     */ {
/*     */   private final JTextArea systemInfoArea;
/*     */   private final JTextArea upRequestArea;
/*     */   private final JTextArea downRequestArea;
/*     */   private final JTextArea elevatorStatusArea;
/*     */   private final JTextArea floorElevatorArea;
/*     */   private final JLabel errorArea;
/*     */   private final JTextField startFloorField;
/*     */   private final JTextField endFloorField;
/*     */   private final JTextField numFloorsField;
/*     */   private final JTextField numElevatorsField;
/*     */   private final JTextField elevatorCapacityField;
/*     */   private final JButton rebuildSystemButton;
/*     */   private final JButton startSystemButton;
/*     */   private final JButton stopSystemButton;
/*     */   private final JButton stepSystemButton;
/*     */   private final JButton submitRequestButton;
/*     */   private final JButton exitButton;
/*     */   
/*     */   public SwingBuildingView(String title) {
/*  63 */     super(title);
/*  64 */     setDefaultCloseOperation(3);
/*  65 */     setSize(800, 800);
/*  66 */     setLocationRelativeTo(null);
/*     */     
/*  68 */     Container contentPane = getContentPane();
/*  69 */     contentPane.setLayout(new BoxLayout(contentPane, 1));
/*     */ 
/*     */     
/*  72 */     this.systemInfoArea = new JTextArea(4, 40);
/*  73 */     this.systemInfoArea.setEditable(false);
/*  74 */     addPanelWithLabel("System Information", this.systemInfoArea);
/*     */ 
/*     */     
/*  77 */     JPanel rebuildPanel = new JPanel(new FlowLayout());
/*     */     
/*  79 */     rebuildPanel.add(new JLabel("Number of Floors:"));
/*  80 */     this.numFloorsField = new JTextField(5);
/*  81 */     rebuildPanel.add(this.numFloorsField);
/*     */     
/*  83 */     rebuildPanel.add(new JLabel("Number of Elevators:"));
/*  84 */     this.numElevatorsField = new JTextField(5);
/*  85 */     rebuildPanel.add(this.numElevatorsField);
/*     */     
/*  87 */     rebuildPanel.add(new JLabel("Elevator Capacity:"));
/*  88 */     this.elevatorCapacityField = new JTextField(5);
/*  89 */     rebuildPanel.add(this.elevatorCapacityField);
/*     */     
/*  91 */     this.rebuildSystemButton = new JButton("Rebuild System");
/*  92 */     rebuildPanel.add(this.rebuildSystemButton, "South");
/*     */     
/*  94 */     getContentPane().add(rebuildPanel, "North");
/*     */ 
/*     */     
/*  97 */     JPanel requestPanel = new JPanel(new GridLayout(2, 1));
/*     */     
/*  99 */     this.upRequestArea = new JTextArea(2, 40);
/* 100 */     this.upRequestArea.setEditable(false);
/* 101 */     requestPanel.add(new JScrollPane(this.upRequestArea));
/*     */     
/* 103 */     this.downRequestArea = new JTextArea(2, 40);
/* 104 */     this.downRequestArea.setEditable(false);
/* 105 */     requestPanel.add(new JScrollPane(this.downRequestArea));
/*     */     
/* 107 */     addPanelWithLabel("Requests", requestPanel);
/*     */ 
/*     */     
/* 110 */     this.elevatorStatusArea = new JTextArea(5, 40);
/* 111 */     this.elevatorStatusArea.setEditable(false);
/* 112 */     addPanelWithLabel("Elevator Status", this.elevatorStatusArea);
/*     */ 
/*     */     
/* 115 */     this.floorElevatorArea = new JTextArea(15, 40);
/* 116 */     this.floorElevatorArea.setEditable(false);
/* 117 */     addPanelWithLabel("Elevator Simulation", this.floorElevatorArea);
/*     */ 
/*     */     
/* 120 */     JPanel inputPanel = new JPanel();
/* 121 */     this.startFloorField = new JTextField(5);
/* 122 */     this.endFloorField = new JTextField(5);
/* 123 */     inputPanel.add(new JLabel("Start Floor:"));
/* 124 */     inputPanel.add(this.startFloorField);
/* 125 */     inputPanel.add(new JLabel("End Floor:"));
/* 126 */     inputPanel.add(this.endFloorField);
/* 127 */     addPanel(inputPanel);
/*     */ 
/*     */     
/* 130 */     JPanel buttonPanel = new JPanel();
/*     */     
/* 132 */     this.submitRequestButton = new JButton("Submit Request");
/* 133 */     buttonPanel.add(this.submitRequestButton);
/*     */     
/* 135 */     this.startSystemButton = new JButton("Start");
/* 136 */     buttonPanel.add(this.startSystemButton);
/*     */     
/* 138 */     this.stepSystemButton = new JButton("Step");
/* 139 */     buttonPanel.add(this.stepSystemButton);
/*     */     
/* 141 */     this.stopSystemButton = new JButton("Stop");
/* 142 */     buttonPanel.add(this.stopSystemButton);
/*     */     
/* 144 */     this.exitButton = new JButton("Exit");
/* 145 */     buttonPanel.add(this.exitButton);
/*     */     
/* 147 */     addPanel(buttonPanel);
/*     */ 
/*     */     
/* 150 */     this.errorArea = new JLabel("Building with default floors: 11, elevators: 2, capacity: 3 | As System is out of service, press <Start> to run on the system", 2);
/*     */ 
/*     */ 
/*     */     
/* 154 */     this.errorArea.setHorizontalAlignment(2);
/*     */     
/* 156 */     JPanel errorPanel = new JPanel(new BorderLayout());
/* 157 */     errorPanel.add(this.errorArea, "West");
/*     */     
/* 159 */     getContentPane().add(errorPanel, "South");
/*     */ 
/*     */ 
/*     */     
/* 163 */     setVisible(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addPanelWithLabel(String labelText, JComponent component) {
/* 173 */     JPanel panel = new JPanel();
/* 174 */     panel.setLayout(new BorderLayout());
/* 175 */     panel.add(new JLabel(labelText), "North");
/* 176 */     panel.add(new JScrollPane(component), "Center");
/* 177 */     getContentPane().add(panel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addPanel(JComponent component) {
/* 186 */     JPanel panel = new JPanel();
/* 187 */     panel.add(component);
/* 188 */     getContentPane().add(panel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 197 */     SwingUtilities.invokeLater(() -> new SwingBuildingView("Elevator System"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setButtonEnabled(BuildingReport buildingReport) {
/* 202 */     ElevatorSystemStatus systemStatus = buildingReport.getSystemStatus();
/*     */     
/* 204 */     this.submitRequestButton.setEnabled((systemStatus == ElevatorSystemStatus.running));
/*     */     
/* 206 */     this.stopSystemButton.setEnabled((systemStatus == ElevatorSystemStatus.running));
/*     */     
/* 208 */     this.stepSystemButton.setEnabled((systemStatus != ElevatorSystemStatus.outOfService));
/*     */     
/* 210 */     this.startSystemButton.setEnabled((systemStatus == ElevatorSystemStatus.outOfService));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayBuildingInfo(BuildingReport buildingReport) {
/* 216 */     int numFloors = buildingReport.getNumFloors();
/* 217 */     int numElevators = buildingReport.getNumElevators();
/* 218 */     int elevatorCapacity = buildingReport.getElevatorCapacity();
/* 219 */     ElevatorSystemStatus systemStatus = buildingReport.getSystemStatus();
/*     */     
/* 221 */     String infoBuilder = "numFloors: " + numFloors + "\nnumElevators: " + numElevators + "\nelevatorCapacity: " + elevatorCapacity + "\nsystemStatus: " + systemStatus;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     this.systemInfoArea.setText(infoBuilder);
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayRequestInfo(BuildingReport buildingReport) {
/* 232 */     StringBuilder upRequestBuilder = new StringBuilder();
/* 233 */     int numUpRequests = buildingReport.getUpRequests().size();
/* 234 */     upRequestBuilder.append("Number of upRequests: ").append(numUpRequests).append("\n");
/* 235 */     for (Request request : buildingReport.getUpRequests()) {
/* 236 */       upRequestBuilder.append(request).append(" | ");
/*     */     }
/* 238 */     this.upRequestArea.setText(upRequestBuilder.toString());
/*     */     
/* 240 */     StringBuilder downRequestBuilder = new StringBuilder();
/* 241 */     int numDownRequests = buildingReport.getDownRequests().size();
/* 242 */     downRequestBuilder.append("Number of downRequests: ").append(numDownRequests).append("\n");
/* 243 */     for (Request request : buildingReport.getDownRequests()) {
/* 244 */       downRequestBuilder.append(request).append(" | ");
/*     */     }
/* 246 */     this.downRequestArea.setText(downRequestBuilder.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displayElevatorInfo(BuildingReport buildingReport) {
/* 252 */     StringBuilder elevatorStatusBuilder = new StringBuilder();
/* 253 */     int index = 0;
/* 254 */     for (ElevatorReport elevatorReport : buildingReport.getElevatorReports()) {
/* 255 */       elevatorStatusBuilder.append("Elevator").append(index).append(" ")
/* 256 */         .append("[Door Closed: ").append(elevatorReport.isDoorClosed()).append("]: ")
/* 257 */         .append(elevatorReport)
/* 258 */         .append("\n");
/* 259 */       index++;
/*     */     } 
/* 261 */     this.elevatorStatusArea.setText(elevatorStatusBuilder.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayElevatorSimulation(BuildingReport buildingReport) {
/* 266 */     StringBuilder floorElevatorBuilder = new StringBuilder();
/* 267 */     Map<Integer, List<Integer>> elevatorPerFloor = new HashMap<>();
/* 268 */     int index = 0;
/* 269 */     for (ElevatorReport elevatorReport : buildingReport.getElevatorReports()) {
/* 270 */       int currentFloor = elevatorReport.getCurrentFloor();
/* 271 */       elevatorPerFloor.putIfAbsent(Integer.valueOf(currentFloor), new ArrayList<>());
/* 272 */       ((List<Integer>)elevatorPerFloor.get(Integer.valueOf(currentFloor))).add(Integer.valueOf(index));
/* 273 */       index++;
/*     */     } 
/* 275 */     for (int i = buildingReport.getNumFloors() - 1; i >= 0; i--) {
/* 276 */       floorElevatorBuilder.append("Floor ").append(i).append(": ");
/* 277 */       if (elevatorPerFloor.containsKey(Integer.valueOf(i))) {
/* 278 */         for (Integer elevatorIndex : elevatorPerFloor.get(Integer.valueOf(i))) {
/* 279 */           floorElevatorBuilder.append("Elevator ").append(elevatorIndex).append(", ");
/*     */         }
/*     */         
/* 282 */         floorElevatorBuilder.setLength(floorElevatorBuilder.length() - 2);
/*     */       } 
/* 284 */       floorElevatorBuilder.append("\n");
/*     */     } 
/* 286 */     this.floorElevatorArea.setText(floorElevatorBuilder.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addControl(BuildingControl control) {
/* 292 */     this.startSystemButton.addActionListener(e -> control.startElevatorSystem());
/*     */     
/* 294 */     this.stopSystemButton.addActionListener(e -> control.stopElevatorSystem());
/*     */     
/* 296 */     this.stepSystemButton.addActionListener(e -> control.stepElevatorSystem());
/*     */     
/* 298 */     this.exitButton.addActionListener(e -> control.exitProgram());
/*     */     
/* 300 */     this.submitRequestButton.addActionListener(e -> {
/*     */           try {
/*     */             String startFloorText = this.startFloorField.getText();
/*     */             
/*     */             String endFloorText = this.endFloorField.getText();
/*     */             
/*     */             if (startFloorText.isEmpty() || endFloorText.isEmpty()) {
/*     */               throw new IllegalArgumentException("Request cannot be empty to be submitted");
/*     */             }
/*     */             
/*     */             int startFloor = Integer.parseInt(startFloorText);
/*     */             int endFloor = Integer.parseInt(endFloorText);
/*     */             control.addNewRequest(new Request(startFloor, endFloor));
/* 313 */           } catch (NumberFormatException ex) {
/*     */             
/*     */             displaySystemStatus("Invalid input: start and end floor must be integersto be submitted");
/*     */           }
/* 317 */           catch (IllegalArgumentException ex) {
/*     */             displaySystemStatus(ex.getMessage());
/*     */           } 
/*     */         });
/*     */     
/* 322 */     this.rebuildSystemButton.addActionListener(e -> {
/*     */           try {
/*     */             String numFloorsText = this.numFloorsField.getText();
/*     */             
/*     */             String numElevatorsText = this.numElevatorsField.getText();
/*     */             
/*     */             String elevatorCapacityText = this.elevatorCapacityField.getText();
/*     */             
/*     */             if (numFloorsText.isEmpty() || numElevatorsText.isEmpty() || elevatorCapacityText.isEmpty()) {
/*     */               throw new IllegalArgumentException("Fields cannot be empty");
/*     */             }
/*     */             
/*     */             int numFloors = Integer.parseInt(numFloorsText);
/*     */             int numElevators = Integer.parseInt(numElevatorsText);
/*     */             int elevatorCapacity = Integer.parseInt(elevatorCapacityText);
/*     */             control.rebuildElevatorSystem(numFloors, numElevators, elevatorCapacity);
/* 338 */           } catch (NumberFormatException ex) {
/*     */             displaySystemStatus("Invalid input: all fields must be integers");
/* 340 */           } catch (IllegalArgumentException ex) {
/*     */             displaySystemStatus(ex.getMessage());
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void displaySystemStatus(String systemMessage) {
/* 349 */     this.errorArea.setText(systemMessage);
/*     */   }
/*     */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/building/SwingBuildingView.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */