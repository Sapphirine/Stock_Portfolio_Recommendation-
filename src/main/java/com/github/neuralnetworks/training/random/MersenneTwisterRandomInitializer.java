package com.github.neuralnetworks.training.random;

/**
 * Mersenne twister random initializer
 */
public class MersenneTwisterRandomInitializer extends RandomInitializerImpl {

    private static final long serialVersionUID = 1L;

    public MersenneTwisterRandomInitializer() {
        super(new MersenneTwisterRandom());
    }

    public MersenneTwisterRandomInitializer(float start, float end) {
	      super(new MersenneTwisterRandom(), start, end);
    }
}
