/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockRenderLayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.DrawBlockHighlightEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.codechicken.lib.raytracer.ExtendedMOP;
/*     */ import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
/*     */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*     */ import thaumcraft.codechicken.lib.vec.BlockCoord;
/*     */ import thaumcraft.codechicken.lib.vec.Cuboid6;
/*     */ import thaumcraft.codechicken.lib.vec.Vector3;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber({Side.CLIENT})
/*     */ public class BlockTaintFibre
/*     */   extends Block
/*     */   implements ITaintBlock
/*     */ {
/*  56 */   public static final PropertyBool NORTH = PropertyBool.func_177716_a("north");
/*  57 */   public static final PropertyBool EAST = PropertyBool.func_177716_a("east");
/*  58 */   public static final PropertyBool SOUTH = PropertyBool.func_177716_a("south");
/*  59 */   public static final PropertyBool WEST = PropertyBool.func_177716_a("west");
/*  60 */   public static final PropertyBool UP = PropertyBool.func_177716_a("up");
/*  61 */   public static final PropertyBool DOWN = PropertyBool.func_177716_a("down");
/*  62 */   public static final PropertyBool GROWTH1 = PropertyBool.func_177716_a("growth1");
/*  63 */   public static final PropertyBool GROWTH2 = PropertyBool.func_177716_a("growth2");
/*  64 */   public static final PropertyBool GROWTH3 = PropertyBool.func_177716_a("growth3");
/*  65 */   public static final PropertyBool GROWTH4 = PropertyBool.func_177716_a("growth4");
/*     */   private RayTracer rayTracer;
/*     */   
/*  68 */   public BlockTaintFibre() { super(ThaumcraftMaterials.MATERIAL_TAINT);
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
/* 241 */     this.rayTracer = new RayTracer(); func_149663_c("taint_fibre"); setRegistryName("thaumcraft", "taint_fibre"); func_149711_c(1.0F); func_149672_a(SoundsTC.GORE); func_149675_a(true); func_149647_a(ConfigItems.TABTC); func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(NORTH, Boolean.valueOf(false)).func_177226_a(EAST, Boolean.valueOf(false)).func_177226_a(SOUTH, Boolean.valueOf(false)).func_177226_a(WEST, Boolean.valueOf(false)).func_177226_a(UP, Boolean.valueOf(false)).func_177226_a(DOWN, Boolean.valueOf(false)).func_177226_a(GROWTH1, Boolean.valueOf(false)).func_177226_a(GROWTH2, Boolean.valueOf(false)).func_177226_a(GROWTH3, Boolean.valueOf(false)).func_177226_a(GROWTH4, Boolean.valueOf(false))); } public SoundType func_185467_w() { return SoundsTC.GORE; } public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; } public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) { return 3; } public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 3; } public MapColor func_180659_g(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return MapColor.field_151678_z; } public void die(World world, BlockPos pos, IBlockState blockState) { world.func_175698_g(pos); } protected boolean func_149700_E() { return false; } public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); } public void func_180653_a(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) { state = func_176221_a(state, worldIn, pos);
/*     */     if (state instanceof IBlockState && ((Boolean)state.func_177229_b(GROWTH3)).booleanValue()) {
/*     */       if (worldIn.field_73012_v.nextInt(5) <= fortune)
/*     */         func_180635_a(worldIn, pos, ConfigItems.FLUX_CRYSTAL.func_77946_l()); 
/*     */       AuraHelper.polluteAura(worldIn, pos, 1.0F, true);
/*     */     }  }
/* 247 */   @SideOnly(Side.CLIENT) @SubscribeEvent public void onBlockHighlight(DrawBlockHighlightEvent event) { if ((event.getTarget()).field_72313_a == RayTraceResult.Type.BLOCK && 
/* 248 */       (event.getPlayer()).field_70170_p.func_180495_p(event.getTarget().func_178782_a()).func_177230_c() == this)
/* 249 */       RayTracer.retraceBlock((event.getPlayer()).field_70170_p, event.getPlayer(), event.getTarget().func_178782_a());  }
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random) { if (!world.field_72995_K) { state = func_176221_a(state, world, pos); if (state instanceof IBlockState)
/*     */         if (!((Boolean)state.func_177229_b(GROWTH1)).booleanValue() && !((Boolean)state.func_177229_b(GROWTH2)).booleanValue() && !((Boolean)state.func_177229_b(GROWTH3)).booleanValue() && !((Boolean)state.func_177229_b(GROWTH4)).booleanValue() && isOnlyAdjacentToTaint(world, pos)) { die(world, pos, state); } else if (!TaintHelper.isNearTaintSeed(world, pos)) { die(world, pos, state); }
/*     */         else { TaintHelper.spreadFibres(world, pos); }
/*     */           }
/*     */      }
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos pos2) { state = func_176221_a(state, worldIn, pos); if (state instanceof IBlockState && !((Boolean)state.func_177229_b(GROWTH1)).booleanValue() && !((Boolean)state.func_177229_b(GROWTH2)).booleanValue() && !((Boolean)state.func_177229_b(GROWTH3)).booleanValue() && !((Boolean)state.func_177229_b(GROWTH4)).booleanValue() && isOnlyAdjacentToTaint(worldIn, pos))
/* 256 */       worldIn.func_175698_g(pos);  } public RayTraceResult func_180636_a(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end) { List<IndexedCuboid6> cuboids = new LinkedList<IndexedCuboid6>();
/*     */     
/* 258 */     if (drawAt(world, pos.func_177984_a(), EnumFacing.UP)) {
/* 259 */       cuboids.add(new IndexedCuboid6(Integer.valueOf(0), new Cuboid6(AABB_UP.func_186670_a(pos))));
/*     */     }
/* 261 */     if (drawAt(world, pos.func_177977_b(), EnumFacing.DOWN)) {
/* 262 */       cuboids.add(new IndexedCuboid6(Integer.valueOf(1), new Cuboid6(AABB_DOWN.func_186670_a(pos))));
/*     */     }
/* 264 */     if (drawAt(world, pos.func_177974_f(), EnumFacing.EAST)) {
/* 265 */       cuboids.add(new IndexedCuboid6(Integer.valueOf(2), new Cuboid6(AABB_EAST.func_186670_a(pos))));
/*     */     }
/* 267 */     if (drawAt(world, pos.func_177976_e(), EnumFacing.WEST)) {
/* 268 */       cuboids.add(new IndexedCuboid6(Integer.valueOf(3), new Cuboid6(AABB_WEST.func_186670_a(pos))));
/*     */     }
/* 270 */     if (drawAt(world, pos.func_177968_d(), EnumFacing.SOUTH)) {
/* 271 */       cuboids.add(new IndexedCuboid6(Integer.valueOf(4), new Cuboid6(AABB_SOUTH.func_186670_a(pos))));
/*     */     }
/* 273 */     if (drawAt(world, pos.func_177978_c(), EnumFacing.NORTH)) {
/* 274 */       cuboids.add(new IndexedCuboid6(Integer.valueOf(5), new Cuboid6(AABB_NORTH.func_186670_a(pos))));
/*     */     }
/*     */     
/* 277 */     IBlockState ss = func_176221_a(world.func_180495_p(pos), world, pos);
/* 278 */     if (ss.func_177230_c() == this && ss instanceof IBlockState) {
/* 279 */       if (((Boolean)ss.func_177229_b(GROWTH1)).booleanValue()) {
/* 280 */         cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6((new AxisAlignedBB(0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.8999999761581421D, 0.4000000059604645D, 0.8999999761581421D)).func_186670_a(pos))));
/*     */       }
/* 282 */       else if (((Boolean)ss.func_177229_b(GROWTH2)).booleanValue()) {
/* 283 */         cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6((new AxisAlignedBB(0.20000000298023224D, 0.0D, 0.20000000298023224D, 0.800000011920929D, 1.0D, 0.800000011920929D)).func_186670_a(pos))));
/*     */       }
/* 285 */       else if (((Boolean)ss.func_177229_b(GROWTH3)).booleanValue()) {
/* 286 */         cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6((new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.3125D, 0.75D)).func_186670_a(pos))));
/*     */       }
/* 288 */       else if (((Boolean)ss.func_177229_b(GROWTH4)).booleanValue()) {
/* 289 */         cuboids.add(new IndexedCuboid6(Integer.valueOf(6), new Cuboid6((new AxisAlignedBB(0.10000000149011612D, 0.30000001192092896D, 0.10000000149011612D, 0.8999999761581421D, 1.0D, 0.8999999761581421D)).func_186670_a(pos))));
/*     */       } 
/*     */     }
/*     */     
/* 293 */     ArrayList<ExtendedMOP> list = new ArrayList<ExtendedMOP>();
/* 294 */     this.rayTracer.rayTraceCuboids(new Vector3(start), new Vector3(end), cuboids, new BlockCoord(pos), this, list);
/* 295 */     return (list.size() > 0) ? (RayTraceResult)list.get(0) : super.func_180636_a(state, world, pos, start, end); }
/*     */   public static int getAdjacentTaint(IBlockAccess world, BlockPos pos) { int count = 0; for (EnumFacing dir : EnumFacing.field_82609_l) { if (world.func_180495_p(pos.func_177972_a(dir)).func_185904_a() != ThaumcraftMaterials.MATERIAL_TAINT) count++;  }  return count; }
/*     */   public static boolean isOnlyAdjacentToTaint(World world, BlockPos pos) { for (EnumFacing dir : EnumFacing.field_82609_l) { if (!world.func_175623_d(pos.func_177972_a(dir)) && world.func_180495_p(pos.func_177972_a(dir)).func_185904_a() != ThaumcraftMaterials.MATERIAL_TAINT && world.func_180495_p(pos.func_177972_a(dir)).func_177230_c().isSideSolid(world.func_180495_p(pos.func_177972_a(dir)), world, pos.func_177972_a(dir), dir.func_176734_d())) return false;  }  return true; }
/*     */   public static boolean isHemmedByTaint(World world, BlockPos pos) { int c = 0; for (EnumFacing dir : EnumFacing.field_82609_l) { IBlockState block = world.func_180495_p(pos.func_177972_a(dir)); if (block.func_185904_a() == ThaumcraftMaterials.MATERIAL_TAINT) { c++; } else if (world.func_175623_d(pos.func_177972_a(dir))) { c--; } else if (!block.func_185904_a().func_76224_d() && !block.isSideSolid(world, pos.func_177972_a(dir), dir.func_176734_d())) { c--; }  }  return (c > 0); }
/*     */   public void func_176199_a(World world, BlockPos pos, Entity entity) { if (!world.field_72995_K && entity instanceof EntityLivingBase && !((EntityLivingBase)entity).func_70662_br() && world.field_73012_v.nextInt(750) == 0) ((EntityLivingBase)entity).func_70690_d(new PotionEffect(PotionFluxTaint.instance, 200, 0, false, true));  }
/* 300 */   public boolean func_189539_a(IBlockState state, World worldIn, BlockPos pos, int eventID, int eventParam) { if (eventID == 1) { if (worldIn.field_72995_K) worldIn.func_184133_a(null, pos, SoundEvents.field_187540_ab, SoundCategory.BLOCKS, 0.1F, 0.9F + worldIn.field_73012_v.nextFloat() * 0.2F);  return true; }  return super.func_189539_a(state, worldIn, pos, eventID, eventParam); } @SideOnly(Side.CLIENT) public BlockRenderLayer func_180664_k() { return BlockRenderLayer.CUTOUT; } public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) { return false; } private boolean drawAt(IBlockAccess worldIn, BlockPos pos, EnumFacing side) { IBlockState b = worldIn.func_180495_p(pos); return (b.func_177230_c() != BlocksTC.taintFibre && b.func_177230_c() != BlocksTC.taintFeature && b.isSideSolid(worldIn, pos, side.func_176734_d())); } public AxisAlignedBB func_185496_a(IBlockState s, IBlockAccess source, BlockPos pos) { return AABB_EMPTY; }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_180640_a(IBlockState s, World world, BlockPos pos) {
/* 306 */     IBlockState state = func_176221_a(world.func_180495_p(pos), world, pos);
/* 307 */     if (state.func_177230_c() == this && state instanceof IBlockState) {
/* 308 */       if (((Boolean)state.func_177229_b(GROWTH1)).booleanValue()) {
/* 309 */         return (new AxisAlignedBB(0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.8999999761581421D, 0.4000000059604645D, 0.8999999761581421D)).func_186670_a(pos);
/*     */       }
/* 311 */       if (((Boolean)state.func_177229_b(GROWTH2)).booleanValue()) {
/* 312 */         return (new AxisAlignedBB(0.20000000298023224D, 0.0D, 0.20000000298023224D, 0.800000011920929D, 1.0D, 0.800000011920929D)).func_186670_a(pos);
/*     */       }
/* 314 */       if (((Boolean)state.func_177229_b(GROWTH3)).booleanValue()) {
/* 315 */         return (new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.3125D, 0.75D)).func_186670_a(pos);
/*     */       }
/* 317 */       if (((Boolean)state.func_177229_b(GROWTH4)).booleanValue()) {
/* 318 */         return (new AxisAlignedBB(0.10000000149011612D, 0.30000001192092896D, 0.10000000149011612D, 0.8999999761581421D, 1.0D, 0.8999999761581421D)).func_186670_a(pos);
/*     */       }
/*     */     } 
/* 321 */     RayTraceResult hit = RayTracer.retraceBlock(world, (Minecraft.func_71410_x()).field_71439_g, pos);
/* 322 */     if (hit != null) {
/* 323 */       switch (hit.subHit) { case 0:
/* 324 */           return AABB_UP.func_186670_a(pos);
/* 325 */         case 1: return AABB_DOWN.func_186670_a(pos);
/* 326 */         case 2: return AABB_EAST.func_186670_a(pos);
/* 327 */         case 3: return AABB_WEST.func_186670_a(pos);
/* 328 */         case 4: return AABB_SOUTH.func_186670_a(pos);
/* 329 */         case 5: return AABB_NORTH.func_186670_a(pos); }
/*     */     
/*     */     }
/* 332 */     return AABB_EMPTY;
/*     */   }
/*     */   
/* 335 */   protected static final AxisAlignedBB AABB_EMPTY = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/* 336 */   protected static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.0D, 0.949999988079071D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 337 */   protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.05000000074505806D, 1.0D);
/* 338 */   protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.949999988079071D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 339 */   protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.05000000074505806D, 1.0D, 1.0D);
/* 340 */   protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.949999988079071D, 1.0D, 1.0D, 1.0D);
/* 341 */   protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.05000000074505806D);
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_185477_a(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
/* 346 */     if (drawAt(worldIn, pos.func_177984_a(), EnumFacing.UP)) {
/* 347 */       func_185492_a(pos, entityBox, collidingBoxes, AABB_UP);
/*     */     }
/* 349 */     if (drawAt(worldIn, pos.func_177977_b(), EnumFacing.DOWN)) {
/* 350 */       func_185492_a(pos, entityBox, collidingBoxes, AABB_DOWN);
/*     */     }
/* 352 */     if (drawAt(worldIn, pos.func_177974_f(), EnumFacing.EAST)) {
/* 353 */       func_185492_a(pos, entityBox, collidingBoxes, AABB_EAST);
/*     */     }
/* 355 */     if (drawAt(worldIn, pos.func_177976_e(), EnumFacing.WEST)) {
/* 356 */       func_185492_a(pos, entityBox, collidingBoxes, AABB_WEST);
/*     */     }
/* 358 */     if (drawAt(worldIn, pos.func_177968_d(), EnumFacing.SOUTH)) {
/* 359 */       func_185492_a(pos, entityBox, collidingBoxes, AABB_SOUTH);
/*     */     }
/* 361 */     if (drawAt(worldIn, pos.func_177978_c(), EnumFacing.NORTH)) {
/* 362 */       func_185492_a(pos, entityBox, collidingBoxes, AABB_NORTH);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 368 */   public boolean func_176200_f(IBlockAccess worldIn, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 373 */   public boolean func_176205_b(IBlockAccess worldIn, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public int func_176201_c(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLightValue(IBlockState state2, IBlockAccess world, BlockPos pos) {
/* 398 */     IBlockState state = func_176221_a(world.func_180495_p(pos), world, pos);
/* 399 */     if (state.func_177230_c() == this && state instanceof IBlockState) {
/* 400 */       return ((Boolean)state.func_177229_b(GROWTH3)).booleanValue() ? 12 : ((((Boolean)state
/* 401 */         .func_177229_b(GROWTH2)).booleanValue() || ((Boolean)state
/* 402 */         .func_177229_b(GROWTH4)).booleanValue()) ? 6 : super.getLightValue(state2, world, pos));
/*     */     }
/* 404 */     return super.getLightValue(state2, world, pos);
/*     */   }
/*     */   
/*     */   private Boolean[] makeConnections(IBlockState state, IBlockAccess world, BlockPos pos) {
/* 408 */     Boolean[] cons = { null, null, null, null, null, (new Boolean[6][4] = (new Boolean[6][3] = (new Boolean[6][2] = (new Boolean[6][1] = (new Boolean[6][0] = Boolean.valueOf(false)).valueOf(false)).valueOf(false)).valueOf(false)).valueOf(false)).valueOf(false) };
/* 409 */     int a = 0;
/* 410 */     for (EnumFacing face : EnumFacing.field_82609_l) {
/* 411 */       if (drawAt(world, pos.func_177972_a(face), face)) {
/* 412 */         cons[a] = Boolean.valueOf(true);
/*     */       }
/* 414 */       a++;
/*     */     } 
/*     */     
/* 417 */     return cons;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 423 */     Boolean[] cons = makeConnections(state, worldIn, pos);
/*     */     
/* 425 */     boolean d = drawAt(worldIn, pos.func_177977_b(), EnumFacing.DOWN);
/* 426 */     boolean u = drawAt(worldIn, pos.func_177984_a(), EnumFacing.UP);
/* 427 */     int growth = 0;
/* 428 */     Random rand = new Random(pos.func_177986_g());
/* 429 */     int q = rand.nextInt(50);
/* 430 */     if (d)
/* 431 */       if (q < 4) {
/* 432 */         growth = 1;
/* 433 */       } else if (q == 4 || q == 5) {
/* 434 */         growth = 2;
/* 435 */       } else if (q == 6) {
/* 436 */         growth = 3;
/*     */       }  
/* 438 */     if (u && 
/* 439 */       q > 47) {
/* 440 */       growth = 4;
/*     */     }
/*     */     
/*     */     try {
/* 444 */       return state
/* 445 */         .func_177226_a(DOWN, cons[0])
/* 446 */         .func_177226_a(UP, cons[1])
/* 447 */         .func_177226_a(NORTH, cons[2])
/* 448 */         .func_177226_a(SOUTH, cons[3])
/* 449 */         .func_177226_a(WEST, cons[4])
/* 450 */         .func_177226_a(EAST, cons[5])
/* 451 */         .func_177226_a(GROWTH1, Boolean.valueOf((growth == 1)))
/* 452 */         .func_177226_a(GROWTH2, Boolean.valueOf((growth == 2)))
/* 453 */         .func_177226_a(GROWTH3, Boolean.valueOf((growth == 3)))
/* 454 */         .func_177226_a(GROWTH4, Boolean.valueOf((growth == 4)));
/* 455 */     } catch (Exception e) {
/* 456 */       return state;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 464 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST, UP, DOWN, GROWTH1, GROWTH2, GROWTH3, GROWTH4 }); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\taint\BlockTaintFibre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */