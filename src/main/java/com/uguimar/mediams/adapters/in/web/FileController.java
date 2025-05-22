package com.uguimar.mediams.adapters.in.web;

import com.uguimar.mediams.domain.port.in.FileUploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadUseCase fileUploadUseCase;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Map<String, String>>> uploadFile(@RequestPart("file") FilePart filePart) {
        return DataBufferUtils.join(filePart.content())
                .map(buffer -> {
                    try (InputStream inputStream = buffer.asInputStream()) {
                        String url = fileUploadUseCase.uploadAndGetUrl(filePart.filename(),
                                filePart.headers().getContentType().toString(), inputStream);
                        return ResponseEntity.ok(Map.of("url", url));
                    } catch (IOException e) {
                        return ResponseEntity.internalServerError().body(Map.of("error", "Upload failed"));
                    }
                });
    }
}