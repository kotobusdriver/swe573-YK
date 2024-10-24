package swe.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.TemplateEntity;
import swe.domain.models.CommunityStatus;
import swe.domain.repositories.CommunitiesRepository;
import swe.rest.models.CreateCommunityRequest;
import swe.rest.models.PostTemplate;

import java.util.List;

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
    community.setTemplates(convert(command.getTemplates(), community));
    return communitiesRepository.save(community);
  }

  private List<TemplateEntity> convert(List<PostTemplate> templates, CommunityEntity community) {
    return templates.stream().map(template -> template.convert(community)).toList();
  }
}
