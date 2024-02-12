package shop.remindermemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.remindermemo.domain.Memo;
import shop.remindermemo.dto.AddMemoRequest;
import shop.remindermemo.repository.MemoRepository;

import java.util.List;

/**
 * Memo 추가 로직
 */
@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public Memo save(AddMemoRequest request) {
        return memoRepository.save(request.toEntity());
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }
}
