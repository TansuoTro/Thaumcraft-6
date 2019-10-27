/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.IAspectSource;
/*     */ import thaumcraft.api.internal.WorldCoordinates;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EssentiaHandler
/*     */ {
/*     */   static final int DELAY = 10000;
/*  23 */   private static HashMap<WorldCoordinates, ArrayList<WorldCoordinates>> sources = new HashMap();
/*  24 */   private static HashMap<WorldCoordinates, Long> sourcesDelay = new HashMap();
/*     */ 
/*     */   
/*  27 */   public static boolean drainEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, int ext) { return drainEssentia(tile, aspect, direction, range, false, ext); }
/*     */ 
/*     */   
/*     */   public static boolean drainEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror, int ext) {
/*  31 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), (tile.func_145831_w()).field_73011_w.getDimension());
/*  32 */     if (!sources.containsKey(tileLoc)) {
/*  33 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/*  34 */       if (sources.containsKey(tileLoc))
/*  35 */         return drainEssentia(tile, aspect, direction, range, ignoreMirror, ext); 
/*  36 */       return false;
/*     */     } 
/*  38 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/*  39 */     for (WorldCoordinates source : es) {
/*  40 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/*  41 */       if (sourceTile != null && sourceTile instanceof IAspectSource) {
/*  42 */         IAspectSource as = (IAspectSource)sourceTile;
/*  43 */         if (as.isBlocked() || (ignoreMirror && sourceTile instanceof TileMirrorEssentia))
/*     */           continue; 
/*  45 */         if (as.takeFromContainer(aspect, 1)) {
/*     */           
/*  47 */           PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(tile
/*  48 */                 .func_174877_v(), 
/*  49 */                 (byte)(tile.func_174877_v().func_177958_n() - source.pos.func_177958_n()), (byte)(tile.func_174877_v().func_177956_o() - source.pos.func_177956_o()), (byte)(tile.func_174877_v().func_177952_p() - source.pos.func_177952_p()), aspect
/*  50 */                 .getColor(), ext), new NetworkRegistry.TargetPoint(
/*  51 */                 (tile.func_145831_w()).field_73011_w.getDimension(), tile.func_174877_v().func_177958_n(), tile.func_174877_v().func_177956_o(), tile.func_174877_v().func_177952_p(), 32.0D));
/*     */           
/*  53 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  59 */     sources.remove(tileLoc);
/*  60 */     sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 10000L));
/*     */     
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean drainEssentiaWithConfirmation(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror, int ext) {
/*  66 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), (tile.func_145831_w()).field_73011_w.getDimension());
/*  67 */     if (!sources.containsKey(tileLoc)) {
/*  68 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/*  69 */       if (sources.containsKey(tileLoc))
/*  70 */         return drainEssentiaWithConfirmation(tile, aspect, direction, range, ignoreMirror, ext); 
/*  71 */       return false;
/*     */     } 
/*  73 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/*  74 */     for (WorldCoordinates source : es) {
/*  75 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/*  76 */       if (sourceTile != null && sourceTile instanceof IAspectSource) {
/*     */         
/*  78 */         IAspectSource as = (IAspectSource)sourceTile;
/*  79 */         if (as.isBlocked() || (ignoreMirror && sourceTile instanceof TileMirrorEssentia))
/*     */           continue; 
/*  81 */         if (as.doesContainerContainAmount(aspect, 1)) {
/*  82 */           las = sourceTile;
/*  83 */           lasp = aspect;
/*  84 */           lat = tile;
/*  85 */           lext = ext;
/*  86 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  92 */     sources.remove(tileLoc);
/*  93 */     sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 10000L));
/*     */     
/*  95 */     return false;
/*     */   }
/*  97 */   private static TileEntity lat = null;
/*  98 */   private static TileEntity las = null;
/*  99 */   private static Aspect lasp = null;
/* 100 */   private static int lext = 0;
/*     */   public static void confirmDrain() {
/* 102 */     if (las != null && lasp != null && lat != null) {
/* 103 */       as = (IAspectSource)las;
/* 104 */       if (as.takeFromContainer(lasp, 1))
/* 105 */         PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(lat
/* 106 */               .func_174877_v(), 
/* 107 */               (byte)(lat.func_174877_v().func_177958_n() - las.func_174877_v().func_177958_n()), (byte)(lat.func_174877_v().func_177956_o() - las.func_174877_v().func_177956_o()), (byte)(lat.func_174877_v().func_177952_p() - las.func_174877_v().func_177952_p()), lasp
/* 108 */               .getColor(), lext), new NetworkRegistry.TargetPoint(
/* 109 */               (lat.func_145831_w()).field_73011_w.getDimension(), lat.func_174877_v().func_177958_n(), lat.func_174877_v().func_177956_o(), lat.func_174877_v().func_177952_p(), 32.0D)); 
/*     */     } 
/* 111 */     las = null;
/* 112 */     lasp = null;
/* 113 */     lat = null;
/*     */   }
/*     */   
/*     */   public static boolean addEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror, int ext) {
/* 117 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), (tile.func_145831_w()).field_73011_w.getDimension());
/* 118 */     if (!sources.containsKey(tileLoc)) {
/* 119 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/* 120 */       if (sources.containsKey(tileLoc))
/* 121 */         return addEssentia(tile, aspect, direction, range, ignoreMirror, ext); 
/* 122 */       return false;
/*     */     } 
/* 124 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/* 125 */     ArrayList<WorldCoordinates> empties = new ArrayList<WorldCoordinates>();
/*     */ 
/*     */     
/* 128 */     for (WorldCoordinates source : es) {
/* 129 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/* 130 */       if (sourceTile != null && sourceTile instanceof IAspectSource) {
/*     */         
/* 132 */         IAspectSource as = (IAspectSource)sourceTile;
/* 133 */         if (as.isBlocked() || (ignoreMirror && sourceTile instanceof TileMirrorEssentia))
/*     */           continue; 
/* 135 */         if (as.doesContainerAccept(aspect) && (as.getAspects() == null || as.getAspects().visSize() == 0)) {
/* 136 */           empties.add(source); continue;
/*     */         } 
/* 138 */         if (as.doesContainerAccept(aspect) && as.addToContainer(aspect, 1) <= 0) {
/* 139 */           PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(source.pos, 
/*     */                 
/* 141 */                 (byte)(source.pos.func_177958_n() - tile.func_174877_v().func_177958_n()), (byte)(source.pos.func_177956_o() - tile.func_174877_v().func_177956_o()), (byte)(source.pos.func_177952_p() - tile.func_174877_v().func_177952_p()), aspect
/* 142 */                 .getColor(), ext), new NetworkRegistry.TargetPoint(
/* 143 */                 (tile.func_145831_w()).field_73011_w.getDimension(), tile.func_174877_v().func_177958_n(), tile.func_174877_v().func_177956_o(), tile.func_174877_v().func_177952_p(), 32.0D));
/* 144 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     for (WorldCoordinates source : empties) {
/* 153 */       if (source == null || source.pos == null)
/* 154 */         continue;  TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/* 155 */       if (sourceTile != null && sourceTile instanceof IAspectSource) {
/* 156 */         IAspectSource as = (IAspectSource)sourceTile;
/* 157 */         if (aspect != null && as.doesContainerAccept(aspect) && as.addToContainer(aspect, 1) <= 0) {
/* 158 */           PacketHandler.INSTANCE.sendToAllAround(new PacketFXEssentiaSource(source.pos, 
/*     */                 
/* 160 */                 (byte)(source.pos.func_177958_n() - tile.func_174877_v().func_177958_n()), (byte)(source.pos.func_177956_o() - tile.func_174877_v().func_177956_o()), (byte)(source.pos.func_177952_p() - tile.func_174877_v().func_177952_p()), aspect
/* 161 */                 .getColor(), ext), new NetworkRegistry.TargetPoint(
/* 162 */                 (tile.func_145831_w()).field_73011_w.getDimension(), tile.func_174877_v().func_177958_n(), tile.func_174877_v().func_177956_o(), tile.func_174877_v().func_177952_p(), 32.0D));
/* 163 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 170 */     sources.remove(tileLoc);
/* 171 */     sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 10000L));
/*     */     
/* 173 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean findEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror) {
/* 177 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), (tile.func_145831_w()).field_73011_w.getDimension());
/* 178 */     if (!sources.containsKey(tileLoc)) {
/* 179 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/* 180 */       if (sources.containsKey(tileLoc))
/* 181 */         return findEssentia(tile, aspect, direction, range, ignoreMirror); 
/* 182 */       return false;
/*     */     } 
/* 184 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/* 185 */     for (WorldCoordinates source : es) {
/* 186 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/* 187 */       if (sourceTile != null && sourceTile instanceof IAspectSource) {
/*     */         
/* 189 */         IAspectSource as = (IAspectSource)sourceTile;
/* 190 */         if (as.isBlocked() || (ignoreMirror && sourceTile instanceof TileMirrorEssentia))
/*     */           continue; 
/* 192 */         if (as.doesContainerContainAmount(aspect, 1)) {
/* 193 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean canAcceptEssentia(TileEntity tile, Aspect aspect, EnumFacing direction, int range, boolean ignoreMirror) {
/* 206 */     WorldCoordinates tileLoc = new WorldCoordinates(tile.func_174877_v(), (tile.func_145831_w()).field_73011_w.getDimension());
/* 207 */     if (!sources.containsKey(tileLoc)) {
/* 208 */       getSources(tile.func_145831_w(), tileLoc, direction, range);
/* 209 */       if (sources.containsKey(tileLoc))
/* 210 */         return findEssentia(tile, aspect, direction, range, ignoreMirror); 
/* 211 */       return false;
/*     */     } 
/* 213 */     ArrayList<WorldCoordinates> es = (ArrayList)sources.get(tileLoc);
/* 214 */     for (WorldCoordinates source : es) {
/* 215 */       TileEntity sourceTile = tile.func_145831_w().func_175625_s(source.pos);
/* 216 */       if (sourceTile != null && sourceTile instanceof IAspectSource) {
/* 217 */         if (ignoreMirror && sourceTile instanceof TileMirrorEssentia)
/* 218 */           continue;  IAspectSource as = (IAspectSource)sourceTile;
/* 219 */         if (!as.isBlocked() && as.doesContainerAccept(aspect)) {
/* 220 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void getSources(World world, WorldCoordinates tileLoc, EnumFacing direction, int range) {
/* 234 */     if (sourcesDelay.containsKey(tileLoc)) {
/* 235 */       long d = ((Long)sourcesDelay.get(tileLoc)).longValue();
/* 236 */       if (d <= System.currentTimeMillis()) {
/* 237 */         sourcesDelay.remove(tileLoc);
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 243 */     TileEntity sourceTile = world.func_175625_s(tileLoc.pos);
/*     */     
/* 245 */     ArrayList<WorldCoordinates> sourceList = new ArrayList<WorldCoordinates>();
/* 246 */     int start = 0;
/* 247 */     if (direction == null) {
/* 248 */       start = -range;
/* 249 */       direction = EnumFacing.UP;
/*     */     } 
/*     */ 
/*     */     
/* 253 */     int xx = 0;
/* 254 */     int yy = 0;
/* 255 */     int zz = 0;
/* 256 */     for (int aa = -range; aa <= range; aa++) {
/* 257 */       for (int bb = -range; bb <= range; bb++) {
/* 258 */         for (int cc = start; cc < range; cc++) {
/*     */           
/* 260 */           if (aa != 0 || bb != 0 || cc != 0) {
/*     */             
/* 262 */             xx = tileLoc.pos.func_177958_n();
/* 263 */             yy = tileLoc.pos.func_177956_o();
/* 264 */             zz = tileLoc.pos.func_177952_p();
/*     */             
/* 266 */             if (direction.func_96559_d() != 0) {
/* 267 */               xx += aa;
/* 268 */               yy += cc * direction.func_96559_d();
/* 269 */               zz += bb;
/*     */             }
/* 271 */             else if (direction.func_82601_c() == 0) {
/* 272 */               xx += aa;
/* 273 */               yy += bb;
/* 274 */               zz += cc * direction.func_82599_e();
/*     */             } else {
/* 276 */               xx += cc * direction.func_82601_c();
/* 277 */               yy += aa;
/* 278 */               zz += bb;
/*     */             } 
/*     */             
/* 281 */             TileEntity te = world.func_175625_s(new BlockPos(xx, yy, zz));
/* 282 */             if (te != null && te instanceof IAspectSource)
/*     */             {
/* 284 */               if (!(sourceTile instanceof TileMirrorEssentia) || !(te instanceof TileMirrorEssentia) || 
/* 285 */                 sourceTile.func_174877_v().func_177958_n() != ((TileMirrorEssentia)te).linkX || sourceTile
/* 286 */                 .func_174877_v().func_177956_o() != ((TileMirrorEssentia)te).linkY || sourceTile
/* 287 */                 .func_174877_v().func_177952_p() != ((TileMirrorEssentia)te).linkZ || 
/* 288 */                 (sourceTile.func_145831_w()).field_73011_w.getDimension() != ((TileMirrorEssentia)te).linkDim)
/*     */               {
/*     */ 
/*     */                 
/* 292 */                 sourceList.add(new WorldCoordinates(new BlockPos(xx, yy, zz), world.field_73011_w.getDimension())); }  } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 296 */     }  if (sourceList.size() > 0) {
/* 297 */       ArrayList<WorldCoordinates> sourceList2 = new ArrayList<WorldCoordinates>();
/* 298 */       label66: for (WorldCoordinates wc : sourceList) {
/* 299 */         double dist = wc.getDistanceSquaredToWorldCoordinates(tileLoc);
/* 300 */         if (!sourceList2.isEmpty()) {
/* 301 */           for (int a = 0; a < sourceList2.size(); a++) {
/* 302 */             double d2 = ((WorldCoordinates)sourceList2.get(a)).getDistanceSquaredToWorldCoordinates(tileLoc);
/* 303 */             if (dist < d2) {
/* 304 */               sourceList2.add(a, wc);
/*     */               continue label66;
/*     */             } 
/*     */           } 
/*     */         }
/* 309 */         sourceList2.add(wc);
/*     */       } 
/* 311 */       sources.put(tileLoc, sourceList2);
/*     */     } else {
/*     */       
/* 314 */       sourcesDelay.put(tileLoc, Long.valueOf(System.currentTimeMillis() + 10000L));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 319 */   public static void refreshSources(TileEntity tile) { sources.remove(new WorldCoordinates(tile.func_174877_v(), (tile.func_145831_w()).field_73011_w.getDimension())); }
/*     */   
/*     */   public static class EssentiaSourceFX {
/*     */     public BlockPos start;
/*     */     public BlockPos end;
/*     */     public int color;
/*     */     public int ext;
/*     */     
/*     */     public EssentiaSourceFX(BlockPos start, BlockPos end, int color, int ext) {
/* 328 */       this.start = start;
/* 329 */       this.end = end;
/* 330 */       this.color = color;
/* 331 */       this.ext = ext;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   public static ConcurrentHashMap<String, EssentiaSourceFX> sourceFX = new ConcurrentHashMap();
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\EssentiaHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */