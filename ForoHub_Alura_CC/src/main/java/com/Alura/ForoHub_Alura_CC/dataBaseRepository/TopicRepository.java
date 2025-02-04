package com.Alura.ForoHub_Alura_CC.dataBaseRepository;

import com.Alura.ForoHub_Alura_CC.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("Select t from Topic t Where t.title=:paramTitle or t.message=:paramMessage")
    public Topic findTopicByTitleOrMessage(String paramTitle, String paramMessage);

    @Query("Select t from Topic t ORDER BY creationDate DESC LIMIT 10")
    public List<Topic> findLastTenRecordsByCreationDate();

    @Query(nativeQuery = true, value = """
            SELECT t.code,
            t.title,
            t.message,
            t.creation_date,
            t.status,
            t.author,
            t.course
            FROM topic as t
            INNER JOIN course c
            ON t.course = c.code
            WHERE UPPER(c.name) = :courseName
            AND YEAR(creation_date) = :year
            """)
    public List<Topic> findTopicByCourseNameAndYear(String courseName, Integer year);
}
