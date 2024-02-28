package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.mapper.TypeListMapper;
import org.example.mapper.TypeMapper;
import org.example.services.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/types")
public class TypeController {

  private final TypeService typeService;
  private  TypeMapper typeMapper;
  private TypeListMapper typeListMapper;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAll(){
      Map<String, Object> response = new HashMap<>();

      response.put("data", typeListMapper.toDTOList(typeService.getAll()));
      return ResponseEntity.ok(response);
    }
}
