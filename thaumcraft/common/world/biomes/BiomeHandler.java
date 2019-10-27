/*     */ package thaumcraft.common.world.biomes;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.common.BiomeDictionary.Type;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BiomeHandler
/*     */ {
/*     */   public static Biome EERIE;
/*     */   public static Biome MAGICAL_FOREST;
/*     */   public static Biome ELDRITCH;
/*  23 */   public static HashMap<BiomeDictionary.Type, List> biomeInfo = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   public static void registerBiomeInfo(BiomeDictionary.Type type, float auraLevel, Aspect tag, boolean greatwood, float greatwoodchance) { biomeInfo.put(type, Arrays.asList(new Object[] { Float.valueOf(auraLevel), tag, Boolean.valueOf(greatwood), Float.valueOf(greatwoodchance) })); }
/*     */ 
/*     */   
/*     */   public static float getBiomeAuraModifier(Biome biome) {
/*     */     try {
/*  33 */       Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
/*  34 */       float average = 0.0F;
/*  35 */       int count = 0;
/*  36 */       for (BiomeDictionary.Type type : types) {
/*  37 */         average += ((Float)((List)biomeInfo.get(type)).get(0)).floatValue();
/*  38 */         count++;
/*     */       } 
/*  40 */       return average / count;
/*  41 */     } catch (Exception exception) {
/*  42 */       return 0.5F;
/*     */     } 
/*     */   }
/*     */   public static Aspect getRandomBiomeTag(int biomeId, Random random) {
/*     */     try {
/*  47 */       Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(Biome.func_150568_d(biomeId));
/*  48 */       BiomeDictionary.Type type = (Type[])types.toArray(new BiomeDictionary.Type[0])[random.nextInt(types.size())];
/*  49 */       return (Aspect)((List)biomeInfo.get(type)).get(1);
/*  50 */     } catch (Exception exception) {
/*  51 */       return null;
/*     */     } 
/*     */   }
/*     */   public static float getBiomeSupportsGreatwood(int biomeId) {
/*     */     try {
/*  56 */       Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(Biome.func_150568_d(biomeId));
/*  57 */       for (BiomeDictionary.Type type : types) {
/*  58 */         if (((Boolean)((List)biomeInfo.get(type)).get(2)).booleanValue()) return ((Float)((List)biomeInfo.get(type)).get(3)).floatValue(); 
/*     */       } 
/*  60 */     } catch (Exception exception) {}
/*  61 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*  65 */   public static Collection<Aspect> c = Aspect.aspects.values();
/*  66 */   public static ArrayList<Aspect> basicAspects = new ArrayList();
/*  67 */   public static ArrayList<Aspect> complexAspects = new ArrayList();
/*  68 */   public static HashMap<Integer, Integer> dimensionBlacklist = new HashMap();
/*  69 */   public static HashMap<Integer, Integer> biomeBlacklist = new HashMap();
/*     */   
/*     */   public static int getFirstFreeBiomeSlot(int old) {
/*  72 */     for (int a = 0; a < Biome.field_185377_q.func_148742_b().size() * 2; a++) {
/*  73 */       if (Biome.func_150568_d(a) == null) {
/*  74 */         Thaumcraft.log.warn("Biome slot " + old + " already occupied. Using first free biome slot at " + a);
/*  75 */         return a;
/*     */       } 
/*     */     } 
/*  78 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*  82 */   public static void addDimBlacklist(int dim, int level) { dimensionBlacklist.put(Integer.valueOf(dim), Integer.valueOf(level)); }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public static int getDimBlacklist(int dim) { return !dimensionBlacklist.containsKey(Integer.valueOf(dim)) ? -1 : ((Integer)dimensionBlacklist.get(Integer.valueOf(dim))).intValue(); }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static void addBiomeBlacklist(int biome, int level) { biomeBlacklist.put(Integer.valueOf(biome), Integer.valueOf(level)); }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public static int getBiomeBlacklist(int biome) { return !biomeBlacklist.containsKey(Integer.valueOf(biome)) ? -1 : ((Integer)biomeBlacklist.get(Integer.valueOf(biome))).intValue(); }
/*     */ 
/*     */   
/*     */   public static void registerBiomes() {
/*  98 */     BiomeDictionary.addTypes(EERIE, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY });
/*  99 */     BiomeDictionary.addTypes(ELDRITCH, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.END });
/* 100 */     BiomeDictionary.addTypes(MAGICAL_FOREST, new BiomeDictionary.Type[] { BiomeDictionary.Type.MAGICAL, BiomeDictionary.Type.FOREST });
/*     */ 
/*     */     
/* 103 */     registerBiomeInfo(BiomeDictionary.Type.WATER, 0.33F, Aspect.WATER, false, 0.0F);
/* 104 */     registerBiomeInfo(BiomeDictionary.Type.OCEAN, 0.33F, Aspect.WATER, false, 0.0F);
/* 105 */     registerBiomeInfo(BiomeDictionary.Type.RIVER, 0.4F, Aspect.WATER, false, 0.0F);
/* 106 */     registerBiomeInfo(BiomeDictionary.Type.WET, 0.4F, Aspect.WATER, false, 0.0F);
/* 107 */     registerBiomeInfo(BiomeDictionary.Type.LUSH, 0.5F, Aspect.WATER, true, 0.5F);
/*     */     
/* 109 */     registerBiomeInfo(BiomeDictionary.Type.HOT, 0.33F, Aspect.FIRE, false, 0.0F);
/* 110 */     registerBiomeInfo(BiomeDictionary.Type.DRY, 0.25F, Aspect.FIRE, false, 0.0F);
/* 111 */     registerBiomeInfo(BiomeDictionary.Type.NETHER, 0.125F, Aspect.FIRE, false, 0.0F);
/* 112 */     registerBiomeInfo(BiomeDictionary.Type.MESA, 0.33F, Aspect.FIRE, false, 0.0F);
/* 113 */     registerBiomeInfo(BiomeDictionary.Type.SPOOKY, 0.5F, Aspect.FIRE, false, 0.0F);
/*     */     
/* 115 */     registerBiomeInfo(BiomeDictionary.Type.DENSE, 0.4F, Aspect.ORDER, false, 0.0F);
/* 116 */     registerBiomeInfo(BiomeDictionary.Type.SNOWY, 0.25F, Aspect.ORDER, false, 0.0F);
/* 117 */     registerBiomeInfo(BiomeDictionary.Type.COLD, 0.25F, Aspect.ORDER, false, 0.0F);
/* 118 */     registerBiomeInfo(BiomeDictionary.Type.MUSHROOM, 0.75F, Aspect.ORDER, false, 0.0F);
/* 119 */     registerBiomeInfo(BiomeDictionary.Type.MAGICAL, 0.75F, Aspect.ORDER, true, 1.0F);
/*     */     
/* 121 */     registerBiomeInfo(BiomeDictionary.Type.CONIFEROUS, 0.33F, Aspect.EARTH, true, 0.2F);
/* 122 */     registerBiomeInfo(BiomeDictionary.Type.FOREST, 0.5F, Aspect.EARTH, true, 1.0F);
/* 123 */     registerBiomeInfo(BiomeDictionary.Type.SANDY, 0.25F, Aspect.EARTH, false, 0.0F);
/* 124 */     registerBiomeInfo(BiomeDictionary.Type.BEACH, 0.3F, Aspect.EARTH, false, 0.0F);
/* 125 */     registerBiomeInfo(BiomeDictionary.Type.JUNGLE, 0.6F, Aspect.EARTH, false, 0.0F);
/*     */     
/* 127 */     registerBiomeInfo(BiomeDictionary.Type.SAVANNA, 0.25F, Aspect.AIR, true, 0.2F);
/* 128 */     registerBiomeInfo(BiomeDictionary.Type.MOUNTAIN, 0.3F, Aspect.AIR, false, 0.0F);
/* 129 */     registerBiomeInfo(BiomeDictionary.Type.HILLS, 0.33F, Aspect.AIR, false, 0.0F);
/* 130 */     registerBiomeInfo(BiomeDictionary.Type.PLAINS, 0.3F, Aspect.AIR, true, 0.2F);
/* 131 */     registerBiomeInfo(BiomeDictionary.Type.END, 0.125F, Aspect.AIR, false, 0.0F);
/*     */     
/* 133 */     registerBiomeInfo(BiomeDictionary.Type.DRY, 0.125F, Aspect.ENTROPY, false, 0.0F);
/* 134 */     registerBiomeInfo(BiomeDictionary.Type.SPARSE, 0.2F, Aspect.ENTROPY, false, 0.0F);
/* 135 */     registerBiomeInfo(BiomeDictionary.Type.SWAMP, 0.5F, Aspect.ENTROPY, true, 0.2F);
/* 136 */     registerBiomeInfo(BiomeDictionary.Type.WASTELAND, 0.125F, Aspect.ENTROPY, false, 0.0F);
/* 137 */     registerBiomeInfo(BiomeDictionary.Type.DEAD, 0.1F, Aspect.ENTROPY, false, 0.0F);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\biomes\BiomeHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */