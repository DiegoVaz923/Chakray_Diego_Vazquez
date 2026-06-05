package org.chakray.diego_vazquez.mapper;

import org.chakray.diego_vazquez.dto.response.UserResponse;
import org.chakray.diego_vazquez.entity.User;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {

        ZoneId madagascarZone = ZoneId.of("Indian/Antananarivo");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = user.getCreatedAt().atZone(ZoneId.systemDefault())
                                .withZoneSameInstant(madagascarZone)
                                .format(formatter);

        return new UserResponse(user.getId(),user.getEmail(), user.getName(),
                user.getPhone(), user.getTaxId(), formattedDate, user.getAddresses()
        );
    }
}
