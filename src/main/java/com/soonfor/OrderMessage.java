package com.soonfor;

import java.io.Serializable;

public final class OrderMessage implements Serializable {

	private String order;
	private int amount;
	private boolean status;

	//默认构造函数需要反序列化json
	public OrderMessage() {
	}

	public OrderMessage(String order, int amount, boolean status) {
		this.order = order;
		this.amount = amount;
		this.status = status;
	}

	public String getOrder() {
		return order;
	}

	public int getAmount() {
		return amount;
	}

	public boolean isStatus() {
		return status;
	}


	@Override
	public String toString() {
		return "OrderMessage{" + "order='" + order + '\'' + ", amount=" + amount + ", status=" + status + '}';
	}
}
