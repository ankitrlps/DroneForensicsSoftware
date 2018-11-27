package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class AltitudeTabController {
	
	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;

	public void dislayDroneAltitude(List<DjiParameters> list) {
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Altitude");

		for (DjiParameters values : list) {
			String timeDate = values.getUpdateTime();
			String[] split = timeDate.split(" ");
			int altitude = Integer.parseInt(values.getOsdAltitude());

			series.getData().add(new XYChart.Data<>(split[1], altitude));
		}
		lineChart.getData().add(series);
	}
}
