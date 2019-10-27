/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemHoe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import thaumcraft.api.items.IWarpingGear;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ public class ItemVoidHoe
/*    */   extends ItemHoe
/*    */   implements IWarpingGear, IThaumcraftItems {
/*    */   public ItemVoidHoe(Item.ToolMaterial enumtoolmaterial) {
/* 23 */     super(enumtoolmaterial);
/* 24 */     func_77637_a(ConfigItems.TABTC);
/* 25 */     setRegistryName("void_hoe");
/* 26 */     func_77655_b("void_hoe");
/* 27 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
/* 59 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 61 */     if (stack.func_77951_h() && entity != null && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase) {
/* 62 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
/* 68 */     if (!player.field_70170_p.field_72995_K && entity instanceof EntityLivingBase && (
/* 69 */       !(entity instanceof EntityPlayer) || FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W()))
/*    */     {
/*    */       
/* 72 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(MobEffects.field_76437_t, 80));
/*    */     }
/*    */     
/* 75 */     return super.onLeftClickEntity(stack, player, entity);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 80 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return 1; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemVoidHoe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */