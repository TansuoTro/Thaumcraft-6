/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ 
/*    */ public class ItemTripleMeatTreat
/*    */   extends ItemFood
/*    */   implements IThaumcraftItems
/*    */ {
/*    */   public ItemTripleMeatTreat() {
/* 18 */     super(6, 0.8F, true);
/* 19 */     func_77848_i();
/* 20 */     setRegistryName("triple_meat_treat");
/* 21 */     func_77655_b("triple_meat_treat");
/* 22 */     func_77637_a(ConfigItems.TABTC);
/* 23 */     func_185070_a(new PotionEffect(MobEffects.field_76428_l, 100, 0), 0.66F);
/* 24 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemTripleMeatTreat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */