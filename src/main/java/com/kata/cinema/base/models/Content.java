package com.kata.cinema.base.models;

import com.kata.cinema.base.models.enums.TypeContent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Content {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_content_id")
    @SequenceGenerator(name = "seq_content_id", sequenceName = "SEQ_CONTENT_ID", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movies_id", insertable = false, updatable = false)
    private Movie movie;

    @Column(name = "content_url")
    private String contentUrl;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeContent typeContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id) && Objects.equals(contentUrl, content.contentUrl) && Objects.equals(typeContent, content.typeContent);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", movie=" + movie +
                ", content_url='" + contentUrl + '\'' +
                ", type='" + typeContent + '\'' +
                '}';
    }
}

