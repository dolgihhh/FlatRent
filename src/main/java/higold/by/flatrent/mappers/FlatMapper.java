package higold.by.flatrent.mappers;

import higold.by.flatrent.dto.requests.FlatCreateDTO;
import higold.by.flatrent.dto.requests.FlatUpdateDTO;
import higold.by.flatrent.dto.responses.FlatDTO;
import higold.by.flatrent.entities.Flat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlatMapper {

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "user", ignore = true)
    Flat flatCreateDTOToFlat(FlatCreateDTO flatCreateDTO);

    FlatDTO flatToFlatDTO(Flat flat);

    void updateFlatFromDTO(FlatUpdateDTO flatUpdateDTO, @MappingTarget Flat flat);
}
