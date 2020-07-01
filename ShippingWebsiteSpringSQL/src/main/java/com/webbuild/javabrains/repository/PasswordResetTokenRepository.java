package com.webbuild.javabrains.repository;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.webbuild.javabrains.model.PasswordResetToken;
import com.webbuild.javabrains.model.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
	PasswordResetToken findByToken(String token);
	PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByexpirydateLessThan(Date now);

    void deleteByexpirydateLessThan(Date now);

    @Modifying
    @Query("delete from PasswordResetToken t where t.expirydate <= ?1")
    void deleteAllExpiredSince(Date now);
}