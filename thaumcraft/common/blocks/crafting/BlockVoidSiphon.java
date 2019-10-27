/*    */ package thaumcraft.common.blocks.crafting;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.BlockFaceShape;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.blocks.BlockTCDevice;
/*    */ import thaumcraft.common.blocks.IBlockEnabled;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber({Side.CLIENT})
/*    */ public class BlockVoidSiphon
/*    */   extends BlockTCDevice
/*    */   implements IBlockEnabled
/*    */ {
/*    */   public BlockVoidSiphon() {
/* 29 */     super(Material.field_151573_f, thaumcraft.common.tiles.crafting.TileVoidSiphon.class, "void_siphon");
/* 30 */     func_149672_a(SoundType.field_185852_e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public int func_180651_a(IBlockState state) { return 0; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public boolean func_149662_c(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   public boolean func_149686_d(IBlockState state) { return false; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) { return false; }
/*    */ 
/*    */   
/* 61 */   protected static final AxisAlignedBB AABB_MAIN = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
/* 62 */   protected static final AxisAlignedBB AABB_BASE = new AxisAlignedBB(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.125D, 0.8125D);
/* 63 */   protected static final AxisAlignedBB AABB_TOP = new AxisAlignedBB(0.25D, 0.125D, 0.25D, 0.75D, 0.6875D, 0.75D);
/* 64 */   protected static final AxisAlignedBB AABB_ORB = new AxisAlignedBB(0.3125D, 0.75D, 0.3125D, 0.625D, 1.0D, 0.625D);
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_185477_a(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB AABB, List<AxisAlignedBB> list, Entity p_185477_6_, boolean isActualState) {
/* 69 */     func_185492_a(pos, AABB, list, AABB_BASE);
/* 70 */     func_185492_a(pos, AABB, list, AABB_TOP);
/* 71 */     func_185492_a(pos, AABB, list, AABB_ORB);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 76 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return AABB_MAIN; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 86 */     if (world.field_72995_K) return true;
/*    */     
/* 88 */     player.openGui(Thaumcraft.instance, 22, world, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */     
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockVoidSiphon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */