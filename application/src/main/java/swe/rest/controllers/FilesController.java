package swe.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swe.services.FilesStorageService;

import static swe.rest.controllers.FilesController.BASE_PATH;


@CrossOrigin
@RequestMapping(BASE_PATH)
@RestController
@AllArgsConstructor
public class FilesController {
    public static final String BASE_PATH = "/files";

    private final FilesStorageService storageService;

    @PostMapping("/{filename}")
    public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file, @PathVariable("filename") String filename) {
        storageService.save(file, filename);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> download(@PathVariable("filename") String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .body(file);
    }

}
