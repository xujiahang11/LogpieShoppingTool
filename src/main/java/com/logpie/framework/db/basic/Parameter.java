package com.logpie.framework.db.basic;

public interface Parameter {

	/**
	 * 
	 * @return the key of the condition parameter
	 */
	public String getKey();

	/**
	 * 
	 * @return the operator of the condition parameter
	 */
	public String getOperator();

	/**
	 * 
	 * @return the value of the condition parameter
	 */
	public Object getValue();

	/**
	 * 
	 * @return a SQL string representation of value
	 */
	public String valueToString();

	/**
	 * 
	 * @return the table where the condition parameter belongs to
	 */
	public Table getTable();

}
