package swe.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.entities.CommunityMemberEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityMemberResource {
  String id;
  String userId;
  String communityId;
  Boolean admin;

  public static CommunityMemberResource convert(CommunityMemberEntity member) {
    return CommunityMemberResource.builder()
        .id(member.getId())
        .userId(member.getUser().getId())
        .communityId(member.getCommunity().getId())
        .admin(member.getIsAdmin())
        .build();
  }
}
