package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/uploadImage/{productID}")
    public ResponseEntity<String> uploadImage(@RequestParam("image") List<MultipartFile> files,
                                              @PathVariable("productID") Long productID) {
        return new ResponseEntity<>(imageService.uploadImages(files, productID), HttpStatus.OK);
    }

    @DeleteMapping("/deleteImage/{imageID}")
    public ResponseEntity<String> uploadImage(@PathVariable("imageID") Long imageID) {
        return new ResponseEntity<>(imageService.deleteImage(imageID), HttpStatus.OK);
    }

    @PostMapping("/uploadMainImage/{productID}")
    public ResponseEntity<String> uploadMainImage(@RequestParam("image") MultipartFile file,
                                                  @PathVariable("productID") Long productID) {
        return new ResponseEntity<>(imageService.uploadMainImage(file, productID), HttpStatus.OK);
    }

    @GetMapping("/downloadImage/{imageID}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("imageID") Long imageID) {
        byte[] imageData = imageService.downloadImage(imageID);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpg"))
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
