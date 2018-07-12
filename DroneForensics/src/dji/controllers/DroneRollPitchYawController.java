package dji.controllers;

import java.util.List;

import dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class DroneRollPitchYawController {

	@FXML LineChart<String, Number> lineChart;
	@FXML CategoryAxis xAxis;
	@FXML NumberAxis yAxis;
	
@SuppressWarnings("unchecked")
public void dislayDroneRPY(List<DjiParameters> list) {
		
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Roll");
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		series2.setName("Pitch");
		XYChart.Series<String, Number> series3 = new XYChart.Series<>();
		series3.setName("Yaw");

		for (DjiParameters values : list) {
			String timeDate = values.getUpdateTime();
			String[] split = timeDate.split(" ");
			float roll = Float.parseFloat(values.getOsdRoll());
			float pitch = Float.parseFloat(values.getOsdPitch());
			float yaw = Float.parseFloat(values.getOsdYaw());

			series1.getData().add(new XYChart.Data<>(split[1], roll));
			series2.getData().add(new XYChart.Data<>(split[1], pitch));
			series3.getData().add(new XYChart.Data<>(split[1], yaw));
		}
		lineChart.getData().addAll(series1, series2, series3);
	}
}
