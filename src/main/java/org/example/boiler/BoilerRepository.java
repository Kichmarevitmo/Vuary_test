package org.example.boiler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoilerRepository extends JpaRepository<Boiler, Long> {
    List<Boiler> findByCharacteristicsTitle(String title);
    List<Boiler> findBySeriesTitle(String title);
    List<Boiler> findByTypeTitle(String title);
    @Query("SELECT DISTINCT b FROM Boiler b JOIN b.characteristics c WHERE c.title = ?1 AND c.dValue = ?2")
    List<Boiler> findByCharacteristicTitleAndDValue(String title, Double dValue);

    @Query("SELECT DISTINCT b FROM Boiler b JOIN b.characteristics c WHERE c.title = ?1 AND c.minValue = ?2")
    List<Boiler> findByCharacteristicTitleAndMinValue(String title, Double minValue);

    @Query("SELECT DISTINCT b FROM Boiler b JOIN b.characteristics c WHERE c.title = ?1 AND c.maxValue = ?2")
    List<Boiler> findByCharacteristicTitleAndMaxValue(String title, Double maxValue);

    @Query("SELECT DISTINCT b FROM Boiler b JOIN b.characteristics c WHERE c.title = ?1 AND c.sValue = ?2")
    List<Boiler> findByCharacteristicTitleAndSValue(String title, String sValue);
}
