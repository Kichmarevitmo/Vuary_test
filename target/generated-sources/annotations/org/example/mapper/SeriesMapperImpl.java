package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.SeriesDto;
import org.example.entities.Series;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-01T13:25:29+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class SeriesMapperImpl implements SeriesMapper {

    @Override
    public SeriesDto map(Series series) {
        if ( series == null ) {
            return null;
        }

        SeriesDto seriesDto = new SeriesDto();

        seriesDto.setId( series.getId() );
        seriesDto.setDescription( series.getDescription() );
        seriesDto.setKind( series.getKind() );

        return seriesDto;
    }

    @Override
    public Series map(SeriesDto dto) {
        if ( dto == null ) {
            return null;
        }

        Series series = new Series();

        series.setId( dto.getId() );
        series.setDescription( dto.getDescription() );
        series.setKind( dto.getKind() );

        return series;
    }
}
