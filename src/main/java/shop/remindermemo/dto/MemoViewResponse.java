package shop.remindermemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.remindermemo.domain.Memo;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class MemoViewResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String author;

    public MemoViewResponse(Memo memo) {
        this.id = memo.getId();
        this.content = memo.getContent();
        this.createdAt = memo.getCreatedAt();
        this.author = memo.getAuthor();
    }
}
