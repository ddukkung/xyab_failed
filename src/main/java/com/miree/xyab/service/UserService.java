package com.miree.xyab.service;

import com.miree.xyab.domain.User;
import com.miree.xyab.dto.UserDto;
import com.miree.xyab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User toEntity(UserDto userDto) {
        return userRepository.findById(userDto.getIdx()).orElse(null);
    }
}
