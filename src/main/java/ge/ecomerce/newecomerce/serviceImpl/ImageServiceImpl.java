package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.product.Image;
import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.repository.ImageRepository;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    private static final String imageFolderBasePath = "C:/Users/t.bakradze/Desktop/image/%s/%s";

    @Override
    public byte[] downloadImage(Long imageID) {
        Image image = imageRepository.findById(imageID).orElseThrow(() -> new DataNotFoundException("Image not found"));
        Path path = Paths.get(image.getFilePath());
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteImage(Long imageID) {
        Image image = imageRepository.findById(imageID).orElseThrow(() -> new DataNotFoundException("Image not found"));
        Path path = Paths.get(image.getFilePath());
        try {
            Files.delete(path);
            imageRepository.delete(image);
            return "Image deleted successfully.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String uploadImages(List<MultipartFile> files, Long id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
            Path imageFolderPath = Paths.get
                    (String.format(imageFolderBasePath, product.getSubcategory().getName(), product.getName()));
            if (!Files.exists(imageFolderPath)) {
                Files.createDirectories(imageFolderPath);
            }
            for (MultipartFile file : files) {
                saveImage(file, imageFolderPath, product, "noMain");
            }
            return "Image uploaded successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String uploadMainImage(MultipartFile file, Long id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
            if (!product.getImages().isEmpty()) {
                for (Image image : product.getImages()) {
                    if (image.getIsMainPhoto() != null && image.getIsMainPhoto()) {
                        image.setIsMainPhoto(null);
                        imageRepository.save(image);
                    }
                }
            }
            Path imageFolderPath = Paths.get
                    (String.format(imageFolderBasePath, product.getSubcategory().getName(), product.getName()));
            if (!Files.exists(imageFolderPath)) {
                Files.createDirectories(imageFolderPath);
            }
            saveImage(file, imageFolderPath, product, "main");
            return "Main image uploaded successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveImage(MultipartFile file, Path imageFolderPath, Product product, String mainStatus) {
        try {
            UUID uuid = UUID.randomUUID();
            String name = uuid + "_" + file.getOriginalFilename().replaceAll("[^a-zA-Z0-9 .]", "");
            Path filePath = imageFolderPath.resolve(name);

            Image image = new Image(name, file.getContentType(), filePath.toString());
            image.setProduct(product);


            if (mainStatus.equals("main")) {
                image.setIsMainPhoto(true);
            }
            Set<Image> images = product.getImages();
            images.add(image);
            product.setImages(images);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            imageRepository.save(image);
            productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


