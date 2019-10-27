/*    */ package thaumcraft.common.blocks.crafting;
/*    */ 
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.InventoryHelper;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*    */ 
/*    */ public class BlockArcaneWorkbench
/*    */   extends BlockTCDevice
/*    */ {
/*    */   public BlockArcaneWorkbench() {
/* 21 */     super(Material.field_151575_d, TileArcaneWorkbench.class, "arcane_workbench");
/* 22 */     func_149672_a(SoundType.field_185848_a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 42 */     if (world.field_72995_K) return true; 
/* 43 */     player.openGui(Thaumcraft.instance, 13, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_180663_b(World world, BlockPos pos, IBlockState state) {
/* 50 */     TileEntity tileEntity = world.func_175625_s(pos);
/* 51 */     if (tileEntity != null && tileEntity instanceof TileArcaneWorkbench) {
/* 52 */       InventoryHelper.func_180175_a(world, pos, ((TileArcaneWorkbench)tileEntity).inventoryCraft);
/*    */     }
/* 54 */     super.func_180663_b(world, pos, state);
/* 55 */     world.func_175713_t(pos);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockArcaneWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */