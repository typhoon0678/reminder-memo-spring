package shop.remindermemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.remindermemo.domain.Memo;
import shop.remindermemo.dto.AddMemoRequest;
import shop.remindermemo.dto.UpdateMemoRequest;
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

    public Memo findById(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        memoRepository.deleteById(id);
    }

    @Transactional
    public Memo update(long id, UpdateMemoRequest request) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        memo.update(request.getContent());

        return memo;
    }
}
