import java.util.List;

public class Main {
    public static void main(String[] args) {
/*        int[][] adjacencyMatrix = {
                {0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 1},
                {0, 0, 0, 1, 0}
        };*/
        int[][] adjacencyMatrix = Graph.createRandomAdjacencyMatrix(6);

        Graph graph = new Graph(adjacencyMatrix);

        Vertex vertex1 = new Vertex(0);
        Vertex vertex2 = new Vertex(1);
        Vertex vertex3 = new Vertex(2);
        Vertex vertex4 = new Vertex(3);
        Vertex vertex5 = new Vertex(4);

        System.out.println("Are vertex1 and vertex2 adjacent? " + graph.areAdjacent(vertex1, vertex2));
        System.out.println("Are vertex1 and vertex3 adjacent? " + graph.areAdjacent(vertex1, vertex3));

        System.out.println("List of adjacent vertices for vertex1: " + graph.getListOfAdjacentVertexes(vertex1));
        System.out.println("List of adjacent vertices for vertex4: " + graph.getListOfAdjacentVertexes(vertex4));

        Graph spanningTree = graph.getSpanningTree();
        System.out.println("Spanning tree: ");
        printAdjacencyMatrix(spanningTree.adjacencyMatrix);

        List<List<Vertex>> connectedComponents = graph.getConnectedComponents();
        System.out.println("Connected components: ");
        for (List<Vertex> component : connectedComponents) {
            System.out.println(component);
        }

        System.out.println("Is the graph connected? " + graph.isConnected());

        System.out.println("Does a path exist between vertex1 and vertex4? " + graph.hasPathBetween(vertex1, vertex4));
        System.out.println("Does a path exist between vertex1 and vertex5? " + graph.hasPathBetween(vertex1, vertex5));

        System.out.println("Does the graph contain a cycle? " + graph.containsCycle());

        System.out.println("Is the graph a tree? " + graph.isTree());

        System.out.println("Is the graph bipartite? " + graph.isBipartite());

        System.out.println("Length of the shortest path between vertex1 and vertex4: " + graph.lengthOfShortestPath(vertex1, vertex4));
        System.out.println("Length of the shortest path between vertex1 and vertex5: " + graph.lengthOfShortestPath(vertex1, vertex5));
    }

    private static void printAdjacencyMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}