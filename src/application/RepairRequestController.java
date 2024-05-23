package application;

import java.sql.SQLException;
import java.util.List;

import DAO.RepairRequestDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import models.RepairRequest;


public class RepairRequestController {
    
	@FXML
	private TextField carIdField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TableView<RepairRequest> repairRequestsTable;

    @FXML
    private TableColumn<RepairRequest, String> makeColumn;

    @FXML
    private TableColumn<RepairRequest, String> modelColumn;

    @FXML
    private TableColumn<RepairRequest, String> descriptionColumn;

    @FXML
    private TableColumn<RepairRequest, String> statusColumn;

    private RepairRequestDAO repairRequestDAO;
    
    public RepairRequestController() {
        repairRequestDAO = new RepairRequestDAO();
    }

    @FXML
    private void updateRequest() {
    	makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
    	modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadRepairRequests();
    }
    
    private void loadRepairRequests() {
        try {
            List<RepairRequest> repairRequests = repairRequestDAO.getAllRepairRequests();
            ObservableList<RepairRequest> observableList = FXCollections.observableArrayList(repairRequests);
            repairRequestsTable.setItems(observableList);
        } catch (SQLException e) {
            System.err.println("Error loading repair requests: " + e.getMessage());
        }
    }
    
    @FXML
    public void submitRequest() {
        try {
            int carId = Integer.parseInt(carIdField.getText());
            String description = descriptionArea.getText();
            
            repairRequestDAO.registerRepairRequest(carId, description);
        } catch (NumberFormatException e) {
            System.err.println("Invalid car ID, please enter a valid number.");
        } catch (SQLException e) {
            System.err.println("Error while submitting zayavka na remont: " + e.getMessage());
        }
    }
    
    @FXML
    public void updateStatus() {
        RepairRequest selectedRequest = repairRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest != null) {
            try {
                repairRequestDAO.updateRepairRequestStatus(selectedRequest.getId(), "Completed");
                System.out.println("Repair request status updated successfully!");
            } catch (SQLException e) {
                System.err.println("Error updating repair request status: " + e.getMessage());
            }
        } else {
            System.err.println("Please select a repair request to update its status.");
        }
    }
    
    @FXML
    public void deleteRequest() {
        RepairRequest selectedRequest = repairRequestsTable.getSelectionModel().getSelectedItem();
        if (selectedRequest != null) {
            try {
                repairRequestDAO.deleteRepairRequest(selectedRequest.getId());
                System.out.println(selectedRequest.getId());

                System.out.println("Repair request deleted successfully!");
            } catch (SQLException e) {
                System.err.println("Error deleting repair request: " + e.getMessage());
            }
        } else {
            System.err.println("Please select a repair request to delete.");
        }
    }
}