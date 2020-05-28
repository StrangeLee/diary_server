package com.strange.diary_server.controller;

import com.strange.diary_server.entity.Diary;
import com.strange.diary_server.entity.Todo;
import com.strange.diary_server.model.response.ListResult;
import com.strange.diary_server.model.response.SingleResult;
import com.strange.diary_server.repo.DiaryRepo;
import com.strange.diary_server.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Api(tags = {"1. Diary"})
@AllArgsConstructor
@RestController
@RequestMapping(value = "/")
public class DiaryController {

    private final DiaryRepo diaryRepo;
    private final ResponseService responseService;

    @ApiOperation(value = "일기 리스트 조회", notes = "모든 일기 리스트를 불러온다.")
    @GetMapping(value = "/diary")
    public ListResult<Diary> findAllDiary() {
        return responseService.getListResult(diaryRepo.findAll());
    }

    @ApiOperation(value = "일기 조회", notes = "Id로 일기를 조회한다.")
    @GetMapping(value = "/diary/{id}")
    public SingleResult<Diary> findDiary(
            @ApiParam(value = "diary pk", required = true) @PathVariable Long id
    ) {
        return responseService.getSingleResult(diaryRepo.findById(id).orElse(null));
    }

    // Todo : weather 랑 location api 연구
    @ApiOperation(value = "일기 추가", notes = "새로운 일기를 추가한다.")
    @PostMapping(value = "/diary")
    public SingleResult<Diary> addDiary(
            @ApiParam(value ="title", required = true) @RequestParam String title,
            @ApiParam(value ="content", required = true) @RequestParam String content,
            @ApiParam(value ="weather", required = false) @RequestParam String weather,
            @ApiParam(value ="location", required = false) @RequestParam String location
    ) {
        final LocalDateTime now = LocalDateTime.now();

        Diary diary = Diary.builder()
                .title(title)
                .content(content)
                .weather(weather)
                .location(location)
                .uploadDate(now)
                .build();

        return responseService.getSingleResult(diaryRepo.save(diary));
    }
}
