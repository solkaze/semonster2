package org.example;

public class ItemStack {
  public final Item item;
  public int count;

  public ItemStack(Item item, int count) {
    this.item = item;
    this.count = count;
  }
}
