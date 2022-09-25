package ru.geekbrains.products.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.products.service.FileService;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping(value = "/{fileName}", produces = "application/octet-stream")
    public byte[] getFile(@PathVariable String fileName){
        try {
            return fileService.getFileData(fileName);
        } catch (IOException e) {
            return new byte[] {};
        }
    }
    @GetMapping("/report")
    public String[] createReport(){
        return fileService.openBook();

    }
}



















