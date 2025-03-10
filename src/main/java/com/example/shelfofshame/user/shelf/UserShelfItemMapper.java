package com.example.shelfofshame.user.shelf;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserShelfItemMapper {
    // I need @Mapping source and target specified, but first, I need to create a BookDto
    UserShelfItemDto toUserShelfItemDto(UserShelfItem userShelfItem);
}
