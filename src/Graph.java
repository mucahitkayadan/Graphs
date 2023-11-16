import java.util.*;

public class Graph {
    protected int[][] adjacencyMatrix;
    private final int numVertices;

    public Graph(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.numVertices = adjacencyMatrix.length;
    }

    public boolean areAdjacent(Vertex u, Vertex v) {
        int uIndex = u.index();
        int vIndex = v.index();
        return adjacencyMatrix[uIndex][vIndex] != 0;
    }

    public List<Vertex> getListOfAdjacentVertexes(Vertex u) {
        List<Vertex> adjacentVertexes = new ArrayList<>();
        int uIndex = u.index();
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[uIndex][i] != 0) {
                adjacentVertexes.add(new Vertex(i));
            }
        }
        return adjacentVertexes;
    }

    public Graph getSpanningTree() {
        int[][] spanningTreeMatrix = new int[numVertices][numVertices];
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[0] = true;
        queue.add(0);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < numVertices; v++) {
                if (adjacencyMatrix[u][v] != 0 && !visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                    spanningTreeMatrix[u][v] = adjacencyMatrix[u][v];
                }
            }
        }

        return new Graph(spanningTreeMatrix);
    }

    public List<List<Vertex>> getConnectedComponents() {
        List<List<Vertex>> connectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                List<Vertex> component = new ArrayList<>();
                dfs(i, visited, component);
                connectedComponents.add(component);
            }
        }

        return connectedComponents;
    }

    private void dfs(int u, boolean[] visited, List<Vertex> component) {
        visited[u] = true;
        component.add(new Vertex(u));

        for (int v = 0; v < numVertices; v++) {
            if (adjacencyMatrix[u][v] != 0 && !visited[v]) {
                dfs(v, visited, component);
            }
        }
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[numVertices];
        dfs(0, visited, new ArrayList<>());

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    public boolean hasPathBetween(Vertex u, Vertex v) {
        int uIndex = u.index();
        int vIndex = v.index();
        boolean[] visited = new boolean[numVertices];
        dfs(uIndex, visited, new ArrayList<>());

        return visited[vIndex];
    }

    public boolean containsCycle() {
        boolean[] visited = new boolean[numVertices];
        boolean[] recursionStack = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            if (containsCycleUtil(i, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean containsCycleUtil(int u, boolean[] visited, boolean[] recursionStack) {
        if (!visited[u]) {
            visited[u] = true;
            recursionStack[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (adjacencyMatrix[u][v] != 0) {
                    if (!visited[v] && containsCycleUtil(v, visited, recursionStack)) {
                        return true;
                    } else if (recursionStack[v]) {
                        return true;
                    }
                }
            }
        }

        recursionStack[u] = false;
        return false;
    }

    public boolean isTree() {
        if (containsCycle()) {
            return false;
        }

        boolean[] visited = new boolean[numVertices];
        dfs(0, visited, new ArrayList<>());

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    public boolean isBipartite() {
        int[] color = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            color[i] = -1;
        }

        color[0] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < numVertices; v++) {
                if (adjacencyMatrix[u][v] != 0 && color[v] == -1) {
                    color[v] = 1 - color[u];
                    queue.add(v);
                } else if (adjacencyMatrix[u][v] != 0 && color[v] == color[u]) {
                    return false;
                }
            }
        }

        return true;
    }

    public int lengthOfShortestPath(Vertex u, Vertex v) {
        int uIndex = u.index();
        int vIndex = v.index();
        boolean[] visited = new boolean[numVertices];
        int[] distance = new int[numVertices];

        Queue<Integer> queue = new LinkedList<>();
        visited[uIndex] = true;
        queue.add(uIndex);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[curr][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    distance[i] = distance[curr] + 1;
                    queue.add(i);

                    if (i == vIndex) {
                        return distance[i];
                    }
                }
            }
        }

        return -1; // No path found
    }
    public static int[][] createRandomAdjacencyMatrix(int size) {
        int[][] adjacencyMatrix = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    adjacencyMatrix[i][j] = random.nextInt(2);
                }
            }
        }

        return adjacencyMatrix;
    }
}

