package com.apprentice.app.api.controller;

import com.apprentice.app.util.FTPClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final FTPClientUtil ftpClientUtil;

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(@RequestParam("image") MultipartFile file) throws IOException {
        if (file.isEmpty()) return ResponseEntity.badRequest().body("파일이 존재하지 않습니다.");

        String uploadedPath = ftpClientUtil.FTPUploader(file);

        if (uploadedPath.equals("NO_IMAGE")) return ResponseEntity.badRequest().body("파일 업로드에 실패하였습니다.");
        uploadedPath = "http://jdu.iptime.org:20080".concat(uploadedPath);

        return ResponseEntity.ok().body(uploadedPath);
    }
}
