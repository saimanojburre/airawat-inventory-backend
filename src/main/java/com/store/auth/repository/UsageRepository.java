package com.store.auth.repository;

import com.store.auth.entity.Usage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsageRepository extends JpaRepository<Usage, Long> {

	// ✅ Total Used Quantity (for inventory calculation)
	@Query("""
			SELECT COALESCE(SUM(u.quantity),0)
			FROM Usage u
			WHERE u.itemId = :itemId
			""")
	Double getTotalUsed(@Param("itemId") Long itemId);
}