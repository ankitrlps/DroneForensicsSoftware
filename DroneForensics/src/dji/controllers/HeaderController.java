package dji.controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import dji.pojoClass.DjiParameters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class HeaderController {

	DJIMainController djiMainController;

	@FXML private Text fileReadNotice;
	@FXML TextField kmlFilePath;

	FileChooser fileChooser = new FileChooser();
	String filePath;
	String fileName;
	String kmlPath;
	CSVReader reader = null;
	CSVWriter writer = null;
	FileWriter kmlWriter;
	DocumentBuilderFactory docFactory;
	DocumentBuilder docbuilder;
	TransformerFactory transformFac;
	Transformer transform;
	DOMSource source;
	StreamResult result;
	Date dateStart = null;
	Date dateEnd = null;
	List<DjiParameters> paramValues = new ArrayList<DjiParameters>();

	public void injectHeader(DJIMainController mainController) {
		this.djiMainController = mainController;
	}

	@FXML
	private void uploadFile(ActionEvent event) {

		fileChooser.setTitle("choose a File");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			filePath = file.getAbsolutePath();
			fileName = file.getName();
			try {
				reader = new CSVReader(new FileReader(filePath), ',', '\'', 2);
				String[] column = null;
				while ((column = reader.readNext()) != null) {
					DjiParameters params = new DjiParameters(column[0], column[1], column[2], column[3], column[4],
							column[5], column[6], column[7], column[8], column[9], column[10], column[11], column[12],
							column[13], column[14], column[15], column[16], column[17], column[18], column[19],
							column[20], column[21], column[22], column[23], column[24], column[25], column[26],
							column[27], column[28], column[29], column[30], column[31], column[32], column[33],
							column[34], column[35], column[36], column[37], column[38], column[39], column[40],
							column[41], column[42], column[43], column[44], column[45], column[46], column[47],
							column[48], column[49], column[50], column[51], column[52], column[53], column[54],
							column[55], column[56], column[57], column[58], column[59], column[60], column[61],
							column[62], column[63], column[64], column[65], column[66], column[67], column[68],
							column[69], column[70], column[71], column[72], column[73], column[74], column[75],
							column[76], column[77], column[78], column[79], column[80], column[81], column[82],
							column[83], column[84], column[85], column[86], column[87], column[88], column[89],
							column[90], column[91], column[92], column[93], column[94], column[95], column[96],
							column[97], column[98], column[99], column[100], column[101], column[102], column[103],
							column[104], column[105], column[106], column[107], column[108], column[109], column[110],
							column[111], column[112], column[113], column[114], column[115], column[116], column[117],
							column[118], column[119], column[120], column[121], column[122], column[123], column[124],
							column[125], column[126], column[127], column[128], column[129], column[130], column[131],
							column[132], column[133], column[134], column[135], column[136], column[137], column[138],
							column[139], column[140], column[141], column[142], column[143], column[144], column[145],
							column[146], column[147], column[148], column[149], column[150], column[151], column[152],
							column[153], column[154], column[155], column[156], column[157], column[158], column[159],
							column[160], column[161], column[162], column[163], column[164], column[165], column[166],
							column[167], column[168], column[169], column[170], column[171], column[172], column[173],
							column[174], column[175], column[176], column[177], column[178], column[179], column[180],
							column[181], column[182], column[183], column[184], column[185], column[186], column[187],
							column[188], column[189], column[190], column[191], column[192], column[193], column[194],
							column[195], column[196], column[197], column[198], column[199], column[200], column[201],
							column[202], column[203], column[204], column[205], column[206], column[207], column[208],
							column[209], column[210], column[211], column[212], column[213], column[214], column[215],
							column[216], column[217], column[218], column[219], column[220], column[221], column[222],
							column[223], column[224], column[225], column[226], column[227], column[228], column[229],
							column[230], column[231], column[232], column[233], column[234], column[235], column[236],
							column[237], column[238], column[239], column[240], column[241], column[242]);
					paramValues.add(params);
				}
				fileReadNotice.setText("File Read Success");
				
				djiMainController.getListForOverview(paramValues);
				
				djiMainController.getListForFlightController(paramValues);
				
				djiMainController.getListForBattery(paramValues);
				
				djiMainController.getListForSpeed(paramValues);
				
				djiMainController.getListForRPY(paramValues);
				
				djiMainController.getListForAltitude(paramValues);
				
				djiMainController.getListForRPYGimbal(paramValues);
				
				djiMainController.getListForSat(paramValues);
				
				
			} catch (IOException io) {
				Logger.getLogger(HeaderController.class.getName()).log(Level.SEVERE, null, io);
			}
		} else {
			fileReadNotice.setText("File Read Failed");
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Error");
			errorAlert.setHeaderText("Error Information");
			errorAlert.setContentText("Error while selection");
			errorAlert.show();
		}

	}
	
	@FXML
	private void kmlFileLoc(ActionEvent event) {
		kmlPath = kmlFilePath.getText();
		djiMainController.getMap(kmlPath);
	}

	@FXML
	private void convertCSVToKML(ActionEvent event) {

		try {
			String firstLineTimeElement = null;
			String firstLineLatitude = null;
			String firstLineLongitude = null;
			String firstLineAltitudeElement = null;
			String lastLineTimeElement = null;
			String lastLineAltitudeElement = null;
			String lastLineLatitudeElement = null;
			String lastLineLongitudeElement = null;

			if (paramValues != null && !paramValues.isEmpty()) {
				firstLineTimeElement = paramValues.get(0).getUpdateTime();
				firstLineLatitude = paramValues.get(0).getOsdLatitude();
				firstLineLongitude = paramValues.get(0).getOsdLongitude();
				firstLineAltitudeElement = paramValues.get(0).getOsdAltitude();
				lastLineTimeElement = paramValues.get(paramValues.size() - 1).getUpdateTime();
				lastLineAltitudeElement = paramValues.get(paramValues.size() - 1).getOsdAltitude();
				lastLineLatitudeElement = paramValues.get(paramValues.size() - 1).getOsdLatitude();
				lastLineLongitudeElement = paramValues.get(paramValues.size() - 1).getOsdLongitude();
				System.out.println("Here it is: " + lastLineAltitudeElement + "  " + lastLineLatitudeElement + "  "
						+ lastLineLongitudeElement);
			}
			String[] firstDateTimeDivide = firstLineTimeElement.split(" ");
			String firstDate = firstDateTimeDivide[0];
			String firstTime = firstDateTimeDivide[1];

			String[] lastDateTimeDivide = lastLineTimeElement.split(" ");
			String lastDate = lastDateTimeDivide[0];
			String lastTime = lastDateTimeDivide[1];

			docFactory = DocumentBuilderFactory.newInstance();
			docbuilder = docFactory.newDocumentBuilder();
			Document doc = docbuilder.newDocument();
			Element rootElement = doc.createElement("kml");
			doc.appendChild(rootElement);

			Attr attribute1 = doc.createAttribute("xmlns");
			attribute1.setValue("http://www.opengis.net/kml/2.2");
			rootElement.setAttributeNode(attribute1);

			Attr attribute2 = doc.createAttribute("xmlns:gx");
			attribute2.setValue("http://www.google.com/kml/ext/2.2");
			rootElement.setAttributeNode(attribute2);

			Element document = doc.createElement("Document");
			rootElement.appendChild(document);

			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode("FlightLog evaluation for DJI Phantom copter"));
			document.appendChild(name);

			Element desc = doc.createElement("description");
			desc.appendChild(doc.createTextNode(firstLineTimeElement + " - " + fileName));
			document.appendChild(desc);

			/* ~~~Style 1~~~ */

			Element style1 = doc.createElement("Style");
			document.appendChild(style1);

			Attr style1Attr = doc.createAttribute("id");
			style1Attr.setValue("Flightpath");
			style1.setAttributeNode(style1Attr);

			Element linestyle1 = doc.createElement("LineStyle");
			style1.appendChild(linestyle1);

			Element color1 = doc.createElement("color");
			color1.appendChild(doc.createTextNode("ff00ff00"));
			linestyle1.appendChild(color1);

			Element width1 = doc.createElement("width");
			width1.appendChild(doc.createTextNode("2"));
			linestyle1.appendChild(width1);

			Element polyStyle = doc.createElement("PolyStyle");
			style1.appendChild(polyStyle);

			Element colorPoly = doc.createElement("color");
			colorPoly.appendChild(doc.createTextNode("7f00ff00"));
			polyStyle.appendChild(colorPoly);

			/* ~~~Style 2~~~ */

			Element style2 = doc.createElement("Style");
			Attr style2Attr = doc.createAttribute("id");
			style2Attr.setValue("GrndStn");
			style2.setAttributeNode(style2Attr);
			document.appendChild(style2);

			Element linestyle2 = doc.createElement("LineStyle");
			style2.appendChild(linestyle2);

			Element color2 = doc.createElement("color");
			color2.appendChild(doc.createTextNode("FF000000"));
			linestyle2.appendChild(color2);

			Element width2 = doc.createElement("width");
			width2.appendChild(doc.createTextNode("2"));
			linestyle2.appendChild(width2);

			/* ~~~Style 3~~~ */

			Element style3 = doc.createElement("Style");
			Attr style3Attr = doc.createAttribute("id");
			style3Attr.setValue("starting");
			style3.setAttributeNode(style3Attr);
			document.appendChild(style3);

			Element iconStyle3 = doc.createElement("IconStyle");
			Element icon3 = doc.createElement("Icon");
			Element href3 = doc.createElement("href");
			href3.appendChild(doc.createTextNode("http://maps.google.com/mapfiles/dir_0.png"));
			icon3.appendChild(href3);
			iconStyle3.appendChild(icon3);
			style3.appendChild(iconStyle3);

			/* ~~~Style 4~~~ */

			Element style4 = doc.createElement("Style");
			Attr style4Attr = doc.createAttribute("id");
			style4Attr.setValue("landing");
			style4.setAttributeNode(style4Attr);
			document.appendChild(style4);

			Element iconStyle4 = doc.createElement("IconStyle");
			Element icon4 = doc.createElement("Icon");
			Element href4 = doc.createElement("href");
			href4.appendChild(doc.createTextNode("http://maps.google.com/mapfiles/dir_walk_60.png"));
			icon4.appendChild(href4);
			iconStyle4.appendChild(icon4);
			style4.appendChild(iconStyle4);

			/* ~~~ Placemark 1 ~~~ */

			Element placemark1 = doc.createElement("Placemark");
			Element timeStamp1 = doc.createElement("TimeStamp");
			Element when1 = doc.createElement("when");
			when1.appendChild(doc.createTextNode(firstDate + "T" + firstTime + "Z"));
			timeStamp1.appendChild(when1);
			placemark1.appendChild(timeStamp1);
			document.appendChild(placemark1);
			Element styleURLStarting = doc.createElement("styleUrl");
			styleURLStarting.appendChild(doc.createTextNode("#starting"));
			placemark1.appendChild(styleURLStarting);
			Element point1 = doc.createElement("Point");
			Element coordinates1 = doc.createElement("coordinates");
			coordinates1.appendChild(
					doc.createTextNode(firstLineLongitude + "," + firstLineLatitude + "," + firstLineAltitudeElement));
			point1.appendChild(coordinates1);
			placemark1.appendChild(point1);

			/* ~~~ Placemark 2 ~~~ */

			Element placemark2 = doc.createElement("Placemark");
			document.appendChild(placemark2);
			Element name1 = doc.createElement("name");
			name1.appendChild(doc.createTextNode("DJI Phantom 4 Flight Path"));
			placemark2.appendChild(name1);
			Element desc1 = doc.createElement("description");
			desc1.appendChild(doc.createTextNode(fileName));
			placemark2.appendChild(desc1);
			Element styleURLFlightPath = doc.createElement("styleUrl");
			styleURLFlightPath.appendChild(doc.createTextNode("#Flightpath"));
			placemark2.appendChild(styleURLFlightPath);
			Element lineString = doc.createElement("LineString");
			placemark2.appendChild(lineString);
			Element altOffset = doc.createElement("gx:altitudeOffset");
			altOffset.appendChild(doc.createTextNode("190.0"));
			lineString.appendChild(altOffset);
			Element altMode = doc.createElement("altitudeMode");
			altMode.appendChild(doc.createTextNode("absolute"));
			lineString.appendChild(altMode);
			Element coordinates2 = doc.createElement("coordinates");
			for (DjiParameters listValues : paramValues) {

				coordinates2.appendChild(doc.createTextNode("\n" + listValues.getOsdLongitude() + ","
						+ listValues.getOsdLatitude() + "," + listValues.getOsdAltitude() + "\n"));
			}
			lineString.appendChild(coordinates2);

			/* ~~~ Placemark 3 ~~~ */
			lineString.appendChild(coordinates2);
			Element placemark3 = doc.createElement("Placemark");
			document.appendChild(placemark3);
			Element timeStamp2 = doc.createElement("TimeStamp");
			placemark3.appendChild(timeStamp2);
			Element when2 = doc.createElement("when");
			when2.appendChild(doc.createTextNode(lastDate + "T" + lastTime + "Z"));
			timeStamp2.appendChild(when2);
			Element styleURLLanding = doc.createElement("styleUrl");
			styleURLLanding.appendChild(doc.createTextNode("#landing"));
			placemark3.appendChild(styleURLLanding);
			Element point2 = doc.createElement("Point");
			placemark3.appendChild(point2);
			Element coordinates3 = doc.createElement("coordinates");
			coordinates3.appendChild(doc.createTextNode(
					lastLineLongitudeElement + "," + lastLineLatitudeElement + "," + lastLineAltitudeElement));
			point2.appendChild(coordinates3);

			transformFac = TransformerFactory.newInstance();
			try {
				transform = transformFac.newTransformer();
				transform.setOutputProperty(OutputKeys.INDENT, "yes");
				transform.setOutputProperty(OutputKeys.METHOD, "xml");
				transform.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				source = new DOMSource(doc);
				result = new StreamResult(new File("C://program test folder//DJI_Kml1.kml"));

				transform.transform(source, result);

				System.out.println("Saved!!");
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
