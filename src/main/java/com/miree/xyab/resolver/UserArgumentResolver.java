package com.miree.xyab.resolver;

import com.miree.xyab.annotation.SocialUser;
import com.miree.xyab.domain.User;
import com.miree.xyab.domain.enums.SocialType;
import com.miree.xyab.domain.enums.UserType;
import com.miree.xyab.dto.UserDto;
import com.miree.xyab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.miree.xyab.domain.enums.SocialType.*;

@Component
@RequiredArgsConstructor
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(SocialUser.class) != null && parameter.getParameterType().equals(UserDto.class);
    }

    // 세션에서 인증된 user 객체를 가져옴.
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        UserDto userDto = (UserDto) session.getAttribute("user");
        return getUser(userDto, session);
    }

    // 세션에서 가져온 User 객체가 없으면 새로 생성하고 이미 있으면 바로 사용하도록 반환
    private UserDto getUser(UserDto userDto, HttpSession session) {
        if(userDto == null){
            try {
                OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                Map<String,Object> map = authentication.getPrincipal().getAttributes();
                User convertUser = convertUser(authentication.getAuthorizedClientRegistrationId(), map);

                User user = userRepository.findByEmail(convertUser.getEmail());

                if (user == null) {
                    user = userRepository.save(convertUser);
                }

                userDto = new UserDto(user);
                setRoleIfNotSame(user, authentication, map);
                session.setAttribute("user", userDto);

            } catch (ClassCastException e){
                return userDto;
            }
        }
        return userDto;
    }

    private User convertUser(String authority, Map<String, Object> map) {

        if (GOOGLE.isEquals(authority)) return getModernUser(GOOGLE, map);
        else if (FACEBOOK.isEquals(authority)) return getModernUser(FACEBOOK, map);

        return null;
    }

    private User getModernUser(SocialType socialType, Map<String, Object> map) {
        return User.builder()
                .name(String.valueOf(map.get("name")))
                .email(String.valueOf(map.get("email")))
                .principal(String.valueOf(map.get("id")))
                .userType(UserType.COMMON_USER)
                .socialType(socialType)
                .build();
    }

    private void setRoleIfNotSame(User user, OAuth2AuthenticationToken authentication, Map<String, Object> map) {
        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority(user.getSocialType().getRoleType()))) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(map, "N/A", AuthorityUtils.createAuthorityList(user.getSocialType().getRoleType())));
        }
    }

}
