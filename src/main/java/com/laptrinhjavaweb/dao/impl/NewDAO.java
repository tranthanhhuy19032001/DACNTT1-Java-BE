package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.Mapper.NewMapper;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.pageable.Pageable;
import org.apache.commons.lang.StringUtils;

public class NewDAO extends AbstracDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findByCategoryById(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
//		String sql = "INSERT INTO news (title, content, categoryid) VALUES(?, ?, ?)";
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
		sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getContent(), 
				newModel.getThumbnail(), newModel.getShortDescription(), newModel.getCategoryId(), 
				newModel.getCreatedDate(), newModel.getCreatedBy());
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby= ? WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(), 
				updateNew.getCreatedDate(), updateNew.getCreatedBy(), 
				updateNew.getModifiedDate(), updateNew.getModifiedBy(),
				updateNew.getId());

	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
		
	}

	@Override
	public List<NewModel> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if(pageable.getSorter() != null && StringUtils.isNotBlank(pageable.getSorter().getSortName()) && StringUtils.isNotBlank(pageable.getSorter().getSortBy())) {
			sql.append(" ORDER BY " + pageable.getSorter().getSortName() + " " + pageable.getSorter().getSortBy());
		}
		if(pageable.getOffset() !=null && pageable.getLimit() !=null) {
			sql.append(" LIMIT " +pageable.getOffset()+ ", " + pageable.getLimit());
		}
		return query(sql.toString(), new NewMapper());
	}
 
	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}
}
