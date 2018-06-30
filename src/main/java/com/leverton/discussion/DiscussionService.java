package com.leverton.discussion;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author anaida.gasparyan
 */
@Service
public class DiscussionService {

    private Entry discussion;

    public Entry getRootDiscussion() {
        return discussion;
    }

    public Entry addComment(String parentEntryId, @NonNull String comment) {
        // add root entry
        if (parentEntryId == null) {
            discussion = new Entry(comment);
            return discussion;
        }

        Entry rootEntry = findById(parentEntryId);
        return rootEntry.addComment(comment);
    }

    public void delete(@NonNull String id) {
        if (discussion == null){
            return;
        }

        if (discussion.getId().equals(id)) {
            discussion = null;
            return;
        }

        Entry entry = findById(id);
        entry.delete();
    }

    public Entry findById(String id) {
        if (discussion == null) {
            throw new NoFoundException();
        }
        return findById(id, discussion).orElseThrow(NoFoundException::new);
    }

    private Optional<Entry> findById(String id, Entry node) {
        if (node.getId().equals(id)) {
            return Optional.of(node);
        }
        for (Entry thread : node.getComments()) {
            Optional<Entry> result = findById(id, thread);
            if (result.isPresent()) {
                return result;
            }
        }
        return Optional.empty();
    }
}
