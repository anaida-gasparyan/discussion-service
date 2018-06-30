package com.leverton.discussion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author anaida.gasparyan
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    private DiscussionService discussionService;

    @GetMapping
    public Entry get() {
        return discussionService.getRootDiscussion();
    }

    @GetMapping("/{id}")
    public Entry getById(@PathVariable("id") String id) {
        return discussionService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Entry addRoot(@RequestBody String comment) {
        return discussionService.addComment(null, comment);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{parentId}")
    public Entry add(@PathVariable String parentId, @RequestBody String comment) {
        return discussionService.addComment(parentId, comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        discussionService.delete(id);
    }

    @DeleteMapping
    public void deleteRoot() {
        discussionService.delete(null);
    }


}
