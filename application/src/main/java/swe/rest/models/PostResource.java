package swe.rest.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.entities.PostEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResource {
  String id;
  String communityId;
  String byMemberId;
  List<PostFieldResource> fields;

  public static PostResource convert(PostEntity postEntity) {
    return PostResource.builder()
        .id(postEntity.getId())
        .communityId(postEntity.getCommunity().getId())
        .byMemberId(postEntity.getMember().getId())
        .fields(
            postEntity.getFields().stream()
                .map(
                    f ->
                        PostFieldResource.builder()
                            .id(f.getId())
                            .templateFieldId(f.getFieldDefinition().getId())
                            .data(f.getData())
                            .build())
                .toList())
        .build();
  }
}
