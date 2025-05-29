package com.ecommerce.model;

public class Order extends Products{

	private int orderid;
	private int uid;
	private String date;
	private int quantity;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderid, int uid, String date, int quantity) {
		super();
		this.orderid = orderid;
		this.uid = uid;
		this.date = date;
		this.quantity = quantity;
	}
	
	public Order(int uid, String date, int quantity) {
		super();
		this.uid = uid;
		this.date = date;
		this.quantity = quantity;
	}
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", uid=" + uid + ", date=" + date + ", quantity=" + quantity + "]";
	}
	
	
	
	
}
