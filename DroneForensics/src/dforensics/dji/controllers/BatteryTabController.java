package dforensics.dji.controllers;

import java.util.List;

import dforensics.dji.pojoClass.DjiParameters;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BatteryTabController {

	@FXML
	LineChart<String, Number> batteryLineChart;
	@FXML
	CategoryAxis xAxis;
	@FXML
	NumberAxis yAxis;

	@SuppressWarnings("unchecked")
	public void displayBattery(List<DjiParameters> getList) {
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		series1.setName("Battery Over Time");

		int i = 10;

		while (i < getList.size()) {
			String timeDate = getList.get(i).getUpdateTime();
			String[] split = timeDate.split(" ");
			int battery = Integer.parseInt(getList.get(i).getSbBattery());
			// System.out.println(split[1] + " " + battery);
			series1.getData().add(new XYChart.Data<>(split[1], battery));
			i++;
		}
		batteryLineChart.getData().addAll(series1);
	}
}
