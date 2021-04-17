package com.xyzInsurance.brokerOnboardingSvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.xyzInsurance.brokerOnboardingSvc.entities.BrokerRecord;
import com.xyzInsurance.brokerOnboardingSvc.services.BrokerService;

@RestController
public class MyController {
	
	@Autowired
	private BrokerService brokerService;
	
	//get all broker records
	@GetMapping("/allbrokers")
	public ResponseEntity<List<BrokerRecord>> getBrokerRecords(@RequestHeader(value="Accept") String acceptHeader,
			@RequestHeader(value="Authorization") String authorizationHeader)
	{
		if(authorizationHeader.equals("Bearer authToken"))
		{
			List<BrokerRecord> list=brokerService.getBrokerRecords();
			if(list.size()<=0)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return ResponseEntity.of(Optional.of(list));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	//get a single broker record
	@GetMapping("/broker/{brokerId}")
	public ResponseEntity<List<BrokerRecord>> getSingleBrokerRecord(@RequestHeader(value="Accept") String acceptHeader,
			@RequestHeader(value="Authorization") String authorizationHeader,
			@PathVariable String brokerId)
	{
		if(authorizationHeader.equals("Bearer authToken"))
		{
			List<BrokerRecord> list=brokerService.getSingleBrokerRecord(Long.parseLong(brokerId));
			if(list.size()<=0)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return ResponseEntity.of(Optional.of(list));
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	//adding(Post) a broker record
	@PostMapping("/broker/save")
	public ResponseEntity<BrokerRecord> addBrokerRecord(@RequestHeader(value="Accept") String acceptHeader,
			@RequestHeader(value="Authorization") String authorizationHeader,
			@RequestBody BrokerRecord brokerRecord)
	{
		if(authorizationHeader.equals("Bearer authToken"))
		{
			int checkPost=brokerService.addBrokerRecord(brokerRecord);
			if(checkPost>0)
				return ResponseEntity.status(HttpStatus.CREATED).body(brokerRecord);
			
			else
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
		
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	//update(Put) a broker record
	@PutMapping("/broker/{brokerId}")
	public ResponseEntity<BrokerRecord> updateBrokerRecord(@RequestHeader(value="Accept") String acceptHeader,
			@RequestHeader(value="Authorization") String authorizationHeader,
			@RequestBody BrokerRecord brokerRecord,@PathVariable String brokerId)
	{
		if(authorizationHeader.equals("Bearer authToken"))
		{
			int checkUpdate=brokerService.updateBrokerRecord(brokerRecord,Long.parseLong(brokerId));
			if(checkUpdate>0)
			return ResponseEntity.status(HttpStatus.OK).body(brokerRecord);
			else 
				return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	
	//delete a single broker record
	@DeleteMapping("/broker/delete/{brokerId}")
	public ResponseEntity<String> deleteBrokerRecord(@RequestHeader(value="Accept") String acceptHeader,
			@RequestHeader(value="Authorization") String authorizationHeader,
			@PathVariable String brokerId)
	{
		if(authorizationHeader.equals("Bearer authToken"))
		{
		
			int checkDelete=brokerService.deleteBrokerRecord(Long.parseLong(brokerId));
			if(checkDelete>0)
			return new ResponseEntity<String>("msg : Record deleted successfully",HttpStatus.OK);
			else
			return new ResponseEntity<String>("msg : Something went wrong. Record was not deleted",HttpStatus.SERVICE_UNAVAILABLE);
		}
		else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}

}
