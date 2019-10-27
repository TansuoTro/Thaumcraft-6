/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockVisBattery
/*     */   extends Block
/*     */ {
/*  29 */   public static final PropertyInteger CHARGE = PropertyInteger.func_177719_a("charge", 0, 10);
/*     */   
/*     */   public BlockVisBattery() {
/*  32 */     super(Material.field_151576_e);
/*  33 */     func_149663_c("vis_battery");
/*  34 */     setRegistryName("thaumcraft", "vis_battery");
/*  35 */     func_149711_c(0.5F);
/*  36 */     func_149672_a(SoundType.field_185851_d);
/*  37 */     func_149675_a(true);
/*  38 */     func_149647_a(ConfigItems.TABTC);
/*  39 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(CHARGE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  44 */   public int func_180651_a(IBlockState state) { return 0; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  49 */     if (!worldIn.field_72995_K) {
/*     */       
/*  51 */       int charge = func_176201_c(state);
/*  52 */       if (worldIn.func_175640_z(pos)) {
/*  53 */         if (charge > 0) {
/*  54 */           AuraHandler.addVis(worldIn, pos, 1.0F);
/*  55 */           worldIn.func_175656_a(pos, state.func_177226_a(CHARGE, Integer.valueOf(charge - 1)));
/*  56 */           worldIn.func_175684_a(pos, state.func_177230_c(), 5);
/*     */         } 
/*     */       } else {
/*  59 */         float aura = AuraHelper.getVis(worldIn, pos);
/*  60 */         int base = AuraHelper.getAuraBase(worldIn, pos);
/*  61 */         if (charge < 10 && aura > base * 0.9D && aura > 1.0F) {
/*  62 */           AuraHandler.drainVis(worldIn, pos, 1.0F, false);
/*  63 */           worldIn.func_175656_a(pos, state.func_177226_a(CHARGE, Integer.valueOf(charge + 1)));
/*  64 */           worldIn.func_175684_a(pos, state.func_177230_c(), 100 + rand.nextInt(100));
/*     */         }
/*  66 */         else if (charge > 0 && aura < base * 0.75D) {
/*  67 */           AuraHandler.addVis(worldIn, pos, 1.0F);
/*  68 */           worldIn.func_175656_a(pos, state.func_177226_a(CHARGE, Integer.valueOf(charge - 1)));
/*  69 */           worldIn.func_175684_a(pos, state.func_177230_c(), 20 + rand.nextInt(20));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
/*  78 */     if (worldIn.func_175640_z(pos))
/*     */     {
/*  80 */       worldIn.func_175684_a(pos, this, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public boolean func_149740_M(IBlockState state) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public int func_180641_l(IBlockState state, World world, BlockPos pos) { return func_176201_c(state); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { return state.func_177230_c().func_176201_c(state); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_185484_c(IBlockState state, IBlockAccess source, BlockPos pos) {
/* 101 */     int i = source.func_175626_b(pos, state.getLightValue(source, pos));
/* 102 */     int j = 180;
/* 103 */     int k = i & 0xFF;
/* 104 */     int l = j & 0xFF;
/* 105 */     int i1 = i >> 16 & 0xFF;
/* 106 */     int j1 = j >> 16 & 0xFF;
/* 107 */     return ((k > l) ? k : l) | ((i1 > j1) ? i1 : j1) << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   protected BlockStateContainer func_180661_e() { return new BlockStateContainer(this, new IProperty[] { CHARGE }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public IBlockState func_176203_a(int meta) { return func_176223_P().func_177226_a(CHARGE, Integer.valueOf(meta)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int func_176201_c(IBlockState state) { return ((Integer)state.func_177229_b(CHARGE)).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 134 */   public void func_149666_a(CreativeTabs tab, NonNullList<ItemStack> list) { list.add(new ItemStack(this, 1, 0)); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockVisBattery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */