package swe.domain.entities;

import jakarta.persistence.*;
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
public class CommunityMemberEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;

  private Boolean admin;

  @ManyToOne
  @JoinColumn(name = "userId")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "communityId")
  private CommunityEntity community;
}
