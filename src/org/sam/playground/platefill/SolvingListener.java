package org.sam.playground.platefill;

import java.util.List;

public interface SolvingListener {

    void stateChanged(List<Slice> slices, List<Slice> partialSolution);

    void problemSolved(List<Slice> slices, List<Slice> partialSolution);
}
