package com.company;

import java.util.*;

class Info {                                         //вспомогательный класс
	public String vertex;
	public Integer weight;

	public Info (String vertex, Integer weight) {
		super();
		this.vertex = vertex;
		this.weight = weight;
	}
	public String getVertex() {
		return String.valueOf(vertex);
	}

	@Override
	public String toString() {
		return vertex + weight;
	}

}

public class Main implements Main1 {

	private final HashMap<String, List<Info>> vertexMap;

	public static void main(String[] args){

	}

	public Main(HashMap<String, List<Info>> vertexMap) {
		this.vertexMap = vertexMap;
	}

		public void addVertex(String vertexName){
			if (!hasVertex(vertexName)) {
				vertexMap.put(vertexName, new ArrayList<>());
			}
		}

		public boolean hasVertex(String vertexName){
			return vertexMap.containsKey(vertexName);
		}

		public void addEdge(String vertexName1, String vertexName2, Integer weight){
			if (!hasVertex(vertexName1)) addVertex(vertexName1);
			if (!hasVertex(vertexName2)) addVertex(vertexName2);
			List<Info> edges1 = vertexMap.get(vertexName1);
			edges1.add(new Info(vertexName2, weight));
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
		}

		public void removeEdge(String vertexName1, String vertexName2){
		    List<Info> edges1 = vertexMap.get(vertexName1);
			for (Info each : edges1) {
				if (each.getVertex().equals(vertexName2)) {
					edges1.remove(each);
					vertexMap.put(vertexName1,edges1);
				}
			}
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
		}

		public void changeWeight(String vertexName1, String vertexName2, Integer newWeight){
			List<Info> edges1 = vertexMap.get(vertexName1);
			for (Info each : edges1) {
				if (each.getVertex().equals(vertexName2)) each.weight = newWeight;
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
			Set<String> curves = new HashSet<>();
			for (String key : vertexMap.keySet()) {
				for (Info each : vertexMap.get(key)) {
					if (each.getVertex().equals(vertexName)) {
						curves.add(key);
					}
				}
			}
			return curves;
		}

	@Override
	public HashMap<String, List<Info>> getVertexMap() {
		return vertexMap;
	}

}

