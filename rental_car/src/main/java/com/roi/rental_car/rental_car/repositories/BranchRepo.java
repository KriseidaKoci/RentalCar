package com.roi.rental_car.rental_car.repositories;

import com.roi.rental_car.rental_car.entities.Branch;
import com.roi.rental_car.rental_car.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


// Do te krijohet ne paketen repository Interface per secilin entitet i cili ben extend JpaRepository<Emri i Entitetit, tipi i Id>
@Repository
public interface BranchRepo extends JpaRepository<Branch, Long> {
    @Query(value = "select branch.name from Branch branch where  branch.name= : name ")
    Branch getByName(@Param("name") String branchName);
    Branch getBranchByNameAndCity_Name(String name, String cityName);// krijohet query on runtime , nga JPA
    Branch getById(@Param("id") Long id);
    boolean existsBranchByName(String name);
    Branch getBranchByName(String name);
    boolean existsBranchByNameIgnoreCase(String name);
}
