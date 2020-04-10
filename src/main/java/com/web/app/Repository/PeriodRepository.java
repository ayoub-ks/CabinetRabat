package com.web.app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.app.entity.Day;
import com.web.app.entity.Period;

public interface PeriodRepository extends JpaRepository<Period,Integer> {

	@Query("select a from Period a where a.etat = false and a.dayID= :dayid")
	public List<Period> findFreePeriodInDay(@Param("dayid") Day date);
}
