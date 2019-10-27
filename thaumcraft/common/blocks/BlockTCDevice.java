/*     */ package thaumcraft.common.blocks;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.common.lib.utils.BlockStateUtils;
/*     */ 
/*     */ 
/*     */ public class BlockTCDevice
/*     */   extends BlockTCTile
/*     */ {
/*     */   public BlockTCDevice(Material mat, Class tc, String name) {
/*  21 */     super(mat, tc, name);
/*  22 */     IBlockState bs = this.field_176227_L.func_177621_b();
/*  23 */     if (this instanceof IBlockFacingHorizontal) { bs.func_177226_a(IBlockFacingHorizontal.FACING, EnumFacing.NORTH); }
/*  24 */     else if (this instanceof IBlockFacing) { bs.func_177226_a(IBlockFacing.FACING, EnumFacing.UP); }
/*  25 */      if (this instanceof IBlockEnabled) bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)); 
/*  26 */     func_180632_j(bs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
/*  32 */     IBlockState state = world.func_180495_p(pos);
/*  33 */     for (UnmodifiableIterator unmodifiableIterator = state.func_177228_b().keySet().iterator(); unmodifiableIterator.hasNext(); ) { IProperty<?> prop = (IProperty)unmodifiableIterator.next();
/*     */       
/*  35 */       if (prop.func_177701_a().equals("facing")) {
/*     */         
/*  37 */         world.func_175656_a(pos, state.func_177231_a(prop));
/*  38 */         return true;
/*     */       }  }
/*     */     
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_176213_c(World worldIn, BlockPos pos, IBlockState state) {
/*  47 */     super.func_176213_c(worldIn, pos, state);
/*  48 */     updateState(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos frompos) {
/*  55 */     updateState(worldIn, pos, state);
/*  56 */     super.func_189540_a(state, worldIn, pos, blockIn, frompos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_180642_a(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  62 */     IBlockState bs = func_176223_P();
/*  63 */     if (this instanceof IBlockFacingHorizontal) bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, placer.func_70093_af() ? placer.func_174811_aO() : placer.func_174811_aO().func_176734_d()); 
/*  64 */     if (this instanceof IBlockFacing) bs = bs.func_177226_a(IBlockFacing.FACING, placer.func_70093_af() ? 
/*  65 */           EnumFacing.func_190914_a(pos, placer).func_176734_d() : EnumFacing.func_190914_a(pos, placer)); 
/*  66 */     if (this instanceof IBlockEnabled) bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(true)); 
/*  67 */     return bs;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateState(World worldIn, BlockPos pos, IBlockState state) {
/*  72 */     if (this instanceof IBlockEnabled) {
/*  73 */       boolean flag = !worldIn.func_175640_z(pos);
/*     */       
/*  75 */       if (flag != ((Boolean)state.func_177229_b(IBlockEnabled.ENABLED)).booleanValue())
/*     */       {
/*  77 */         worldIn.func_180501_a(pos, state.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(flag)), 3);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void updateFacing(World world, BlockPos pos, EnumFacing face) {
/*  83 */     if (this instanceof IBlockFacing || this instanceof IBlockFacingHorizontal) {
/*  84 */       if (face == BlockStateUtils.getFacing(world.func_180495_p(pos)))
/*  85 */         return;  if (this instanceof IBlockFacingHorizontal && face.func_176736_b() >= 0)
/*  86 */         world.func_180501_a(pos, world.func_180495_p(pos).func_177226_a(IBlockFacingHorizontal.FACING, face), 3); 
/*  87 */       if (this instanceof IBlockFacing) {
/*  88 */         world.func_180501_a(pos, world.func_180495_p(pos).func_177226_a(IBlockFacing.FACING, face), 3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/*  95 */     IBlockState bs = func_176223_P();
/*     */     try {
/*  97 */       if (this instanceof IBlockFacingHorizontal) bs = bs.func_177226_a(IBlockFacingHorizontal.FACING, BlockStateUtils.getFacing(meta)); 
/*  98 */       if (this instanceof IBlockFacing) bs = bs.func_177226_a(IBlockFacing.FACING, BlockStateUtils.getFacing(meta)); 
/*  99 */       if (this instanceof IBlockEnabled) bs = bs.func_177226_a(IBlockEnabled.ENABLED, Boolean.valueOf(BlockStateUtils.isEnabled(meta))); 
/* 100 */     } catch (Exception exception) {}
/* 101 */     return bs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_176201_c(IBlockState state) {
/* 107 */     byte b0 = 0;
/*     */     
/* 109 */     int i = (this instanceof IBlockFacingHorizontal) ? (b0 | ((EnumFacing)state.func_177229_b(IBlockFacingHorizontal.FACING)).func_176745_a()) : ((this instanceof IBlockFacing) ? (b0 | ((EnumFacing)state.func_177229_b(IBlockFacing.FACING)).func_176745_a()) : b0);
/*     */ 
/*     */     
/* 112 */     if (this instanceof IBlockEnabled && !((Boolean)state.func_177229_b(IBlockEnabled.ENABLED)).booleanValue())
/*     */     {
/* 114 */       i |= 0x8;
/*     */     }
/*     */     
/* 117 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockStateContainer func_180661_e() {
/* 123 */     ArrayList<IProperty> ip = new ArrayList<IProperty>();
/* 124 */     if (this instanceof IBlockFacingHorizontal) ip.add(IBlockFacingHorizontal.FACING); 
/* 125 */     if (this instanceof IBlockFacing) ip.add(IBlockFacing.FACING); 
/* 126 */     if (this instanceof IBlockEnabled) ip.add(IBlockEnabled.ENABLED); 
/* 127 */     return (ip.size() == 0) ? super.func_180661_e() : new BlockStateContainer(this, (IProperty[])ip
/* 128 */         .toArray(new IProperty[ip.size()]));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\BlockTCDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */