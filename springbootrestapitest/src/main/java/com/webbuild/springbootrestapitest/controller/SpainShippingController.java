package com.webbuild.springbootrestapitest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webbuild.springbootrestapitest.Service.ShippingDAO;
import com.webbuild.springbootrestapitest.model.TableObjects;


@RestController
@RequestMapping(value = "/Shipping")
public class SpainShippingController {
	
	@Autowired
	ShippingDAO shippingdao;
	

	@RequestMapping({"/", "/index"})
	public String index(ModelMap model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
		System.out.println("hi");
		model.put("name", name);
		return "Index";
	}
	
	@RequestMapping(value = "/spain")
	public List<TableObjects> getAllTableObjects(ModelMap model) {
		model.put("ReturnResults", shippingdao.getAllOrders());
		return shippingdao.getAllOrders(); 	
	}
	
	@RequestMapping(value = "/Spain/{id}")
	public ResponseEntity<TableObjects> getTableObjects(ModelMap model, @PathVariable String id) {
		TableObjects temp = shippingdao.getOrders(id);
		if(temp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(temp);
	}
	
	@RequestMapping(value = "/Spain", method=RequestMethod.POST)
	public void addTableObjects(ModelMap model, @RequestBody TableObjects Topic) {
		shippingdao.addOrder(Topic);	
	}
	
	@RequestMapping(value = "/Spain/{id}", method=RequestMethod.PUT)
	public void updateTableObjects(ModelMap model, @RequestBody TableObjects Topic, @PathVariable String id) {
		shippingdao.updateOrder(Topic, id);	
	}
	
	@RequestMapping(value = "/Spain/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<TableObjects> deleteTableObjects(ModelMap model, @PathVariable String id) {
		TableObjects temp = shippingdao.getOrders(id);
		if(temp==null) {
			return ResponseEntity.notFound().build();
		}
		shippingdao.deleteOrder(id);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/Spain/push", method=RequestMethod.PUT)
	public void PushtoTable(ModelMap model) {
		shippingdao.updateTable();	
	}
}
