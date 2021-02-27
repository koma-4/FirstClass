package com.company;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class GraphTest {

    @Test
    void testAddVertex() {
        Info info = new Info("B",2);
        Info info1 = new Info("D", 4);
        Info info2 = new Info("C", 3);
        Info info3 = new Info("A", 1);
        Info info4 = new Info("C", 5);
        Map<String, List<Info>> expected = new HashMap<>();
        Map<String, List<Info>> inExpected = new HashMap<>();
        expected.put("A",List.of(info, info4));
        expected.put("B", List.of(info1));
        expected.put("D", List.of(info3, info2));
        expected.put("C", List.of());
        inExpected.put("A", List.of(new Info("D", 1)));
        inExpected.put("B", List.of(new Info("A", 2)));
        inExpected.put("C", List.of(new Info("A", 5), new Info("D", 3)));
        inExpected.put("D", List.of(new Info("B", 4)));

        Graph expect = new Graph(expected, inExpected);
        expect.addVertex("F");


        expected.put("F", List.of());
        inExpected.put("F", List.of());
        Graph actual = new Graph(expected,inExpected);
        assertEquals(expect.getVertexMap(),actual.getVertexMap());
    }


    @Test
    void testAddEdge() {
        Map<String, List<Info>> expected = new HashMap<>();
        Map<String, List<Info>> inExpected = new HashMap<>();
        expected.put("C", List.of());
        inExpected.put("C", List.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("B","D",4);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);

        Map<String, List<Info>> actual = new HashMap<>();
        Map<String, List<Info>> inActual = new HashMap<>();
        Info info = new Info("B",2);
        Info info1 = new Info("D", 4);
        Info info2 = new Info("C", 3);
        Info info3 = new Info("A", 1);
        Info info4 = new Info("C", 5);
        actual.put("A",List.of(info, info4));
        actual.put("B", List.of(info1));
        actual.put("D", List.of(info3, info2));
        actual.put("C", List.of());
        inActual.put("A", List.of(new Info("D", 1)));
        inActual.put("B", List.of(new Info("A", 2)));
        inActual.put("C", List.of(new Info("A", 5), new Info("D", 3)));
        inActual.put("D", List.of(new Info("B", 4)));

        Graph act = new Graph(actual,inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());

    }

    @Test
    void removeEdge() {
        Map<String, List<Info>> expected = new HashMap<>();
        Map<String, List<Info>> inExpected = new HashMap<>();
        expected.put("C", List.of());
        inExpected.put("C", List.of());
        Graph expect = new Graph(expected,inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("B","D",4);
        expect.addEdge("B","C",8);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);
        expect.removeEdge("B", "D");
        expect.removeEdge("D","A");

        Map<String, List<Info>> actual = new HashMap<>();
        Map<String, List<Info>> inActual = new HashMap<>();
        actual.put("C", List.of());
        inActual.put("C", List.of());
        Graph act = new Graph(actual, inActual);
        act.addEdge("A", "B", 2);
        act.addEdge("A","C",5);
        act.addEdge("D","C",3);
        act.addEdge("B","C",8);


        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }


    @Test
    void testRemoveVertex() {
        Map<String, List<Info>> expected = new HashMap<>();
        expected.put("C", List.of());
        Map<String, List<Info>> inExpected = new HashMap<>();
        inExpected.put("C", List.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("A","D",6);
        expect.addEdge("B","D",4);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);
        expect.removeVertex("D");

        Map<String, List<Info>> actual = new HashMap<>();
        Map<String, List<Info>> inActual = new HashMap<>();
        inActual.put("C", List.of());
        Info info = new Info("B",2);
        Info info4 = new Info("C", 5);
        actual.put("A",List.of(info, info4));
        actual.put("B", List.of());
        actual.put("C", List.of());
        inActual.put("B", List.of(new Info("A", 2)));
        inActual.put("C", List.of(new Info("A", 5)));
        inActual.put("A", List.of());
        Graph act = new Graph(actual, inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }


    @Test
    void testRenameVertex() {
        Map<String, List<Info>> expected = new HashMap<>();
        Map<String, List<Info>> inExpected = new HashMap<>();
        inExpected.put("C", List.of());
        expected.put("C", List.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.renameVertex("A","center");

        Map<String, List<Info>> actual = new HashMap<>();
        Map<String, List<Info>> inActual = new HashMap<>();
        Info info = new Info("B",2);
        Info info4 = new Info("C", 5);
        actual.put("center",List.of(info, info4));
        actual.put("B", List.of());
        actual.put("C", List.of());
        inActual.put("B", List.of(new Info("center", 2)));
        inActual.put("C", List.of(new Info("center", 5)));
        inActual.put("center", List.of());
        Graph act = new Graph(actual, inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }

    @Test
    void testChangeWeight() {
        Map<String, List<Info>> expected = new HashMap<>();
        Map<String, List<Info>> inExpected = new HashMap<>();
        inExpected.put("C", List.of());
        expected.put("C", List.of());
        Graph expect = new Graph(expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.changeWeight("A", "B", 10);

        Map<String, List<Info>> actual = new HashMap<>();
        Map<String, List<Info>> inActual = new HashMap<>();
        Info info = new Info("B",10);
        Info info4 = new Info("C", 5);
        actual.put("A",List.of(info, info4));
        actual.put("B", List.of());
        actual.put("C", List.of());
        inActual.put("B", List.of(new Info("A", 10)));
        inActual.put("C", List.of(new Info("A", 5)));
        inActual.put("A", List.of());
        Graph act = new Graph(actual, inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }

    @Test
    void testOutgoingCurves() {
        Map<String, List<Info>> expected = new HashMap<>();
        Map<String, List<Info>> inExpected = new HashMap<>();
        inExpected.put("C", List.of());
        expected.put("C", List.of());
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
        Map<String, List<Info>> expected = new HashMap<>();
        Map<String, List<Info>> inExpected = new HashMap<>();
        inExpected.put("C", List.of());
        expected.put("C", List.of());
        Graph expect = new Graph (expected, inExpected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("D","C",3);
        expect.addEdge("B","C",8);
        List<Info> expectation = expect.incomingCurves("C");

        List<Info> reality = new ArrayList<>
                (List.of(new Info("A", 5), new Info("D", 3), new Info("B", 8)));
        assertEquals(expectation.toString(), reality.toString());
    }

    @Test
    void testEmpty() {
        Graph expect = Graph.empty();
        Map<String, List<Info>> actual = new HashMap<>();
        Map<String, List<Info>> inActual = new HashMap<>();
        Graph act = new Graph(actual, inActual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }

}