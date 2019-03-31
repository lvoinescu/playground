package org.sam.playground.graph;

import java.util.*;

public class Graph {

    private final Set<Vertex> vertices;

    Graph() {
        vertices = new LinkedHashSet<>();
    }

    public void addLink(Vertex v1, Vertex v2) {
        addVertex(v1);
        addVertex(v2);

        markNeighbours(v1, v2);
    }

    // this makes sure to reach to all nodes in the graph
    public Set<Vertex> travers(Vertex start) {
        Stack<Vertex> toVisit = new Stack<>();
        Set<Vertex> visited = new LinkedHashSet<>();
        toVisit.push(start);
        visited.add(start);
        while (!toVisit.empty()) {
            Vertex node = toVisit.pop();
            for (Vertex n : node.getNeighbours()) {
                if (!visited.contains(n)) {
                    toVisit.push(n);
                    visited.add(n);
                }
            }
        }

        return visited;
    }

    public List<Vertex> recursivelyTravers(Vertex start) {
        List<Vertex> output = new ArrayList<>();
        output.add(start);
        return recursivelyTravers(start, output);
    }

    //DEPTH-First
    private List<Vertex> recursivelyTravers(Vertex node, List<Vertex> output) {
        for (Vertex n : node.getNeighbours()) {
            if (!output.contains(n)) {
                output.add(n);
                recursivelyTravers(n, output);
            }
        }

        return output;
    }

    public void findCycles(Vertex target, boolean avoidBackAndForward) {
        List<Vertex> output = new ArrayList<>();
        output.add(target);
        findCycles(target, target, output, avoidBackAndForward);
    }

    private void findCycles(Vertex node, Vertex target, List<Vertex> output, boolean avoidBackAndForward) {
        for (Vertex next : node.getNeighbours()) {
            if (pathDoesNotExist(output, next, avoidBackAndForward)) {
                output.add(next);
                if (next != target) {
                    findCycles(next, target, output, avoidBackAndForward);
                } else {
                    System.out.println(output);
                }
                //removing the last element to try next node as solution
                output.remove(output.size() - 1);
            }
        }
    }

    private boolean pathDoesNotExist(List<Vertex> output, Vertex toAdd, boolean avoidBackAndForward) {
        if (output.size() > 1) {
            Vertex beforeLast = output.get(output.size() - 2);

            boolean pathExists = false;
            for (int i = 1; i < output.size(); i++) {
                if (output.get(i).equals(toAdd)) {
                    if (output.get(i - 1).equals(output.get(output.size() - 1))) {
                        pathExists = true;
                        System.out.println("Path [" + output.get(output.size() - 1).getName()
                                + "," + toAdd.getName() + "] already exists!");
                        break;
                    }
                }
            }
            return !(avoidBackAndForward && toAdd.equals(beforeLast)) && !pathExists;
        }
        return true;
    }

    private void addVertex(Vertex v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
        }
    }

    private void markNeighbours(Vertex v1, Vertex v2) {
        if (!v1.getNeighbours().contains(v2)) {
            v1.getNeighbours().add(v2);
        }

        if (!v2.getNeighbours().contains(v1)) {
            v2.getNeighbours().add(v1);
        }
    }
}
