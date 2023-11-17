package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.GoalsDTO;
import com.example.individualprojectstaniel.model.entity.GoalsEntity;
import com.example.individualprojectstaniel.repository.GoalsRepository;
import com.example.individualprojectstaniel.service.GoalsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalsServiceImpl implements GoalsService {
    private final GoalsRepository goalsRepository;

   private final ModelMapper modelMapper;

    public GoalsServiceImpl(GoalsRepository goalsRepository, ModelMapper modelMapper) {
        this.goalsRepository = goalsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GoalsDTO createGoals(GoalsDTO goalsDTO) {
        GoalsEntity goalsEntity = modelMapper.map(goalsDTO, GoalsEntity.class);
        goalsEntity = goalsRepository.save(goalsEntity);

        return modelMapper.map(goalsEntity, GoalsDTO.class);
    }

    @Override
    public GoalsDTO getGoalsById(Long id) {
        GoalsEntity goalsEntity = goalsRepository.findById(id).orElse(null);

        return (goalsEntity != null) ? modelMapper.map(goalsEntity, GoalsDTO.class) : null;
    }

    @Override
    public List<GoalsDTO> getAllGoals() {
        List<GoalsEntity> goalsEntities = goalsRepository.findAll();

        return goalsEntities.stream()
                .map(goalsEntity -> modelMapper.map(goalsEntity, GoalsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public GoalsDTO updateGoals(Long id, GoalsDTO updatedGoalsDTO) {
        GoalsEntity existingGoals = goalsRepository.findById(id).orElse(null);

        if (existingGoals != null) {
            modelMapper.map(updatedGoalsDTO, existingGoals);
            existingGoals = goalsRepository.save(existingGoals);

            return modelMapper.map(existingGoals, GoalsDTO.class);
        }

        return null;
    }

    @Override
    public void deleteGoals(Long id) {
        goalsRepository.deleteById(id);
    }
}
