package com.Alura.ForoHub_Alura_CC.dataBaseRepository;

import com.Alura.ForoHub_Alura_CC.models.Course;
import com.Alura.ForoHub_Alura_CC.models.Responses;
import com.Alura.ForoHub_Alura_CC.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
//    @Query("SELECT r from Responses r WHERE r.topic=:codeTopic")
//    List<Responses> findByTopic(Topic codeTopic);
//
//    @Query(nativeQuery = true, value = """
//            SELECT r.code,
//            r.message,
//            r.topic,
//            r.creationdate,
//            r.author,
//            r.solution
//            FROM responses as r
//            WHERE r.topic = :codeTopic
//            """)
//    List<Responses> finByTopicId(Integer codeTopic);
}
