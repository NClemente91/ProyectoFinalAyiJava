package com.ayi.rest.serv.app.repositories;

import com.ayi.rest.serv.app.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
}
