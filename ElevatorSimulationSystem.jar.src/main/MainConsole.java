/*     */ package main;
/*     */ 
/*     */ import building.Building;
/*     */ import building.BuildingReport;
/*     */ import java.util.Scanner;
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
/*     */ 
/*     */ 
/*     */ public class MainConsole
/*     */ {
/*     */   public static void main(String[] args) {
/*  27 */     int numFloors = 11;
/*  28 */     int numElevators = 1;
/*  29 */     int numPeople = 3;
/*     */ 
/*     */     
/*  32 */     String[] introText = { "Welcome to the Elevator System!", "This system will simulate the operation of an elevator system.", "The system will be initialized with the following parameters:", "Number of floors: 11", "Number of elevators: 1", "Number of people: 3", "The system will then be run and the results will be displayed.", "", "Press enter to continue." };
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
/*  44 */     for (String line : introText) {
/*  45 */       System.out.println(line);
/*     */     }
/*     */     
/*  48 */     Scanner scanner = new Scanner(System.in);
/*  49 */     scanner.nextLine();
/*     */ 
/*     */     
/*  52 */     Building building = new Building(11, 1, 3);
/*  53 */     System.out.println("\n - After initialization, system not running yet -  \n - The system is out of service -  \n - All elevators are on the ground floor -  \n - All elevators are out of service -  ");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     BuildingReport buildingReport = building.getElevatorSystemStatus();
/*  59 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/*  62 */     building.startElevatorSystem();
/*  63 */     System.out.println("\n - After entering <start> command into the system -  \n - The system is running -  \n - All elevators are on the ground floor -  \n - All elevators are waiting for taking requests -  ");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     buildingReport = building.getElevatorSystemStatus();
/*  69 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/*  72 */     building.addRequest(new Request(0, 3));
/*  73 */     building.addRequest(new Request(0, 5));
/*  74 */     building.addRequest(new Request(0, 7));
/*     */     
/*  76 */     System.out.println("\n - Adding <Request> (0,3) (0,5) (0,7) To The Building -  \n - The elevator system lists all three up requests in the upRequests list-  ");
/*     */ 
/*     */     
/*  79 */     buildingReport = building.getElevatorSystemStatus();
/*  80 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/*  83 */     building.addRequest(new Request(10, 3));
/*  84 */     System.out.println("\n - Adding <Request> (10,3) To The Building -  \n - The elevator system lists this down request in the downRequests list-  ");
/*     */ 
/*     */     
/*  87 */     buildingReport = building.getElevatorSystemStatus();
/*  88 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/*  91 */     building.stepElevatorSystem();
/*  92 */     System.out.println("\n - After <step> the system for 1 time-  \n - The elevator is only taking the first three up requests -  \n - The elevator is now on the ground floor -  \n - The elevator's door is open for 3 Second -  ");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     buildingReport = building.getElevatorSystemStatus();
/*  98 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */     
/*     */     int i;
/* 101 */     for (i = 0; i < 3; i++) {
/* 102 */       building.stepElevatorSystem();
/*     */     }
/* 104 */     System.out.println("\n - After <step> the system for next 3 times -  \n - The elevator is now on the ground floor -  \n - The elevator's door is close, ready to go up -  ");
/*     */ 
/*     */     
/* 107 */     buildingReport = building.getElevatorSystemStatus();
/* 108 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/* 111 */     for (i = 0; i < 4; i++) {
/* 112 */       building.stepElevatorSystem();
/*     */     }
/*     */     
/* 115 */     System.out.println("\n - After <step> the system for next 4 times -  \n - The elevator is now on the No.3 floor -  \n - The elevator's door is open for 3 seconds -  ");
/*     */ 
/*     */ 
/*     */     
/* 119 */     buildingReport = building.getElevatorSystemStatus();
/* 120 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/* 123 */     building.stopElevatorSystem();
/*     */     
/* 125 */     System.out.println("\n - After entering <stop> command into the system -  \n - The system is turning to stopping status-  \n - The system pending requests are all removed -  \n - All elevators are now set direction to down -  \n - All elevators' processing requests are moved -  \n - The elevator's door will keep open as before -  ");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     buildingReport = building.getElevatorSystemStatus();
/* 133 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/* 136 */     for (i = 0; i < 3; i++) {
/* 137 */       building.stepElevatorSystem();
/*     */     }
/* 139 */     System.out.println("\n - After <step> the system for next 3 times -  \n - The system is still on the stopping status -  \n - The elevator is still on the No.3 floor -  \n - But the elevator's door is close, ready to go down -  ");
/*     */ 
/*     */ 
/*     */     
/* 143 */     buildingReport = building.getElevatorSystemStatus();
/* 144 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/* 147 */     for (i = 0; i < 3; i++) {
/* 148 */       building.stepElevatorSystem();
/*     */     }
/* 150 */     System.out.println("\n - After <step> the system for next 3 times -  \n - All elevators are now on the ground floor -  \n - All elevators are out of service -  \n - The system is still on stopping mode because elevator's door is closed -  ");
/*     */ 
/*     */ 
/*     */     
/* 154 */     buildingReport = building.getElevatorSystemStatus();
/* 155 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/* 158 */     building.stepElevatorSystem();
/* 159 */     System.out.println("\n - After <step> the system for next 1 time -  \n - All elevators are now on the ground floor -  \n - All elevators are out of service -  \n - The system is still on stopping mode as elevator's door is just open -  ");
/*     */ 
/*     */ 
/*     */     
/* 163 */     buildingReport = building.getElevatorSystemStatus();
/* 164 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */ 
/*     */     
/* 167 */     building.stepElevatorSystem();
/* 168 */     System.out.println("\n - After <step> the system for the last time -  \n - The door of the elevator is now open on the ground floor -  \n - So that the system is now turning to out of service status -  ");
/*     */ 
/*     */     
/* 171 */     buildingReport = building.getElevatorSystemStatus();
/* 172 */     System.out.println("\nBuilding Report: " + buildingReport);
/*     */   }
/*     */ }


/* Location:              /Users/sophiawang/Desktop/ElevatorSimulationSystem.jar!/main/MainConsole.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */