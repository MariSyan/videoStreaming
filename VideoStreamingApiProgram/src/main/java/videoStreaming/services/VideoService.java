package videoStreaming.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import videoStreaming.entities.Video;
import videoStreaming.repositories.VideoRepository;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }
    public Video updateVideo(Long id, Video updatedVideo) {
        Video existingVideo = videoRepository.findById(id).orElse(null);
        if (existingVideo != null) {
            existingVideo.setTitle(updatedVideo.getTitle());
            existingVideo.setSynopsis(updatedVideo.getSynopsis());
            existingVideo.setDirector(updatedVideo.getDirector());
            return videoRepository.save(existingVideo);
        }
        return null;
    }

    public void deleteVideo(Long id) {
        Video existingVideo = videoRepository.findById(id).orElse(null);
        if (existingVideo != null) {
            existingVideo.setDeleted(true);
            videoRepository.save(existingVideo);
        }
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public List<Video> searchVideosByDirector(String director) {
        return videoRepository.findByDirector(director);
    }


}
