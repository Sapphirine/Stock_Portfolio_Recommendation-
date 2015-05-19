package com.github.neuralnetworks.training.random;

import java.util.Random;
import org.apache.commons.math3.random.MersenneTwister;


/**
 * Created by wakahiu on 5/15/15.
 */
public class MersenneTwisterRandom extends Random{
  MersenneTwister mersenneTwister;
  public MersenneTwisterRandom () {
    mersenneTwister = new MersenneTwister();
  }
  public MersenneTwisterRandom (long seed) {
    mersenneTwister = new MersenneTwister(seed);
  }

  public int nextInt() {
    return mersenneTwister.nextInt();
  }
  public int nextInt(int n) {
    return mersenneTwister.nextInt(n);
  }
  public boolean nextBoolean () {
    return mersenneTwister.nextBoolean();
  }
  public long nextLong() {
    return mersenneTwister.nextLong();
  }
  public long nextLong(long n) {
    return mersenneTwister.nextLong(n);
  }
  public double nextGaussian() {
    return mersenneTwister.nextGaussian();
  }
  public float nextFloat() {
    return mersenneTwister.nextFloat();
  }

  public double nextDouble() {
    return mersenneTwister.nextDouble();
  }
}
