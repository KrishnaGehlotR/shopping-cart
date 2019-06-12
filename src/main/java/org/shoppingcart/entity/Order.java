package org.shoppingcart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order implements Serializable {

	private static final long serialVersionUID = 8425514605540316080L;

	@Id
	@Column(name="orderId",length=50)
	private String orderId;
	private Date OrderDate;
	private int orderNum;
	private double amount;

	private String customerName;
	private String customerAddress;
	private String customerEmail;
	private String customerPhone;
}	