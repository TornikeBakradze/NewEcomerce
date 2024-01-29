package ge.ecomerce.newecomerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    String uploadImageToFile(List<MultipartFile> file, Long id);

    byte[] downloadImage(String name);
}
