/*     */ package thaumcraft.common.blocks.world.taint;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ public class BlockTaintLog
/*     */   extends BlockTC
/*     */   implements ITaintBlock {
/*  25 */   public static final PropertyEnum AXIS = PropertyEnum.func_177709_a("axis", EnumFacing.Axis.class);
/*     */ 
/*     */   
/*     */   public BlockTaintLog() {
/*  29 */     super(ThaumcraftMaterials.MATERIAL_TAINT, "taint_log");
/*  30 */     setHarvestLevel("axe", 0);
/*  31 */     func_149711_c(3.0F);
/*  32 */     func_149752_b(100.0F);
/*  33 */     func_149672_a(SoundsTC.GORE);
/*  34 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(AXIS, EnumFacing.Axis.Y));
/*  35 */     func_149675_a(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  40 */   public SoundType func_185467_w() { return SoundsTC.GORE; }
/*     */ 
/*     */ 
/*     */   
/*  44 */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) { return 4; }
/*     */ 
/*     */   
/*  47 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 4; }
/*     */ 
/*     */   
/*  50 */   public void die(World world, BlockPos pos, IBlockState blockState) { world.func_175656_a(pos, BlocksTC.fluxGoo.func_176223_P()); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180650_b(World world, BlockPos pos, IBlockState state, Random random) {
/*  55 */     if (!world.field_72995_K)
/*     */     {
/*     */       
/*  58 */       if (!TaintHelper.isNearTaintSeed(world, pos)) {
/*  59 */         die(world, pos, state);
/*     */       } else {
/*  61 */         TaintHelper.spreadFibres(world, pos);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase entity) { return super.func_180642_a(world, pos, side, hitX, hitY, hitZ, metadata, entity).func_177226_a(AXIS, side.func_176740_k()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   protected ItemStack func_180643_i(IBlockState state) { return new ItemStack(Item.func_150898_a(this), 1, func_180651_a(state)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/*  86 */     int axis = meta % 3;
/*  87 */     return func_176223_P().func_177226_a(AXIS, EnumFacing.Axis.values()[axis]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public int func_176201_c(IBlockState state) { return ((EnumFacing.Axis)state.func_177229_b(AXIS)).ordinal(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { AXIS }); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) { return true; }
/* 105 */   public boolean isWood(IBlockAccess world, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 110 */     byte b0 = 4;
/* 111 */     int i = b0 + 1;
/*     */     
/* 113 */     if (worldIn.func_175707_a(pos.func_177982_a(-i, -i, -i), pos.func_177982_a(i, i, i))) {
/*     */       
/* 115 */       Iterator iterator = BlockPos.func_177980_a(pos.func_177982_a(-b0, -b0, -b0), pos.func_177982_a(b0, b0, b0)).iterator();
/*     */       
/* 117 */       while (iterator.hasNext()) {
/*     */         
/* 119 */         BlockPos blockpos1 = (BlockPos)iterator.next();
/* 120 */         IBlockState iblockstate1 = worldIn.func_180495_p(blockpos1);
/*     */         
/* 122 */         if (iblockstate1.func_177230_c().isLeaves(iblockstate1, worldIn, blockpos1))
/*     */         {
/* 124 */           iblockstate1.func_177230_c().beginLeavesDecay(iblockstate1, worldIn, blockpos1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\taint\BlockTaintLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */