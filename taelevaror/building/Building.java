package building;

import building.enums.ElevatorSystemStatus;
import elevator.Elevator;
import elevator.ElevatorInterface;
import elevator.ElevatorReport;
import java.util.ArrayList;
import java.util.List;
import scanerzus.Request;

public class Building implements BuildingInterface {
    private final List<Elevator> elevators = new ArrayList<>();
    private final List<Request> upRequests = new ArrayList<>();
    private final List<Request> downRequests = new ArrayList<>();

    private ElevatorSystemStatus systemStatus;

    private final int numberOfFloors;
    private final int numberOfElevators;
    private final int elevatorCapacity;

    public Building(int numberOfFloors, int numberOfElevators, int elevatorCapacity) {
        if (numberOfFloors < 3 || numberOfFloors > 30) {
            throw new IllegalArgumentException("numberOfFloors must be between 3 and 30");
        }
        if (elevatorCapacity < 3 || elevatorCapacity > 20) {
            throw new IllegalArgumentException("elevatorCapacity must be between 3 and 20");
        }
        if (numberOfElevators < 1) {
            throw new IllegalArgumentException("numberOfElevators must be at least 1");
        }
        this.numberOfFloors = numberOfFloors;
        this.numberOfElevators = numberOfElevators;
        this.elevatorCapacity = elevatorCapacity;
        this.systemStatus = ElevatorSystemStatus.outOfService;
        for (int i = 0; i < numberOfElevators; i++) {
            this.elevators.add(new Elevator(numberOfFloors, elevatorCapacity));
        }
    }

    public boolean addRequest(Request request) {
        if (this.systemStatus == ElevatorSystemStatus.outOfService || this.systemStatus == ElevatorSystemStatus.stopping) {
            throw new IllegalStateException("System is out of service or stopping.");
        }
        if (request.getStartFloor() < 0 || request.getEndFloor() < 0 || request.getStartFloor() == request.getEndFloor() || request.getStartFloor() >= this.numberOfFloors || request.getEndFloor() >= this.numberOfFloors) {
            return false;
        }
        if (request.getStartFloor() > request.getEndFloor()) {
            this.downRequests.add(request);
        } else {
            this.upRequests.add(request);
        }
        return true;
    }

    private void distributeRequests() {
        if (this.systemStatus == ElevatorSystemStatus.outOfService || this.systemStatus == ElevatorSystemStatus.stopping) {
            throw new IllegalStateException("System is out of service or stopping.");
        }
        for (Elevator elevator : this.elevators) {
            if (this.upRequests.isEmpty() && this.downRequests.isEmpty()) {
                return;
            }
            if (elevator.isTakingRequests() && elevator.getCurrentFloor() == 0) {
                if (this.upRequests.size() <= this.elevatorCapacity) {
                    elevator.processRequests(new ArrayList<>(this.upRequests));
                    this.upRequests.clear();
                } else {
                    elevator.processRequests(new ArrayList<>(this.upRequests.subList(0, this.elevatorCapacity)));
                    this.upRequests.subList(0, this.elevatorCapacity).clear();
                }
            } else if (elevator.isTakingRequests() && elevator.getCurrentFloor() == this.numberOfFloors - 1) {
                if (this.downRequests.size() <= this.elevatorCapacity) {
                    elevator.processRequests(new ArrayList<>(this.downRequests));
                    this.downRequests.clear();
                } else {
                    elevator.processRequests(new ArrayList<>(this.downRequests.subList(0, this.elevatorCapacity)));
                    this.downRequests.subList(0, this.elevatorCapacity).clear();
                }
            }
        }
    }

    public void step() {
        if (this.systemStatus == ElevatorSystemStatus.outOfService) {
            return;
        }
        if (this.systemStatus == ElevatorSystemStatus.stopping) {
            this.elevators.forEach(Elevator::step);
            if (this.elevators.stream().allMatch(e -> !e.isDoorClosed() && e.getCurrentFloor() == 0)) {
                this.systemStatus = ElevatorSystemStatus.outOfService;
            }
            return;
        }
        distributeRequests();
        this.elevators.forEach(Elevator::step);
    }

    public boolean startElevatorSystem() {
        if (this.systemStatus == ElevatorSystemStatus.stopping) {
            throw new IllegalStateException("System is stopping.");
        }
        if (this.systemStatus == ElevatorSystemStatus.running) {
            return false;
        }
        this.elevators.forEach(Elevator::start);
        this.systemStatus = ElevatorSystemStatus.running;
        return true;
    }

    public void stopElevatorSystem() {
        this.elevators.forEach(Elevator::takeOutOfService);
        this.upRequests.clear();
        this.downRequests.clear();
        this.systemStatus = ElevatorSystemStatus.stopping;
    }

    public void stopElevator(int elevatorId) {
        ElevatorInterface elevator = this.elevators.get(elevatorId);
        elevator.takeOutOfService();
    }

    public BuildingReport getElevatorSystemStatus() {
        ElevatorReport[] elevatorReports = new ElevatorReport[this.elevators.size()];
        for (int i = 0; i < this.elevators.size(); i++) {
            elevatorReports[i] = this.elevators.get(i).getElevatorStatus();
        }
        return new BuildingReport(this.numberOfFloors, this.numberOfElevators, this.elevatorCapacity, elevatorReports, this.upRequests, this.downRequests, this.systemStatus);
    }
}
