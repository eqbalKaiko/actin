package com.hartwig.actin.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;

import org.junit.Test;

public class FileUtilTest {

    @Test
    public void canAppendFileSeparator() {
        assertTrue(FileUtil.appendFileSeparator("hi").endsWith(File.separator));

        String dir = "this" + File.separator + "dir" + File.separator;
        assertEquals(dir, FileUtil.appendFileSeparator(dir));
    }

    @Test
    public void canCreateFieldIndexMap() {
        String[] header = new String[] { "header0", "header1", "header2" };
        Map<String, Integer> fieldIndexMap = FileUtil.createFieldIndexMap(header);

        assertEquals(0, (int) fieldIndexMap.get("header0"));
        assertEquals(1, (int) fieldIndexMap.get("header1"));
        assertEquals(2, (int) fieldIndexMap.get("header2"));
    }
}