package com.example.individualprojectstaniel.service.Impl;

import com.example.individualprojectstaniel.model.dto.ChallengeDTO;
import com.example.individualprojectstaniel.model.entity.ChallengeEntity;
import com.example.individualprojectstaniel.repository.ChallengeRepository;
import com.example.individualprojectstaniel.service.ChallengeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeServiceImpl implements ChallengeService {


    private final ChallengeRepository challengeRepository;


    private final ModelMapper modelMapper;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository, ModelMapper modelMapper) {
        this.challengeRepository = challengeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        ChallengeEntity challengeEntity = modelMapper.map(challengeDTO, ChallengeEntity.class);
        challengeEntity = challengeRepository.save(challengeEntity);
        return modelMapper.map(challengeEntity, ChallengeDTO.class);
    }

    @Override
    public ChallengeDTO getChallengeById(Long id) {
        ChallengeEntity challengeEntity = challengeRepository.findById(id).orElse(null);
        return (challengeEntity != null) ? modelMapper.map(challengeEntity, ChallengeDTO.class) : null;
    }

    @Override
    public List<ChallengeDTO> getAllChallenges() {
        List<ChallengeEntity> challengeEntities = challengeRepository.findAll();
        return challengeEntities.stream()
                .map(challengeEntity -> modelMapper.map(challengeEntity, ChallengeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChallengeDTO updateChallenge(Long id, ChallengeDTO updatedChallengeDTO) {
        ChallengeEntity existingChallenge = challengeRepository.findById(id).orElse(null);

        if (existingChallenge != null) {
            modelMapper.map(updatedChallengeDTO, existingChallenge);
            existingChallenge = challengeRepository.save(existingChallenge);
            return modelMapper.map(existingChallenge, ChallengeDTO.class);
        }

        return null; // Challenge with the given ID not found
    }

    @Override
    public void deleteChallenge(Long id) {
        challengeRepository.deleteById(id);
    }
}