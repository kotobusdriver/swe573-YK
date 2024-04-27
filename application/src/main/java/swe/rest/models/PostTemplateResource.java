package swe.rest.models;

import java.util.List;

import lombok.*;
import swe.domain.entities.FieldDefinitionEntity;
import swe.domain.models.FieldType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTemplateResource {
    @Singular
    private List<TemplateFieldResource> fields;

    public static PostTemplateResource convert(List<FieldDefinitionEntity> postTemplate) {
        return PostTemplateResource.builder()
                .fields(
                        postTemplate.stream()
                                .map(
                                        f ->
                                                TemplateFieldResource.builder()
                                                        .id(f.getId())
                                                        .name(f.getName())
                                                        .optional(f.getOptional())
                                                        .type(f.getType())
                                                        .build())
                                .toList())
                .build();
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
