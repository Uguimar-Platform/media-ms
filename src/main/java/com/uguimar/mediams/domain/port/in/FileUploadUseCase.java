package com.uguimar.mediams.domain.port.in;

import java.io.IOException;
import java.io.InputStream;

public interface FileUploadUseCase {
    String uploadAndGetUrl(String name, String contentType, InputStream inputStream) throws IOException;
}