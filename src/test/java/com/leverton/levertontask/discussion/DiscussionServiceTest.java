package com.leverton.levertontask.discussion;

import com.leverton.discussion.DiscussionService;
import com.leverton.discussion.Entry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author anaida.gasparyan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscussionServiceTest {

    @Autowired
    private DiscussionService service;

    @Test
    public void testAdd() {
        Entry root = service.addComment(null, "root");
        Entry a = service.addComment(root.getId(), "a");
        Entry b = service.addComment(root.getId(), "b");
        Entry c = service.addComment(root.getId(), "c");
        Entry a1 = service.addComment(a.getId(), "a1");

        assertEquals(4, root.getCount());
        assertEquals(1, a.getCount());
        assertEquals(0, a1.getCount());

    }

    @Test
    public void testDelete() {
        Entry root = service.addComment(null, "root");
        Entry a = service.addComment(root.getId(), "a");
        Entry b = service.addComment(root.getId(), "b");
        Entry c = service.addComment(root.getId(), "c");
        Entry a1 = service.addComment(a.getId(), "a1");

        service.delete(a.getId());

        assertEquals(2, root.getCount());

    }
}
