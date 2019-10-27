/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.ILabelable;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.ItemTCEssentiaContainer;
/*    */ 
/*    */ 
/*    */ public class ItemLabel
/*    */   extends ItemTCEssentiaContainer
/*    */ {
/* 23 */   public ItemLabel() { super("label", 1, new String[] { "blank", "filled" }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public String func_77667_c(ItemStack stack) { return func_77658_a() + "." + getVariantNames()[stack.func_77952_i()]; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 34 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 35 */       items.add(new ItemStack(this, 1, 0));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/* 44 */     if (world.field_72995_K) return EnumActionResult.PASS;
/*    */     
/* 46 */     IBlockState bs = world.func_180495_p(pos);
/* 47 */     if (bs.func_177230_c() instanceof ILabelable) {
/* 48 */       if (((ILabelable)bs.func_177230_c()).applyLabel(player, pos, side, player.func_184586_b(hand))) {
/* 49 */         player.func_184586_b(hand).func_190918_g(1);
/* 50 */         player.field_71069_bz.func_75142_b();
/*    */       } 
/* 52 */       return EnumActionResult.SUCCESS;
/*    */     } 
/*    */     
/* 55 */     TileEntity te = world.func_175625_s(pos);
/* 56 */     if (te instanceof ILabelable) {
/* 57 */       if (((ILabelable)te).applyLabel(player, pos, side, player.func_184586_b(hand))) {
/* 58 */         player.func_184586_b(hand).func_190918_g(1);
/* 59 */         player.field_71069_bz.func_75142_b();
/*    */       } 
/* 61 */       return EnumActionResult.SUCCESS;
/*    */     } 
/*    */     
/* 64 */     return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {}
/*    */ 
/*    */   
/*    */   public void func_77622_d(ItemStack stack, World world, EntityPlayer player) {}
/*    */ 
/*    */   
/* 74 */   public boolean ignoreContainedAspects() { return true; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */