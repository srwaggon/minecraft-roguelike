package greymerk.roguelike.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class WeightedRandomizer<T> implements IWeighted<T> {

  private int weight;
  private int weightSum = 0;
  private final List<IWeighted<T>> items = new ArrayList<>();

  public WeightedRandomizer() {
  }

  public WeightedRandomizer(int weight) {
    this.weight = weight;
  }

  public WeightedRandomizer(WeightedRandomizer<T> toCopy) {
    weight = toCopy.weight;
    weightSum = toCopy.weightSum;
    items.addAll(toCopy.items);
  }

  public WeightedRandomizer(WeightedRandomizer<T> base, WeightedRandomizer<T> other) {
    for (IWeighted<T> item : base.items) {
      add(item);
    }

    for (IWeighted<T> item : other.items) {
      add(item);
    }
  }

  @Override
  public int getWeight() {
    return weight;
  }

  public boolean isEmpty() {
    return items.isEmpty();
  }

  public void add(IWeighted<T> toAdd) {
    weightSum += toAdd.getWeight();
    items.add(toAdd);
  }

  public WeightedRandomizer<T> with(IWeighted<T> toAdd) {
    add(toAdd);
    return this;
  }

  public WeightedRandomizer<T> with(WeightedChoice<T> toAdd) {
    add(toAdd);
    return this;
  }

  public T get(Random rand) {
    if (weightSum == 0) {
      return null;
    }
    if (items.isEmpty()) {
      return null;
    }

    int roll = rand.nextInt(weightSum);

    for (IWeighted<T> i : items) {
      roll -= i.getWeight();
      if (roll < 0) {
        return i.get(rand);
      }
    }

    return null;
  }

  public void merge(WeightedRandomizer<T> toMerge) {
    for (IWeighted<T> item : toMerge.items) {
      add(item);
    }
  }

  @Override
  public boolean equals(Object o) {
    // I think this is only for tests, which means the behaviour isn't being tested, just the state
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WeightedRandomizer<?> that = (WeightedRandomizer<?>) o;
    return weight == that.weight &&
        weightSum == that.weightSum &&
        Objects.equals(items, that.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(weight, weightSum, items);
  }

  @Override
  public String toString() {
    return "WeightedRandomizer{" +
        "weight=" + weight +
        ", weightSum=" + weightSum +
        ", items=" + items +
        '}';
  }

  public WeightedRandomizer<T> copy() {
    return new WeightedRandomizer<T>(this);
  }
}
