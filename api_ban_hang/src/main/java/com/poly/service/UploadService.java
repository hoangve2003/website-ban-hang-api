package com.poly.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface UploadService {
    File save(MultipartFile file, String folder);
}
