package swe.rest.models;

import java.util.List;
import lombok.*;
import swe.domain.entities.CommunityPostTemplateEntity;
import swe.domain.entities.FieldDefinition;
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

    public FieldDefinition convert() {
      return FieldDefinition.builder().name(name).optional(optional).type(type).build();
    }
  }

  public CommunityPostTemplateEntity convert() {
    return CommunityPostTemplateEntity.builder()
        .fieldDefinitions(fields.stream().map(PostTemplate.TemplateField::convert).toList())
        .build();
  }
}
