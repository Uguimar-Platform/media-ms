package com.uguimar.mediams.youtube.controller;

import com.uguimar.mediams.youtube.service.YoutubeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/youtube")
public class YoutubeDemoController {
    private final YoutubeService youtubeService;

    public YoutubeDemoController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        String url = youtubeService.uploadVideo(file);
        return ResponseEntity.ok(url);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editMetadata(@RequestParam String videoId,
                                            @RequestParam String title,
                                            @RequestParam String description) {
        String result = youtubeService.editMetadata(videoId,title,description);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{videoId}")
    public ResponseEntity<String> deleteVideo(@PathVariable String videoId) {
        boolean deleted = youtubeService.deleteVideo(videoId);
        return deleted ? ResponseEntity.ok("Video Eliminado") :
                ResponseEntity.badRequest().body("No se pudo eliminar el video0");
    }

    @GetMapping("url/{videoId}")
    public ResponseEntity<String> getVideoUrl(@PathVariable String videoId) {
        String url = youtubeService.getVideoUrl(videoId);
        return ResponseEntity.ok(url);
    }
}
