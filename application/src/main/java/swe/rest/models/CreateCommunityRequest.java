package swe.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import swe.domain.models.CommunityVisibility;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommunityRequest {
  String name;
  String description;
  String adminUserId;
  CommunityVisibility visibility;
  List<PostTemplate> templates;
}
