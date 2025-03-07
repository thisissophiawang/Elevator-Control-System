package main;

import building.Building;
import scanerzus.Request;

public class MainConsole {
    public static void main(String[] args) {
        int numFloors = 6;
        int numElevators = 1;
        int numPeople = 3;

        String[] introText = {
            "Welcome to the Elevator System!",
            "This system will simulate the operation of an elevator system.",
            "The system will be initialized with the following parameters:",
            "Number of floors: 6",
            "Number of elevators: 1",
            "Number of people: 3",
            "The system will then be run and the results will be displayed."
        };

        printDivider();
        for (String line : introText) {
            System.out.println(line);
        }
        printDivider();

        Building building = new Building(6, 1, 3);
        printDivider();
        System.out.println("(1) Building created");
        building.getElevatorSystemStatus().generateLog();
        printDivider();

        building.startElevatorSystem();
        System.out.println("(2) Start the elevator system");
        building.getElevatorSystemStatus().generateLog();
        printDivider();

        building.step();
        System.out.println("(3) Step the elevator system 1 time");
        building.getElevatorSystemStatus().generateLog();
        printDivider();

        building.addRequest(new Request(0, 1));
        building.addRequest(new Request(4, 2));
        building.addRequest(new Request(3, 1));
        System.out.println("(4) 3 Requests added: 0->1, 4->2, 3->1");
        building.getElevatorSystemStatus().generateLog();
        printDivider();

        System.out.println("(5) Step the building 20 times"); 
        for (int i = 0; i < 20; i++) {
            building.step();
            System.out.println(" ");
            System.out.println("Elevator system stepped " + (i + 1) + " times");
            building.getElevatorSystemStatus().generateLog();
        }
        printDivider();

        building.stopElevatorSystem();
        System.out.println("(6) Stop the elevator system");
        building.getElevatorSystemStatus().generateLog();
        printDivider();

        System.out.println("(7) Step the building 5 times");
        for (int i = 0; i < 5; i++) {
            building.step();
            System.out.println(" ");
            System.out.println("Elevator system stepped " + (i + 1) + " times");
            building.getElevatorSystemStatus().generateLog();
        }
        System.out.println(" ");
        System.out.println("Now the system is out of service.");
        printDivider();

        building.startElevatorSystem();
        System.out.println("(8) Start the elevator system again");
        building.getElevatorSystemStatus().generateLog();
        printDivider();

        building.addRequest(new Request(2, 3));
        building.addRequest(new Request(4, 1));
        System.out.println("(9) 2 requests added 2->3, 4->1");
        building.getElevatorSystemStatus().generateLog();
        printDivider();

        System.out.println("(10) Step the building 5 times");
        for (int i = 0; i < 5; i++) {
            building.step();
            System.out.println(" ");
            System.out.println("Elevator system stepped " + (i + 1) + " times");
            building.getElevatorSystemStatus().generateLog();
        }
        printDivider();

        building.stopElevatorSystem();
        System.out.println("(11) Stop the elevator system again");
        building.getElevatorSystemStatus().generateLog();
        printDivider();

        System.out.println("(12) Step the building 4 times");
        for (int i = 0; i < 4; i++) {
            building.step();
            System.out.println(" ");
            System.out.println("Elevator system stepped " + (i + 1) + " times");
            building.getElevatorSystemStatus().generateLog();
        }
        System.out.println(" ");
        System.out.println("Now the system is out of service.");
        printDivider();
    }

    public static void printDivider() {
        System.out.println("-------------------------------------------------------------------------");
    }
}
