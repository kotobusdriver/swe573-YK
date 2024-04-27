package swe.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.entities.CommunityEntity;
import swe.domain.models.CommunityStatus;
import swe.domain.models.CommunityVisibility;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityResource {
  String id;
  String name;
  String description;
  CommunityVisibility visibility;
  CommunityStatus status;
  PostTemplateResource postTemplateResource;

  public static CommunityResource convert(CommunityEntity community) {
    return CommunityResource.builder()
        .id(community.getId())
        .name(community.getName())
        .description(community.getDescription())
        .visibility(community.getVisibility())
        .status(community.getStatus())
        .postTemplateResource(PostTemplateResource.convert(community.getFieldDefinitions()))
        .build();
  }
}
