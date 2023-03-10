package com.example.addressbook.repository;

import com.example.addressbook.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<ApplicationUser, Long> {
}
