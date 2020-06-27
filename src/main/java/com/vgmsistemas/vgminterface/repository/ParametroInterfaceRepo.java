package com.vgmsistemas.vgminterface.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vgmsistemas.vgminterface.entity.ParametroInterface;

@Repository
public interface ParametroInterfaceRepo extends JpaRepository<ParametroInterface, Long>  {
    
 
}