package com.Alura.ForoHub_Alura_CC.models;

import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoCreateTopicToDatabase;
import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoCreateUserToDatabase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "topic")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private String title;
    private String message;
    @Column(name = "creacion_date")
    private LocalDateTime creationDate;

    @JoinColumn(name = "status", referencedColumnName = "code")
    @OneToOne
    private  Status status;

    @JoinColumn(name = "author", referencedColumnName = "code")
    @OneToOne
    private  User author;

    @JoinColumn(name = "course", referencedColumnName = "code")
    @OneToOne
    private  Course course;

    public Topic(DtoCreateTopicToDatabase dtoCreateTopicToDatabase)
    {
        this.title = dtoCreateTopicToDatabase.title();
        this.message = dtoCreateTopicToDatabase.message();
        this.creationDate = LocalDateTime.now();
        this.author = dtoCreateTopicToDatabase.user();
        this.course = dtoCreateTopicToDatabase.course();
        this.status = dtoCreateTopicToDatabase.status();
    }

}
