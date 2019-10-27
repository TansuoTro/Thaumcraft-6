/*     */ package thaumcraft.common.world.aura;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.common.lib.utils.PosXY;
/*     */ import thaumcraft.common.world.biomes.BiomeHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AuraHandler
/*     */ {
/*     */   public static final int AURA_CEILING = 500;
/*  24 */   static ConcurrentHashMap<Integer, AuraWorld> auras = new ConcurrentHashMap();
/*  25 */   public static ConcurrentHashMap<Integer, CopyOnWriteArrayList<ChunkPos>> dirtyChunks = new ConcurrentHashMap();
/*  26 */   public static ConcurrentHashMap<Integer, BlockPos> riftTrigger = new ConcurrentHashMap();
/*     */ 
/*     */   
/*  29 */   public static AuraWorld getAuraWorld(int dim) { return (AuraWorld)auras.get(Integer.valueOf(dim)); }
/*     */ 
/*     */   
/*     */   public static AuraChunk getAuraChunk(int dim, int x, int y) {
/*  33 */     if (auras.containsKey(Integer.valueOf(dim))) {
/*  34 */       return ((AuraWorld)auras.get(Integer.valueOf(dim))).getAuraChunkAt(x, y);
/*     */     }
/*  36 */     addAuraWorld(dim);
/*  37 */     if (auras.containsKey(Integer.valueOf(dim))) return ((AuraWorld)auras.get(Integer.valueOf(dim))).getAuraChunkAt(x, y);
/*     */     
/*  39 */     return null;
/*     */   }
/*     */   
/*     */   public static void addAuraWorld(int dim) {
/*  43 */     if (!auras.containsKey(Integer.valueOf(dim))) {
/*  44 */       auras.put(Integer.valueOf(dim), new AuraWorld(dim));
/*  45 */       Thaumcraft.log.info("Creating aura cache for world " + dim);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void removeAuraWorld(int dim) {
/*  50 */     auras.remove(Integer.valueOf(dim));
/*  51 */     Thaumcraft.log.info("Removing aura cache for world " + dim);
/*     */   }
/*     */   
/*     */   public static void addAuraChunk(int dim, Chunk chunk, short base, float vis, float flux) {
/*  55 */     AuraWorld aw = (AuraWorld)auras.get(Integer.valueOf(dim));
/*  56 */     if (aw == null) {
/*  57 */       aw = new AuraWorld(dim);
/*     */     }
/*     */     
/*  60 */     aw.getAuraChunks().put(new PosXY(chunk.field_76635_g, chunk.field_76647_h), new AuraChunk(chunk, base, vis, flux));
/*     */     
/*  62 */     auras.put(Integer.valueOf(dim), aw);
/*     */   }
/*     */   
/*     */   public static void removeAuraChunk(int dim, int x, int y) {
/*  66 */     AuraWorld aw = (AuraWorld)auras.get(Integer.valueOf(dim));
/*  67 */     if (aw != null) {
/*  68 */       aw.getAuraChunks().remove(new PosXY(x, y));
/*     */     }
/*     */   }
/*     */   
/*     */   public static float getTotalAura(World world, BlockPos pos) {
/*  73 */     AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/*  74 */     return (ac != null) ? (ac.getVis() + ac.getFlux()) : 0.0F;
/*     */   }
/*     */   
/*     */   public static float getFluxSaturation(World world, BlockPos pos) {
/*  78 */     AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/*  79 */     return (ac != null) ? (ac.getFlux() / ac.getBase()) : 0.0F;
/*     */   }
/*     */   
/*     */   public static float getVis(World world, BlockPos pos) {
/*  83 */     AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/*  84 */     return (ac != null) ? ac.getVis() : 0.0F;
/*     */   }
/*     */   
/*     */   public static float getFlux(World world, BlockPos pos) {
/*  88 */     AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/*  89 */     return (ac != null) ? ac.getFlux() : 0.0F;
/*     */   }
/*     */   
/*     */   public static int getAuraBase(World world, BlockPos pos) {
/*  93 */     AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/*  94 */     return (ac != null) ? ac.getBase() : 0;
/*     */   }
/*     */   
/*     */   public static boolean shouldPreserveAura(World world, EntityPlayer player, BlockPos pos) {
/*  98 */     return ((player == null || ThaumcraftCapabilities.getKnowledge(player).isResearchComplete("AURAPRESERVE")) && (
/*  99 */       getVis(world, pos) / getAuraBase(world, pos)) < 0.1D);
/*     */   }
/*     */   
/*     */   public static void addVis(World world, BlockPos pos, float amount) {
/* 103 */     if (amount < 0.0F)
/*     */       return;  try {
/* 105 */       AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/* 106 */       modifyVisInChunk(ac, amount, true);
/* 107 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   public static void addFlux(World world, BlockPos pos, float amount) {
/* 111 */     if (amount < 0.0F)
/*     */       return;  try {
/* 113 */       AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/* 114 */       modifyFluxInChunk(ac, amount, true);
/* 115 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public static float drainVis(World world, BlockPos pos, float amount, boolean simulate) {
/* 120 */     boolean didit = false;
/*     */     try {
/* 122 */       AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/* 123 */       if (amount > ac.getVis()) {
/* 124 */         amount = ac.getVis();
/*     */       }
/* 126 */       didit = modifyVisInChunk(ac, -amount, !simulate);
/* 127 */     } catch (Exception exception) {}
/* 128 */     return didit ? amount : 0.0F;
/*     */   }
/*     */   
/*     */   public static float drainFlux(World world, BlockPos pos, float amount, boolean simulate) {
/* 132 */     boolean didit = false;
/*     */     try {
/* 134 */       AuraChunk ac = getAuraChunk(world.field_73011_w.getDimension(), pos.func_177958_n() >> 4, pos.func_177952_p() >> 4);
/* 135 */       if (amount > ac.getFlux()) {
/* 136 */         amount = ac.getFlux();
/*     */       }
/* 138 */       didit = modifyFluxInChunk(ac, -amount, !simulate);
/* 139 */     } catch (Exception exception) {}
/* 140 */     return didit ? amount : 0.0F;
/*     */   }
/*     */   
/*     */   public static boolean modifyVisInChunk(AuraChunk ac, float amount, boolean doit) {
/* 144 */     if (ac != null) {
/* 145 */       if (doit) ac.setVis(Math.max(0.0F, ac.getVis() + amount)); 
/* 146 */       return true;
/*     */     } 
/* 148 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean modifyFluxInChunk(AuraChunk ac, float amount, boolean doit) {
/* 152 */     if (ac != null) {
/* 153 */       if (doit) ac.setFlux(Math.max(0.0F, ac.getFlux() + amount)); 
/* 154 */       return true;
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */   
/*     */   public static void generateAura(Chunk chunk, Random rand) {
/* 160 */     Biome bgb = chunk.func_177412_p().func_180494_b(new BlockPos(chunk.field_76635_g * 16 + 8, 50, chunk.field_76647_h * 16 + 8));
/*     */     
/* 162 */     if (BiomeHandler.getBiomeBlacklist(Biome.func_185362_a(bgb)) != -1)
/*     */       return; 
/* 164 */     float life = BiomeHandler.getBiomeAuraModifier(bgb);
/*     */     
/* 166 */     for (int a = 0; a < 4; a++) {
/* 167 */       EnumFacing dir = EnumFacing.func_176731_b(a);
/* 168 */       Biome bgb2 = chunk.func_177412_p().func_180494_b(new BlockPos((chunk.field_76635_g + dir
/*     */             
/* 170 */             .func_82601_c()) * 16 + 8, 50, (chunk.field_76647_h + dir
/*     */             
/* 172 */             .func_82599_e()) * 16 + 8));
/* 173 */       life += BiomeHandler.getBiomeAuraModifier(bgb2);
/*     */     } 
/*     */     
/* 176 */     life /= 5.0F;
/*     */     
/* 178 */     float noise = (float)(1.0D + rand.nextGaussian() * 0.10000000149011612D);
/* 179 */     short base = (short)(int)(life * 500.0F * noise);
/* 180 */     base = (short)MathHelper.func_76125_a(base, 0, 500);
/* 181 */     addAuraChunk((chunk.func_177412_p()).field_73011_w.getDimension(), chunk, base, base, 0.0F);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\world\aura\AuraHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */