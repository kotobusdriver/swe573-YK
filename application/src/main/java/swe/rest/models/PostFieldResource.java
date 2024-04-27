package swe.rest.models;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.entities.FieldDefinitionEntity;
import swe.domain.entities.PostFieldEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostFieldResource {
  private String templateFieldId;
  // TODO: Inject actual template field information here
  private String data;

  public PostFieldEntity convert(Map<String, FieldDefinitionEntity> map) {
    return PostFieldEntity.builder().fieldDefinition(map.get(templateFieldId)).data(data).build();
  }
}
