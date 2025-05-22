package com.uguimar.mediams.adapters.out.drive;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.google.api.client.http.FileContent;
import com.uguimar.mediams.domain.port.out.DrivePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@RequiredArgsConstructor
public class GoogleDriveAdapter implements DrivePort {

    private final Drive drive;

    @Override
    public String uploadFile(String name, String contentType, InputStream fileStream) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(name);

        java.io.File tempFile = convertToTempFile(fileStream, name);
        FileContent mediaContent = new FileContent(contentType, tempFile);

        File file = drive.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute();

        Permission permission = new Permission()
                .setType("anyone")
                .setRole("reader");

        drive.permissions().create(file.getId(), permission).execute();

        return file.getId();
    }

    @Override
    public String getFileUrl(String fileId) {
        return "https://drive.google.com/file/d/" + fileId + "/view";
    }

    private java.io.File convertToTempFile(InputStream inputStream, String name) throws IOException {
        java.io.File tempFile = java.io.File.createTempFile("upload-", name);
        try (OutputStream out = new FileOutputStream(tempFile)) {
            inputStream.transferTo(out);
        }
        return tempFile;
    }
}