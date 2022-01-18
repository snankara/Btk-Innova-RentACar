package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceDto;
import com.btkAkademi.rentACar.business.dtos.carMaintenanceDtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintenanceRequests.UpdateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

public interface CarMaintenanceService {
	DataResult<List<CarMaintenanceListDto>> getAll();
	DataResult<CarMaintenanceDto> getById(int id);
	DataResult<CarMaintenance> getByCarIdAndMaintenanceReturnDateIsNull(int carId);
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest);
	Result delete(int id);
}
