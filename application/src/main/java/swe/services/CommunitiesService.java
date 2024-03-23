package swe.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.CommunityPostTemplateEntity;
import swe.domain.models.CommunityStatus;
import swe.domain.repositories.CommunitiesRepository;
import swe.domain.repositories.CommunityPostTemplatesRepository;
import swe.rest.models.CreateCommunityRequest;

@Service
@AllArgsConstructor
public class CommunitiesService {
  private final CommunitiesRepository communitiesRepository;
  private final CommunityPostTemplatesRepository communityPostTemplatesRepository;

  public CommunityEntity createCommunity(CreateCommunityRequest command) {
    CommunityEntity community =
        CommunityEntity.builder()
            .name(command.getName())
            .description(command.getDescription())
            .visibility(command.getVisibility())
            .status(CommunityStatus.ACTIVE)
            .postTemplate(defaultPostTemplate())
            .build();
    return communitiesRepository.save(community);
  }

  private CommunityPostTemplateEntity defaultPostTemplate() {
    return CommunityPostTemplateEntity.builder().template("template-change-me").build();
  }
}
