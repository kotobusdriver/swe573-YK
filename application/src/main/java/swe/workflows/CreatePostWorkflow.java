package swe.workflows;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import swe.domain.entities.PostEntity;
import swe.rest.models.CreatePostRequest;
import swe.services.PostService;

@Component
@AllArgsConstructor
public class CreatePostWorkflow implements Workflow<CreatePostRequest, PostEntity> {
  private final PostService postService;

  @Override
  public PostEntity execute(CreatePostRequest command) {
    return postService.createPost(command);
  }
}
