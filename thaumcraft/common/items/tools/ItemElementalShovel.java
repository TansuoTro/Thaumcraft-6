/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemSpade;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.IArchitect;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemElementalShovel
/*     */   extends ItemSpade
/*     */   implements IArchitect, IThaumcraftItems
/*     */ {
/*  44 */   private static final Block[] isEffective = { Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150431_aC, Blocks.field_150433_aE, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, Blocks.field_150391_bh };
/*     */   
/*     */   EnumFacing side;
/*     */   
/*     */   public ItemElementalShovel(Item.ToolMaterial enumtoolmaterial)
/*     */   {
/*  50 */     super(enumtoolmaterial);
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
/* 207 */     this.side = EnumFacing.DOWN;
/*     */     func_77637_a(ConfigItems.TABTC);
/*     */     setRegistryName("elemental_shovel");
/*     */     func_77655_b("elemental_shovel");
/* 211 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this); } public Item getItem() { return this; } public String[] getVariantNames() { return new String[] { "normal" }; } public int[] getVariantMeta() { return new int[] { 0 }; } public ItemMeshDefinition getCustomMesh() { return null; } public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); } public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) { if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 212 */       ItemStack w1 = new ItemStack(this);
/* 213 */       EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/* 214 */       items.add(w1);
/*     */     } else {
/* 216 */       super.func_150895_a(tab, items);
/*     */     }  }
/*     */ 
/*     */   
/*     */   public Set<String> getToolClasses(ItemStack stack) { return ImmutableSet.of("shovel"); }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*     */   
/* 224 */   public ArrayList<BlockPos> getArchitectBlocks(ItemStack focusstack, World world, BlockPos pos, EnumFacing side, EntityPlayer player) { ArrayList<BlockPos> b = new ArrayList<BlockPos>();
/* 225 */     if (!player.func_70093_af()) return b;
/*     */     
/* 227 */     IBlockState bs = world.func_180495_p(pos);
/* 228 */     for (int aa = -1; aa <= 1; aa++) {
/* 229 */       for (int bb = -1; bb <= 1; bb++) {
/* 230 */         int xx = 0;
/* 231 */         int yy = 0;
/* 232 */         int zz = 0;
/* 233 */         byte o = getOrientation(focusstack);
/* 234 */         if (o == 1) {
/* 235 */           yy = bb;
/* 236 */           if (side.ordinal() <= 1) {
/* 237 */             int l = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 238 */             if (l == 0 || l == 2) {
/* 239 */               xx = aa;
/*     */             } else {
/* 241 */               zz = aa;
/*     */             } 
/* 243 */           } else if (side.ordinal() <= 3) {
/* 244 */             zz = aa;
/*     */           } else {
/* 246 */             xx = aa;
/*     */           } 
/* 248 */         } else if (o == 2) {
/*     */           
/* 250 */           if (side.ordinal() <= 1) {
/* 251 */             int l = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 252 */             yy = bb;
/* 253 */             if (l == 0 || l == 2) {
/* 254 */               xx = aa;
/*     */             } else {
/* 256 */               zz = aa;
/*     */             } 
/*     */           } else {
/* 259 */             zz = bb;
/* 260 */             xx = aa;
/*     */           }
/*     */         
/* 263 */         } else if (side.ordinal() <= 1) {
/* 264 */           xx = aa;
/* 265 */           zz = bb;
/* 266 */         } else if (side.ordinal() <= 3) {
/* 267 */           xx = aa;
/* 268 */           yy = bb;
/*     */         } else {
/* 270 */           zz = aa;
/* 271 */           yy = bb;
/*     */         } 
/*     */         
/* 274 */         BlockPos p2 = pos.func_177972_a(side).func_177982_a(xx, yy, zz);
/* 275 */         IBlockState b2 = world.func_180495_p(p2);
/* 276 */         if (bs.func_177230_c().func_176196_c(world, p2)) {
/* 277 */           b.add(p2);
/*     */         }
/*     */       } 
/*     */     } 
/* 281 */     return b; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(stack1, stack2); }
/*     */   
/* 287 */   public boolean showAxis(ItemStack stack, World world, EntityPlayer player, EnumFacing side, IArchitect.EnumAxis axis) { return false; }
/*     */   public EnumActionResult func_180614_a(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float par8, float par9, float par10) { IBlockState bs = world.func_180495_p(pos); TileEntity te = world.func_175625_s(pos); if (te == null) for (int aa = -1; aa <= 1; aa++) { for (int bb = -1; bb <= 1; bb++) { int xx = 0; int yy = 0; int zz = 0; byte o = getOrientation(player.func_184586_b(hand)); if (o == 1) { yy = bb; if (side.ordinal() <= 1) { int l = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3; if (l == 0 || l == 2) { xx = aa; } else { zz = aa; }  } else if (side.ordinal() <= 3) { zz = aa; } else { xx = aa; }  } else if (o == 2) { if (side.ordinal() <= 1) { int l = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3; yy = bb; if (l == 0 || l == 2) { xx = aa; } else { zz = aa; }  } else { zz = bb; xx = aa; }  } else if (side.ordinal() <= 1) { xx = aa; zz = bb; } else if (side.ordinal() <= 3) { xx = aa; yy = bb; } else { zz = aa; yy = bb; }  BlockPos p2 = pos.func_177972_a(side).func_177982_a(xx, yy, zz); IBlockState b2 = world.func_180495_p(p2); if (bs.func_177230_c().func_176196_c(world, p2)) if (player.field_71075_bZ.field_75098_d || InventoryUtils.consumePlayerItem(player, Item.func_150898_a(bs.func_177230_c()), bs.func_177230_c().func_176201_c(bs))) { world.func_184134_a(p2.func_177958_n(), p2.func_177956_o(), p2.func_177952_p(), bs.func_177230_c().func_185467_w().func_185845_c(), SoundCategory.BLOCKS, 0.6F, 0.9F + world.field_73012_v.nextFloat() * 0.2F, false); world.func_175656_a(p2, bs); player.func_184586_b(hand).func_77972_a(1, player); if (world.field_72995_K) FXDispatcher.INSTANCE.drawBamf(p2, 8401408, false, false, side);  player.func_184609_a(hand); } else if (bs.func_177230_c() == Blocks.field_150349_c && (player.field_71075_bZ.field_75098_d || InventoryUtils.consumePlayerItem(player, Item.func_150898_a(Blocks.field_150346_d), 0))) { world.func_184134_a(p2.func_177958_n(), p2.func_177956_o(), p2.func_177952_p(), bs.func_177230_c().func_185467_w().func_185845_c(), SoundCategory.BLOCKS, 0.6F, 0.9F + world.field_73012_v.nextFloat() * 0.2F, false); world.func_175656_a(p2, Blocks.field_150346_d.func_176223_P()); player.func_184586_b(hand).func_77972_a(1, player); if (world.field_72995_K) FXDispatcher.INSTANCE.drawBamf(p2, 8401408, false, false, side);  player.func_184609_a(hand); if (player.func_184586_b(hand).func_190926_b() || player.func_184586_b(hand).func_190916_E() < 1)
/*     */                 break;  }   }  }   return EnumActionResult.FAIL; }
/*     */   private boolean isEffectiveAgainst(Block block) { int var3 = 0; this; for (; var3 < isEffective.length; var3++) { this; if (isEffective[var3] == block)
/* 291 */         return true;  }  return false; } public static byte getOrientation(ItemStack stack) { if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("or")) {
/* 292 */       return stack.func_77978_p().func_74771_c("or");
/*     */     }
/* 294 */     return 0; }
/*     */ 
/*     */   
/*     */   public static void setOrientation(ItemStack stack, byte o) {
/* 298 */     if (!stack.func_77942_o()) stack.func_77982_d(new NBTTagCompound()); 
/* 299 */     if (stack.func_77942_o()) {
/* 300 */       stack.func_77978_p().func_74774_a("or", (byte)(o % 3));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 306 */   public RayTraceResult getArchitectMOP(ItemStack stack, World world, EntityLivingBase player) { return Utils.rayTrace(world, player, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public boolean useBlockHighlight(ItemStack stack) { return true; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemElementalShovel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */