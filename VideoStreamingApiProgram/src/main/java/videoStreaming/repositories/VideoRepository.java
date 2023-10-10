package videoStreaming.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import videoStreaming.entities.Video;

import java.util.List;

@Repository
public interface VideoRepository  extends JpaRepository<Video, Long> {

    List<Video> findByDirector(String director);

    List<Video> findByTitleContaining(String title);


}
