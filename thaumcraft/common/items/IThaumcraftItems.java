package thaumcraft.common.items;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public interface IThaumcraftItems {
  Item getItem();
  
  String[] getVariantNames();
  
  int[] getVariantMeta();
  
  ItemMeshDefinition getCustomMesh();
  
  ModelResourceLocation getCustomModelResourceLocation(String paramString);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\IThaumcraftItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */