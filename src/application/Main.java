package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


//CREATE DATABASE car_repair;
//USE car_repair;
//
//CREATE TABLE users (
//    id INT AUTO_INCREMENT PRIMARY KEY,
//    login VARCHAR(100) NOT NULL,
//    password VARCHAR(20) NOT NULL
//);
//
//CREATE TABLE cars (
//    id INT AUTO_INCREMENT PRIMARY KEY,
//    client_id INT,
//    make VARCHAR(50),
//    model VARCHAR(50),
//    FOREIGN KEY (client_id) REFERENCES users(id)
//);
//
//CREATE TABLE repair_requests (
//    id INT AUTO_INCREMENT PRIMARY KEY,
//    car_id INT,
//    description TEXT,
//    status VARCHAR(50),
//    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//    FOREIGN KEY (car_id) REFERENCES cars(id)
//);
