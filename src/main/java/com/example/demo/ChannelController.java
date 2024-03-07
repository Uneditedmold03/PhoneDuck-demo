
package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/channels")
public class ChannelController {


    private final ChannelRepository channelRepository;
    private final MessageRepository messageRepository;

    public ChannelController(ChannelRepository channelRepository, MessageRepository messageRepository) {
        this.channelRepository = channelRepository;
        this.messageRepository = messageRepository;

    }


    @GetMapping
    public ResponseEntity<List<Channel>> getAllChannels() {
        List<Channel> channels = channelRepository.findAll();
        // Ger http statuskod
        return new ResponseEntity<>(channels, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Channel> createChannel(@RequestBody Channel newChannel) {
        Channel savedChannel = channelRepository.save(newChannel);

        // Ger http statuskod
        return new ResponseEntity<>(savedChannel, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateChannel(@PathVariable Long id, @RequestBody Channel updatedChannel) {
        Optional<Channel> optionalChannel = channelRepository.findById(id);
        if (!optionalChannel.isPresent()) {

            // Ger http statuskod om den inte hittar sidan
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Channel existingChannel = optionalChannel.get();
        // Uppdatera de fält i existingChannel som du vill ändra
        // ...
        channelRepository.save(existingChannel);

        // Ger http statuskod
        return new ResponseEntity<>(existingChannel, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Channel> createMessage(@PathVariable Long id, @RequestBody String messageText) {
        Optional<Channel> optionalChannel = channelRepository.findById(id);
        if (optionalChannel.isPresent()) {
            Channel channel = optionalChannel.get();
            Message message = new Message(messageText, channel);
            messageRepository.save(message);
            channel.getMessages().add(message);
            channelRepository.save(channel);
            //return ResponseEntity.ok(channel);
            return new ResponseEntity<>(channel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }




    @GetMapping("/{id}")
    public ResponseEntity<List<Message>> getMessagesInChannel(@PathVariable Long id) {
        Optional<Channel> optionalChannel = channelRepository.findById(id);
        if (optionalChannel.isPresent()) {
            Channel channel = optionalChannel.get();
            List<Message> messages = messageRepository.findByChannel(channel);
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChannel(@PathVariable Long id) {
        channelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
