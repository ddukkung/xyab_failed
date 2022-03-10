package com.miree.xyab;

import com.miree.xyab.domain.Board;
import com.miree.xyab.domain.User;
import com.miree.xyab.domain.enums.BoardType;
import com.miree.xyab.repository.BoardRepository;
import com.miree.xyab.repository.UserRepository;
import com.miree.xyab.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.IntStream;

import static com.miree.xyab.domain.enums.SocialType.FACEBOOK;

@SpringBootApplication
public class XyabApplication implements WebMvcConfigurer {

	@Autowired
	private UserArgumentResolver userArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}

	public static void main(String[] args) {
		SpringApplication.run(XyabApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) {
		return (args) -> {
			User user = userRepository.save(User.builder()
					.name("havi")
					.password("test")
					.email("havi@gmail.com")
					.principal("textPrincipal")
					.socialType(FACEBOOK)
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
