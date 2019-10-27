/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemFood;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.capabilities.IPlayerWarp;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ 
/*    */ public class ItemZombieBrain
/*    */   extends ItemFood
/*    */   implements IThaumcraftItems
/*    */ {
/*    */   public ItemZombieBrain() {
/* 23 */     super(4, 0.2F, true);
/* 24 */     func_185070_a(new PotionEffect(MobEffects.field_76438_s, 30, 0), 0.8F);
/* 25 */     func_77637_a(ConfigItems.TABTC);
/* 26 */     setRegistryName("brain");
/* 27 */     func_77655_b("brain");
/* 28 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77849_c(ItemStack stack, World world, EntityPlayer player) {
/* 33 */     if (!world.field_72995_K) {
/* 34 */       if (world.field_73012_v.nextFloat() < 0.1F) {
/* 35 */         ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, IPlayerWarp.EnumWarpType.NORMAL);
/*    */       } else {
/* 37 */         ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1 + world.field_73012_v.nextInt(3), IPlayerWarp.EnumWarpType.TEMPORARY);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemZombieBrain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */