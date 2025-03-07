
package view;

import controller.BuildingControllerInterface;
import elevator.ElevatorReport;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import scanerzus.Request;

public class BuildingSimulationView extends JFrame implements BuildingSimulationViewInterface {
  private BuildingControllerInterface controller;
  private CardLayout cardLayout = new CardLayout();
  private JPanel welcomePage = new JPanel();
  private JPanel buildingPage = new JPanel();

  private JComboBox<Integer> chooseFloorNumber;
  private JComboBox<Integer> chooseElevatorNumber;
  private JComboBox<Integer> chooseCapacityNumber;
  private JButton createBuildingButton;
  private JLabel elevatorStateLabel;
  private JButton switchElevatorSystemButton;
  private JLabel[][] elevatorLabels;
  private JComboBox<Integer> chooseStartFloor;
  private JComboBox<Integer> chooseEndFloor;
  private JButton addRequestButton;
  private JLabel upRequestsLabel;
  private JLabel downRequestsLabel;
  private JLabel[] elevatorRequestsLabels;
  private JTextField addStepNumber;
  private JButton stepButton;
  private ActionListener addRequestButtonListener;
  private ActionListener stepButtonListener;


  public BuildingSimulationView() {
    super("The Simulation of Building Elevator ");
    setLocation(400, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(cardLayout);
    createWelcomePage();
    add(welcomePage, "Welcome Page");
    pack();
    setVisible(true);
  }

  private void createWelcomePage() {
    welcomePage.setLayout(new BoxLayout(welcomePage, BoxLayout.Y_AXIS));

    // Welcome panel using Box layout to arrange labels vertically and center-align them
    JPanel welcomePanel = new JPanel();
    welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
    welcomePanel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center-align the panel itself

    // Adding vertical glue before the first component to center everything vertically
    welcomePanel.add(Box.createVerticalGlue());

    // Panel to contain the welcome label, centered horizontally
    JPanel welcomeLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    // MAKE the welcome label more bigger text
    JLabel welcomeLabel = new JLabel("<html><div style='text-align: center; color: blue; font-size: 20px;'><br><br>Welcome to the Building Elevator Simulation!</div></html>");
    welcomeLabelPanel.add(welcomeLabel);
    welcomePanel.add(welcomeLabelPanel);


    // Panel to contain the description label, also centered
    JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel descriptionLabel = new JLabel("<html><div style='text-align: center;'><br><br>This system simulates an elevator's operation in a building, " +
        "where you can create a building with number of floors, elevators, and capacity.<br><br>" +
        "You can also add requests to the system and observe the elevator's operation.</div></html>");
    descriptionPanel.add(descriptionLabel);
    welcomePanel.add(descriptionPanel);


    // Adding a horizontal separator
    welcomePanel.add(new JSeparator(SwingConstants.HORIZONTAL));

    // Panel for "Please enter information" label, centered
    JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel buildingInfoLabel = new JLabel("Please enter the information of building, elevator, and capacity first.");
    infoPanel.add(buildingInfoLabel);
    welcomePanel.add(infoPanel);

    // Adding vertical glue after the last component to center everything vertically
    welcomePanel.add(Box.createVerticalGlue());

    // Add the welcome panel to the welcome page
    welcomePage.add(welcomePanel);

    // Panel for building configuration options using FlowLayout for horizontal center alignment
    JPanel creationBuildingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JLabel floorNumberLabel = new JLabel("Total Floor Number:");
    creationBuildingPanel.add(floorNumberLabel);
    chooseFloorNumber = new JComboBox<>();
    for (int i = 3; i <= 10; i++) {
      chooseFloorNumber.addItem(i);
    }
    creationBuildingPanel.add(chooseFloorNumber);

    JLabel elevatorNumberLabel = new JLabel("Total Elevator Number:");
    creationBuildingPanel.add(elevatorNumberLabel);
    chooseElevatorNumber = new JComboBox<>();
    for (int j = 1; j <= 10; j++) {
      chooseElevatorNumber.addItem(j);
    }
    creationBuildingPanel.add(chooseElevatorNumber);

    JLabel capacityNumberLabel = new JLabel("Capacity Number of Elevator:");
    creationBuildingPanel.add(capacityNumberLabel);
    chooseCapacityNumber = new JComboBox<>();
    for (int k = 3; k <= 20; k++) {
      chooseCapacityNumber.addItem(k);
    }
    creationBuildingPanel.add(chooseCapacityNumber);

    // Add the building configuration panel to the welcome page
    welcomePage.add(creationBuildingPanel);

    // Panel for the create/rebuild building button using FlowLayout for horizontal center alignment
    JPanel createBuildingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    createBuildingButton = new JButton("Create/Rebuild the Building");
    createBuildingPanel.add(createBuildingButton);
    welcomePage.add(createBuildingPanel);

    // Make the welcome page visible
    welcomePage.setVisible(true);
  }





  @Override
  public void addController(BuildingControllerInterface paramBuildingControllerInterface) {
    this.controller = paramBuildingControllerInterface;
    AtomicReference<Integer> selectedFloorNumber = new AtomicReference<>((Integer) this.chooseFloorNumber.getSelectedItem());
    AtomicReference<Integer> selectedElevatorNumber = new AtomicReference<>((Integer) this.chooseElevatorNumber.getSelectedItem());
    AtomicReference<Integer> selectedCapacityNumber = new AtomicReference<>((Integer) this.chooseCapacityNumber.getSelectedItem());
    this.chooseFloorNumber.addActionListener(e -> selectedFloorNumber.set((Integer) this.chooseFloorNumber.getSelectedItem()));
    this.chooseElevatorNumber.addActionListener(e -> selectedElevatorNumber.set((Integer) this.chooseElevatorNumber.getSelectedItem()));
    this.chooseCapacityNumber.addActionListener(e -> selectedCapacityNumber.set((Integer) this.chooseCapacityNumber.getSelectedItem()));
    this.createBuildingButton.addActionListener(e -> this.controller.createElevatorSystem(selectedFloorNumber.get(), selectedElevatorNumber.get(), selectedCapacityNumber.get()));
  }


  @Override
  public void createBuildingView(Integer floorNumber, Integer elevatorNumber, Integer capacityNumber, ElevatorReport[] elevatorReports) {
    this.elevatorLabels = new JLabel[floorNumber][elevatorNumber];
    this.buildingPage.setLayout(new BorderLayout());

    JPanel upperPanel = new JPanel(new FlowLayout());
    this.elevatorStateLabel = new JLabel("System Status: Out of Service; ");
    upperPanel.add(this.elevatorStateLabel);

    JLabel infoLabel = new JLabel("Floor Number: " + floorNumber + ", Elevator Number: " + elevatorNumber + ", Capacity Number: " + capacityNumber);
    upperPanel.add(infoLabel);

    this.switchElevatorSystemButton = new JButton("Start Elevator System");
    upperPanel.add(this.switchElevatorSystemButton);

    JPanel midPanel = new JPanel(new BorderLayout());
    JPanel midUp = getUpJpanel(elevatorNumber, elevatorReports);
    JPanel midDown = getjPanel(floorNumber, elevatorNumber);
    midPanel.add(midUp, BorderLayout.NORTH);
    midPanel.add(midDown, BorderLayout.CENTER);

    JPanel lowerPanel = new JPanel(new FlowLayout());
    JLabel startFloorLabel = new JLabel("Number of Start Floor");
    JLabel endFloorLabel = new JLabel("Number of End Floor");
    JLabel enterStepNumber = new JLabel("Enter Step Number:");
    this.chooseStartFloor = new JComboBox<>();
    this.chooseEndFloor = new JComboBox<>();
    this.addRequestButton = new JButton("Add Request");
    this.addRequestButton.setVisible(true);
    this.addStepNumber = new JTextField();
    this.addStepNumber.setPreferredSize(new Dimension(40, 20));

    this.stepButton = new JButton("Step Function ");
    this.stepButton.setVisible(true);
    JButton returnButton = new JButton("Back to Main Page");
    returnButton.setVisible(true);
    lowerPanel.add(startFloorLabel);
    lowerPanel.add(this.chooseStartFloor);
    lowerPanel.add(endFloorLabel);
    lowerPanel.add(this.chooseEndFloor);
    lowerPanel.add(this.addRequestButton);
    lowerPanel.add(enterStepNumber);
    lowerPanel.add(this.addStepNumber);
    lowerPanel.add(this.stepButton);
    lowerPanel.add(returnButton);

    this.buildingPage.add(upperPanel, BorderLayout.NORTH);
    this.buildingPage.add(midPanel, BorderLayout.CENTER);
    this.buildingPage.add(lowerPanel, BorderLayout.SOUTH);

    add(this.buildingPage, "Building Page");
    this.cardLayout.show(getContentPane(), "Building Page");
    pack();

    returnButton.addActionListener(e -> {
      this.buildingPage.removeAll();
      this.cardLayout.show(getContentPane(), "Welcome Page");
      pack();
    });
    this.switchElevatorSystemButton.addActionListener(e -> this.controller.switchElevatorSystem());
  }

  private JPanel getUpJpanel(Integer elevatorNumber, ElevatorReport[] elevatorReports) {
    // Main panel setup
    JPanel upPanel = new JPanel(new GridLayout(elevatorNumber + 1, 1, 5, 5));
    upPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

    // Sub-panel for request labels
    JPanel requestPanel = new JPanel(new GridLayout(3, 1));
    requestPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  // Black border around the request labels

    // Title label for the section
    JLabel titleLabel = new JLabel("Take the Request:");
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
    requestPanel.add(titleLabel);

    // Label for up requests
    this.upRequestsLabel = new JLabel("Up Requests[0]:");
    upRequestsLabel.setHorizontalAlignment(SwingConstants.LEFT);
    requestPanel.add(this.upRequestsLabel);

    // Label for down requests
    this.downRequestsLabel = new JLabel("Down Requests[0]:");
    downRequestsLabel.setHorizontalAlignment(SwingConstants.LEFT);
    requestPanel.add(this.downRequestsLabel);

    // Add the request panel to the main panel
    upPanel.add(requestPanel);

    // Labels for elevator reports
    this.elevatorRequestsLabels = new JLabel[elevatorNumber];
    for (int i = 0; i < elevatorNumber; i++) {
      this.elevatorRequestsLabels[i] = new JLabel("Elevator " + (i + 1) + ": " + elevatorReports[i].toString());
      elevatorRequestsLabels[i].setHorizontalAlignment(SwingConstants.LEFT);
      upPanel.add(this.elevatorRequestsLabels[i]);
    }

    return upPanel;

  }


  @Override
  public void switchElevatorSystemView(String status) {
    if ("running".equals(status)) {
      this.elevatorStateLabel.setText("System Status: Running; ");
      this.switchElevatorSystemButton.setText("Stop the System");
    }
    if ("stopping".equals(status)) {
      this.elevatorStateLabel.setText("System Status: Stopping; ");
      this.switchElevatorSystemButton.setText("Not Available");
      this.switchElevatorSystemButton.setEnabled(false);
    }
    if ("outOfService".equals(status)) {
      this.elevatorStateLabel.setText("System Status: Out of Service; ");
      this.switchElevatorSystemButton.setText("Begin the System");
      this.switchElevatorSystemButton.setEnabled(true);
    }
    pack();
  }



  @Override
  public void updateElevators(ElevatorReport[] elevatorReports) {
    for (int i = 0; i < elevatorReports.length; i++) {
      ElevatorReport elevatorReport = elevatorReports[i];
      // Update the status label for each elevator
      this.elevatorRequestsLabels[i].setText("Elevator " + (i + 1) + ": " + elevatorReport.toString());
      // Loop through each floor label and update based on elevator's current floor
      for (int j = 0; j < elevatorReport.getFloorRequests().length; j++) {
        // If the elevator's current floor matches the label's floor
        if (elevatorReport.getCurrentFloor() == j) {
          this.elevatorLabels[j][i].setText("E" + (i + 1));
          this.elevatorLabels[j][i].setOpaque(true);
          this.elevatorLabels[j][i].setBackground(new Color(218, 238, 144)); // Light Green for current floor
          this.elevatorLabels[j][i].setBorder(BorderFactory.createLineBorder(new Color(70, 101, 180), 2)); // Steel Blue border
        } else {
          // Otherwise, clear the label for non-current floors
          this.elevatorLabels[j][i].setText("");
          this.elevatorLabels[j][i].setOpaque(false);
          this.elevatorLabels[j][i].setBorder(BorderFactory.createLineBorder(new Color(211, 211, 211), 1)); // Light Gray border
        }
      }
    }
    pack();
  }

  @Override
  public void activateRequests(int numFloors) {
    for (int i = 0; i < numFloors; i++) {
      this.chooseStartFloor.addItem(i);
      this.chooseEndFloor.addItem(i);
    }

    AtomicReference<Integer> selectedStartFloor = new AtomicReference<>((Integer)this.chooseStartFloor.getSelectedItem());
    AtomicReference<Integer> selectedEndFloor = new AtomicReference<>((Integer)this.chooseEndFloor.getSelectedItem());
    this.chooseStartFloor.addActionListener(e -> selectedStartFloor.set((Integer)this.chooseStartFloor.getSelectedItem()));
    this.chooseEndFloor.addActionListener(e -> selectedEndFloor.set((Integer)this.chooseEndFloor.getSelectedItem()));

    this.addRequestButtonListener = e -> this.controller.addRequest(selectedStartFloor.get(), selectedEndFloor.get());
    this.addRequestButton.addActionListener(this.addRequestButtonListener);
    this.stepButtonListener = e -> {
      String input = this.addStepNumber.getText();
      if (input != null && !input.isEmpty()) {
        for (int i = 0; i < Integer.parseInt(input); i++) {
          this.controller.step();
          this.addStepNumber.setText("");
        }
      } else {
        this.controller.step();
      }
    };
    this.stepButton.addActionListener(this.stepButtonListener);
    pack();
  }

  @Override
  public void addRequestView(List<Request> upRequests, List<Request> downRequests) {
    this.upRequestsLabel.setText("Up Requests Of System[" + upRequests.size() + "]: " + upRequests);
    this.downRequestsLabel.setText("Down Requests Of System[" + downRequests.size() + "]: " + downRequests);
  }

  @Override
  public void disableRequests() {
    this.chooseStartFloor.removeAllItems();
    this.chooseEndFloor.removeAllItems();
    this.addRequestButton.removeActionListener(this.addRequestButtonListener);
  }

  @Override
  public void disablestep() {
    this.stepButton.removeActionListener(this.stepButtonListener);
  }


  private JPanel getjPanel(Integer floorNumber, Integer elevatorNumber) {
    // Main panel for grid layout
    JPanel midPanel = new JPanel(new GridLayout(floorNumber.intValue() + 1, 10, 5, 5));
    midPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

    // Adding components to midPanel as per your earlier code
    for (int i = 0; i < floorNumber.intValue(); i++) {
      JLabel floorLabel = new JLabel("Floor" + (floorNumber.intValue() - i - 1));
      floorLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
      floorLabel.setHorizontalAlignment(SwingConstants.CENTER);
      midPanel.add(floorLabel);

      for (int k = 0; k < elevatorNumber.intValue(); k++) {
        elevatorLabels[floorNumber.intValue() - i - 1][k] = new JLabel("");
        elevatorLabels[floorNumber.intValue() - i - 1][k].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        elevatorLabels[floorNumber.intValue() - i - 1][k].setHorizontalAlignment(SwingConstants.CENTER);
        midPanel.add(elevatorLabels[floorNumber.intValue() - i - 1][k]);

        if (i == floorNumber.intValue() - 1) {
          elevatorLabels[floorNumber.intValue() - i - 1][k].setText("Elevator" + (k + 1));
          elevatorLabels[floorNumber.intValue() - i - 1][k].setOpaque(true);
          elevatorLabels[floorNumber.intValue() - i - 1][k].setBackground(new Color(135, 206, 235));
          elevatorLabels[floorNumber.intValue() - i - 1][k].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
      }
    }

    // Title Label for the simulation
    JLabel titleLabel = new JLabel("Simulation of Elevator System");
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));  // Set font here as per your design needs

    // Outer panel to contain both the title and midPanel
    JPanel outerPanel = new JPanel(new BorderLayout());
    outerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), ""));
    outerPanel.add(titleLabel, BorderLayout.NORTH);
    outerPanel.add(midPanel, BorderLayout.CENTER);

    return outerPanel;
  }


}
