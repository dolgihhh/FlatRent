package higold.by.flatrent.mappers;

import higold.by.flatrent.dto.requests.FlatReqDTO;
import higold.by.flatrent.dto.responses.UserDTO;
import higold.by.flatrent.entities.Flat;
import higold.by.flatrent.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "flats", ignore = true)
    @Mapping(target = "favouriteAdvertisments", ignore = true)
    User userDTOToUser(UserDTO userDTO);
}
