/*     */ package thaumcraft.common.blocks.basic;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSlab;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSlabTC
/*     */   extends BlockSlab
/*     */ {
/*  28 */   public static final PropertyEnum<Variant> VARIANT = PropertyEnum.func_177709_a("variant", Variant.class);
/*     */   
/*     */   boolean wood = false;
/*     */   
/*  32 */   Block drop = null;
/*     */ 
/*     */   
/*     */   protected BlockSlabTC(String name, Block b, boolean wood) {
/*  36 */     super(wood ? Material.field_151575_d : Material.field_151576_e);
/*  37 */     this.drop = b;
/*  38 */     this.wood = wood;
/*  39 */     func_149663_c(name);
/*  40 */     setRegistryName("thaumcraft", name);
/*     */     
/*  42 */     IBlockState iblockstate = this.field_176227_L.func_177621_b();
/*     */     
/*  44 */     if (!func_176552_j()) {
/*     */       
/*  46 */       iblockstate = iblockstate.func_177226_a(field_176554_a, BlockSlab.EnumBlockHalf.BOTTOM);
/*  47 */       func_149647_a(ConfigItems.TABTC);
/*     */     } 
/*     */     
/*  50 */     func_149672_a(wood ? SoundType.field_185848_a : SoundType.field_185851_d);
/*  51 */     func_180632_j(iblockstate.func_177226_a(VARIANT, Variant.DEFAULT));
/*  52 */     this.field_149783_u = !func_176552_j();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return (this.drop == null) ? Item.func_150898_a(state.func_177230_c()) : Item.func_150898_a(this.drop); }
/*     */ 
/*     */   
/*     */   public static class Double
/*     */     extends BlockSlabTC
/*     */   {
/*  64 */     public Double(String name, Block b, boolean wood) { super(name, b, wood); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     public boolean func_176552_j() { return true; }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Half
/*     */     extends BlockSlabTC
/*     */   {
/*  76 */     public Half(String name, Block b, boolean wood) { super(name, b, wood); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     public boolean func_176552_j() { return false; }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  89 */   public ItemStack func_185473_a(World worldIn, BlockPos pos, IBlockState state) { return new ItemStack(state.func_177230_c()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/*  95 */     IBlockState iblockstate = func_176223_P().func_177226_a(VARIANT, Variant.DEFAULT);
/*     */     
/*  97 */     if (!func_176552_j())
/*     */     {
/*  99 */       iblockstate = iblockstate.func_177226_a(field_176554_a, ((meta & 0x8) == 0) ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
/*     */     }
/*     */     
/* 102 */     return iblockstate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_176201_c(IBlockState state) {
/* 108 */     int i = 0;
/*     */     
/* 110 */     if (!func_176552_j() && state.func_177229_b(field_176554_a) == BlockSlab.EnumBlockHalf.TOP)
/*     */     {
/* 112 */       i |= 0x8;
/*     */     }
/*     */     
/* 115 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   protected BlockStateContainer func_180661_e() { return func_176552_j() ? new BlockStateContainer(this, new IProperty[] { VARIANT }) : new BlockStateContainer(this, new IProperty[] { field_176554_a, VARIANT }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public boolean func_176552_j() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public String func_150002_b(int meta) { return func_149739_a(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public IProperty<?> func_176551_l() { return VARIANT; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public Comparable<?> func_185674_a(ItemStack stack) { return Variant.DEFAULT; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) { return this.wood ? 20 : super.getFlammability(world, pos, face); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) { return this.wood ? 5 : super.getFireSpreadSpeed(world, pos, face); }
/*     */   
/*     */   public enum Variant
/*     */     implements IStringSerializable
/*     */   {
/* 164 */     DEFAULT;
/*     */ 
/*     */ 
/*     */     
/* 168 */     public String func_176610_l() { return "default"; }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockSlabTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */