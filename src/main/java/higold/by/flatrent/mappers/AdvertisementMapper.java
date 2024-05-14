package higold.by.flatrent.mappers;

import higold.by.flatrent.dto.requests.AdvCreateDTO;
import higold.by.flatrent.dto.responses.AdvertisementDTO;
import higold.by.flatrent.entities.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "flat", ignore = true)
    @Mapping(target = "advStatus", ignore = true)
    @Mapping(target = "numberOfViews", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Advertisement advCreateDTOToAdv(AdvCreateDTO advCreateDTO);

    @Mapping(target = "isFavourite", ignore = true)
    AdvertisementDTO advToAdvertisementDTO(Advertisement advertisement);
}
