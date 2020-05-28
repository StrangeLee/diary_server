package com.strange.diary_server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "weather", length = 30, nullable = true)
    private String weather;

    @Column(name = "location", length = 70, nullable = true)
    private String location;

    @Column(name = "upload_date", nullable = false)
    private LocalDateTime uploadDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diary")
    private Collection<Todo> todo;

}
