package com.github.miteshaghera.hackernewsapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.miteshaghera.hackernewsapi.util.Constants;

import java.io.Serializable;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Story implements Serializable {
    private static final long serialVersionUID = 4072495391407322323L;

    private final long id;

    private String title;

    private String url;

    private Long score;

    private ZonedDateTime time;

    private String by;

    public Story(long id) {
        this.id = id;
    }

    public static Story of(Item item) {
        Story story = new Story(item.getId());
        story.score = item.getScore();
        story.by = item.getBy();
        story.time = ZonedDateTime.ofInstant(item.getTime(), Constants.UTC);
        story.title = item.getTitle();
        story.url = item.getUrl();
        return story;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Story story = (Story) o;

        return id == story.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
