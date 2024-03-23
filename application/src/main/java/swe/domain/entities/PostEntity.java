package swe.domain.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  @ManyToOne private CommunityEntity community;

  @ManyToOne private CommunityMemberEntity member;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PostField> fields;
}
