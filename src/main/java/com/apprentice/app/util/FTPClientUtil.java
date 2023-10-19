package com.apprentice.app.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class FTPClientUtil {
    private final String host;
    private final int port;
    private final String user;
    private final String pwd;

    public FTPClientUtil(@Value("${synology.host}") String host, @Value("${synology.port}") int port,
                         @Value("${synology.user}") String user, @Value("${synology.pwd}") String pwd) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    public String FTPUploader(MultipartFile revFile) throws SocketException, IOException {
        FTPSClient ftpClient = new FTPSClient();

        try {
            // log 남기려면 주석 해제
//            ftpClient.addProtocolCommandListener(
//                new PrintCommandListener(
//                        new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)), true));

            int reply = 0;
            ftpClient.connect(host, port);
            reply = ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            } else {
                Date now = new Date();
                SimpleDateFormat nowDateYmd = new SimpleDateFormat("yyyyMMdd");

                String nowYmd = nowDateYmd.format(now);
                String dir = "/web/blog_images/" + nowYmd;

                ftpClient.login(user, pwd);
                ftpClient.makeDirectory(dir);
                ftpClient.changeWorkingDirectory(dir);

                ftpClient.enterLocalPassiveMode();

                InputStream is = revFile.getInputStream();
                String fileName = makeFileName(revFile);

                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                boolean isSuccess = ftpClient.storeFile(fileName, is);

                if (isSuccess) {
                    log.info("업로드 성공");
                    return dir.substring(4) + "/" + fileName;
                } else {
                    return "NO_IMAGE";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.info(e.toString(), "파일이 없음.");
        } catch (SocketException e) {
            log.info(e.toString(), "소켓 오류.");
        } catch (IOException e) {
            log.info(e.toString(), "연결 오류.");
        }

        return "NO_IMAGE";
    }

    private String makeFileName(MultipartFile multipartFile) {
        // 파일 확장자 추출
        int pos = multipartFile.getOriginalFilename().lastIndexOf(".");
        String ext = multipartFile.getOriginalFilename().substring(pos + 1);

        // 서버에 올라갈 파일명 반환
        return uniqueFileName() + "." + ext;
    }

    private String uniqueFileName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
