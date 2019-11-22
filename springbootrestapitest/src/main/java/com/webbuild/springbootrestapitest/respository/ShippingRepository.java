package com.webbuild.springbootrestapitest.respository;

import java.util.List;
import java.util.Optional;

import com.webbuild.springbootrestapitest.model.*;

public interface ShippingRepository {
	
	int count();

    int save(TableObjects ShipOrder);

    int update(TableObjects ShipOrder);

    int deleteById(String id);

    List<TableObjects> findAll();

    Optional<TableObjects> findById(String id);

    List<TableObjects> findByidAndPrice(String id, String freight);
    
    String getNameById(String id);
}
