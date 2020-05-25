package com.strange.diary_server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title", length = 30, nullable = false)
    public String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    public String content;

    @Column(name = "weather", length = 30, nullable = true)
    public String weather;

    @Column(name = "location", length = 70, nullable = true)
    public String location;

    @Column(name = "upload_date", nullable = false)
    public LocalDateTime uploadDate;

}
