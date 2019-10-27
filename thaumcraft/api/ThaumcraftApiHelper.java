/*     */ package thaumcraft.api;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.Ingredient;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.crafting.CraftingHelper;
/*     */ import net.minecraftforge.items.IItemHandler;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaTransport;
/*     */ import thaumcraft.api.crafting.IngredientNBTTC;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ 
/*     */ 
/*     */ public class ThaumcraftApiHelper
/*     */ {
/*  34 */   public static final IAttribute CHAMPION_MOD = (new RangedAttribute((IAttribute)null, "tc.mobmod", -2.0D, -2.0D, 100.0D)).func_111117_a("Champion modifier").func_111112_a(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static boolean areItemsEqual(ItemStack s1, ItemStack s2) { return ThaumcraftInvHelper.areItemsEqual(s1, s2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public static boolean containsMatch(boolean strict, ItemStack[] inputs, List<ItemStack> targets) { return ThaumcraftInvHelper.containsMatch(strict, inputs, targets); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static boolean areItemStacksEqualForCrafting(ItemStack stack0, Object in) { return ThaumcraftInvHelper.areItemStacksEqualForCrafting(stack0, in); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public static boolean areItemStackTagsEqualForCrafting(ItemStack slotItem, ItemStack recipeItem) { return ThaumcraftInvHelper.areItemStackTagsEqualForCrafting(slotItem, recipeItem); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static TileEntity getConnectableTile(World world, BlockPos pos, EnumFacing face) {
/*  70 */     TileEntity te = world.func_175625_s(pos.func_177972_a(face));
/*  71 */     if (te instanceof IEssentiaTransport && ((IEssentiaTransport)te).isConnectable(face.func_176734_d())) {
/*  72 */       return te;
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */   
/*     */   public static TileEntity getConnectableTile(IBlockAccess world, BlockPos pos, EnumFacing face) {
/*  78 */     TileEntity te = world.func_175625_s(pos.func_177972_a(face));
/*  79 */     if (te instanceof IEssentiaTransport && ((IEssentiaTransport)te).isConnectable(face.func_176734_d())) {
/*  80 */       return te;
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static RayTraceResult rayTraceIgnoringSource(World world, Vec3d v1, Vec3d v2, boolean bool1, boolean bool2, boolean bool3) {
/*  88 */     if (!Double.isNaN(v1.field_72450_a) && !Double.isNaN(v1.field_72448_b) && !Double.isNaN(v1.field_72449_c)) {
/*     */       
/*  90 */       if (!Double.isNaN(v2.field_72450_a) && !Double.isNaN(v2.field_72448_b) && !Double.isNaN(v2.field_72449_c)) {
/*     */         
/*  92 */         int i = MathHelper.func_76128_c(v2.field_72450_a);
/*  93 */         int j = MathHelper.func_76128_c(v2.field_72448_b);
/*  94 */         int k = MathHelper.func_76128_c(v2.field_72449_c);
/*  95 */         int l = MathHelper.func_76128_c(v1.field_72450_a);
/*  96 */         int i1 = MathHelper.func_76128_c(v1.field_72448_b);
/*  97 */         int j1 = MathHelper.func_76128_c(v1.field_72449_c);
/*  98 */         IBlockState block = world.func_180495_p(new BlockPos(l, i1, j1));
/*     */         
/* 100 */         RayTraceResult rayTraceResult2 = null;
/* 101 */         int k1 = 200;
/*     */         
/* 103 */         while (k1-- >= 0) {
/*     */           EnumFacing enumfacing;
/* 105 */           if (Double.isNaN(v1.field_72450_a) || Double.isNaN(v1.field_72448_b) || Double.isNaN(v1.field_72449_c))
/*     */           {
/* 107 */             return null;
/*     */           }
/*     */           
/* 110 */           if (l == i && i1 == j && j1 == k) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */           
/* 115 */           boolean flag6 = true;
/* 116 */           boolean flag3 = true;
/* 117 */           boolean flag4 = true;
/* 118 */           double d0 = 999.0D;
/* 119 */           double d1 = 999.0D;
/* 120 */           double d2 = 999.0D;
/*     */           
/* 122 */           if (i > l) {
/*     */             
/* 124 */             d0 = l + 1.0D;
/*     */           }
/* 126 */           else if (i < l) {
/*     */             
/* 128 */             d0 = l + 0.0D;
/*     */           }
/*     */           else {
/*     */             
/* 132 */             flag6 = false;
/*     */           } 
/*     */           
/* 135 */           if (j > i1) {
/*     */             
/* 137 */             d1 = i1 + 1.0D;
/*     */           }
/* 139 */           else if (j < i1) {
/*     */             
/* 141 */             d1 = i1 + 0.0D;
/*     */           }
/*     */           else {
/*     */             
/* 145 */             flag3 = false;
/*     */           } 
/*     */           
/* 148 */           if (k > j1) {
/*     */             
/* 150 */             d2 = j1 + 1.0D;
/*     */           }
/* 152 */           else if (k < j1) {
/*     */             
/* 154 */             d2 = j1 + 0.0D;
/*     */           }
/*     */           else {
/*     */             
/* 158 */             flag4 = false;
/*     */           } 
/*     */           
/* 161 */           double d3 = 999.0D;
/* 162 */           double d4 = 999.0D;
/* 163 */           double d5 = 999.0D;
/* 164 */           double d6 = v2.field_72450_a - v1.field_72450_a;
/* 165 */           double d7 = v2.field_72448_b - v1.field_72448_b;
/* 166 */           double d8 = v2.field_72449_c - v1.field_72449_c;
/*     */           
/* 168 */           if (flag6)
/*     */           {
/* 170 */             d3 = (d0 - v1.field_72450_a) / d6;
/*     */           }
/*     */           
/* 173 */           if (flag3)
/*     */           {
/* 175 */             d4 = (d1 - v1.field_72448_b) / d7;
/*     */           }
/*     */           
/* 178 */           if (flag4)
/*     */           {
/* 180 */             d5 = (d2 - v1.field_72449_c) / d8;
/*     */           }
/*     */           
/* 183 */           if (d3 == -0.0D)
/*     */           {
/* 185 */             d3 = -1.0E-4D;
/*     */           }
/*     */           
/* 188 */           if (d4 == -0.0D)
/*     */           {
/* 190 */             d4 = -1.0E-4D;
/*     */           }
/*     */           
/* 193 */           if (d5 == -0.0D)
/*     */           {
/* 195 */             d5 = -1.0E-4D;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 200 */           if (d3 < d4 && d3 < d5) {
/*     */             
/* 202 */             enumfacing = (i > l) ? EnumFacing.WEST : EnumFacing.EAST;
/* 203 */             v1 = new Vec3d(d0, v1.field_72448_b + d7 * d3, v1.field_72449_c + d8 * d3);
/*     */           }
/* 205 */           else if (d4 < d5) {
/*     */             
/* 207 */             enumfacing = (j > i1) ? EnumFacing.DOWN : EnumFacing.UP;
/* 208 */             v1 = new Vec3d(v1.field_72450_a + d6 * d4, d1, v1.field_72449_c + d8 * d4);
/*     */           }
/*     */           else {
/*     */             
/* 212 */             enumfacing = (k > j1) ? EnumFacing.NORTH : EnumFacing.SOUTH;
/* 213 */             v1 = new Vec3d(v1.field_72450_a + d6 * d5, v1.field_72448_b + d7 * d5, d2);
/*     */           } 
/*     */           
/* 216 */           l = MathHelper.func_76128_c(v1.field_72450_a) - ((enumfacing == EnumFacing.EAST) ? 1 : 0);
/* 217 */           i1 = MathHelper.func_76128_c(v1.field_72448_b) - ((enumfacing == EnumFacing.UP) ? 1 : 0);
/* 218 */           j1 = MathHelper.func_76128_c(v1.field_72449_c) - ((enumfacing == EnumFacing.SOUTH) ? 1 : 0);
/*     */           
/* 220 */           IBlockState block1 = world.func_180495_p(new BlockPos(l, i1, j1));
/*     */           
/* 222 */           if (!bool2 || block1.func_185890_d(world, new BlockPos(l, i1, j1)) != null) {
/*     */             
/* 224 */             if (block1.func_177230_c().func_176209_a(block1, bool1)) {
/*     */               
/* 226 */               RayTraceResult rayTraceResult1 = block1.func_185910_a(world, new BlockPos(l, i1, j1), v1, v2);
/*     */               
/* 228 */               if (rayTraceResult1 != null)
/*     */               {
/* 230 */                 return rayTraceResult1;
/*     */               }
/*     */               
/*     */               continue;
/*     */             } 
/* 235 */             rayTraceResult2 = new RayTraceResult(RayTraceResult.Type.MISS, v1, enumfacing, new BlockPos(l, i1, j1));
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 240 */         return bool3 ? rayTraceResult2 : null;
/*     */       } 
/*     */ 
/*     */       
/* 244 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 249 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object getNBTDataFromId(NBTTagCompound nbt, byte id, String key) {
/* 254 */     switch (id) { case 1:
/* 255 */         return Byte.valueOf(nbt.func_74771_c(key));
/* 256 */       case 2: return Short.valueOf(nbt.func_74765_d(key));
/* 257 */       case 3: return Integer.valueOf(nbt.func_74762_e(key));
/* 258 */       case 4: return Long.valueOf(nbt.func_74763_f(key));
/* 259 */       case 5: return Float.valueOf(nbt.func_74760_g(key));
/* 260 */       case 6: return Double.valueOf(nbt.func_74769_h(key));
/* 261 */       case 7: return nbt.func_74770_j(key);
/* 262 */       case 8: return nbt.func_74779_i(key);
/* 263 */       case 9: return nbt.func_150295_c(key, 10);
/* 264 */       case 10: return nbt.func_74781_a(key);
/* 265 */       case 11: return nbt.func_74759_k(key); }
/* 266 */      return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int setByteInInt(int data, byte b, int index) {
/* 272 */     ByteBuffer bb = ByteBuffer.allocate(4);
/* 273 */     bb.putInt(0, data);
/* 274 */     bb.put(index, b);
/* 275 */     return bb.getInt(0);
/*     */   }
/*     */   
/*     */   public static byte getByteInInt(int data, int index) {
/* 279 */     ByteBuffer bb = ByteBuffer.allocate(4);
/* 280 */     bb.putInt(0, data);
/* 281 */     return bb.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long setByteInLong(long data, byte b, int index) {
/* 286 */     ByteBuffer bb = ByteBuffer.allocate(8);
/* 287 */     bb.putLong(0, data);
/* 288 */     bb.put(index, b);
/* 289 */     return bb.getLong(0);
/*     */   }
/*     */   
/*     */   public static byte getByteInLong(long data, int index) {
/* 293 */     ByteBuffer bb = ByteBuffer.allocate(8);
/* 294 */     bb.putLong(0, data);
/* 295 */     return bb.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int setNibbleInInt(int data, int nibble, int nibbleIndex) {
/* 300 */     int shift = nibbleIndex * 4;
/* 301 */     return data & (15 << shift ^ 0xFFFFFFFF) | nibble << shift;
/*     */   }
/*     */ 
/*     */   
/* 305 */   public static int getNibbleInInt(int data, int nibbleIndex) { return data >> nibbleIndex << 2 & 0xF; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack makeCrystal(Aspect aspect, int stackSize) {
/* 315 */     if (aspect == null) return null; 
/* 316 */     ItemStack is = new ItemStack(ItemsTC.crystalEssence, stackSize, 0);
/* 317 */     ((ItemGenericEssentiaContainer)ItemsTC.crystalEssence).setAspects(is, (new AspectList()).add(aspect, 1));
/* 318 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public static ItemStack makeCrystal(Aspect aspect) { return makeCrystal(aspect, 1); }
/*     */ 
/*     */   
/*     */   public static List<ItemStack> getOresWithWildCards(String oreDict) {
/* 331 */     if (oreDict.trim().endsWith("*")) {
/* 332 */       ArrayList<ItemStack> ores = new ArrayList<ItemStack>();
/* 333 */       String[] names = OreDictionary.getOreNames();
/* 334 */       String m = oreDict.trim().replaceAll("\\*", "");
/* 335 */       for (String name : names) {
/* 336 */         if (name.startsWith(m)) {
/* 337 */           ores.addAll(OreDictionary.getOres(name, false));
/*     */         }
/*     */       } 
/* 340 */       return ores;
/*     */     } 
/* 342 */     return OreDictionary.getOres(oreDict, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Ingredient getIngredient(Object obj) {
/* 348 */     if (obj instanceof Ingredient) return (Ingredient)obj; 
/* 349 */     if (obj != null && obj instanceof ItemStack && ((ItemStack)obj).func_77942_o()) {
/* 350 */       return new IngredientNBTTC((ItemStack)obj);
/*     */     }
/* 352 */     return CraftingHelper.getIngredient(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public static IItemHandler getItemHandlerAt(World world, BlockPos pos, EnumFacing side) { return ThaumcraftInvHelper.getItemHandlerAt(world, pos, side); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 366 */   public static IItemHandler wrapInventory(IInventory inventory, EnumFacing side) { return ThaumcraftInvHelper.wrapInventory(inventory, side); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 373 */   public static boolean areItemStackTagsEqualRelaxed(ItemStack prime, ItemStack other) { return ThaumcraftInvHelper.areItemStackTagsEqualRelaxed(prime, other); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public static boolean compareTagsRelaxed(NBTTagCompound prime, NBTTagCompound other) { return ThaumcraftInvHelper.compareTagsRelaxed(prime, other); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\ThaumcraftApiHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */