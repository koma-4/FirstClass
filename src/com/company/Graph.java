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

	private final Map<String, List<Info>> vertexMap;
	private final Map<String, Set<String>> vertexMap1;

	public Graph (Map<String, List<Info>> vertexMap, Map<String, Set<String>> vertexMap1) {
		this.vertexMap = vertexMap;
		this.vertexMap1 = vertexMap1;
	}

	public void addVertex(String vertexName){
		if (!hasVertex(vertexName)) {
			vertexMap.put(vertexName, new ArrayList<>());
			vertexMap1.put(vertexName, new HashSet<>());
		}
	}
    public boolean hasVertex(String vertexName){
		return vertexMap.containsKey(vertexName);
	}

	public void addEdge(String vertexNameOut, String vertexNameIn, int weight){
		if (!hasVertex(vertexNameOut)) addVertex(vertexNameOut);
		if (!hasVertex(vertexNameIn)) addVertex(vertexNameIn);
		List<Info> edges1 = vertexMap.get(vertexNameOut);
		edges1.add(new Info(vertexNameIn, weight));
		Set<String> incCurves = new HashSet<>(Set.of());
		var incomingCurves = vertexMap1.get(vertexNameIn);
		incCurves.addAll(incomingCurves);
		incCurves.add(vertexNameOut);
		vertexMap1.put(vertexNameIn,incCurves);
	}

	public void removeVertex(String vertexName){
		vertexMap.remove(vertexName);
		for (String key : vertexMap.keySet()) {
			for (Info each : vertexMap.get(key)) {
				if (each.getVertex().equals(vertexName)) {
					vertexMap.get(key).remove(each);
					break;
				}
			}
		}
		vertexMap1.remove(vertexName);
		for (String key : vertexMap1.keySet()) {
			for (String each : vertexMap1.get(key)) {
				if (each.equals(vertexName)) {
					vertexMap1.get(key).remove(each);
					break;
				}
			}
		}
	}

	public void removeEdge(String vertexNameOut, String vertexNameIn){
		List<Info> edges1 = vertexMap.get(vertexNameOut);
		for (Info each : edges1) {
			if (each.getVertex().equals(vertexNameIn)) {
				edges1.remove(each);
				vertexMap.put(vertexNameOut,edges1);
			}
		}
		Set<String> edges2 = vertexMap1.get(vertexNameIn);
		Set<String> edgesToRemove = new HashSet<>();
		for (String each : edges2) {
			if(each.equals(vertexNameOut)) {
				edgesToRemove.add(each);
			}
		}
		edges2.removeAll(edgesToRemove);
		vertexMap1.put(vertexNameIn,edges2);
	}

	public void renameVertex(String vertexName, String newVertexName){
		List<Info> edges1 = vertexMap.get(vertexName);
		vertexMap.remove(vertexName);
		for (List<Info> value : vertexMap.values()) {
			for (Info each : value) {
				if (each.getVertex().equals(vertexName)) each.vertex = newVertexName;
			}
		}
		vertexMap.put(newVertexName, edges1);
		for (String key : vertexMap1.keySet()) {
			for (String each : vertexMap1.get(key)) {
				if (each.equals(vertexName)) {
					Set<String> edges2 = vertexMap1.get(key);
					edges2.remove(vertexName);
					edges2.add(newVertexName);
					vertexMap1.put(key,edges2);
					break;
				}
			}
		}
	}

	public void changeWeight(String vertexNameOut, String vertexNameIn, int newWeight){
		List<Info> edges1 = vertexMap.get(vertexNameOut);
		for (Info each : edges1) {
			if (each.getVertex().equals(vertexNameIn)) each.weight = newWeight;
		}
	}

	public Set<String> outgoingCurves(String vertexName){
		List<Info> edges1 = vertexMap.get(vertexName);
		Set<String> curves = new HashSet<>();
		for (Info each : edges1) {
			curves.add(each.getVertex());
		}
		return curves;
	}

	public Set<String> incomingCurves(String vertexName){
		Set<String> curves = new HashSet<>(Set.of());
		curves.addAll(vertexMap1.get(vertexName));
		return curves;
	}

	public void EmptyGraphOf(Set<String> vertexes) {
		for (String each : vertexes) {
			vertexMap.put(each, List.of());
			vertexMap1.put(each,Set.of());
		}
	}

	public Map<String, List<Info>> getVertexMap() {
		return vertexMap;
	}
}

