package swe.domain.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import swe.domain.models.CommunityStatus;
import swe.domain.models.CommunityVisibility;

@Entity
@Table(name = "Community")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  private String name;

  private String description;

  @Enumerated(EnumType.STRING)
  private CommunityStatus status;

  @Enumerated(EnumType.STRING)
  private CommunityVisibility visibility;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "community")
  private List<TemplateEntity> templates;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "community")
  private List<CommunityMemberEntity> members;

  public Optional<TemplateEntity> getTemplate(String templateId) {
    return templates.stream()
            .filter(template -> template.getId().equals(templateId))
            .findAny();
  }
}
