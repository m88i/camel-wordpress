package br.com.tecnobiz.camel.component.wordpress.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class PostStatusTest {

    @Test
    public void testFromString() {
        final String input1 = "PRIVATE";
        final String input2 = "private";

        assertThat(PostStatus.fromString(input1), is(PostStatus.private_));
        assertThat(PostStatus.fromString(input2), is(PostStatus.private_));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromStringEmpty() {
        final String input3 = "";

        assertThat(PostStatus.fromString(input3), is(PostStatus.private_));
    }

    @Test(expected = NullPointerException.class)
    public void testFromStringNull() {
        final String input4 = null;

        assertThat(PostStatus.fromString(input4), is(PostStatus.private_));
    }

}
