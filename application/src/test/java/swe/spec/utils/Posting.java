package swe.spec.utils;

import swe.domain.models.FieldType;
import swe.rest.models.CommunityResource;
import swe.rest.models.PostFieldResource;
import swe.rest.models.PostTemplate;
import swe.rest.models.PostTemplateResource;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Posting {
    public static PostTemplate buildMySpecialTemplate() {
        return PostTemplate.builder()
                .field(
                        PostTemplate.TemplateField.builder()
                                .name("Title")
                                .optional(false)
                                .type(FieldType.TEXT)
                                .build())
                .field(
                        PostTemplate.TemplateField.builder()
                                .name("Picture")
                                .optional(false)
                                .type(FieldType.IMAGE)
                                .build())
                .field(
                        PostTemplate.TemplateField.builder()
                                .name("Date")
                                .optional(false)
                                .type(FieldType.DATE)
                                .build())
                .field(
                        PostTemplate.TemplateField.builder()
                                .name("Story")
                                .optional(true)
                                .type(FieldType.TEXT)
                                .build())
                .build();
    }

    public static List<PostFieldResource> createMySpecialPostFields(CommunityResource community) {
        Map<String, PostTemplateResource.TemplateFieldResource> fieldDefinitions =
                community.getPostTemplateResource().getFields().stream()
                        .collect(Collectors.toMap(f -> f.getName(), Function.identity()));

        return List.of(
                PostFieldResource.builder()
                        .templateFieldId(fieldDefinitions.get("Title").getId())
                        .data("I saw a crow")
                        .build(),
                PostFieldResource.builder()
                        .templateFieldId(fieldDefinitions.get("Picture").getId())
                        .data("http://pictures.com/the-crow.jpg")
                        .build(),
                PostFieldResource.builder()
                        .templateFieldId(fieldDefinitions.get("Date").getId())
                        .data("2024-02-09")
                        .build());
    }
}
