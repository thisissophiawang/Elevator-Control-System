package controller;

import building.Building;
import building.BuildingInterface;
import building.enums.ElevatorSystemStatus;
import scanerzus.Request;
import view.BuildingViewInterface;

public class BuildingController implements BuildingControllerInterface {
    private final BuildingViewInterface view;
    private BuildingInterface model;

    public BuildingController(BuildingViewInterface view) {
        this.view = view;
    }

    public void addView() {
        this.view.addController(this);
    }

    public void createElevatorSystem(Integer floorNumber, Integer elevatorNumber, Integer capacityNumber) {
        if (floorNumber == null || elevatorNumber == null || capacityNumber == null) {
            throw new IllegalArgumentException("Null argument");
        }
        this.model = new Building(floorNumber, elevatorNumber, capacityNumber);
        this.view.createBuildingView(floorNumber, elevatorNumber, capacityNumber, this.model.getElevatorSystemStatus().getElevatorReports());
    }

    public void switchElevatorSystem() {
        if (this.model == null) {
            throw new IllegalStateException("Model is null");
        }
        if (this.model.getElevatorSystemStatus().getSystemStatus() == ElevatorSystemStatus.outOfService) {
            this.model.startElevatorSystem();
            this.view.switchElevatorSystemView("running");
            this.view.updateElevators(this.model.getElevatorSystemStatus().getElevatorReports());
            this.view.activateRequests(this.model.getElevatorSystemStatus().getNumFloors());
        } else if (this.model.getElevatorSystemStatus().getSystemStatus() == ElevatorSystemStatus.running) {
            this.model.stopElevatorSystem();
            this.view.switchElevatorSystemView("stopping");
            this.view.updateElevators(this.model.getElevatorSystemStatus().getElevatorReports());
            this.view.disableRequests();
        }
    }

    public void addRequest(Integer start, Integer end) {
        if (this.model == null) {
            throw new IllegalStateException("Model is null");
        }
        this.model.addRequest(new Request(start, end));
        this.view.addRequestView(this.model.getElevatorSystemStatus().getUpRequests(), this.model.getElevatorSystemStatus().getDownRequests());
    }

    public void step() {
        if (this.model == null) {
            throw new IllegalStateException("Model is null");
        }
        this.model.step();
        this.view.updateElevators(this.model.getElevatorSystemStatus().getElevatorReports());
        this.view.addRequestView(this.model.getElevatorSystemStatus().getUpRequests(), this.model.getElevatorSystemStatus().getDownRequests());
        if (this.model.getElevatorSystemStatus().getSystemStatus() == ElevatorSystemStatus.outOfService) {
            this.view.switchElevatorSystemView("outOfService");
            this.view.disablestep();
        }
    }
}
