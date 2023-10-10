package videoStreaming.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import videoStreaming.emums.EngagementType;
import videoStreaming.entities.Engagement;
import videoStreaming.entities.Video;
import videoStreaming.model.EngagementStatistics;
import videoStreaming.repositories.EngagementRepository;
import videoStreaming.repositories.VideoRepository;

import java.util.List;

@Service
public class EngagementService {

    @Autowired
    private EngagementRepository engagementRepository;

    @Autowired
    private VideoRepository videoRepository;

    // Track a new engagement
    public Engagement trackEngagement(Engagement engagement) throws ChangeSetPersister.NotFoundException {
        Video video = videoRepository.findById(engagement.getVideo().getId()).orElse(null);

        if (video != null) {

            if (engagement.getType() == EngagementType.IMPRESSION) {
                video.incrementImpressions();
            } else if (engagement.getType() == EngagementType.VIEW) {
                video.incrementViews();
            }
            videoRepository.save(video);

            engagement.setVideo(video);
            return engagementRepository.save(engagement);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public EngagementStatistics getEngagementStatistics(Long videoId) {
        Video video = videoRepository.findById(videoId).orElse(null);
        if (video != null) {
            List<Engagement> impressions = engagementRepository.findByVideoIdAndType(videoId, EngagementType.IMPRESSION);
            List<Engagement> views = engagementRepository.findByVideoIdAndType(videoId, EngagementType.VIEW);

            EngagementStatistics statistics = new EngagementStatistics();
            statistics.setVideoId(videoId);
            statistics.setVideoTitle(video.getTitle());
            statistics.setImpressions(impressions.size());
            statistics.setViews(views.size());

            return statistics;
        }
        return null;
    }

}
