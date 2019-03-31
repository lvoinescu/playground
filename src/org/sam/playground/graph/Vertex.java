package org.sam.playground.graph;


import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Vertex {

    private final String name;
    private final Set<Vertex> neighbours;

    Vertex(String name) {
        this.name = name;
        this.neighbours = new LinkedHashSet<>();
    }

    String getName() {
        return name;
    }

    public Set<Vertex> getNeighbours() {
        return neighbours;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
