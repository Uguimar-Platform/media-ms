package com.uguimar.mediams.domain.port.out;

import java.io.IOException;
import java.io.InputStream;

public interface DrivePort {
    String uploadFile(String name, String contentType, InputStream fileStream) throws IOException;
    String getFileUrl(String fileId);
}