package swe.rest.models;

import java.util.List;
import lombok.*;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.FieldDefinitionEntity;
import swe.domain.models.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTemplate {
  @Singular private List<TemplateField> fields;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TemplateField {
    String name;
    Boolean optional;
    FieldType type;

    public FieldDefinitionEntity convert(CommunityEntity community) {
      return FieldDefinitionEntity.builder()
          .community(community)
          .name(name)
          .optional(optional)
          .type(type)
          .build();
    }
  }

  public List<FieldDefinitionEntity> convert(CommunityEntity community) {
    return fields.stream().map(f -> f.convert(community)).toList();
  }
}
