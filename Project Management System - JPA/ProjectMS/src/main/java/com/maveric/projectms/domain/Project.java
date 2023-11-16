package com.maveric.projectms.domain;


import jakarta.persistence.*;

import java.util.*;
@Table(name="JPA_Project")
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="project_id")
    private long id;
    @Column(name="project_name",nullable = false)
    private String name;
    @OneToMany(mappedBy = "projectWorkingOn")
    //@Column(name="project_members")
    private List<Employee> members;
    @ElementCollection
    private Set<String> technologies;

    public Project(String name, Set<String> technologies) {
        this.name = name;
        this.technologies = technologies;
        this.members = new ArrayList<>();
    }

    public Project(){}

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
