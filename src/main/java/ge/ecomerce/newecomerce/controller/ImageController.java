package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload/{productID}")
    public ResponseEntity<String> uploadImage(@RequestParam("image") List<MultipartFile> files,
                                              @PathVariable("productID") Long productID) {
        return new ResponseEntity<>(imageService.uploadImageToFile(files, productID), HttpStatus.OK);
    }
}
