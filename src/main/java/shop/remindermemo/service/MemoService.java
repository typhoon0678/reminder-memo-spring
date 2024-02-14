package shop.remindermemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public Memo save(AddMemoRequest request, String userName) {
        return memoRepository.save(request.toEntity(userName));
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    public Memo findById(Long id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeMemoAuthor(memo);
        memoRepository.delete(memo);
    }

    @Transactional
    public Memo update(long id, UpdateMemoRequest request) {
        Memo memo = memoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeMemoAuthor(memo);
        memo.update(request.getContent());

        return memo;
    }

    private static void authorizeMemoAuthor(Memo memo) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!memo.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

}
