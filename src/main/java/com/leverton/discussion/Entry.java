package com.leverton.discussion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author anaida.gasparyan
 */
@Data
public class Entry {

    private final String id = UUID.randomUUID().toString();

    private final String name;

    private List<Entry> comments = new ArrayList<>();

    private int count = 0;

    @JsonIgnore
    private Entry parent = null;

    public Entry addComment(String comment) {
        Entry commentThread = new Entry(comment);
        commentThread.setParent(this);
        comments.add(commentThread);

        // Update count for all parents
        Entry current = this;
        do  {
            current.count += 1;
        } while ((current = current.getParent()) != null);

        return commentThread;
    }

    public void delete(){
        this.getParent().getComments().remove(this);

        Entry current = this.getParent();
        do  {
            current.count -= this.getCount() + 1;
        } while ((current = current.getParent()) != null);
    }

}
