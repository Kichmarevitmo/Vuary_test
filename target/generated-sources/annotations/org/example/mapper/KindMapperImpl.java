package org.example.mapper;

import javax.annotation.processing.Generated;
import org.example.dto.KindDto;
import org.example.entities.Kind;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-01T13:25:29+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class KindMapperImpl implements KindMapper {

    @Override
    public KindDto map(Kind kind) {
        if ( kind == null ) {
            return null;
        }

        KindDto kindDto = new KindDto();

        return kindDto;
    }

    @Override
    public Kind map(KindDto dto) {
        if ( dto == null ) {
            return null;
        }

        Kind kind = new Kind();

        return kind;
    }
}
