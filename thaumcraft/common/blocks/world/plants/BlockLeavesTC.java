/*     */ package thaumcraft.common.blocks.world.plants;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockLeaves;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockRenderLayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockLeavesTC
/*     */   extends BlockLeaves
/*     */ {
/*     */   public BlockLeavesTC(String name) {
/*  37 */     func_180632_j(this.field_176227_L.func_177621_b()
/*  38 */         .func_177226_a(field_176236_b, Boolean.valueOf(true))
/*  39 */         .func_177226_a(field_176237_a, Boolean.valueOf(true)));
/*  40 */     func_149647_a(ConfigItems.TABTC);
/*  41 */     func_149663_c(name);
/*  42 */     setRegistryName("thaumcraft", name);
/*     */   }
/*     */ 
/*     */   
/*  46 */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) { return 60; }
/*     */ 
/*     */   
/*  49 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return 30; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  55 */   public BlockRenderLayer func_180664_k() { return Blocks.field_150362_t.func_180664_k(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public boolean func_149662_c(IBlockState state) { return Blocks.field_150362_t.func_149662_c(state); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180633_a(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/*  68 */     super.func_180633_a(worldIn, pos, state, placer, stack);
/*     */     
/*  70 */     if (placer != null && placer instanceof net.minecraft.entity.player.EntityPlayer) {
/*  71 */       worldIn.func_175656_a(pos, state.func_177226_a(field_176237_a, Boolean.valueOf(false)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176225_a(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
/*  77 */     func_150122_b(!func_149662_c(blockState));
/*  78 */     return super.func_176225_a(blockState, blockAccess, pos, side);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public MapColor func_180659_g(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return (state.func_177230_c() == BlocksTC.leafSilverwood) ? MapColor.field_151674_s : super.func_180659_g(state, worldIn, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  97 */   public void func_149666_a(CreativeTabs tab, NonNullList<ItemStack> list) { list.add(new ItemStack(this)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   protected ItemStack func_180643_i(IBlockState state) { return new ItemStack(this); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 109 */     if (!worldIn.field_72995_K)
/*     */     {
/* 111 */       if (state.func_177230_c() == BlocksTC.leafSilverwood && ((Boolean)state.func_177229_b(field_176237_a)).booleanValue() && 
/* 112 */         AuraHandler.getVis(worldIn, pos) < AuraHandler.getAuraBase(worldIn, pos))
/*     */       {
/* 114 */         AuraHandler.addVis(worldIn, pos, 0.01F);
/*     */       }
/*     */     }
/* 117 */     super.func_180650_b(worldIn, pos, state, rand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/* 124 */     return func_176223_P()
/* 125 */       .func_177226_a(field_176237_a, Boolean.valueOf(((meta & 0x4) == 0)))
/* 126 */       .func_177226_a(field_176236_b, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_176201_c(IBlockState state) {
/* 132 */     int i = 0;
/* 133 */     if (!((Boolean)state.func_177229_b(field_176237_a)).booleanValue()) i |= 0x4; 
/* 134 */     if (((Boolean)state.func_177229_b(field_176236_b)).booleanValue()) i |= 0x8; 
/* 135 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 141 */   protected int func_176232_d(IBlockState state) { return 75; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_176234_a(World worldIn, BlockPos pos, IBlockState state, int chance) {
/* 146 */     if (state.func_177230_c() == BlocksTC.leafSilverwood && worldIn.field_73012_v.nextInt((int)(chance * 0.75D)) == 0)
/*     */     {
/* 148 */       func_180635_a(worldIn, pos, new ItemStack(ItemsTC.nuggets, 1, 5));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
/* 155 */     return (state.func_177230_c() == BlocksTC.leafSilverwood) ? 
/* 156 */       Item.func_150898_a(BlocksTC.saplingSilverwood) : 
/* 157 */       Item.func_150898_a(BlocksTC.saplingGreatwood);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { field_176236_b, field_176237_a }); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
/* 169 */     IBlockState state = world.func_180495_p(pos);
/* 170 */     return new ArrayList(Arrays.asList(new ItemStack[] { new ItemStack(this) }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public BlockPlanks.EnumType func_176233_b(int meta) { return null; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\plants\BlockLeavesTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */