/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.samplebank.repository;

import com.samplebank.entity.User;
import jakarta.validation.constraints.NotBlank;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author simiyu
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByUsername(@NotBlank String userNmae);
}
