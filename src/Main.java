import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //int[][] adjacencyMatrix = Graph.createRandomAdjacencyMatrix(6);

        int[][] adjacentMatrix_W3D3Q3 = {
     //         A  B   C  D  E  F  G  H  I
                {0, 1, 1, 0, 0, 1, 0, 0, 0}, //A
                {1, 0, 0, 0, 0, 1, 0, 0, 0}, //B
                {1, 0, 0, 0, 0, 1, 1, 0, 0}, //C
                {0, 0, 0, 0, 1, 0, 0, 0, 1}, //D
                {0, 0, 0, 1, 0, 0, 0, 0, 1}, //E
                {1, 1, 1, 0, 0, 0, 0, 1, 0}, //F
                {0, 0, 1, 0, 0, 0, 0, 1, 0}, //G
                {0, 0, 0, 0, 0, 1, 1, 0, 0}, //H
                {0, 0, 0, 1, 1, 0, 0, 0, 0} //I
        };

        Graph graph = new Graph(adjacentMatrix_W3D3Q3);

        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < adjacentMatrix_W3D3Q3.length; i++) {
            vertices.add(new Vertex(i));
        }


        System.out.println("Are vertexA and vertexB adjacent? " + graph.areAdjacent(vertices.get(0), vertices.get(1)));
        System.out.println("Are vertexA and vertexD adjacent? " + graph.areAdjacent(vertices.get(0), vertices.get(3)));

        System.out.println("List of adjacent vertices for vertexA: " + graph.getListOfAdjacentVertexes(vertices.get(0)));
        System.out.println("List of adjacent vertices for vertexE: " + graph.getListOfAdjacentVertexes(vertices.get(4)));

        Graph spanningTree = graph.getSpanningTree();
        System.out.println("Spanning tree: ");
        printAdjacencyMatrix(spanningTree.adjacencyMatrix);

        List<List<Vertex>> connectedComponents = graph.getConnectedComponents();
        System.out.println("Connected components: ");
        for (List<Vertex> component : connectedComponents) {
            System.out.println(component);
        }

        System.out.println("Is the graph connected? " + graph.isConnected());

        System.out.println("Does a path exist between vertexA and vertexH? " + graph.hasPathBetween(vertices.get(0), vertices.get(7)));
        System.out.println("Does a path exist between vertexA and vertexD? " + graph.hasPathBetween(vertices.get(0), vertices.get(3)));

        System.out.println("Does the graph contain a cycle? " + graph.containsCycle());

        System.out.println("Is the graph a tree? " + graph.isTree());

        System.out.println("Is the graph bipartite? " + graph.isBipartite());

        System.out.println("Length of the shortest path between vertexB and vertexG: " + graph.lengthOfShortestPath(vertices.get(1), vertices.get(6)));
        System.out.println("Length of the shortest path between vertexA and vertexD: " + graph.lengthOfShortestPath(vertices.get(0), vertices.get(3)));
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