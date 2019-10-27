/*     */ package thaumcraft.common.blocks.world.ore;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockFaceShape;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumBlockRenderType;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.property.ExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IExtendedBlockState;
/*     */ import net.minecraftforge.common.property.IUnlistedProperty;
/*     */ import net.minecraftforge.common.property.Properties;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockCrystal
/*     */   extends Block
/*     */ {
/*  46 */   public static final PropertyInteger SIZE = PropertyInteger.func_177719_a("size", 0, 3);
/*  47 */   public static final PropertyInteger GENERATION = PropertyInteger.func_177719_a("gen", 1, 4);
/*  48 */   public static final IUnlistedProperty<Boolean> NORTH = new Properties.PropertyAdapter(PropertyBool.func_177716_a("north"));
/*  49 */   public static final IUnlistedProperty<Boolean> EAST = new Properties.PropertyAdapter(PropertyBool.func_177716_a("east"));
/*  50 */   public static final IUnlistedProperty<Boolean> SOUTH = new Properties.PropertyAdapter(PropertyBool.func_177716_a("south"));
/*  51 */   public static final IUnlistedProperty<Boolean> WEST = new Properties.PropertyAdapter(PropertyBool.func_177716_a("west"));
/*  52 */   public static final IUnlistedProperty<Boolean> UP = new Properties.PropertyAdapter(PropertyBool.func_177716_a("up"));
/*  53 */   public static final IUnlistedProperty<Boolean> DOWN = new Properties.PropertyAdapter(PropertyBool.func_177716_a("down"));
/*     */   
/*     */   public Aspect aspect;
/*     */   
/*     */   public BlockCrystal(String name, Aspect aspect) {
/*  58 */     super(Material.field_151592_s);
/*  59 */     func_149663_c(name);
/*  60 */     setRegistryName("thaumcraft", name);
/*  61 */     this.aspect = aspect;
/*  62 */     func_149711_c(0.25F);
/*  63 */     func_149672_a(SoundsTC.CRYSTAL);
/*  64 */     func_149675_a(true);
/*  65 */     func_149647_a(ConfigItems.TABTC);
/*  66 */     func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(SIZE, Integer.valueOf(0)).func_177226_a(GENERATION, Integer.valueOf(1)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public SoundType func_185467_w() { return SoundsTC.CRYSTAL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public BlockFaceShape func_193383_a(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) { return BlockFaceShape.UNDEFINED; }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public EnumBlockRenderType func_149645_b(IBlockState state) { return EnumBlockRenderType.MODEL; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return Item.func_150899_d(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   protected boolean func_149700_E() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
/*  97 */     List<ItemStack> ret = new ArrayList<ItemStack>();
/*     */     
/*  99 */     int count = getGrowth(state) + 1;
/* 100 */     for (int i = 0; i < count; i++)
/*     */     {
/* 102 */       ret.add(ThaumcraftApiHelper.makeCrystal(this.aspect));
/*     */     }
/* 104 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 109 */     if (!worldIn.field_72995_K && rand.nextInt(3 + getGeneration(state)) == 0) {
/*     */       
/* 111 */       int threshold = 10;
/* 112 */       int growth = getGrowth(state);
/* 113 */       int generation = getGeneration(state);
/* 114 */       if (this.aspect != Aspect.FLUX) {
/* 115 */         if (AuraHelper.getVis(worldIn, pos) <= threshold) {
/* 116 */           if (growth > 0) {
/* 117 */             worldIn.func_175656_a(pos, state.func_177226_a(SIZE, Integer.valueOf(growth - 1)));
/* 118 */             AuraHelper.addVis(worldIn, pos, threshold);
/*     */           }
/* 120 */           else if (BlockUtils.isBlockTouching(worldIn, pos, this)) {
/* 121 */             worldIn.func_175698_g(pos);
/* 122 */             AuraHandler.addVis(worldIn, pos, threshold);
/*     */           }
/*     */         
/*     */         }
/* 126 */         else if (AuraHelper.getVis(worldIn, pos) > (AuraHandler.getAuraBase(worldIn, pos) + threshold)) {
/* 127 */           if (growth < 3 && growth < (5 - generation) + pos.func_177986_g() % 3L) {
/* 128 */             if (AuraHelper.drainVis(worldIn, pos, threshold, false) > 0.0F) {
/* 129 */               worldIn.func_175656_a(pos, state.func_177226_a(SIZE, Integer.valueOf(growth + 1)));
/*     */             }
/*     */           }
/* 132 */           else if (generation < 4) {
/* 133 */             BlockPos p2 = spreadCrystal(worldIn, pos);
/* 134 */             if (p2 != null && AuraHelper.drainVis(worldIn, pos, threshold, false) > 0.0F) {
/* 135 */               if (rand.nextInt(6) == 0) generation--; 
/* 136 */               worldIn.func_175656_a(p2, func_176223_P().func_177226_a(GENERATION, Integer.valueOf(generation + 1)));
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         } 
/* 142 */       } else if (AuraHelper.getFlux(worldIn, pos) <= threshold) {
/* 143 */         if (growth > 0) {
/* 144 */           worldIn.func_175656_a(pos, state.func_177226_a(SIZE, Integer.valueOf(growth - 1)));
/* 145 */           AuraHelper.polluteAura(worldIn, pos, threshold, false);
/*     */         }
/* 147 */         else if (BlockUtils.isBlockTouching(worldIn, pos, this)) {
/* 148 */           worldIn.func_175698_g(pos);
/* 149 */           AuraHelper.polluteAura(worldIn, pos, threshold, false);
/*     */         }
/*     */       
/*     */       }
/* 153 */       else if (AuraHelper.getFlux(worldIn, pos) > (AuraHandler.getAuraBase(worldIn, pos) + threshold)) {
/* 154 */         if (growth < 3 && growth < (5 - generation) + pos.func_177986_g() % 3L) {
/* 155 */           if (AuraHelper.drainFlux(worldIn, pos, threshold, false) > 0.0F) {
/* 156 */             worldIn.func_175656_a(pos, state.func_177226_a(SIZE, Integer.valueOf(growth + 1)));
/*     */           }
/*     */         }
/* 159 */         else if (generation < 4) {
/* 160 */           BlockPos p2 = spreadCrystal(worldIn, pos);
/* 161 */           if (p2 != null && AuraHelper.drainFlux(worldIn, pos, threshold, false) > 0.0F) {
/* 162 */             if (rand.nextInt(6) == 0) generation--; 
/* 163 */             worldIn.func_175656_a(p2, func_176223_P().func_177226_a(GENERATION, Integer.valueOf(generation + 1)));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockPos spreadCrystal(World world, BlockPos pos) {
/* 174 */     int xx = pos.func_177958_n() + world.field_73012_v.nextInt(3) - 1;
/* 175 */     int yy = pos.func_177956_o() + world.field_73012_v.nextInt(3) - 1;
/* 176 */     int zz = pos.func_177952_p() + world.field_73012_v.nextInt(3) - 1;
/*     */     
/* 178 */     BlockPos t = new BlockPos(xx, yy, zz);
/*     */     
/* 180 */     if (t.equals(pos)) return null;
/*     */     
/* 182 */     IBlockState bs = world.func_180495_p(t);
/* 183 */     Material bm = bs.func_185904_a();
/*     */     
/* 185 */     if (!bm.func_76224_d() && (world.func_175623_d(t) || bs.func_177230_c().func_176200_f(world, t)) && world.field_73012_v
/* 186 */       .nextInt(16) == 0 && BlockUtils.isBlockTouching(world, t, Material.field_151576_e, true)) {
/* 187 */       return t;
/*     */     }
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
/* 195 */     if (!BlockUtils.isBlockTouching(worldIn, pos, Material.field_151576_e, true)) {
/* 196 */       func_176226_b(worldIn, pos, state, 0);
/* 197 */       worldIn.func_175698_g(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 203 */   public boolean isSideSolid(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing o) { return false; }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean drawAt(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 208 */     IBlockState fbs = worldIn.func_180495_p(pos);
/* 209 */     return (fbs.func_185904_a() == Material.field_151576_e && fbs.func_177230_c().isSideSolid(fbs, worldIn, pos, side.func_176734_d()));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_185496_a(IBlockState bs, IBlockAccess iblockaccess, BlockPos pos) {
/* 214 */     IBlockState state = getExtendedState(bs, iblockaccess, pos);
/* 215 */     if (state instanceof IExtendedBlockState) {
/* 216 */       IExtendedBlockState es = (IExtendedBlockState)state;
/* 217 */       int c = 0;
/* 218 */       if (((Boolean)es.getValue(UP)).booleanValue()) c++; 
/* 219 */       if (((Boolean)es.getValue(DOWN)).booleanValue()) c++; 
/* 220 */       if (((Boolean)es.getValue(EAST)).booleanValue()) c++; 
/* 221 */       if (((Boolean)es.getValue(WEST)).booleanValue()) c++; 
/* 222 */       if (((Boolean)es.getValue(SOUTH)).booleanValue()) c++; 
/* 223 */       if (((Boolean)es.getValue(NORTH)).booleanValue()) c++; 
/* 224 */       if (c > 1) {
/* 225 */         return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */       }
/* 227 */       if (((Boolean)es.getValue(UP)).booleanValue()) {
/* 228 */         return new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */       }
/* 230 */       if (((Boolean)es.getValue(DOWN)).booleanValue()) {
/* 231 */         return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
/*     */       }
/* 233 */       if (((Boolean)es.getValue(EAST)).booleanValue()) {
/* 234 */         return new AxisAlignedBB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */       }
/* 236 */       if (((Boolean)es.getValue(WEST)).booleanValue()) {
/* 237 */         return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
/*     */       }
/* 239 */       if (((Boolean)es.getValue(SOUTH)).booleanValue()) {
/* 240 */         return new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
/*     */       }
/* 242 */       if (((Boolean)es.getValue(NORTH)).booleanValue()) {
/* 243 */         return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
/*     */       }
/*     */     } 
/*     */     
/* 247 */     return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 252 */   public AxisAlignedBB func_180646_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public boolean func_149662_c(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public boolean func_149686_d(IBlockState state) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) { return 1; }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_185484_c(IBlockState state, IBlockAccess source, BlockPos pos) {
/* 277 */     int i = source.func_175626_b(pos, state.getLightValue(source, pos));
/* 278 */     int j = 180;
/* 279 */     int k = i & 0xFF;
/* 280 */     int l = j & 0xFF;
/* 281 */     int i1 = i >> 16 & 0xFF;
/* 282 */     int j1 = j >> 16 & 0xFF;
/* 283 */     return ((k > l) ? k : l) | ((i1 > j1) ? i1 : j1) << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockStateContainer func_180661_e() {
/* 290 */     IProperty[] listedProperties = { SIZE, GENERATION };
/* 291 */     IUnlistedProperty[] unlistedProperties = { UP, DOWN, NORTH, EAST, WEST, SOUTH };
/* 292 */     return new ExtendedBlockState(this, listedProperties, unlistedProperties);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
/* 297 */     if (state instanceof IExtendedBlockState) {
/* 298 */       IExtendedBlockState retval = (IExtendedBlockState)state;
/* 299 */       return retval
/* 300 */         .withProperty(UP, Boolean.valueOf(drawAt(world, pos.func_177984_a(), EnumFacing.UP)))
/* 301 */         .withProperty(DOWN, Boolean.valueOf(drawAt(world, pos.func_177977_b(), EnumFacing.DOWN)))
/* 302 */         .withProperty(NORTH, Boolean.valueOf(drawAt(world, pos.func_177978_c(), EnumFacing.NORTH)))
/* 303 */         .withProperty(EAST, Boolean.valueOf(drawAt(world, pos.func_177974_f(), EnumFacing.EAST)))
/* 304 */         .withProperty(SOUTH, Boolean.valueOf(drawAt(world, pos.func_177968_d(), EnumFacing.SOUTH)))
/* 305 */         .withProperty(WEST, Boolean.valueOf(drawAt(world, pos.func_177976_e(), EnumFacing.WEST)));
/*     */     } 
/* 307 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public IBlockState func_176221_a(IBlockState state, IBlockAccess worldIn, BlockPos pos) { return state; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState func_176203_a(int meta) {
/* 319 */     return func_176223_P()
/* 320 */       .func_177226_a(SIZE, Integer.valueOf(meta & 0x3))
/* 321 */       .func_177226_a(GENERATION, Integer.valueOf(1 + (meta >> 2 & 0x3)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_176201_c(IBlockState state) {
/* 327 */     i = 0;
/* 328 */     i |= ((Integer)state.func_177229_b(SIZE)).intValue();
/* 329 */     return ((Integer)state.func_177229_b(GENERATION)).intValue() - 1 << 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 334 */   public int getGrowth(IBlockState state) { return func_176201_c(state) & 0x3; }
/*     */ 
/*     */ 
/*     */   
/* 338 */   public int getGeneration(IBlockState state) { return 1 + (func_176201_c(state) >> 2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/* 345 */   public void func_149666_a(CreativeTabs tab, NonNullList<ItemStack> list) { list.add(new ItemStack(this, 1, 0)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public boolean func_176196_c(World worldIn, BlockPos pos) { return (BlockUtils.isBlockTouching(worldIn, pos, Material.field_151576_e, true) && super.func_176196_c(worldIn, pos)); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\ore\BlockCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */