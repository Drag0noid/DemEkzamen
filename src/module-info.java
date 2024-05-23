module dem {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
    opens models to javafx.base;
    
	exports DAO;
	exports models;

	opens application to javafx.graphics, javafx.fxml;
}
