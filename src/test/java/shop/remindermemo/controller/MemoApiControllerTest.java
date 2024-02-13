package shop.remindermemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import shop.remindermemo.domain.Memo;
import shop.remindermemo.dto.AddMemoRequest;
import shop.remindermemo.dto.UpdateMemoRequest;
import shop.remindermemo.repository.MemoRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class MemoApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    MemoRepository memoRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        memoRepository.deleteAll();
    }


    @DisplayName("addMemo")
    @Test
    public void addMemo() throws Exception {
        // given
        final String url = "/api/memos";
        final String content = "content";
        final AddMemoRequest userRequest = new AddMemoRequest(content);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Memo> memos = memoRepository.findAll();

        assertThat(memos.size()).isEqualTo(1);
        assertThat(memos.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAllMemos")
    @Test
    public void findAllMemos() throws Exception {
        // given
        final String url = "/api/memos";
        final String content = "content";

        memoRepository.save(Memo.builder()
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content));
    }

    @DisplayName("findMemo")
    @Test
    public void findMemo() throws Exception {
        // given
        final String url = "/api/memos/{id}";
        final String content = "content";

        Memo savedMemo = memoRepository.save(Memo.builder()
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, savedMemo.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content));
    }

    @DisplayName("deleteArticle")
    @Test
    public void deleteArticle() throws Exception {
        // given
        final String url = "/api/memos/{id}";
        final String content = "content";

        Memo savedMemo = memoRepository.save(Memo.builder()
                .content(content)
                .build());

        // when
        mockMvc.perform(delete(url, savedMemo.getId()))
                .andExpect(status().isOk());

        // then
        List<Memo> memos = memoRepository.findAll();

        assertThat(memos).isEmpty();
    }

    @DisplayName("updateMemo")
    @Test
    public void updateMemo() throws Exception {
        // given
        final String url = "/api/memos/{id}";
        final String content = "content";

        Memo savedMemo = memoRepository.save(Memo.builder()
                .content(content)
                .build());

        final String newContent = "new content";

        UpdateMemoRequest request = new UpdateMemoRequest(newContent);

        // when
        ResultActions result = mockMvc.perform(put(url, savedMemo.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isOk());

        Memo memo = memoRepository.findById(savedMemo.getId()).get();

        assertThat(memo.getContent()).isEqualTo(newContent);
    }
}