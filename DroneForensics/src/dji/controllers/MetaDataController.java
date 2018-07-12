package dji.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class MetaDataController {
	
	@FXML TextField textField;
	@FXML TextArea textID;
	
	FileChooser fileChooser = new FileChooser();
	
	@FXML
	public void showExifData(ActionEvent event) {
		
		StringBuilder sb = new StringBuilder();
		
		fileChooser.setTitle("choose a File");
		
		File file = fileChooser.showOpenDialog(null);
		
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
			//System.out.println(sb);
		} catch (ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
