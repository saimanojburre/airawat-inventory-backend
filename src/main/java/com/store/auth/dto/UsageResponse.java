package com.store.auth.dto;

import java.time.LocalDateTime;

public class UsageResponse {

	private Long id;
	private Long itemId;
	private String itemName;
	private Double quantity;
	private String department;
	private String usedFor;
	private String givenBy;
	private String takenBy;
	private LocalDateTime usageTime;

	public UsageResponse() {
	}

	// ===== GETTERS & SETTERS =====

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUsedFor() {
		return usedFor;
	}

	public void setUsedFor(String usedFor) {
		this.usedFor = usedFor;
	}

	public String getGivenBy() {
		return givenBy;
	}

	public void setGivenBy(String givenBy) {
		this.givenBy = givenBy;
	}

	public String getTakenBy() {
		return takenBy;
	}

	public void setTakenBy(String takenBy) {
		this.takenBy = takenBy;
	}

	public LocalDateTime getUsageTime() {
		return usageTime;
	}

	public void setUsageTime(LocalDateTime usageTime) {
		this.usageTime = usageTime;
	}
}