package dforensics.dji.controllers;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MetaDataController {

	@FXML
	TextField textField;
	@FXML
	TextArea textID;

	@FXML
	public void showExifData(ActionEvent event) {

		StringBuilder sb = new StringBuilder();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("choose a File");
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Media Files", "*.jpg", "*.JPEG",
				"*.JPG", "*.png", "*.PNG", "*.BMP", "*.AVI", "*.GIF", "*.WAV", "*.MP4", "*.TIFF");
		fileChooser.getExtensionFilters().addAll(extensionFilter);
		File file = fileChooser.showOpenDialog(null);

		if (file == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("File Selection");
			alert.setHeaderText("Media file not selected");
			alert.setContentText("Please upload the valid media file.");
			alert.show();
			return;
		}

		textField.setText(file.getAbsolutePath());
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(file.getAbsoluteFile());

			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					sb.append(directory.getName() + " - " + tag.getTagName() + " = " + tag.getDescription() + "\n");
				}
				if (directory.hasErrors()) {
					for (String error : directory.getErrors()) {
						System.err.format("ERROR: %s", error);
					}
				}
			}

			textID.setText(sb.toString());
			// System.out.println(sb);
		} catch (ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
