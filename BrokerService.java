package com.xyzInsurance.brokerOnboardingSvc.services;

import java.util.List;

import com.xyzInsurance.brokerOnboardingSvc.entities.BrokerRecord;

public interface BrokerService {
	
	public List<BrokerRecord>getBrokerRecords();
	
	public List<BrokerRecord>getSingleBrokerRecord(long brokerId);
	
	public int addBrokerRecord(BrokerRecord brokerRecord);
	
	public int updateBrokerRecord(BrokerRecord brokerRecord,long brokerId);
	
	public int deleteBrokerRecord(long brokerId);

}
