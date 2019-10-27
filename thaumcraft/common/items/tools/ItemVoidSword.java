/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*    */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.items.IWarpingGear;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.IThaumcraftItems;
/*    */ 
/*    */ public class ItemVoidSword
/*    */   extends ItemSword
/*    */   implements IWarpingGear, IThaumcraftItems
/*    */ {
/*    */   public ItemVoidSword(Item.ToolMaterial enumtoolmaterial) {
/* 30 */     super(enumtoolmaterial);
/* 31 */     func_77637_a(ConfigItems.TABTC);
/* 32 */     setRegistryName("void_sword");
/* 33 */     func_77655_b("void_sword");
/* 34 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public Item getItem() { return this; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
/* 65 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*    */     
/* 67 */     if (stack.func_77951_h() && entity != null && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase) {
/* 68 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_77644_a(ItemStack is, EntityLivingBase target, EntityLivingBase hitter) {
/* 74 */     if (!target.field_70170_p.field_72995_K && (
/* 75 */       !(target instanceof EntityPlayer) || !(hitter instanceof EntityPlayer) || FMLCommonHandler.instance().getMinecraftServerInstance().func_71219_W())) {
/*    */       
/*    */       try {
/*    */         
/* 79 */         target.func_70690_d(new PotionEffect(MobEffects.field_76437_t, 60));
/* 80 */       } catch (Exception exception) {}
/*    */     }
/*    */     
/* 83 */     return super.func_77644_a(is, target, hitter);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 88 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return 1; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 94 */     tooltip.add(TextFormatting.GOLD + I18n.func_74838_a("enchantment.special.sapless"));
/* 95 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemVoidSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */