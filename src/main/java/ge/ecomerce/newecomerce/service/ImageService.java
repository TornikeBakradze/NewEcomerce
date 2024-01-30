package ge.ecomerce.newecomerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    String uploadImages(List<MultipartFile> file, Long id);

    String uploadMainImage(MultipartFile file, Long id);

    byte[] downloadImage(Long imageID);

    String deleteImage(Long imageID);
}
