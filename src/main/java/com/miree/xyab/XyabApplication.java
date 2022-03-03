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
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class XyabApplication implements WebMvcConfigurer {

	@Autowired
	private UserArgumentResolver userArgumentResolver;

	public static void main(String[] args) {
		SpringApplication.run(XyabApplication.class, args);
	}


	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) {
		return (args) -> {
			User user = userRepository.save(User.builder()
					.name("havi")
					.password("test")
					.email("havi@gmail.com")
					.createdDate(LocalDateTime.now())
					.build());

			IntStream.rangeClosed(1, 200).forEach(index ->
					boardRepository.save(Board.builder()
							.title("게시글"+index)
							.subTitle("순서"+index)
							.content("컨텐츠")
							.boardType(BoardType.free)
							.createdDate(LocalDateTime.now())
							.updatedDate(LocalDateTime.now())
							.user(user).build())
			);
		};
	}

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.addAllowedOrigin(CorsConfiguration.ALL);
			configuration.addAllowedMethod(CorsConfiguration.ALL);
			configuration.addAllowedHeader(CorsConfiguration.ALL);
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);

			http.httpBasic()
					.and().authorizeRequests()
					.anyRequest().permitAll()
					.and().cors().configurationSource(source)
					.and().csrf().disable();
		}

	}

}
