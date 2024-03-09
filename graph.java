import java.util.*;

import java.io.*;

public class graph {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static List<Integer>[] adj = new ArrayList[50];
	static List<Graph>[] adj1 = new ArrayList[50];
	static boolean[] vis = new boolean[50];

	// 50 Vertices
	// maybe add one if vertices start a 1

	// List<List<Integer>> adj = new ArrayList<>();
	// unknown vertices

	static boolean[] recStack; // cycle

	public static void main(String[] args) throws IOException {

		for (int i = 1; i <= 50; i++) {
			adj[i] = new ArrayList<>(); // Initialize each vertex with an empty ArrayList
		}

	}

	static void dijkstra(int source) {

		int[] dist = new int[50];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[source] = 0;

		PriorityQueue<Graph> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
		priorityQueue.add(new Graph(source, 0));

		while (!priorityQueue.isEmpty()) {
			int u = priorityQueue.poll().dest;

			for (Graph neighbor : adj1[u]) {
				int v = neighbor.dest;
				int weight = neighbor.weight;

				if (dist[u] + weight < dist[v]) {
					dist[v] = dist[u] + weight;
					priorityQueue.add(new Graph(v, dist[v]));
				}
			}
		}
	}
	//calculate the smallest longest edge you must traverse to go from node 1 to node 2
	static void dijkstraMinLongestEdge(int src, int[][] dist) {
	    dist[src][src] = 0;
	    PriorityQueue<Graph> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight)); 
	    pq.add(new Graph(src, 0));

	    while (!pq.isEmpty()) {
	        Graph current = pq.poll();
	        int u = current.dest;
	        int uWeight = current.weight;

	        for (Graph neighbor : adj1[u]) {
	            int v = neighbor.dest;
	            int edgeWeight = neighbor.weight;

	            // Update the distance array with the maximum of the largest edge encountered so far
	            int maxEdge = Math.max(uWeight, edgeWeight); // Maximum of the largest edge
	            if (maxEdge < dist[src][v]) {
	                dist[src][v] = maxEdge;
	                pq.add(new Graph(v, maxEdge));
	            }
	        }
	    }
	}

	static void topSort() {
		int[] in = new int[50];
		int vertices = 7;

		for (int i = 1; i < 50; i++) {
			// read src, dest
			int src = 0;
			int dest = 0;

			in[dest]++;
		}

		// Priority if smallest first if at the same time, else normal queue
		PriorityQueue<Integer> q = new PriorityQueue<>();
		List<Integer> print = new ArrayList<>();

		for (int i = 1; i < vertices; i++) {
			if (in[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int u = q.poll();

			print.add(u);

			for (int v : adj[u]) {
				in[v]--;

				if (in[v] == 0) {
					q.add(v);
				}
			}
		}

		if (print.size() != vertices) {
			System.out.println("HAS CYCLE");
		} else {
			for (int i : print) {
				System.out.print(i + " ");
			}

		}
	}

	static void primsMST() {
		int start = 1;
        List<Graph>[] m1 = new ArrayList[50 + 1]; // Initialize your graph structure

        PriorityQueue<Graph> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        boolean[] visited = new boolean[50+1];

        int minCost = 0;

        pq.add(new Graph(start, 0));

        while (!pq.isEmpty()) {
            Graph node = pq.poll();
            int u = node.dest;

            // Skip if already visited
            if (visited[u])
                continue;

            minCost += node.weight;
            visited[u] = true;

            for (Graph neighbor : m1[u]) {
                int v = neighbor.dest;
                int weight = neighbor.weight;

                if (!visited[v]) {
                    pq.add(new Graph(v, weight));
                }
            }
        }

        System.out.println("Minimum Cost Spanning Tree: " + minCost);
    }

	static void dfs(int cur) {
		vis[cur] = true;
		for (int nxt : adj[cur]) {
			if (vis[nxt] == true) {
				// cycle = dis[cur] - dis[nxt] + 1;
			} else if (vis[nxt] == false) {
				// dis[nxt] = dis[cur] + 1;
				dfs(nxt);
			}
		}

	}
	//dijsktra with negative edges
	static void spfa(int source) {
	    int[] dist = new int[50];
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    dist[source] = 0;

	    Queue<Integer> queue = new LinkedList<>();
	    boolean[] inQueue = new boolean[50];
	    queue.add(source);
	    inQueue[source] = true;

	    while (!queue.isEmpty()) {
	        int u = queue.poll();
	        inQueue[u] = false;

	        for (Graph neighbor : adj1[u]) {
	            int v = neighbor.dest;
	            int weight = neighbor.weight;

	            if (dist[u] + weight < dist[v]) {
	                dist[v] = dist[u] + weight;
	                if (!inQueue[v]) {
	                    queue.add(v);
	                    inQueue[v] = true;
	                }
	            }
	        }
	    }
	}

	public void dfs(int v, boolean[] visited) {

		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		int cycleLength = 0;

		while (!stack.isEmpty()) {
			int current = stack.pop();
			visited[current] = true;

			for (int neighbor : adj[current]) {
				if (!visited[neighbor]) {
					stack.push(neighbor);
				} else if (neighbor != current) {
					// If the neighbor is visited and not the parent of the current node, a cycle is
					// found.
					cycleLength = stack.size(); // Length of the cycle
					// break;
				}
			}
		}
	}

	static void edge(int u, int v) {
		if (!adj[u].contains(v)) {
			adj[u].add(v);
			adj[v].add(u);
		}
	}

	static void removeEdge(int u, int v) {
		adj[u].remove(Integer.valueOf(v));
		adj[v].remove(Integer.valueOf(u));
	}

	static int degreeOfVertices(int u) {
		return adj[u].size();
	}

	static void bfs(int start, int end) {

		boolean[] visited = new boolean[adj.length];
		int[] dist = new int[adj.length];
		int[] pre = new int[adj.length]; // Array to store the predecessors
		Queue<Integer> q = new LinkedList<>();

		q.add(start);
		visited[start] = true;
		dist[start] = 0;

		while (!q.isEmpty()) {

			int curr = q.poll();

			for (Integer neighbour : adj[curr]) {
				if (!visited[neighbour]) {

					visited[neighbour] = true;
					dist[neighbour] = dist[curr] + 1;
					pre[neighbour] = curr; // Store the predecessor of the neighbour

					q.add(neighbour);
				}
			}
		}

		if (!visited[end]) {
			System.out.println("No path exists from " + start + " to " + end);
		} else {
			System.out.println("Shortest path from " + end + " to " + start + ":");
			for (int i = end; i != start; i = pre[i]) {
				System.out.print(i);
				if (i != start) {
					System.out.print(" -> ");
				}
			}

		}
	}
	/*
	 * To find the center(s) of a tree, you can use a two-step process:

1. Find the Tree's Diameter: This involves running a depth-first search (DFS) or breadth-first search (BFS) 
from an arbitrary node to find the farthest node. Then, from that farthest node, run another search to find 
the actual diameter of the tree.

2. Find the Center(s): Once you have the diameter, the center(s) of the tree are the middle node(s) or nodes 
that are equidistant from the ends of the diameter.

	 */
	/*
	public static int[] findTreeCenter(Graph graph) {
        int n = graph.vertices;
        int[] degrees = new int[n];
        Queue<Integer> leaves = new LinkedList<>();

        // Step 1: Initialize degrees of vertices and find initial leaves
        for (int i = 0; i < n; i++) {
            degrees[i] = graph.adjacencyList[i].size();
            if (degrees[i] == 1) {
                leaves.offer(i);
            }
        }

        int remainingNodes = n;
        while (remainingNodes > 2) {
            int leavesSize = leaves.size();
            remainingNodes -= leavesSize;
            for (int i = 0; i < leavesSize; i++) {
                int leaf = leaves.poll();
                for (int neighbor : graph.adjacencyList[leaf]) {
                    degrees[neighbor]--;
                    if (degrees[neighbor] == 1) {
                        leaves.offer(neighbor);
                    }
                }
            }
        }

        // Centers will be the remaining nodes in the queue
        int[] centers = new int[leaves.size()];
        int idx = 0;
        for (int center : leaves) {
            centers[idx++] = center;
        }
        return centers;
    }
    */
	
	
	//Join tree max diameter, endpoint to endpoint
	//Join tree min diameter, center to center
	
	
	/*Tree's diameter, diameter property
Thinking problems:
P1: Given a tree with N nodes, you can start from any node, walk to visit every node, and finally 
return to the start node, what's the minimum distance for the trip? 
ans = 2*E = 2* (n - 1) 

P2: Given a tree with N nodes, you start from node 1, walk to visit every node, and finally don't 
return to the start node, what's the minimum distance for the trip? 
ans = 2 * E - longest dist from start node 


P3: Given a tree with N nodes, you can start from any node, walk to visit every node, and finally 
don't return to the start node, what's the minimum distance for the trip? 
ans = 2 * E - tree's diameter 

Tree's center and Tree's radius 

Tree's center is a node u in the tree, which has the min longest distance from u 
Tree's center is always on tree's diameter path, dividing the diameter as even as possible 

Every tree must have a center, at least 1 at most 2 

Tree's radius is the longest distance starting from tree's center 

	 * 
	 * 
	 * 
	 */

	static class Graph {
		int dest, weight;

		Graph(int dest, int weight) {

			this.dest = dest;
			this.weight = weight;
		}

	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}