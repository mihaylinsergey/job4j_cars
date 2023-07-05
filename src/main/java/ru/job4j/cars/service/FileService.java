package ru.job4j.cars.service;

import net.jcip.annotations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.model.File;
import ru.job4j.cars.repository.FileRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@ThreadSafe
@Service
public class FileService {

    private final FileRepository fileRepository;

    private final String storageDirectory;
    private static final Logger LOG = LoggerFactory.getLogger(FileService.class.getName());

    public FileService(FileRepository fileRepository,
                             @Value("${file.directory}") String storageDirectory) {
        this.fileRepository = fileRepository;
        this.storageDirectory = storageDirectory;
        createStorageDirectory(storageDirectory);
    }

    private void createStorageDirectory(String path) {
        try {
            Files.createDirectories(Path.of(path));
        } catch (IOException e) {
            LOG.error("Error!", e);
        }
    }

    public List<FileDto> getFileById(int id) {
        var file = fileRepository.findById(id);
        if (file.isEmpty()) {
            return Collections.emptyList();
        }
        return file.stream()
                .map(x -> new FileDto(x.getName(), readFileAsBytes(x.getPath())))
                .collect(Collectors.toList());
    }

    private byte[] readFileAsBytes(String path) {
        byte[] rsl = new byte[1];
        try {
            rsl = Files.readAllBytes(Path.of(path));
        } catch (IOException e) {
            LOG.error("Error!", e);
        }
        return rsl;
    }

    public File save(FileDto file) {
        var path = getFilePath(file.getName());
        writeFileBytes(path, file.getContent());
        return fileRepository.save(new File(file.getName(), path));
    }

    private String getFilePath(String name) {
        return storageDirectory
                + java.io.File.separator
                + UUID.randomUUID()
                + name;
    }

    private void writeFileBytes(String path, byte[] content) {
        try {
            Files.write(Path.of(path), content);
        } catch (IOException e) {
            LOG.error("Error!", e);
        }
    }
}
