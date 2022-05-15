package blockchainstudy.example.tastyload.User;

import blockchainstudy.example.tastyload.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception{

        mockMvc.perform(
                get("/api/test")
        ).andDo(print());
    }

    @Test
    @Transactional(readOnly=true)
    public void authenticate() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> input = new HashMap<>();
        input.put("userId", "tmddn645");
        input.put("userPw", "Asdfqwer!");

        ResultActions result = mockMvc.perform(
                post("/api/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void signup() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> input = new HashMap<>();

        input.put("userId", "tmddn641");
        input.put("userPw", "Asdfqwer!");
        input.put("userName", "유승우");
        input.put("email", "tmddn641@naver.com");
        input.put("joinedDate", "2012-02-12");

        ResultActions result = mockMvc.perform(
                        post("/api/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andExpect(status().isOk());
    }



}