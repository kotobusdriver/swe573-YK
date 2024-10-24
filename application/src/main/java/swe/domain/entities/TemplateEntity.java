package swe.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "Template")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  @ManyToOne
  @JoinColumn(name = "community_id")
  private CommunityEntity community;

  private String name;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "template")
  private List<FieldDefinitionEntity> fieldDefinitions;
}
