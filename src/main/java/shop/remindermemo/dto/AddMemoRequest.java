package shop.remindermemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.remindermemo.domain.Memo;

/**
 * Memo DB 저장 역할
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddMemoRequest {

    private String content;

    public Memo toEntity(String author) {
        return Memo.builder()
                .content(content)
                .author(author)
                .build();
    }
}
