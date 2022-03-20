package com.miree.xyab;

import com.miree.xyab.domain.Board;
import com.miree.xyab.domain.User;
import com.miree.xyab.domain.enums.BoardType;
import com.miree.xyab.repository.BoardRepository;
import com.miree.xyab.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.IntStream;

@SpringBootApplication
public class XyabApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(XyabApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) {
		return (args) -> {
			User user = userRepository.save(User.builder()
					.userId("havi")
					.password("test")
					.build());

			IntStream.rangeClosed(1, 50).forEach(index -> boardRepository.save(Board.builder()
					.title("게시글" + index)
					.content("컨텐츠")
					.boardType(BoardType.free)
					.user(user)
					.build())
			);
		};
	}

}
