package com.logpie.framework.db.basic;

import java.util.List;

public interface Page<T extends Model> extends Iterable<T> {

	/**
	 * 
	 * @return the total number of pages
	 */
	public int getTotalPages();

	/**
	 * 
	 * @return the number of the current page
	 */
	public int getNumber();

	/**
	 * 
	 * @return the total number of elements
	 */
	public int getTotalElements();

	/**
	 * 
	 * @return the total number of elements currently on this page
	 */
	public int getNumberOfElements();

	/**
	 * 
	 * @return the size of the page
	 */
	public int getSize();

	/**
	 * 
	 * @return whether the page list is empty
	 */
	public boolean isEmpty();

	/**
	 * 
	 * @return the page content as List
	 */
	public List<T> getContent();

	/**
	 * 
	 * @return whether the page has any content
	 */
	public boolean hasContent();

	/**
	 * 
	 * @return if the current page is the first
	 */
	public boolean isFirst();

	/**
	 * 
	 * @return if the current page is the last
	 */
	public boolean isLast();

	/**
	 * 
	 * @return if the current page has a previous page
	 */
	public boolean hasPrevious();

	/**
	 * 
	 * @return if the current page has a next page
	 */
	public boolean hasNext();

	/**
	 * 
	 * @return the previous page if it has a previous one or return the first
	 *         page
	 */
	public Pageable previousPageable();

	/**
	 * 
	 * @return the next page
	 */
	public Pageable nextPageable();
}
