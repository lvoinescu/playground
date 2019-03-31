package org.sam.playground.graph;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();

        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");

        graph.addLink(a, c);
        graph.addLink(a, b);
        graph.addLink(a, d);
        graph.addLink(b, c);
        graph.addLink(c, e);
        graph.addLink(d, e);

        graph.addLink(f, c);
        graph.addLink(e, f);

        System.out.println("Nodes in graph are: " + graph.travers(a));
        graph.recursivelyTravers(a).forEach(target -> graph.findCycles(target, true));
    }
}
