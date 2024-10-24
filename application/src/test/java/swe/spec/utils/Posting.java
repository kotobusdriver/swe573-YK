package swe.spec.utils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import swe.domain.models.FieldType;
import swe.rest.models.CommunityResource;
import swe.rest.models.PostFieldResource;
import swe.rest.models.PostTemplate;
import swe.rest.models.PostTemplateResource;

public class Posting {
  public static List<PostTemplate> buildMySpecialTemplate(String templateName) {
    return List.of(PostTemplate.builder()
        .name(templateName)
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
        .build());
  }

  public static List<PostTemplate> buildMySpecialMultipleTemplates(String ... templateName) {
    var template0 = PostTemplate.builder()
            .name(templateName[0])
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
    var template1 = PostTemplate.builder()
            .name(templateName[1])
            .field(
                    PostTemplate.TemplateField.builder()
                            .name("Description")
                            .optional(false)
                            .type(FieldType.TEXT)
                            .build())
            .build();
    return List.of(template0, template1);
  }

  public static List<PostFieldResource> createMySpecialPostFields(CommunityResource community, String templateId) {
    PostTemplateResource template = community.getTemplate(templateId).get();
    Map<String, PostTemplateResource.TemplateFieldResource> fieldDefinitions =
        template.getFields().stream()
            .collect(Collectors.toMap(PostTemplateResource.TemplateFieldResource::getName, Function.identity()));

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

  public static List<PostFieldResource> createMySpecialPostFieldsTemplate2(CommunityResource community, String templateId) {
    PostTemplateResource template = community.getTemplate(templateId).get();
    Map<String, PostTemplateResource.TemplateFieldResource> fieldDefinitions =
            template.getFields().stream()
                    .collect(Collectors.toMap(PostTemplateResource.TemplateFieldResource::getName, Function.identity()));

    return List.of(
            PostFieldResource.builder()
                    .templateFieldId(fieldDefinitions.get("Description").getId())
                    .data("I saw a stork!")
                    .build());
  }
}
