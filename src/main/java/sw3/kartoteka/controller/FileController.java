package sw3.kartoteka.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import sw3.kartoteka.services.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping("/uploadImage/rekvizit/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,@PathVariable String id) {
        String fileName = fileStorageService.storeFile(file,"rekviziti",id);
        
        return ResponseEntity.ok().build();
    }

    @PostMapping("/uploadImage/rekvizitt")
    public ResponseEntity<?> uploadImagee(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file,"rekviziti","333");
        
        return ResponseEntity.ok().build();
    }

    @GetMapping("/downloadFile/{folder}/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") String fileName, HttpServletRequest request,@PathVariable("folder") String folder) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(folder,fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}