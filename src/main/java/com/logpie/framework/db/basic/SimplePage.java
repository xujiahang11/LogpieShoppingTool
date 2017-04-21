package com.logpie.framework.db.basic;

import java.util.Iterator;
import java.util.List;

public class SimplePage<T extends Model> implements Page<T> {

	private final Pageable pageable;
	private final List<T> contents;
	private final int total;

	/**
	 * Create a new Page with the paging information and the given content
	 * 
	 * @param pageRequest
	 *            paging information
	 * @param contents
	 *            the items of the current page, as List
	 * @param total
	 *            the total amount of items available
	 */
	public SimplePage(Pageable pageRequest, List<T> contents, int total) {
		this.pageable = pageRequest;
		this.contents = contents;
		this.total = total;
	}

	/**
	 * Create a new Page with the given content. This will result in the created
	 * Page being identical to the entire List.
	 * 
	 * @param contents
	 *            all of items
	 */
	public SimplePage(List<T> contents) {
		this(Unpaged.INSTANCE, contents, contents == null ? 0 : contents.size());
	}

	@Override
	public int getTotalPages() {
		return getSize() == 0 ? 1 : (int) Math.ceil(total / getSize());
	}

	@Override
	public int getNumber() {
		return pageable.getPage();
	}

	@Override
	public int getTotalElements() {
		return total;
	}

	@Override
	public int getNumberOfElements() {
		return contents.size();
	}

	@Override
	public int getSize() {
		return pageable.getSize();
	}

	@Override
	public List<T> getContent() {
		return contents;
	}

	@Override
	public boolean hasContent() {
		return !contents.isEmpty();
	}

	@Override
	public boolean isFirst() {
		return !pageable.hasPrevious();
	}

	@Override
	public boolean isLast() {
		return pageable == Unpaged.INSTANCE || getNumber() == getTotalPages();
	}

	@Override
	public boolean hasPrevious() {
		return getNumber() > 1;
	}

	@Override
	public boolean hasNext() {
		return getNumber() < getTotalPages();
	}

	@Override
	public Pageable previousPageable() {
		if (hasPrevious()) {
			return new PageRequest(getNumber() - 1, getSize());
		}
		return this.pageable;
	}

	@Override
	public Pageable nextPageable() {
		if (hasNext()) {
			return new PageRequest(getNumber() + 1, getSize());
		}
		return this.pageable;
	}

	@Override
	public Iterator<T> iterator() {
		return contents.iterator();
	}

}
