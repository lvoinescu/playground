package org.sam.playground.platefill;

import java.util.List;

public interface SolvingListener {

    void stateChanged(List<Slice> slices);

    void problemSolved(List<Slice> slices);
}
