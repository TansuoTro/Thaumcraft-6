/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketSealToClient;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ public class SealHandler
/*     */ {
/*  27 */   public static LinkedHashMap<String, ISeal> types = new LinkedHashMap();
/*  28 */   private static int lastID = 0;
/*     */   
/*     */   public static void registerSeal(ISeal seal) {
/*  31 */     if (types.containsKey(seal.getKey())) {
/*  32 */       Thaumcraft.log.error("Attempting to register Seal [" + seal.getKey() + "] twice. Ignoring.");
/*     */     } else {
/*  34 */       types.put(seal.getKey(), seal);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  39 */   public static String[] getRegisteredSeals() { return (String[])types.keySet().toArray(new String[0]); }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static ISeal getSeal(String key) { return (ISeal)types.get(key); }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public static ConcurrentHashMap<Integer, ConcurrentHashMap<SealPos, SealEntity>> sealEntities = new ConcurrentHashMap();
/*     */ 
/*     */   
/*     */   public static CopyOnWriteArrayList<SealEntity> getSealsInRange(World world, BlockPos source, int range) {
/*  51 */     CopyOnWriteArrayList<SealEntity> out = new CopyOnWriteArrayList<SealEntity>();
/*  52 */     ConcurrentHashMap<SealPos, SealEntity> list = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  53 */     if (list != null && list.size() > 0)
/*  54 */       for (SealEntity se : list.values()) {
/*  55 */         if (se.getSeal() != null && se.getSealPos() != null && 
/*  56 */           se.sealPos.pos.func_177951_i(source) <= (range * range)) {
/*  57 */           out.add(se);
/*     */         }
/*     */       }  
/*  60 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public static CopyOnWriteArrayList<SealEntity> getSealsInChunk(World world, ChunkPos chunk) {
/*  65 */     CopyOnWriteArrayList<SealEntity> out = new CopyOnWriteArrayList<SealEntity>();
/*  66 */     ConcurrentHashMap<SealPos, SealEntity> list = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  67 */     if (list != null && list.size() > 0)
/*  68 */       for (SealEntity se : list.values()) {
/*  69 */         if (se.getSeal() == null || se.getSealPos() == null)
/*  70 */           continue;  ChunkPos cc = new ChunkPos(se.sealPos.pos);
/*  71 */         if (cc.equals(chunk)) {
/*  72 */           out.add(se);
/*     */         }
/*     */       }  
/*  75 */     return out;
/*     */   }
/*     */   
/*     */   public static void removeSealEntity(World world, SealPos pos, boolean quiet) {
/*  79 */     if (!sealEntities.containsKey(Integer.valueOf(world.field_73011_w.getDimension()))) {
/*  80 */       sealEntities.put(Integer.valueOf(world.field_73011_w.getDimension()), new ConcurrentHashMap());
/*     */     }
/*  82 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.getDimension()));
/*  83 */     if (se != null) {
/*     */       
/*  85 */       SealEntity seal = (SealEntity)se.remove(pos);
/*     */       
/*     */       try {
/*  88 */         if (!world.field_72995_K && seal != null && seal.seal != null)
/*  89 */           seal.seal.onRemoval(world, pos.pos, pos.face); 
/*  90 */         if (!quiet && seal != null && !world.field_72995_K) {
/*  91 */           String[] rs = getRegisteredSeals();
/*  92 */           int indx = 1;
/*  93 */           for (String s : rs) {
/*  94 */             if (s.equals(seal.getSeal().getKey())) {
/*  95 */               world.func_72838_d(new EntityItem(world, pos.pos
/*     */                     
/*  97 */                     .func_177958_n() + 0.5D + (pos.face.func_82601_c() / 1.7F), pos.pos
/*  98 */                     .func_177956_o() + 0.5D + (pos.face.func_96559_d() / 1.7F), pos.pos
/*  99 */                     .func_177952_p() + 0.5D + (pos.face.func_82599_e() / 1.7F), new ItemStack(ItemsTC.seals, 1, indx)));
/*     */               
/*     */               break;
/*     */             } 
/* 103 */             indx++;
/*     */           } 
/*     */         } 
/* 106 */       } catch (Exception e) {
/* 107 */         Thaumcraft.log.warn("Removing invalid seal at " + pos.pos);
/*     */       } 
/*     */       
/* 110 */       ConcurrentHashMap<Integer, Task> ts = TaskHandler.getTasks(world.field_73011_w.getDimension());
/* 111 */       for (Task task : ts.values()) {
/* 112 */         if (task.getSealPos() != null && task.getSealPos().equals(pos)) {
/* 113 */           task.setSuspended(true);
/*     */         }
/*     */       } 
/*     */       
/* 117 */       if (!world.field_72995_K) PacketHandler.INSTANCE.sendToDimension(new PacketSealToClient(new SealEntity(world, pos, null)), world.field_73011_w.getDimension()); 
/* 118 */       if (!quiet) markChunkAsDirty(world.field_73011_w.getDimension(), pos.pos);
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static ISealEntity getSealEntity(int dim, SealPos pos) {
/* 124 */     if (!sealEntities.containsKey(Integer.valueOf(dim)))
/* 125 */       sealEntities.put(Integer.valueOf(dim), new ConcurrentHashMap()); 
/* 126 */     if (pos == null) return null; 
/* 127 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(dim));
/* 128 */     if (se != null) {
/* 129 */       return (ISealEntity)se.get(pos);
/*     */     }
/*     */     
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean addSealEntity(World world, BlockPos pos, EnumFacing face, ISeal seal, EntityPlayer player) {
/* 136 */     if (!sealEntities.containsKey(Integer.valueOf(world.field_73011_w.getDimension()))) {
/* 137 */       sealEntities.put(Integer.valueOf(world.field_73011_w.getDimension()), new ConcurrentHashMap());
/*     */     }
/* 139 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.getDimension()));
/* 140 */     SealPos sp = new SealPos(pos, face);
/*     */     
/* 142 */     if (se.containsKey(sp)) {
/* 143 */       return false;
/*     */     }
/*     */     
/* 146 */     SealEntity sealent = new SealEntity(world, sp, seal);
/* 147 */     sealent.setOwner(player.func_110124_au().toString());
/* 148 */     se.put(sp, sealent);
/*     */     
/* 150 */     if (!world.field_72995_K) {
/* 151 */       sealent.syncToClient(world);
/* 152 */       markChunkAsDirty(world.field_73011_w.getDimension(), pos);
/*     */     } 
/*     */     
/* 155 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean addSealEntity(World world, SealEntity seal) {
/* 159 */     if (world == null || sealEntities == null) return false; 
/* 160 */     if (!sealEntities.containsKey(Integer.valueOf(world.field_73011_w.getDimension()))) {
/* 161 */       sealEntities.put(Integer.valueOf(world.field_73011_w.getDimension()), new ConcurrentHashMap());
/*     */     }
/* 163 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.getDimension()));
/* 164 */     if (se.containsKey(seal.getSealPos())) {
/* 165 */       return false;
/*     */     }
/*     */     
/* 168 */     se.put(seal.getSealPos(), seal);
/*     */     
/* 170 */     if (!world.field_72995_K) {
/* 171 */       seal.syncToClient(world);
/* 172 */       markChunkAsDirty(world.field_73011_w.getDimension(), (seal.getSealPos()).pos);
/*     */     } 
/* 174 */     return true;
/*     */   }
/*     */   
/* 177 */   static int count = 0;
/*     */   public static void tickSealEntities(World world) {
/* 179 */     if (!sealEntities.containsKey(Integer.valueOf(world.field_73011_w.getDimension()))) {
/* 180 */       sealEntities.put(Integer.valueOf(world.field_73011_w.getDimension()), new ConcurrentHashMap());
/*     */     }
/* 182 */     ConcurrentHashMap<SealPos, SealEntity> se = (ConcurrentHashMap)sealEntities.get(Integer.valueOf(world.field_73011_w.getDimension()));
/* 183 */     count++;
/* 184 */     for (SealEntity sealEntity : se.values()) {
/* 185 */       if (world.func_175667_e(sealEntity.sealPos.pos)) {
/*     */         try {
/* 187 */           boolean tick = true;
/* 188 */           if (count % 20 == 0 && 
/* 189 */             !sealEntity.seal.canPlaceAt(world, sealEntity.sealPos.pos, sealEntity.sealPos.face)) {
/* 190 */             removeSealEntity(world, sealEntity.sealPos, false);
/* 191 */             tick = false;
/*     */           } 
/*     */           
/* 194 */           if (tick) sealEntity.tickSealEntity(world); 
/* 195 */         } catch (Exception e) {
/* 196 */           removeSealEntity(world, sealEntity.sealPos, false);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void markChunkAsDirty(int dim, BlockPos bp) {
/* 203 */     ChunkPos pos = new ChunkPos(bp);
/* 204 */     if (!AuraHandler.dirtyChunks.containsKey(Integer.valueOf(dim)))
/* 205 */       AuraHandler.dirtyChunks.put(Integer.valueOf(dim), new CopyOnWriteArrayList()); 
/* 206 */     CopyOnWriteArrayList<ChunkPos> dc = (CopyOnWriteArrayList)AuraHandler.dirtyChunks.get(Integer.valueOf(dim));
/* 207 */     if (!dc.contains(pos))
/* 208 */       dc.add(pos); 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */