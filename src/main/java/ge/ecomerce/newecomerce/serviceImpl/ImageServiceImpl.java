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

import javax.xml.crypto.Data;
import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Override
    public String uploadImageToFile(List<MultipartFile> files, Long id) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
            File imageFile = new File("C:/Users/t.bakradze/Desktop/image");
            String folderPath = URLDecoder.decode(String.valueOf(imageFile), StandardCharsets.UTF_8);
            for (MultipartFile file : files) {
                UUID uuid = UUID.randomUUID();
                String name = uuid + "_" + file.getOriginalFilename().replaceAll("[^a-zA-Z0-9 .]", "");
                String filePath = folderPath + File.separator + name;

                Image image = new Image(name, file.getContentType(), filePath);
                product.getImages().add(image);

                file.transferTo(new File(filePath));
                productRepository.save(product);
            }
            return "Image uploaded successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadImage(String name) {
        return new byte[0];
    }
}
