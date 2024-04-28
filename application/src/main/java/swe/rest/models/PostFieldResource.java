package swe.rest.models;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.entities.FieldDefinitionEntity;
import swe.domain.entities.PostEntity;
import swe.domain.entities.PostFieldEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostFieldResource {
  private String id;
  private String templateFieldId;
  private String data;

  public PostFieldEntity convert(Map<String, FieldDefinitionEntity> map, PostEntity post) {
    return PostFieldEntity.builder()
            .post(post)
            .fieldDefinition(map.get(templateFieldId))
            .data(data).build();
  }
}
