package com.example.demo.repository;

import com.example.demo.model.Channel;
import com.example.demo.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChannel(Channel channel);
}
