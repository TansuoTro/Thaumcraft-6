/*     */ package thaumcraft.common.blocks.world.plants;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
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
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.blocks.BlockTC;
/*     */ 
/*     */ public class BlockLogsTC
/*     */   extends BlockTC {
/*  23 */   public static final PropertyEnum AXIS = PropertyEnum.func_177709_a("axis", EnumFacing.Axis.class);
/*     */ 
/*     */   
/*     */   public BlockLogsTC(String name) {
/*  27 */     super(Material.field_151575_d, name);
/*  28 */     setHarvestLevel("axe", 0);
/*  29 */     func_149711_c(2.0F);
/*  30 */     func_149752_b(5.0F);
/*  31 */     func_149672_a(SoundType.field_185848_a);
/*  32 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(AXIS, EnumFacing.Axis.Y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public IBlockState func_180642_a(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int metadata, EntityLivingBase entity) { return super.func_180642_a(world, pos, side, hitX, hitY, hitZ, metadata, entity).func_177226_a(AXIS, side.func_176740_k()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { return (state.func_177230_c() == BlocksTC.logSilverwood) ? 5 : super.getLightValue(state, world, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
/*  49 */     IBlockState state = world.func_180495_p(pos);
/*  50 */     for (UnmodifiableIterator unmodifiableIterator = state.func_177228_b().keySet().iterator(); unmodifiableIterator.hasNext(); ) { IProperty<?> prop = (IProperty)unmodifiableIterator.next();
/*     */       
/*  52 */       if (prop.func_177701_a().equals("axis")) {
/*     */         
/*  54 */         world.func_175656_a(pos, state.func_177231_a(prop));
/*  55 */         return true;
/*     */       }  }
/*     */     
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   protected ItemStack func_180643_i(IBlockState state) { return new ItemStack(Item.func_150898_a(this)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/*  70 */     int axis = meta % 3;
/*  71 */     return func_176223_P().func_177226_a(AXIS, EnumFacing.Axis.values()[axis]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public int func_176201_c(IBlockState state) { return ((EnumFacing.Axis)state.func_177229_b(AXIS)).ordinal(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { AXIS }); }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) { return true; }
/*     */ 
/*     */   
/*  90 */   public boolean isWood(IBlockAccess world, BlockPos pos) { return true; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/*  95 */     byte b0 = 4;
/*  96 */     int i = b0 + 1;
/*     */     
/*  98 */     if (worldIn.func_175707_a(pos.func_177982_a(-i, -i, -i), pos.func_177982_a(i, i, i))) {
/*     */       
/* 100 */       Iterator iterator = BlockPos.func_177980_a(pos.func_177982_a(-b0, -b0, -b0), pos.func_177982_a(b0, b0, b0)).iterator();
/*     */       
/* 102 */       while (iterator.hasNext()) {
/*     */         
/* 104 */         BlockPos blockpos1 = (BlockPos)iterator.next();
/* 105 */         IBlockState iblockstate1 = worldIn.func_180495_p(blockpos1);
/*     */         
/* 107 */         if (iblockstate1.func_177230_c().isLeaves(iblockstate1, worldIn, blockpos1))
/*     */         {
/* 109 */           iblockstate1.func_177230_c().beginLeavesDecay(iblockstate1, worldIn, blockpos1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 116 */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) { return 5; }
/*     */ 
/*     */   
/* 119 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 5; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\plants\BlockLogsTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */