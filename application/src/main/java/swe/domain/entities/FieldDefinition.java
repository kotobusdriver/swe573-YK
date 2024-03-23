package swe.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import swe.domain.models.FieldType;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldDefinition {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  private String name;

  private Boolean optional;

  @Enumerated(EnumType.STRING)
  private FieldType type;

  @OneToOne private CommunityPostTemplateEntity template;
}
