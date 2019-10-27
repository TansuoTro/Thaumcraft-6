/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import thaumcraft.api.items.IWarpingGear;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ 
/*    */ public class ItemVoidPickaxe
/*    */   extends ItemPickaxe
/*    */   implements IWarpingGear, IThaumcraftItems
/*    */ {
/*    */   public ItemVoidPickaxe(Item.ToolMaterial enumtoolmaterial) {
/* 27 */     super(enumtoolmaterial);
/* 28 */     func_77637_a(ConfigItems.TABTC);
/* 29 */     setRegistryName("void_pick");
/* 30 */     func_77655_b("void_pick");
/* 31 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 51 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 61 */   public Set<String> getToolClasses(ItemStack stack) { return ImmutableSet.of("pickaxe"); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
/* 68 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 70 */     if (stack.func_77951_h() && entity != null && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase) {
/* 71 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
/* 77 */     if (!player.field_70170_p.field_72995_K && entity instanceof EntityLivingBase && (
/* 78 */       !(entity instanceof EntityPlayer) || FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W()))
/*    */     {
/*    */       
/* 81 */       ((EntityLivingBase)entity).func_70690_d(new PotionEffect(MobEffects.field_76437_t, 80));
/*    */     }
/*    */     
/* 84 */     return super.onLeftClickEntity(stack, player, entity);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 89 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return 1; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemVoidPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */