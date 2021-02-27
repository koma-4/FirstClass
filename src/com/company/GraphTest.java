package com.company;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GraphTest {

    @Test
    void testAddVertex() {
        Map<String,Info> info = new HashMap<>(Map.of("B", new Info("B",2)));
        info.put("C", new Info("C", 5));
        Map<String,Info> info1 = new HashMap<>(Map.of("D", new Info("D", 4)));
        Map<String,Info> info2 = new HashMap<>(Map.of("C",new Info("C", 3)));
        info2.put("A", new Info("A", 1));
        Map<String, Map<String, Info>> expected = new HashMap<>();
        Map<String, Map<String, Info>> inExpected = new HashMap<>();
        expected.put("A", info);
        expected.put("B", info1);
        expected.put("D", info2);
        expected.put("C", Map.of());
        Map<String,Info> info3 = new HashMap<>(Map.of("D", new Info("D", 3)));
        info3.put("A", new Info("A", 5));
        inExpected.put("A", Map.of("D", new Info("D", 1)));
        inExpected.put("B", Map.of("A", new Info("A", 2)));
        inExpected.put("C", info3);
        inExpected.put("D", Map.of("B", new Info("B", 4)));

        Graph expect = new Graph(expected, inExpected);
        expect.addVertex("F");


        expected.put("F", Map.of());
        inExpected.put("F", Map.of());
        Graph actual = new Graph(expected,inExpected);
        assertEquals(expect.getVertexMap(),actual.getVertexMap());
    }


    @Test
    void testAddEdge() {
        Map<String, Map<String, Info>> expected = new HashMap<>();
        Map<String, Map<String, Info>> inExpected = new HashMap<>();
        expected.put("C", Map.of());
        inExpected.put("C", Map.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("B","D",4);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);

        Map<String, Map<String, Info>> actual = new HashMap<>();
        Map<String, Map<String, Info>> inActual = new HashMap<>();
        Map<String,Info> info = new HashMap<>(Map.of("B", new Info("B",2)));
        info.put("C", new Info("C", 5));
        Map<String,Info> info1 = new HashMap<>(Map.of("D", new Info("D", 4)));
        Map<String,Info> info2 = new HashMap<>(Map.of("C",new Info("C", 3)));
        info2.put("A", new Info("A", 1));
        actual.put("A", info);
        actual.put("B", info1);
        actual.put("D", info2);
        actual.put("C", Map.of());

        Map<String,Info> info3 = new HashMap<>(Map.of("D", new Info("D", 3)));
        info3.put("A", new Info("A", 5));
        inActual.put("A", Map.of("D", new Info("D", 1)));
        inActual.put("B", Map.of("A", new Info("A", 2)));
        inActual.put("C", info3);
        inActual.put("D", Map.of("B", new Info("B", 4)));

        Graph act = new Graph(actual,inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());

    }

    @Test
    void removeEdge() {
        Map<String, Map<String, Info>> expected = new HashMap<>();
        Map<String, Map<String, Info>> inExpected = new HashMap<>();
        expected.put("C", Map.of());
        inExpected.put("C", Map.of());
        Graph expect = new Graph(expected,inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("B","D",4);
        expect.addEdge("B","C",8);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);
        expect.removeEdge("B", "D");
        expect.removeEdge("D","A");

        Map<String, Map<String, Info>> actual = new HashMap<>();
        Map<String, Map<String, Info>> inActual = new HashMap<>();
        actual.put("C", Map.of());
        inActual.put("C", Map.of());
        Graph act = new Graph(actual, inActual);
        act.addEdge("A", "B", 2);
        act.addEdge("A","C",5);
        act.addEdge("D","C",3);
        act.addEdge("B","C",8);


        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }


    @Test
    void testRemoveVertex() {
        Map<String, Map<String, Info>> expected = new HashMap<>();
        expected.put("C", Map.of());
        Map<String, Map<String, Info>> inExpected = new HashMap<>();
        inExpected.put("C", Map.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("A","D",6);
        expect.addEdge("B","D",4);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);
        expect.removeVertex("D");

        Map<String, Map<String, Info>> actual = new HashMap<>();
        Map<String, Map<String, Info>> inActual = new HashMap<>();
        inActual.put("C", Map.of());
        Map<String, Info> info = new HashMap<>(Map.of("B", new Info("B",2)));
        info.put("C", new Info("C", 5));
        actual.put("A",info);
        actual.put("B", Map.of());
        actual.put("C", Map.of());
        inActual.put("B", Map.of("A", new Info("A", 2)));
        inActual.put("C", Map.of("A", new Info("A", 5)));
        inActual.put("A", Map.of());
        Graph act = new Graph(actual, inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }


    @Test
    void testRenameVertex() {
        Map<String, Map<String, Info>> expected = new HashMap<>();
        Map<String, Map<String, Info>> inExpected = new HashMap<>();
        inExpected.put("C", Map.of());
        expected.put("C", Map.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.renameVertex("A","center");

        Map<String, Map<String, Info>> actual = new HashMap<>();
        Map<String, Map<String, Info>> inActual = new HashMap<>();
        Map<String, Info> info = new HashMap<>(Map.of("B", new Info("B",2)));
        info.put("C", new Info("C", 5));
        actual.put("center",info);
        actual.put("B", Map.of());
        actual.put("C", Map.of());
        inActual.put("B", Map.of("center", new Info("center", 2)));
        inActual.put("C", Map.of("center", new Info("center", 5)));
        inActual.put("center", Map.of());
        Graph act = new Graph(actual, inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }

    @Test
    void testChangeWeight() {
        Map<String, Map<String, Info>> expected = new HashMap<>();
        Map<String, Map<String, Info>> inExpected = new HashMap<>();
        inExpected.put("C", Map.of());
        expected.put("C", Map.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.changeWeight("A", "B", 10);

        Map<String, Map<String, Info>> actual = new HashMap<>();
        Map<String, Map<String, Info>> inActual = new HashMap<>();
        Map<String, Info> info = new HashMap<>(Map.of("B", new Info("B",10)));
        info.put("C", new Info("C", 5));
        actual.put("A", info);
        actual.put("B", Map.of());
        actual.put("C", Map.of());
        inActual.put("B", Map.of("A", new Info("A", 10)));
        inActual.put("C", Map.of("A", new Info("A", 5)));
        inActual.put("A", Map.of());
        Graph act = new Graph(actual, inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }

    @Test
    void testOutgoingCurves() {
        Map<String, Map<String, Info>> expected = new HashMap<>();
        Map<String, Map<String, Info>> inExpected = new HashMap<>();
        inExpected.put("C", Map.of());
        expected.put("C", Map.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        List<Info> expectation = new ArrayList<>(expect.outgoingCurves("A"));

        List<Info> reality = new ArrayList<>(List.of());
        reality.add(new Info("B", 2));
        reality.add(new Info("C", 5));

        assertEquals(expectation.toString(), reality.toString());

    }

    @Test
    void testIncomingCurves() {
        Map<String, Map<String, Info>> expected = new HashMap<>();
        Map<String, Map<String, Info>> inExpected = new HashMap<>();
        inExpected.put("C", Map.of());
        expected.put("C", Map.of());
        Graph expect = new Graph (expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("D","C",3);
        expect.addEdge("B","C",8);
        List<Info> expectation = expect.incomingCurves("C");

        List<Info> reality = new ArrayList<>
                (List.of(new Info("D", 3), new Info("A", 5), new Info("B", 8)));
        assertEquals(expectation.toString(), reality.toString());
    }

    @Test
    void testEmpty() {
        Graph expect = Graph.empty();
        Map<String, Map<String, Info>> actual = new HashMap<>();
        Map<String, Map<String, Info>> inActual = new HashMap<>();
        Graph act = new Graph(actual, inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }

}