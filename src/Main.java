import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                {0, 0, 0, 1, 1, 0, 0, 0, 0}  //I
        };

        Graph graph = new Graph(adjacentMatrix_W3D3Q3);

        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < adjacentMatrix_W3D3Q3.length; i++) {
            vertices.add(new Vertex(i));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 0 for DFS or 1 for BFS: ");
        int choice = scanner.nextInt();
        boolean useDFS = (choice == 0);

        if (useDFS) {
            System.out.println("Using Depth-First Search (DFS):");
            System.out.println("-----------------------------");
            System.out.println("Are vertexA and vertexB adjacent? " + graph.areAdjacentDFS(vertices.get(0), vertices.get(1)));
            System.out.println("Are vertexA and vertexD adjacent? " + graph.areAdjacentDFS(vertices.get(0), vertices.get(3)));

            System.out.println("List of adjacent vertices for vertexA: " + graph.getListOfAdjacentVertexesDFS(vertices.get(0)));
            System.out.println("List of adjacent vertices for vertexE: " + graph.getListOfAdjacentVertexesDFS(vertices.get(4)));

            System.out.println("Spanning tree: ");
            printAdjacencyMatrix(graph.getSpanningTreeDFS().adjacencyMatrix);

            System.out.println("Connected components: ");
            for (List<Vertex> component : graph.getConnectedComponentsDFS()) {
                System.out.println(component);
            }

            System.out.println("Is the graph connected? " + graph.isConnectedDFS());

            System.out.println("Does a path exist between vertexA and vertexH? " + graph.hasPathBetweenDFS(vertices.get(0), vertices.get(7)));
            System.out.println("Does a path exist between vertexA and vertexD? " + graph.hasPathBetweenDFS(vertices.get(0), vertices.get(3)));

            System.out.println("Does the graph contain a cycle? " + graph.containsCycleDFS());

            System.out.println("Is the graph a tree? " + graph.isTreeDFS());

            System.out.println("Is the graph bipartite? " + graph.isBipartiteDFS());

            System.out.println("Length of the shortest path between vertexB and vertexG: " + graph.lengthOfShortestPathDFS(vertices.get(1), vertices.get(6)));
            System.out.println("Length of the shortest path between vertexA and vertexD: " + graph.lengthOfShortestPathDFS(vertices.get(0), vertices.get(3)));
        } else {
            System.out.println("Using Breadth-First Search (BFS):");
            System.out.println("-------------------------------");
            System.out.println("Are vertexA and vertexB adjacent? " + graph.areAdjacentBFS(vertices.get(0), vertices.get(1)));
            System.out.println("Are vertexA and vertexD adjacent? " + graph.areAdjacentBFS(vertices.get(0), vertices.get(3)));

            System.out.println("List of adjacent vertices for vertexA: " + graph.getListOfAdjacentVertexesBFS(vertices.get(0)));
            System.out.println("List of adjacent vertices for vertexE: " + graph.getListOfAdjacentVertexesBFS(vertices.get(4)));

            System.out.println("Spanning tree: ");
            printAdjacencyMatrix(graph.getSpanningTreeBFS().adjacencyMatrix);

            System.out.println("Connected components: ");
            for (List<Vertex> component : graph.getConnectedComponentsBFS()) {
                System.out.println(component);
            }

            System.out.println("Is the graph connected? " + graph.isConnectedBFS());

            System.out.println("Does a path exist between vertexA and vertexH? " + graph.hasPathBetweenBFS(vertices.get(0), vertices.get(7)));
            System.out.println("Does a path exist between vertexA and vertexD? " + graph.hasPathBetweenBFS(vertices.get(0), vertices.get(3)));

            System.out.println("Does the graph contain a cycle? " + graph.containsCycleBFS());

            System.out.println("Is the graph a tree? " + graph.isTreeBFS());

            System.out.println("Is the graph bipartite? " + graph.isBipartiteBFS());

            System.out.println("Length of the shortest path between vertexB and vertexG: " + graph.lengthOfShortestPathBFS(vertices.get(1), vertices.get(6)));
            System.out.println("Length of the shortest path between vertexA and vertexD: " + graph.lengthOfShortestPathBFS(vertices.get(0), vertices.get(3)));
        }
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