package com.hartwig.actin.algo.doid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.hartwig.actin.algo.doid.datamodel.Edge;
import com.hartwig.actin.algo.doid.datamodel.ImmutableEdge;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class DoidModelTest {

    @Test
    public void testBehaviour() {
        List<Edge> edges = Lists.newArrayList();
        edges.add(createParent("299", "305"));
        edges.add(createParent("305", "162"));
        edges.add(createEdge("305", "has_a", "162"));

        DoidModel victim = DoidModel.fromEdges(edges);
        assertEquals(2, victim.size());

        Set<String> parents299 = victim.parents("299");
        assertEquals(2, parents299.size());
        assertTrue(parents299.contains("305"));
        assertTrue(parents299.contains("162"));

        Set<String> parents305 = victim.parents("305");
        assertEquals(1, parents305.size());
        assertTrue(parents305.contains("162"));
    }

    @NotNull
    private static Edge createParent(@NotNull String child, @NotNull String parent) {
        String prefix = "http://purl.obolibrary.org/obo/DOID_";
        return createEdge(prefix + child, "is_a", prefix + parent);
    }

    @NotNull
    private static Edge createEdge(@NotNull String subject, @NotNull String pred, @NotNull String object) {
        return ImmutableEdge.builder().subject(subject).predicate(pred).object(object).build();
    }
}
