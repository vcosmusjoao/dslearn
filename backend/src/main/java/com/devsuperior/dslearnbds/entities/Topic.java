package com.devsuperior.dslearnbds.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_topic")
public class Topic implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    @Column( columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToMany
    @JoinTable( name = "tb_topic_likes",
                joinColumns = @JoinColumn( name = "topic_id"),
                inverseJoinColumns = @JoinColumn ( name = "user_id"))
    private Set<User> likes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Reply answer;

    @OneToMany (mappedBy = "topic")
    private List<Reply> replies = new ArrayList<>();

    public Reply getAnswer() {
        return answer;
    }


    public Lesson getLesson() {
        return lesson;
    }

    public Offer getOffer() {
        return offer;
    }


    public User getAuthor() {
        return author;
    }


    public void setAnswer(Reply answer) {
        this.answer = answer;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public Topic() {
    }


    public Topic(Long id, String title, String body, Instant moment, User author, Offer offer, Lesson lesson) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.moment = moment;
        this.author = author;
        this.offer = offer;
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Set<User> getLikes() {
        return likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id.equals(topic.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
