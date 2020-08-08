package com.github.miteshaghera.hackernewsapi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.miteshaghera.hackernewsapi.util.Constants;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = -5318970714445808141L;

    private final String id;

    private Integer delay;

    private Instant created;

    private Integer karma;

    private String about;

    private Long[] submitted;

    @JsonCreator
    public User(@JsonProperty("id") String id) {
        Objects.requireNonNull(id, "User.id is required.");
        this.id = id;
    }

    public User(String id, Integer delay, Instant created, Integer karma, String about, Long[] submitted) {
        this.id = id;
        this.delay = delay;
        this.created = created;
        this.karma = karma;
        this.about = about;
        this.submitted = submitted;
    }

    public String getId() {
        return id;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Integer getKarma() {
        return karma;
    }

    public void setKarma(Integer karma) {
        this.karma = karma;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long[] getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Long[] submitted) {
        this.submitted = submitted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public long calculateAgeInYears() {
        Instant now = Instant.now();
        long diffInSeconds = now.minus(created.getEpochSecond(), ChronoUnit.SECONDS).getEpochSecond();
        return diffInSeconds / Constants.SECONDS_IN_A_YEAR;
    }
}
