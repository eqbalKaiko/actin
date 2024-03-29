package com.hartwig.actin.treatment.sort;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.google.common.collect.Lists;
import com.hartwig.actin.treatment.datamodel.CohortMetadata;
import com.hartwig.actin.treatment.datamodel.ImmutableCohortMetadata;

import org.junit.Test;

public class CohortMetadataComparatorTest {

    @Test
    public void canSortCohortMetadata() {
        ImmutableCohortMetadata.Builder builder = ImmutableCohortMetadata.builder().evaluable(true).slotsAvailable(true);
        CohortMetadata metadata1 = builder.cohortId("A").description("A First").open(true).blacklist(false).build();
        CohortMetadata metadata2 = builder.cohortId("A").description("A First").open(false).blacklist(false).build();
        CohortMetadata metadata3 = builder.cohortId("A").description("Second A").open(true).blacklist(false).build();
        CohortMetadata metadata4 = builder.cohortId("B").description("B Third").open(true).blacklist(false).build();
        CohortMetadata metadata5 = builder.cohortId("A").description("A First").open(false).blacklist(true).build();

        List<CohortMetadata> metadata = Lists.newArrayList(metadata1, metadata2, metadata3, metadata4, metadata5);
        metadata.sort(new CohortMetadataComparator());

        assertEquals(metadata1, metadata.get(0));
        assertEquals(metadata2, metadata.get(1));
        assertEquals(metadata5, metadata.get(2));
        assertEquals(metadata3, metadata.get(3));
        assertEquals(metadata4, metadata.get(4));
    }
}