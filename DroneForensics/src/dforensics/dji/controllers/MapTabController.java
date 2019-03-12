package dforensics.dji.controllers;

import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MapTabController {

	@FXML private WebView webView;
	
	DJIMainController djiMainController;
	
	final static String htmlFile = "/dforensics/dji/html/kmlMap.html";
	
	public void openMaps(String kmlFilePath) {
		WebEngine engine = webView.getEngine();
		URL localURL = getClass().getResource(htmlFile);
		//System.out.println(localURL);
		
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				if(newValue== State.SUCCEEDED){
					//engine.executeScript("initMap('"+kmlFilePath+"')");
					JSObject win = (JSObject) engine.executeScript("window");
					win.setMember("javaObj", new JsObjectBridge(engine, kmlFilePath));
					engine.executeScript("javaObj.start()");
				}
			};
		});
		engine.load(localURL.toString());
	}
	
	public void injectMapTab(DJIMainController mainController) {
		this.djiMainController = mainController;
	}
}
