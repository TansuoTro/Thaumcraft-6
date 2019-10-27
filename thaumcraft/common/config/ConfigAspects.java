/*     */ package thaumcraft.common.config;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.PotionTypes;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionType;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraftforge.common.ForgeModContainer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.fluids.FluidUtil;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectEventProxy;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.AspectRegistryEvent;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.internal.CommonInternals;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
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
/*     */ public class ConfigAspects
/*     */ {
/*     */   public static void postInit() {
/*  40 */     CommonInternals.objectTags.clear();
/*  41 */     registerItemAspects();
/*  42 */     registerEntityAspects();
/*  43 */     are = new AspectRegistryEvent();
/*  44 */     are.register = new AspectEventProxy();
/*  45 */     MinecraftForge.EVENT_BUS.post(are);
/*     */   }
/*     */   public static String[] dyes = { 
/*  48 */       "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerEntityAspects() {
/*  55 */     ThaumcraftApi.registerEntityTag("Zombie", (new AspectList()).add(Aspect.UNDEAD, 20).add(Aspect.MAN, 10).add(Aspect.EARTH, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  56 */     ThaumcraftApi.registerEntityTag("Husk", (new AspectList()).add(Aspect.UNDEAD, 20).add(Aspect.MAN, 10).add(Aspect.FIRE, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  57 */     ThaumcraftApi.registerEntityTag("Giant", (new AspectList()).add(Aspect.UNDEAD, 25).add(Aspect.MAN, 15).add(Aspect.EARTH, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  58 */     ThaumcraftApi.registerEntityTag("Skeleton", (new AspectList()).add(Aspect.UNDEAD, 20).add(Aspect.MAN, 5).add(Aspect.EARTH, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  59 */     ThaumcraftApi.registerEntityTag("WitherSkeleton", (new AspectList()).add(Aspect.UNDEAD, 25).add(Aspect.MAN, 5).add(Aspect.ENTROPY, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  60 */     ThaumcraftApi.registerEntityTag("Creeper", (new AspectList()).add(Aspect.PLANT, 15).add(Aspect.FIRE, 15), new ThaumcraftApi.EntityTagsNBT[0]);
/*  61 */     ThaumcraftApi.registerEntityTag("Creeper", (new AspectList()).add(Aspect.PLANT, 15).add(Aspect.FIRE, 15).add(Aspect.ENERGY, 15), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("powered", Byte.valueOf((byte)1)) });
/*  62 */     ThaumcraftApi.registerEntityTag("Horse", (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.EARTH, 5).add(Aspect.AIR, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  63 */     ThaumcraftApi.registerEntityTag("Donkey", (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.EARTH, 5).add(Aspect.AIR, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  64 */     ThaumcraftApi.registerEntityTag("Mule", (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.EARTH, 5).add(Aspect.AIR, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  65 */     ThaumcraftApi.registerEntityTag("SkeletonHorse", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.UNDEAD, 10).add(Aspect.EARTH, 5).add(Aspect.AIR, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  66 */     ThaumcraftApi.registerEntityTag("ZombieHorse", (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.UNDEAD, 5).add(Aspect.EARTH, 5).add(Aspect.AIR, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  67 */     ThaumcraftApi.registerEntityTag("Pig", (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.EARTH, 10).add(Aspect.DESIRE, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  68 */     ThaumcraftApi.registerEntityTag("XPOrb", (new AspectList()).add(Aspect.MIND, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  69 */     ThaumcraftApi.registerEntityTag("Sheep", (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.EARTH, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  70 */     ThaumcraftApi.registerEntityTag("Cow", (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.EARTH, 15), new ThaumcraftApi.EntityTagsNBT[0]);
/*  71 */     ThaumcraftApi.registerEntityTag("MushroomCow", (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.PLANT, 15).add(Aspect.EARTH, 15), new ThaumcraftApi.EntityTagsNBT[0]);
/*  72 */     ThaumcraftApi.registerEntityTag("SnowMan", (new AspectList()).add(Aspect.COLD, 10).add(Aspect.MAN, 5).add(Aspect.MECHANISM, 5).add(Aspect.MAGIC, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  73 */     ThaumcraftApi.registerEntityTag("Ozelot", (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.ENTROPY, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  74 */     ThaumcraftApi.registerEntityTag("Chicken", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 5).add(Aspect.AIR, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  75 */     ThaumcraftApi.registerEntityTag("Squid", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.WATER, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  76 */     ThaumcraftApi.registerEntityTag("Wolf", (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.EARTH, 10).add(Aspect.AVERSION, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  77 */     ThaumcraftApi.registerEntityTag("Bat", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 5).add(Aspect.DARKNESS, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */     
/*  79 */     ThaumcraftApi.registerEntityTag("Spider", (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.ENTROPY, 10).add(Aspect.TRAP, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  80 */     ThaumcraftApi.registerEntityTag("Slime", (new AspectList()).add(Aspect.LIFE, 10).add(Aspect.WATER, 10).add(Aspect.ALCHEMY, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  81 */     ThaumcraftApi.registerEntityTag("Ghast", (new AspectList()).add(Aspect.UNDEAD, 15).add(Aspect.FIRE, 15), new ThaumcraftApi.EntityTagsNBT[0]);
/*  82 */     ThaumcraftApi.registerEntityTag("PigZombie", (new AspectList()).add(Aspect.UNDEAD, 15).add(Aspect.FIRE, 15).add(Aspect.BEAST, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  83 */     ThaumcraftApi.registerEntityTag("Enderman", (new AspectList()).add(Aspect.ELDRITCH, 10).add(Aspect.MOTION, 15).add(Aspect.DESIRE, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  84 */     ThaumcraftApi.registerEntityTag("CaveSpider", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.DEATH, 10).add(Aspect.TRAP, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  85 */     ThaumcraftApi.registerEntityTag("Silverfish", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.EARTH, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  86 */     ThaumcraftApi.registerEntityTag("Blaze", (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.FIRE, 15).add(Aspect.FLIGHT, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  87 */     ThaumcraftApi.registerEntityTag("LavaSlime", (new AspectList()).add(Aspect.WATER, 5).add(Aspect.FIRE, 10).add(Aspect.ALCHEMY, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  88 */     ThaumcraftApi.registerEntityTag("EnderDragon", (new AspectList()).add(Aspect.ELDRITCH, 50).add(Aspect.BEAST, 30).add(Aspect.ENTROPY, 50).add(Aspect.FLIGHT, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  89 */     ThaumcraftApi.registerEntityTag("WitherBoss", (new AspectList()).add(Aspect.UNDEAD, 50).add(Aspect.ENTROPY, 25).add(Aspect.FIRE, 25), new ThaumcraftApi.EntityTagsNBT[0]);
/*  90 */     ThaumcraftApi.registerEntityTag("Witch", (new AspectList()).add(Aspect.MAN, 15).add(Aspect.MAGIC, 5).add(Aspect.ALCHEMY, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  91 */     ThaumcraftApi.registerEntityTag("Villager", (new AspectList()).add(Aspect.MAN, 15), new ThaumcraftApi.EntityTagsNBT[0]);
/*  92 */     ThaumcraftApi.registerEntityTag("VillagerGolem", (new AspectList()).add(Aspect.METAL, 15).add(Aspect.MAN, 5).add(Aspect.MECHANISM, 5).add(Aspect.MAGIC, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  93 */     ThaumcraftApi.registerEntityTag("EnderCrystal", (new AspectList()).add(Aspect.ELDRITCH, 15).add(Aspect.AURA, 15).add(Aspect.LIFE, 15), new ThaumcraftApi.EntityTagsNBT[0]);
/*  94 */     ThaumcraftApi.registerEntityTag("ItemFrame", (new AspectList()).add(Aspect.SENSES, 5).add(Aspect.CRAFT, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  95 */     ThaumcraftApi.registerEntityTag("Painting", (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.CRAFT, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  96 */     ThaumcraftApi.registerEntityTag("Guardian", (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.ELDRITCH, 10).add(Aspect.WATER, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*  97 */     ThaumcraftApi.registerEntityTag("Guardian", (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.ELDRITCH, 15).add(Aspect.WATER, 15), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("Elder", Boolean.valueOf(true)) });
/*  98 */     ThaumcraftApi.registerEntityTag("Rabbit", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.EARTH, 5).add(Aspect.MOTION, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*  99 */     ThaumcraftApi.registerEntityTag("Endermite", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.ELDRITCH, 5).add(Aspect.MOTION, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 100 */     ThaumcraftApi.registerEntityTag("PolarBear", (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.COLD, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/* 101 */     ThaumcraftApi.registerEntityTag("Shulker", (new AspectList()).add(Aspect.ELDRITCH, 10).add(Aspect.TRAP, 5).add(Aspect.FLIGHT, 5).add(Aspect.PROTECT, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 102 */     ThaumcraftApi.registerEntityTag("EvocationIllager", (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.MAGIC, 5).add(Aspect.MAN, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/* 103 */     ThaumcraftApi.registerEntityTag("VindicationIllager", (new AspectList()).add(Aspect.AVERSION, 5).add(Aspect.MAGIC, 5).add(Aspect.MAN, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/* 104 */     ThaumcraftApi.registerEntityTag("IllusionIllager", (new AspectList()).add(Aspect.SENSES, 5).add(Aspect.MAGIC, 5).add(Aspect.MAN, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/* 105 */     ThaumcraftApi.registerEntityTag("Llama", (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.WATER, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 106 */     ThaumcraftApi.registerEntityTag("Parrot", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 5).add(Aspect.SENSES, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 107 */     ThaumcraftApi.registerEntityTag("Stray", (new AspectList()).add(Aspect.UNDEAD, 20).add(Aspect.MAN, 5).add(Aspect.TRAP, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 108 */     ThaumcraftApi.registerEntityTag("Vex", (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.FLIGHT, 5).add(Aspect.MAGIC, 5).add(Aspect.MAN, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 109 */     ThaumcraftApi.registerEntityTag("Stray", (new AspectList()).add(Aspect.UNDEAD, 20).add(Aspect.MAN, 5).add(Aspect.TRAP, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */ 
/*     */ 
/*     */     
/* 113 */     ThaumcraftApi.registerEntityTag("Thaumcraft.FluxRift", (new AspectList()).add(Aspect.FLUX, 20).add(Aspect.ELDRITCH, 20).add(Aspect.AURA, 20), new ThaumcraftApi.EntityTagsNBT[0]);
/* 114 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Firebat", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.FLIGHT, 5).add(Aspect.FIRE, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/* 115 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Pech", (new AspectList()).add(Aspect.MAN, 10).add(Aspect.AURA, 5).add(Aspect.EXCHANGE, 10).add(Aspect.DESIRE, 5), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("PechType", Byte.valueOf((byte)0)) });
/* 116 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Pech", (new AspectList()).add(Aspect.MAN, 10).add(Aspect.AURA, 5).add(Aspect.EXCHANGE, 10).add(Aspect.AVERSION, 5), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("PechType", Byte.valueOf((byte)1)) });
/* 117 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Pech", (new AspectList()).add(Aspect.MAN, 10).add(Aspect.AURA, 5).add(Aspect.EXCHANGE, 10).add(Aspect.MAGIC, 5), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("PechType", Byte.valueOf((byte)2)) });
/* 118 */     ThaumcraftApi.registerEntityTag("Thaumcraft.ThaumSlime", (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.WATER, 5).add(Aspect.FLUX, 5).add(Aspect.ALCHEMY, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 119 */     ThaumcraftApi.registerEntityTag("Thaumcraft.BrainyZombie", (new AspectList()).add(Aspect.UNDEAD, 20).add(Aspect.MAN, 10).add(Aspect.MIND, 5).add(Aspect.AVERSION, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 120 */     ThaumcraftApi.registerEntityTag("Thaumcraft.GiantBrainyZombie", (new AspectList()).add(Aspect.UNDEAD, 25).add(Aspect.MAN, 15).add(Aspect.MIND, 5).add(Aspect.AVERSION, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/* 121 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Taintacle", (new AspectList()).add(Aspect.FLUX, 15).add(Aspect.BEAST, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/* 122 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSeed", (new AspectList()).add(Aspect.FLUX, 20).add(Aspect.AURA, 10).add(Aspect.PLANT, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 123 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSeedPrime", (new AspectList()).add(Aspect.FLUX, 25).add(Aspect.AURA, 15).add(Aspect.PLANT, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 124 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintacleTiny", (new AspectList()).add(Aspect.FLUX, 5).add(Aspect.BEAST, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 125 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSwarm", (new AspectList()).add(Aspect.FLUX, 15).add(Aspect.AIR, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 126 */     ThaumcraftApi.registerEntityTag("Thaumcraft.MindSpider", (new AspectList()).add(Aspect.FLUX, 5).add(Aspect.FIRE, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */     
/* 128 */     ThaumcraftApi.registerEntityTag("Thaumcraft.EldritchGuardian", (new AspectList()).add(Aspect.ELDRITCH, 20).add(Aspect.DEATH, 20).add(Aspect.UNDEAD, 20), new ThaumcraftApi.EntityTagsNBT[0]);
/* 129 */     ThaumcraftApi.registerEntityTag("Thaumcraft.CultistKnight", (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.MAN, 15).add(Aspect.AVERSION, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 130 */     ThaumcraftApi.registerEntityTag("Thaumcraft.CultistCleric", (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.MAN, 15).add(Aspect.AVERSION, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 131 */     ThaumcraftApi.registerEntityTag("Thaumcraft.EldritchCrab", (new AspectList()).add(Aspect.ELDRITCH, 10).add(Aspect.BEAST, 10).add(Aspect.TRAP, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/* 132 */     ThaumcraftApi.registerEntityTag("Thaumcraft.InhabitedZombie", (new AspectList()).add(Aspect.ELDRITCH, 10).add(Aspect.UNDEAD, 10).add(Aspect.MAN, 5), new ThaumcraftApi.EntityTagsNBT[0]);
/* 133 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSeed", (new AspectList()).add(Aspect.PLANT, 20).add(Aspect.BEAST, 20).add(Aspect.FLUX, 20), new ThaumcraftApi.EntityTagsNBT[0]);
/* 134 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintSeedPrime", (new AspectList()).add(Aspect.PLANT, 30).add(Aspect.BEAST, 30).add(Aspect.FLUX, 30), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */ 
/*     */     
/* 137 */     ThaumcraftApi.registerEntityTag("Thaumcraft.EldritchWarden", (new AspectList()).add(Aspect.ELDRITCH, 40).add(Aspect.DEATH, 40).add(Aspect.UNDEAD, 40), new ThaumcraftApi.EntityTagsNBT[0]);
/* 138 */     ThaumcraftApi.registerEntityTag("Thaumcraft.EldritchGolem", (new AspectList()).add(Aspect.ELDRITCH, 40).add(Aspect.ENERGY, 40).add(Aspect.MECHANISM, 40), new ThaumcraftApi.EntityTagsNBT[0]);
/* 139 */     ThaumcraftApi.registerEntityTag("Thaumcraft.CultistLeader", (new AspectList()).add(Aspect.ELDRITCH, 40).add(Aspect.AVERSION, 40).add(Aspect.MAN, 40), new ThaumcraftApi.EntityTagsNBT[0]);
/* 140 */     ThaumcraftApi.registerEntityTag("Thaumcraft.TaintacleGiant", (new AspectList()).add(Aspect.ELDRITCH, 40).add(Aspect.BEAST, 40).add(Aspect.FLUX, 40), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */     
/* 142 */     for (Aspect tag : Aspect.aspects.values()) {
/* 143 */       ThaumcraftApi.registerEntityTag("Thaumcraft.Wisp", (new AspectList())
/* 144 */           .add(tag, 5).add(Aspect.AURA, 5).add(Aspect.FLIGHT, 5), new ThaumcraftApi.EntityTagsNBT[] { new ThaumcraftApi.EntityTagsNBT("Type", tag
/* 145 */               .getTag()) });
/*     */     } 
/*     */     
/* 148 */     ThaumcraftApi.registerEntityTag("Thaumcraft.Golem", (new AspectList()).add(Aspect.MECHANISM, 10).add(Aspect.MAN, 10).add(Aspect.MOTION, 10), new ThaumcraftApi.EntityTagsNBT[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void registerItemAspects() {
/* 156 */     ThaumcraftApi.registerObjectTag("oreLapis", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.SENSES, 15));
/* 157 */     ThaumcraftApi.registerObjectTag("oreDiamond", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.DESIRE, 15).add(Aspect.CRYSTAL, 15));
/* 158 */     ThaumcraftApi.registerObjectTag("gemDiamond", (new AspectList()).add(Aspect.CRYSTAL, 15).add(Aspect.DESIRE, 15));
/* 159 */     ThaumcraftApi.registerObjectTag("oreRedstone", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ENERGY, 15));
/* 160 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150439_ay), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ENERGY, 15));
/* 161 */     ThaumcraftApi.registerObjectTag("oreEmerald", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.DESIRE, 10).add(Aspect.CRYSTAL, 15));
/* 162 */     ThaumcraftApi.registerObjectTag("gemEmerald", (new AspectList()).add(Aspect.CRYSTAL, 15).add(Aspect.DESIRE, 10));
/* 163 */     ThaumcraftApi.registerObjectTag("oreQuartz", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.CRYSTAL, 10));
/* 164 */     ThaumcraftApi.registerObjectTag("gemQuartz", (new AspectList()).add(Aspect.CRYSTAL, 5));
/* 165 */     ThaumcraftApi.registerObjectTag("oreIron", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.METAL, 15));
/* 166 */     ThaumcraftApi.registerObjectTag("dustIron", (new AspectList()).add(Aspect.METAL, 15).add(Aspect.ENTROPY, 1));
/* 167 */     ThaumcraftApi.registerObjectTag("ingotIron", (new AspectList()).add(Aspect.METAL, 15));
/* 168 */     ThaumcraftApi.registerObjectTag("oreGold", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.METAL, 10).add(Aspect.DESIRE, 10));
/* 169 */     ThaumcraftApi.registerObjectTag("dustGold", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.DESIRE, 10).add(Aspect.ENTROPY, 1));
/* 170 */     ThaumcraftApi.registerObjectTag("ingotGold", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.DESIRE, 10));
/* 171 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150365_q), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ENERGY, 15).add(Aspect.FIRE, 15));
/* 172 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151044_h, 1, 32767), (new AspectList()).add(Aspect.ENERGY, 10).add(Aspect.FIRE, 10));
/* 173 */     ThaumcraftApi.registerObjectTag("dustRedstone", (new AspectList()).add(Aspect.ENERGY, 10));
/* 174 */     ThaumcraftApi.registerObjectTag("dustGlowstone", (new AspectList()).add(Aspect.SENSES, 5).add(Aspect.LIGHT, 10));
/* 175 */     ThaumcraftApi.registerObjectTag("glowstone", new AspectList(new ItemStack(Blocks.field_150426_aN)));
/*     */     
/* 177 */     ThaumcraftApi.registerObjectTag("ingotCopper", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.EXCHANGE, 5));
/* 178 */     ThaumcraftApi.registerObjectTag("dustCopper", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 5));
/* 179 */     ThaumcraftApi.registerObjectTag("oreCopper", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.EARTH, 5).add(Aspect.EXCHANGE, 5));
/* 180 */     ThaumcraftApi.registerObjectTag("clusterCopper", (new AspectList()).add(Aspect.ORDER, 5).add(Aspect.METAL, 15).add(Aspect.EARTH, 5).add(Aspect.EXCHANGE, 10));
/*     */     
/* 182 */     ThaumcraftApi.registerObjectTag("ingotTin", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.CRYSTAL, 5));
/* 183 */     ThaumcraftApi.registerObjectTag("dustTin", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.ENTROPY, 1).add(Aspect.CRYSTAL, 5));
/* 184 */     ThaumcraftApi.registerObjectTag("oreTin", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.EARTH, 5).add(Aspect.CRYSTAL, 5));
/* 185 */     ThaumcraftApi.registerObjectTag("clusterTin", (new AspectList()).add(Aspect.ORDER, 5).add(Aspect.METAL, 15).add(Aspect.EARTH, 5).add(Aspect.CRYSTAL, 10));
/*     */     
/* 187 */     ThaumcraftApi.registerObjectTag("ingotSilver", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.DESIRE, 5));
/* 188 */     ThaumcraftApi.registerObjectTag("dustSilver", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.ENTROPY, 1).add(Aspect.DESIRE, 5));
/* 189 */     ThaumcraftApi.registerObjectTag("oreSilver", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.EARTH, 5).add(Aspect.DESIRE, 5));
/* 190 */     ThaumcraftApi.registerObjectTag("clusterSilver", (new AspectList()).add(Aspect.ORDER, 5).add(Aspect.METAL, 15).add(Aspect.EARTH, 5).add(Aspect.DESIRE, 10));
/*     */     
/* 192 */     ThaumcraftApi.registerObjectTag("ingotLead", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.ORDER, 5));
/* 193 */     ThaumcraftApi.registerObjectTag("dustLead", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.ENTROPY, 1).add(Aspect.ORDER, 5));
/* 194 */     ThaumcraftApi.registerObjectTag("oreLead", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5));
/* 195 */     ThaumcraftApi.registerObjectTag("clusterLead", (new AspectList()).add(Aspect.ORDER, 5).add(Aspect.METAL, 15).add(Aspect.EARTH, 5).add(Aspect.ORDER, 10));
/*     */     
/* 197 */     ThaumcraftApi.registerObjectTag("ingotBrass", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.TOOL, 5));
/* 198 */     ThaumcraftApi.registerObjectTag("dustBrass", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.ENTROPY, 1).add(Aspect.TOOL, 5));
/* 199 */     ThaumcraftApi.registerObjectTag("ingotBronze", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.TOOL, 5));
/* 200 */     ThaumcraftApi.registerObjectTag("dustBronze", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.ENTROPY, 1).add(Aspect.TOOL, 5));
/*     */     
/* 202 */     ThaumcraftApi.registerObjectTag("oreUranium", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.DEATH, 5).add(Aspect.ENERGY, 10));
/* 203 */     ThaumcraftApi.registerObjectTag("itemDropUranium", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.DEATH, 5).add(Aspect.ENERGY, 10));
/* 204 */     ThaumcraftApi.registerObjectTag("ingotUranium", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.DEATH, 5).add(Aspect.ENERGY, 10));
/* 205 */     ThaumcraftApi.registerObjectTag("gemRuby", (new AspectList()).add(Aspect.CRYSTAL, 10).add(Aspect.DESIRE, 10));
/* 206 */     ThaumcraftApi.registerObjectTag("gemGreenSapphire", (new AspectList()).add(Aspect.CRYSTAL, 10).add(Aspect.DESIRE, 10));
/* 207 */     ThaumcraftApi.registerObjectTag("gemSapphire", (new AspectList()).add(Aspect.CRYSTAL, 10).add(Aspect.DESIRE, 10));
/* 208 */     ThaumcraftApi.registerObjectTag("ingotSteel", (new AspectList()).add(Aspect.METAL, 15).add(Aspect.ORDER, 5));
/* 209 */     ThaumcraftApi.registerObjectTag("itemRubber", (new AspectList()).add(Aspect.MOTION, 5).add(Aspect.TOOL, 5));
/*     */ 
/*     */     
/* 212 */     ThaumcraftApi.registerObjectTag("stone", (new AspectList()).add(Aspect.EARTH, 5));
/* 213 */     ThaumcraftApi.registerObjectTag("stoneGranite", (new AspectList()).add(Aspect.EARTH, 5));
/* 214 */     ThaumcraftApi.registerObjectTag("stoneDiorite", (new AspectList()).add(Aspect.EARTH, 5));
/* 215 */     ThaumcraftApi.registerObjectTag("stoneAndesite", (new AspectList()).add(Aspect.EARTH, 5));
/* 216 */     ThaumcraftApi.registerObjectTag("cobblestone", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ENTROPY, 1));
/* 217 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150357_h), (new AspectList()).add(Aspect.VOID, 25).add(Aspect.ENTROPY, 25).add(Aspect.EARTH, 25).add(Aspect.DARKNESS, 25));
/* 218 */     ThaumcraftApi.registerObjectTag("dirt", (new AspectList()).add(Aspect.EARTH, 5));
/* 219 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150346_d, 1, 2), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.PLANT, 1));
/* 220 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150458_ak, 1, 32767), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.WATER, 2).add(Aspect.ORDER, 2));
/* 221 */     ThaumcraftApi.registerObjectTag("sand", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ENTROPY, 5));
/* 222 */     ThaumcraftApi.registerObjectTag("grass", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.PLANT, 2));
/* 223 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_185774_da), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.PLANT, 2).add(Aspect.ORDER, 2));
/* 224 */     ThaumcraftApi.registerObjectTag("endstone", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.DARKNESS, 5));
/* 225 */     ThaumcraftApi.registerObjectTag("gravel", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ENTROPY, 2));
/* 226 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150391_bh), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.PLANT, 1).add(Aspect.FLUX, 1));
/* 227 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151119_aD, 1, 32767), (new AspectList()).add(Aspect.WATER, 5).add(Aspect.EARTH, 5));
/* 228 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150405_ch, 1, 32767), (new AspectList(new ItemStack(Blocks.field_150435_aG))).add(Aspect.FIRE, 1));
/* 229 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150406_ce, 1, 32767), (new AspectList(new ItemStack(Blocks.field_150435_aG))).add(Aspect.FIRE, 1).add(Aspect.SENSES, 1));
/* 230 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151118_aC, 1, 32767), (new AspectList(new ItemStack(Items.field_151119_aD))).add(Aspect.FIRE, 1));
/* 231 */     ThaumcraftApi.registerObjectTag("netherrack", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.FIRE, 2));
/* 232 */     ThaumcraftApi.registerObjectTag("ingotBrickNether", (new AspectList(new ItemStack(Blocks.field_150424_aL))).add(Aspect.FIRE, 1).add(Aspect.ORDER, 1));
/* 233 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150425_aM, 1, 32767), (new AspectList()).add(Aspect.EARTH, 3).add(Aspect.TRAP, 1).add(Aspect.SOUL, 3));
/*     */     
/* 235 */     ThaumcraftApi.registerObjectTag("blockGlass", (new AspectList()).add(Aspect.CRYSTAL, 5));
/* 236 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150341_Y, 1, 32767), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.PLANT, 3).add(Aspect.ENTROPY, 1));
/* 237 */     ThaumcraftApi.registerObjectTag("obsidian", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.DARKNESS, 5));
/*     */     
/* 239 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150417_aV, 1, 1), (new AspectList(new ItemStack(Blocks.field_150417_aV))).add(Aspect.PLANT, 1));
/* 240 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150417_aV, 1, 2), (new AspectList(new ItemStack(Blocks.field_150417_aV))).add(Aspect.ENTROPY, 1));
/* 241 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150417_aV, 1, 3), (new AspectList(new ItemStack(Blocks.field_150417_aV))).add(Aspect.ORDER, 1));
/*     */     
/* 243 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150322_A, 1, 1), (new AspectList(new ItemStack(Blocks.field_150322_A))).add(Aspect.ORDER, 1));
/* 244 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150322_A, 1, 2), (new AspectList(new ItemStack(Blocks.field_150322_A))).add(Aspect.ORDER, 1));
/*     */     
/* 246 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_192443_dR, 1, 32767), (new AspectList(new ItemStack(Blocks.field_192444_dS))).add(Aspect.WATER, 1).add(Aspect.ORDER, 1));
/* 247 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192442_dQ), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 248 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192438_dM), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 249 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192439_dN), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 250 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192436_dK), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 251 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192434_dI), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 252 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192440_dO), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 253 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192430_dE), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 254 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192432_dG), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 255 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192429_dD), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 256 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192428_dC), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 257 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192433_dH), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 258 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192437_dL), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 259 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192441_dP), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 260 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192435_dJ), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 261 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192427_dB), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/* 262 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_192431_dF), (new AspectList(new ItemStack(Blocks.field_150406_ce))).add(Aspect.SENSES, 1).add(Aspect.FIRE, 1));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 267 */     ThaumcraftApi.registerObjectTag("logWood", (new AspectList()).add(Aspect.PLANT, 20));
/* 268 */     ThaumcraftApi.registerObjectTag("treeSapling", (new AspectList()).add(Aspect.PLANT, 15).add(Aspect.LIFE, 5));
/* 269 */     ThaumcraftApi.registerObjectTag("treeLeaves", (new AspectList()).add(Aspect.PLANT, 5));
/*     */     
/* 271 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150329_H, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AIR, 1));
/* 272 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150398_cm, 1, 0), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AIR, 1).add(Aspect.SENSES, 5).add(Aspect.LIFE, 1));
/* 273 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150398_cm, 1, 1), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AIR, 1).add(Aspect.SENSES, 5).add(Aspect.LIFE, 1));
/* 274 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150398_cm, 1, 2), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AIR, 1));
/* 275 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150398_cm, 1, 3), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AIR, 1));
/* 276 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150398_cm, 1, 4), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AIR, 1).add(Aspect.SENSES, 5).add(Aspect.LIFE, 1));
/* 277 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150398_cm, 1, 5), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AIR, 1).add(Aspect.SENSES, 5).add(Aspect.LIFE, 1));
/* 278 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150392_bi, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.WATER, 1));
/* 279 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150330_I, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.ENTROPY, 1));
/* 280 */     ThaumcraftApi.registerObjectTag("vine", (new AspectList()).add(Aspect.PLANT, 5));
/* 281 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151014_N, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 1));
/* 282 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151081_bc, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 1));
/* 283 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151080_bb, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 1));
/* 284 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_185163_cU, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 1));
/* 285 */     ThaumcraftApi.registerObjectTag("cropNetherWart", (new AspectList()).add(Aspect.PLANT, 1).add(Aspect.FLUX, 2).add(Aspect.ALCHEMY, 3));
/*     */     
/* 287 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150328_O, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 1).add(Aspect.SENSES, 5));
/* 288 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150327_N, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 1).add(Aspect.SENSES, 5));
/* 289 */     ThaumcraftApi.registerObjectTag("blockCactus", (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.WATER, 5).add(Aspect.AVERSION, 1));
/* 290 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150338_P), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.DARKNESS, 2).add(Aspect.EARTH, 2));
/* 291 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150337_Q), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.DARKNESS, 2).add(Aspect.FIRE, 2));
/* 292 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150420_aW, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.DARKNESS, 2).add(Aspect.EARTH, 2));
/* 293 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150419_aX, 1, 32767), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.DARKNESS, 2).add(Aspect.FIRE, 2));
/* 294 */     ThaumcraftApi.registerObjectTag("sugarcane", (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.WATER, 3).add(Aspect.AIR, 2));
/* 295 */     ThaumcraftApi.registerObjectTag("cropWheat", (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 5));
/* 296 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151034_e), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 5));
/* 297 */     ThaumcraftApi.registerObjectTag("cropCarrot", (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 5).add(Aspect.SENSES, 5));
/* 298 */     ThaumcraftApi.registerObjectTag("cropPotato", (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 5).add(Aspect.EARTH, 5));
/* 299 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_185164_cV), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 5).add(Aspect.DESIRE, 1));
/* 300 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151168_bH), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.LIFE, 5));
/* 301 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151170_bI), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.DEATH, 5));
/* 302 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150423_aK), (new AspectList()).add(Aspect.PLANT, 10));
/* 303 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150440_ba, 1, 32767), (new AspectList()).add(Aspect.PLANT, 10).remove(Aspect.LIFE, 5));
/* 304 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151127_ba), (new AspectList()).add(Aspect.PLANT, 1));
/* 305 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150360_v, 1, 0), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.TRAP, 5).add(Aspect.VOID, 5));
/* 306 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150360_v, 1, 1), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.TRAP, 5).add(Aspect.WATER, 5));
/* 307 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151102_aT), (new AspectList()).add(Aspect.DESIRE, 1).add(Aspect.ENERGY, 1));
/* 308 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151105_aU), (new AspectList()).add(Aspect.DESIRE, 1).add(Aspect.LIFE, 2));
/* 309 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151158_bO), (new AspectList()).add(Aspect.DESIRE, 1).add(Aspect.LIFE, 2));
/*     */ 
/*     */     
/* 312 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150325_L), (new AspectList()).add(Aspect.BEAST, 15).add(Aspect.CRAFT, 5));
/* 313 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151062_by), (new AspectList()).add(Aspect.MIND, 20));
/* 314 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151057_cb, 1, 32767), (new AspectList()).add(Aspect.MIND, 10).add(Aspect.BEAST, 10));
/* 315 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151138_bX, 1, 32767), (new AspectList()).add(Aspect.METAL, 15).add(Aspect.PROTECT, 10).add(Aspect.BEAST, 5));
/* 316 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151136_bY, 1, 32767), (new AspectList()).add(Aspect.METAL, 10).add(Aspect.PROTECT, 15).add(Aspect.BEAST, 5));
/* 317 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151125_bZ, 1, 32767), (new AspectList()).add(Aspect.CRYSTAL, 15).add(Aspect.PROTECT, 20).add(Aspect.BEAST, 5));
/* 318 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150480_ab, 1, 32767), (new AspectList()).add(Aspect.FIRE, 20));
/* 319 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_189877_df, 1, 32767), (new AspectList()).add(Aspect.FIRE, 10).add(Aspect.EARTH, 5));
/* 320 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_185766_cS, 1, 32767), (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.SENSES, 5).add(Aspect.PLANT, 5));
/* 321 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_185765_cR, 1, 32767), (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.PLANT, 5));
/* 322 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_185161_cS), (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.SENSES, 5).add(Aspect.PLANT, 5));
/* 323 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_185162_cT), (new AspectList()).add(Aspect.ELDRITCH, 5).add(Aspect.SENSES, 5).add(Aspect.PLANT, 4).add(Aspect.FIRE, 1));
/*     */ 
/*     */     
/* 326 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150432_aD, 1, 32767), (new AspectList()).add(Aspect.COLD, 20));
/* 327 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150403_cj, 1, 32767), (new AspectList()).add(Aspect.COLD, 15).add(Aspect.ORDER, 5));
/* 328 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151126_ay, 1, 32767), (new AspectList()).add(Aspect.COLD, 1));
/* 329 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151106_aX, 1, 32767), (new AspectList()).add(Aspect.DESIRE, 1));
/* 330 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151068_bn), (new AspectList()).add(Aspect.WATER, 5).add(Aspect.CRYSTAL, 5));
/* 331 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150478_aa, 1, 32767), (new AspectList()).add(Aspect.LIGHT, 5));
/* 332 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150321_G, 1, 32767), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.BEAST, 1));
/* 333 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151145_ak, 1, 32767), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.TOOL, 5));
/* 334 */     ThaumcraftApi.registerObjectTag("string", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.CRAFT, 1));
/* 335 */     ThaumcraftApi.registerObjectTag("slimeball", (new AspectList()).add(Aspect.WATER, 5).add(Aspect.LIFE, 5).add(Aspect.ALCHEMY, 1));
/* 336 */     ThaumcraftApi.registerObjectTag("leather", (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.PROTECT, 5));
/* 337 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151078_bh, 1, 32767), (new AspectList()).add(Aspect.MAN, 5).add(Aspect.LIFE, 5).add(Aspect.ENTROPY, 5));
/* 338 */     ThaumcraftApi.registerObjectTag("feather", (new AspectList()).add(Aspect.FLIGHT, 5).add(Aspect.AIR, 5));
/* 339 */     ThaumcraftApi.registerObjectTag("bone", (new AspectList()).add(Aspect.DEATH, 5).add(Aspect.LIFE, 5));
/* 340 */     ThaumcraftApi.registerObjectTag("egg", (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.BEAST, 5));
/* 341 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151070_bp, 1, 32767), (new AspectList()).add(Aspect.SENSES, 5).add(Aspect.BEAST, 5).add(Aspect.DEATH, 5));
/* 342 */     ThaumcraftApi.registerObjectTag("gunpowder", (new AspectList()).add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 10).add(Aspect.ALCHEMY, 5));
/*     */     
/* 344 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151115_aP, 1, 32767), (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5).add(Aspect.WATER, 5));
/* 345 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179566_aV, 1, 32767), (new AspectList()).add(Aspect.CRAFT, 1).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5));
/* 346 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151076_bf, 1, 32767), (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5).add(Aspect.AIR, 5));
/* 347 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151077_bg, 1, 32767), (new AspectList()).add(Aspect.CRAFT, 1).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5));
/* 348 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151147_al, 1, 32767), (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5).add(Aspect.EARTH, 5));
/* 349 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151157_am, 1, 32767), (new AspectList()).add(Aspect.CRAFT, 1).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5));
/* 350 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151082_bd, 1, 32767), (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5).add(Aspect.EARTH, 5));
/* 351 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151083_be, 1, 32767), (new AspectList()).add(Aspect.CRAFT, 1).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5));
/* 352 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179561_bm, 1, 32767), (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5).add(Aspect.EARTH, 5));
/* 353 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179557_bn, 1, 32767), (new AspectList()).add(Aspect.CRAFT, 1).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5));
/* 354 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179558_bo, 1, 32767), (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5).add(Aspect.EARTH, 5));
/* 355 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179559_bp, 1, 32767), (new AspectList()).add(Aspect.CRAFT, 1).add(Aspect.BEAST, 5).add(Aspect.LIFE, 5));
/*     */     
/* 357 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179555_bs, 1, 32767), (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.PROTECT, 2));
/* 358 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179556_br, 1, 32767), (new AspectList()).add(Aspect.BEAST, 5).add(Aspect.PROTECT, 5).add(Aspect.MOTION, 10).add(Aspect.ALCHEMY, 5));
/*     */     
/* 360 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151072_bj, 1, 32767), (new AspectList()).add(Aspect.FIRE, 15).add(Aspect.ENERGY, 5));
/* 361 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151141_av, 1, 32767), (new AspectList()).add(Aspect.BEAST, 10).add(Aspect.MOTION, 10).add(Aspect.ORDER, 5));
/* 362 */     ThaumcraftApi.registerObjectTag("enderpearl", (new AspectList()).add(Aspect.ELDRITCH, 10).add(Aspect.MOTION, 15));
/* 363 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151073_bk, 1, 32767), (new AspectList()).add(Aspect.UNDEAD, 5).add(Aspect.SOUL, 10).add(Aspect.ALCHEMY, 10));
/* 364 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 0), (new AspectList()).add(Aspect.DEATH, 10).add(Aspect.SOUL, 10).add(Aspect.UNDEAD, 10));
/* 365 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 1), (new AspectList()).add(Aspect.DEATH, 10).add(Aspect.SOUL, 10).add(Aspect.UNDEAD, 10));
/* 366 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 2), (new AspectList()).add(Aspect.DEATH, 10).add(Aspect.SOUL, 10).add(Aspect.MAN, 10));
/* 367 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 3), (new AspectList()).add(Aspect.DEATH, 10).add(Aspect.SOUL, 10).add(Aspect.MAN, 10));
/* 368 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 4), (new AspectList()).add(Aspect.DEATH, 10).add(Aspect.SOUL, 10).add(Aspect.ENTROPY, 5).add(Aspect.FIRE, 5));
/* 369 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151144_bL, 1, 5), (new AspectList()).add(Aspect.DEATH, 10).add(Aspect.SOUL, 10).add(Aspect.FIRE, 10).add(Aspect.DARKNESS, 10));
/* 370 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_185157_bK), (new AspectList()).add(Aspect.DARKNESS, 10).add(Aspect.ENTROPY, 10).add(Aspect.FIRE, 10).add(Aspect.ALCHEMY, 10));
/* 371 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151066_bu), (new AspectList()).add(Aspect.ALCHEMY, 15));
/* 372 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151071_bq), (new AspectList()).add(Aspect.ALCHEMY, 5));
/* 373 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151065_br), (new AspectList()).add(Aspect.ALCHEMY, 5));
/* 374 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151060_bw), (new AspectList()).add(Aspect.ALCHEMY, 5));
/* 375 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151064_bs), (new AspectList()).add(Aspect.ALCHEMY, 5));
/*     */     
/* 377 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_190929_cY), (new AspectList()).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10).add(Aspect.LIFE, 25).add(Aspect.UNDEAD, 10));
/* 378 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_190930_cZ), (new AspectList()).add(Aspect.PROTECT, 10).add(Aspect.ELDRITCH, 5).add(Aspect.BEAST, 5).add(Aspect.VOID, 5));
/* 379 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190975_dA), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 380 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190988_dw), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 381 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190990_dy), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 382 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190991_dz), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 383 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190981_dp), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 384 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190985_dt), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 385 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190977_dl), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 386 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190978_dm), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 387 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190983_dr), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 388 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190984_ds), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 389 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190986_du), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 390 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190980_do), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 391 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190982_dq), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 392 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190979_dn), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/* 393 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_190989_dx), new AspectList(new ItemStack(Blocks.field_190987_dv)));
/*     */     
/* 395 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151086_cn), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.DESIRE, 15));
/* 396 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151096_cd), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.WATER, 5).add(Aspect.DESIRE, 10));
/* 397 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151093_ce), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.BEAST, 5).add(Aspect.DESIRE, 10));
/* 398 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151091_cg), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.EARTH, 5).add(Aspect.DESIRE, 10));
/* 399 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151092_ch), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.ELDRITCH, 5).add(Aspect.DESIRE, 10));
/* 400 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151089_ci), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.MAN, 5).add(Aspect.DESIRE, 10));
/* 401 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151090_cj), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.CRAFT, 5).add(Aspect.DESIRE, 10));
/* 402 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151087_ck), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.DARKNESS, 5).add(Aspect.DESIRE, 10));
/* 403 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151088_cl), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.ENERGY, 5).add(Aspect.DESIRE, 10));
/* 404 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151085_cm), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.LIFE, 5).add(Aspect.DESIRE, 10));
/* 405 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151094_cf), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.TOOL, 5).add(Aspect.DESIRE, 10));
/* 406 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151084_co), (new AspectList()).add(Aspect.SENSES, 15).add(Aspect.AIR, 5).add(Aspect.TRAP, 5).add(Aspect.DESIRE, 10));
/*     */     
/* 408 */     ThaumcraftApi.registerObjectTag("netherStar", (new AspectList()).add(Aspect.ELDRITCH, 10).add(Aspect.MAGIC, 20).add(Aspect.ORDER, 20).add(Aspect.AURA, 10));
/*     */     
/* 410 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151020_U, 1, 32767), (new AspectList()).add(Aspect.METAL, 42));
/* 411 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151023_V, 1, 32767), (new AspectList()).add(Aspect.METAL, 67));
/* 412 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151022_W, 1, 32767), (new AspectList()).add(Aspect.METAL, 58));
/* 413 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151029_X, 1, 32767), (new AspectList()).add(Aspect.METAL, 33));
/*     */     
/* 415 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151121_aF), (new AspectList()).add(Aspect.MIND, 2));
/*     */     
/* 417 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151134_bR), new AspectList(new ItemStack(Items.field_151122_aG)));
/* 418 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150342_X), (new AspectList()).add(Aspect.MIND, 8));
/* 419 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150380_bt), (new AspectList()).add(Aspect.ELDRITCH, 15).add(Aspect.BEAST, 15).add(Aspect.DARKNESS, 15).add(Aspect.MOTION, 15).add(Aspect.MAGIC, 5));
/* 420 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150427_aO, 1, 32767), (new AspectList()).add(Aspect.FIRE, 10).add(Aspect.MOTION, 20).add(Aspect.MAGIC, 10));
/* 421 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150384_bq, 1, 32767), (new AspectList()).add(Aspect.ELDRITCH, 10).add(Aspect.MOTION, 20).add(Aspect.MAGIC, 10));
/* 422 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150378_br, 1, 32767), (new AspectList()).add(Aspect.ELDRITCH, 10).add(Aspect.ENERGY, 10).add(Aspect.MOTION, 10).add(Aspect.MAGIC, 5));
/* 423 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150474_ac, 1, 32767), (new AspectList()).add(Aspect.BEAST, 20).add(Aspect.MOTION, 20).add(Aspect.UNDEAD, 20).add(Aspect.MAGIC, 20));
/* 424 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179562_cC), (new AspectList()).add(Aspect.WATER, 5).add(Aspect.EARTH, 5));
/* 425 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_179563_cD), (new AspectList()).add(Aspect.WATER, 5).add(Aspect.CRYSTAL, 5).add(Aspect.LIGHT, 5));
/* 426 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_185160_cR), (new AspectList()).add(Aspect.FLIGHT, 20).add(Aspect.MOTION, 15));
/* 427 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_185764_cQ, 1, 32767), (new AspectList()).add(Aspect.FIRE, 1).add(Aspect.LIGHT, 5));
/*     */ 
/*     */     
/* 430 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150486_ae, 1, 32767), (new AspectList()).add(Aspect.VOID, 15));
/* 431 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150447_bR, 1, 32767), (new AspectList()).add(Aspect.TRAP, 10));
/*     */     
/* 433 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151061_bv), (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.MAGIC, 5));
/* 434 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151032_g), (new AspectList()).add(Aspect.AVERSION, 5));
/* 435 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151069_bo), (new AspectList()).add(Aspect.VOID, 5));
/*     */     
/* 437 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151153_ao, 1, 0), (new AspectList()).add(Aspect.MAGIC, 5).add(Aspect.LIFE, 10));
/* 438 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151153_ao, 1, 1), (new AspectList()).add(Aspect.MAGIC, 5).add(Aspect.LIFE, 15).add(Aspect.PROTECT, 15));
/* 439 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151054_z), (new AspectList()).add(Aspect.VOID, 5));
/* 440 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151009_A), (new AspectList()).add(Aspect.LIFE, 5));
/* 441 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151143_au), (new AspectList()).add(Aspect.MOTION, 15));
/* 442 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151139_aw), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 443 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_179572_au), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 444 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_179571_av), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 445 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_179567_at), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 446 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_179570_aq), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 447 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_179569_ar), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 448 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_179568_as), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/*     */     
/* 450 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151124_az), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.MOTION, 15));
/* 451 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_185153_aK), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.MOTION, 15));
/* 452 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_185151_aI), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.MOTION, 15));
/* 453 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_185154_aL), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.MOTION, 15));
/* 454 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_185152_aJ), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.MOTION, 15));
/* 455 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_185150_aH), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.MOTION, 15));
/* 456 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151033_d, 1, 32767), (new AspectList()).add(Aspect.FIRE, 10).add(Aspect.TOOL, 5));
/* 457 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151112_aM, 1, 32767), (new AspectList()).add(Aspect.WATER, 10).add(Aspect.TOOL, 5));
/*     */     
/* 459 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_185159_cQ, 1, 32767), (new AspectList()).add(Aspect.PROTECT, 20));
/* 460 */     for (a = 0; a < 16; a++) {
/* 461 */       ItemStack sis = new ItemStack(Items.field_185159_cQ, 1, 32767);
/* 462 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 463 */       nbttagcompound.func_74768_a("Base", a);
/* 464 */       sis.func_77983_a("BlockEntityTag", nbttagcompound);
/* 465 */       ThaumcraftApi.registerComplexObjectTag(sis, (new AspectList()).merge(Aspect.PROTECT, 20));
/*     */     } 
/*     */     
/* 468 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_185166_h, 1, 32767), (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.MAGIC, 5));
/*     */     
/* 470 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151133_ar), (new AspectList()).add(Aspect.VOID, 5));
/* 471 */     ThaumcraftApi.registerObjectTag(new ItemStack((ForgeModContainer.getInstance()).universalBucket), new AspectList(new ItemStack(Items.field_151133_ar)));
/*     */     
/* 473 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151131_as), (new AspectList(new ItemStack(Items.field_151133_ar))).add(Aspect.WATER, 20));
/* 474 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151129_at), (new AspectList(new ItemStack(Items.field_151133_ar))).add(Aspect.FIRE, 15).add(Aspect.EARTH, 5));
/* 475 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151117_aB), (new AspectList(new ItemStack(Items.field_151133_ar))).add(Aspect.LIFE, 10).add(Aspect.BEAST, 5).add(Aspect.WATER, 5));
/*     */     
/* 477 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151067_bt), (new AspectList()).add(Aspect.CRAFT, 15).add(Aspect.ALCHEMY, 25));
/* 478 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150430_aB), (new AspectList()).add(Aspect.MECHANISM, 5));
/* 479 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150448_aq, 1, 32767), (new AspectList()).add(Aspect.MOTION, 10));
/* 480 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150319_E, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 1));
/* 481 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150318_D, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5).add(Aspect.ENERGY, 1));
/* 482 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150408_cc, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5));
/* 483 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_180387_bt, 1, 32767), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 484 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_180385_bs, 1, 32767), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 485 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_180386_br, 1, 32767), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 486 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_180385_bs, 1, 32767), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 487 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_180391_bp, 1, 32767), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 488 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_180392_bq, 1, 32767), (new AspectList()).add(Aspect.TRAP, 5).add(Aspect.MECHANISM, 5));
/* 489 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150452_aw, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
/* 490 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150456_au, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
/* 491 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150445_bS, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
/* 492 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150443_bT, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5).add(Aspect.SENSES, 5));
/* 493 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150442_at, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5));
/*     */     
/* 495 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150331_J, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 10).add(Aspect.MOTION, 10));
/* 496 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150320_F, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 10).add(Aspect.MOTION, 10));
/* 497 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150421_aI), (new AspectList()).add(Aspect.SENSES, 20).add(Aspect.MECHANISM, 10).add(Aspect.AIR, 15));
/* 498 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150323_B), (new AspectList()).add(Aspect.SENSES, 20).add(Aspect.MECHANISM, 10).add(Aspect.AIR, 15));
/* 499 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150415_aT, 1, 32767), (new AspectList()).add(Aspect.MOTION, 5));
/* 500 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150460_al, 1, 32767), (new AspectList()).add(Aspect.FIRE, 10));
/* 501 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150381_bn), (new AspectList()).add(Aspect.MAGIC, 25).add(Aspect.CRAFT, 15));
/* 502 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150462_ai), (new AspectList()).add(Aspect.CRAFT, 20));
/* 503 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151113_aN), (new AspectList()).add(Aspect.MECHANISM, 10));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 508 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150461_bJ), (new AspectList()).add(Aspect.AURA, 10).add(Aspect.MAGIC, 10).add(Aspect.EXCHANGE, 10));
/* 509 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150471_bO, 1, 32767), (new AspectList()).add(Aspect.MECHANISM, 5));
/* 510 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151146_bM, 1, 32767), (new AspectList()).add(Aspect.MOTION, 5).add(Aspect.DESIRE, 10));
/*     */     
/* 512 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151162_bE), (new AspectList()).add(Aspect.VOID, 5).add(Aspect.PLANT, 5));
/* 513 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151150_bK), (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.ALCHEMY, 5));
/* 514 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150477_bB, 1, 32767), (new AspectList()).merge(Aspect.EXCHANGE, 10).merge(Aspect.MOTION, 10).merge(Aspect.VOID, 20));
/* 515 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151132_bS, 1, 32767), (new AspectList()).merge(Aspect.MECHANISM, 15).merge(Aspect.ORDER, 5).merge(Aspect.SENSES, 5));
/* 516 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Items.field_151107_aW, 1, 32767), (new AspectList()).merge(Aspect.MECHANISM, 15).merge(Aspect.ENERGY, 10));
/* 517 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150438_bZ, 1, 32767), (new AspectList()).merge(Aspect.MECHANISM, 5).merge(Aspect.EXCHANGE, 10).merge(Aspect.VOID, 5));
/* 518 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150409_cd, 1, 32767), (new AspectList()).merge(Aspect.MECHANISM, 5).merge(Aspect.EXCHANGE, 10).merge(Aspect.VOID, 5));
/* 519 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150367_z, 1, 32767), (new AspectList()).merge(Aspect.MECHANISM, 5).merge(Aspect.EXCHANGE, 10).merge(Aspect.VOID, 5));
/* 520 */     ThaumcraftApi.registerObjectTag(new ItemStack(Blocks.field_150479_bC, 1, 32767), (new AspectList()).add(Aspect.SENSES, 5).add(Aspect.MECHANISM, 5).add(Aspect.TRAP, 5));
/* 521 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150473_bD, 1, 32767), (new AspectList()).merge(Aspect.SENSES, 5).merge(Aspect.MECHANISM, 5).merge(Aspect.TRAP, 5));
/* 522 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(Blocks.field_150453_bW, 1, 32767), (new AspectList()).merge(Aspect.SENSES, 10).merge(Aspect.LIGHT, 10).merge(Aspect.MECHANISM, 5));
/*     */     
/* 524 */     ThaumcraftApi.registerComplexObjectTag("gear*", (new AspectList()).add(Aspect.MECHANISM, 5));
/*     */ 
/*     */     
/* 527 */     for (PotionType potiontype : PotionType.field_185176_a) {
/*     */       
/* 529 */       ItemStack stack = PotionUtils.func_185188_a(new ItemStack(Items.field_151068_bn), potiontype);
/* 530 */       ThaumcraftApi.registerObjectTag(stack, getPotionAspects(stack).add(Aspect.WATER, 5));
/* 531 */       ItemStack stack2 = PotionUtils.func_185188_a(new ItemStack(Items.field_185167_i), potiontype);
/* 532 */       ThaumcraftApi.registerObjectTag(stack2, getPotionAspects(stack2).add(Aspect.AVERSION, 5));
/* 533 */       ItemStack stack3 = PotionUtils.func_185188_a(new ItemStack(Items.field_185155_bH), potiontype);
/* 534 */       ThaumcraftApi.registerObjectTag(stack3, getPotionAspects(stack3).add(Aspect.ENERGY, 5));
/* 535 */       ItemStack stack4 = PotionUtils.func_185188_a(new ItemStack(Items.field_185156_bI), potiontype);
/* 536 */       ThaumcraftApi.registerObjectTag(stack4, getPotionAspects(stack4).add(Aspect.TRAP, 5));
/*     */     } 
/*     */     
/* 539 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151100_aR, 1, 0), (new AspectList()).add(Aspect.WATER, 2).add(Aspect.BEAST, 2));
/* 540 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151100_aR, 1, 2), (new AspectList(new ItemStack(Blocks.field_150434_aF))).add(Aspect.WATER, 1).add(Aspect.FIRE, 1));
/* 541 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151100_aR, 1, 3), (new AspectList()).add(Aspect.DESIRE, 2).add(Aspect.ENERGY, 2));
/* 542 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151100_aR, 1, 4), (new AspectList()).add(Aspect.EARTH, 2).add(Aspect.DESIRE, 2));
/* 543 */     ThaumcraftApi.registerObjectTag(new ItemStack(Items.field_151100_aR, 1, 15), (new AspectList()).add(Aspect.LIFE, 2).add(Aspect.DEATH, 1).add(Aspect.PLANT, 1));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 548 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.phial, 1, 0), (new AspectList()).add(Aspect.VOID, 3));
/* 549 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.phial, 1, 1), new AspectList());
/*     */ 
/*     */ 
/*     */     
/* 553 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.grassAmbient), (new AspectList(new ItemStack(Blocks.field_150349_c))).add(Aspect.LIGHT, 5));
/*     */     
/* 555 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(BlocksTC.tableWood), (new AspectList()).add(Aspect.TOOL, 1));
/* 556 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(BlocksTC.tableStone), (new AspectList()).add(Aspect.TOOL, 1));
/* 557 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.arcaneWorkbench), (new AspectList(new ItemStack(Blocks.field_150462_ai))).add(Aspect.MAGIC, 5).add(Aspect.AURA, 5));
/*     */     
/* 559 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.tripleMeatTreat), (new AspectList()).add(Aspect.LIFE, 10).add(Aspect.DESIRE, 10));
/*     */     
/* 561 */     ThaumcraftApi.registerObjectTag(FluidUtil.getFilledBucket(new FluidStack(ConfigBlocks.FluidPure.instance, 1000)), (new AspectList(new ItemStack(Items.field_151133_ar)))
/* 562 */         .add(Aspect.MIND, 15).add(Aspect.ORDER, 15));
/*     */     
/* 564 */     ThaumcraftApi.registerObjectTag(FluidUtil.getFilledBucket(new FluidStack(ConfigBlocks.FluidDeath.instance, 1000)), (new AspectList(new ItemStack(Items.field_151133_ar)))
/* 565 */         .add(Aspect.DEATH, 15).add(Aspect.ENTROPY, 15));
/*     */     
/* 567 */     ThaumcraftApi.registerObjectTag("clusterIron", (new AspectList()).add(Aspect.ORDER, 5).add(Aspect.METAL, 15).add(Aspect.EARTH, 5));
/* 568 */     ThaumcraftApi.registerObjectTag("clusterGold", (new AspectList()).add(Aspect.ORDER, 5).add(Aspect.METAL, 15).add(Aspect.EARTH, 5).add(Aspect.DESIRE, 10));
/* 569 */     ThaumcraftApi.registerObjectTag("clusterCinnabar", (new AspectList()).add(Aspect.ORDER, 5).add(Aspect.METAL, 15).add(Aspect.EARTH, 5).add(Aspect.ALCHEMY, 5).add(Aspect.DEATH, 5));
/* 570 */     ThaumcraftApi.registerObjectTag("clusterQuartz", (new AspectList()).add(Aspect.ORDER, 5).add(Aspect.CRYSTAL, 10));
/* 571 */     ThaumcraftApi.registerObjectTag("oreCinnabar", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.METAL, 10).add(Aspect.ALCHEMY, 5).add(Aspect.DEATH, 5));
/* 572 */     ThaumcraftApi.registerObjectTag("oreAmber", (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.TRAP, 10).add(Aspect.CRYSTAL, 10));
/* 573 */     ThaumcraftApi.registerObjectTag("quicksilver", (new AspectList()).add(Aspect.METAL, 10).add(Aspect.DEATH, 5).add(Aspect.ALCHEMY, 5));
/* 574 */     ThaumcraftApi.registerObjectTag("gemAmber", (new AspectList()).add(Aspect.TRAP, 10).add(Aspect.CRYSTAL, 10));
/* 575 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.nuggets, 1, 10), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5).add(Aspect.METAL, 5));
/*     */     
/* 577 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.crystalAir, 1, 32767), (new AspectList()).add(Aspect.AIR, 15).add(Aspect.CRYSTAL, 10));
/* 578 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.crystalFire, 1, 32767), (new AspectList()).add(Aspect.FIRE, 15).add(Aspect.CRYSTAL, 10));
/* 579 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.crystalWater, 1, 32767), (new AspectList()).add(Aspect.WATER, 15).add(Aspect.CRYSTAL, 10));
/* 580 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.crystalEarth, 1, 32767), (new AspectList()).add(Aspect.EARTH, 15).add(Aspect.CRYSTAL, 10));
/* 581 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.crystalOrder, 1, 32767), (new AspectList()).add(Aspect.ORDER, 15).add(Aspect.CRYSTAL, 10));
/* 582 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.crystalEntropy, 1, 32767), (new AspectList()).add(Aspect.ENTROPY, 15).add(Aspect.CRYSTAL, 10));
/* 583 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.crystalTaint, 1, 32767), (new AspectList()).add(Aspect.FLUX, 15).add(Aspect.CRYSTAL, 10));
/*     */ 
/*     */     
/* 586 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.taintFibre), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.FLUX, 10));
/* 587 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.taintCrust), (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.FLUX, 5));
/* 588 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.taintSoil), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.FLUX, 5));
/* 589 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.taintGeyser), (new AspectList()).add(Aspect.AURA, 5).add(Aspect.WATER, 5).add(Aspect.FLUX, 10));
/* 590 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.taintRock), (new AspectList()).add(Aspect.EARTH, 10).add(Aspect.FLUX, 5));
/* 591 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.taintFeature, 1, 0), (new AspectList()).add(Aspect.AURA, 5).add(Aspect.BEAST, 5).add(Aspect.FLUX, 10));
/* 592 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.taintLog, 1, 0), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.FLUX, 5));
/*     */     
/* 594 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.logGreatwood), (new AspectList()).add(Aspect.PLANT, 20).add(Aspect.LIFE, 5));
/* 595 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.logSilverwood), (new AspectList()).add(Aspect.PLANT, 20).add(Aspect.AURA, 5));
/* 596 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.leafGreatwood), (new AspectList()).add(Aspect.PLANT, 5));
/* 597 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.leafSilverwood), (new AspectList()).add(Aspect.PLANT, 5));
/* 598 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.saplingGreatwood), (new AspectList()).add(Aspect.PLANT, 15).add(Aspect.LIFE, 5));
/* 599 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.saplingSilverwood), (new AspectList()).add(Aspect.PLANT, 15).add(Aspect.AURA, 5));
/* 600 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.shimmerleaf), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AURA, 10).add(Aspect.ENERGY, 5));
/* 601 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.cinderpearl), (new AspectList()).add(Aspect.PLANT, 5).add(Aspect.AURA, 5).add(Aspect.FIRE, 10));
/* 602 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.vishroom), (new AspectList()).add(Aspect.PLANT, 2).add(Aspect.DEATH, 1).add(Aspect.MAGIC, 1).add(Aspect.ENTROPY, 1));
/*     */     
/* 604 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.stoneAncient), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ELDRITCH, 5));
/* 605 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.stoneAncientTile), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ELDRITCH, 5));
/* 606 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.stoneAncientRock), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ELDRITCH, 5));
/* 607 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.stoneEldritchTile), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.ELDRITCH, 5));
/* 608 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.stoneAncientDoorway), (new AspectList()).add(Aspect.METAL, 5).add(Aspect.ELDRITCH, 5).add(Aspect.TRAP, 5));
/* 609 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.stoneAncientGlyphed), (new AspectList()).add(Aspect.METAL, 5).add(Aspect.ELDRITCH, 5).add(Aspect.MIND, 5));
/* 610 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.stonePorous), (new AspectList()).add(Aspect.EARTH, 5).add(Aspect.VOID, 5));
/*     */     
/* 612 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.researchTable, 1, 32767), (new AspectList(new ItemStack(BlocksTC.tableWood))).add(Aspect.MIND, 5));
/*     */     
/* 614 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.brain), (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.MIND, 20).add(Aspect.UNDEAD, 10));
/*     */     
/* 616 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.lootBag, 1, 0), (new AspectList()).add(Aspect.DESIRE, 10));
/* 617 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.lootBag, 1, 1), (new AspectList()).add(Aspect.DESIRE, 20));
/* 618 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.lootBag, 1, 2), (new AspectList()).add(Aspect.DESIRE, 30));
/* 619 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.lootUrnCommon), (new AspectList()).add(Aspect.DESIRE, 10).add(Aspect.EARTH, 2));
/* 620 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.lootUrnUncommon), (new AspectList()).add(Aspect.DESIRE, 20).add(Aspect.EARTH, 2));
/* 621 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.lootUrnRare), (new AspectList()).add(Aspect.DESIRE, 30).add(Aspect.EARTH, 2));
/* 622 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.lootCrateCommon), (new AspectList()).add(Aspect.DESIRE, 10).add(Aspect.PLANT, 2));
/* 623 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.lootCrateUncommon), (new AspectList()).add(Aspect.DESIRE, 20).add(Aspect.PLANT, 2));
/* 624 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.lootCrateRare), (new AspectList()).add(Aspect.DESIRE, 30).add(Aspect.PLANT, 2));
/*     */     
/* 626 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.chunks, 1, 32767), (new AspectList()).add(Aspect.LIFE, 5).add(Aspect.ENTROPY, 1));
/*     */     
/* 628 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.salisMundus), (new AspectList()).add(Aspect.MAGIC, 5).add(Aspect.ENERGY, 5));
/*     */     
/* 630 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.crucible), (new AspectList(new ItemStack(Items.field_151066_bu, 1, 32767))).add(Aspect.CRAFT, 20).add(Aspect.ALCHEMY, 20));
/*     */     
/* 632 */     for (Block ca : BlocksTC.candles.values()) {
/* 633 */       ThaumcraftApi.registerComplexObjectTag(new ItemStack(ca), (new AspectList()).add(Aspect.LIGHT, 5));
/*     */     }
/* 635 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.thaumonomicon, 1, 32767), (new AspectList(new ItemStack(Blocks.field_150342_X))).merge(Aspect.MAGIC, 10));
/*     */     
/* 637 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(BlocksTC.pedestalArcane, 1, 0), (new AspectList()).add(Aspect.MAGIC, 3).add(Aspect.AIR, 3));
/* 638 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(BlocksTC.pedestalAncient, 1, 1), (new AspectList()).add(Aspect.MAGIC, 3).add(Aspect.ELDRITCH, 3));
/* 639 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(BlocksTC.pedestalEldritch, 1, 2), (new AspectList()).add(Aspect.MAGIC, 3).add(Aspect.ELDRITCH, 3));
/*     */     
/* 641 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(ItemsTC.thaumometer, 1, 32767), (new AspectList()).add(Aspect.SENSES, 10).add(Aspect.AURA, 10));
/* 642 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(ItemsTC.goggles, 1, 32767), (new AspectList()).merge(Aspect.SENSES, 10).merge(Aspect.AURA, 10));
/* 643 */     ThaumcraftApi.registerComplexObjectTag(new ItemStack(BlocksTC.arcaneEar), (new AspectList()).add(Aspect.SENSES, 20));
/*     */ 
/*     */     
/* 646 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.amuletVis, 1, 0), (new AspectList()).add(Aspect.AURA, 20).add(Aspect.METAL, 5).add(Aspect.MAGIC, 5));
/* 647 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.baubles, 1, 3), (new AspectList()).add(Aspect.AURA, 5).add(Aspect.METAL, 5).add(Aspect.MAGIC, 20));
/* 648 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonPlateChest, 1, 32767), (new AspectList(new ItemStack(Items.field_151030_Z))).add(Aspect.ELDRITCH, 5));
/* 649 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonPraetorChest, 1, 32767), (new AspectList(new ItemStack(Items.field_151030_Z))).add(Aspect.ELDRITCH, 10));
/* 650 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonRobeChest, 1, 32767), (new AspectList(new ItemStack(Items.field_151027_R))).add(Aspect.MAGIC, 5).add(Aspect.ELDRITCH, 5));
/* 651 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonPlateLegs, 1, 32767), (new AspectList(new ItemStack(Items.field_151165_aa))).add(Aspect.ELDRITCH, 5));
/* 652 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonPraetorLegs, 1, 32767), (new AspectList(new ItemStack(Items.field_151165_aa))).add(Aspect.ELDRITCH, 10));
/* 653 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonRobeLegs, 1, 32767), (new AspectList(new ItemStack(Items.field_151026_S))).add(Aspect.MAGIC, 5).add(Aspect.ELDRITCH, 5));
/* 654 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonPlateHelm, 1, 32767), (new AspectList(new ItemStack(Items.field_151028_Y))).add(Aspect.ELDRITCH, 5));
/* 655 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonPraetorHelm, 1, 32767), (new AspectList(new ItemStack(Items.field_151028_Y))).add(Aspect.ELDRITCH, 10));
/* 656 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonRobeHelm, 1, 32767), (new AspectList(new ItemStack(Items.field_151024_Q))).add(Aspect.MAGIC, 5).add(Aspect.ELDRITCH, 5));
/* 657 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonBoots, 1, 32767), (new AspectList(new ItemStack(Items.field_151167_ab))).add(Aspect.ELDRITCH, 5));
/* 658 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.crimsonBlade, 1, 32767), (new AspectList(new ItemStack(Items.field_151040_l))).add(Aspect.ELDRITCH, 10).add(Aspect.DEATH, 10));
/* 659 */     for (Block ca : BlocksTC.banners.values()) {
/* 660 */       ThaumcraftApi.registerComplexObjectTag(new ItemStack(ca), (new AspectList()).add(Aspect.ELDRITCH, 5));
/*     */     }
/*     */     
/* 663 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.eldritchEye), (new AspectList()).add(Aspect.ELDRITCH, 15).add(Aspect.AURA, 15).add(Aspect.SENSES, 15).add(Aspect.SOUL, 15));
/* 664 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.curio, 1, 0), (new AspectList()).add(Aspect.MIND, 15).add(Aspect.MAGIC, 15));
/* 665 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.curio, 1, 1), (new AspectList()).add(Aspect.MIND, 15).add(Aspect.BEAST, 15));
/* 666 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.curio, 1, 2), (new AspectList()).add(Aspect.MIND, 15).add(Aspect.DEATH, 15));
/* 667 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.curio, 1, 3), (new AspectList()).add(Aspect.MIND, 15).add(Aspect.ELDRITCH, 15));
/* 668 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.curio, 1, 4), (new AspectList()).add(Aspect.MIND, 30));
/* 669 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.curio, 1, 5), (new AspectList()).add(Aspect.MIND, 15).add(Aspect.FLUX, 15));
/* 670 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.curio, 1, 6), (new AspectList()).add(Aspect.MIND, 15).add(Aspect.ELDRITCH, 5).add(Aspect.SOUL, 5).add(Aspect.MAGIC, 5));
/* 671 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.runedTablet), (new AspectList()).add(Aspect.TRAP, 15).add(Aspect.MIND, 15).add(Aspect.MECHANISM, 15));
/*     */     
/* 673 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.celestialNotes, 1, 32767), (new AspectList()).add(Aspect.MIND, 5).add(Aspect.DARKNESS, 5).add(Aspect.LIGHT, 5));
/*     */     
/* 675 */     ThaumcraftApi.registerObjectTag(new ItemStack(ItemsTC.primordialPearl, 1, 32767), (new AspectList())
/* 676 */         .add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10));
/*     */     
/* 678 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.eldritch, 1, 0), (new AspectList()).add(Aspect.VOID, 10).add(Aspect.ELDRITCH, 10));
/* 679 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.eldritch, 1, 1), (new AspectList()).add(Aspect.VOID, 10).add(Aspect.ELDRITCH, 10));
/* 680 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.eldritch, 1, 2), (new AspectList()).add(Aspect.VOID, 10).add(Aspect.ELDRITCH, 10));
/* 681 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.eldritch, 1, 3), (new AspectList()).add(Aspect.VOID, 10).add(Aspect.ELDRITCH, 10));
/* 682 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.eldritch, 1, 4), (new AspectList()).add(Aspect.VOID, 10).add(Aspect.ELDRITCH, 10).add(Aspect.MECHANISM, 10));
/* 683 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.eldritch, 1, 5), (new AspectList()).add(Aspect.VOID, 10).add(Aspect.ELDRITCH, 10));
/* 684 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.eldritch, 1, 6), (new AspectList()).add(Aspect.VOID, 10).add(Aspect.ELDRITCH, 10).add(Aspect.MOTION, 15));
/* 685 */     ThaumcraftApi.registerObjectTag(new ItemStack(BlocksTC.eldritch, 1, 7), (new AspectList()).add(Aspect.VOID, 10).add(Aspect.ELDRITCH, 10).add(Aspect.BEAST, 15));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AspectList getPotionAspects(ItemStack itemstack) {
/* 692 */     AspectList tmp = new AspectList();
/* 693 */     boolean didit = false;
/* 694 */     PotionType potion = PotionUtils.func_185191_c(itemstack);
/* 695 */     if (potion != PotionTypes.field_185230_b) {
/* 696 */       HashSet<ItemStack> hs = new HashSet<ItemStack>();
/* 697 */       ThaumcraftCraftingManager.getPotionReagentsRecursive(potion, hs);
/* 698 */       for (ItemStack his : hs) {
/* 699 */         AspectList tmp2 = ThaumcraftCraftingManager.getObjectTags(his);
/* 700 */         for (Aspect a : tmp2.aspects.keySet()) {
/* 701 */           tmp.add(a, tmp2.getAmount(a));
/*     */         }
/* 703 */         didit = true;
/* 704 */         tmp.add(Aspect.ALCHEMY, 3);
/*     */       } 
/*     */       try {
/* 707 */         for (Aspect a : (Aspect[])tmp.aspects.keySet().toArray(new Aspect[0])) {
/* 708 */           int amt = tmp.getAmount(a);
/* 709 */           tmp.remove(a, (int)(amt * 0.66D));
/*     */         } 
/* 711 */       } catch (Exception exception) {}
/* 712 */       if (!didit) {
/* 713 */         tmp.add(Aspect.MAGIC, 5);
/* 714 */         tmp.add(Aspect.ALCHEMY, 5);
/*     */       } 
/*     */     } else {
/* 717 */       tmp.add(Aspect.WATER, 5);
/*     */     } 
/* 719 */     return tmp;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\config\ConfigAspects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */