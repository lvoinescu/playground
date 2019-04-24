package org.sam.playground.platefill;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cook {

    private final Random random;
    private final int minSize;
    private final int maxSlice;

    public Cook(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSlice = maxSize;
        random = new Random();
    }

    public List<Sandwich> createSandwiches(int number) {
        Random randomForType = new Random();
        return IntStream.range(0, number)
                .mapToObj(i -> {
                    int width = 0;
                    int height = 0;
                    while (width < minSize || height < minSize) {
                        width = Math.abs(random.nextInt(maxSlice));
                        height = Math.abs(random.nextInt(maxSlice));
                    }
                    int i1 = randomForType.nextInt(SandwichType.values().length);
                    return new Sandwich(width, height, SandwichType.values()[i1], i);
                })
                .collect(Collectors.toList());
    }
}
