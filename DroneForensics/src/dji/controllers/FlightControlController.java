package dji.controllers;

import java.util.List;

import dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class FlightControlController {

	DJIMainController mainController;

	@FXML
	CategoryAxis xAxis;
	@FXML
	NumberAxis yAxis;
	@FXML
	LineChart<String, Number> fControlLineChart;
	@FXML
	Label helpText;

	/*
	 * final static String takeOff = "AutoTakeoff"; final static String onGpsAlt
	 * = "GPS_Atti"; final static String landing = "AutoLanding"; final static
	 * int takeOffNum = 0; final static int flyNum = 5; final static int
	 * landingNum = 10;
	 */

	@SuppressWarnings("unchecked")
	public void showFlightControl(List<DjiParameters> list) {
		
		
		helpText.setText("'10' - Assisted Take Off \n'6' - Flying (GPS) \n'11' - Auto Take Off \n'12' - Auto Landing");

		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Flight Control Over Time");

		for (DjiParameters values : list) {
			String timeDate = values.getUpdateTime();
			String[] split = timeDate.split(" ");
			int flyState = Integer.parseInt(values.getFlycStateRaw());

			series1.getData().add(new XYChart.Data<>(split[1], flyState));
		}
		fControlLineChart.getData().addAll(series1);
	}
}

/*
 * if (values.getFlycState() == takeOff) { series1.getData().add(new
 * XYChart.Data<>(split[1], takeOffNum)); } else if
 * (values.getFlycState() == onGpsAlt) { series1.getData().add(new
 * XYChart.Data<>(split[1], flyNum)); } else if
 * (values.getFlycState() == landing) { series1.getData().add(new
 * XYChart.Data<>(split[1], landingNum));
 */
