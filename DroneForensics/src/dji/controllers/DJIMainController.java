package dji.controllers;

import java.util.List;

import dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;

public class DJIMainController {

	@FXML private HeaderController 	headerController;
	@FXML private OverviewTabController overViewTabController;
	@FXML private MapTabController mapTabController;
	@FXML private FlightControlController flightControlController;
	@FXML private BatteryTabController batteryTabController;
	
	@FXML private void initialize() {
		headerController.injectHeader(this);
	}
	
	public void getMap(String kmlPath) {
		mapTabController.openMaps(kmlPath);
	}
	
	public void getListForOverview(List<DjiParameters> getList){
		overViewTabController.displayOverview(getList);
	}
	
	public void getListForFlightController(List<DjiParameters> list){
		flightControlController.showFlightControl(list);
	}
	
	public void getListForBattery(List<DjiParameters> getList) {
		batteryTabController.displayBattery(getList);
	}
	
}