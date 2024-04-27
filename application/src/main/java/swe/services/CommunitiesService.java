package swe.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swe.domain.entities.CommunityEntity;
import swe.domain.models.CommunityStatus;
import swe.domain.repositories.CommunitiesRepository;
import swe.rest.models.CreateCommunityRequest;

@Service
@AllArgsConstructor
public class CommunitiesService {
    private final CommunitiesRepository communitiesRepository;

    public CommunityEntity createCommunity(CreateCommunityRequest command) {
        CommunityEntity community =
                CommunityEntity.builder()
                        .name(command.getName())
                        .description(command.getDescription())
                        .visibility(command.getVisibility())
                        .status(CommunityStatus.ACTIVE)
                        .build();
        community.setFieldDefinitions(command.getTemplate().convert(community));
        return communitiesRepository.save(community);
    }
}
