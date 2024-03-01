package org.example.services;

import org.example.entities.Kind;
import org.example.repositories.KindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@Service
public class KindServiceImpl implements KindService {

    private KindRepository kindRepository;

    @Autowired
    public void setKindRepository(KindRepository kindRepository) {
        this.kindRepository = kindRepository;
    }

    @Override
    public void addAll(List<Kind> kinds) {
        if (!kinds.isEmpty()) {
            kindRepository.saveAllAndFlush(kinds);
        }
    }

    public List<Kind> getAll() {
        return kindRepository.findAll();
    }

    public List<Kind> getByTypeId(Long id) {
        return kindRepository.getKindByTypeId(id);
    }

}
