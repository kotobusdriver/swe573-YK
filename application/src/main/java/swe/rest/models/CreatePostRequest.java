package swe.rest.models;

import java.util.List;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequest {
  String communityId;
  String byMemberId;
  String templateId;
  @Singular List<PostFieldResource> fields;
}
