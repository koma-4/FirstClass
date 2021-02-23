package com.company;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MainTest {

    @Test
    void testAddVertex() {
        Info info = new Info("B",2);
        Info info1 = new Info("D", 4);
        Info info2 = new Info("C", 3);
        Info info3 = new Info("A", 1);
        Info info4 = new Info("C", 5);
        HashMap<String, List<Info>> expected = new HashMap<>();
        expected.put("A",List.of(info, info4));
        expected.put("B", List.of(info1));
        expected.put("D", List.of(info3, info2));
        expected.put("C", List.of());

        Main expect = new Main(expected);
        expect.addVertex("F");


        expected.put("F",List.of());
        Main actual = new Main(expected);
        assertEquals(expect.getVertexMap(),actual.getVertexMap());
    }


    @Test
    void testAddEdge() {
        HashMap<String, List<Info>> expected = new HashMap<>();
        expected.put("C", List.of());
        Main expect = new Main(expected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("B","D",4);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);

        HashMap<String, List<Info>> actual = new HashMap<>();
        Info info = new Info("B",2);
        Info info1 = new Info("D", 4);
        Info info2 = new Info("C", 3);
        Info info3 = new Info("A", 1);
        Info info4 = new Info("C", 5);
        actual.put("A",List.of(info, info4));
        actual.put("B", List.of(info1));
        actual.put("D", List.of(info3, info2));
        actual.put("C", List.of());
        Main act = new Main(actual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());

    }

    @Test
    void removeEdge() {
        HashMap<String, List<Info>> expected = new HashMap<>();
        expected.put("C", List.of());
        Main expect = new Main(expected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("B","D",4);
        expect.addEdge("B","C",8);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);
        expect.removeEdge("B", "D");
        expect.removeEdge("D","A");

        HashMap<String, List<Info>> actual = new HashMap<>();
        actual.put("C", List.of());
        Main act = new Main(actual);
        act.addEdge("A", "B", 2);
        act.addEdge("A","C",5);
        act.addEdge("D","C",3);
        act.addEdge("B","C",8);


        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }


    @Test
    void testRemoveVertex() {
        HashMap<String, List<Info>> expected = new HashMap<>();
        expected.put("C", List.of());
        Main expect = new Main(expected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.addEdge("A","D",6);
        expect.addEdge("B","D",4);
        expect.addEdge("D","A",1);
        expect.addEdge("D","C",3);
        expect.removeVertex("D");

        HashMap<String, List<Info>> actual = new HashMap<>();
        Info info = new Info("B",2);
        Info info4 = new Info("C", 5);
        actual.put("A",List.of(info, info4));
        actual.put("B", List.of());
        actual.put("C", List.of());
        Main act = new Main(actual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }


    @Test
    void testRenameVertex() {
        HashMap<String, List<Info>> expected = new HashMap<>();
        expected.put("C", List.of());
        Main expect = new Main(expected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.renameVertex("A","center");

        HashMap<String, List<Info>> actual = new HashMap<>();
        Info info = new Info("B",2);
        Info info4 = new Info("C", 5);
        actual.put("center",List.of(info, info4));
        actual.put("B", List.of());
        actual.put("C", List.of());
        Main act = new Main(actual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }

    @Test
    void testChangeWeight() {
        HashMap<String, List<Info>> expected = new HashMap<>();
        expected.put("C", List.of());
        Main expect = new Main(expected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        expect.changeWeight("A", "B", 10);

        HashMap<String, List<Info>> actual = new HashMap<>();
        Info info = new Info("B",10);
        Info info4 = new Info("C", 5);
        actual.put("A",List.of(info, info4));
        actual.put("B", List.of());
        actual.put("C", List.of());
        Main act = new Main(actual);

        assertEquals(expect.getVertexMap().toString(),act.getVertexMap().toString());
    }

    @Test
    void testOutgoingCurves() {
        HashMap<String, List<Info>> expected = new HashMap<>();
        expected.put("C", List.of());
        Main expect = new Main(expected);
        expect.addEdge("A", "B", 2);
        expect.addEdge("A","C",5);
        Set<String> expectation = expect.outgoingCurves("A");

        Set<String> reality = Set.of("B", "C");

        assertEquals(expectation, reality);

    }

    @Test
    void testIncomingCurves() {
        HashMap<String, List<Info>> actual = new HashMap<>();
        actual.put("C", List.of());
        Main act = new Main(actual);
        act.addEdge("A", "B", 2);
        act.addEdge("A","C",5);
        act.addEdge("D","C",3);
        act.addEdge("B","C",8);
        Set<String> expectation = act.incomingCurves("C");

        Set<String> reality = Set.of("B", "D", "A");

        assertEquals(expectation, reality);
    }

}