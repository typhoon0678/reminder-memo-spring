package shop.remindermemo.dto;

import lombok.Getter;
import shop.remindermemo.domain.Memo;

@Getter
public class MemoListViewResponse {

    private final Long id;
    private final String content;

    public MemoListViewResponse(Memo memo) {
        this.id = memo.getId();
        this.content = memo.getContent();
    }
}
