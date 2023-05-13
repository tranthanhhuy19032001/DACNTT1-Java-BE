package com.laptrinhjavaweb.pageable;

import com.laptrinhjavaweb.sort.Sorter;

public interface Pageable {
	Integer getPage();
	Integer getLimit();
	Integer getOffset();
	Sorter getSorter();
}
