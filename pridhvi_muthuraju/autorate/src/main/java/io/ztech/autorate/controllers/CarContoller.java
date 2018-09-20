package io.ztech.autorate.controllers;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ztech.autorate.beans.CarType;
import io.ztech.autorate.beans.Make;
import io.ztech.autorate.beans.Specification;
import io.ztech.autorate.delegate.AddCarDelegate;
import io.ztech.autorate.delegate.DeleteCarDelegate;
import io.ztech.autorate.delegate.EditCarDelegate;

@RestController
@RequestMapping("/car")
public class CarContoller {

	AddCarDelegate addCarDelegate = new AddCarDelegate();
	DeleteCarDelegate deleteCarDelegate = new DeleteCarDelegate();
	EditCarDelegate editCarDelegate = new EditCarDelegate();

	@PostMapping("/add/{typeData}/{makeData}")
	public int addCar(@PathVariable String typeData, @PathVariable String makeData,
			@RequestBody Specification specification) {
		try {
			CarType carType = new CarType();
			if (typeData.charAt(0) == '0')
				carType.setCarTypeName(typeData);
			else
				carType.setCarTypeId(Integer.parseInt(typeData));
			Make make = new Make();
			if (makeData.charAt(0) == '0')
				make.setMakeName(makeData);
			else
				make.setMakeId(Integer.parseInt(makeData));
			return addCarDelegate.addCar(carType, make, specification);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@DeleteMapping("/delete/{carId}")
	public boolean deleteCar(@PathVariable int carId) {
		try {
			return deleteCarDelegate.deleteCar(carId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@PostMapping("/edit")
	public boolean editCar(@RequestBody Specification specification) {
		try {
			return editCarDelegate.editCar(specification);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
