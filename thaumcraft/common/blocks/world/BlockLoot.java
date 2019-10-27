/*    */ package thaumcraft.common.blocks.world;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ import thaumcraft.common.lib.SoundsTC;
/*    */ 
/*    */ public class BlockLoot extends BlockTC {
/*    */   LootType type;
/*    */   Random rand;
/*    */   
/*    */   public SoundType func_185467_w() { return (this.field_149764_J == Material.field_151575_d) ? SoundType.field_185848_a : SoundsTC.URN; }
/*    */   
/*    */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */   
/* 24 */   public BlockLoot(Material mat, String name, LootType type) { super(mat, name);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 71 */     this.rand = new Random();
/*    */     func_149711_c(0.15F);
/*    */     func_149752_b(0.0F);
/*    */     this.type = type; } public boolean func_149686_d(IBlockState state) { return false; } protected boolean func_149700_E() { return true; } public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; } public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
/* 75 */     ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
/* 76 */     int q = 1 + this.type.ordinal() + this.rand.nextInt(3);
/* 77 */     for (int a = 0; a < q; a++) {
/* 78 */       ItemStack is = Utils.generateLoot(this.type.ordinal(), this.rand);
/* 79 */       if (is != null && !is.func_190926_b()) {
/* 80 */         ret.add(is.func_77946_l());
/*    */       }
/*    */     } 
/* 83 */     return ret;
/*    */   } public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
/*    */     if (func_149688_o(state) == Material.field_151576_e)
/*    */       return new AxisAlignedBB(0.125D, 0.0625D, 0.125D, 0.875D, 0.8125D, 0.875D); 
/*    */     return new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);
/* 88 */   } public enum LootType { COMMON, UNCOMMON, RARE; }
/*    */ 
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\BlockLoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */