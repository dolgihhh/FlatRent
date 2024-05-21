package higold.by.flatrent.mappers;

import higold.by.flatrent.dto.requests.AdvCreateDTO;
import higold.by.flatrent.dto.requests.AdvUpdateDTO;
import higold.by.flatrent.dto.responses.AdvertisementDTO;
import higold.by.flatrent.entities.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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
    @Mapping(target = "flat.photos", ignore = true)
    AdvertisementDTO advToAdvertisementDTO(Advertisement advertisement);

    void updateAdvertisementFromDTO(AdvUpdateDTO advUpdateDTO,
                                    @MappingTarget Advertisement advertisement);

    void updateAdvertisementFromCreateDTO(AdvCreateDTO advCreateDTO,
                                    @MappingTarget Advertisement advertisement);
}
