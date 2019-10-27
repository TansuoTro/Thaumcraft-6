/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.block.model.ModelBakery;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraftforge.client.model.ModelLoader;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ import thaumcraft.api.OreDictionaryEntries;
/*     */ import thaumcraft.api.ThaumcraftApiHelper;
/*     */ import thaumcraft.api.ThaumcraftMaterials;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.casters.FocusEngine;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.entities.construct.ItemTurretPlacer;
/*     */ import thaumcraft.common.golems.ItemGolemBell;
/*     */ import thaumcraft.common.golems.ItemGolemPlacer;
/*     */ import thaumcraft.common.golems.seals.ItemSealPlacer;
/*     */ import thaumcraft.common.golems.seals.SealBreaker;
/*     */ import thaumcraft.common.golems.seals.SealBreakerAdvanced;
/*     */ import thaumcraft.common.golems.seals.SealButcher;
/*     */ import thaumcraft.common.golems.seals.SealEmpty;
/*     */ import thaumcraft.common.golems.seals.SealEmptyAdvanced;
/*     */ import thaumcraft.common.golems.seals.SealFill;
/*     */ import thaumcraft.common.golems.seals.SealFillAdvanced;
/*     */ import thaumcraft.common.golems.seals.SealGuard;
/*     */ import thaumcraft.common.golems.seals.SealGuardAdvanced;
/*     */ import thaumcraft.common.golems.seals.SealHandler;
/*     */ import thaumcraft.common.golems.seals.SealHarvest;
/*     */ import thaumcraft.common.golems.seals.SealLumber;
/*     */ import thaumcraft.common.golems.seals.SealPickup;
/*     */ import thaumcraft.common.golems.seals.SealPickupAdvanced;
/*     */ import thaumcraft.common.golems.seals.SealProvide;
/*     */ import thaumcraft.common.golems.seals.SealStock;
/*     */ import thaumcraft.common.golems.seals.SealUse;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.items.armor.ItemBootsTraveller;
/*     */ import thaumcraft.common.items.armor.ItemCultistBoots;
/*     */ import thaumcraft.common.items.armor.ItemCultistLeaderArmor;
/*     */ import thaumcraft.common.items.armor.ItemCultistPlateArmor;
/*     */ import thaumcraft.common.items.armor.ItemCultistRobeArmor;
/*     */ import thaumcraft.common.items.armor.ItemFortressArmor;
/*     */ import thaumcraft.common.items.armor.ItemGoggles;
/*     */ import thaumcraft.common.items.armor.ItemRobeArmor;
/*     */ import thaumcraft.common.items.armor.ItemThaumiumArmor;
/*     */ import thaumcraft.common.items.armor.ItemVoidArmor;
/*     */ import thaumcraft.common.items.armor.ItemVoidRobeArmor;
/*     */ import thaumcraft.common.items.baubles.ItemAmuletVis;
/*     */ import thaumcraft.common.items.baubles.ItemBaubles;
/*     */ import thaumcraft.common.items.baubles.ItemCharmUndying;
/*     */ import thaumcraft.common.items.baubles.ItemCloudRing;
/*     */ import thaumcraft.common.items.baubles.ItemCuriosityBand;
/*     */ import thaumcraft.common.items.baubles.ItemVerdantCharm;
/*     */ import thaumcraft.common.items.baubles.ItemVoidseerCharm;
/*     */ import thaumcraft.common.items.casters.ItemCaster;
/*     */ import thaumcraft.common.items.casters.ItemFocus;
/*     */ import thaumcraft.common.items.casters.ItemFocusPouch;
/*     */ import thaumcraft.common.items.consumables.ItemAlumentum;
/*     */ import thaumcraft.common.items.consumables.ItemBathSalts;
/*     */ import thaumcraft.common.items.consumables.ItemBottleTaint;
/*     */ import thaumcraft.common.items.consumables.ItemCausalityCollapser;
/*     */ import thaumcraft.common.items.consumables.ItemChunksEdible;
/*     */ import thaumcraft.common.items.consumables.ItemLabel;
/*     */ import thaumcraft.common.items.consumables.ItemPhial;
/*     */ import thaumcraft.common.items.consumables.ItemSanitySoap;
/*     */ import thaumcraft.common.items.consumables.ItemTripleMeatTreat;
/*     */ import thaumcraft.common.items.consumables.ItemZombieBrain;
/*     */ import thaumcraft.common.items.curios.ItemCelestialNotes;
/*     */ import thaumcraft.common.items.curios.ItemCurio;
/*     */ import thaumcraft.common.items.curios.ItemEnchantmentPlaceholder;
/*     */ import thaumcraft.common.items.curios.ItemLootBag;
/*     */ import thaumcraft.common.items.curios.ItemPechWand;
/*     */ import thaumcraft.common.items.curios.ItemPrimordialPearl;
/*     */ import thaumcraft.common.items.curios.ItemThaumonomicon;
/*     */ import thaumcraft.common.items.misc.ItemCreativeFluxSponge;
/*     */ import thaumcraft.common.items.resources.ItemCrystalEssence;
/*     */ import thaumcraft.common.items.resources.ItemMagicDust;
/*     */ import thaumcraft.common.items.tools.ItemCrimsonBlade;
/*     */ import thaumcraft.common.items.tools.ItemElementalAxe;
/*     */ import thaumcraft.common.items.tools.ItemElementalHoe;
/*     */ import thaumcraft.common.items.tools.ItemElementalPickaxe;
/*     */ import thaumcraft.common.items.tools.ItemElementalShovel;
/*     */ import thaumcraft.common.items.tools.ItemElementalSword;
/*     */ import thaumcraft.common.items.tools.ItemGrappleGun;
/*     */ import thaumcraft.common.items.tools.ItemHandMirror;
/*     */ import thaumcraft.common.items.tools.ItemPrimalCrusher;
/*     */ import thaumcraft.common.items.tools.ItemResonator;
/*     */ import thaumcraft.common.items.tools.ItemSanityChecker;
/*     */ import thaumcraft.common.items.tools.ItemScribingTools;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumAxe;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumHoe;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumPickaxe;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumShovel;
/*     */ import thaumcraft.common.items.tools.ItemThaumiumSword;
/*     */ import thaumcraft.common.items.tools.ItemThaumometer;
/*     */ import thaumcraft.common.items.tools.ItemVoidAxe;
/*     */ import thaumcraft.common.items.tools.ItemVoidHoe;
/*     */ import thaumcraft.common.items.tools.ItemVoidPickaxe;
/*     */ import thaumcraft.common.items.tools.ItemVoidShovel;
/*     */ import thaumcraft.common.items.tools.ItemVoidSword;
/*     */ import thaumcraft.common.lib.CreativeTabThaumcraft;
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
/*     */ public class ConfigItems
/*     */ {
/* 141 */   public static ItemStack startBook = new ItemStack(Items.field_151164_bB);
/*     */ 
/*     */   
/*     */   public static void initMisc() {
/* 145 */     OreDictionaryEntries.initializeOreDictionary();
/*     */     
/* 147 */     AIR_CRYSTAL = ThaumcraftApiHelper.makeCrystal(Aspect.AIR);
/* 148 */     FIRE_CRYSTAL = ThaumcraftApiHelper.makeCrystal(Aspect.FIRE);
/* 149 */     WATER_CRYSTAL = ThaumcraftApiHelper.makeCrystal(Aspect.WATER);
/* 150 */     EARTH_CRYSTAL = ThaumcraftApiHelper.makeCrystal(Aspect.EARTH);
/* 151 */     ORDER_CRYSTAL = ThaumcraftApiHelper.makeCrystal(Aspect.ORDER);
/* 152 */     ENTROPY_CRYSTAL = ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY);
/* 153 */     FLUX_CRYSTAL = ThaumcraftApiHelper.makeCrystal(Aspect.FLUX);
/*     */ 
/*     */     
/* 156 */     contents = new NBTTagCompound();
/* 157 */     contents.func_74768_a("generation", 3);
/* 158 */     contents.func_74778_a("title", I18n.func_74838_a("book.start.title"));
/* 159 */     NBTTagList pages = new NBTTagList();
/* 160 */     pages.func_74742_a(new NBTTagString(I18n.func_74838_a("book.start.1")));
/* 161 */     pages.func_74742_a(new NBTTagString(I18n.func_74838_a("book.start.2")));
/* 162 */     pages.func_74742_a(new NBTTagString(I18n.func_74838_a("book.start.3")));
/* 163 */     contents.func_74782_a("pages", pages);
/* 164 */     startBook.func_77982_d(contents);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initItems(IForgeRegistry<Item> iForgeRegistry) {
/* 170 */     iForgeRegistry.register(ItemsTC.thaumonomicon = new ItemThaumonomicon());
/* 171 */     iForgeRegistry.register(ItemsTC.curio = new ItemCurio());
/* 172 */     iForgeRegistry.register(ItemsTC.lootBag = new ItemLootBag());
/* 173 */     iForgeRegistry.register(ItemsTC.primordialPearl = new ItemPrimordialPearl());
/* 174 */     iForgeRegistry.register(ItemsTC.pechWand = new ItemPechWand());
/* 175 */     iForgeRegistry.register(ItemsTC.celestialNotes = new ItemCelestialNotes());
/*     */ 
/*     */     
/* 178 */     iForgeRegistry.register(ItemsTC.amber = new ItemTCBase("amber", new String[0]));
/* 179 */     iForgeRegistry.register(ItemsTC.quicksilver = new ItemTCBase("quicksilver", new String[0]));
/* 180 */     iForgeRegistry.register(ItemsTC.ingots = new ItemTCBase("ingot", new String[] { "thaumium", "void", "brass" }));
/* 181 */     iForgeRegistry.register(ItemsTC.nuggets = new ItemTCBase("nugget", new String[] { "iron", "copper", "tin", "silver", "lead", "quicksilver", "thaumium", "void", "brass", "quartz", "rareearth" }));
/* 182 */     iForgeRegistry.register(ItemsTC.clusters = new ItemTCBase("cluster", new String[] { "iron", "gold", "copper", "tin", "silver", "lead", "cinnabar", "quartz" }));
/* 183 */     iForgeRegistry.register(ItemsTC.fabric = new ItemTCBase("fabric", new String[0]));
/* 184 */     iForgeRegistry.register(ItemsTC.visResonator = new ItemTCBase("vis_resonator", new String[0]));
/* 185 */     iForgeRegistry.register(ItemsTC.tallow = new ItemTCBase("tallow", new String[0]));
/* 186 */     iForgeRegistry.register(ItemsTC.mechanismSimple = new ItemTCBase("mechanism_simple", new String[0]));
/* 187 */     iForgeRegistry.register(ItemsTC.mechanismComplex = new ItemTCBase("mechanism_complex", new String[0]));
/* 188 */     iForgeRegistry.register(ItemsTC.plate = new ItemTCBase("plate", new String[] { "brass", "iron", "thaumium", "void" }));
/* 189 */     iForgeRegistry.register(ItemsTC.filter = new ItemTCBase("filter", new String[0]));
/* 190 */     iForgeRegistry.register(ItemsTC.morphicResonator = new ItemTCBase("morphic_resonator", new String[0]));
/* 191 */     iForgeRegistry.register(ItemsTC.salisMundus = new ItemMagicDust());
/* 192 */     iForgeRegistry.register(ItemsTC.mirroredGlass = new ItemTCBase("mirrored_glass", new String[0]));
/* 193 */     iForgeRegistry.register(ItemsTC.voidSeed = new ItemTCBase("void_seed", new String[0]));
/* 194 */     iForgeRegistry.register(ItemsTC.mind = new ItemTCBase("mind", new String[] { "clockwork", "biothaumic" }));
/* 195 */     iForgeRegistry.register(ItemsTC.modules = new ItemTCBase("module", new String[] { "vision", "aggression" }));
/* 196 */     iForgeRegistry.register(ItemsTC.crystalEssence = new ItemCrystalEssence());
/*     */ 
/*     */ 
/*     */     
/* 200 */     iForgeRegistry.register(ItemsTC.chunks = new ItemChunksEdible());
/* 201 */     iForgeRegistry.register(ItemsTC.tripleMeatTreat = new ItemTripleMeatTreat());
/* 202 */     iForgeRegistry.register(ItemsTC.brain = new ItemZombieBrain());
/* 203 */     iForgeRegistry.register(ItemsTC.label = new ItemLabel());
/* 204 */     iForgeRegistry.register(ItemsTC.phial = new ItemPhial());
/* 205 */     iForgeRegistry.register(ItemsTC.alumentum = new ItemAlumentum());
/* 206 */     iForgeRegistry.register(ItemsTC.jarBrace = new ItemTCBase("jar_brace", new String[0]));
/* 207 */     iForgeRegistry.register(ItemsTC.bottleTaint = new ItemBottleTaint());
/* 208 */     iForgeRegistry.register(ItemsTC.sanitySoap = new ItemSanitySoap());
/* 209 */     iForgeRegistry.register(ItemsTC.bathSalts = new ItemBathSalts());
/* 210 */     iForgeRegistry.register(ItemsTC.turretPlacer = new ItemTurretPlacer());
/* 211 */     iForgeRegistry.register(ItemsTC.causalityCollapser = new ItemCausalityCollapser());
/*     */ 
/*     */     
/* 214 */     iForgeRegistry.register(ItemsTC.scribingTools = new ItemScribingTools());
/* 215 */     iForgeRegistry.register(ItemsTC.thaumometer = new ItemThaumometer());
/* 216 */     iForgeRegistry.register(ItemsTC.resonator = new ItemResonator());
/* 217 */     iForgeRegistry.register(ItemsTC.sanityChecker = new ItemSanityChecker());
/* 218 */     iForgeRegistry.register(ItemsTC.handMirror = new ItemHandMirror());
/*     */     
/* 220 */     iForgeRegistry.register(ItemsTC.thaumiumAxe = new ItemThaumiumAxe(ThaumcraftMaterials.TOOLMAT_THAUMIUM));
/* 221 */     iForgeRegistry.register(ItemsTC.thaumiumSword = new ItemThaumiumSword(ThaumcraftMaterials.TOOLMAT_THAUMIUM));
/* 222 */     iForgeRegistry.register(ItemsTC.thaumiumShovel = new ItemThaumiumShovel(ThaumcraftMaterials.TOOLMAT_THAUMIUM));
/* 223 */     iForgeRegistry.register(ItemsTC.thaumiumPick = new ItemThaumiumPickaxe(ThaumcraftMaterials.TOOLMAT_THAUMIUM));
/* 224 */     iForgeRegistry.register(ItemsTC.thaumiumHoe = new ItemThaumiumHoe(ThaumcraftMaterials.TOOLMAT_THAUMIUM));
/*     */     
/* 226 */     iForgeRegistry.register(ItemsTC.voidAxe = new ItemVoidAxe(ThaumcraftMaterials.TOOLMAT_VOID));
/* 227 */     iForgeRegistry.register(ItemsTC.voidSword = new ItemVoidSword(ThaumcraftMaterials.TOOLMAT_VOID));
/* 228 */     iForgeRegistry.register(ItemsTC.voidShovel = new ItemVoidShovel(ThaumcraftMaterials.TOOLMAT_VOID));
/* 229 */     iForgeRegistry.register(ItemsTC.voidPick = new ItemVoidPickaxe(ThaumcraftMaterials.TOOLMAT_VOID));
/* 230 */     iForgeRegistry.register(ItemsTC.voidHoe = new ItemVoidHoe(ThaumcraftMaterials.TOOLMAT_VOID));
/*     */     
/* 232 */     iForgeRegistry.register(ItemsTC.elementalAxe = new ItemElementalAxe(ThaumcraftMaterials.TOOLMAT_ELEMENTAL));
/* 233 */     iForgeRegistry.register(ItemsTC.elementalSword = new ItemElementalSword(ThaumcraftMaterials.TOOLMAT_ELEMENTAL));
/* 234 */     iForgeRegistry.register(ItemsTC.elementalShovel = new ItemElementalShovel(ThaumcraftMaterials.TOOLMAT_ELEMENTAL));
/* 235 */     iForgeRegistry.register(ItemsTC.elementalPick = new ItemElementalPickaxe(ThaumcraftMaterials.TOOLMAT_ELEMENTAL));
/* 236 */     iForgeRegistry.register(ItemsTC.elementalHoe = new ItemElementalHoe(ThaumcraftMaterials.TOOLMAT_ELEMENTAL));
/*     */     
/* 238 */     iForgeRegistry.register(ItemsTC.primalCrusher = new ItemPrimalCrusher());
/* 239 */     iForgeRegistry.register(ItemsTC.crimsonBlade = new ItemCrimsonBlade());
/*     */     
/* 241 */     iForgeRegistry.register(ItemsTC.grappleGun = new ItemGrappleGun());
/* 242 */     iForgeRegistry.register(ItemsTC.grappleGunTip = new ItemTCBase("grapple_gun_tip", new String[0]));
/* 243 */     iForgeRegistry.register(ItemsTC.grappleGunSpool = new ItemTCBase("grapple_gun_spool", new String[0]));
/*     */ 
/*     */     
/* 246 */     iForgeRegistry.register(ItemsTC.goggles = new ItemGoggles());
/*     */     
/* 248 */     iForgeRegistry.register(ItemsTC.thaumiumHelm = new ItemThaumiumArmor("thaumium_helm", ThaumcraftMaterials.ARMORMAT_THAUMIUM, 2, EntityEquipmentSlot.HEAD));
/* 249 */     iForgeRegistry.register(ItemsTC.thaumiumChest = new ItemThaumiumArmor("thaumium_chest", ThaumcraftMaterials.ARMORMAT_THAUMIUM, 2, EntityEquipmentSlot.CHEST));
/* 250 */     iForgeRegistry.register(ItemsTC.thaumiumLegs = new ItemThaumiumArmor("thaumium_legs", ThaumcraftMaterials.ARMORMAT_THAUMIUM, 2, EntityEquipmentSlot.LEGS));
/* 251 */     iForgeRegistry.register(ItemsTC.thaumiumBoots = new ItemThaumiumArmor("thaumium_boots", ThaumcraftMaterials.ARMORMAT_THAUMIUM, 2, EntityEquipmentSlot.FEET));
/*     */     
/* 253 */     iForgeRegistry.register(ItemsTC.clothChest = new ItemRobeArmor("cloth_chest", ThaumcraftMaterials.ARMORMAT_SPECIAL, 1, EntityEquipmentSlot.CHEST));
/* 254 */     iForgeRegistry.register(ItemsTC.clothLegs = new ItemRobeArmor("cloth_legs", ThaumcraftMaterials.ARMORMAT_SPECIAL, 2, EntityEquipmentSlot.LEGS));
/* 255 */     iForgeRegistry.register(ItemsTC.clothBoots = new ItemRobeArmor("cloth_boots", ThaumcraftMaterials.ARMORMAT_SPECIAL, 1, EntityEquipmentSlot.FEET));
/*     */     
/* 257 */     iForgeRegistry.register(ItemsTC.travellerBoots = new ItemBootsTraveller());
/*     */     
/* 259 */     iForgeRegistry.register(ItemsTC.fortressHelm = new ItemFortressArmor("fortress_helm", ThaumcraftMaterials.ARMORMAT_FORTRESS, 4, EntityEquipmentSlot.HEAD));
/* 260 */     iForgeRegistry.register(ItemsTC.fortressChest = new ItemFortressArmor("fortress_chest", ThaumcraftMaterials.ARMORMAT_FORTRESS, 4, EntityEquipmentSlot.CHEST));
/* 261 */     iForgeRegistry.register(ItemsTC.fortressLegs = new ItemFortressArmor("fortress_legs", ThaumcraftMaterials.ARMORMAT_FORTRESS, 4, EntityEquipmentSlot.LEGS));
/*     */     
/* 263 */     iForgeRegistry.register(ItemsTC.voidHelm = new ItemVoidArmor("void_helm", ThaumcraftMaterials.ARMORMAT_VOID, 2, EntityEquipmentSlot.HEAD));
/* 264 */     iForgeRegistry.register(ItemsTC.voidChest = new ItemVoidArmor("void_chest", ThaumcraftMaterials.ARMORMAT_VOID, 2, EntityEquipmentSlot.CHEST));
/* 265 */     iForgeRegistry.register(ItemsTC.voidLegs = new ItemVoidArmor("void_legs", ThaumcraftMaterials.ARMORMAT_VOID, 2, EntityEquipmentSlot.LEGS));
/* 266 */     iForgeRegistry.register(ItemsTC.voidBoots = new ItemVoidArmor("void_boots", ThaumcraftMaterials.ARMORMAT_VOID, 2, EntityEquipmentSlot.FEET));
/*     */     
/* 268 */     iForgeRegistry.register(ItemsTC.voidRobeHelm = new ItemVoidRobeArmor("void_robe_helm", ThaumcraftMaterials.ARMORMAT_VOIDROBE, 4, EntityEquipmentSlot.HEAD));
/* 269 */     iForgeRegistry.register(ItemsTC.voidRobeChest = new ItemVoidRobeArmor("void_robe_chest", ThaumcraftMaterials.ARMORMAT_VOIDROBE, 4, EntityEquipmentSlot.CHEST));
/* 270 */     iForgeRegistry.register(ItemsTC.voidRobeLegs = new ItemVoidRobeArmor("void_robe_legs", ThaumcraftMaterials.ARMORMAT_VOIDROBE, 4, EntityEquipmentSlot.LEGS));
/*     */     
/* 272 */     iForgeRegistry.register(ItemsTC.crimsonPlateHelm = new ItemCultistPlateArmor("crimson_plate_helm", ThaumcraftMaterials.ARMORMAT_CULTIST_PLATE, 4, EntityEquipmentSlot.HEAD));
/* 273 */     iForgeRegistry.register(ItemsTC.crimsonPlateChest = new ItemCultistPlateArmor("crimson_plate_chest", ThaumcraftMaterials.ARMORMAT_CULTIST_PLATE, 4, EntityEquipmentSlot.CHEST));
/* 274 */     iForgeRegistry.register(ItemsTC.crimsonPlateLegs = new ItemCultistPlateArmor("crimson_plate_legs", ThaumcraftMaterials.ARMORMAT_CULTIST_PLATE, 4, EntityEquipmentSlot.LEGS));
/*     */     
/* 276 */     iForgeRegistry.register(ItemsTC.crimsonBoots = new ItemCultistBoots());
/*     */     
/* 278 */     iForgeRegistry.register(ItemsTC.crimsonRobeHelm = new ItemCultistRobeArmor("crimson_robe_helm", ThaumcraftMaterials.ARMORMAT_CULTIST_ROBE, 4, EntityEquipmentSlot.HEAD));
/* 279 */     iForgeRegistry.register(ItemsTC.crimsonRobeChest = new ItemCultistRobeArmor("crimson_robe_chest", ThaumcraftMaterials.ARMORMAT_CULTIST_ROBE, 4, EntityEquipmentSlot.CHEST));
/* 280 */     iForgeRegistry.register(ItemsTC.crimsonRobeLegs = new ItemCultistRobeArmor("crimson_robe_legs", ThaumcraftMaterials.ARMORMAT_CULTIST_ROBE, 4, EntityEquipmentSlot.LEGS));
/*     */     
/* 282 */     iForgeRegistry.register(ItemsTC.crimsonPraetorHelm = new ItemCultistLeaderArmor("crimson_praetor_helm", 4, EntityEquipmentSlot.HEAD));
/* 283 */     iForgeRegistry.register(ItemsTC.crimsonPraetorChest = new ItemCultistLeaderArmor("crimson_praetor_chest", 4, EntityEquipmentSlot.CHEST));
/* 284 */     iForgeRegistry.register(ItemsTC.crimsonPraetorLegs = new ItemCultistLeaderArmor("crimson_praetor_legs", 4, EntityEquipmentSlot.LEGS));
/*     */ 
/*     */     
/* 287 */     iForgeRegistry.register(ItemsTC.baubles = new ItemBaubles());
/* 288 */     iForgeRegistry.register(ItemsTC.amuletVis = new ItemAmuletVis());
/* 289 */     iForgeRegistry.register(ItemsTC.charmVerdant = new ItemVerdantCharm());
/* 290 */     iForgeRegistry.register(ItemsTC.bandCuriosity = new ItemCuriosityBand());
/* 291 */     iForgeRegistry.register(ItemsTC.charmVoidseer = new ItemVoidseerCharm());
/* 292 */     iForgeRegistry.register(ItemsTC.ringCloud = new ItemCloudRing());
/* 293 */     iForgeRegistry.register(ItemsTC.charmUndying = new ItemCharmUndying());
/*     */ 
/*     */ 
/*     */     
/* 297 */     iForgeRegistry.register(ItemsTC.creativeFluxSponge = new ItemCreativeFluxSponge());
/* 298 */     iForgeRegistry.register(ItemsTC.enchantedPlaceholder = new ItemEnchantmentPlaceholder());
/*     */ 
/*     */     
/* 301 */     iForgeRegistry.register(ItemsTC.casterBasic = new ItemCaster("caster_basic", 0));
/* 302 */     iForgeRegistry.register(ItemsTC.focus1 = new ItemFocus("focus_1", 15));
/* 303 */     iForgeRegistry.register(ItemsTC.focus2 = new ItemFocus("focus_2", 25));
/* 304 */     iForgeRegistry.register(ItemsTC.focus3 = new ItemFocus("focus_3", 50));
/* 305 */     iForgeRegistry.register(ItemsTC.focusPouch = new ItemFocusPouch());
/*     */ 
/*     */     
/* 308 */     iForgeRegistry.register(ItemsTC.golemBell = new ItemGolemBell());
/* 309 */     iForgeRegistry.register(ItemsTC.golemPlacer = new ItemGolemPlacer());
/* 310 */     iForgeRegistry.register(ItemsTC.seals = new ItemSealPlacer());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void init() {
/* 315 */     FocusEngine.registerElement(thaumcraft.api.casters.FocusMediumRoot.class, new ResourceLocation("thaumcraft", "textures/foci/root.png"), 10066329);
/*     */     
/* 317 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusMediumTouch.class, new ResourceLocation("thaumcraft", "textures/foci/touch.png"), 11371909);
/* 318 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusMediumBolt.class, new ResourceLocation("thaumcraft", "textures/foci/bolt.png"), 11377029);
/* 319 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusMediumProjectile.class, new ResourceLocation("thaumcraft", "textures/foci/projectile.png"), 11382149);
/* 320 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusMediumCloud.class, new ResourceLocation("thaumcraft", "textures/foci/cloud.png"), 10071429);
/* 321 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusMediumMine.class, new ResourceLocation("thaumcraft", "textures/foci/mine.png"), 8760709);
/* 322 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusMediumPlan.class, new ResourceLocation("thaumcraft", "textures/foci/plan.png"), 8760728);
/* 323 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusMediumSpellBat.class, new ResourceLocation("thaumcraft", "textures/foci/spellbat.png"), 8760748);
/*     */     
/* 325 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectFire.class, new ResourceLocation("thaumcraft", "textures/foci/fire.png"), 16734721);
/* 326 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectFrost.class, new ResourceLocation("thaumcraft", "textures/foci/frost.png"), 14811135);
/* 327 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectAir.class, new ResourceLocation("thaumcraft", "textures/foci/air.png"), 16777086);
/* 328 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectEarth.class, new ResourceLocation("thaumcraft", "textures/foci/earth.png"), 5685248);
/* 329 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectFlux.class, new ResourceLocation("thaumcraft", "textures/foci/flux.png"), 8388736);
/* 330 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectBreak.class, new ResourceLocation("thaumcraft", "textures/foci/break.png"), 9063176);
/* 331 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectRift.class, new ResourceLocation("thaumcraft", "textures/foci/rift.png"), 3084645);
/* 332 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectExchange.class, new ResourceLocation("thaumcraft", "textures/foci/exchange.png"), 5735255);
/* 333 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectCurse.class, new ResourceLocation("thaumcraft", "textures/foci/curse.png"), 6946821);
/* 334 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusEffectHeal.class, new ResourceLocation("thaumcraft", "textures/foci/heal.png"), 14548997);
/*     */     
/* 336 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusModScatter.class, new ResourceLocation("thaumcraft", "textures/foci/scatter.png"), 10066329);
/* 337 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusModSplitTarget.class, new ResourceLocation("thaumcraft", "textures/foci/split_target.png"), 10066329);
/* 338 */     FocusEngine.registerElement(thaumcraft.common.items.casters.foci.FocusModSplitTrajectory.class, new ResourceLocation("thaumcraft", "textures/foci/split_trajectory.png"), 10066329);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void preInitSeals() {
/* 343 */     SealHandler.registerSeal(new SealPickup());
/* 344 */     SealHandler.registerSeal(new SealPickupAdvanced());
/* 345 */     SealHandler.registerSeal(new SealFill());
/* 346 */     SealHandler.registerSeal(new SealFillAdvanced());
/* 347 */     SealHandler.registerSeal(new SealEmpty());
/* 348 */     SealHandler.registerSeal(new SealEmptyAdvanced());
/* 349 */     SealHandler.registerSeal(new SealHarvest());
/* 350 */     SealHandler.registerSeal(new SealButcher());
/* 351 */     SealHandler.registerSeal(new SealGuard());
/* 352 */     SealHandler.registerSeal(new SealGuardAdvanced());
/* 353 */     SealHandler.registerSeal(new SealLumber());
/* 354 */     SealHandler.registerSeal(new SealBreaker());
/* 355 */     SealHandler.registerSeal(new SealUse());
/* 356 */     SealHandler.registerSeal(new SealProvide());
/* 357 */     SealHandler.registerSeal(new SealStock());
/* 358 */     SealHandler.registerSeal(new SealBreakerAdvanced());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public static CreativeTabs TABTC = new CreativeTabThaumcraft(CreativeTabs.getNextID(), "thaumcraft"); public static ItemStack AIR_CRYSTAL;
/*     */   public static ItemStack FIRE_CRYSTAL;
/* 366 */   public static final List<IThaumcraftItems> ITEM_VARIANT_HOLDERS = new ArrayList();
/*     */   public static ItemStack WATER_CRYSTAL;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static void initModelsAndVariants() {
/* 371 */     for (IThaumcraftItems itemVariantHolder : ITEM_VARIANT_HOLDERS)
/* 372 */       initModelAndVariants(itemVariantHolder); 
/*     */   }
/*     */   public static ItemStack EARTH_CRYSTAL;
/*     */   public static ItemStack ORDER_CRYSTAL;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private static void initModelAndVariants(IThaumcraftItems item) {
/* 379 */     if (item.getCustomMesh() != null) {
/*     */       
/* 381 */       ModelLoader.setCustomMeshDefinition(item.getItem(), item.getCustomMesh());
/* 382 */       for (int i = 0; i < item.getVariantNames().length; i++) {
/* 383 */         ModelBakery.registerItemVariants(item.getItem(), new ResourceLocation[] { item.getCustomModelResourceLocation(item.getVariantNames()[i]) });
/*     */       }
/*     */     
/*     */     }
/* 387 */     else if (item.getItem() == ItemsTC.seals) {
/* 388 */       for (int i = 0; i < item.getVariantNames().length; i++) {
/* 389 */         ModelLoader.setCustomModelResourceLocation(item.getItem(), item.getVariantMeta()[i], new ModelResourceLocation(item
/* 390 */               .getItem().getRegistryName() + "_" + item.getVariantNames()[i], null));
/*     */       }
/*     */     }
/* 393 */     else if (!item.getItem().func_77614_k()) {
/* 394 */       ModelLoader.setCustomModelResourceLocation(item.getItem(), 0, new ModelResourceLocation(item.getItem().getRegistryName(), null));
/*     */     } else {
/*     */       
/* 397 */       for (int i = 0; i < item.getVariantNames().length; i++)
/* 398 */         ModelLoader.setCustomModelResourceLocation(item.getItem(), item.getVariantMeta()[i], item.getCustomModelResourceLocation(item.getVariantNames()[i])); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ItemStack ENTROPY_CRYSTAL;
/*     */   public static ItemStack FLUX_CRYSTAL;
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\config\ConfigItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */