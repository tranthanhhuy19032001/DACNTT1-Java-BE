package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.pageable.Pageable;

public interface INewDAO extends GenericDAO<NewModel> {
	NewModel findOne(Long id);
	List<NewModel> findByCategoryById(Long categoryId);
	Long save(NewModel newModel);
	void update(NewModel updateNew);
	void delete(long id);
	List<NewModel> findAll(Pageable pageable);
	int getTotalItem();
}


