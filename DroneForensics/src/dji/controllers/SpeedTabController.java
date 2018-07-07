package dji.controllers;

import java.util.List;

import dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class SpeedTabController {

	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;
	
	public void dislaySpeed(List<DjiParameters> list) {
		
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Spped Over Time");

		for (DjiParameters values : list) {
			String timeDate = values.getUpdateTime();
			String[] split = timeDate.split(" ");
			int flyState = Integer.parseInt(values.gethSpeedRunning_max());

			series.getData().add(new XYChart.Data<>(split[1], (flyState * 10)));
		}
		lineChart.getData().add(series);
	}
}
