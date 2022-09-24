package uz.silver.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.silver.appjparelationships.entity.Address;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
