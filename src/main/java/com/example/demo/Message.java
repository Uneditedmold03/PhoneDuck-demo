package com.example.demo;
import javax.persistence.*;


@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private com.example.demo.model.Channel channel;


}