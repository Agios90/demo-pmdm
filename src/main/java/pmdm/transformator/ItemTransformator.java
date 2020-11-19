package pmdm.transformator;

import pmdm.model.Item;
import pmdm.model.TransformedItem;

public class ItemTransformator {

    public static TransformedItem transform(Item item) {
        TransformedItem transformedItem = new TransformedItem();
        transformedItem.setTransformedValue(item.getValue() + "Tr");
        return transformedItem;
    }
}
