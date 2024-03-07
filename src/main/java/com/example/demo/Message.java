package com.example.demo;


import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    // Konstruktor
    public Message() {
    }

    public Message(String content, Channel channel) {
        this.content = content;
        this.channel = channel;
    }


}