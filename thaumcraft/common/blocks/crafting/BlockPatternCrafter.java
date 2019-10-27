/*     */ package thaumcraft.common.blocks.crafting;
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.codechicken.lib.raytracer.ExtendedMOP;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.blocks.IBlockEnabled;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.crafting.TilePatternCrafter;
/*     */ 
/*     */ @EventBusSubscriber({Side.CLIENT})
/*     */ public class BlockPatternCrafter extends BlockTCDevice implements IBlockFacingHorizontal, IBlockEnabled {
/*     */   private RayTracer rayTracer;
/*     */   
/*     */   public int func_180651_a(IBlockState state) { return 0; }
/*     */   
/*     */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */   
/*     */   public BlockPatternCrafter() {
/*  46 */     super(Material.field_151573_f, TilePatternCrafter.class, "pattern_crafter");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     this.rayTracer = new RayTracer();
/*     */     func_149672_a(SoundType.field_185852_e);
/*     */   } public boolean func_149686_d(IBlockState state) { return false; } public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public void onBlockHighlight(DrawBlockHighlightEvent event) {
/* 140 */     if ((event.getTarget()).field_72313_a == RayTraceResult.Type.BLOCK && 
/* 141 */       (event.getPlayer()).field_70170_p.func_180495_p(event.getTarget().func_178782_a()).func_177230_c() == this)
/* 142 */       RayTracer.retraceBlock((event.getPlayer()).field_70170_p, event.getPlayer(), event.getTarget().func_178782_a()); 
/*     */   } public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) { return false; }
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*     */     bs = func_176223_P();
/*     */     return bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_174811_aO());
/*     */   }
/*     */   public RayTraceResult func_180636_a(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end) {
/* 149 */     TileEntity tile = world.func_175625_s(pos);
/* 150 */     if (tile == null || !(tile instanceof TilePatternCrafter)) {
/* 151 */       return super.func_180636_a(state, world, pos, start, end);
/*     */     }
/* 153 */     List<IndexedCuboid6> cuboids = new LinkedList<IndexedCuboid6>();
/* 154 */     if (tile instanceof TilePatternCrafter) {
/* 155 */       ((TilePatternCrafter)tile).addTraceableCuboids(cuboids);
/*     */     }
/* 157 */     ArrayList<ExtendedMOP> list = new ArrayList<ExtendedMOP>();
/* 158 */     this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(pos), this, list);
/* 159 */     return (list.size() > 0) ? (RayTraceResult)list.get(0) : super.func_180636_a(state, world, pos, start, end);
/*     */   }
/*     */   
/*     */   public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*     */     RayTraceResult hit = RayTracer.retraceBlock(world, player, pos);
/*     */     if (hit == null)
/*     */       return super.func_180639_a(world, pos, state, player, hand, side, hitX, hitY, hitZ); 
/*     */     TileEntity tile = world.func_175625_s(pos);
/*     */     if (hit.subHit == 0 && tile instanceof TilePatternCrafter) {
/*     */       if (!world.field_72995_K) {
/*     */         ((TilePatternCrafter)tile).cycle();
/*     */         world.func_184148_a(null, pos.func_177958_n() + 0.5D, pos.func_177956_o() + 0.5D, pos.func_177952_p() + 0.5D, SoundsTC.key, SoundCategory.BLOCKS, 0.5F, 1.0F);
/*     */       } 
/*     */       return true;
/*     */     } 
/*     */     return super.func_180639_a(world, pos, state, player, hand, side, hitX, hitY, hitZ);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_180640_a(IBlockState state, World world, BlockPos pos) {
/*     */     TileEntity tile = world.func_175625_s(pos);
/*     */     if (tile != null && tile instanceof TilePatternCrafter) {
/*     */       RayTraceResult hit = RayTracer.retraceBlock(world, (Minecraft.func_71410_x()).field_71439_g, pos);
/*     */       if (hit != null && hit.subHit == 0) {
/*     */         Cuboid6 cubeoid = ((TilePatternCrafter)tile).getCuboidByFacing(BlockStateUtils.getFacing(tile.func_145832_p()));
/*     */         Vector3 v = new Vector3(pos);
/*     */         Cuboid6 c = cubeoid.sub(v);
/*     */         return (new AxisAlignedBB((float)c.min.x, (float)c.min.y, (float)c.min.z, (float)c.max.x, (float)c.max.y, (float)c.max.z)).func_186670_a(pos);
/*     */       } 
/*     */     } 
/*     */     return super.func_180640_a(state, world, pos);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return field_185505_j; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\crafting\BlockPatternCrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */