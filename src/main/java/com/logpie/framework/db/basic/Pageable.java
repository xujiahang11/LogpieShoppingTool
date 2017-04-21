package com.logpie.framework.db.basic;

/**
 * Interface for pagination information
 * 
 * @author xujiahang
 *
 */
public interface Pageable {

	/**
	 * 
	 * @return whether the current page is pageable
	 */
	public boolean isPaged();

	/**
	 * 
	 * @return the number of the current page
	 */
	public int getPage();

	/**
	 * 
	 * @return the number of elements to be returned
	 */
	public int getSize();

	/**
	 * 
	 * @return the offset to be taken according to the current page and page
	 *         size
	 */
	public int getOffset();

	/**
	 * 
	 * @return the sorting parameters
	 */
	public Sort getSort();

	/**
	 * 
	 * @return whether there's a previous Pageable we can access from the
	 *         current one
	 */
	public boolean hasPrevious();

	/**
	 * 
	 * @return the Pageable requesting the first page
	 */
	public Pageable first();

	/**
	 * 
	 * @return the Pageable requesting the next page if exists
	 */
	public Pageable next();

	/**
	 * 
	 * @return the Pageable requesting the previous page or the first page if it
	 *         is already the first one
	 */
	public Pageable previousOrFirst();

}
