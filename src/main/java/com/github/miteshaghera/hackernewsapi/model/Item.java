package com.github.miteshaghera.hackernewsapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item implements Serializable {
    private static final long serialVersionUID = 3163239552313101196L;

    private final long id;

    private Boolean deleted;

    private ItemType type;

    private String by;

    private Instant time;

    private String text;

    private Boolean dead;

    private Long parent;

    private Long poll;

    private Long[] kids;

    private String url;

    private Long score;

    private String title;

    private Long[] parts;

    private Long descendants;

    @JsonCreator
    public Item(@JsonProperty("id") long id) {
        this.id = id;
    }

    public Item(long id, Boolean deleted, ItemType type, String by, Instant time, String text, Boolean dead, Long parent,
                Long poll, Long[] kids, String url, Long score, String title, Long[] parts, Long descendants) {
        this.id = id;
        this.deleted = deleted;
        this.type = type;
        this.by = by;
        this.time = time;
        this.text = text;
        this.dead = dead;
        this.parent = parent;
        this.poll = poll;
        this.kids = kids;
        this.url = url;
        this.score = score;
        this.title = title;
        this.parts = parts;
        this.descendants = descendants;
    }



    public long getId() {
        return id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isDead() {
        return dead;
    }

    public void setDead(Boolean dead) {
        this.dead = dead;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getPoll() {
        return poll;
    }

    public void setPoll(Long poll) {
        this.poll = poll;
    }

    public Long[] getKids() {
        return kids;
    }

    public void setKids(Long[] kids) {
        this.kids = kids;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long[] getParts() {
        return parts;
    }

    public void setParts(Long[] parts) {
        this.parts = parts;
    }

    public Long getDescendants() {
        return descendants;
    }

    public void setDescendants(Long descendants) {
        this.descendants = descendants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id == item.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public enum ItemType {
        JOB("job"), STORY("story"), COMMENT("comment"), POLL("poll"), POLL_OPT("pollopt");

        private final String type;

        ItemType(String type) {
            this.type = type;
        }

        public static Optional<ItemType> parse(String type) {
            return Arrays.stream(values())
                    .filter(t -> t.type.equals(type)).findAny();
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }
}
