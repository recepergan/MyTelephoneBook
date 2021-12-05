module MyTelephoneBook {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application.view to javafx.graphics, javafx.fxml;
	
	exports application.view to javafx.graphics, javafx.fxml;
	exports application;
}
