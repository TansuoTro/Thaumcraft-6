/*      */ package thaumcraft.common.config;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockPistonBase;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.EnumDyeColor;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.crafting.CraftingManager;
/*      */ import net.minecraft.item.crafting.IRecipe;
/*      */ import net.minecraft.item.crafting.Ingredient;
/*      */ import net.minecraft.nbt.NBTTagByte;
/*      */ import net.minecraft.nbt.NBTTagInt;
/*      */ import net.minecraft.nbt.NBTTagString;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraftforge.fluids.FluidStack;
/*      */ import net.minecraftforge.fluids.FluidUtil;
/*      */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*      */ import net.minecraftforge.oredict.ShapedOreRecipe;
/*      */ import net.minecraftforge.oredict.ShapelessOreRecipe;
/*      */ import net.minecraftforge.registries.GameData;
/*      */ import net.minecraftforge.registries.IForgeRegistry;
/*      */ import thaumcraft.api.ThaumcraftApi;
/*      */ import thaumcraft.api.ThaumcraftApiHelper;
/*      */ import thaumcraft.api.aspects.Aspect;
/*      */ import thaumcraft.api.aspects.AspectList;
/*      */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*      */ import thaumcraft.api.blocks.BlocksTC;
/*      */ import thaumcraft.api.crafting.CrucibleRecipe;
/*      */ import thaumcraft.api.crafting.IDustTrigger;
/*      */ import thaumcraft.api.crafting.IThaumcraftRecipe;
/*      */ import thaumcraft.api.crafting.InfusionRecipe;
/*      */ import thaumcraft.api.crafting.IngredientNBTTC;
/*      */ import thaumcraft.api.crafting.Part;
/*      */ import thaumcraft.api.crafting.ShapedArcaneRecipe;
/*      */ import thaumcraft.api.crafting.ShapelessArcaneRecipe;
/*      */ import thaumcraft.api.golems.GolemHelper;
/*      */ import thaumcraft.api.internal.CommonInternals;
/*      */ import thaumcraft.api.items.ItemsTC;
/*      */ import thaumcraft.common.blocks.basic.BlockPillar;
/*      */ import thaumcraft.common.items.consumables.ItemPhial;
/*      */ import thaumcraft.common.lib.crafting.DustTriggerMultiblock;
/*      */ import thaumcraft.common.lib.crafting.DustTriggerOre;
/*      */ import thaumcraft.common.lib.crafting.DustTriggerSimple;
/*      */ import thaumcraft.common.lib.crafting.InfusionEnchantmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;
/*      */ import thaumcraft.common.lib.crafting.RecipeMagicDust;
/*      */ import thaumcraft.common.lib.crafting.RecipeTripleMeatTreat;
/*      */ import thaumcraft.common.lib.crafting.RecipesRobeArmorDyes;
/*      */ import thaumcraft.common.lib.crafting.RecipesVoidRobeArmorDyes;
/*      */ import thaumcraft.common.lib.crafting.ShapedArcaneVoidJar;
/*      */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConfigRecipes
/*      */ {
/*      */   public static void initializeCompoundRecipes() {
/*   68 */     IDustTrigger.registerDustTrigger(new DustTriggerSimple("!gotdream", Blocks.field_150342_X, new ItemStack(ItemsTC.thaumonomicon)));
/*   69 */     IDustTrigger.registerDustTrigger(new DustTriggerOre("!gotdream", "bookshelf", new ItemStack(ItemsTC.thaumonomicon)));
/*   70 */     IDustTrigger.registerDustTrigger(new DustTriggerSimple("FIRSTSTEPS@1", Blocks.field_150462_ai, new ItemStack(BlocksTC.arcaneWorkbench)));
/*   71 */     IDustTrigger.registerDustTrigger(new DustTriggerOre("FIRSTSTEPS@1", "workbench", new ItemStack(BlocksTC.arcaneWorkbench)));
/*   72 */     IDustTrigger.registerDustTrigger(new DustTriggerSimple("UNLOCKALCHEMY@1", Blocks.field_150383_bp, new ItemStack(BlocksTC.crucible)));
/*      */     
/*   74 */     NB = new Part(Blocks.field_150385_bj, new ItemStack(BlocksTC.placeholderNetherbrick));
/*   75 */     Part OB = new Part(Blocks.field_150343_Z, new ItemStack(BlocksTC.placeholderObsidian));
/*   76 */     Part IB = new Part(Blocks.field_150411_aY, "AIR");
/*   77 */     Part LA = new Part(Material.field_151587_i, BlocksTC.infernalFurnace, true);
/*   78 */     Part[][][] infernalFurnaceBlueprint = { { { NB, OB, NB }, { OB, null, OB }, { NB, OB, NB } }, { { NB, OB, NB }, { OB, LA, OB }, { NB, IB, NB } }, { { NB, OB, NB }, { OB, OB, OB }, { NB, OB, NB } } };
/*      */ 
/*      */ 
/*      */     
/*   82 */     IDustTrigger.registerDustTrigger(new DustTriggerMultiblock("INFERNALFURNACE", infernalFurnaceBlueprint));
/*   83 */     ThaumcraftApi.addMultiblockRecipeToCatalog(new ResourceLocation("thaumcraft:infernalfurnace"), new ThaumcraftApi.BluePrint("INFERNALFURNACE", infernalFurnaceBlueprint, new ItemStack[] { new ItemStack(Blocks.field_150385_bj, 12), new ItemStack(Blocks.field_150343_Z, 12), new ItemStack(Blocks.field_150411_aY), new ItemStack(Items.field_151129_at) }));
/*      */ 
/*      */ 
/*      */     
/*   87 */     Part IM = new Part(BlocksTC.infusionMatrix, null);
/*   88 */     Part SNT = new Part(BlocksTC.stoneArcane, "AIR");
/*   89 */     Part SNB1 = new Part(BlocksTC.stoneArcane, new ItemStack(BlocksTC.pillarArcane, 1, BlockPillar.calcMeta(EnumFacing.EAST)));
/*   90 */     Part SNB2 = new Part(BlocksTC.stoneArcane, new ItemStack(BlocksTC.pillarArcane, 1, BlockPillar.calcMeta(EnumFacing.NORTH)));
/*   91 */     Part SNB3 = new Part(BlocksTC.stoneArcane, new ItemStack(BlocksTC.pillarArcane, 1, BlockPillar.calcMeta(EnumFacing.SOUTH)));
/*   92 */     Part SNB4 = new Part(BlocksTC.stoneArcane, new ItemStack(BlocksTC.pillarArcane, 1, BlockPillar.calcMeta(EnumFacing.WEST)));
/*   93 */     Part PN = new Part(BlocksTC.pedestalArcane.func_176223_P(), null);
/*   94 */     Part[][][] infusionAltarNormalBlueprint = { { { null, null, null }, { null, IM, null }, { null, null, null } }, { { SNT, null, SNT }, { null, null, null }, { SNT, null, SNT } }, { { SNB1, null, SNB2 }, { null, PN, null }, { SNB3, null, SNB4 } } };
/*      */ 
/*      */ 
/*      */     
/*   98 */     IDustTrigger.registerDustTrigger(new DustTriggerMultiblock("INFUSION", infusionAltarNormalBlueprint));
/*   99 */     ThaumcraftApi.addMultiblockRecipeToCatalog(new ResourceLocation("thaumcraft:infusionaltar"), new ThaumcraftApi.BluePrint("INFUSION", infusionAltarNormalBlueprint, new ItemStack[] { new ItemStack(BlocksTC.stoneArcane, 8), new ItemStack(BlocksTC.pedestalArcane), new ItemStack(BlocksTC.infusionMatrix) }));
/*      */ 
/*      */     
/*  102 */     Part SAT = new Part(BlocksTC.stoneAncient, "AIR");
/*  103 */     Part SAB1 = new Part(BlocksTC.stoneAncient, new ItemStack(BlocksTC.pillarAncient, 1, BlockPillar.calcMeta(EnumFacing.EAST)));
/*  104 */     Part SAB2 = new Part(BlocksTC.stoneAncient, new ItemStack(BlocksTC.pillarAncient, 1, BlockPillar.calcMeta(EnumFacing.NORTH)));
/*  105 */     Part SAB3 = new Part(BlocksTC.stoneAncient, new ItemStack(BlocksTC.pillarAncient, 1, BlockPillar.calcMeta(EnumFacing.SOUTH)));
/*  106 */     Part SAB4 = new Part(BlocksTC.stoneAncient, new ItemStack(BlocksTC.pillarAncient, 1, BlockPillar.calcMeta(EnumFacing.WEST)));
/*  107 */     Part PA = new Part(BlocksTC.pedestalAncient.func_176203_a(2), null);
/*  108 */     Part[][][] infusionAltarAncientBlueprint = { { { null, null, null }, { null, IM, null }, { null, null, null } }, { { SAT, null, SAT }, { null, null, null }, { SAT, null, SAT } }, { { SAB1, null, SAB2 }, { null, PA, null }, { SAB3, null, SAB4 } } };
/*      */ 
/*      */ 
/*      */     
/*  112 */     IDustTrigger.registerDustTrigger(new DustTriggerMultiblock("INFUSIONANCIENT", infusionAltarAncientBlueprint));
/*  113 */     ThaumcraftApi.addMultiblockRecipeToCatalog(new ResourceLocation("thaumcraft:infusionaltarancient"), new ThaumcraftApi.BluePrint("INFUSIONANCIENT", infusionAltarAncientBlueprint, new ItemStack[] { new ItemStack(BlocksTC.stoneAncient, 8), new ItemStack(BlocksTC.pedestalAncient), new ItemStack(BlocksTC.infusionMatrix) }));
/*      */ 
/*      */     
/*  116 */     Part SET = new Part(BlocksTC.stoneEldritchTile, "AIR");
/*  117 */     Part SEB1 = new Part(BlocksTC.stoneEldritchTile, new ItemStack(BlocksTC.pillarEldritch, 1, BlockPillar.calcMeta(EnumFacing.EAST)));
/*  118 */     Part SEB2 = new Part(BlocksTC.stoneEldritchTile, new ItemStack(BlocksTC.pillarEldritch, 1, BlockPillar.calcMeta(EnumFacing.NORTH)));
/*  119 */     Part SEB3 = new Part(BlocksTC.stoneEldritchTile, new ItemStack(BlocksTC.pillarEldritch, 1, BlockPillar.calcMeta(EnumFacing.SOUTH)));
/*  120 */     Part SEB4 = new Part(BlocksTC.stoneEldritchTile, new ItemStack(BlocksTC.pillarEldritch, 1, BlockPillar.calcMeta(EnumFacing.WEST)));
/*  121 */     Part PE = new Part(BlocksTC.pedestalEldritch.func_176203_a(1), null);
/*  122 */     Part[][][] infusionAltarEldritchBlueprint = { { { null, null, null }, { null, IM, null }, { null, null, null } }, { { SET, null, SET }, { null, null, null }, { SET, null, SET } }, { { SEB1, null, SEB2 }, { null, PE, null }, { SEB3, null, SEB4 } } };
/*      */ 
/*      */ 
/*      */     
/*  126 */     IDustTrigger.registerDustTrigger(new DustTriggerMultiblock("INFUSIONELDRITCH", infusionAltarEldritchBlueprint));
/*      */     
/*  128 */     ThaumcraftApi.addMultiblockRecipeToCatalog(new ResourceLocation("thaumcraft:infusionaltareldritch"), new ThaumcraftApi.BluePrint("INFUSIONELDRITCH", infusionAltarEldritchBlueprint, new ItemStack[] { new ItemStack(BlocksTC.stoneEldritchTile, 8), new ItemStack(BlocksTC.pedestalEldritch), new ItemStack(BlocksTC.infusionMatrix) }));
/*      */ 
/*      */ 
/*      */     
/*  132 */     Part TH1 = (new Part(BlocksTC.metalAlchemical.func_176223_P(), BlocksTC.thaumatoriumTop)).setApplyPlayerFacing(true);
/*  133 */     Part TH2 = (new Part(BlocksTC.metalAlchemical.func_176223_P(), BlocksTC.thaumatorium)).setApplyPlayerFacing(true);
/*  134 */     Part TH3 = new Part(BlocksTC.crucible, null);
/*  135 */     Part[][][] thaumotoriumBlueprint = { { { TH1 } }, { { TH2 } }, { { TH3 } } };
/*  136 */     IDustTrigger.registerDustTrigger(new DustTriggerMultiblock("THAUMATORIUM", thaumotoriumBlueprint));
/*  137 */     ThaumcraftApi.addMultiblockRecipeToCatalog(new ResourceLocation("thaumcraft:Thaumatorium"), new ThaumcraftApi.BluePrint("THAUMATORIUM", thaumotoriumBlueprint, new ItemStack[] { new ItemStack(BlocksTC.metalAlchemical, 2), new ItemStack(BlocksTC.crucible) }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  142 */     Part GP1 = new Part(Blocks.field_150411_aY, new ItemStack(BlocksTC.placeholderBars));
/*  143 */     Part GP2 = new Part(Blocks.field_150383_bp, new ItemStack(BlocksTC.placeholderCauldron));
/*  144 */     Part GP3 = new Part(Blocks.field_150331_J.func_176223_P().func_177226_a(BlockPistonBase.field_176387_N, EnumFacing.UP), BlocksTC.golemBuilder);
/*  145 */     Part GP4 = new Part(Blocks.field_150467_bQ, new ItemStack(BlocksTC.placeholderAnvil));
/*  146 */     Part GP5 = new Part(BlocksTC.tableStone, new ItemStack(BlocksTC.placeholderTable));
/*  147 */     Part[][][] golempressBlueprint = { { { null, null }, { GP1, null } }, { { GP2, GP4 }, { GP3, GP5 } } };
/*      */ 
/*      */     
/*  150 */     IDustTrigger.registerDustTrigger(new DustTriggerMultiblock("MINDCLOCKWORK", golempressBlueprint));
/*  151 */     ThaumcraftApi.addMultiblockRecipeToCatalog(new ResourceLocation("thaumcraft:GolemPress"), new ThaumcraftApi.BluePrint("MINDCLOCKWORK", new ItemStack(BlocksTC.golemBuilder), golempressBlueprint, new ItemStack[] { new ItemStack(Blocks.field_150411_aY), new ItemStack(Items.field_151066_bu), new ItemStack(Blocks.field_150331_J), new ItemStack(Blocks.field_150467_bQ), new ItemStack(BlocksTC.tableStone) }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void initializeAlchemyRecipes() {
/*  159 */     visCrystalGroup = new ResourceLocation("thaumcraft:viscrystalgroup");
/*  160 */     CrucibleRecipe[] cre = new CrucibleRecipe[Aspect.aspects.size()];
/*  161 */     for (Aspect aspect : Aspect.aspects.values()) {
/*  162 */       ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:vis_crystal_" + aspect.getTag()), (new CrucibleRecipe("BASEALCHEMY", 
/*  163 */             ThaumcraftApiHelper.makeCrystal(aspect), "nuggetQuartz", (new AspectList()).add(aspect, 2)))
/*  164 */           .setGroup(visCrystalGroup));
/*      */     }
/*      */ 
/*      */     
/*  168 */     ResourceLocation nitorGroup = new ResourceLocation("thaumcraft", "nitorgroup");
/*  169 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:nitor"), new CrucibleRecipe("UNLOCKALCHEMY@3", new ItemStack((Block)BlocksTC.nitor
/*  170 */             .get(EnumDyeColor.YELLOW)), "dustGlowstone", (new AspectList())
/*  171 */           .merge(Aspect.ENERGY, 10).merge(Aspect.FIRE, 10).merge(Aspect.LIGHT, 10)));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  176 */     int a = 0;
/*  177 */     for (EnumDyeColor d : EnumDyeColor.values()) {
/*  178 */       shapelessOreDictRecipe("NitorDye" + d.func_176762_d().toLowerCase(), nitorGroup, new ItemStack((Block)BlocksTC.nitor
/*  179 */             .get(d)), new Object[] { ConfigAspects.dyes[15 - a], "nitor" });
/*  180 */       a++;
/*      */     } 
/*      */     
/*  183 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:alumentum"), new CrucibleRecipe("ALUMENTUM", new ItemStack(ItemsTC.alumentum), new ItemStack(Items.field_151044_h, 1, 32767), (new AspectList())
/*      */           
/*  185 */           .merge(Aspect.ENERGY, 10).merge(Aspect.FIRE, 10).merge(Aspect.ENTROPY, 5)));
/*      */     
/*  187 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:brassingot"), new CrucibleRecipe("METALLURGY@1", new ItemStack(ItemsTC.ingots, 1, 2), "ingotIron", (new AspectList())
/*  188 */           .merge(Aspect.TOOL, 5)));
/*      */     
/*  190 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:thaumiumingot"), new CrucibleRecipe("METALLURGY@2", new ItemStack(ItemsTC.ingots, 1, 0), "ingotIron", (new AspectList())
/*  191 */           .merge(Aspect.MAGIC, 5).merge(Aspect.EARTH, 5)));
/*      */     
/*  193 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:voidingot"), new CrucibleRecipe("BASEELDRITCH", new ItemStack(ItemsTC.ingots, 1, 1), new ItemStack(ItemsTC.voidSeed), (new AspectList())
/*  194 */           .merge(Aspect.METAL, 10).merge(Aspect.FLUX, 5)));
/*      */     
/*  196 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_tallow"), new CrucibleRecipe("HEDGEALCHEMY@1", new ItemStack(ItemsTC.tallow), new ItemStack(Items.field_151078_bh), (new AspectList())
/*  197 */           .merge(Aspect.FIRE, 1)));
/*  198 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_leather"), new CrucibleRecipe("HEDGEALCHEMY@1", new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151078_bh), (new AspectList())
/*  199 */           .merge(Aspect.AIR, 3).merge(Aspect.BEAST, 3)));
/*      */ 
/*      */     
/*  202 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:focus_1"), new CrucibleRecipe("UNLOCKAUROMANCY", new ItemStack(ItemsTC.focus1), ConfigItems.ORDER_CRYSTAL, (new AspectList())
/*  203 */           .merge(Aspect.CRYSTAL, 20).merge(Aspect.MAGIC, 10).merge(Aspect.AURA, 5)));
/*      */ 
/*      */     
/*  206 */     ArrayList<CrucibleRecipe> rl = new ArrayList<CrucibleRecipe>();
/*  207 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:metal_purification_iron"), new CrucibleRecipe("METALPURIFICATION", new ItemStack(ItemsTC.clusters, 1, 0), "oreIron", (new AspectList()).merge(Aspect.METAL, 5).merge(Aspect.ORDER, 5)));
/*  208 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:metal_purification_gold"), new CrucibleRecipe("METALPURIFICATION", new ItemStack(ItemsTC.clusters, 1, 1), "oreGold", (new AspectList()).merge(Aspect.METAL, 5).merge(Aspect.ORDER, 5)));
/*  209 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:metal_purification_cinnabar"), new CrucibleRecipe("METALPURIFICATION", new ItemStack(ItemsTC.clusters, 1, 6), "oreCinnabar", (new AspectList()).merge(Aspect.METAL, 5).merge(Aspect.ORDER, 5)));
/*  210 */     if (ModConfig.foundCopperOre)
/*  211 */       ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:metal_purification_copper"), new CrucibleRecipe("METALPURIFICATION", new ItemStack(ItemsTC.clusters, 1, 2), "oreCopper", (new AspectList()).merge(Aspect.METAL, 5).merge(Aspect.ORDER, 5))); 
/*  212 */     if (ModConfig.foundTinOre)
/*  213 */       ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:metal_purification_tin"), new CrucibleRecipe("METALPURIFICATION", new ItemStack(ItemsTC.clusters, 1, 3), "oreTin", (new AspectList()).merge(Aspect.METAL, 5).merge(Aspect.ORDER, 5))); 
/*  214 */     if (ModConfig.foundSilverOre)
/*  215 */       ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:metal_purification_silver"), new CrucibleRecipe("METALPURIFICATION", new ItemStack(ItemsTC.clusters, 1, 4), "oreSilver", (new AspectList()).merge(Aspect.METAL, 5).merge(Aspect.ORDER, 5))); 
/*  216 */     if (ModConfig.foundLeadOre) {
/*  217 */       ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:metal_purification_lead"), new CrucibleRecipe("METALPURIFICATION", new ItemStack(ItemsTC.clusters, 1, 5), "oreLead", (new AspectList()).merge(Aspect.METAL, 5).merge(Aspect.ORDER, 5)));
/*      */     }
/*      */ 
/*      */     
/*  221 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:LiquidDeath"), new CrucibleRecipe("LIQUIDDEATH", FluidUtil.getFilledBucket(new FluidStack(ConfigBlocks.FluidDeath.instance, 1000)), new ItemStack(Items.field_151133_ar), (new AspectList())
/*  222 */           .add(Aspect.DEATH, 100).add(Aspect.ALCHEMY, 20).add(Aspect.ENTROPY, 50)));
/*      */ 
/*      */     
/*  225 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:BottleTaint"), new CrucibleRecipe("BOTTLETAINT", new ItemStack(ItemsTC.bottleTaint), 
/*  226 */           ItemPhial.makeFilledPhial(Aspect.FLUX), (new AspectList()).add(Aspect.FLUX, 30).add(Aspect.WATER, 30)));
/*      */ 
/*      */     
/*  229 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:BathSalts"), new CrucibleRecipe("BATHSALTS", new ItemStack(ItemsTC.bathSalts), new ItemStack(ItemsTC.salisMundus), (new AspectList())
/*  230 */           .add(Aspect.MIND, 40).add(Aspect.AIR, 40).add(Aspect.ORDER, 40).add(Aspect.LIFE, 40)));
/*      */ 
/*      */     
/*  233 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SaneSoap"), new CrucibleRecipe("SANESOAP", new ItemStack(ItemsTC.sanitySoap), new ItemStack(BlocksTC.fleshBlock), (new AspectList())
/*  234 */           .add(Aspect.MIND, 75).add(Aspect.ELDRITCH, 50).add(Aspect.ORDER, 75).add(Aspect.LIFE, 50)));
/*      */ 
/*      */     
/*  237 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealCollect"), new CrucibleRecipe("SEALCOLLECT", 
/*  238 */           GolemHelper.getSealStack("thaumcraft:pickup"), new ItemStack(ItemsTC.seals), (new AspectList())
/*  239 */           .add(Aspect.DESIRE, 10)));
/*  240 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealCollectAdv"), new CrucibleRecipe("SEALCOLLECT&&MINDBIOTHAUMIC", 
/*  241 */           GolemHelper.getSealStack("thaumcraft:pickup_advanced"), 
/*  242 */           GolemHelper.getSealStack("thaumcraft:pickup"), (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.MIND, 10)));
/*      */     
/*  244 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealStore"), new CrucibleRecipe("SEALSTORE", 
/*  245 */           GolemHelper.getSealStack("thaumcraft:fill"), new ItemStack(ItemsTC.seals), (new AspectList())
/*  246 */           .add(Aspect.AVERSION, 10)));
/*  247 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealStoreAdv"), new CrucibleRecipe("SEALSTORE&&MINDBIOTHAUMIC", 
/*  248 */           GolemHelper.getSealStack("thaumcraft:fill_advanced"), 
/*  249 */           GolemHelper.getSealStack("thaumcraft:fill"), (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.MIND, 10)));
/*      */     
/*  251 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealEmpty"), new CrucibleRecipe("SEALEMPTY", 
/*  252 */           GolemHelper.getSealStack("thaumcraft:empty"), new ItemStack(ItemsTC.seals), (new AspectList())
/*  253 */           .add(Aspect.VOID, 10)));
/*  254 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealEmptyAdv"), new CrucibleRecipe("SEALEMPTY&&MINDBIOTHAUMIC", 
/*  255 */           GolemHelper.getSealStack("thaumcraft:empty_advanced"), 
/*  256 */           GolemHelper.getSealStack("thaumcraft:empty"), (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.MIND, 10)));
/*      */     
/*  258 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealProvide"), new CrucibleRecipe("SEALPROVIDE", 
/*  259 */           GolemHelper.getSealStack("thaumcraft:provider"), 
/*  260 */           GolemHelper.getSealStack("thaumcraft:empty_advanced"), (new AspectList()).add(Aspect.EXCHANGE, 10).add(Aspect.DESIRE, 10)));
/*      */     
/*  262 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealStock"), new CrucibleRecipe("SEALSTOCK", 
/*  263 */           GolemHelper.getSealStack("thaumcraft:stock"), 
/*  264 */           GolemHelper.getSealStack("thaumcraft:fill"), (new AspectList()).add(Aspect.MIND, 10).add(Aspect.DESIRE, 10)));
/*      */     
/*  266 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealGuard"), new CrucibleRecipe("SEALGUARD", 
/*  267 */           GolemHelper.getSealStack("thaumcraft:guard"), new ItemStack(ItemsTC.seals), (new AspectList())
/*  268 */           .add(Aspect.AVERSION, 20).add(Aspect.PROTECT, 20)));
/*  269 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealGuardAdv"), new CrucibleRecipe("SEALGUARD&&MINDBIOTHAUMIC", 
/*  270 */           GolemHelper.getSealStack("thaumcraft:guard_advanced"), 
/*  271 */           GolemHelper.getSealStack("thaumcraft:guard"), (new AspectList()).add(Aspect.SENSES, 20).add(Aspect.MIND, 20)));
/*      */     
/*  273 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealLumber"), new CrucibleRecipe("SEALLUMBER", 
/*  274 */           GolemHelper.getSealStack("thaumcraft:lumber"), 
/*  275 */           GolemHelper.getSealStack("thaumcraft:breaker"), (new AspectList()).add(Aspect.PLANT, 40).add(Aspect.SENSES, 20)));
/*      */     
/*  277 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealUse"), new CrucibleRecipe("SEALUSE", 
/*  278 */           GolemHelper.getSealStack("thaumcraft:use"), new ItemStack(ItemsTC.seals), (new AspectList())
/*  279 */           .add(Aspect.CRAFT, 20).add(Aspect.SENSES, 10).add(Aspect.MIND, 20)));
/*      */     
/*  281 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:SealBreakAdv"), new CrucibleRecipe("SEALBREAK&&MINDBIOTHAUMIC", 
/*  282 */           GolemHelper.getSealStack("thaumcraft:breaker_advanced"), 
/*  283 */           GolemHelper.getSealStack("thaumcraft:breaker"), (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.MIND, 10).add(Aspect.TOOL, 20)));
/*      */ 
/*      */     
/*  286 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:EverfullUrn"), new CrucibleRecipe("EVERFULLURN", new ItemStack(BlocksTC.everfullUrn), new ItemStack(Items.field_151162_bE), (new AspectList())
/*  287 */           .add(Aspect.WATER, 30).add(Aspect.CRAFT, 10).add(Aspect.EARTH, 10)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void initializeArcaneRecipes(IForgeRegistry<IRecipe> iForgeRegistry) {
/*  295 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:mechanism_simple"), new ShapedArcaneRecipe(defaultGroup, "BASEARTIFICE", 10, (new AspectList())
/*  296 */           .add(Aspect.FIRE, 1).add(Aspect.WATER, 1), ItemsTC.mechanismSimple, new Object[] { " B ", "ISI", " B ", 
/*  297 */             Character.valueOf('B'), "plateBrass", 
/*  298 */             Character.valueOf('I'), "plateIron", Character.valueOf('S'), "stickWood" }));
/*  299 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:mechanism_complex"), new ShapedArcaneRecipe(defaultGroup, "BASEARTIFICE", 50, (new AspectList())
/*  300 */           .add(Aspect.FIRE, 1).add(Aspect.WATER, 1), ItemsTC.mechanismComplex, new Object[] { " M ", "TQT", " M ", 
/*  301 */             Character.valueOf('T'), "plateThaumium", 
/*  302 */             Character.valueOf('Q'), Blocks.field_150331_J, Character.valueOf('M'), new ItemStack(ItemsTC.mechanismSimple) }));
/*      */ 
/*      */     
/*  305 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:vis_resonator"), new ShapelessArcaneRecipe(defaultGroup, "UNLOCKAUROMANCY@2", 50, (new AspectList())
/*  306 */           .add(Aspect.AIR, 1).add(Aspect.WATER, 1), ItemsTC.visResonator, new Object[] { "plateIron", "gemQuartz" }));
/*      */ 
/*      */ 
/*      */     
/*  310 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:activatorrail"), new ShapelessArcaneRecipe(defaultGroup, "FIRSTSTEPS", 10, null, BlocksTC.activatorRail, new Object[] { new ItemStack(Blocks.field_150408_cc) }));
/*      */ 
/*      */ 
/*      */     
/*  314 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:thaumometer"), new ShapedArcaneRecipe(defaultGroup, "FIRSTSTEPS@2", 20, (new AspectList())
/*      */           
/*  316 */           .add(Aspect.AIR, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.FIRE, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), ItemsTC.thaumometer, new Object[] { " I ", "IGI", " I ", 
/*  317 */             Character.valueOf('I'), "ingotGold", 
/*  318 */             Character.valueOf('G'), new ItemStack(Blocks.field_150410_aZ) }));
/*      */ 
/*      */     
/*  321 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:sanitychecker"), new ShapedArcaneRecipe(defaultGroup, "WARP", 20, (new AspectList())
/*  322 */           .add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), ItemsTC.sanityChecker, new Object[] { "BN ", "M N", "BN ", 
/*      */             
/*  324 */             Character.valueOf('N'), "nuggetBrass", Character.valueOf('B'), new ItemStack(ItemsTC.brain), Character.valueOf('M'), new ItemStack(ItemsTC.mirroredGlass) }));
/*      */ 
/*      */     
/*  327 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:rechargepedestal"), new ShapedArcaneRecipe(defaultGroup, "RECHARGEPEDESTAL", 100, (new AspectList())
/*  328 */           .add(Aspect.AIR, 1).add(Aspect.ORDER, 1), BlocksTC.rechargePedestal, new Object[] { " R ", "DID", "SSS", 
/*  329 */             Character.valueOf('I'), "ingotGold", Character.valueOf('D'), "gemDiamond", 
/*  330 */             Character.valueOf('R'), new ItemStack(ItemsTC.visResonator), Character.valueOf('S'), "stone" }));
/*      */ 
/*      */     
/*  333 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:workbenchcharger"), new ShapedArcaneRecipe(defaultGroup, "WORKBENCHCHARGER", 200, (new AspectList())
/*  334 */           .add(Aspect.AIR, 2).add(Aspect.ORDER, 2), new ItemStack(BlocksTC.arcaneWorkbenchCharger), new Object[] { " R ", "W W", "I I", 
/*  335 */             Character.valueOf('I'), "ingotIron", Character.valueOf('R'), new ItemStack(ItemsTC.visResonator), 
/*  336 */             Character.valueOf('W'), new ItemStack(BlocksTC.plankGreatwood) }));
/*      */ 
/*      */     
/*  339 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:wand_workbench"), new ShapedArcaneRecipe(defaultGroup, "BASEAUROMANCY@2", 100, (new AspectList())
/*  340 */           .add(Aspect.EARTH, 1).add(Aspect.WATER, 1), new ItemStack(BlocksTC.wandWorkbench), new Object[] { "ISI", "BRB", "GTG", 
/*      */             
/*  342 */             Character.valueOf('S'), new ItemStack(BlocksTC.slabArcaneStone), Character.valueOf('T'), new ItemStack(BlocksTC.tableStone), 
/*  343 */             Character.valueOf('R'), new ItemStack(ItemsTC.visResonator), Character.valueOf('B'), new ItemStack(BlocksTC.stoneArcane), 
/*  344 */             Character.valueOf('G'), "ingotGold", Character.valueOf('I'), "plateIron" }));
/*      */ 
/*      */ 
/*      */     
/*  348 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:caster_basic"), new ShapedArcaneRecipe(defaultGroup, "UNLOCKAUROMANCY@2", 100, (new AspectList())
/*      */           
/*  350 */           .add(Aspect.AIR, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.FIRE, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), new ItemStack(ItemsTC.casterBasic), new Object[] { "III", "LRL", "LTL", 
/*      */             
/*  352 */             Character.valueOf('T'), new ItemStack(ItemsTC.thaumometer), Character.valueOf('R'), new ItemStack(ItemsTC.visResonator), 
/*  353 */             Character.valueOf('L'), "leather", Character.valueOf('I'), "ingotIron" }));
/*      */ 
/*      */ 
/*      */     
/*  357 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:EnchantedFabric"), new ShapedArcaneRecipe(defaultGroup, "UNLOCKINFUSION", 5, null, new ItemStack(ItemsTC.fabric), new Object[] { " S ", "SCS", " S ", 
/*  358 */             Character.valueOf('S'), "string", 
/*  359 */             Character.valueOf('C'), new ItemStack(Blocks.field_150325_L, 1, 32767) }));
/*      */ 
/*      */     
/*  362 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:RobeChest"), new ShapedArcaneRecipe(defaultGroup, "UNLOCKINFUSION", 100, null, new ItemStack(ItemsTC.clothChest, 1), new Object[] { "I I", "III", "III", 
/*      */             
/*  364 */             Character.valueOf('I'), new ItemStack(ItemsTC.fabric) }));
/*  365 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:RobeLegs"), new ShapedArcaneRecipe(defaultGroup, "UNLOCKINFUSION", 100, null, new ItemStack(ItemsTC.clothLegs, 1), new Object[] { "III", "I I", "I I", 
/*      */             
/*  367 */             Character.valueOf('I'), new ItemStack(ItemsTC.fabric) }));
/*  368 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:RobeBoots"), new ShapedArcaneRecipe(defaultGroup, "UNLOCKINFUSION", 100, null, new ItemStack(ItemsTC.clothBoots, 1), new Object[] { "I I", "I I", 
/*      */             
/*  370 */             Character.valueOf('I'), new ItemStack(ItemsTC.fabric) }));
/*      */ 
/*      */     
/*  373 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Goggles"), new ShapedArcaneRecipe(defaultGroup, "UNLOCKARTIFICE", 50, null, new ItemStack(ItemsTC.goggles), new Object[] { "LGL", "L L", "TGT", 
/*  374 */             Character.valueOf('T'), new ItemStack(ItemsTC.thaumometer), 
/*  375 */             Character.valueOf('G'), "ingotBrass", Character.valueOf('L'), "leather" }));
/*      */ 
/*      */     
/*  378 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:SealBlank"), new ShapelessArcaneRecipe(defaultGroup, "CONTROLSEALS", 20, (new AspectList())
/*  379 */           .add(Aspect.AIR, 1), new ItemStack(ItemsTC.seals, 3), new Object[] { new ItemStack(Items.field_151119_aD), new ItemStack(ItemsTC.tallow), "dyeRed", "nitor" }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  384 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:modvision"), new ShapedArcaneRecipe(defaultGroup, "GOLEMVISION", 50, (new AspectList())
/*  385 */           .add(Aspect.WATER, 1), new ItemStack(ItemsTC.modules, 1, 0), new Object[] { "B B", "E E", "PGP", 
/*  386 */             Character.valueOf('B'), new ItemStack(Items.field_151069_bo), 
/*  387 */             Character.valueOf('E'), new ItemStack(Items.field_151071_bq), Character.valueOf('P'), "plateBrass", 
/*  388 */             Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple) }));
/*      */     
/*  390 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:modaggression"), new ShapedArcaneRecipe(defaultGroup, "SEALGUARD", 50, (new AspectList())
/*  391 */           .add(Aspect.FIRE, 1), new ItemStack(ItemsTC.modules, 1, 1), new Object[] { " R ", "RTR", "PGP", 
/*  392 */             Character.valueOf('R'), "paneGlass", Character.valueOf('T'), new ItemStack(Items.field_151065_br), 
/*  393 */             Character.valueOf('P'), "plateBrass", Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple) }));
/*      */ 
/*      */     
/*  396 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:mirrorglass"), new ShapelessArcaneRecipe(defaultGroup, "BASEARTIFICE", 50, (new AspectList())
/*  397 */           .add(Aspect.WATER, 1).add(Aspect.ORDER, 1), new ItemStack(ItemsTC.mirroredGlass), new Object[] { new ItemStack(ItemsTC.quicksilver), "paneGlass" }));
/*      */ 
/*      */ 
/*      */     
/*  401 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:ArcaneSpa"), new ShapedArcaneRecipe(defaultGroup, "ARCANESPA", 50, (new AspectList())
/*  402 */           .add(Aspect.WATER, 1), new ItemStack(BlocksTC.spa), new Object[] { "QIQ", "SJS", "SPS", 
/*  403 */             Character.valueOf('P'), new ItemStack(ItemsTC.mechanismSimple), 
/*  404 */             Character.valueOf('J'), new ItemStack(BlocksTC.jarNormal), Character.valueOf('S'), new ItemStack(BlocksTC.stoneArcane), 
/*  405 */             Character.valueOf('Q'), new ItemStack(Blocks.field_150371_ca), Character.valueOf('I'), new ItemStack(Blocks.field_150411_aY) }));
/*      */ 
/*      */     
/*  408 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Tube"), new ShapedArcaneRecipe(defaultGroup, "TUBES", 10, null, new ItemStack(BlocksTC.tube, 8, 0), new Object[] { " Q ", "IGI", " B ", 
/*  409 */             Character.valueOf('I'), "plateIron", 
/*  410 */             Character.valueOf('B'), "nuggetBrass", Character.valueOf('G'), "blockGlass", Character.valueOf('Q'), "nuggetQuicksilver" }));
/*  411 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Resonator"), new ShapedArcaneRecipe(defaultGroup, "TUBES", 50, null, new ItemStack(ItemsTC.resonator), new Object[] { "I I", "INI", " S ", 
/*      */             
/*  413 */             Character.valueOf('I'), "plateIron", 
/*  414 */             Character.valueOf('N'), Items.field_151128_bU, Character.valueOf('S'), "stickWood" }));
/*  415 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:TubeValve"), new ShapelessArcaneRecipe(defaultGroup, "TUBES", 10, null, new ItemStack(BlocksTC.tubeValve), new Object[] { new ItemStack(BlocksTC.tube), new ItemStack(Blocks.field_150442_at) }));
/*      */ 
/*      */     
/*  418 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:TubeFilter"), new ShapelessArcaneRecipe(defaultGroup, "TUBES", 10, null, new ItemStack(BlocksTC.tubeFilter), new Object[] { new ItemStack(BlocksTC.tube, 1, 0), new ItemStack(ItemsTC.filter) }));
/*      */ 
/*      */     
/*  421 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:TubeRestrict"), new ShapelessArcaneRecipe(defaultGroup, "TUBES", 10, (new AspectList())
/*  422 */           .add(Aspect.EARTH, 1), new ItemStack(BlocksTC.tubeRestrict), new Object[] { new ItemStack(BlocksTC.tube) }));
/*      */     
/*  424 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:TubeOneway"), new ShapelessArcaneRecipe(defaultGroup, "TUBES", 10, (new AspectList())
/*  425 */           .add(Aspect.WATER, 1), new ItemStack(BlocksTC.tubeOneway), new Object[] { new ItemStack(BlocksTC.tube) }));
/*      */     
/*  427 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:TubeBuffer"), new ShapedArcaneRecipe(defaultGroup, "TUBES", 25, null, new ItemStack(BlocksTC.tubeBuffer), new Object[] { "PVP", "TWT", "PRP", 
/*      */             
/*  429 */             Character.valueOf('T'), new ItemStack(BlocksTC.tube), 
/*  430 */             Character.valueOf('V'), new ItemStack(BlocksTC.tubeValve), Character.valueOf('W'), "plateIron", 
/*  431 */             Character.valueOf('R'), new ItemStack(BlocksTC.tubeRestrict), Character.valueOf('P'), new ItemStack(ItemsTC.phial) }));
/*      */ 
/*      */     
/*  434 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:WardedJar"), new ShapedArcaneRecipe(defaultGroup, "WARDEDJARS", 5, null, new ItemStack(BlocksTC.jarNormal), new Object[] { "GWG", "G G", "GGG", 
/*      */             
/*  436 */             Character.valueOf('W'), "slabWood", Character.valueOf('G'), "paneGlass" }));
/*      */ 
/*      */     
/*  439 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:JarVoid"), new ShapedArcaneVoidJar(defaultGroup, "WARDEDJARS", 50, (new AspectList())
/*  440 */           .add(Aspect.ENTROPY, 1), new ItemStack(BlocksTC.jarVoid), new Object[] { "J", 
/*  441 */             Character.valueOf('J'), new ItemStack(BlocksTC.jarNormal) }));
/*      */ 
/*      */     
/*  444 */     ResourceLocation bannerGroup = new ResourceLocation("thaumcraft", "banners");
/*  445 */     int a = 0;
/*  446 */     for (EnumDyeColor d : EnumDyeColor.values()) {
/*  447 */       ItemStack banner = new ItemStack((Block)BlocksTC.banners.get(d));
/*  448 */       ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Banner" + d
/*  449 */             .func_176762_d().toLowerCase()), new ShapedArcaneRecipe(bannerGroup, "BASEINFUSION", 10, null, banner, new Object[] { "WS", "WS", "WB", 
/*  450 */               Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, a), Character.valueOf('S'), "stickWood", Character.valueOf('B'), "slabWood" }));
/*  451 */       a++;
/*      */     } 
/*      */ 
/*      */     
/*  455 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:PaveBarrier"), new ShapedArcaneRecipe(defaultGroup, "PAVINGSTONES", 50, (new AspectList())
/*      */           
/*  457 */           .add(Aspect.FIRE, 1).add(Aspect.ORDER, 1), new ItemStack(BlocksTC.pavingStoneBarrier, 4), new Object[] { "SS", "SS", 
/*  458 */             Character.valueOf('S'), new ItemStack(BlocksTC.stoneArcaneBrick) }));
/*      */ 
/*      */     
/*  461 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:PaveTravel"), new ShapedArcaneRecipe(defaultGroup, "PAVINGSTONES", 50, (new AspectList())
/*      */           
/*  463 */           .add(Aspect.AIR, 1).add(Aspect.EARTH, 1), new ItemStack(BlocksTC.pavingStoneTravel, 4), new Object[] { "SS", "SS", 
/*  464 */             Character.valueOf('S'), new ItemStack(BlocksTC.stoneArcaneBrick) }));
/*      */ 
/*      */     
/*  467 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:ArcaneLamp"), new ShapedArcaneRecipe(defaultGroup, "ARCANELAMP", 50, (new AspectList())
/*  468 */           .add(Aspect.AIR, 1).add(Aspect.FIRE, 1), new ItemStack(BlocksTC.lampArcane), new Object[] { " I ", "IAI", " I ", 
/*  469 */             Character.valueOf('A'), new ItemStack(BlocksTC.amberBlock), Character.valueOf('I'), "plateIron" }));
/*      */ 
/*      */     
/*  472 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Levitator"), new ShapedArcaneRecipe(defaultGroup, "LEVITATOR", 35, (new AspectList())
/*  473 */           .add(Aspect.AIR, 1), new ItemStack(BlocksTC.levitator), new Object[] { "WIW", "BNB", "WGW", 
/*  474 */             Character.valueOf('I'), "plateThaumium", Character.valueOf('N'), "nitor", 
/*  475 */             Character.valueOf('W'), "plankWood", Character.valueOf('B'), "plateIron", Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple) }));
/*      */ 
/*      */     
/*  478 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:RedstoneRelay"), new ShapedArcaneRecipe(defaultGroup, "REDSTONERELAY", 10, (new AspectList())
/*  479 */           .add(Aspect.ORDER, 1), new ItemStack(BlocksTC.redstoneRelay), new Object[] { "   ", "TGT", "SSS", 
/*  480 */             Character.valueOf('T'), new ItemStack(Blocks.field_150429_aA), 
/*  481 */             Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple), Character.valueOf('S'), new ItemStack(Blocks.field_150333_U) }));
/*      */ 
/*      */     
/*  484 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:ArcaneEar"), new ShapedArcaneRecipe(defaultGroup, "ARCANEEAR", 15, (new AspectList())
/*  485 */           .add(Aspect.AIR, 1), new ItemStack(BlocksTC.arcaneEar), new Object[] { "P P", " G ", "WRW", 
/*  486 */             Character.valueOf('W'), "slabWood", Character.valueOf('R'), Items.field_151137_ax, 
/*  487 */             Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple), Character.valueOf('P'), "plateBrass" }));
/*      */     
/*  489 */     shapelessOreDictRecipe("ArcaneEarToggle", defaultGroup, new ItemStack(BlocksTC.arcaneEarToggle), new Object[] { new ItemStack(BlocksTC.arcaneEar), new ItemStack(Blocks.field_150442_at) });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  494 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:InfusionMatrix"), new ShapedArcaneRecipe(defaultGroup, "INFUSION@2", 150, (new AspectList())
/*  495 */           .add(Aspect.AIR, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.FIRE, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), new ItemStack(BlocksTC.infusionMatrix), new Object[] { "S S", " N ", "S S", 
/*  496 */             Character.valueOf('S'), new ItemStack(BlocksTC.stoneArcaneBrick), 
/*  497 */             Character.valueOf('N'), "nitor" }));
/*      */     
/*  499 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:MatrixMotion"), new ShapedArcaneRecipe(defaultGroup, "INFUSIONBOOST", 500, (new AspectList())
/*  500 */           .add(Aspect.AIR, 1).add(Aspect.ORDER, 1), new ItemStack(BlocksTC.matrixSpeed), new Object[] { "SNS", "NGN", "SNS", 
/*  501 */             Character.valueOf('S'), new ItemStack(BlocksTC.stoneArcane), 
/*  502 */             Character.valueOf('N'), "nitor", Character.valueOf('G'), new ItemStack(Blocks.field_150484_ah) }));
/*      */     
/*  504 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:MatrixCost"), new ShapedArcaneRecipe(defaultGroup, "INFUSIONBOOST", 500, (new AspectList())
/*  505 */           .add(Aspect.AIR, 1).add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1), new ItemStack(BlocksTC.matrixCost), new Object[] { "SAS", "AGA", "SAS", 
/*  506 */             Character.valueOf('S'), new ItemStack(BlocksTC.stoneArcane), 
/*  507 */             Character.valueOf('A'), new ItemStack(ItemsTC.alumentum), Character.valueOf('G'), new ItemStack(Blocks.field_150484_ah) }));
/*      */ 
/*      */     
/*  510 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:ArcanePedestal"), new ShapedArcaneRecipe(defaultGroup, "INFUSION", 10, null, new ItemStack(BlocksTC.pedestalArcane), new Object[] { "SSS", " B ", "SSS", 
/*      */             
/*  512 */             Character.valueOf('S'), new ItemStack(BlocksTC.slabArcaneStone), 
/*  513 */             Character.valueOf('B'), new ItemStack(BlocksTC.stoneArcane) }));
/*  514 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:AncientPedestal"), new ShapedArcaneRecipe(defaultGroup, "INFUSIONANCIENT", 150, null, new ItemStack(BlocksTC.pedestalAncient), new Object[] { "SSS", " B ", "SSS", 
/*      */             
/*  516 */             Character.valueOf('S'), new ItemStack(BlocksTC.slabAncient), 
/*  517 */             Character.valueOf('B'), new ItemStack(BlocksTC.stoneAncient) }));
/*  518 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:EldritchPedestal"), new ShapedArcaneRecipe(defaultGroup, "INFUSIONELDRITCH", 150, null, new ItemStack(BlocksTC.pedestalEldritch), new Object[] { "SSS", " B ", "SSS", 
/*      */             
/*  520 */             Character.valueOf('S'), new ItemStack(BlocksTC.slabEldritch), 
/*  521 */             Character.valueOf('B'), new ItemStack(BlocksTC.stoneEldritchTile) }));
/*      */ 
/*      */     
/*  524 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:FocusPouch"), new ShapedArcaneRecipe(defaultGroup, "FOCUSPOUCH", 25, null, new ItemStack(ItemsTC.focusPouch), new Object[] { "LGL", "LBL", "LLL", 
/*      */             
/*  526 */             Character.valueOf('B'), new ItemStack(ItemsTC.baubles, 1, 2), Character.valueOf('L'), "leather", 
/*  527 */             Character.valueOf('G'), Items.field_151043_k }));
/*      */ 
/*      */     
/*  530 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:dioptra"), new ShapedArcaneRecipe(defaultGroup, "DIOPTRA", 50, (new AspectList())
/*  531 */           .add(Aspect.AIR, 1).add(Aspect.WATER, 1), new ItemStack(BlocksTC.dioptra), new Object[] { "APA", "IGI", "AAA", 
/*  532 */             Character.valueOf('A'), new ItemStack(BlocksTC.stoneArcane), 
/*  533 */             Character.valueOf('G'), new ItemStack(ItemsTC.thaumometer), Character.valueOf('P'), new ItemStack(ItemsTC.visResonator), 
/*  534 */             Character.valueOf('I'), "plateIron" }));
/*      */ 
/*      */     
/*  537 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:HungryChest"), new ShapedArcaneRecipe(defaultGroup, "HUNGRYCHEST", 15, (new AspectList())
/*  538 */           .add(Aspect.EARTH, 1).add(Aspect.WATER, 1), new ItemStack(BlocksTC.hungryChest), new Object[] { "WTW", "W W", "WWW", 
/*  539 */             Character.valueOf('W'), new ItemStack(BlocksTC.plankGreatwood), 
/*  540 */             Character.valueOf('T'), "trapdoorWood" }));
/*      */ 
/*      */     
/*  543 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Filter"), new ShapedArcaneRecipe(defaultGroup, "BASEALCHEMY", 15, (new AspectList())
/*  544 */           .add(Aspect.WATER, 1), new ItemStack(ItemsTC.filter, 2, 0), new Object[] { "GWG", 
/*  545 */             Character.valueOf('G'), Items.field_151043_k, Character.valueOf('W'), new ItemStack(BlocksTC.plankSilverwood) }));
/*      */ 
/*      */     
/*  548 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:MorphicResonator"), new ShapedArcaneRecipe(defaultGroup, "BASEALCHEMY", 50, (new AspectList())
/*  549 */           .add(Aspect.AIR, 1).add(Aspect.FIRE, 1), new ItemStack(ItemsTC.morphicResonator), new Object[] { " G ", "BSB", " G ", 
/*  550 */             Character.valueOf('G'), "paneGlass", Character.valueOf('B'), "plateBrass", Character.valueOf('S'), new ItemStack(ItemsTC.nuggets, 1, 10) }));
/*      */ 
/*      */     
/*  553 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Alembic"), new ShapedArcaneRecipe(defaultGroup, "ESSENTIASMELTER", 50, (new AspectList())
/*  554 */           .add(Aspect.WATER, 1), new ItemStack(BlocksTC.alembic), new Object[] { "WFW", "SBS", "WFW", 
/*  555 */             Character.valueOf('W'), new ItemStack(BlocksTC.plankGreatwood), Character.valueOf('B'), Items.field_151133_ar, 
/*  556 */             Character.valueOf('F'), new ItemStack(ItemsTC.filter), Character.valueOf('S'), "plateBrass" }));
/*      */ 
/*      */     
/*  559 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:EssentiaSmelter"), new ShapedArcaneRecipe(defaultGroup, "ESSENTIASMELTER@2", 50, (new AspectList())
/*  560 */           .add(Aspect.FIRE, 1), new ItemStack(BlocksTC.smelterBasic), new Object[] { "BCB", "SFS", "SSS", 
/*  561 */             Character.valueOf('C'), new ItemStack(BlocksTC.crucible), 
/*  562 */             Character.valueOf('F'), new ItemStack(Blocks.field_150460_al), Character.valueOf('S'), "cobblestone", 
/*  563 */             Character.valueOf('B'), "plateBrass" }));
/*      */     
/*  565 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:EssentiaSmelterThaumium"), new ShapedArcaneRecipe(defaultGroup, "ESSENTIASMELTERTHAUMIUM", 250, (new AspectList())
/*  566 */           .add(Aspect.FIRE, 2), new ItemStack(BlocksTC.smelterThaumium), new Object[] { "BFB", "IGI", "III", 
/*  567 */             Character.valueOf('F'), new ItemStack(BlocksTC.smelterBasic), Character.valueOf('G'), new ItemStack(BlocksTC.metalAlchemical), 
/*  568 */             Character.valueOf('I'), "plateThaumium", Character.valueOf('B'), "plateBrass" }));
/*      */     
/*  570 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:EssentiaSmelterVoid"), new ShapedArcaneRecipe(defaultGroup, "ESSENTIASMELTERVOID", 750, (new AspectList())
/*  571 */           .add(Aspect.FIRE, 3), new ItemStack(BlocksTC.smelterVoid), new Object[] { "BFB", "IGI", "III", 
/*  572 */             Character.valueOf('F'), new ItemStack(BlocksTC.smelterBasic), Character.valueOf('G'), new ItemStack(BlocksTC.metalAlchemicalAdvanced), 
/*  573 */             Character.valueOf('I'), "plateVoid", Character.valueOf('B'), "plateBrass" }));
/*      */ 
/*      */     
/*  576 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:AlchemicalConstruct"), new ShapedArcaneRecipe(defaultGroup, "TUBES", 75, (new AspectList())
/*  577 */           .add(Aspect.WATER, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), new ItemStack(BlocksTC.metalAlchemical, 2), new Object[] { "IVI", "TWT", "IVI", 
/*  578 */             Character.valueOf('W'), new ItemStack(BlocksTC.plankGreatwood), Character.valueOf('V'), new ItemStack(BlocksTC.tubeValve), 
/*  579 */             Character.valueOf('T'), new ItemStack(BlocksTC.tube), Character.valueOf('I'), "plateIron" }));
/*      */     
/*  581 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:AdvAlchemyConstruct"), new ShapedArcaneRecipe(defaultGroup, "ESSENTIASMELTERVOID@1", 200, (new AspectList())
/*  582 */           .add(Aspect.EARTH, 1).add(Aspect.FIRE, 1), new ItemStack(BlocksTC.metalAlchemicalAdvanced), new Object[] { " A ", "VPV", " A ", 
/*  583 */             Character.valueOf('A'), new ItemStack(BlocksTC.metalAlchemical), 
/*  584 */             Character.valueOf('V'), "plateVoid", Character.valueOf('P'), Ingredient.func_193367_a(ItemsTC.primordialPearl) }));
/*      */ 
/*      */     
/*  587 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:PotionSprayer"), new ShapedArcaneRecipe(defaultGroup, "POTIONSPRAYER", 75, (new AspectList())
/*  588 */           .add(Aspect.WATER, 1).add(Aspect.FIRE, 1), new ItemStack(BlocksTC.potionSprayer), new Object[] { "BDB", "IAI", "ICI", 
/*  589 */             Character.valueOf('B'), "plateBrass", Character.valueOf('I'), "plateIron", 
/*  590 */             Character.valueOf('A'), new ItemStack(Items.field_151067_bt), Character.valueOf('D'), new ItemStack(Blocks.field_150367_z), 
/*  591 */             Character.valueOf('C'), new ItemStack(BlocksTC.metalAlchemical) }));
/*      */ 
/*      */     
/*  594 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:SmelterAux"), new ShapedArcaneRecipe(defaultGroup, "IMPROVEDSMELTING", 100, (new AspectList())
/*  595 */           .add(Aspect.AIR, 1).add(Aspect.EARTH, 1), new ItemStack(BlocksTC.smelterAux), new Object[] { "WTW", "RGR", "IBI", 
/*  596 */             Character.valueOf('W'), new ItemStack(BlocksTC.plankGreatwood), 
/*  597 */             Character.valueOf('B'), new ItemStack(BlocksTC.bellows), Character.valueOf('R'), "plateBrass", 
/*  598 */             Character.valueOf('T'), new ItemStack(BlocksTC.tubeFilter), 
/*  599 */             Character.valueOf('I'), "plateIron", Character.valueOf('G'), new ItemStack(BlocksTC.metalAlchemical) }));
/*      */ 
/*      */     
/*  602 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:SmelterVent"), new ShapedArcaneRecipe(defaultGroup, "IMPROVEDSMELTING2", 150, (new AspectList())
/*  603 */           .add(Aspect.AIR, 1), new ItemStack(BlocksTC.smelterVent), new Object[] { "IBI", "MGF", "IBI", 
/*  604 */             Character.valueOf('I'), "plateIron", Character.valueOf('B'), "plateBrass", 
/*  605 */             Character.valueOf('F'), new ItemStack(ItemsTC.filter), Character.valueOf('M'), new ItemStack(ItemsTC.filter), 
/*  606 */             Character.valueOf('G'), new ItemStack(BlocksTC.metalAlchemical) }));
/*      */ 
/*      */     
/*  609 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:EssentiaTransportIn"), new ShapedArcaneRecipe(defaultGroup, "ESSENTIATRANSPORT", 100, (new AspectList())
/*  610 */           .add(Aspect.AIR, 1).add(Aspect.WATER, 1), new ItemStack(BlocksTC.essentiaTransportInput), new Object[] { "   ", "BQB", "IGI", 
/*  611 */             Character.valueOf('I'), "plateIron", Character.valueOf('B'), "plateBrass", 
/*  612 */             Character.valueOf('Q'), new ItemStack(Blocks.field_150367_z), Character.valueOf('G'), new ItemStack(BlocksTC.metalAlchemical) }));
/*      */     
/*  614 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:EssentiaTransportOut"), new ShapedArcaneRecipe(defaultGroup, "ESSENTIATRANSPORT", 100, (new AspectList())
/*  615 */           .add(Aspect.AIR, 1).add(Aspect.WATER, 1), new ItemStack(BlocksTC.essentiaTransportOutput), new Object[] { "   ", "BQB", "IGI", 
/*  616 */             Character.valueOf('I'), "plateIron", Character.valueOf('B'), "plateBrass", 
/*  617 */             Character.valueOf('Q'), new ItemStack(Blocks.field_150438_bZ), Character.valueOf('G'), new ItemStack(BlocksTC.metalAlchemical) }));
/*      */ 
/*      */     
/*  620 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Bellows"), new ShapedArcaneRecipe(defaultGroup, "BELLOWS", 25, (new AspectList())
/*  621 */           .add(Aspect.AIR, 1), new ItemStack(BlocksTC.bellows), new Object[] { "WW ", "LLI", "WW ", 
/*  622 */             Character.valueOf('W'), "plankWood", Character.valueOf('I'), "ingotIron", Character.valueOf('L'), "leather" }));
/*      */ 
/*      */ 
/*      */     
/*  626 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Centrifuge"), new ShapedArcaneRecipe(defaultGroup, "CENTRIFUGE", 100, (new AspectList())
/*  627 */           .add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), new ItemStack(BlocksTC.centrifuge), new Object[] { " T ", "RCP", " T ", 
/*  628 */             Character.valueOf('T'), new ItemStack(BlocksTC.tube), 
/*  629 */             Character.valueOf('P'), new ItemStack(ItemsTC.mechanismSimple), Character.valueOf('R'), new ItemStack(ItemsTC.morphicResonator), 
/*  630 */             Character.valueOf('C'), new ItemStack(BlocksTC.metalAlchemical) }));
/*      */ 
/*      */     
/*  633 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:MnemonicMatrix"), new ShapedArcaneRecipe(defaultGroup, "THAUMATORIUM", 50, (new AspectList())
/*  634 */           .add(Aspect.EARTH, 1).add(Aspect.ORDER, 1), new ItemStack(BlocksTC.brainBox), new Object[] { "IAI", "ABA", "IAI", 
/*  635 */             Character.valueOf('B'), new ItemStack(ItemsTC.mind, 1, 0), 
/*  636 */             Character.valueOf('A'), "gemAmber", Character.valueOf('I'), "plateIron" }));
/*      */ 
/*      */     
/*  639 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:MindClockwork"), new ShapedArcaneRecipe(defaultGroup, "MINDCLOCKWORK@2", 25, (new AspectList())
/*  640 */           .add(Aspect.FIRE, 1).add(Aspect.ORDER, 1), new ItemStack(ItemsTC.mind, 1, 0), new Object[] { " P ", "PGP", "BCB", 
/*  641 */             Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple), Character.valueOf('B'), "plateBrass", 
/*  642 */             Character.valueOf('P'), "paneGlass", Character.valueOf('C'), new ItemStack(Items.field_151132_bS) }));
/*      */ 
/*      */     
/*  645 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:AutomatedCrossbow"), new ShapedArcaneRecipe(defaultGroup, "BASICTURRET", 100, (new AspectList())
/*  646 */           .add(Aspect.AIR, 1), new ItemStack(ItemsTC.turretPlacer, 1, 0), new Object[] { "BGI", "WMW", "S S", 
/*  647 */             Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple), Character.valueOf('I'), "plateIron", 
/*  648 */             Character.valueOf('S'), "stickWood", Character.valueOf('M'), new ItemStack(ItemsTC.mind), 
/*  649 */             Character.valueOf('B'), Ingredient.func_193367_a(Items.field_151031_f), Character.valueOf('W'), new ItemStack(BlocksTC.plankGreatwood) }));
/*      */     
/*  651 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:AdvancedCrossbow"), new ShapedArcaneRecipe(defaultGroup, "ADVANCEDTURRET", 150, (new AspectList())
/*  652 */           .add(Aspect.AIR, 2), new ItemStack(ItemsTC.turretPlacer, 1, 1), new Object[] { "PMP", "PTP", "   ", 
/*  653 */             Character.valueOf('T'), new ItemStack(ItemsTC.turretPlacer, 1, 0), 
/*  654 */             Character.valueOf('P'), "plateIron", Character.valueOf('M'), new ItemStack(ItemsTC.mind, 1, 1) }));
/*      */ 
/*      */     
/*  657 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:patterncrafter"), new ShapedArcaneRecipe(defaultGroup, "ARCANEPATTERNCRAFTER", 50, (new AspectList())
/*  658 */           .add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1), new ItemStack(BlocksTC.patternCrafter), new Object[] { "VH ", "GCG", " W ", 
/*  659 */             Character.valueOf('H'), new ItemStack(Blocks.field_150438_bZ), 
/*  660 */             Character.valueOf('W'), new ItemStack(BlocksTC.plankGreatwood), Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple), Character.valueOf('V'), new ItemStack(ItemsTC.visResonator), 
/*  661 */             Character.valueOf('C'), "workbench" }));
/*      */ 
/*      */     
/*  664 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:GrappleGunTip"), new ShapedArcaneRecipe(defaultGroup, "GRAPPLEGUN", 25, (new AspectList())
/*  665 */           .add(Aspect.EARTH, 1), new ItemStack(ItemsTC.grappleGunTip), new Object[] { "BRB", "RHR", "BRB", 
/*  666 */             Character.valueOf('B'), "plateBrass", Character.valueOf('R'), new ItemStack(ItemsTC.nuggets, 1, 10), 
/*  667 */             Character.valueOf('H'), new ItemStack(Blocks.field_150479_bC) }));
/*  668 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:GrappleGunSpool"), new ShapedArcaneRecipe(defaultGroup, "GRAPPLEGUN", 25, (new AspectList())
/*  669 */           .add(Aspect.WATER, 1), new ItemStack(ItemsTC.grappleGunSpool), new Object[] { "SHS", "SGS", "SSS", 
/*  670 */             Character.valueOf('G'), new ItemStack(ItemsTC.mechanismSimple), Character.valueOf('S'), "string", 
/*  671 */             Character.valueOf('H'), new ItemStack(Blocks.field_150479_bC) }));
/*  672 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:GrappleGun"), new ShapedArcaneRecipe(defaultGroup, "GRAPPLEGUN", 75, (new AspectList())
/*  673 */           .add(Aspect.AIR, 1).add(Aspect.FIRE, 1), new ItemStack(ItemsTC.grappleGun), new Object[] { "  S", "TII", " BW", 
/*  674 */             Character.valueOf('B'), "plateBrass", Character.valueOf('I'), "plateIron", 
/*  675 */             Character.valueOf('T'), new ItemStack(ItemsTC.grappleGunTip), Character.valueOf('W'), "plankWood", 
/*  676 */             Character.valueOf('S'), new ItemStack(ItemsTC.grappleGunSpool) }));
/*      */ 
/*      */     
/*  679 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:VisBattery"), new ShapedArcaneRecipe(defaultGroup, "VISBATTERY", 50, (new AspectList())
/*  680 */           .add(Aspect.AIR, 2).add(Aspect.EARTH, 2).add(Aspect.WATER, 2).add(Aspect.FIRE, 2).add(Aspect.ORDER, 2).add(Aspect.ENTROPY, 2), new ItemStack(BlocksTC.visBattery), new Object[] { "SSS", "SRS", "SSS", 
/*  681 */             Character.valueOf('R'), new ItemStack(ItemsTC.visResonator), Character.valueOf('S'), new ItemStack(BlocksTC.slabArcaneStone) }));
/*      */ 
/*      */     
/*  684 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:VisGenerator"), new ShapedArcaneRecipe(defaultGroup, "VISGENERATOR", 25, (new AspectList())
/*  685 */           .add(Aspect.FIRE, 1).add(Aspect.ORDER, 1), new ItemStack(BlocksTC.visGenerator), new Object[] { "WSW", "EPE", "WRW", 
/*  686 */             Character.valueOf('R'), new ItemStack(ItemsTC.visResonator), 
/*  687 */             Character.valueOf('E'), new ItemStack(ItemsTC.nuggets, 1, 10), Character.valueOf('S'), "dustRedstone", 
/*  688 */             Character.valueOf('P'), new ItemStack(Blocks.field_150331_J), Character.valueOf('W'), "plankWood" }));
/*      */ 
/*      */ 
/*      */     
/*  692 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Condenser"), new ShapedArcaneRecipe(defaultGroup, "FLUXCLEANUP", 500, (new AspectList())
/*  693 */           .add(Aspect.AIR, 5).add(Aspect.WATER, 5).add(Aspect.ENTROPY, 5), new ItemStack(BlocksTC.condenser), new Object[] { "BCB", "WMW", "BTB", 
/*  694 */             Character.valueOf('T'), new ItemStack(BlocksTC.tube), Character.valueOf('C'), new ItemStack(ItemsTC.morphicResonator), 
/*  695 */             Character.valueOf('W'), "plankWood", Character.valueOf('M'), new ItemStack(ItemsTC.mechanismComplex), 
/*  696 */             Character.valueOf('B'), "plateBrass" }));
/*      */     
/*  698 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:CondenserLattice"), new ShapedArcaneRecipe(defaultGroup, "FLUXCLEANUP", 100, (new AspectList())
/*  699 */           .add(Aspect.EARTH, 3).add(Aspect.AIR, 3), new ItemStack(BlocksTC.condenserlattice), new Object[] { "QTQ", "QFQ", "QTQ", 
/*  700 */             Character.valueOf('T'), "plateThaumium", Character.valueOf('F'), new ItemStack(ItemsTC.filter), 
/*  701 */             Character.valueOf('Q'), "gemQuartz" }));
/*      */ 
/*      */     
/*  704 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:Stabilizer"), new ShapedArcaneRecipe(defaultGroup, "INFUSIONSTABLE", 250, (new AspectList())
/*  705 */           .add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ENTROPY, 1), new ItemStack(BlocksTC.stabilizer), new Object[] { "SRS", "BVB", "IMI", 
/*  706 */             Character.valueOf('R'), "blockRedstone", Character.valueOf('S'), BlocksTC.slabArcaneStone, 
/*  707 */             Character.valueOf('B'), BlocksTC.stoneArcane, Character.valueOf('M'), new ItemStack(ItemsTC.mechanismComplex), 
/*  708 */             Character.valueOf('V'), new ItemStack(ItemsTC.visResonator), Character.valueOf('I'), new ItemStack(BlocksTC.inlay) }));
/*      */ 
/*      */     
/*  711 */     ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumcraft:RedstoneInlay"), new ShapelessArcaneRecipe(defaultGroup, "INFUSIONSTABLE", 25, (new AspectList())
/*  712 */           .add(Aspect.WATER, 1), new ItemStack(BlocksTC.inlay, 2), new Object[] { "dustRedstone", "ingotGold" }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void initializeInfusionRecipes() {
/*  719 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:SealHarvest"), new InfusionRecipe("SEALHARVEST", 
/*  720 */           GolemHelper.getSealStack("thaumcraft:harvest"), 0, (new AspectList()).add(Aspect.PLANT, 10).add(Aspect.SENSES, 10).add(Aspect.MAN, 10), new ItemStack(ItemsTC.seals), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(Items.field_151080_bb), new ItemStack(Items.field_151081_bc), new ItemStack(Items.field_185163_cU), new ItemStack(Items.field_151120_aE), new ItemStack(Blocks.field_150434_aF) }));
/*      */ 
/*      */ 
/*      */     
/*  724 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:SealButcher"), new InfusionRecipe("SEALBUTCHER", 
/*  725 */           GolemHelper.getSealStack("thaumcraft:butcher"), 0, (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.SENSES, 10).add(Aspect.MAN, 10), 
/*  726 */           GolemHelper.getSealStack("thaumcraft:guard"), new Object[] { "leather", new ItemStack(Blocks.field_150325_L, 1, 32767), new ItemStack(Items.field_179555_bs), new ItemStack(Items.field_151147_al), new ItemStack(Items.field_179561_bm), new ItemStack(Items.field_151082_bd) }));
/*      */ 
/*      */     
/*  729 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:SealBreak"), new InfusionRecipe("SEALBREAK", 
/*  730 */           GolemHelper.getSealStack("thaumcraft:breaker"), 1, (new AspectList()).add(Aspect.TOOL, 10).add(Aspect.ENTROPY, 10).add(Aspect.MAN, 10), new ItemStack(ItemsTC.seals), new Object[] {
/*  731 */             Ingredient.func_193367_a(Items.field_151006_E), Ingredient.func_193367_a(Items.field_151005_D), Ingredient.func_193367_a(Items.field_151011_C)
/*      */           }));
/*      */     
/*  734 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CrystalClusterAir"), new InfusionRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalAir), 0, (new AspectList())
/*  735 */           .add(Aspect.AIR, 10).add(Aspect.CRYSTAL, 10).add(Aspect.TRAP, 5), 
/*  736 */           ThaumcraftApiHelper.makeCrystal(Aspect.AIR), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*  737 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CrystalClusterFire"), new InfusionRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalFire), 0, (new AspectList())
/*  738 */           .add(Aspect.FIRE, 10).add(Aspect.CRYSTAL, 10).add(Aspect.TRAP, 5), 
/*  739 */           ThaumcraftApiHelper.makeCrystal(Aspect.FIRE), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*  740 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CrystalClusterWater"), new InfusionRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalWater), 0, (new AspectList())
/*  741 */           .add(Aspect.WATER, 10).add(Aspect.CRYSTAL, 10).add(Aspect.TRAP, 5), 
/*  742 */           ThaumcraftApiHelper.makeCrystal(Aspect.WATER), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*  743 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CrystalClusterEarth"), new InfusionRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalEarth), 0, (new AspectList())
/*  744 */           .add(Aspect.EARTH, 10).add(Aspect.CRYSTAL, 10).add(Aspect.TRAP, 5), 
/*  745 */           ThaumcraftApiHelper.makeCrystal(Aspect.EARTH), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*  746 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CrystalClusterOrder"), new InfusionRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalOrder), 0, (new AspectList())
/*  747 */           .add(Aspect.ORDER, 10).add(Aspect.CRYSTAL, 10).add(Aspect.TRAP, 5), 
/*  748 */           ThaumcraftApiHelper.makeCrystal(Aspect.ORDER), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*  749 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CrystalClusterEntropy"), new InfusionRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalEntropy), 0, (new AspectList())
/*  750 */           .add(Aspect.ENTROPY, 10).add(Aspect.CRYSTAL, 10).add(Aspect.TRAP, 5), 
/*  751 */           ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*  752 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CrystalClusterFlux"), new InfusionRecipe("CRYSTALFARMER", new ItemStack(BlocksTC.crystalTaint), 4, (new AspectList())
/*  753 */           .add(Aspect.FLUX, 10).add(Aspect.CRYSTAL, 10).add(Aspect.TRAP, 5), 
/*  754 */           ThaumcraftApiHelper.makeCrystal(Aspect.FLUX), new Object[] { new ItemStack(Items.field_151014_N), new ItemStack(ItemsTC.salisMundus) }));
/*      */ 
/*      */     
/*  757 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:focus_2"), new InfusionRecipe("FOCUSADVANCED@1", new ItemStack(ItemsTC.focus2), 3, (new AspectList())
/*  758 */           .add(Aspect.MAGIC, 25).add(Aspect.ORDER, 50), new ItemStack(ItemsTC.focus1), new Object[] { new ItemStack(ItemsTC.quicksilver), "gemDiamond", new ItemStack(ItemsTC.quicksilver), new ItemStack(Items.field_151079_bi) }));
/*      */ 
/*      */ 
/*      */     
/*  762 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:focus_3"), new InfusionRecipe("FOCUSGREATER@1", new ItemStack(ItemsTC.focus3), 5, (new AspectList())
/*  763 */           .add(Aspect.MAGIC, 25).add(Aspect.ORDER, 50).add(Aspect.VOID, 100), new ItemStack(ItemsTC.focus2), new Object[] { new ItemStack(ItemsTC.quicksilver), 
/*  764 */             Ingredient.func_193367_a(ItemsTC.primordialPearl), new ItemStack(ItemsTC.quicksilver), new ItemStack(Items.field_151156_bN) }));
/*      */ 
/*      */     
/*  767 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:JarBrain"), new InfusionRecipe("JARBRAIN", new ItemStack(BlocksTC.jarBrain), 4, (new AspectList())
/*  768 */           .add(Aspect.MIND, 25).add(Aspect.SENSES, 25).add(Aspect.UNDEAD, 25), new ItemStack(BlocksTC.jarNormal), new Object[] { new ItemStack(ItemsTC.brain), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151131_as), new ItemStack(Items.field_151070_bp) }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  774 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VisAmulet"), new InfusionRecipe("VISAMULET", new ItemStack(ItemsTC.amuletVis, 1, 1), 6, (new AspectList())
/*  775 */           .add(Aspect.AURA, 50).add(Aspect.ENERGY, 100).add(Aspect.VOID, 50), new ItemStack(ItemsTC.baubles, 1, 0), new Object[] { new ItemStack(ItemsTC.visResonator), 
/*      */             
/*  777 */             ThaumcraftApiHelper.makeCrystal(Aspect.AIR), ThaumcraftApiHelper.makeCrystal(Aspect.FIRE), 
/*  778 */             ThaumcraftApiHelper.makeCrystal(Aspect.WATER), ThaumcraftApiHelper.makeCrystal(Aspect.EARTH), ThaumcraftApiHelper.makeCrystal(Aspect.ORDER) }));
/*      */ 
/*      */     
/*  781 */     ra = new InfusionRunicAugmentRecipe();
/*  782 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:RunicArmor"), ra);
/*      */     
/*  784 */     for (int a = 0; a < 3; a++) {
/*  785 */       ItemStack in = new ItemStack(ItemsTC.baubles, 1, 1);
/*  786 */       if (a > 0) in.func_77983_a("TC.RUNIC", new NBTTagByte((byte)a)); 
/*  787 */       ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:RunicArmorFake" + a), new InfusionRunicAugmentRecipe(in));
/*      */     } 
/*      */ 
/*      */     
/*  791 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:Mirror"), new InfusionRecipe("MIRROR", new ItemStack(BlocksTC.mirror), 1, (new AspectList())
/*  792 */           .add(Aspect.MOTION, 25).add(Aspect.DARKNESS, 25).add(Aspect.EXCHANGE, 25), new ItemStack(ItemsTC.mirroredGlass), new Object[] { "ingotGold", "ingotGold", "ingotGold", new ItemStack(Items.field_151079_bi) }));
/*      */ 
/*      */     
/*  795 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:MirrorHand"), new InfusionRecipe("MIRRORHAND", new ItemStack(ItemsTC.handMirror), 5, (new AspectList())
/*  796 */           .add(Aspect.TOOL, 50).add(Aspect.MOTION, 50), new ItemStack(BlocksTC.mirror), new Object[] { "stickWood", new ItemStack(Items.field_151111_aL), new ItemStack(Items.field_151148_bJ) }));
/*      */ 
/*      */     
/*  799 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:MirrorEssentia"), new InfusionRecipe("MIRRORESSENTIA", new ItemStack(BlocksTC.mirrorEssentia), 2, (new AspectList())
/*  800 */           .add(Aspect.MOTION, 25).add(Aspect.WATER, 25).add(Aspect.EXCHANGE, 25), new ItemStack(ItemsTC.mirroredGlass), new Object[] { "ingotIron", "ingotIron", "ingotIron", new ItemStack(Items.field_151079_bi) }));
/*      */ 
/*      */ 
/*      */     
/*  804 */     ItemStack isEA = new ItemStack(ItemsTC.elementalAxe);
/*  805 */     EnumInfusionEnchantment.addInfusionEnchantment(isEA, EnumInfusionEnchantment.COLLECTOR, 1);
/*  806 */     EnumInfusionEnchantment.addInfusionEnchantment(isEA, EnumInfusionEnchantment.BURROWING, 1);
/*  807 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ElementalAxe"), new InfusionRecipe("ELEMENTALTOOLS", isEA, 1, (new AspectList())
/*  808 */           .add(Aspect.WATER, 60).add(Aspect.PLANT, 30), new ItemStack(ItemsTC.thaumiumAxe, 1, 32767), new Object[] { ConfigItems.WATER_CRYSTAL, ConfigItems.WATER_CRYSTAL, new ItemStack(ItemsTC.nuggets, 1, 10), new ItemStack(BlocksTC.plankGreatwood) }));
/*      */ 
/*      */     
/*  811 */     ItemStack isEP = new ItemStack(ItemsTC.elementalPick);
/*  812 */     EnumInfusionEnchantment.addInfusionEnchantment(isEP, EnumInfusionEnchantment.REFINING, 1);
/*  813 */     EnumInfusionEnchantment.addInfusionEnchantment(isEP, EnumInfusionEnchantment.SOUNDING, 2);
/*  814 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ElementalPick"), new InfusionRecipe("ELEMENTALTOOLS", isEP, 1, (new AspectList())
/*  815 */           .add(Aspect.FIRE, 30).add(Aspect.METAL, 30).add(Aspect.SENSES, 30), new ItemStack(ItemsTC.thaumiumPick, 1, 32767), new Object[] { ConfigItems.FIRE_CRYSTAL, ConfigItems.FIRE_CRYSTAL, new ItemStack(ItemsTC.nuggets, 1, 10), new ItemStack(BlocksTC.plankGreatwood) }));
/*      */ 
/*      */     
/*  818 */     ItemStack isESW = new ItemStack(ItemsTC.elementalSword);
/*  819 */     EnumInfusionEnchantment.addInfusionEnchantment(isESW, EnumInfusionEnchantment.ARCING, 2);
/*  820 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ElementalSword"), new InfusionRecipe("ELEMENTALTOOLS", isESW, 1, (new AspectList())
/*  821 */           .add(Aspect.AIR, 30).add(Aspect.MOTION, 30).add(Aspect.AVERSION, 30), new ItemStack(ItemsTC.thaumiumSword, 1, 32767), new Object[] { ConfigItems.AIR_CRYSTAL, ConfigItems.AIR_CRYSTAL, new ItemStack(ItemsTC.nuggets, 1, 10), new ItemStack(BlocksTC.plankGreatwood) }));
/*      */ 
/*      */     
/*  824 */     ItemStack isES = new ItemStack(ItemsTC.elementalShovel);
/*  825 */     EnumInfusionEnchantment.addInfusionEnchantment(isES, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/*  826 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ElementalShovel"), new InfusionRecipe("ELEMENTALTOOLS", isES, 1, (new AspectList())
/*  827 */           .add(Aspect.EARTH, 60).add(Aspect.CRAFT, 30), new ItemStack(ItemsTC.thaumiumShovel, 1, 32767), new Object[] { ConfigItems.EARTH_CRYSTAL, ConfigItems.EARTH_CRYSTAL, new ItemStack(ItemsTC.nuggets, 1, 10), new ItemStack(BlocksTC.plankGreatwood) }));
/*      */ 
/*      */     
/*  830 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ElementalHoe"), new InfusionRecipe("ELEMENTALTOOLS", new ItemStack(ItemsTC.elementalHoe), 1, (new AspectList())
/*  831 */           .add(Aspect.ORDER, 30).add(Aspect.PLANT, 30).add(Aspect.ENTROPY, 30), new ItemStack(ItemsTC.thaumiumHoe, 1, 32767), new Object[] { ConfigItems.ORDER_CRYSTAL, ConfigItems.ENTROPY_CRYSTAL, new ItemStack(ItemsTC.nuggets, 1, 10), new ItemStack(BlocksTC.plankGreatwood) }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  836 */     InfusionEnchantmentRecipe IEBURROWING = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.BURROWING, (new AspectList()).add(Aspect.SENSES, 80).add(Aspect.EARTH, 150), new Object[] { new IngredientNBTTC(new ItemStack(Items.field_151134_bR)), new ItemStack(Items.field_179556_br) });
/*      */     
/*  838 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:IEBURROWING"), IEBURROWING);
/*  839 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:IEBURROWINGFAKE"), new InfusionEnchantmentRecipe(IEBURROWING, new ItemStack(Items.field_151039_o)));
/*      */ 
/*      */ 
/*      */     
/*  843 */     InfusionEnchantmentRecipe IECOLLECTOR = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.COLLECTOR, (new AspectList()).add(Aspect.DESIRE, 80).add(Aspect.WATER, 100), new Object[] { new IngredientNBTTC(new ItemStack(Items.field_151134_bR)), new ItemStack(Items.field_151058_ca) });
/*      */     
/*  845 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:IECOLLECTOR"), IECOLLECTOR);
/*  846 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:IECOLLECTORFAKE"), new InfusionEnchantmentRecipe(IECOLLECTOR, new ItemStack(Items.field_151049_t)));
/*      */ 
/*      */ 
/*      */     
/*  850 */     InfusionEnchantmentRecipe IEDESTRUCTIVE = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.DESTRUCTIVE, (new AspectList()).add(Aspect.AVERSION, 200).add(Aspect.ENTROPY, 250), new Object[] { new IngredientNBTTC(new ItemStack(Items.field_151134_bR)), new ItemStack(Blocks.field_150335_W) });
/*      */     
/*  852 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:IEDESTRUCTIVE"), IEDESTRUCTIVE);
/*  853 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:IEDESTRUCTIVEFAKE"), new InfusionEnchantmentRecipe(IEDESTRUCTIVE, new ItemStack(Items.field_151050_s)));
/*      */ 
/*      */ 
/*      */     
/*  857 */     InfusionEnchantmentRecipe IEREFINING = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.REFINING, (new AspectList()).add(Aspect.ORDER, 80).add(Aspect.EXCHANGE, 60), new Object[] { new IngredientNBTTC(new ItemStack(Items.field_151134_bR)), new ItemStack(ItemsTC.salisMundus) });
/*      */     
/*  859 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:IEREFINING"), IEREFINING);
/*  860 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:IEREFININGFAKE"), new InfusionEnchantmentRecipe(IEREFINING, new ItemStack(Items.field_151035_b)));
/*      */ 
/*      */ 
/*      */     
/*  864 */     InfusionEnchantmentRecipe IESOUNDING = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.SOUNDING, (new AspectList()).add(Aspect.SENSES, 40).add(Aspect.FIRE, 60), new Object[] { new IngredientNBTTC(new ItemStack(Items.field_151134_bR)), new ItemStack(Items.field_151148_bJ) });
/*      */     
/*  866 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:IESOUNDING"), IESOUNDING);
/*  867 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:IESOUNDINGFAKE"), new InfusionEnchantmentRecipe(IESOUNDING, new ItemStack(Items.field_151005_D)));
/*      */ 
/*      */ 
/*      */     
/*  871 */     InfusionEnchantmentRecipe IEARCING = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.ARCING, (new AspectList()).add(Aspect.ENERGY, 40).add(Aspect.AIR, 60), new Object[] { new IngredientNBTTC(new ItemStack(Items.field_151134_bR)), new ItemStack(Blocks.field_150451_bX) });
/*      */     
/*  873 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:IEARCING"), IEARCING);
/*  874 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:IEARCINGFAKE"), new InfusionEnchantmentRecipe(IEARCING, new ItemStack(Items.field_151041_m)));
/*      */ 
/*      */ 
/*      */     
/*  878 */     InfusionEnchantmentRecipe IEESSENCE = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.ESSENCE, (new AspectList()).add(Aspect.BEAST, 40).add(Aspect.FLUX, 60), new Object[] { new IngredientNBTTC(new ItemStack(Items.field_151134_bR)), new ItemStack(ItemsTC.crystalEssence) });
/*      */     
/*  880 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:IEESSENCE"), IEESSENCE);
/*  881 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:IEESSENCEFAKE"), new InfusionEnchantmentRecipe(IEESSENCE, new ItemStack(Items.field_151052_q)));
/*      */ 
/*      */ 
/*      */     
/*  885 */     InfusionEnchantmentRecipe IELAMPLIGHT = new InfusionEnchantmentRecipe(EnumInfusionEnchantment.LAMPLIGHT, (new AspectList()).add(Aspect.LIGHT, 80).add(Aspect.AIR, 20), new Object[] { new IngredientNBTTC(new ItemStack(Items.field_151134_bR)), "nitor" });
/*      */     
/*  887 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:IELAMPLIGHT"), IELAMPLIGHT);
/*  888 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:IELAMPLIGHTFAKE"), new InfusionEnchantmentRecipe(IELAMPLIGHT, new ItemStack(Items.field_151005_D)));
/*      */ 
/*      */ 
/*      */     
/*  892 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:BootsTraveller"), new InfusionRecipe("BOOTSTRAVELLER", new ItemStack(ItemsTC.travellerBoots), 1, (new AspectList())
/*      */           
/*  894 */           .add(Aspect.FLIGHT, 100).add(Aspect.MOTION, 100), new ItemStack(Items.field_151021_T, 1, 32767), new Object[] { ConfigItems.AIR_CRYSTAL, ConfigItems.AIR_CRYSTAL, new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.fabric), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151115_aP, 1, 32767) }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  899 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:MindBiothaumic"), new InfusionRecipe("MINDBIOTHAUMIC", new ItemStack(ItemsTC.mind, 1, 1), 4, (new AspectList())
/*  900 */           .add(Aspect.MIND, 50).add(Aspect.MECHANISM, 25), new ItemStack(ItemsTC.mind, 1, 0), new Object[] { new ItemStack(ItemsTC.brain), new ItemStack(ItemsTC.mechanismComplex) }));
/*      */ 
/*      */ 
/*      */     
/*  904 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ArcaneBore"), new InfusionRecipe("ARCANEBORE", new ItemStack(ItemsTC.turretPlacer, 1, 2), 4, (new AspectList())
/*      */           
/*  906 */           .add(Aspect.ENERGY, 25).add(Aspect.EARTH, 25).add(Aspect.MECHANISM, 100).add(Aspect.VOID, 25).add(Aspect.MOTION, 25), new ItemStack(ItemsTC.turretPlacer), new Object[] { new ItemStack(BlocksTC.plankGreatwood), new ItemStack(BlocksTC.plankGreatwood), new ItemStack(ItemsTC.mechanismComplex), "plateBrass", 
/*      */ 
/*      */             
/*  909 */             Ingredient.func_193367_a(Items.field_151046_w), Ingredient.func_193367_a(Items.field_151047_v), new ItemStack(ItemsTC.morphicResonator), new ItemStack(ItemsTC.nuggets, 1, 10) }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  937 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:LampGrowth"), new InfusionRecipe("LAMPGROWTH", new ItemStack(BlocksTC.lampGrowth), 4, (new AspectList())
/*  938 */           .add(Aspect.PLANT, 20).add(Aspect.LIGHT, 15).add(Aspect.LIFE, 15).add(Aspect.TOOL, 15), new ItemStack(BlocksTC.lampArcane), new Object[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151100_aR, 1, 15), ConfigItems.EARTH_CRYSTAL, new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151100_aR, 1, 15), ConfigItems.EARTH_CRYSTAL }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  943 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:LampFertility"), new InfusionRecipe("LAMPFERTILITY", new ItemStack(BlocksTC.lampFertility), 4, (new AspectList())
/*  944 */           .add(Aspect.BEAST, 20).add(Aspect.LIGHT, 15).add(Aspect.LIFE, 15).add(Aspect.DESIRE, 15), new ItemStack(BlocksTC.lampArcane), new Object[] { new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151015_O), ConfigItems.FIRE_CRYSTAL, new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151172_bF), ConfigItems.FIRE_CRYSTAL }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  949 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ThaumiumFortressHelm"), new InfusionRecipe("ARMORFORTRESS", new ItemStack(ItemsTC.fortressHelm), 3, (new AspectList())
/*  950 */           .add(Aspect.METAL, 50).add(Aspect.PROTECT, 20).add(Aspect.ENERGY, 25), new ItemStack(ItemsTC.thaumiumHelm, 1, 32767), new Object[] { "plateThaumium", "plateThaumium", new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151166_bC) }));
/*      */ 
/*      */     
/*  953 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ThaumiumFortressChest"), new InfusionRecipe("ARMORFORTRESS", new ItemStack(ItemsTC.fortressChest), 3, (new AspectList())
/*  954 */           .add(Aspect.METAL, 50).add(Aspect.PROTECT, 30).add(Aspect.ENERGY, 25), new ItemStack(ItemsTC.thaumiumChest, 1, 32767), new Object[] { "plateThaumium", "plateThaumium", "plateThaumium", "plateThaumium", new ItemStack(Items.field_151043_k), "leather" }));
/*      */ 
/*      */     
/*  957 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:ThaumiumFortressLegs"), new InfusionRecipe("ARMORFORTRESS", new ItemStack(ItemsTC.fortressLegs), 3, (new AspectList())
/*  958 */           .add(Aspect.METAL, 50).add(Aspect.PROTECT, 25).add(Aspect.ENERGY, 25), new ItemStack(ItemsTC.thaumiumLegs, 1, 32767), new Object[] { "plateThaumium", "plateThaumium", "plateThaumium", new ItemStack(Items.field_151043_k), "leather" }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  963 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VoidRobeHelm"), new InfusionRecipe("VOIDROBEARMOR", new ItemStack(ItemsTC.voidRobeHelm), 6, (new AspectList())
/*      */           
/*  965 */           .add(Aspect.METAL, 25).add(Aspect.SENSES, 25).add(Aspect.PROTECT, 25).add(Aspect.ENERGY, 25).add(Aspect.ELDRITCH, 25).add(Aspect.VOID, 25), new ItemStack(ItemsTC.voidHelm), new Object[] { new ItemStack(ItemsTC.goggles, 1, 32767), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.fabric) }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  970 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VoidRobeChest"), new InfusionRecipe("VOIDROBEARMOR", new ItemStack(ItemsTC.voidRobeChest), 6, (new AspectList())
/*      */           
/*  972 */           .add(Aspect.METAL, 35).add(Aspect.PROTECT, 35).add(Aspect.ENERGY, 25).add(Aspect.ELDRITCH, 25).add(Aspect.VOID, 35), new ItemStack(ItemsTC.voidChest), new Object[] { new ItemStack(ItemsTC.clothChest), "plateVoid", "plateVoid", new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.fabric), "leather" }));
/*      */ 
/*      */ 
/*      */     
/*  976 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VoidRobeLegs"), new InfusionRecipe("VOIDROBEARMOR", new ItemStack(ItemsTC.voidRobeLegs), 6, (new AspectList())
/*      */           
/*  978 */           .add(Aspect.METAL, 30).add(Aspect.PROTECT, 30).add(Aspect.ENERGY, 25).add(Aspect.ELDRITCH, 25).add(Aspect.VOID, 30), new ItemStack(ItemsTC.voidLegs), new Object[] { new ItemStack(ItemsTC.clothLegs), "plateVoid", "plateVoid", new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.fabric), "leather" }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  984 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:HelmGoggles"), new InfusionRecipe("FORTRESSMASK", new Object[] { "goggles", new NBTTagByte((byte)1) }, 5, (new AspectList())
/*      */           
/*  986 */           .add(Aspect.SENSES, 40).add(Aspect.AURA, 20).add(Aspect.PROTECT, 20), new ItemStack(ItemsTC.fortressHelm, 1, 32767), new Object[] { new ItemStack(Items.field_151123_aH), new ItemStack(ItemsTC.goggles, 1, 32767) }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  991 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:MaskGrinningDevil"), new InfusionRecipe("FORTRESSMASK", new Object[] { "mask", new NBTTagInt(0) }, 8, (new AspectList())
/*      */           
/*  993 */           .add(Aspect.MIND, 80).add(Aspect.LIFE, 80).add(Aspect.PROTECT, 20), new ItemStack(ItemsTC.fortressHelm, 1, 32767), new Object[] { new ItemStack(Items.field_151100_aR, 1, 0), "plateIron", "leather", new ItemStack(BlocksTC.shimmerleaf), new ItemStack(ItemsTC.brain), "plateIron" }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  999 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:MaskAngryGhost"), new InfusionRecipe("FORTRESSMASK", new Object[] { "mask", new NBTTagInt(1) }, 8, (new AspectList())
/*      */           
/* 1001 */           .add(Aspect.ENTROPY, 80).add(Aspect.DEATH, 80).add(Aspect.PROTECT, 20), new ItemStack(ItemsTC.fortressHelm, 1, 32767), new Object[] { new ItemStack(Items.field_151100_aR, 1, 15), "plateIron", "leather", new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151144_bL, 1, 1), "plateIron" }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1007 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:MaskSippingFiend"), new InfusionRecipe("FORTRESSMASK", new Object[] { "mask", new NBTTagInt(2) }, 8, (new AspectList())
/*      */           
/* 1009 */           .add(Aspect.UNDEAD, 80).add(Aspect.LIFE, 80).add(Aspect.PROTECT, 20), new ItemStack(ItemsTC.fortressHelm, 1, 32767), new Object[] { new ItemStack(Items.field_151100_aR, 1, 1), "plateIron", "leather", new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151117_aB), "plateIron" }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1019 */     ItemStack isPC = new ItemStack(ItemsTC.primalCrusher);
/* 1020 */     EnumInfusionEnchantment.addInfusionEnchantment(isPC, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/* 1021 */     EnumInfusionEnchantment.addInfusionEnchantment(isPC, EnumInfusionEnchantment.REFINING, 1);
/* 1022 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:PrimalCrusher"), new InfusionRecipe("PRIMALCRUSHER", isPC, 6, (new AspectList())
/* 1023 */           .add(Aspect.EARTH, 75).add(Aspect.TOOL, 75).add(Aspect.ENTROPY, 50).add(Aspect.VOID, 50).add(Aspect.AVERSION, 50).add(Aspect.ELDRITCH, 50).add(Aspect.DESIRE, 50), 
/* 1024 */           Ingredient.func_193367_a(ItemsTC.primordialPearl), new Object[] {
/* 1025 */             Ingredient.func_193367_a(ItemsTC.voidPick), Ingredient.func_193367_a(ItemsTC.voidShovel), 
/* 1026 */             Ingredient.func_193367_a(ItemsTC.elementalPick), Ingredient.func_193367_a(ItemsTC.elementalShovel)
/*      */           }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1034 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VerdantHeart"), new InfusionRecipe("VERDANTCHARMS", new ItemStack(ItemsTC.charmVerdant), 5, (new AspectList())
/* 1035 */           .add(Aspect.LIFE, 60).add(Aspect.ORDER, 30).add(Aspect.PLANT, 60), new ItemStack(ItemsTC.baubles, 1, 4), new Object[] { new ItemStack(ItemsTC.nuggets, 1, 10), 
/* 1036 */             ThaumcraftApiHelper.makeCrystal(Aspect.LIFE), new ItemStack(Items.field_151117_aB), 
/* 1037 */             ThaumcraftApiHelper.makeCrystal(Aspect.PLANT) }));
/*      */     
/* 1039 */     ItemStack pis1 = new ItemStack(Items.field_151068_bn);
/* 1040 */     pis1.func_77983_a("Potion", new NBTTagString("minecraft:strong_healing"));
/* 1041 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VerdantHeartLife"), new InfusionRecipe("VERDANTCHARMS", new Object[] { "type", new NBTTagByte((byte)1) }, 5, (new AspectList())
/* 1042 */           .add(Aspect.LIFE, 80).add(Aspect.MAN, 80), new ItemStack(ItemsTC.charmVerdant), new Object[] { new ItemStack(Items.field_151153_ao), 
/* 1043 */             ThaumcraftApiHelper.makeCrystal(Aspect.LIFE), pis1, 
/* 1044 */             ThaumcraftApiHelper.makeCrystal(Aspect.MAN) }));
/* 1045 */     ItemStack pis2 = new ItemStack(Items.field_151068_bn);
/* 1046 */     pis2.func_77983_a("Potion", new NBTTagString("minecraft:strong_regeneration"));
/* 1047 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VerdantHeartSustain"), new InfusionRecipe("VERDANTCHARMS", new Object[] { "type", new NBTTagByte((byte)2) }, 5, (new AspectList())
/* 1048 */           .add(Aspect.DESIRE, 80).add(Aspect.AIR, 80), new ItemStack(ItemsTC.charmVerdant), new Object[] { new ItemStack(ItemsTC.tripleMeatTreat), 
/* 1049 */             ThaumcraftApiHelper.makeCrystal(Aspect.DESIRE), pis2, 
/* 1050 */             ThaumcraftApiHelper.makeCrystal(Aspect.AIR) }));
/*      */ 
/*      */     
/* 1053 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CLOUDRING"), new InfusionRecipe("CLOUDRING", new ItemStack(ItemsTC.ringCloud), 1, (new AspectList())
/*      */           
/* 1055 */           .add(Aspect.AIR, 50), new ItemStack(ItemsTC.baubles, 1, 1), new Object[] { ConfigItems.AIR_CRYSTAL, new ItemStack(Items.field_151008_G) }));
/*      */ 
/*      */ 
/*      */     
/* 1059 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CuriosityBand"), new InfusionRecipe("CURIOSITYBAND", new ItemStack(ItemsTC.bandCuriosity), 5, (new AspectList())
/*      */           
/* 1061 */           .add(Aspect.MIND, 150).add(Aspect.VOID, 50).add(Aspect.TRAP, 100), new ItemStack(ItemsTC.baubles, 1, 6), new Object[] { new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151099_bA), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151099_bA), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151099_bA), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151099_bA) }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1066 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CHARMUNDYING"), new InfusionRecipe("CHARMUNDYING", new ItemStack(ItemsTC.charmUndying), 2, (new AspectList())
/* 1067 */           .add(Aspect.LIFE, 25), new ItemStack(Items.field_190929_cY), new Object[] { "plateBrass" }));
/*      */ 
/*      */ 
/*      */     
/* 1071 */     int a = 0;
/* 1072 */     ItemStack[] nitorStacks = new ItemStack[16];
/* 1073 */     for (EnumDyeColor d : EnumDyeColor.values()) {
/* 1074 */       nitorStacks[a] = new ItemStack((Block)BlocksTC.nitor.get(d));
/* 1075 */       a++;
/*      */     } 
/* 1077 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:CausalityCollapser"), new InfusionRecipe("RIFTCLOSER", new ItemStack(ItemsTC.causalityCollapser), 8, (new AspectList())
/* 1078 */           .add(Aspect.ELDRITCH, 50).add(Aspect.FLUX, 50), new ItemStack(Blocks.field_150335_W), new Object[] { new ItemStack(ItemsTC.morphicResonator), new ItemStack(Blocks.field_150451_bX), new ItemStack(ItemsTC.alumentum), 
/*      */             
/* 1080 */             Ingredient.func_193369_a(nitorStacks), new ItemStack(ItemsTC.visResonator), new ItemStack(Blocks.field_150451_bX), new ItemStack(ItemsTC.alumentum), 
/* 1081 */             Ingredient.func_193369_a(nitorStacks) }));
/*      */ 
/*      */     
/* 1084 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VoidSiphon"), new InfusionRecipe("VOIDSIPHON", new ItemStack(BlocksTC.voidSiphon), 7, (new AspectList())
/* 1085 */           .add(Aspect.ELDRITCH, 50).add(Aspect.ENTROPY, 50).add(Aspect.VOID, 100).add(Aspect.CRAFT, 50), new ItemStack(BlocksTC.metalBlockVoid), new Object[] { new ItemStack(BlocksTC.stoneArcane), new ItemStack(BlocksTC.stoneArcane), new ItemStack(ItemsTC.mechanismComplex), "plateBrass", "plateBrass", new ItemStack(Items.field_151156_bN) }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1091 */     ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumcraft:VoidseerPearl"), new InfusionRecipe("VOIDSEERPEARL", new ItemStack(ItemsTC.charmVoidseer), 8, (new AspectList())
/* 1092 */           .add(Aspect.MIND, 150).add(Aspect.VOID, 150).add(Aspect.MAGIC, 100), new ItemStack(ItemsTC.baubles, 1, 4), new Object[] { new ItemStack(ItemsTC.brain), new ItemStack(ItemsTC.voidSeed), new ItemStack(ItemsTC.brain), 
/*      */ 
/*      */             
/* 1095 */             Ingredient.func_193367_a(ItemsTC.primordialPearl) }));
/*      */   }
/*      */ 
/*      */   
/* 1099 */   static ResourceLocation defaultGroup = new ResourceLocation("");
/*      */ 
/*      */ 
/*      */   
/*      */   public static void initializeNormalRecipes(IForgeRegistry<IRecipe> iForgeRegistry) {
/* 1104 */     ResourceLocation brassGroup = new ResourceLocation("thaumcraft", "brass_stuff");
/* 1105 */     ResourceLocation thaumiumGroup = new ResourceLocation("thaumcraft", "thaumium_stuff");
/* 1106 */     ResourceLocation voidGroup = new ResourceLocation("thaumcraft", "void_stuff");
/* 1107 */     ResourceLocation baublesGroup = new ResourceLocation("thaumcraft", "baubles_stuff");
/*      */     
/* 1109 */     iForgeRegistry.register((new RecipesRobeArmorDyes()).setRegistryName("robedye"));
/* 1110 */     iForgeRegistry.register((new RecipesVoidRobeArmorDyes()).setRegistryName("voidarmordye"));
/*      */     
/* 1112 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "ironnuggetconvert"), defaultGroup, new ItemStack(Items.field_191525_da), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.nuggets, 1, 0) });
/* 1113 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "thaumiumtonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 6), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 0) });
/* 1114 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "voidtonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 7), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 1) });
/* 1115 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "brasstonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 8), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 2) });
/* 1116 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "quartztonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 9), new Object[] { "#", Character.valueOf('#'), new ItemStack(Items.field_151128_bU) });
/* 1117 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "quicksilvertonuggets"), defaultGroup, new ItemStack(ItemsTC.nuggets, 9, 5), new Object[] { "#", Character.valueOf('#'), new ItemStack(ItemsTC.quicksilver) });
/*      */ 
/*      */     
/* 1120 */     oreDictRecipe("nuggetstothaumium", defaultGroup, new ItemStack(ItemsTC.ingots, 1, 0), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetThaumium" });
/* 1121 */     oreDictRecipe("nuggetstovoid", defaultGroup, new ItemStack(ItemsTC.ingots, 1, 1), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetVoid" });
/* 1122 */     oreDictRecipe("nuggetstobrass", defaultGroup, new ItemStack(ItemsTC.ingots, 1, 2), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetBrass" });
/* 1123 */     oreDictRecipe("nuggetstoquicksilver", defaultGroup, new ItemStack(ItemsTC.quicksilver), new Object[] { "###", "###", "###", Character.valueOf('#'), "nuggetQuicksilver" });
/*      */     
/* 1125 */     oreDictRecipe("thaumiumingotstoblock", thaumiumGroup, new ItemStack(BlocksTC.metalBlockThaumium), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 0) });
/* 1126 */     oreDictRecipe("thaumiumblocktoingots", thaumiumGroup, new ItemStack(ItemsTC.ingots, 9, 0), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.metalBlockThaumium) });
/*      */     
/* 1128 */     oreDictRecipe("voidingotstoblock", voidGroup, new ItemStack(BlocksTC.metalBlockVoid), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 1) });
/* 1129 */     oreDictRecipe("voidblocktoingots", voidGroup, new ItemStack(ItemsTC.ingots, 9, 1), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.metalBlockVoid) });
/*      */     
/* 1131 */     oreDictRecipe("brassingotstoblock", brassGroup, new ItemStack(BlocksTC.metalBlockBrass), new Object[] { "###", "###", "###", Character.valueOf('#'), new ItemStack(ItemsTC.ingots, 1, 2) });
/* 1132 */     oreDictRecipe("brassblocktoingots", brassGroup, new ItemStack(ItemsTC.ingots, 9, 2), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.metalBlockBrass) });
/*      */     
/* 1134 */     oreDictRecipe("fleshtoblock", defaultGroup, new ItemStack(BlocksTC.fleshBlock), new Object[] { "###", "###", "###", Character.valueOf('#'), Items.field_151078_bh });
/* 1135 */     oreDictRecipe("blocktoflesh", defaultGroup, new ItemStack(Items.field_151078_bh, 9, 0), new Object[] { "#", Character.valueOf('#'), BlocksTC.fleshBlock });
/*      */     
/* 1137 */     oreDictRecipe("ambertoblock", defaultGroup, new ItemStack(BlocksTC.amberBlock), new Object[] { "##", "##", Character.valueOf('#'), "gemAmber" });
/* 1138 */     oreDictRecipe("amberblocktobrick", defaultGroup, new ItemStack(BlocksTC.amberBrick, 4), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(BlocksTC.amberBlock) });
/* 1139 */     oreDictRecipe("amberbricktoblock", defaultGroup, new ItemStack(BlocksTC.amberBlock, 4), new Object[] { "##", "##", Character.valueOf('#'), new ItemStack(BlocksTC.amberBrick) });
/* 1140 */     oreDictRecipe("amberblocktoamber", defaultGroup, new ItemStack(ItemsTC.amber, 4), new Object[] { "#", Character.valueOf('#'), new ItemStack(BlocksTC.amberBlock) });
/*      */ 
/*      */     
/* 1143 */     oreDictRecipe("ironplate", defaultGroup, new ItemStack(ItemsTC.plate, 3, 1), new Object[] { "BBB", Character.valueOf('B'), "ingotIron" });
/*      */     
/* 1145 */     oreDictRecipe("brassplate", brassGroup, new ItemStack(ItemsTC.plate, 3, 0), new Object[] { "BBB", Character.valueOf('B'), "ingotBrass" });
/*      */     
/* 1147 */     oreDictRecipe("thaumiumplate", thaumiumGroup, new ItemStack(ItemsTC.plate, 3, 2), new Object[] { "BBB", Character.valueOf('B'), "ingotThaumium" });
/* 1148 */     oreDictRecipe("thaumiumhelm", thaumiumGroup, new ItemStack(ItemsTC.thaumiumHelm, 1), new Object[] { "III", "I I", Character.valueOf('I'), "ingotThaumium" });
/* 1149 */     oreDictRecipe("thaumiumchest", thaumiumGroup, new ItemStack(ItemsTC.thaumiumChest, 1), new Object[] { "I I", "III", "III", Character.valueOf('I'), "ingotThaumium" });
/* 1150 */     oreDictRecipe("thaumiumlegs", thaumiumGroup, new ItemStack(ItemsTC.thaumiumLegs, 1), new Object[] { "III", "I I", "I I", Character.valueOf('I'), "ingotThaumium" });
/* 1151 */     oreDictRecipe("thaumiumboots", thaumiumGroup, new ItemStack(ItemsTC.thaumiumBoots, 1), new Object[] { "I I", "I I", Character.valueOf('I'), "ingotThaumium" });
/* 1152 */     oreDictRecipe("thaumiumshovel", thaumiumGroup, new ItemStack(ItemsTC.thaumiumShovel, 1), new Object[] { "I", "S", "S", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" });
/* 1153 */     oreDictRecipe("thaumiumpick", thaumiumGroup, new ItemStack(ItemsTC.thaumiumPick, 1), new Object[] { "III", " S ", " S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" });
/* 1154 */     oreDictRecipe("thaumiumaxe", thaumiumGroup, new ItemStack(ItemsTC.thaumiumAxe, 1), new Object[] { "II", "SI", "S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" });
/* 1155 */     oreDictRecipe("thaumiumhoe", thaumiumGroup, new ItemStack(ItemsTC.thaumiumHoe, 1), new Object[] { "II", "S ", "S ", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" });
/* 1156 */     oreDictRecipe("thaumiumsword", thaumiumGroup, new ItemStack(ItemsTC.thaumiumSword, 1), new Object[] { "I", "I", "S", Character.valueOf('I'), "ingotThaumium", Character.valueOf('S'), "stickWood" });
/*      */     
/* 1158 */     oreDictRecipe("voidplate", voidGroup, new ItemStack(ItemsTC.plate, 3, 3), new Object[] { "BBB", Character.valueOf('B'), "ingotVoid" });
/* 1159 */     oreDictRecipe("voidhelm", voidGroup, new ItemStack(ItemsTC.voidHelm, 1), new Object[] { "III", "I I", Character.valueOf('I'), "ingotVoid" });
/* 1160 */     oreDictRecipe("voidchest", voidGroup, new ItemStack(ItemsTC.voidChest, 1), new Object[] { "I I", "III", "III", Character.valueOf('I'), "ingotVoid" });
/* 1161 */     oreDictRecipe("voidlegs", voidGroup, new ItemStack(ItemsTC.voidLegs, 1), new Object[] { "III", "I I", "I I", Character.valueOf('I'), "ingotVoid" });
/* 1162 */     oreDictRecipe("voidboots", voidGroup, new ItemStack(ItemsTC.voidBoots, 1), new Object[] { "I I", "I I", Character.valueOf('I'), "ingotVoid" });
/* 1163 */     oreDictRecipe("voidshovel", voidGroup, new ItemStack(ItemsTC.voidShovel, 1), new Object[] { "I", "S", "S", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" });
/* 1164 */     oreDictRecipe("voidpick", voidGroup, new ItemStack(ItemsTC.voidPick, 1), new Object[] { "III", " S ", " S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" });
/* 1165 */     oreDictRecipe("voidaxe", voidGroup, new ItemStack(ItemsTC.voidAxe, 1), new Object[] { "II", "SI", "S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" });
/* 1166 */     oreDictRecipe("voidhoe", voidGroup, new ItemStack(ItemsTC.voidHoe, 1), new Object[] { "II", "S ", "S ", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" });
/* 1167 */     oreDictRecipe("voidsword", voidGroup, new ItemStack(ItemsTC.voidSword, 1), new Object[] { "I", "I", "S", Character.valueOf('I'), "ingotVoid", Character.valueOf('S'), "stickWood" });
/*      */ 
/*      */ 
/*      */     
/* 1171 */     oreDictRecipe("babuleamulet", baublesGroup, new ItemStack(ItemsTC.baubles, 1, 0), new Object[] { " S ", "S S", " I ", 
/* 1172 */           Character.valueOf('S'), "string", Character.valueOf('I'), "ingotBrass" });
/* 1173 */     oreDictRecipe("babulering", baublesGroup, new ItemStack(ItemsTC.baubles, 1, 1), new Object[] { "NNN", "N N", "NNN", Character.valueOf('N'), "nuggetBrass" });
/* 1174 */     oreDictRecipe("babulegirdle", baublesGroup, new ItemStack(ItemsTC.baubles, 1, 2), new Object[] { " L ", "L L", " I ", 
/* 1175 */           Character.valueOf('L'), "leather", Character.valueOf('I'), "ingotBrass" });
/* 1176 */     oreDictRecipe("babuleamuletfancy", baublesGroup, new ItemStack(ItemsTC.baubles, 1, 4), new Object[] { " S ", "SGS", " I ", Character.valueOf('S'), "string", 
/* 1177 */           Character.valueOf('G'), "gemDiamond", Character.valueOf('I'), "ingotGold" });
/* 1178 */     oreDictRecipe("babuleringfancy", baublesGroup, new ItemStack(ItemsTC.baubles, 1, 5), new Object[] { "NGN", "N N", "NNN", 
/* 1179 */           Character.valueOf('G'), "gemDiamond", Character.valueOf('N'), "nuggetGold" });
/* 1180 */     oreDictRecipe("babulegirdlefancy", baublesGroup, new ItemStack(ItemsTC.baubles, 1, 6), new Object[] { " L ", "LGL", " I ", Character.valueOf('L'), "leather", 
/* 1181 */           Character.valueOf('G'), "gemDiamond", Character.valueOf('I'), "ingotGold" });
/*      */ 
/*      */     
/* 1184 */     iForgeRegistry.register((new RecipeTripleMeatTreat()).setRegistryName("triplemeattreat"));
/* 1185 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:triplemeattreatfake"), new ShapelessOreRecipe(defaultGroup, new ItemStack(ItemsTC.tripleMeatTreat), new Object[] { "nuggetMeat", "nuggetMeat", "nuggetMeat", new ItemStack(Items.field_151102_aT) }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1190 */     iForgeRegistry.register((new RecipeMagicDust()).setRegistryName("salismundus"));
/* 1191 */     ThaumcraftApi.addFakeCraftingRecipe(new ResourceLocation("thaumcraft:salismundusfake"), new ShapelessOreRecipe(defaultGroup, new ItemStack(ItemsTC.salisMundus), new Object[] { Items.field_151145_ak, Items.field_151054_z, Items.field_151137_ax, new ItemStack(ItemsTC.crystalEssence, 1, 32767), new ItemStack(ItemsTC.crystalEssence, 1, 32767), new ItemStack(ItemsTC.crystalEssence, 1, 32767) }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1198 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "shimmerleaftoquicksilver"), defaultGroup, new ItemStack(ItemsTC.quicksilver), new Object[] { "#", 
/* 1199 */           Character.valueOf('#'), BlocksTC.shimmerleaf });
/* 1200 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "cinderpearltoblazepowder"), defaultGroup, new ItemStack(Items.field_151065_br), new Object[] { "#", 
/* 1201 */           Character.valueOf('#'), BlocksTC.cinderpearl });
/*      */ 
/*      */     
/* 1204 */     ResourceLocation labelsGroup = new ResourceLocation("thaumcraft", "jarlabels");
/* 1205 */     shapelessOreDictRecipe("JarLabel", labelsGroup, new ItemStack(ItemsTC.label, 4, 0), new Object[] { "dyeBlack", "slimeball", Items.field_151121_aF, Items.field_151121_aF, Items.field_151121_aF, Items.field_151121_aF });
/*      */     
/* 1207 */     int count = 0;
/* 1208 */     for (Aspect aspect : Aspect.aspects.values()) {
/* 1209 */       ItemStack output = new ItemStack(ItemsTC.label, 1, 1);
/* 1210 */       ((IEssentiaContainerItem)output.func_77973_b()).setAspects(output, (new AspectList()).add(aspect, 1));
/* 1211 */       shapelessOreDictRecipe("label_" + aspect.getTag(), labelsGroup, output, new Object[] { new ItemStack(ItemsTC.label), new IngredientNBTTC(
/* 1212 */               ItemPhial.makeFilledPhial(aspect)) });
/*      */     } 
/* 1214 */     shapelessOreDictRecipe("JarLabelNull", labelsGroup, new ItemStack(ItemsTC.label), new Object[] { new ItemStack(ItemsTC.label, 1, 1) });
/*      */ 
/*      */     
/* 1217 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "PlankGreatwood"), defaultGroup, new ItemStack(BlocksTC.plankGreatwood, 4), new Object[] { "W", Character.valueOf('W'), new ItemStack(BlocksTC.logGreatwood) });
/* 1218 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "PlankSilverwood"), defaultGroup, new ItemStack(BlocksTC.plankSilverwood, 4), new Object[] { "W", Character.valueOf('W'), new ItemStack(BlocksTC.logSilverwood) });
/*      */ 
/*      */     
/* 1221 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "StairsGreatwood"), defaultGroup, new ItemStack(BlocksTC.stairsGreatwood, 4, 0), new Object[] { "K  ", "KK ", "KKK", 
/* 1222 */           Character.valueOf('K'), new ItemStack(BlocksTC.plankGreatwood) });
/* 1223 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "StairsSilverwood"), defaultGroup, new ItemStack(BlocksTC.stairsSilverwood, 4, 0), new Object[] { "K  ", "KK ", "KKK", 
/* 1224 */           Character.valueOf('K'), new ItemStack(BlocksTC.plankSilverwood) });
/* 1225 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "StairsArcane"), defaultGroup, new ItemStack(BlocksTC.stairsArcane, 4, 0), new Object[] { "K  ", "KK ", "KKK", 
/* 1226 */           Character.valueOf('K'), new ItemStack(BlocksTC.stoneArcane) });
/* 1227 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "StairsArcaneBrick"), defaultGroup, new ItemStack(BlocksTC.stairsArcaneBrick, 4, 0), new Object[] { "K  ", "KK ", "KKK", 
/* 1228 */           Character.valueOf('K'), new ItemStack(BlocksTC.stoneArcaneBrick) });
/* 1229 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "StairsAncient"), defaultGroup, new ItemStack(BlocksTC.stairsAncient, 4, 0), new Object[] { "K  ", "KK ", "KKK", 
/* 1230 */           Character.valueOf('K'), new ItemStack(BlocksTC.stoneAncient) });
/*      */ 
/*      */     
/* 1233 */     oreDictRecipe("StoneArcane", defaultGroup, new ItemStack(BlocksTC.stoneArcane, 9), new Object[] { "KKK", "KCK", "KKK", 
/* 1234 */           Character.valueOf('K'), "stone", Character.valueOf('C'), new ItemStack(ItemsTC.crystalEssence) });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1241 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "BrickArcane"), defaultGroup, new ItemStack(BlocksTC.stoneArcaneBrick, 4), new Object[] { "KK", "KK", 
/* 1242 */           Character.valueOf('K'), new ItemStack(BlocksTC.stoneArcane) });
/*      */ 
/*      */     
/* 1245 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "SlabGreatwood"), defaultGroup, new ItemStack(BlocksTC.slabGreatwood, 6), new Object[] { "KKK", 
/* 1246 */           Character.valueOf('K'), new ItemStack(BlocksTC.plankGreatwood) });
/* 1247 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "SlabSilverwood"), defaultGroup, new ItemStack(BlocksTC.slabSilverwood, 6), new Object[] { "KKK", 
/* 1248 */           Character.valueOf('K'), new ItemStack(BlocksTC.plankSilverwood) });
/* 1249 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "SlabArcaneStone"), defaultGroup, new ItemStack(BlocksTC.slabArcaneStone, 6), new Object[] { "KKK", 
/* 1250 */           Character.valueOf('K'), new ItemStack(BlocksTC.stoneArcane) });
/* 1251 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "SlabArcaneBrick"), defaultGroup, new ItemStack(BlocksTC.slabArcaneBrick, 6), new Object[] { "KKK", 
/* 1252 */           Character.valueOf('K'), new ItemStack(BlocksTC.stoneArcaneBrick) });
/* 1253 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "SlabAncient"), defaultGroup, new ItemStack(BlocksTC.slabAncient, 6), new Object[] { "KKK", 
/* 1254 */           Character.valueOf('K'), new ItemStack(BlocksTC.stoneAncient) });
/* 1255 */     GameRegistry.addShapedRecipe(new ResourceLocation("thaumcraft", "SlabEldritch"), defaultGroup, new ItemStack(BlocksTC.slabEldritch, 6), new Object[] { "KKK", 
/* 1256 */           Character.valueOf('K'), new ItemStack(BlocksTC.stoneEldritchTile) });
/*      */ 
/*      */ 
/*      */     
/* 1260 */     oreDictRecipe("phial", defaultGroup, new ItemStack(ItemsTC.phial, 8, 0), new Object[] { " C ", "G G", " G ", 
/* 1261 */           Character.valueOf('G'), "blockGlass", Character.valueOf('C'), Items.field_151119_aD });
/*      */ 
/*      */     
/* 1264 */     oreDictRecipe("tablewood", defaultGroup, new ItemStack(BlocksTC.tableWood), new Object[] { "SSS", "W W", 
/* 1265 */           Character.valueOf('S'), "slabWood", Character.valueOf('W'), "plankWood" });
/* 1266 */     oreDictRecipe("tablestone", defaultGroup, new ItemStack(BlocksTC.tableStone), new Object[] { "SSS", "W W", 
/* 1267 */           Character.valueOf('S'), new ItemStack(Blocks.field_150333_U), Character.valueOf('W'), "stone" });
/*      */ 
/*      */     
/* 1270 */     ResourceLocation inkwellGroup = new ResourceLocation("thaumcraft", "inkwell");
/* 1271 */     shapelessOreDictRecipe("scribingtoolscraft1", inkwellGroup, new ItemStack(ItemsTC.scribingTools), new Object[] { new ItemStack(ItemsTC.phial, 1, 0), Items.field_151008_G, "dyeBlack" });
/*      */     
/* 1273 */     shapelessOreDictRecipe("scribingtoolscraft2", inkwellGroup, new ItemStack(ItemsTC.scribingTools), new Object[] { Items.field_151069_bo, Items.field_151008_G, "dyeBlack" });
/*      */     
/* 1275 */     shapelessOreDictRecipe("scribingtoolsrefill", inkwellGroup, new ItemStack(ItemsTC.scribingTools), new Object[] { new ItemStack(ItemsTC.scribingTools, 1, 32767), "dyeBlack" });
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1280 */     oreDictRecipe("GolemBell", defaultGroup, new ItemStack(ItemsTC.golemBell), new Object[] { " QQ", " QQ", "S  ", 
/* 1281 */           Character.valueOf('S'), "stickWood", Character.valueOf('Q'), "gemQuartz" });
/*      */ 
/*      */     
/* 1284 */     ResourceLocation candlesGroup = new ResourceLocation("thaumcraft", "tallowcandles");
/* 1285 */     oreDictRecipe("TallowCandle", candlesGroup, new ItemStack((Block)BlocksTC.candles.get(EnumDyeColor.WHITE), 3), new Object[] { " S ", " T ", " T ", 
/* 1286 */           Character.valueOf('S'), "string", Character.valueOf('T'), new ItemStack(ItemsTC.tallow) });
/*      */     
/* 1288 */     IRecipe[] trs = new IRecipe[16];
/* 1289 */     int a = 0;
/* 1290 */     for (EnumDyeColor d : EnumDyeColor.values()) {
/* 1291 */       trs[a] = shapelessOreDictRecipe("TallowCandle" + d.func_176762_d().toLowerCase(), candlesGroup, new ItemStack((Block)BlocksTC.candles
/* 1292 */             .get(d)), new Object[] { ConfigAspects.dyes[15 - a], 
/* 1293 */             ingredientsFromBlocks((Block[])BlocksTC.candles.values().toArray(new Block[0])) });
/* 1294 */       a++;
/*      */     } 
/*      */     
/* 1297 */     oreDictRecipe("BrassBrace", defaultGroup, new ItemStack(ItemsTC.jarBrace, 2), new Object[] { "NSN", "S S", "NSN", 
/* 1298 */           Character.valueOf('N'), "nuggetBrass", Character.valueOf('S'), "stickWood" });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Ingredient ingredientsFromBlocks(Block... blocks) {
/* 1305 */     ItemStack[] aitemstack = new ItemStack[blocks.length];
/* 1306 */     for (int i = 0; i < blocks.length; i++)
/*      */     {
/* 1308 */       aitemstack[i] = new ItemStack(blocks[i]);
/*      */     }
/* 1310 */     return Ingredient.func_193369_a(aitemstack);
/*      */   }
/*      */   
/*      */   public static void initializeSmelting() {
/* 1314 */     GameRegistry.addSmelting(BlocksTC.oreCinnabar, new ItemStack(ItemsTC.quicksilver), 1.0F);
/* 1315 */     GameRegistry.addSmelting(BlocksTC.oreAmber, new ItemStack(ItemsTC.amber), 1.0F);
/* 1316 */     GameRegistry.addSmelting(BlocksTC.oreQuartz, new ItemStack(Items.field_151128_bU), 1.0F);
/* 1317 */     GameRegistry.addSmelting(BlocksTC.logGreatwood, new ItemStack(Items.field_151044_h, 1, 1), 0.5F);
/* 1318 */     GameRegistry.addSmelting(BlocksTC.logSilverwood, new ItemStack(Items.field_151044_h, 1, 1), 0.5F);
/* 1319 */     GameRegistry.addSmelting(new ItemStack(ItemsTC.clusters, 1, 0), new ItemStack(Items.field_151042_j, 2, 0), 1.0F);
/* 1320 */     GameRegistry.addSmelting(new ItemStack(ItemsTC.clusters, 1, 1), new ItemStack(Items.field_151043_k, 2, 0), 1.0F);
/* 1321 */     GameRegistry.addSmelting(new ItemStack(ItemsTC.clusters, 1, 6), new ItemStack(ItemsTC.quicksilver, 2, 0), 1.0F);
/* 1322 */     GameRegistry.addSmelting(new ItemStack(ItemsTC.clusters, 1, 7), new ItemStack(Items.field_151128_bU, 2, 0), 1.0F);
/*      */     
/* 1324 */     ThaumcraftApi.addSmeltingBonus("oreGold", new ItemStack(Items.field_151074_bl));
/* 1325 */     ThaumcraftApi.addSmeltingBonus("oreIron", new ItemStack(Items.field_191525_da));
/* 1326 */     ThaumcraftApi.addSmeltingBonus("oreCinnabar", new ItemStack(ItemsTC.nuggets, 1, 5));
/* 1327 */     ThaumcraftApi.addSmeltingBonus("oreCopper", new ItemStack(ItemsTC.nuggets, 1, 1));
/* 1328 */     ThaumcraftApi.addSmeltingBonus("oreTin", new ItemStack(ItemsTC.nuggets, 1, 2));
/* 1329 */     ThaumcraftApi.addSmeltingBonus("oreSilver", new ItemStack(ItemsTC.nuggets, 1, 3));
/* 1330 */     ThaumcraftApi.addSmeltingBonus("oreLead", new ItemStack(ItemsTC.nuggets, 1, 4));
/* 1331 */     ThaumcraftApi.addSmeltingBonus("oreQuartz", new ItemStack(ItemsTC.nuggets, 1, 9));
/*      */     
/* 1333 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 0), new ItemStack(Items.field_191525_da));
/* 1334 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 1), new ItemStack(Items.field_151074_bl));
/* 1335 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 6), new ItemStack(ItemsTC.nuggets, 1, 5));
/* 1336 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 2), new ItemStack(ItemsTC.nuggets, 1, 1));
/* 1337 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 3), new ItemStack(ItemsTC.nuggets, 1, 2));
/* 1338 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 4), new ItemStack(ItemsTC.nuggets, 1, 3));
/* 1339 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 5), new ItemStack(ItemsTC.nuggets, 1, 4));
/* 1340 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 7), new ItemStack(ItemsTC.nuggets, 1, 9));
/*      */     
/* 1342 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151082_bd), new ItemStack(ItemsTC.chunks, 1, 0));
/* 1343 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151076_bf), new ItemStack(ItemsTC.chunks, 1, 1));
/* 1344 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151147_al), new ItemStack(ItemsTC.chunks, 1, 2));
/* 1345 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_151115_aP, 1, 32767), new ItemStack(ItemsTC.chunks, 1, 3));
/* 1346 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_179558_bo), new ItemStack(ItemsTC.chunks, 1, 4));
/* 1347 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(Items.field_179561_bm), new ItemStack(ItemsTC.chunks, 1, 5));
/*      */ 
/*      */     
/* 1350 */     ThaumcraftApi.addSmeltingBonus("oreDiamond", new ItemStack(ItemsTC.nuggets, 1, 10), 0.025F);
/* 1351 */     ThaumcraftApi.addSmeltingBonus("oreRedstone", new ItemStack(ItemsTC.nuggets, 1, 10), 0.01F);
/* 1352 */     ThaumcraftApi.addSmeltingBonus("oreLapis", new ItemStack(ItemsTC.nuggets, 1, 10), 0.01F);
/* 1353 */     ThaumcraftApi.addSmeltingBonus("oreEmerald", new ItemStack(ItemsTC.nuggets, 1, 10), 0.025F);
/* 1354 */     ThaumcraftApi.addSmeltingBonus("oreGold", new ItemStack(ItemsTC.nuggets, 1, 10), 0.02F);
/* 1355 */     ThaumcraftApi.addSmeltingBonus("oreIron", new ItemStack(ItemsTC.nuggets, 1, 10), 0.01F);
/* 1356 */     ThaumcraftApi.addSmeltingBonus("oreCinnabar", new ItemStack(ItemsTC.nuggets, 1, 10), 0.025F);
/* 1357 */     ThaumcraftApi.addSmeltingBonus("oreCopper", new ItemStack(ItemsTC.nuggets, 1, 10), 0.01F);
/* 1358 */     ThaumcraftApi.addSmeltingBonus("oreTin", new ItemStack(ItemsTC.nuggets, 1, 10), 0.01F);
/* 1359 */     ThaumcraftApi.addSmeltingBonus("oreSilver", new ItemStack(ItemsTC.nuggets, 1, 10), 0.02F);
/* 1360 */     ThaumcraftApi.addSmeltingBonus("oreLead", new ItemStack(ItemsTC.nuggets, 1, 10), 0.01F);
/* 1361 */     ThaumcraftApi.addSmeltingBonus("oreQuartz", new ItemStack(ItemsTC.nuggets, 1, 10), 0.01F);
/* 1362 */     ThaumcraftApi.addSmeltingBonus(new ItemStack(ItemsTC.clusters, 1, 32767), new ItemStack(ItemsTC.nuggets, 1, 10), 0.02F);
/*      */   }
/*      */   
/*      */   static IRecipe oreDictRecipe(String name, ResourceLocation optionalGroup, ItemStack res, Object[] params) {
/* 1366 */     ShapedOreRecipe shapedOreRecipe = new ShapedOreRecipe(optionalGroup, res, params);
/* 1367 */     shapedOreRecipe.setRegistryName(new ResourceLocation("thaumcraft", name));
/* 1368 */     GameData.register_impl(shapedOreRecipe);
/* 1369 */     return shapedOreRecipe;
/*      */   }
/*      */   
/*      */   static IRecipe shapelessOreDictRecipe(String name, ResourceLocation optionalGroup, ItemStack res, Object[] params) {
/* 1373 */     ShapelessOreRecipe shapelessOreRecipe = new ShapelessOreRecipe(optionalGroup, res, params);
/* 1374 */     shapelessOreRecipe.setRegistryName(new ResourceLocation("thaumcraft", name));
/* 1375 */     GameData.register_impl(shapelessOreRecipe);
/* 1376 */     return shapelessOreRecipe;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void postAspects() {
/* 1388 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_gunpowder"), new CrucibleRecipe("HEDGEALCHEMY@2", new ItemStack(Items.field_151016_H, 2, 0), new ItemStack(Items.field_151016_H), new AspectList(new ItemStack(Items.field_151016_H))));
/*      */ 
/*      */     
/* 1391 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_slime"), new CrucibleRecipe("HEDGEALCHEMY@2", new ItemStack(Items.field_151123_aH, 2, 0), new ItemStack(Items.field_151123_aH), new AspectList(new ItemStack(Items.field_151123_aH))));
/*      */ 
/*      */     
/* 1394 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_glowstone"), new CrucibleRecipe("HEDGEALCHEMY@2", new ItemStack(Items.field_151114_aO, 2, 0), "dustGlowstone", new AspectList(new ItemStack(Items.field_151114_aO))));
/*      */ 
/*      */     
/* 1397 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_dye"), new CrucibleRecipe("HEDGEALCHEMY@2", new ItemStack(Items.field_151100_aR, 2, 0), new ItemStack(Items.field_151100_aR, 1, 0), new AspectList(new ItemStack(Items.field_151100_aR))));
/*      */ 
/*      */     
/* 1400 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_clay"), new CrucibleRecipe("HEDGEALCHEMY@3", new ItemStack(Items.field_151119_aD, 1, 0), new ItemStack(Blocks.field_150346_d), (new AspectList(new ItemStack(Items.field_151119_aD, 1, 0)))
/* 1401 */           .remove(new AspectList(new ItemStack(Blocks.field_150346_d)))));
/*      */     
/* 1403 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_string"), new CrucibleRecipe("HEDGEALCHEMY@3", new ItemStack(Items.field_151007_F), new ItemStack(Items.field_151015_O), (new AspectList(new ItemStack(Items.field_151007_F)))
/* 1404 */           .remove(new AspectList(new ItemStack(Items.field_151015_O)))));
/*      */     
/* 1406 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_web"), new CrucibleRecipe("HEDGEALCHEMY@3", new ItemStack(Blocks.field_150321_G), new ItemStack(Items.field_151007_F), (new AspectList(new ItemStack(Blocks.field_150321_G)))
/* 1407 */           .remove(new AspectList(new ItemStack(Items.field_151007_F)))));
/*      */     
/* 1409 */     ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumcraft:hedge_lava"), new CrucibleRecipe("HEDGEALCHEMY@3", new ItemStack(Items.field_151129_at), new ItemStack(Items.field_151133_ar), (new AspectList())
/* 1410 */           .add(Aspect.FIRE, 15).add(Aspect.EARTH, 5)));
/*      */   }
/*      */ 
/*      */   
/* 1414 */   public static HashMap<String, ArrayList<ResourceLocation>> recipeGroups = new HashMap();
/*      */   
/*      */   public static void compileGroups() {
/* 1417 */     for (ResourceLocation reg : CraftingManager.field_193380_a.func_148742_b()) {
/* 1418 */       IRecipe recipe = CraftingManager.func_193373_a(reg);
/* 1419 */       if (recipe != null) {
/* 1420 */         String group = recipe.func_193358_e();
/* 1421 */         if (group.trim().isEmpty() || group.startsWith("minecraft"))
/* 1422 */           continue;  if (!recipeGroups.containsKey(group)) recipeGroups.put(group, new ArrayList()); 
/* 1423 */         ArrayList list = (ArrayList)recipeGroups.get(group);
/* 1424 */         list.add(reg);
/*      */       } 
/*      */     } 
/* 1427 */     for (ResourceLocation reg : CommonInternals.craftingRecipeCatalog.keySet()) {
/* 1428 */       IThaumcraftRecipe recipe = (IThaumcraftRecipe)CommonInternals.craftingRecipeCatalog.get(reg);
/* 1429 */       if (recipe != null) {
/* 1430 */         String group = recipe.getGroup();
/* 1431 */         if (group == null || group.trim().isEmpty())
/* 1432 */           continue;  if (!recipeGroups.containsKey(group)) recipeGroups.put(group, new ArrayList()); 
/* 1433 */         ArrayList list = (ArrayList)recipeGroups.get(group);
/* 1434 */         list.add(reg);
/*      */       } 
/*      */     } 
/* 1437 */     for (ResourceLocation reg : CommonInternals.craftingRecipeCatalogFake.keySet()) {
/* 1438 */       Object recipe = CommonInternals.craftingRecipeCatalogFake.get(reg);
/* 1439 */       if (recipe != null) {
/* 1440 */         String group = "";
/* 1441 */         if (recipe instanceof IRecipe) {
/* 1442 */           recipe = ((IRecipe)recipe).func_193358_e();
/*      */         }
/* 1444 */         else if (recipe instanceof IThaumcraftRecipe) {
/* 1445 */           recipe = ((IThaumcraftRecipe)recipe).getGroup();
/*      */         } 
/* 1447 */         if (group == null || group.trim().isEmpty())
/* 1448 */           continue;  if (!recipeGroups.containsKey(group)) recipeGroups.put(group, new ArrayList()); 
/* 1449 */         ArrayList list = (ArrayList)recipeGroups.get(group);
/* 1450 */         list.add(reg);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\config\ConfigRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */