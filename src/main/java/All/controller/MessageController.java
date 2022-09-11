package All.controller;

import All.helper.Views;
import All.model.Message;
import All.repo.MessageRepo;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepo messageRepo;

    @GetMapping
    @JsonView(Views.OnlyNeededFields.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageRepo.save(message);
    }

    @PutMapping("/{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
    }
}
