package com.company;

import java.util.*;

class Info {                                         //вспомогательный класс
	public String vertex;
	public int weight;

	public Info (String vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
	public String getVertex() {
		return vertex;
	}

	@Override
	public String toString() {
		return vertex + weight;
	}

}

public class Graph {

	private final Map<String, List<Info>> outVertexMap;
	private final Map<String, List<Info>> inVertexMap;

	public Graph (Map<String, List<Info>> outVertexMap, Map<String, List<Info>> inVertexMap) {
		this.outVertexMap = outVertexMap;
		this.inVertexMap = inVertexMap;
	}

	public void addVertex(String vertexName){
		if (!hasVertex(vertexName)) {
			outVertexMap.put(vertexName, new ArrayList<>());
			inVertexMap.put(vertexName, new ArrayList<>());
		}
	}
    public boolean hasVertex(String vertexName){
		return outVertexMap.containsKey(vertexName);
	}

	public void addEdge(String vertexNameOut, String vertexNameIn, int weight){
		if (!hasVertex(vertexNameOut)) addVertex(vertexNameOut);
		if (!hasVertex(vertexNameIn)) addVertex(vertexNameIn);
		List<Info> edges = outVertexMap.get(vertexNameOut);
		edges.add(new Info(vertexNameIn, weight));
		List<Info> incCurves = new ArrayList<>(List.of());
		incCurves.addAll(inVertexMap.get(vertexNameIn));
		incCurves.add(new Info(vertexNameOut, weight));
		inVertexMap.put(vertexNameIn,incCurves);
	}

	public void removeVertex(String vertexName){
		outVertexMap.remove(vertexName);
		for (String key : outVertexMap.keySet()) {
			for (Info each : outVertexMap.get(key)) {
				if (each.getVertex().equals(vertexName)) {
					outVertexMap.get(key).remove(each);
					break;
				}
			}
		}
		inVertexMap.remove(vertexName);
		for (String key : inVertexMap.keySet()) {
			for (Info each : inVertexMap.get(key)) {
				if (each.getVertex().equals(vertexName)) {
					inVertexMap.get(key).remove(each);
					break;
				}
			}
		}
	}

	public void removeEdge(String vertexNameOut, String vertexNameIn){
		List<Info> edges = outVertexMap.get(vertexNameOut);
		for (Info each : edges) {
			if (each.getVertex().equals(vertexNameIn)) {
				edges.remove(each);
				outVertexMap.put(vertexNameOut,edges);
			}
		}
		List<Info> inEdges = inVertexMap.get(vertexNameIn);
		List<Info> inEdgesToRemove = new ArrayList<>(List.of());
		for (Info each : inEdges) {
			if(each.getVertex().equals(vertexNameOut)) inEdgesToRemove.add(each);
		}
		inEdges.removeAll(inEdgesToRemove);
		inVertexMap.put(vertexNameIn, inEdges);
	}

	public void renameVertex(String vertexName, String newVertexName){
		List<Info> edges = outVertexMap.get(vertexName);
		outVertexMap.remove(vertexName);
		for (List<Info> value : outVertexMap.values()) {
			for (Info each : value) {
				if (each.getVertex().equals(vertexName)) each.vertex = newVertexName;
			}
		}
		outVertexMap.put(newVertexName, edges);

		for (String key : inVertexMap.keySet()) {
			for (Info each : inVertexMap.get(key)) {
				if (each.getVertex().equals(vertexName)) {
					each.vertex = newVertexName;
					break;
				}
			}
		}
	}

	public void changeWeight(String vertexNameOut, String vertexNameIn, int newWeight){
		List<Info> edges = outVertexMap.get(vertexNameOut);
		for (Info each : edges) {
			if (each.getVertex().equals(vertexNameIn)) each.weight = newWeight;
		}
		List<Info> inEdges = inVertexMap.get(vertexNameIn);
		for (Info each : inEdges) {
			if (each.getVertex().equals(vertexNameOut)) each.weight = newWeight;
		}
	}

	public List<Info> outgoingCurves(String vertexName){
		List<Info> curves = new ArrayList<>(List.of());
		curves.addAll(outVertexMap.get(vertexName));
		return curves;
	}

	public List<Info> incomingCurves(String vertexName) {
		List<Info> curves = new ArrayList<>(List.of());
		curves.addAll(inVertexMap.get(vertexName));
		return curves;
	}

	static Graph empty() {
		Map<String, List<Info>> expected = new HashMap<>();
		Map<String, List<Info>> inExpected = new HashMap<>();
		return new Graph(expected, inExpected);
	}

	public Map<String, List<Info>> getVertexMap() {
		return outVertexMap;
	}

}

