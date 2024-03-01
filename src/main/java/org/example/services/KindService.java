package org.example.services;

import org.example.entities.Kind;

import java.util.List;

public interface KindService {

    void addAll(List<Kind> kinds);

    List<Kind> getAll();

    List<Kind> getByTypeId(Long id);
}
