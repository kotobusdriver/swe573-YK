package swe.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.entities.CommunityEntity;
import swe.domain.models.CommunityStatus;
import swe.domain.models.CommunityVisibility;

import java.util.List;
import java.util.Optional;

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
  List<PostTemplateResource> postTemplateResources;

  public static CommunityResource convert(CommunityEntity community) {
    return CommunityResource.builder()
        .id(community.getId())
        .name(community.getName())
        .description(community.getDescription())
        .visibility(community.getVisibility())
        .status(community.getStatus())
        .postTemplateResources(PostTemplateResource.convert(community.getTemplates()))
        .build();
  }


  public Optional<PostTemplateResource> getTemplate(String templateId) {
    return postTemplateResources.stream()
            .filter(template -> template.getId().equals(templateId))
            .findAny();
  }

  public Optional<PostTemplateResource> getTemplateByName(String templateName) {
    return postTemplateResources.stream()
            .filter(template -> template.getName().equals(templateName))
            .findAny();
  }
}
