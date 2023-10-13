package com.mavericapps.projectms.domain;

import java.util.List;

public class Project {
    int id;
    private final String name;
    private List<String> technologiesUsed;

    public Project(int id, String name, List<String> technologiesUsed) {
        this.id = id;
        this.name = name;
        this.technologiesUsed = technologiesUsed;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", technologiesUsed=" + technologiesUsed +
                '}';
    }
}
