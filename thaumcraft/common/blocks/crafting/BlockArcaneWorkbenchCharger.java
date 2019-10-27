/*    */ package thaumcraft.common.blocks.crafting;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ import thaumcraft.common.tiles.crafting.TileArcaneWorkbench;
/*    */ import thaumcraft.common.tiles.crafting.TileFocalManipulator;
/*    */ 
/*    */ public class BlockArcaneWorkbenchCharger
/*    */   extends BlockTC
/*    */ {
/*    */   public BlockArcaneWorkbenchCharger() {
/* 26 */     super(Material.field_151575_d, "arcane_workbench_charger");
/* 27 */     func_149672_a(SoundType.field_185848_a);
/* 28 */     func_149711_c(1.25F);
/* 29 */     func_149752_b(10.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_176196_c(World worldIn, BlockPos pos) {
/* 46 */     return (super.func_176196_c(worldIn, pos) && (worldIn
/* 47 */       .func_180495_p(pos.func_177977_b()).func_177230_c() == BlocksTC.arcaneWorkbench || worldIn
/* 48 */       .func_180495_p(pos.func_177977_b()).func_177230_c() == BlocksTC.wandWorkbench));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 60 */     TileEntity te = worldIn.func_175625_s(pos.func_177977_b());
/* 61 */     if (te != null && te instanceof TileArcaneWorkbench) {
/* 62 */       ((TileArcaneWorkbench)te).syncTile(true);
/*    */     }
/* 64 */     if (te != null && te instanceof TileFocalManipulator) {
/* 65 */       ((TileFocalManipulator)te).syncTile(true);
/*    */     }
/* 67 */     return super.func_180642_a(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) {
/* 72 */     if (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() != BlocksTC.arcaneWorkbench && worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() != BlocksTC.wandWorkbench) {
/*    */       
/* 74 */       func_176226_b(worldIn, pos, state, 0);
/* 75 */       worldIn.func_175698_g(pos);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 84 */     if (world.field_72995_K) return true; 
/* 85 */     if (world.func_180495_p(pos.func_177977_b()).func_177230_c() == BlocksTC.arcaneWorkbench)
/* 86 */       player.openGui(Thaumcraft.instance, 13, world, pos.func_177958_n(), pos.func_177977_b().func_177956_o(), pos.func_177952_p()); 
/* 87 */     if (world.func_180495_p(pos.func_177977_b()).func_177230_c() == BlocksTC.wandWorkbench)
/* 88 */       player.openGui(Thaumcraft.instance, 7, world, pos.func_177958_n(), pos.func_177977_b().func_177956_o(), pos.func_177952_p()); 
/* 89 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockArcaneWorkbenchCharger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */