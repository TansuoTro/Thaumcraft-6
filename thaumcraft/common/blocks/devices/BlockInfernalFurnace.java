/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockRenderLayer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTCDevice;
/*     */ import thaumcraft.common.blocks.IBlockFacingHorizontal;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ import thaumcraft.common.tiles.devices.TileInfernalFurnace;
/*     */ 
/*     */ public class BlockInfernalFurnace
/*     */   extends BlockTCDevice
/*     */   implements IBlockFacingHorizontal {
/*     */   public BlockInfernalFurnace() {
/*  31 */     super(Material.field_151576_e, TileInfernalFurnace.class, "infernal_furnace");
/*  32 */     func_149672_a(SoundType.field_185851_d);
/*  33 */     func_149715_a(0.9F);
/*  34 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  35 */     bs.func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.NORTH);
/*  36 */     func_180632_j(bs);
/*     */     
/*  38 */     func_149647_a(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) { return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {}
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  59 */   public BlockRenderLayer func_180664_k() { return BlockRenderLayer.CUTOUT_MIPPED; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  65 */     bs = func_176223_P();
/*  66 */     return bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_174811_aO().func_176734_d());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean ignore = false;
/*     */   
/*     */   public static void destroyFurnace(World worldIn, BlockPos pos, IBlockState state, BlockPos startpos) {
/*  73 */     if (ignore || worldIn.field_72995_K)
/*  74 */       return;  ignore = true;
/*  75 */     for (int a = -1; a <= 1; a++) {
/*  76 */       for (int b = -1; b <= 1; b++) {
/*  77 */         for (int c = -1; c <= 1; c++) {
/*  78 */           if (pos.func_177982_a(a, b, c) != startpos) {
/*  79 */             IBlockState bs = worldIn.func_180495_p(pos.func_177982_a(a, b, c));
/*  80 */             if (bs.func_177230_c() == BlocksTC.placeholderNetherbrick) {
/*  81 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150385_bj.func_176223_P());
/*     */             }
/*  83 */             if (bs.func_177230_c() == BlocksTC.placeholderObsidian)
/*  84 */               worldIn.func_175656_a(pos.func_177982_a(a, b, c), Blocks.field_150343_Z.func_176223_P()); 
/*     */           } 
/*     */         } 
/*     */       } 
/*  88 */     }  if (worldIn.func_175623_d(pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d())))
/*  89 */       worldIn.func_175656_a(pos.func_177972_a(BlockStateUtils.getFacing(state).func_176734_d()), Blocks.field_150411_aY.func_176223_P()); 
/*  90 */     worldIn.func_175656_a(pos, Blocks.field_150353_l.func_176223_P());
/*  91 */     ignore = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 102 */     destroyFurnace(worldIn, pos, state, pos);
/* 103 */     super.func_180663_b(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
/* 108 */     if (entity.field_70165_t < (pos.func_177958_n() + 0.3F)) entity.field_70159_w += 9.999999747378752E-5D; 
/* 109 */     if (entity.field_70165_t > (pos.func_177958_n() + 0.7F)) entity.field_70159_w -= 9.999999747378752E-5D; 
/* 110 */     if (entity.field_70161_v < (pos.func_177952_p() + 0.3F)) entity.field_70179_y += 9.999999747378752E-5D; 
/* 111 */     if (entity.field_70161_v > (pos.func_177952_p() + 0.7F)) entity.field_70179_y -= 9.999999747378752E-5D; 
/* 112 */     if (!world.field_72995_K && entity.field_70173_aa % 10 == 0) {
/* 113 */       if (entity instanceof EntityItem) {
/* 114 */         entity.field_70181_x = 0.02500000037252903D;
/* 115 */         if (entity.field_70122_E) {
/* 116 */           TileInfernalFurnace taf = (TileInfernalFurnace)world.func_175625_s(pos);
/* 117 */           ((EntityItem)entity).func_92058_a(taf.addItemsToInventory(((EntityItem)entity).func_92059_d()));
/*     */         }
/*     */       
/* 120 */       } else if (entity instanceof EntityLivingBase && 
/* 121 */         !entity.func_70045_F()) {
/*     */         
/* 123 */         entity.func_70097_a(DamageSource.field_76371_c, 3.0F);
/* 124 */         entity.func_70015_d(10);
/*     */       } 
/*     */     }
/*     */     
/* 128 */     super.func_180634_a(world, pos, state, entity);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockInfernalFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */