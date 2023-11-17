package com.example.individualprojectstaniel.service;

import com.example.individualprojectstaniel.model.dto.ChallengeDTO;

import java.util.List;

public interface ChallengeService {
    ChallengeDTO createChallenge(ChallengeDTO challengeDTO);
    ChallengeDTO getChallengeById(Long id);
    List<ChallengeDTO> getAllChallenges();
    ChallengeDTO updateChallenge(Long id, ChallengeDTO updatedChallengeDTO);
    void deleteChallenge(Long id);
}
