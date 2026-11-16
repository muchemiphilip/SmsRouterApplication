package com.kentapay.smsrouter.repository;

import com.kentapay.smsrouter.dto.SentSMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentSMSRepository extends JpaRepository<SentSMS, Long> {
}
