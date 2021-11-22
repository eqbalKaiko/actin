package com.hartwig.actin.algo.doid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import org.junit.Test;

public class DoidModelTest {

    @Test
    public void canIncludeParents() {
        Multimap<String, String> relations = ArrayListMultimap.create();
        relations.put("200", "300");
        relations.put("300", "400");

        DoidModel model = new DoidModel(relations);

        Set<String> withParents200 = model.doidWithParents("200");
        assertEquals(3, withParents200.size());
        assertTrue(withParents200.contains("200"));
        assertTrue(withParents200.contains("300"));
        assertTrue(withParents200.contains("400"));

        Set<String> withParents300 = model.doidWithParents("300");
        assertEquals(2, withParents300.size());
        assertFalse(withParents300.contains("200"));
        assertTrue(withParents300.contains("300"));
        assertTrue(withParents300.contains("400"));

        Set<String> withParents400 = model.doidWithParents("400");
        assertEquals(1, withParents400.size());
        assertFalse(withParents400.contains("200"));
        assertFalse(withParents400.contains("300"));
        assertTrue(withParents400.contains("400"));

        assertEquals(1, model.doidWithParents("500").size());
    }
}