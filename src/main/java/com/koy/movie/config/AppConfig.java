package com.koy.movie.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public Map<Long, Long> MovieIdToTid() {
        Map<Long, Long> movieIdToTid = new HashMap<>();

        File csv = new File("C:\\Users\\50\\Desktop\\koy6204\\movie\\src\\main\\java\\com\\koy\\movie\\movieData\\links.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        boolean skipFirstLine = true;
        while (true) {
            try {
                assert br != null;
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (skipFirstLine) {
                skipFirstLine = false;
                continue;
            }

            String[] token = line.split(",");

            if(token.length > 2) {
                Long movieId = Long.parseLong(token[0]);
                Long tId = Long.parseLong(token[2]);

                movieIdToTid.put(movieId, tId);
            }
        }
        return movieIdToTid;
    }

//    @Bean
//    public Map<Long, Long> movieIdToTid() throws IOException {
//        Map<Long, Long> movieIdToTid = new HashMap<>();
//        Path path = Paths.get("{your_path}\\links.csv");
//
//        try (BufferedReader br = Files.newBufferedReader(path)) {
//            String line;
//            br.readLine(); // 첫 번째 줄(헤더) 건너뛰기
//
//            while ((line = br.readLine()) != null) {
//                String[] token = line.split(",");
//                if (token.length > 2) {
//                    Long movieId = Long.parseLong(token[0]);
//                    Long tId = Long.parseLong(token[2]);
//                    movieIdToTid.put(movieId, tId);
//                }
//            }
//        }
//        return movieIdToTid;
//    }
}
