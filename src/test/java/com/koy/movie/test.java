//package com.koy.movie;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.koy.movie.movie.RecoDto;
//import com.koy.movie.movie.MovieRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Transactional
//@SpringBootTest
//@AutoConfigureMockMvc
//public class test {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    MovieRepository movieRepo;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @DisplayName("checkRecommendation")
//    @Test
//    public void checkRecommendation() throws Exception {
//        List<MovieData> movieDataList = new ArrayList<>();
//        for(long i = 1L; i <= 10L; i++) {
//            MovieData movieData = movieRepo.findById(i)
//                    .orElseThrow(() -> new IllegalArgumentException("test failed"))
//                    .toMovieData();
//
//
//            movieDataList.add(movieData);
//        }
//        RecoDto recommendationDto = RecoDto.builder()
//                .pickedMovies(movieDataList)
//                .build();
//
//        String requestBody = objectMapper.writeValueAsString(recommendationDto);
//
//        MvcResult result = mockMvc.perform(post("/api/movie/recommendation")
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print()).andReturn();
//
//        String content = result.getResponse().getContentAsString();
//
//        System.out.println(content);
//    }
//}
