package com.logpie.framework.db.basic;

public class PageRequest implements Pageable {

	private int page;
	private int size;
	private Sort sort;

	public PageRequest(int number, int size) {
		this(number, size, null);
	}

	public PageRequest(int number, int size, Sort sort) {
		if (page < 1) {
			throw new IllegalArgumentException(
					"Page index must not be less than one!");
		}
		if (size < 1) {
			throw new IllegalArgumentException(
					"Page size must not be less than one!");
		}

		this.page = number;
		this.size = size;
		this.sort = sort;
	}

	@Override
	public boolean isPaged() {
		return true;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getOffset() {
		return size * (page - 1);
	}

	@Override
	public Sort getSort() {
		return sort;
	}

	@Override
	public Pageable first() {
		return new PageRequest(1, size);
	}

	@Override
	public boolean hasPrevious() {
		if (page > 1) {
			return true;
		}
		return false;
	}

	@Override
	public Pageable next() {
		return new PageRequest(page + 1, size);
	}

	@Override
	public Pageable previousOrFirst() {
		if (hasPrevious()) {
			return new PageRequest(page - 1, size);
		}
		return first();
	}
}
