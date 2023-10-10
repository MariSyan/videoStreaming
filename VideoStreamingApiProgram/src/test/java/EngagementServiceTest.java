import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.junit4.SpringRunner;
import videoStreaming.emums.EngagementType;
import videoStreaming.entities.Engagement;
import videoStreaming.entities.Video;
import videoStreaming.repositories.EngagementRepository;
import videoStreaming.repositories.VideoRepository;
import videoStreaming.services.EngagementService;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EngagementServiceTest {

     EngagementService engagementService;

     VideoRepository videoRepository;

     EngagementRepository engagementRepository;


    @Before
    public void setUp() {
        // Create and save a test video
        Video video = new Video();
        video.setTitle("Test Video");
        video.setDirector("Test Director");
        // Set other video properties
        videoRepository.save(video);

        Engagement engagement = new Engagement();
        engagement.setVideo(video);
        engagement.setType(EngagementType.IMPRESSION);
        engagementRepository.save(engagement);
    }

    @Test
    public void testTrackEngagement() throws ChangeSetPersister.NotFoundException {
        // Create and save a video using videoRepository
        Video video = new Video();
        video.setTitle("Sample Video");
        video = videoRepository.save(video);

        // Create an engagement
        Engagement engagement = new Engagement();
        engagement.setVideo(video);
        engagement.setType(EngagementType.IMPRESSION);

        // Track the engagement
        Engagement trackedEngagement = engagementService.trackEngagement(engagement);

        // Verify that the engagement is saved
        assertNotNull(trackedEngagement.getId());
    }
}
