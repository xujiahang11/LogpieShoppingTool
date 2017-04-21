package com.logpie.framework.db.basic;

public enum Unpaged implements Pageable {
	INSTANCE;

	@Override
	public boolean isPaged() {
		return false;
	}

	@Override
	public int getPage() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getSize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getOffset() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Sort getSort() {
		return null;
	}

	@Override
	public Pageable first() {
		return this;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	@Override
	public Pageable next() {
		return this;
	}

	@Override
	public Pageable previousOrFirst() {
		return this;
	}
}
