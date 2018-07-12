package dji.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DJIMainController {

	@FXML private HeaderController 	headerController;
	@FXML private OverviewTabController overViewTabController;
	@FXML private MapTabController mapTabController;
	@FXML private FlightControlController flightControlController;
	@FXML private BatteryTabController batteryTabController;
	@FXML private SpeedTabController speedTabController;
	@FXML private DroneRollPitchYawController rpyDroneTabController;
	@FXML private AltitudeTabController altitudeTabController;
	@FXML private RollPitchYawGimbalController rpyGimbalController;
	@FXML private SatellitesNumberController satelliteNumberController;
	
	@FXML private ImageView view;	
	
	@FXML private void initialize() {
		headerController.injectHeader(this);
		Image image = new Image("/dji/images/DJI_Image.png");
		view.setImage(image);
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
	
	public void getListForSpeed(List<DjiParameters> getList) {
		speedTabController.dislaySpeed(getList);
	}
	
	public void getListForRPY(List<DjiParameters> getList) {
		rpyDroneTabController.dislayDroneRPY(getList);
	}
	
	public void getListForAltitude(List<DjiParameters> getList) {
		altitudeTabController.dislayDroneAltitude(getList);
	}
	
	public void getListForRPYGimbal(List<DjiParameters> getList) {
		rpyGimbalController.dislayGimbalRPY(getList);
	}
	
	public void getListForSat(List<DjiParameters> getList) {
		satelliteNumberController.displaySatellites(getList);
	}
}