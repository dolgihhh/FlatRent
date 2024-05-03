package higold.by.flatrent.mappers;

import higold.by.flatrent.dto.requests.FlatReqDTO;
import higold.by.flatrent.entities.Flat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FlatMapper {

    FlatReqDTO flatToFlatReqDTO(Flat flat);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Flat flatReqDTOToFlat(FlatReqDTO flatReqDTODTO);

}
