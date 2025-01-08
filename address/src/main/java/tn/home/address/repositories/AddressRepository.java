package tn.home.address.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.home.address.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}