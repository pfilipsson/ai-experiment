package com.ai.experiment.dto;

import java.util.List;

public class OpenApiMetadata {
    private String file;
    private String title;
    private String version;
    private int paths;
    private List<String> operations;

    public OpenApiMetadata(String file, String title, String version, int paths, List<String> operations) {
        this.file = file;
        this.title = title;
        this.version = version;
        this.paths = paths;
        this.operations = operations;
    }

    public String getFile() {
        return file;
    }

    public String getTitle() {
        return title;
    }

    public String getVersion() {
        return version;
    }

    public int getPaths() {
        return paths;
    }

    public List<String> getOperations() {
        return operations;
    }
}
