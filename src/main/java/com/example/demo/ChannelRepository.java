
package com.example.demo.repository;

import com.example.demo.Message;
import com.example.demo.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    List<Message> findByChannel(Channel channel);

}
