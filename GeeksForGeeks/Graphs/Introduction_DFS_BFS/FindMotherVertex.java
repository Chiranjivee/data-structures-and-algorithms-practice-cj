import java.util.List;
import java.util.ArrayList;

class FindMotherVertex {
    public static void main(String[] args) {
        Graph graph = new Graph();
        GraphVertex v1 = new GraphVertex(1);
        GraphVertex v2 = new GraphVertex(2);
        GraphVertex v3 = new GraphVertex(3);
        GraphVertex v4 = new GraphVertex(4);
        GraphVertex v5 = new GraphVertex(5);

        graph.addNode(v1);
        graph.addNode(v2);
        graph.addNode(v3);
        graph.addNode(v4);
        graph.addNode(v5);

        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v1);
        graph.addEdge(v1, v2);
        graph.addEdge(v1, v4);
        graph.addEdge(v4, v5);

        graph.dfs();
    }
}

enum VertexState {
    UN_VISITED,
    VISITED,
    IN_PROCESS
}

class GraphVertex {
    int data;
    VertexState visited;

    List<GraphVertex> neighbours;

    public GraphVertex(int data) {
        this.data = data;
        this.visited = VertexState.UN_VISITED;
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(GraphVertex g) {
        this.neighbours.add(g);
    }
}

class Graph {

    List<GraphVertex> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(GraphVertex g) {
        this.nodes.add(g);
    }

    public void addEdge(GraphVertex g1, GraphVertex g2) {
        g1.addNeighbour(g2);
    }

    public void resetNodes() {
        for (GraphVertex node : nodes) {
            node.visited = VertexState.UN_VISITED;
        }
    }

    public void dfs() {
        resetNodes();
        for (GraphVertex node : nodes) {
            if (node.visited == VertexState.UN_VISITED) {
                dfsUtil(node);
            }
        }
    }

    private void dfsUtil(GraphVertex g) {
        if (g.visited == VertexState.VISITED) {
            return;
        }

        g.visited = VertexState.IN_PROCESS;
        System.out.print(g.data + " ");

        for (GraphVertex neighbour : g.neighbours) {
            if (g.visited == VertexState.UN_VISITED) {
                dfsUtil(neighbour);
            }  
        }

        g.visited = VertexState.VISITED;
    }
}
