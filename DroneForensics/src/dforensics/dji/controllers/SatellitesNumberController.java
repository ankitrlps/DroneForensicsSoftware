package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class SatellitesNumberController {

	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;
	
	public void displaySatellites(List<DjiParameters> list) {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Satellites");

		for (DjiParameters values : list) {
			String timeDate = values.getUpdateTime();
			String[] split = timeDate.split(" ");
			int sat = Integer.parseInt(values.getOsdGpsNum());

			series.getData().add(new XYChart.Data<>(split[1], sat));
		}
		lineChart.getData().add(series);
	}
	
}
