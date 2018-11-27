# Digital Drone Forensics Software

This desktop application developed using JavaFx is intended to provide the idea of performing Digital Drone Forensics on Drones. 

Primarily, digital drone forensics is performed on DJI Phantom 4 drone. But there is no limitation to include other advanced drones using this approach.

## Environment Setup

- IDE: Eclipse (Newer version preferred).
- Java 8 or newer version of Java.
- JavaFx 8.0
- Build Tool: Maven

**Follow the below steps for setting up the project and running the application:**

- Download and install JavaFx dependency into Eclipse by following instructions mentioned in this link - https://www.eclipse.org/efxclipse/install.html
- Clone this project to your local directory.
- Import it to your Eclipse IDE.
- Third Party Open Source libraries:
  - MetaData Extractor 2.11.0 : This is used for extracting the metadata of photos and videos taken during the flight. And is                                             added as dependency in pom.xml.
  - OpenCSV 3.8 : This is used for effectively reading the log file (.csv). And is added as dependency in pom.xml.
  - 3DS Model Importer : This importer is used for importing the 3D model that helps in visualizing 3D representation of the whole                                  flight. For including this dependency, click on this link - http://www.interactivemesh.org/models/download/JFX3DModelImporters_EA_2014-02-09.zip. Unzip it and find "jim3dsModelImporterJFX.jar". Add this jar to your project's build path.
