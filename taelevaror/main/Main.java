package main;

import controller.BuildingController;
import view.BuildingView;
import view.BuildingViewInterface;

public class Main {
    public static void main(String[] args) {
        BuildingView buildingView = new BuildingView();
        BuildingController buildingController = new BuildingController((BuildingViewInterface)buildingView);
        buildingController.addView();
    }
}
