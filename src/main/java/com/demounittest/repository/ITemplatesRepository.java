package com.demounittest.repository;

import com.demounittest.model.Templates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITemplatesRepository extends JpaRepository<Templates, Long> {
}
