package com.store.auth.repository;

import com.store.auth.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	// ✅ Total Purchased Quantity
	@Query("""
			SELECT COALESCE(SUM(p.quantity),0)
			FROM Purchase p
			WHERE p.itemId = :itemId
			""")
	Double getTotalPurchased(Long itemId);

	// ✅ Total Purchase Value (qty * price)
	@Query("""
			SELECT COALESCE(SUM(p.quantity * p.pricePerUnit),0)
			FROM Purchase p
			WHERE p.itemId = :itemId
			""")
	Double getTotalPurchaseValue(Long itemId);

	// ✅ Average Price Per Unit (NEW - Needed for Inventory)
	@Query("""
			SELECT COALESCE(AVG(p.pricePerUnit),0)
			FROM Purchase p
			WHERE p.itemId = :itemId
			""")
	Double getAveragePrice(Long itemId);

	// ✅ Last Purchase (Spring automatically adds LIMIT 1)
	Purchase findTopByItemIdOrderByPurchaseDateDesc(Long itemId);
}