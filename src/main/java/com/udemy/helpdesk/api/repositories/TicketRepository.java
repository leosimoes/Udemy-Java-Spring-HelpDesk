package com.udemy.helpdesk.api.repositories;

import com.udemy.helpdesk.api.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {

    Page<Ticket> findByUserIdOrderByDateDesc(
            String id,
            Pageable pages
    );

    Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(
            String title,
            String status,
            String priority,
            Pageable pages
    );

    Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(
            String title,
            String status,
            String priority,
            String user_id,
            Pageable pages
    );

    Page<Ticket> findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(
            String title,
            String status,
            String priority,
            String assignedUser_id,
            Pageable pages
    );

    Page<Ticket> findByNumber(
            Integer number,
            Pageable pages
    );
}
