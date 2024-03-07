// Channel.java
package com.example.demo;


import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity @Data
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL)
    private List<Message> messages;


}


