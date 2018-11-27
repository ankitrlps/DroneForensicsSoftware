package dji.controllers;

import javafx.scene.web.WebEngine;

public class JsObjectBridge {
	WebEngine engine;

	String kmlPath;

	public JsObjectBridge(WebEngine engine, String kmlPath) {
		this.engine = engine;
		this.kmlPath = kmlPath;
		//System.out.println(kmlPath);
	}

	public void start() {
		engine.executeScript("var s = document.createElement(\"script\");\n" + "s.type=\"text/javascript\";\n"
				+ "s.src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyD16XHpscdaUcjsp-qX49VuOUucrWENn0U&callback=javaObj.initMap\";\n"
				+ "$(\"head\").append(s);");
	}

	public void initMap() {
		engine.executeScript("" + "var map;" + "map = new google.maps.Map(document.getElementById('map'), {\n"
				+ "  zoom: 15,\n" + "center: {lat: 28.613939, lng: 77.209021}\n" + "});\n"
				+ "var ctaLayer = new google.maps.KmlLayer({\n" + "url: \"" + kmlPath + "\",\n" + "map: map\n" + "});");
	}

	public void call() {
		engine.executeScript("initMap()");
	}
}
