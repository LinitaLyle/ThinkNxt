package com.maveric.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Project {
    private long id;
    private String name;
    List<Employee> members;
    Set<String> technologies;

    public Project(long id, String name, Set<String> technologies) {
        this.id = id;
        this.name = name;
        this.technologies = technologies;
        this.members = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public List<Employee> getMembers() {
        return members;
    }

    public void setMembers(List<Employee> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Project project = (Project) object;
        return id == project.id && Objects.equals(name, project.name) && Objects.equals(members, project.members) && Objects.equals(technologies, project.technologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", technologies=" + technologies;
    }
}
