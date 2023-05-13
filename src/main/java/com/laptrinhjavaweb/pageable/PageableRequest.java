package com.laptrinhjavaweb.pageable;

import com.laptrinhjavaweb.sort.Sorter;

public class PageableRequest implements Pageable {
	
	private Integer page;
	private Integer maxPageItem;
	Sorter sorter;

	public PageableRequest() {}
	public PageableRequest(Integer offset, Integer maxPageItem, Sorter sorter) {
		this.page = offset;
		this.maxPageItem = maxPageItem;
		this.sorter = sorter;
	}

	@Override
	public Integer getPage() {
		return this.page;
	}

	@Override
	public Integer getLimit() {
		return this.maxPageItem;
	}

	@Override
	public Integer getOffset() {
		if(this.page != null && this.maxPageItem != null) {
			return (this.page - 1) * this.maxPageItem;
		}
		return null;
	}

	@Override
	public Sorter getSorter() {
		if(this.sorter != null) {
			return this.sorter;
		}
		return null;
	}

}
