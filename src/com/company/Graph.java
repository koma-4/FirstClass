package com.company;


import java.util.*;

class Info {                                         //вспомогательный класс
	public String vertex;
	public int weight;

	public Info (String vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return vertex + weight;
	}

}

public class Graph {

	private final Map<String, Map<String, Info>> outVertexMap;
	private final Map<String, Map<String, Info>> inVertexMap;

	public Graph (Map<String, Map<String, Info>> outVertexMap, Map<String, Map<String, Info>> inVertexMap) {
		this.outVertexMap = outVertexMap;
		this.inVertexMap = inVertexMap;
	}

	public void addVertex(String vertexName){
		if (!hasVertex(vertexName)) {
			outVertexMap.put(vertexName, new HashMap<>());
			inVertexMap.put(vertexName, new HashMap<>());
		}
	}
    public boolean hasVertex(String vertexName){
		return outVertexMap.containsKey(vertexName);
	}

	public void addEdge(String vertexNameOut, String vertexNameIn, int weight){
		if (!hasVertex(vertexNameOut)) addVertex(vertexNameOut);
		if (!hasVertex(vertexNameIn)) addVertex(vertexNameIn);
		Map<String, Info> edges = outVertexMap.get(vertexNameOut);
		edges.put(vertexNameIn, new Info(vertexNameIn, weight));
		Map<String, Info> incCurves = new HashMap<>(Map.of());
		incCurves.putAll(inVertexMap.get(vertexNameIn));
		incCurves.put(vertexNameOut, new Info(vertexNameOut, weight));
		inVertexMap.put(vertexNameIn,incCurves);
	}

	public void removeVertex(String vertexName){
		outVertexMap.remove(vertexName);
		for (Map<String, Info> value : outVertexMap.values()) {
			if (value.containsKey(vertexName)) {
				while (value.containsKey(vertexName)) {
					value.remove(vertexName);
					break;
				}
			}
		}

		inVertexMap.remove(vertexName);
		for (Map<String, Info> value : inVertexMap.values()) {
			if (value.containsKey(vertexName)) {
				while (value.containsKey(vertexName)) {
					value.remove(vertexName);
					break;
				}
			}
		}
	}

	public void removeEdge(String vertexNameOut, String vertexNameIn){
		outVertexMap.get(vertexNameOut).remove(vertexNameIn);
		inVertexMap.get(vertexNameIn).remove(vertexNameOut);
	}

	public void renameVertex(String vertexName, String newVertexName){
		Map<String, Info> edges = outVertexMap.get(vertexName);
		outVertexMap.remove(vertexName);
		for (Map<String, Info> value : outVertexMap.values()) {
			if (value.containsKey(vertexName)) {
				value.get(vertexName).vertex = newVertexName;
				value.put(newVertexName, value.get(vertexName));
				value.remove(vertexName);
			}
		}
		outVertexMap.put(newVertexName, edges);

		for (Map<String, Info> value : inVertexMap.values()) {
			if (value.containsKey(vertexName)) {
				value.get(vertexName).vertex = newVertexName;
				value.put(newVertexName, value.get(vertexName));
				value.remove(vertexName);
			}
			break;
		}
	}

	public void changeWeight(String vertexNameOut, String vertexNameIn, int newWeight){
		Map<String, Info> edges = outVertexMap.get(vertexNameOut);
		edges.get(vertexNameIn).weight = newWeight;
		Map<String, Info> inEdges = inVertexMap.get(vertexNameIn);
		inEdges.get(vertexNameOut).weight = newWeight;
	}

	public List<Info> outgoingCurves(String vertexName){
		List<Info> curves = new ArrayList<>(List.of());
		curves.addAll(outVertexMap.get(vertexName).values());
		return curves;
	}

	public List<Info> incomingCurves(String vertexName) {
		List<Info> curves = new ArrayList<>(List.of());
		curves.addAll(inVertexMap.get(vertexName).values());
		return curves;
	}

	static Graph empty() {
		Map<String, Map<String, Info>> expected = new HashMap<>();
		Map<String, Map<String, Info>> inExpected = new HashMap<>();
		return new Graph(expected, inExpected);
	}

	public Map<String, Map<String, Info>> getVertexMap() {
		return outVertexMap;
	}

}

