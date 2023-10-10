package videoStreaming.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import videoStreaming.emums.EngagementType;
import videoStreaming.entities.Engagement;

import java.util.List;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, Long> {
        List<Engagement> findByVideoIdAndType(Long videoId, EngagementType type);

        }
