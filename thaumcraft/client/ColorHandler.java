/*     */ package thaumcraft.client;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.color.BlockColors;
/*     */ import net.minecraft.client.renderer.color.IBlockColor;
/*     */ import net.minecraft.client.renderer.color.IItemColor;
/*     */ import net.minecraft.client.renderer.color.ItemColors;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.ColorizerGrass;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.biome.BiomeColorHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.blocks.devices.BlockInlay;
/*     */ import thaumcraft.common.blocks.devices.BlockStabilizer;
/*     */ import thaumcraft.common.blocks.essentia.BlockJarItem;
/*     */ import thaumcraft.common.blocks.world.ore.BlockCrystal;
/*     */ import thaumcraft.common.golems.GolemProperties;
/*     */ import thaumcraft.common.items.casters.ItemCaster;
/*     */ import thaumcraft.common.items.casters.ItemFocus;
/*     */ import thaumcraft.common.tiles.devices.TileStabilizer;
/*     */ import thaumcraft.common.tiles.essentia.TileTubeFilter;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class ColorHandler
/*     */ {
/*     */   public static void registerColourHandlers() {
/*  41 */     blockColors = Minecraft.func_71410_x().func_184125_al();
/*  42 */     ItemColors itemColors = Minecraft.func_71410_x().getItemColors();
/*     */     
/*  44 */     registerBlockColourHandlers(blockColors);
/*  45 */     registerItemColourHandlers(blockColors, itemColors);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerBlockColourHandlers(BlockColors blockColors) {
/*  51 */     IBlockColor basicColourHandler = (state, blockAccess, pos, tintIndex) -> 
/*  52 */       (state.func_177230_c().func_180659_g(state, blockAccess, pos)).field_76291_p;
/*     */ 
/*     */     
/*  55 */     Block[] basicBlocks = new Block[BlocksTC.candles.size() + BlocksTC.banners.size() + BlocksTC.nitor.size()];
/*  56 */     int i = 0;
/*  57 */     for (Block b : BlocksTC.candles.values()) {
/*  58 */       basicBlocks[i] = b;
/*  59 */       i++;
/*     */     } 
/*  61 */     for (Block b : BlocksTC.banners.values()) {
/*  62 */       basicBlocks[i] = b;
/*  63 */       i++;
/*     */     } 
/*  65 */     for (Block b : BlocksTC.nitor.values()) {
/*  66 */       basicBlocks[i] = b;
/*  67 */       i++;
/*     */     } 
/*  69 */     blockColors.func_186722_a(basicColourHandler, basicBlocks);
/*     */ 
/*     */ 
/*     */     
/*  73 */     IBlockColor grassColourHandler = (state, blockAccess, pos, tintIndex) -> 
/*  74 */       (blockAccess != null && pos != null) ? BiomeColorHelper.func_180286_a(blockAccess, pos) : ColorizerGrass.func_77480_a(0.5D, 1.0D);
/*     */ 
/*     */     
/*  77 */     blockColors.func_186722_a(grassColourHandler, new Block[] { BlocksTC.grassAmbient });
/*     */ 
/*     */ 
/*     */     
/*  81 */     IBlockColor leafColourHandler = (state, blockAccess, pos, tintIndex) -> {
/*  82 */         if (state.func_177230_c() == BlocksTC.leafSilverwood) return 16777215; 
/*  83 */         if (blockAccess != null && pos != null) {
/*  84 */           return BiomeColorHelper.func_180287_b(blockAccess, pos);
/*     */         }
/*  86 */         return ColorizerFoliage.func_77468_c();
/*     */       };
/*     */     
/*  89 */     blockColors.func_186722_a(leafColourHandler, new Block[] { BlocksTC.leafGreatwood, BlocksTC.leafSilverwood });
/*     */ 
/*     */     
/*  92 */     IBlockColor crystalColourHandler = (state, blockAccess, pos, tintIndex) -> {
/*  93 */         if (state.func_177230_c() instanceof BlockCrystal) {
/*  94 */           return ((BlockCrystal)state.func_177230_c()).aspect.getColor();
/*     */         }
/*  96 */         return 16777215;
/*     */       };
/*     */     
/*  99 */     blockColors.func_186722_a(crystalColourHandler, new Block[] { BlocksTC.crystalAir, BlocksTC.crystalEarth, BlocksTC.crystalFire, BlocksTC.crystalWater, BlocksTC.crystalEntropy, BlocksTC.crystalOrder, BlocksTC.crystalTaint });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     IBlockColor tubeFilterColourHandler = (state, blockAccess, pos, tintIndex) -> {
/* 105 */         if (state.func_177230_c() instanceof thaumcraft.common.blocks.essentia.BlockTube && tintIndex == 1) {
/* 106 */           TileEntity te = blockAccess.func_175625_s(pos);
/* 107 */           if (te != null && te instanceof TileTubeFilter && ((TileTubeFilter)te).aspectFilter != null) {
/* 108 */             return ((TileTubeFilter)te).aspectFilter.getColor();
/*     */           }
/*     */         } 
/* 111 */         return 16777215;
/*     */       };
/* 113 */     blockColors.func_186722_a(tubeFilterColourHandler, new Block[] { BlocksTC.tubeFilter });
/*     */ 
/*     */     
/* 116 */     IBlockColor inlayColourHandler = (state, blockAccess, pos, tintIndex) -> {
/* 117 */         if (state.func_177230_c() instanceof BlockInlay && tintIndex == 0) {
/* 118 */           return ((BlockInlay)state.func_177230_c()).colorMultiplier(state.func_177230_c().func_176201_c(state));
/*     */         }
/* 120 */         return 16777215;
/*     */       };
/* 122 */     blockColors.func_186722_a(inlayColourHandler, new Block[] { BlocksTC.inlay });
/*     */ 
/*     */     
/* 125 */     IBlockColor stabilizerColourHandler = (state, blockAccess, pos, tintIndex) -> {
/* 126 */         if (state.func_177230_c() instanceof BlockStabilizer && tintIndex == 0) {
/* 127 */           int charge = 0;
/* 128 */           TileEntity te = blockAccess.func_175625_s(pos);
/* 129 */           if (te != null && te instanceof TileStabilizer) {
/* 130 */             charge = ((TileStabilizer)te).getEnergy();
/*     */           }
/* 132 */           return ((BlockStabilizer)state.func_177230_c()).colorMultiplier(charge);
/*     */         } 
/* 134 */         return 16777215;
/*     */       };
/* 136 */     blockColors.func_186722_a(stabilizerColourHandler, new Block[] { BlocksTC.stabilizer });
/*     */   }
/*     */ 
/*     */   
/*     */   private static void registerItemColourHandlers(BlockColors blockColors, ItemColors itemColors) {
/* 141 */     IItemColor itemBlockColourHandler = (stack, tintIndex) -> {
/* 142 */         IBlockState state = ((ItemBlock)stack.func_77973_b()).func_179223_d().func_176203_a(stack.func_77960_j());
/* 143 */         return blockColors.func_186724_a(state, null, null, tintIndex);
/*     */       };
/*     */     
/* 146 */     Block[] basicBlocks = new Block[BlocksTC.candles.size() + BlocksTC.nitor.size() + 3];
/* 147 */     int i = 0;
/* 148 */     for (Block b : BlocksTC.candles.values()) {
/* 149 */       basicBlocks[i] = b;
/* 150 */       i++;
/*     */     } 
/* 152 */     for (Block b : BlocksTC.nitor.values()) {
/* 153 */       basicBlocks[i] = b;
/* 154 */       i++;
/*     */     } 
/*     */     
/* 157 */     basicBlocks[i] = BlocksTC.leafGreatwood; i++;
/* 158 */     basicBlocks[i] = BlocksTC.leafSilverwood; i++;
/* 159 */     basicBlocks[i] = BlocksTC.grassAmbient; i++;
/*     */     
/* 161 */     itemColors.func_186731_a(itemBlockColourHandler, basicBlocks);
/*     */ 
/*     */ 
/*     */     
/* 165 */     IItemColor itemEssentiaColourHandler = (stack, tintIndex) -> {
/* 166 */         ItemGenericEssentiaContainer item = (ItemGenericEssentiaContainer)stack.func_77973_b();
/*     */         try {
/* 168 */           if (item != null && item.getAspects(stack) != null) {
/* 169 */             return item.getAspects(stack).getAspects()[0].getColor();
/*     */           }
/* 171 */         } catch (Exception exception) {}
/* 172 */         return 16777215;
/*     */       };
/*     */     
/* 175 */     itemColors.func_186730_a(itemEssentiaColourHandler, new Item[] { ItemsTC.crystalEssence });
/*     */ 
/*     */ 
/*     */     
/* 179 */     IItemColor itemJarColourHandler = (stack, tintIndex) -> {
/* 180 */         BlockJarItem item = (BlockJarItem)stack.func_77973_b();
/*     */         try {
/* 182 */           if (item.getAspects(stack) != null && tintIndex == 1) {
/* 183 */             return item.getAspects(stack).getAspects()[0].getColor();
/*     */           }
/* 185 */         } catch (Exception exception) {}
/* 186 */         return 16777215;
/*     */       };
/*     */     
/* 189 */     itemColors.func_186731_a(itemJarColourHandler, new Block[] { BlocksTC.jarNormal });
/* 190 */     itemColors.func_186731_a(itemJarColourHandler, new Block[] { BlocksTC.jarVoid });
/*     */ 
/*     */ 
/*     */     
/* 194 */     IItemColor itemCrystalPlanterColourHandler = (stack, tintIndex) -> {
/* 195 */         Item item = stack.func_77973_b();
/* 196 */         if (item instanceof ItemBlock && ((ItemBlock)item).func_179223_d() instanceof BlockCrystal) {
/* 197 */           return ((BlockCrystal)((ItemBlock)item).func_179223_d()).aspect.getColor();
/*     */         }
/* 199 */         return 16777215;
/*     */       };
/*     */ 
/*     */     
/* 203 */     itemColors.func_186731_a(itemCrystalPlanterColourHandler, new Block[] { BlocksTC.crystalAir });
/* 204 */     itemColors.func_186731_a(itemCrystalPlanterColourHandler, new Block[] { BlocksTC.crystalEarth });
/* 205 */     itemColors.func_186731_a(itemCrystalPlanterColourHandler, new Block[] { BlocksTC.crystalFire });
/* 206 */     itemColors.func_186731_a(itemCrystalPlanterColourHandler, new Block[] { BlocksTC.crystalWater });
/* 207 */     itemColors.func_186731_a(itemCrystalPlanterColourHandler, new Block[] { BlocksTC.crystalEntropy });
/* 208 */     itemColors.func_186731_a(itemCrystalPlanterColourHandler, new Block[] { BlocksTC.crystalOrder });
/* 209 */     itemColors.func_186731_a(itemCrystalPlanterColourHandler, new Block[] { BlocksTC.crystalTaint });
/*     */ 
/*     */ 
/*     */     
/* 213 */     IItemColor itemEssentiaAltColourHandler = (stack, tintIndex) -> {
/* 214 */         ItemGenericEssentiaContainer item = (ItemGenericEssentiaContainer)stack.func_77973_b();
/* 215 */         if (stack.func_77952_i() == 1 && item.getAspects(stack) != null && tintIndex == 1) {
/* 216 */           return item.getAspects(stack).getAspects()[0].getColor();
/*     */         }
/* 218 */         return 16777215;
/*     */       };
/*     */ 
/*     */     
/* 222 */     itemColors.func_186730_a(itemEssentiaAltColourHandler, new Item[] { ItemsTC.phial, ItemsTC.label });
/*     */ 
/*     */ 
/*     */     
/* 226 */     IItemColor itemArmorColourHandler = (stack, tintIndex) -> {
/* 227 */         ItemArmor item = (ItemArmor)stack.func_77973_b();
/* 228 */         return (tintIndex > 0) ? -1 : item.func_82814_b(stack);
/*     */       };
/*     */     
/* 231 */     itemColors.func_186730_a(itemArmorColourHandler, new Item[] { ItemsTC.voidRobeChest, ItemsTC.voidRobeHelm, ItemsTC.voidRobeLegs, ItemsTC.clothChest, ItemsTC.clothLegs, ItemsTC.clothBoots });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     IItemColor itemCasterColourHandler = (stack, tintIndex) -> {
/* 239 */         ItemCaster item = (ItemCaster)stack.func_77973_b();
/* 240 */         ItemFocus focus = item.getFocus(stack);
/* 241 */         return (tintIndex > 0 && focus != null) ? focus.getFocusColor(item.getFocusStack(stack)) : -1;
/*     */       };
/*     */     
/* 244 */     itemColors.func_186730_a(itemCasterColourHandler, new Item[] { ItemsTC.casterBasic });
/*     */     
/* 246 */     IItemColor itemFocusColourHandler = (stack, tintIndex) -> {
/* 247 */         ItemFocus item = (ItemFocus)stack.func_77973_b();
/* 248 */         return item.getFocusColor(stack);
/*     */       };
/*     */ 
/*     */     
/* 252 */     itemColors.func_186730_a(itemFocusColourHandler, new Item[] { ItemsTC.focus1 });
/* 253 */     itemColors.func_186730_a(itemFocusColourHandler, new Item[] { ItemsTC.focus2 });
/* 254 */     itemColors.func_186730_a(itemFocusColourHandler, new Item[] { ItemsTC.focus3 });
/*     */     
/* 256 */     IItemColor itemGolemColourHandler = (stack, tintIndex) -> {
/* 257 */         if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("props")) {
/* 258 */           IGolemProperties props = GolemProperties.fromLong(stack.func_77978_p().func_74763_f("props"));
/* 259 */           return (props.getMaterial()).itemColor;
/*     */         } 
/* 261 */         return 16777215;
/*     */       };
/*     */     
/* 264 */     itemColors.func_186730_a(itemGolemColourHandler, new Item[] { ItemsTC.golemPlacer });
/*     */ 
/*     */     
/* 267 */     IItemColor itemBannerColourHandler = (stack, tintIndex) -> {
/* 268 */         if (tintIndex == 1) {
/* 269 */           IBlockState state = ((ItemBlock)stack.func_77973_b()).func_179223_d().func_176203_a(stack.func_77960_j());
/* 270 */           return blockColors.func_186724_a(state, null, null, tintIndex);
/*     */         } 
/* 272 */         if (tintIndex == 2) {
/* 273 */           if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("aspect") && stack.func_77978_p().func_74779_i("aspect") != null) {
/* 274 */             return Aspect.getAspect(stack.func_77978_p().func_74779_i("aspect")).getColor();
/*     */           }
/* 276 */           IBlockState state = ((ItemBlock)stack.func_77973_b()).func_179223_d().func_176203_a(stack.func_77960_j());
/* 277 */           return blockColors.func_186724_a(state, null, null, tintIndex);
/*     */         } 
/*     */ 
/*     */         
/* 281 */         return 16777215;
/*     */       };
/*     */     
/* 284 */     Block[] bannerBlocks = new Block[BlocksTC.banners.size()];
/* 285 */     i = 0;
/* 286 */     for (Block b : BlocksTC.banners.values()) {
/* 287 */       bannerBlocks[i] = b;
/* 288 */       i++;
/*     */     } 
/* 290 */     itemColors.func_186731_a(itemBannerColourHandler, bannerBlocks);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\ColorHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */