import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    int[][] adjacencyMatrix;

    public Graph(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public boolean areAdjacentDFS(Vertex vertex1, Vertex vertex2) {
        int index1 = vertex1.index();
        int index2 = vertex2.index();
        return adjacencyMatrix[index1][index2] == 1;
    }

    public List<Vertex> getListOfAdjacentVertexesDFS(Vertex vertex) {
        return getVertices(vertex);
    }

    @NotNull
    private List<Vertex> getVertices(Vertex vertex) {
        List<Vertex> adjacentVertices = new ArrayList<>();
        int index = vertex.index();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[index][i] == 1) {
                adjacentVertices.add(new Vertex(i));
            }
        }
        return adjacentVertices;
    }

    public Graph getSpanningTreeDFS() {
        int[][] spanningTreeMatrix = new int[adjacencyMatrix.length][adjacencyMatrix.length];
        boolean[] visited = new boolean[adjacencyMatrix.length];
        dfs(0, spanningTreeMatrix, visited);
        return new Graph(spanningTreeMatrix);
    }

    private void dfs(int vertex, int[][] spanningTreeMatrix, boolean[] visited) {
        visited[vertex] = true;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                spanningTreeMatrix[vertex][i] = 1;
                spanningTreeMatrix[i][vertex] = 1;
                dfs(i, spanningTreeMatrix, visited);
            }
        }
    }

    public List<List<Vertex>> getConnectedComponentsDFS() {
        List<List<Vertex>> connectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[adjacencyMatrix.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]) {
                List<Vertex> component = new ArrayList<>();
                dfs(i, visited, component);
                connectedComponents.add(component);
            }
        }
        return connectedComponents;
    }

    private void dfs(int vertex, boolean[] visited, List<Vertex> component) {
        visited[vertex] = true;
        component.add(new Vertex(vertex));
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                dfs(i, visited, component);
            }
        }
    }

    public boolean isConnectedDFS() {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        dfs(0, visited);
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int vertex, boolean[] visited) {
        visited[vertex] = true;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                dfs(i, visited);
            }
        }
    }

    public boolean hasPathBetweenDFS(Vertex vertex1, Vertex vertex2) {
        int index1 = vertex1.index();
        int index2 = vertex2.index();
        boolean[] visited = new boolean[adjacencyMatrix.length];
        return dfsHasPath(index1, index2, visited);
    }

    private boolean dfsHasPath(int start, int end, boolean[] visited) {
        visited[start] = true;
        if (start == end) {
            return true;
        }
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[start][i] == 1 && !visited[i]) {
                if (dfsHasPath(i, end, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsCycleDFS() {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i] && dfsHasCycle(i, visited, -1)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsHasCycle(int vertex, boolean[] visited, int parent) {
        visited[vertex] = true;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                if (!visited[i]) {
                    if (dfsHasCycle(i, visited, vertex)) {
                        return true;
                    }
                } else if (i != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTreeDFS() {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        if (isDfsTree(0, visited, -1)) {
            return false;
        }
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    private boolean isDfsTree(int vertex, boolean[] visited, int parent) {
        visited[vertex] = true;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                if (!visited[i]) {
                    if (isDfsTree(i, visited, vertex)) {
                        return true;
                    }
                } else if (i != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isBipartiteDFS() {
        int[] colors = new int[adjacencyMatrix.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (colors[i] == 0 && isDfsBipartite(i, colors, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean isDfsBipartite(int vertex, int[] colors, int color) {
        colors[vertex] = color;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1) {
                if (colors[i] == color) {
                    return true;
                }
                if (colors[i] == 0 && isDfsBipartite(i, colors, -color)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int lengthOfShortestPathDFS(Vertex start, Vertex end) {
        int index1 = start.index();
        int index2 = end.index();
        boolean[] visited = new boolean[adjacencyMatrix.length];
        int[] distances = new int[adjacencyMatrix.length];
        dfsShortestPath(index1, visited, distances);
        return distances[index2];
    }

    private void dfsShortestPath(int vertex, boolean[] visited, int[] distances) {
        visited[vertex] = true;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                distances[i] = distances[vertex] + 1;
                dfsShortestPath(i, visited, distances);
            }
        }
    }

    public boolean areAdjacentBFS(Vertex vertex1, Vertex vertex2) {
        int index1 = vertex1.index();
        int index2 = vertex2.index();
        return adjacencyMatrix[index1][index2] == 1;
    }

    public List<Vertex> getListOfAdjacentVertexesBFS(Vertex vertex) {
        return getVertices(vertex);
    }

    public Graph getSpanningTreeBFS() {
        int[][] spanningTreeMatrix = new int[adjacencyMatrix.length][adjacencyMatrix.length];
        boolean[] visited = new boolean[adjacencyMatrix.length];
        bfs(0, spanningTreeMatrix, visited);
        return new Graph(spanningTreeMatrix);
    }

    private void bfs(int start, int[][] spanningTreeMatrix, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                    spanningTreeMatrix[vertex][i] = 1;
                    spanningTreeMatrix[i][vertex] = 1;
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public List<List<Vertex>> getConnectedComponentsBFS() {
        List<List<Vertex>> connectedComponents = new ArrayList<>();
        boolean[] visited = new boolean[adjacencyMatrix.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i]) {
                List<Vertex> component = new ArrayList<>();
                bfs(i, visited, component);
                connectedComponents.add(component);
            }
        }
        return connectedComponents;
    }

    private void bfs(int start, boolean[] visited, List<Vertex> component) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            component.add(new Vertex(vertex));
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }


    public boolean hasPathBetweenBFS(Vertex vertex1, Vertex vertex2) {
        int index1 = vertex1.index();
        int index2 = vertex2.index();
        boolean[] visited = new boolean[adjacencyMatrix.length];
        return bfsHasPath(index1, index2, visited);
    }

    private boolean bfsHasPath(int start, int end, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            if (vertex == end) {
                return true;
            }

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        return false;
    }

    public boolean containsCycleBFS() {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        boolean[] inQueue = new boolean[adjacencyMatrix.length];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visited[i] && bfsHasCycle(i, visited, inQueue)) {
                return true;
            }
        }

        return false;
    }

    private boolean bfsHasCycle(int start, boolean[] visited, boolean[] inQueue) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        inQueue[start] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            inQueue[vertex] = false;

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertex][i] == 1) {
                    if (!visited[i]) {
                        queue.add(i);
                        visited[i] = true;
                        inQueue[i] = true;
                    } else if (inQueue[i]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isTreeBFS() {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        if (!bfsIsTree(0, visited, -1)) {
            return false;
        }

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }
    public boolean isConnectedBFS() {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        bfs(0, new int[adjacencyMatrix.length][adjacencyMatrix.length], visited);

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }

        return true;
    }


    private boolean bfsIsTree(int start, boolean[] visited, int parent) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertex][i] == 1) {
                    if (!visited[i]) {
                        queue.add(i);
                        visited[i] = true;
                    } else if (i != parent) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isBipartiteBFS() {
        int[] colors = new int[adjacencyMatrix.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (colors[i] == 0 && !bfsIsBipartite(i, colors, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean bfsIsBipartite(int start, int[] colors, int color) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        colors[start] = color;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertex][i] == 1) {
                    if (colors[i] == color) {
                        return false;
                    }

                    if (colors[i] == 0) {
                        colors[i] = -color;
                        queue.add(i);
                    }
                }
            }
        }

        return true;
    }

    public int lengthOfShortestPathBFS(Vertex start, Vertex end) {
        int index1 = start.index();
        int index2 = end.index();
        boolean[] visited = new boolean[adjacencyMatrix.length];
        int[] distances = new int[adjacencyMatrix.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(index1);
        visited[index1] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    distances[i] = distances[vertex] + 1;
                    queue.add(i);

                    if (i == index2) {
                        return distances[i];
                    }
                }
            }
        }

        return -1; // No path found
    }
}
