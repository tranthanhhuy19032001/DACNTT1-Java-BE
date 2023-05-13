package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.dao.impl.CategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.pageable.Pageable;
import com.laptrinhjavaweb.service.INewService;

public class NewService implements INewService{

	@Inject
	private INewDAO newDAO;
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryById(categoryId);
	}
	
	@Override
	public NewModel save(NewModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		if(newModel.getCategoryId() == null) {
			CategoryModel category = categoryDAO.findOneByCode(newModel.getCategoryCode());
			newModel.setCategoryId(category.getId());
		}
//		newModel.setCreatedBy("");
		Long newId = newDAO.save(newModel);
		return newDAO.findOne(newId);
	}

	@Override
	public NewModel update(NewModel updateNew) {
		NewModel oldNew = newDAO.findOne(updateNew.getId());
		updateNew.setCreatedDate(oldNew.getCreatedDate());
		updateNew.setCreatedBy(oldNew.getCreatedBy());
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//		updateNew.setCreatedBy("");
		CategoryModel category = categoryDAO.findOneByCode(updateNew.getCategoryCode());
		updateNew.setCategoryId(category.getId());
		newDAO.update(updateNew);
		return newDAO.findOne(updateNew.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(long id: ids) {
			//1.delete comment (khoa ngoai new_id)
			//2.delete news
			newDAO.delete(id);
		}
	}

	@Override
	public List<NewModel> findAll(Pageable pageable) {
		return newDAO.findAll(pageable);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewModel findOne(long id) {
		NewModel newModel = newDAO.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(newModel.getCategoryId());
		newModel.setCategoryCode(categoryModel.getCode());
        return newModel;
	}
	
}
