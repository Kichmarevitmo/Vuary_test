package org.example.mapper;

import org.example.dto.TypeDto;
import org.example.entities.Type;
//import org.mapstruct.Mapper;

//@Mapper
public interface TypeMapper {

    TypeDto map(Type type);

    Type map(TypeDto dto);
}

/*
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency> */