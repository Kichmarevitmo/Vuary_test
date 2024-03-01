package org.example.mapper;

import org.example.dto.SeriesDto;
import org.example.entities.Series;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {KindMapper.class})
public interface SeriesMapper  {

    SeriesDto map(Series series);
    @InheritInverseConfiguration
    Series map(SeriesDto dto);
}
