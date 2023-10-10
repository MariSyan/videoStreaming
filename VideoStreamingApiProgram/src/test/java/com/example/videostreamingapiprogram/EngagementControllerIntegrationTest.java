package com.example.videostreamingapiprogram;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import videoStreaming.emums.EngagementType;
import videoStreaming.entities.Engagement;
import videoStreaming.entities.Video;
import videoStreaming.repositories.EngagementRepository;
import videoStreaming.repositories.VideoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EngagementControllerIntegrationTest {

     TestRestTemplate restTemplate;

    VideoRepository videoRepository;

     EngagementRepository engagementRepository;


    @Before
    public void setUp() {
        Video video = new Video();
        video.setTitle("Test Video");
        video.setDirector("Test Director");
        videoRepository.save(video);

        Engagement engagement = new Engagement();
        engagement.setVideo(video);
        engagement.setType(EngagementType.IMPRESSION);
        engagementRepository.save(engagement);
    }

}

