package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.pageable.Pageable;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
	NewModel save(NewModel newModel);
	NewModel update(NewModel updateNew);
	void delete(Long[] ids);	
	List<NewModel> findAll(Pageable pageable);
	int getTotalItem();
	NewModel findOne(long id);
}
