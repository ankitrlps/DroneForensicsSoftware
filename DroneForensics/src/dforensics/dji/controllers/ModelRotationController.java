package dforensics.dji.controllers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.tds.TdsModelImporter;
import com.opencsv.CSVReader;

import dforensics.dji.pojoClass.DjiParameters;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class ModelRotationController {


	@FXML
	AnchorPane anchorPane;

	@FXML
	Button startButton, pauseButton, stopButton;
	// Group modelGroup;
	FileChooser fileChooser = new FileChooser();
	String filePath;
	CSVReader reader = null;
	TdsModelImporter model = new TdsModelImporter();
	// Text text = new Text();
	// ArrayList<Double> an = new ArrayList<>();
	// Group modelGroup;

	private final Rotate cameraXRotate = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
	private final Rotate cameraYRotate = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
	private final Translate cameraPosition = new Translate(-300, -550, -700);
	private double dragStartX, dragStartY, dragStartRotateX, dragStartRotateY;

	@FXML
	private void upload(ActionEvent event) {

		fileChooser.setTitle("choose a File");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		if (file != null) {
			filePath = file.getAbsolutePath();
			try {
				reader = new CSVReader(new FileReader(filePath), ',', '\'', 2);

				String[] column = null;
				// String[] splitDateTime = null;
				List<DjiParameters> paramValues = new ArrayList<DjiParameters>();
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
				showFigure(paramValues);
			} catch (IOException io) {
				Logger.getLogger(ModelRotationController.class.getName()).log(Level.SEVERE, null, io);
			}
		} else {
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Error");
			errorAlert.setHeaderText("Error Information");
			errorAlert.setContentText("Error while selection");
			errorAlert.show();
		}

	}

	private Group show3D() {
		try {
			URL modelUrl = this.getClass().getResource("/dji/tds/hst.3ds");
			model.read(modelUrl);
		} catch (ImportException e) {
			System.out.println("Error importing obj model: " + e.getMessage());
		}
		final Node[] modelMesh = model.getImport();
		model.close();
		Group group = new Group();
		group.getChildren().addAll(modelMesh);

		return group;
	}

	private Group buildScene(Group modelGroup) {
		Group group1 = new Group();
		PointLight pointLight = new PointLight();
		pointLight = new PointLight(Color.ALICEBLUE);
		pointLight.setTranslateX(800);
		pointLight.setTranslateY(-900);
		pointLight.setTranslateZ(-1000);

		group1.getChildren().addAll(modelGroup, pointLight);
		return group1;
	}

	private SubScene createSubScene(Group groupScene) {
		PerspectiveCamera camera = new PerspectiveCamera();
		camera.setTranslateX(-300);
		camera.setTranslateY(0);
		camera.setTranslateZ(200);
		camera.getTransforms().addAll(cameraXRotate, cameraYRotate, cameraPosition);
		SubScene scene = new SubScene(groupScene, 300, 200, true, SceneAntialiasing.BALANCED);
		scene.widthProperty().bind((this.anchorPane).widthProperty());
		scene.heightProperty().bind((this.anchorPane).heightProperty());
		scene.setCamera(camera);

		scene.addEventHandler(MouseEvent.ANY, (MouseEvent event) -> {
			if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				dragStartX = event.getSceneX();
				dragStartY = event.getSceneY();
				dragStartRotateX = cameraXRotate.getAngle();
				dragStartRotateY = cameraYRotate.getAngle();
			} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				double xDelta = event.getSceneX() - dragStartX;
				double yDelta = event.getSceneY() - dragStartY;
				cameraXRotate.setAngle(dragStartRotateX - (yDelta * 0.7));
				cameraYRotate.setAngle(dragStartRotateY + (xDelta * 0.7));
			}
		});

		return scene;
	}

	private void showFigure(List<DjiParameters> list) {
		Group modelGroup = show3D();
		Group buildModelGroup = buildScene(modelGroup);
		SubScene mainSubScene = createSubScene(buildModelGroup);
		double i = 600d;
		Timeline timeline = new Timeline();
		Node n = modelGroup;

		for (DjiParameters listValues : list) {
			double rollValue = Double.parseDouble(listValues.getOsdRoll());
			double pitchValue = Double.parseDouble(listValues.getOsdPitch());
			double yawValue = Double.parseDouble(listValues.getOsdYaw());
			// calc(modelGroup, rollValue, pitchValue,
			// yawValue);

			double A11 = Math.cos(rollValue) * Math.cos(yawValue);
			double A12 = Math.cos(pitchValue) * Math.sin(rollValue)
					+ Math.cos(rollValue) * Math.sin(pitchValue) * Math.sin(yawValue);
			double A13 = Math.sin(rollValue) * Math.sin(pitchValue)
					- Math.cos(rollValue) * Math.cos(pitchValue) * Math.sin(yawValue);
			double A21 = -(Math.cos(yawValue) * Math.sin(rollValue));
			double A22 = Math.cos(rollValue) * Math.cos(pitchValue)
					- Math.sin(rollValue) * Math.sin(pitchValue) * Math.sin(yawValue);
			double A23 = Math.cos(rollValue) * Math.sin(pitchValue)
					+ Math.cos(pitchValue) * Math.sin(rollValue) * Math.sin(yawValue);
			double A31 = Math.sin(yawValue);
			double A32 = -(Math.cos(yawValue) * Math.sin(pitchValue));
			double A33 = Math.cos(pitchValue) * Math.cos(yawValue);
			double angle = Math.acos((A11 + A22 + A33 - 1d) / 2d);
			// an.add(angle);
			if (angle != 0d) {
				double denom = 2d * Math.sin(angle);
				Point3D p = new Point3D((A32 - A23) / denom, (A13 - A31) / denom, (A21 - A12) / denom);
				n.setRotationAxis(p);
				n.setRotate(Math.toDegrees(angle));
				timeline.getKeyFrames()
						.addAll(new KeyFrame(Duration.millis(i),
								new KeyValue(n.rotateProperty(), n.getRotate(), Interpolator.LINEAR)),
								new KeyFrame(Duration.seconds(i++/100), new KeyValue(n.rotateProperty(),
										n.getRotate() + Math.toDegrees(angle), Interpolator.LINEAR)),
								new KeyFrame(Duration.millis(500)));
			}

		}
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				timeline.play();	
			}
		});
		
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				timeline.pause();	
			}
		});
		
stopButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				timeline.stop();	
			}
		});

		this.anchorPane.getChildren().addAll(mainSubScene);

	}

}
