/*    */ package thaumcraft.common.items;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.item.Item;
/*    */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*    */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ 
/*    */ public class ItemTCEssentiaContainer
/*    */   extends ItemGenericEssentiaContainer
/*    */   implements IEssentiaContainerItem, IThaumcraftItems
/*    */ {
/*    */   private final String BASE_NAME;
/*    */   protected String[] VARIANTS;
/*    */   protected int[] VARIANTS_META;
/*    */   
/*    */   public ItemTCEssentiaContainer(String name, int base, String... variants) {
/* 19 */     super(base);
/* 20 */     setRegistryName(name);
/* 21 */     func_77655_b(name);
/* 22 */     func_77637_a(ConfigItems.TABTC);
/* 23 */     setNoRepair();
/* 24 */     func_77627_a((variants.length > 1));
/* 25 */     this.BASE_NAME = name;
/* 26 */     if (variants.length == 0) {
/* 27 */       this.VARIANTS = new String[] { name };
/*    */     } else {
/*    */       
/* 30 */       this.VARIANTS = variants;
/*    */     } 
/* 32 */     this.VARIANTS_META = new int[this.VARIANTS.length];
/* 33 */     for (int m = 0; m < this.VARIANTS.length; m++) {
/* 34 */       this.VARIANTS_META[m] = m;
/*    */     }
/* 36 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   public String[] getVariantNames() { return this.VARIANTS; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public int[] getVariantMeta() { return this.VARIANTS_META; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */   
/*    */   public ModelResourceLocation getCustomModelResourceLocation(String variant) {
/* 62 */     if (variant.equals(this.BASE_NAME)) {
/* 63 */       return new ModelResourceLocation("thaumcraft:" + this.BASE_NAME);
/*    */     }
/* 65 */     return new ModelResourceLocation("thaumcraft:" + this.BASE_NAME, variant);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\ItemTCEssentiaContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */