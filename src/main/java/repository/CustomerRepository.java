package repository;

import entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {


    List<CustomerEntity> findByName(String name);

    List<CustomerEntity> findByPhoneOrEmail(String phone, String email);

    List<CustomerEntity> findBySexAndBirthdateBetween(String nam, LocalDate after, LocalDate before);
}
