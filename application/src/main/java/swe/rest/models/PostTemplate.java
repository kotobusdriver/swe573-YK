package swe.rest.models;

import java.util.List;
import lombok.*;
import swe.domain.entities.CommunityEntity;
import swe.domain.entities.FieldDefinitionEntity;
import swe.domain.entities.TemplateEntity;
import swe.domain.models.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTemplate {
  private String name;
  @Singular private List<TemplateField> fields;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TemplateField {
    String name;
    Boolean optional;
    FieldType type;

    public FieldDefinitionEntity convert(TemplateEntity template) {
      return FieldDefinitionEntity.builder()
          .template(template)
          .name(name)
          .optional(optional)
          .type(type)
          .build();
    }
  }

  public TemplateEntity convert(CommunityEntity community) {
    TemplateEntity template = TemplateEntity.builder()
            .community(community)
            .name(name)
            .build();
    List<FieldDefinitionEntity> fieldDefinitions = fields.stream().map(f -> f.convert(template)).toList();
    template.setFieldDefinitions(fieldDefinitions);
    return template;
  }
}
