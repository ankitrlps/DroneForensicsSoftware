package dji.controllers;

import java.text.DecimalFormat;
import java.util.List;

import dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class OverviewTabController {

	@FXML
	Text distanceTravelled, flightDate, flightLength, maxAlt, droneType, crtlBy, batteryPercent, batterySerial,
			batteryType, volConsumption, batteryProductDate, flightLoc, aircraftName, aircraftSerialNo, photosTaken,
			videoTime, maxSatellites, homeLoc, djiAppLoc, djiAppOsVersion, rcSerialNo, cameraSerialNo, maxSpeed;

	DJIMainController djiMainController;

	public void injectOverview(DJIMainController mainController) {
		this.djiMainController = mainController;
	}

	public void displayOverview(List<DjiParameters> list) {
		int lastIndex = list.size() - 1;
		DecimalFormat df = new DecimalFormat("###.##");
		String dateTime = list.get(0).getUpdateTime();
		String dateTimeSplit[] = dateTime.split(" ");
		flightDate.setText(dateTimeSplit[0]);

		String flyTime = df.format((Double.parseDouble(list.get(lastIndex).getOsdFlyTime()) / 60));
		flightLength.setText("" + flyTime + " mins");

		distanceTravelled.setText(Double.parseDouble("" + list.get(lastIndex).getDistanceTravelled()) + " meters");

		// --------brute force---------
		double altitude = 0.0d;
		for (int i = 0; i <= lastIndex; i++) {
			if (Double.parseDouble(list.get(i).getOsdAltitude()) > altitude) {
				altitude = Double.parseDouble(list.get(i).getOsdAltitude());
			}
		}
		maxAlt.setText(altitude + " meters");
		// ----------------------------

		flightLoc.setText(list.get(lastIndex).getdCity() + ", " + list.get(lastIndex).getdArea());

		aircraftName.setText(list.get(lastIndex).getrAName());

		aircraftSerialNo.setText(list.get(0).getdAirSnBytes());

		photosTaken.setText(list.get(0).getdPhotoNum());

		videoTime.setText(list.get(0).getdVideoTime() + " seconds");
		// ----------Brute Force---------
		int maxSat = 0;
		for (int i = 0; i <= lastIndex; i++) {
			if (Integer.parseInt(list.get(i).getOsdGpsNum()) > maxSat) {
				maxSat = Integer.parseInt(list.get(i).getOsdGpsNum());
			}
		}
		maxSatellites.setText("" + maxSat);
		// ------------------------------

		homeLoc.setText(list.get(lastIndex).gethLatitude() + ", " + list.get(lastIndex).gethLongitude());

		djiAppLoc.setText(list.get(lastIndex).getAgLat() + " , " + list.get(lastIndex).getAgLong());

		djiAppOsVersion.setText(list.get(0).getdAppType() + " & " + list.get(0).getdAppVersion());

		rcSerialNo.setText(list.get(0).getdRcSn());

		cameraSerialNo.setText(list.get(0).getdCamSn());

		droneType.setText(list.get(1).getOsdDroneType() + " - DJI Phantom 4");

		crtlBy.setText(list.get(1).getOsdcrtlDevice().trim() + " (Radio Controller)");

		batterySerial.setText(list.get(0).getdBatterySn());

		batteryType.setText(list.get(0).getOsdBatteryType());

		batteryPercent.setText(list.get(10).getSbBattery() + "%" + " - " + list.get(lastIndex).getSbBattery() + "%");

		volConsumption.setText(list.get(10).getSbVoltage() + "%" + " - " + list.get(lastIndex).getSbVoltage() + "%");

		batteryProductDate.setText(list.get(10).getCbProductDate());
		
		maxSpeed.setText(list.get(2).getdMaxHSpeed());
		
	/*	String timeDate = list.get(0).getUpdateTime();
		System.out.println("time dte " + timeDate);
		String[] split = timeDate.split(" ");
		System.out.println(split[0] + " | " + split[1]);
		String test = split[1].substring(0, 2);
		int i = Integer.parseInt(test);
		String 
		System.out.println(i + 8);*/
	}
}
