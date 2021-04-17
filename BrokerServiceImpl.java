package com.xyzInsurance.brokerOnboardingSvc.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.xyzInsurance.brokerOnboardingSvc.entities.BrokerRecord;

@Service
public class BrokerServiceImpl implements BrokerService {
	
	String dburl="jdbc:ucanaccess://C:\Users\Debdeep.pc\Desktop\DB.accdb;openExclusive=false;ignoreCase=true";

	List<BrokerRecord> list;
	List<BrokerRecord> list1;

	@Override
	public List<BrokerRecord> getBrokerRecords() {
		
		try (Connection connection = DriverManager.getConnection(dburl)) {
            
            String sql = "SELECT * FROM MyTable";
            
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            list=new ArrayList<>(); 
            while (result.next()) {
                long id = result.getLong("brokerId");
                String firstName = result.getString("brokerFirstName");
                String lastName = result.getString("brokerLastName");
          
        		list.add(new BrokerRecord(id,firstName,lastName));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		
		return list;
	}

	@Override
	public List<BrokerRecord> getSingleBrokerRecord(long brokerId) {
		
try (Connection connection = DriverManager.getConnection(dburl)) {
            
            String sql = "SELECT * FROM MyTable WHERE brokerId = "+brokerId;
             
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            list1=new ArrayList<>(); 
            while (result.next()) {
                long id = result.getLong("brokerId");
                String firstName = result.getString("brokerFirstName");
                String lastName = result.getString("brokerLastName");
                list1.add(new BrokerRecord(id,firstName,lastName));
            }
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
			return list1;
	}

	@Override
	public int addBrokerRecord(BrokerRecord brokerRecord){
		int row=0;
		try (Connection connection = DriverManager.getConnection(dburl)) {
            
            
            String sql = "INSERT INTO MyTable (brokerId, brokerFirstName, brokerLastName) VALUES (?, ?, ?)";
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, brokerRecord.getId());
            preparedStatement.setString(2, brokerRecord.getFirstName());
            preparedStatement.setString(3, brokerRecord.getLastName());
             
            row=preparedStatement.executeUpdate();
            
             
        } catch (SQLException ex) {
        	
            ex.printStackTrace();
        }
		return row;
			   
	}

	@Override
	public int updateBrokerRecord(BrokerRecord brokerRecord,long brokerId) {
		int row=0;
		
		try (Connection connection = DriverManager.getConnection(dburl)) {
            
            
            String sql = "UPDATE MyTable SET (brokerId, brokerFirstName, brokerLastName) = (?, ?, ?) WHERE brokerId = ?";
             
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, brokerRecord.getId());
            preparedStatement.setString(2, brokerRecord.getFirstName());
            preparedStatement.setString(3, brokerRecord.getLastName());
            preparedStatement.setLong(4, brokerId);
             
            row=preparedStatement.executeUpdate();
            
             
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return row;
	}

	@Override
	public int deleteBrokerRecord(long brokerId) {
		
		int row=0;
		try (Connection connection = DriverManager.getConnection(dburl)) {
            
            String sql = "DELETE FROM MyTable WHERE brokerId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, brokerId);
            row=preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
		return row;
	}

}
