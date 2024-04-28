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
public class CommunityMemberUserResource {
    String id;
    String userId;
    String communityId;
    String name;
    String email;
    Boolean admin;

    public static CommunityMemberUserResource convert(CommunityMemberEntity member) {
        return CommunityMemberUserResource.builder()
                .id(member.getId())
                .userId(member.getUser().getId())
                .communityId(member.getCommunity().getId())
                .name(member.getUser().getName())
                .email(member.getUser().getEmail())
                .admin(member.getIsAdmin())
                .build();
    }
}
