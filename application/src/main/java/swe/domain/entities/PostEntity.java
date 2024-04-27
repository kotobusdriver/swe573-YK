package swe.domain.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Post")
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

  @ManyToOne
  @JoinColumn(name = "community_id")
  private CommunityEntity community;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private CommunityMemberEntity member;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post")
  private List<PostFieldEntity> fields;
}
