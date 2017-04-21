package com.logpie.framework.db.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.util.Assert;

public class Sort implements Iterable<Sort.Order> {

	private static final Direction DEFAULT_DIRECTION = Direction.ASC;
	private final List<Order> orders;

	public Sort(Direction direction, String... keys) {
		Assert.notNull(keys, "Keys must not be null");
		direction = direction == null ? DEFAULT_DIRECTION : direction;

		orders = new ArrayList<>(keys.length);
		for (String key : keys) {
			Assert.hasLength(key);
			this.orders.add(new Order(direction, key));
		}
	}

	private Sort(List<Order> orders) {
		Assert.notNull(orders, "Orders must not be null");
		this.orders = new ArrayList<>(orders);
	}

	/**
	 * 
	 * @param orders
	 * @return a new Sort instance for the given order List
	 */
	public static Sort by(List<Order> orders) {
		return new Sort(orders);
	}

	/**
	 * 
	 * @param sort
	 * @return a new Sort consisting of the Sort.Order of the current Sort
	 *         combined with the given ones
	 */
	public Sort and(Sort sort) {
		if (sort == null) {
			return this;
		}
		List<Order> these = new ArrayList<>(orders);
		for (Order order : sort) {
			these.add(order);
		}
		return Sort.by(these);
	}

	/**
	 * 
	 * @param key
	 * @return the order registered for the given key
	 */
	public Sort.Order getOrderFor(String key) {
		for (Order order : orders) {
			if (order.getKey().equals(key)) {
				return order;
			}
		}
		return null;
	}

	@Override
	public Iterator<Sort.Order> iterator() {
		return this.orders.iterator();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Sort)) {
			return false;
		}

		Sort that = (Sort) obj;
		return this.orders.equals(that.orders);
	}

	@Override
	public int hashCode() {
		int res = 17;
		res = 31 * res + orders.hashCode();
		return res;
	}

	public static class Order {
		private Sort.Direction direction;
		private String key;

		public Order(Sort.Direction direction, String key) {
			this.direction = direction == null ? Direction.ASC : direction;
			this.key = key;
		}

		public Sort.Direction getDirection() {
			return direction;
		}

		public String getKey() {
			return key;
		}

		public boolean isAscending() {
			return this.direction.isAscending();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof Order)) {
				return false;
			}

			Order that = (Order) obj;
			return this.direction.equals(that.direction)
					&& this.key.equals(that.key);
		}

		@Override
		public int hashCode() {
			int res = 17;
			res = 31 * res + direction.hashCode();
			res = 31 * res + key.hashCode();
			return res;
		}

		@Override
		public String toString() {
			return key + " " + direction.toString();
		}
	}

	public static enum Direction {
		ASC, DESC;

		public boolean isAscending() {
			return this.equals(ASC);
		}

		public static Direction fromString(String value) {
			return Direction.valueOf(value.toLowerCase(Locale.US));
		}
	}

}
