module TrinhChienProject {
	exports main;
	
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.web;
	requires voicerss.tts;
	
	opens main to javafx.fxml, javafx.controls, javafx.web, voicerss.tts;
}
