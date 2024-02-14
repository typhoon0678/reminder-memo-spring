package shop.remindermemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import shop.remindermemo.domain.Memo;
import shop.remindermemo.dto.MemoListViewResponse;
import shop.remindermemo.dto.MemoViewResponse;
import shop.remindermemo.service.MemoService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemoViewController {

    private final MemoService memoService;

    @GetMapping("/memos")
    public String getMemos(Model model) {
        List<MemoListViewResponse> memos = memoService.findAll().stream()
                .map(MemoListViewResponse::new)
                .toList();
        model.addAttribute("memos", memos);

        return "memoList";
    }

    @GetMapping("/memos/{id}")
    public String getMemo(@PathVariable Long id, Model model) {
        Memo memo = memoService.findById(id);
        model.addAttribute("memo", new MemoViewResponse(memo));

        return "memo";
    }

    @GetMapping("/new-memo")
    public String newMemo(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("memo", new MemoViewResponse());
        } else {
            Memo memo = memoService.findById(id);
            model.addAttribute("memo", new MemoViewResponse(memo));
        }

        return "newMemo";
    }
}
