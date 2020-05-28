package com.strange.diary_server.controller;

import com.strange.diary_server.entity.Todo;
import com.strange.diary_server.model.response.ListResult;
import com.strange.diary_server.model.response.SingleResult;
import com.strange.diary_server.repo.TodoRepo;
import com.strange.diary_server.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. Todo"})
@AllArgsConstructor
@RestController
@RequestMapping(value = "/")
public class TodoController {

    private final TodoRepo todoRepo;
    private final ResponseService responseService;

    @ApiOperation(value = "Todo 리스트 조회", notes = "모든 Todo List를 조회한다.")
    @GetMapping(value = "/todo")
    public ListResult<Todo> getAllTodo() {
        return responseService.getListResult(todoRepo.findAll());
    }

    @ApiOperation(value = "단일 Todo Item 조회", notes = "Id로 단일 Todo item을 조회한다.")
    @GetMapping(value = "/todo/{id}")
    public SingleResult<Todo> getTodoList(
            @ApiParam(value = "todo pk", required = true) @PathVariable Long id
    ) {
        return responseService.getSingleResult(todoRepo.findById(id).orElse(null));
    }

    @ApiOperation(value = "Todo Item 추가", notes = "새로운 Todo Item을 추가한다.")
    @PostMapping(value = "/todo")
    public SingleResult<Todo> addTodoItem(
            @ApiParam(value = "name", required = true) @RequestParam String name,
            @ApiParam(value = "content", required = true) @RequestParam String content
    ) {
        Todo todo = Todo.builder()
                .name(name)
                .content(content)
                .checked(false)
                .build();

        return responseService.getSingleResult(todoRepo.save(todo));
    }


    // Todo : 일기의 Todo 를 어떤식으로 가져올지 생각하기
    // Todo : Todo 는 diary의 uplaod date가 오늘날짜 일때만 생성하기
//    @ApiOperation(value = "diary_id로 Todo 조회", notes = "날짜를 통해 ")
//    @GetMapping(value = "/diary/todo")
//    public ListResult<Todo> getTodoWithDiaryId(
//            @ApiParam(value = "diary_id", required = true) @PathVariable Long diary_id
//    ) {
//        return responseService.getListResult(todoRepo.findById(diary_id).orElse(null));
//    }

}
