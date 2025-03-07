package building;

import building.enums.ElevatorSystemStatus;
import elevator.ElevatorReport;
import java.util.Arrays;
import java.util.List;
import scanerzus.Request;

public class BuildingReport {
    int numFloors;
    int numElevators;
    int elevatorCapacity;
    ElevatorReport[] elevatorReports;
    List<Request> upRequests;
    List<Request> downRequests;
    ElevatorSystemStatus systemStatus;

    public BuildingReport(int numFloors, int numElevators, int elevatorCapacity, ElevatorReport[] elevatorReports, List<Request> upRequests, List<Request> downRequests, ElevatorSystemStatus systemStatus) {
        this.numFloors = numFloors;
        this.numElevators = numElevators;
        this.elevatorCapacity = elevatorCapacity;
        this.elevatorReports = elevatorReports;
        this.upRequests = upRequests;
        this.downRequests = downRequests;
        this.systemStatus = systemStatus;
    }

    public int getNumFloors() {
        return this.numFloors;
    }

    public int getNumElevators() {
        return this.numElevators;
    }

    public int getElevatorCapacity() {
        return this.elevatorCapacity;
    }

    public ElevatorReport[] getElevatorReports() {
        return this.elevatorReports;
    }

    public List<Request> getUpRequests() {
        return this.upRequests;
    }

    public List<Request> getDownRequests() {
        return this.downRequests;
    }

    public ElevatorSystemStatus getSystemStatus() {
        return this.systemStatus;
    }

    public void generateLog() {
        String str = "Elevator reports: " + Arrays.toString(this.elevatorReports) + " Up requests: " + this.upRequests + " Down requests: " + this.downRequests + " System status: " + this.systemStatus;
        System.out.println(str);
    }
}

