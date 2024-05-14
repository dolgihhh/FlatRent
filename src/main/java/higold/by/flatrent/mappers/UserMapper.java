package higold.by.flatrent.mappers;

import higold.by.flatrent.dto.responses.UserDTO;
import higold.by.flatrent.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "flats", ignore = true)
    @Mapping(target = "favouriteAdvertisements", ignore = true)
    User userDTOToUser(UserDTO userDTO);
}
