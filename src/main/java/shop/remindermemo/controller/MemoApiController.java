package shop.remindermemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.remindermemo.domain.Memo;
import shop.remindermemo.dto.AddMemoRequest;
import shop.remindermemo.dto.MemoResponse;
import shop.remindermemo.service.MemoService;

import java.util.List;

/**
 * HTTP 요청을 받고, service 와 연결
 */
@RequiredArgsConstructor
@RestController
public class MemoApiController {

    private final MemoService memoService;

    @PostMapping("/api/memos")
    public ResponseEntity<Memo> addMemo(@RequestBody AddMemoRequest request) {
        Memo savedMemo = memoService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedMemo);
    }

    @GetMapping("/api/memos")
    public ResponseEntity<List<MemoResponse>> findAllMemos() {
        List<MemoResponse> memos = memoService.findAll()
                .stream()
                .map(MemoResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(memos);
    }
}
