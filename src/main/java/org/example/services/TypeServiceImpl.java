package org.example.services;

import org.example.entities.Type;
import org.example.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    @Autowired private TypeService typeService;

    @Autowired
    public void setTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public void add(Type type) {
        if(type != null){
            typeRepository.saveAndFlush(type);
        }
    }

    @Override
    public void addAll(List<Type> types) {
        if(!types.isEmpty()){
            typeRepository.saveAllAndFlush(types);
        }
    }

    public List<Type> getAll() {return typeRepository.findAll();}

    @Override
    public Long count() {
        return typeRepository.count();
    }

    @Override
    public void deleteAll() {
        typeRepository.deleteAll();
    }
}
