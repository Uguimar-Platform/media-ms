package com.uguimar.mediams.application.service;

import com.uguimar.mediams.domain.port.in.FileUploadUseCase;
import com.uguimar.mediams.domain.port.out.DrivePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileUploadService implements FileUploadUseCase {

    private final DrivePort drivePort;

    @Override
    public String uploadAndGetUrl(String name, String contentType, InputStream inputStream) throws IOException {
        String fileId = drivePort.uploadFile(name, contentType, inputStream);
        return drivePort.getFileUrl(fileId);
    }
}