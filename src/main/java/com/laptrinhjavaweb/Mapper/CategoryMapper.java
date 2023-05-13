package com.laptrinhjavaweb.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;
public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultset) {
		try {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setId(resultset.getLong("id"));
			categoryModel.setCode(resultset.getString("code"));
			categoryModel.setName(resultset.getString("name"));
			return categoryModel;
		} catch (SQLException e) {
			return null;
		}
	}
}
