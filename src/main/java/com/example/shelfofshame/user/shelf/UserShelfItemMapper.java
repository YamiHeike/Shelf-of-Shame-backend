package com.example.shelfofshame.user.shelf;

import com.example.shelfofshame.user.shelf.dto.UserShelfItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserShelfItemMapper {
    UserShelfItemDto toUserShelfItemDto(UserShelfItem userShelfItem);
}
