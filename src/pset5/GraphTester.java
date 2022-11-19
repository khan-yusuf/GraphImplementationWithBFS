package pset5;

import static org.junit.Assert.*;
import java.util.TreeSet;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GraphTester {
    // tests for method "addEdge" in class "Graph"

    @Test
    public void tae0() {
        Graph g = new Graph(2);
        g.addEdge(0, 1);
        //System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[false, true], [false, false]]");
    }

    // your tests for method "addEdge" in class "Graph" go here
    // you must provide at least 4 test methods;
    // each test method has at least 1 invocation of addEdge;
    // each test method creates exactly 1 graph
    // each test method creates a unique graph w.r.t. "equals" method
    // each test method has at least 1 test assertion;
    // your test methods provide full statement coverage of your
    // implementation of addEdge and any helper methods
    // no test method directly invokes any method that is not
    // declared in the Graph class as given in this homework


    // covers edge expansion with new matrix being created
    @Test
    public void tae1() {
        Graph g = new Graph(2);
        g.addEdge(1,2);
        //System.out.println(g);
        assertEquals(g.toString(), "numNodes: 3\nedges: [[false, false, false], [false, false, true], [false, false, false]]");
    }

    //covers edge expansion with new matrix being created but with reverse matrix
    @Test
    public void tae2() {
        Graph g = new Graph(2);
        g.addEdge(2,1);
        //System.out.println(g);
        assertEquals(g.toString(), "numNodes: 3\nedges: [[false, false, false], [false, false, false], [false, true, false]]");
    }

    // add lots of edges to edges graph
    @Test
    public void tae3() {
        Graph g = new Graph(3);
        g.addEdge(1,2);
        g.addEdge(2,1);
        g.addEdge(0,2);
        g.addEdge(2,2);
        //System.out.println(g);
        assertEquals(g.toString(), "numNodes: 3\nedges: [[false, false, true], [false, false, true], [false, true, true]]");
    }

    //covers adding same edge twice
    @Test
    public void tae4() {
        Graph g = new Graph(2);
        g.addEdge(0,0);
        g.addEdge(0,0);
        //System.out.println(g);
        assertEquals(g.toString(), "numNodes: 2\nedges: [[true, false], [false, false]]");
    };

    //covers adding negative edge value
    @Rule
    public ExpectedException ex1 = ExpectedException.none();
    @Test
    public void tae5() {
        Graph g = new Graph(2);
        g.addEdge(0,0);
        ex1.expect(IllegalArgumentException.class); // for negative index
        g.addEdge(-3,1);
        //System.out.println(g);
    };





    // tests for method "reachable" in class "Graph"

    @Test
    public void tr0() {
        Graph g = new Graph(1);
        Set<Integer> nodes = new TreeSet<Integer>();
        nodes.add(0);
        assertTrue(g.reachable(nodes, nodes));
    }

    // your tests for method "reachable" in class "Graph" go here
    // you must provide at least 6 test methods;
    // each test method must have at least 1 invocation of reachable;
    // each test method must have at least 1 test assertion;
    // at least 2 test methods must have at least 1 invocation of addEdge;
    // your test methods must provide full statement coverage of your
    // implementation of reachable and any helper methods
    // no test method directly invokes any method that is not
    // declared in the Graph class as given in this homework


    //regular test with multiple edges in graph and all targets tested
    @Test
    public void tr1() {
        Graph g = new Graph(4);
        Set<Integer> sources = new TreeSet<Integer>();
        Set<Integer> targets = new TreeSet<Integer>();

        g.addEdge(0,1);
        g.addEdge(2,3);
        g.addEdge(1,2);

        sources.add(0);
        sources.add(1);
        sources.add(2);
        targets.add(1);
        targets.add(2);
        targets.add(3);

        assertTrue(g.reachable(sources, targets));
    }


    // all sources go to one target node
    @Test
    public void tr2() {
        Graph g = new Graph(4);
        Set<Integer> sources = new TreeSet<>();
        Set<Integer> targets = new TreeSet<>();

        g.addEdge(0,3);
        g.addEdge(2,3);
        g.addEdge(1,3);

        sources.add(0);
        sources.add(1);
        sources.add(2);
        targets.add(3);

        assertTrue(g.reachable(sources, targets));
    }


    // No path possible
    @Test
    public void tr3() {
        Graph g = new Graph(4);
        Set<Integer> sources = new TreeSet<>();
        Set<Integer> targets = new TreeSet<>();

        g.addEdge(2,3);
        g.addEdge(1,3);

        targets.add(3);
        sources.add(0);
        sources.add(1);
        sources.add(2);


        assertFalse(g.reachable(sources, targets)); // no path from 0 to 3
    }


    @Rule
    public ExpectedException ex2 = ExpectedException.none();
    @Test
    public void tr4() {
        Graph g = new Graph(5);
        Set<Integer> sources = new TreeSet<>();
        Set<Integer> targets = new TreeSet<>();
        ex2.expect(IllegalArgumentException.class);


        g.addEdge(0,0);

        // sources and targets are empty (no nodes added)

        g.reachable(sources, targets);
    }


    // source is empty and target is null
    @Rule
    public ExpectedException ex3 = ExpectedException.none();
    @Test
    public void tr5() {
        Graph g = new Graph(5);
        Set<Integer> sources = new TreeSet<>();
        ex3.expect(IllegalArgumentException.class);
        g.addEdge(2,3);

        g.reachable(sources, null);
    }


    // adding target that is not in G, greater than its size
    @Test
    public void tr6() {
        Graph g = new Graph(2);
        Set<Integer> sources = new TreeSet<>();
        Set<Integer> targets = new TreeSet<>();

        sources.add(1);
        targets.add(10);

        // sources and targets are empty (no nodes added)

        assertFalse(g.reachable(sources, targets));
    }

    // negative target value
    @Test
    public void tr7() {
        Graph g = new Graph(2);
        Set<Integer> sources = new TreeSet<>();
        Set<Integer> targets = new TreeSet<>();

        sources.add(1);
        targets.add(-4);

        assertFalse(g.reachable(sources, targets));
    }


    // source value greater than numNodes
    @Test
    public void tr8() {
        Graph g = new Graph(2);
        Set<Integer> sources = new TreeSet<>();
        Set<Integer> targets = new TreeSet<>();

        sources.add(7);
        targets.add(0);

        assertFalse(g.reachable(sources, targets));
    }


    // negative source value
    @Test
    public void tr9() {
        Graph g = new Graph(6);
        Set<Integer> sources = new TreeSet<>();
        Set<Integer> targets = new TreeSet<>();

        sources.add(-6);
        targets.add(0);

        assertFalse(g.reachable(sources, targets));
    }


    // test indirect path from 0 to 2
    @Test
    public void tr10() {
        Graph g = new Graph(4);
        Set<Integer> sources = new TreeSet<>();
        Set<Integer> targets = new TreeSet<>();

        g.addEdge(0,3);
        g.addEdge(3,0);
        g.addEdge(0,1);
        g.addEdge(1,2);

        sources.add(0);
        targets.add(2);


        assertTrue(g.reachable(sources, targets)); // should recognize a path indirectly
    }
}