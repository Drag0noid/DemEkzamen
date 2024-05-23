package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.Database;
import models.RepairRequest;

public class RepairRequestDAO {
    
    public void registerRepairRequest(int carId, String description) throws SQLException {
        String query = "INSERT INTO repair_requests (car_id, description, status) VALUES (?, ?, 'Pending')";
        try (Connection conn = Database.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, carId);
            stmt.setString(2, description);
            stmt.executeUpdate();
        }
    }

    public List<RepairRequest> getAllRepairRequests() throws SQLException {
        List<RepairRequest> repairRequests = new ArrayList<>();
        String query = "SELECT r.id, c.make, c.model, r.description, r.status, r.created_at \r\n"
        		+ "FROM repair_requests r left join cars c\r\n"
        		+ "on r.car_id = c.id";
        try (Connection conn = Database.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                RepairRequest repairRequest = new RepairRequest();
                repairRequest.setId(rs.getInt("id"));
                repairRequest.setMake(rs.getString("make"));
                repairRequest.setModel(rs.getString("model"));
                repairRequest.setDescription(rs.getString("description"));
                repairRequest.setStatus(rs.getString("status"));
                repairRequests.add(repairRequest);
            }
        }
        return repairRequests;
    }

    public void updateRepairRequestStatus(int requestId, String status) throws SQLException {
        String query = "UPDATE repair_requests SET status = ? WHERE id = ?";
        try (Connection conn = Database.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();
        }
    }

    public void deleteRepairRequest(int requestId) throws SQLException {
        String query = "DELETE FROM repair_requests WHERE id = ?";
        try (Connection conn = Database.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }
    }
}