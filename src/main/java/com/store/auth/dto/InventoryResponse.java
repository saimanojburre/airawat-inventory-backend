package com.store.auth.dto;

public class InventoryResponse {

	private Long itemId;
	private String itemName;
	private String units;
	private Double quantity;
	private Double avgPricePerUnit;
	private Double totalPrice;
	private String category;

	public InventoryResponse(Long itemId, String itemName, String units, Double quantity, Double avgPricePerUnit,
			Double totalPrice, String category) {

		this.itemId = itemId;
		this.itemName = itemName;
		this.units = units;
		this.quantity = quantity;
		this.avgPricePerUnit = avgPricePerUnit;
		this.totalPrice = totalPrice;
		this.category = category;
	}

	public Long getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getUnits() {
		return units;
	}

	public Double getQuantity() {
		return quantity;
	}

	public Double getAvgPricePerUnit() {
		return avgPricePerUnit;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public String getCategory() {
		return category;
	}
}