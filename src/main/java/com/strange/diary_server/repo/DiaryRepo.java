package com.strange.diary_server.repo;

import com.strange.diary_server.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepo extends JpaRepository<Diary, Long> {
}
