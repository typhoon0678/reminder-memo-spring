package shop.remindermemo.dto;

import lombok.Getter;
import shop.remindermemo.domain.Memo;

@Getter
public class MemoResponse {

    private final String content;

    public MemoResponse(Memo memo) {
        this.content = memo.getContent();
    }
}
