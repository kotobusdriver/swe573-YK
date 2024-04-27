package swe.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import swe.domain.models.FieldType;

@Entity
@Table(name = "Field_Definition")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldDefinitionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  @ManyToOne
  @JoinColumn(name = "community_id")
  private CommunityEntity community;

  @Enumerated(EnumType.STRING)
  private FieldType type;

  private String name;

  private Boolean optional;
}
