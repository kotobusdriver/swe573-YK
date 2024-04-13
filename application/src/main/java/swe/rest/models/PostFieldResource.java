package swe.rest.models;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.entities.FieldDefinition;
import swe.domain.entities.PostField;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostFieldResource {
  private String templateFieldId;
  // TODO: Inject actual template field information here
  private String data;

  public PostField convert(Map<String, FieldDefinition> map) {
    return PostField.builder().fieldDefinition(map.get(templateFieldId)).data(data).build();
  }
}
