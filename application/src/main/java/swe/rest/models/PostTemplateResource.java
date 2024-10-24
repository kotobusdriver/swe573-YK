package swe.rest.models;

import java.util.List;
import lombok.*;
import swe.domain.entities.TemplateEntity;
import swe.domain.models.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTemplateResource {
  private String id;
  private String name;
  @Singular private List<TemplateFieldResource> fields;

  public static List<PostTemplateResource> convert(List<TemplateEntity> postTemplates) {
    return postTemplates.stream()
            .map(postTemplate -> PostTemplateResource.builder()
                    .id(postTemplate.getId())
                    .name(postTemplate.getName())
                    .fields(
                            postTemplate.getFieldDefinitions().stream()
                                    .map(
                                            f ->
                                                    TemplateFieldResource.builder()
                                                            .id(f.getId())
                                                            .name(f.getName())
                                                            .optional(f.getOptional())
                                                            .type(f.getType())
                                                            .build())
                                    .toList())
                    .build())
            .toList();
  }

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TemplateFieldResource {
    String id;
    String name;
    Boolean optional;
    FieldType type;
  }
}
