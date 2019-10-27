/*     */ package thaumcraft.common.lib.events;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.ChunkPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.BlockSnapshot;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.aura.AuraHelper;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.entities.EntityFluxRift;
/*     */ import thaumcraft.common.entities.EntitySpecialItem;
/*     */ import thaumcraft.common.golems.seals.SealHandler;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXBlockBamf;
/*     */ import thaumcraft.common.lib.utils.BlockUtils;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ import thaumcraft.common.tiles.devices.TileArcaneEar;
/*     */ import thaumcraft.common.world.ThaumcraftWorldGenerator;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ import thaumcraft.common.world.aura.AuraThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber
/*     */ public class ServerEvents
/*     */ {
/*  61 */   long lastcheck = 0L;
/*  62 */   static HashMap<Integer, Integer> serverTicks = new HashMap();
/*     */   
/*  64 */   public static ConcurrentHashMap<Integer, AuraThread> auraThreads = new ConcurrentHashMap();
/*     */   
/*  66 */   DecimalFormat myFormatter = new DecimalFormat("#######.##");
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void clientWorldTick(TickEvent.ClientTickEvent event) {
/*  71 */     if (event.side == Side.SERVER)
/*     */       return; 
/*  73 */     if (event.phase == TickEvent.Phase.END)
/*     */     {
/*     */       
/*  76 */       if (!clientRunList.isEmpty()) {
/*  77 */         LinkedBlockingQueue<RunnableEntry> temp = new LinkedBlockingQueue<RunnableEntry>();
/*  78 */         while (!clientRunList.isEmpty()) {
/*  79 */           RunnableEntry current = (RunnableEntry)clientRunList.poll();
/*  80 */           if (current != null) {
/*  81 */             if (current.delay > 0) {
/*  82 */               current.delay--;
/*  83 */               temp.offer(current);
/*     */               continue;
/*     */             } 
/*     */             try {
/*  87 */               current.runnable.run();
/*  88 */             } catch (Exception exception) {}
/*     */           } 
/*     */         } 
/*  91 */         while (!temp.isEmpty()) {
/*  92 */           clientRunList.offer(temp.poll());
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void worldTick(TickEvent.WorldTickEvent event) {
/* 100 */     if (event.side == Side.CLIENT) {
/*     */       return;
/*     */     }
/* 103 */     int dim = event.world.field_73011_w.getDimension();
/*     */     
/* 105 */     if (event.phase == TickEvent.Phase.START) {
/* 106 */       if (!auraThreads.containsKey(Integer.valueOf(dim)) && AuraHandler.getAuraWorld(dim) != null) {
/* 107 */         AuraThread at = new AuraThread(dim);
/* 108 */         Thread thread = new Thread(at);
/* 109 */         thread.start();
/* 110 */         auraThreads.put(Integer.valueOf(dim), at);
/*     */       } 
/*     */     } else {
/*     */       
/* 114 */       if (!serverTicks.containsKey(Integer.valueOf(dim))) serverTicks.put(Integer.valueOf(dim), Integer.valueOf(0));
/*     */ 
/*     */       
/* 117 */       LinkedBlockingQueue<RunnableEntry> rlist = (LinkedBlockingQueue)serverRunList.get(Integer.valueOf(dim));
/* 118 */       if (rlist == null) {
/* 119 */         serverRunList.put(Integer.valueOf(dim), rlist = new LinkedBlockingQueue<RunnableEntry>());
/*     */       }
/* 121 */       else if (!rlist.isEmpty()) {
/* 122 */         LinkedBlockingQueue<RunnableEntry> temp = new LinkedBlockingQueue<RunnableEntry>();
/* 123 */         while (!rlist.isEmpty()) {
/* 124 */           RunnableEntry current = (RunnableEntry)rlist.poll();
/* 125 */           if (current != null) {
/* 126 */             if (current.delay > 0) {
/* 127 */               current.delay--;
/* 128 */               temp.offer(current);
/*     */               continue;
/*     */             } 
/*     */             try {
/* 132 */               current.runnable.run();
/* 133 */             } catch (Exception exception) {}
/*     */           } 
/*     */         } 
/* 136 */         while (!temp.isEmpty()) {
/* 137 */           rlist.offer(temp.poll());
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 142 */       int ticks = ((Integer)serverTicks.get(Integer.valueOf(dim))).intValue();
/*     */       
/* 144 */       tickChunkRegeneration(event);
/*     */       
/* 146 */       tickBlockSwap(event.world);
/*     */       
/* 148 */       tickBlockBreak(event.world);
/*     */       
/* 150 */       ArrayList<Integer[]> nbe = (ArrayList)TileArcaneEar.noteBlockEvents.get(Integer.valueOf(dim));
/* 151 */       if (nbe != null) nbe.clear();
/*     */ 
/*     */       
/* 154 */       if (ticks % 20 == 0) {
/*     */         
/* 156 */         CopyOnWriteArrayList<ChunkPos> dc = (CopyOnWriteArrayList)AuraHandler.dirtyChunks.get(Integer.valueOf(dim));
/* 157 */         if (dc != null && dc.size() > 0) {
/* 158 */           for (ChunkPos pos : dc) {
/* 159 */             event.world.func_175646_b(pos.func_180331_a(5, 5, 5), null);
/*     */           }
/* 161 */           dc.clear();
/*     */         } 
/*     */ 
/*     */         
/* 165 */         if (AuraHandler.riftTrigger.containsKey(Integer.valueOf(dim))) {
/* 166 */           if (!ModConfig.CONFIG_MISC.wussMode)
/* 167 */             EntityFluxRift.createRift(event.world, (BlockPos)AuraHandler.riftTrigger.get(Integer.valueOf(dim))); 
/* 168 */           AuraHandler.riftTrigger.remove(Integer.valueOf(dim));
/*     */         } 
/*     */ 
/*     */         
/* 172 */         TaskHandler.clearSuspendedOrExpiredTasks(event.world);
/*     */       } 
/*     */       
/* 175 */       SealHandler.tickSealEntities(event.world);
/*     */       
/* 177 */       serverTicks.put(Integer.valueOf(dim), Integer.valueOf(ticks + 1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void tickChunkRegeneration(TickEvent.WorldTickEvent event) {
/* 183 */     int dim = event.world.field_73011_w.getDimension();
/* 184 */     int count = 0;
/* 185 */     ArrayList<ChunkPos> chunks = (ArrayList)chunksToGenerate.get(Integer.valueOf(dim));
/* 186 */     if (chunks != null && chunks.size() > 0)
/* 187 */       for (int a = 0; a < 10; ) {
/* 188 */         chunks = (ArrayList)chunksToGenerate.get(Integer.valueOf(dim));
/* 189 */         if (chunks != null && chunks.size() > 0) {
/* 190 */           count++;
/* 191 */           ChunkPos loc = (ChunkPos)chunks.get(0);
/* 192 */           long worldSeed = event.world.func_72905_C();
/* 193 */           Random fmlRandom = new Random(worldSeed);
/* 194 */           long xSeed = fmlRandom.nextLong() >> 3;
/* 195 */           long zSeed = fmlRandom.nextLong() >> 3;
/* 196 */           fmlRandom.setSeed(xSeed * loc.field_77276_a + zSeed * loc.field_77275_b ^ worldSeed);
/* 197 */           ThaumcraftWorldGenerator.INSTANCE.worldGeneration(fmlRandom, loc.field_77276_a, loc.field_77275_b, event.world, false);
/* 198 */           chunks.remove(0);
/* 199 */           chunksToGenerate.put(Integer.valueOf(dim), chunks); a++;
/*     */         } 
/*     */       }  
/* 202 */     if (count > 0) {
/* 203 */       FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[Thaumcraft] Regenerated " + count + " chunks. " + 
/* 204 */           Math.max(0, chunks.size()) + " chunks left");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void tickBlockSwap(World world) {
/* 211 */     int dim = world.field_73011_w.getDimension();
/* 212 */     LinkedBlockingQueue<VirtualSwapper> queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/* 213 */     LinkedBlockingQueue<VirtualSwapper> queue2 = new LinkedBlockingQueue<VirtualSwapper>();
/* 214 */     if (queue != null) {
/* 215 */       while (!queue.isEmpty()) {
/*     */         
/* 217 */         VirtualSwapper vs = (VirtualSwapper)queue.poll();
/* 218 */         if (vs != null) {
/*     */           
/* 220 */           IBlockState bs = world.func_180495_p(vs.pos);
/*     */           
/* 222 */           boolean allow = (bs.func_185887_b(world, vs.pos) >= 0.0F);
/*     */           
/* 224 */           if ((vs.source != null && vs.source instanceof IBlockState && (IBlockState)vs.source != bs) || (vs.source != null && vs.source instanceof Material && (Material)vs.source != bs
/* 225 */             .func_185904_a())) {
/* 226 */             allow = false;
/*     */           }
/*     */           
/* 229 */           if (vs.visCost > 0.0F && AuraHelper.getVis(world, vs.pos) < vs.visCost) allow = false;
/*     */           
/* 231 */           if (world.canMineBlockBody(vs.player, vs.pos) && allow && (vs.target == null || vs.target
/* 232 */             .func_190926_b() || !vs.target.func_77969_a(new ItemStack(bs.func_177230_c(), 1, bs.func_177230_c().func_176201_c(bs)))) && 
/* 233 */             !ForgeEventFactory.onPlayerBlockPlace(vs.player, new BlockSnapshot(world, vs.pos, bs), EnumFacing.UP, EnumHand.MAIN_HAND).isCanceled() && vs.allowSwap
/*     */ 
/*     */             
/* 236 */             .apply(new SwapperPredicate(world, vs.player, vs.pos))) {
/*     */             
/* 238 */             int slot = -1;
/* 239 */             if (!vs.consumeTarget || vs.target == null || vs.target.func_190926_b()) {
/* 240 */               slot = 1;
/*     */             } else {
/* 242 */               slot = InventoryUtils.getPlayerSlotFor(vs.player, vs.target);
/*     */             } 
/*     */             
/* 245 */             if (vs.player.field_71075_bZ.field_75098_d) slot = 1;
/*     */             
/* 247 */             boolean matches = false;
/* 248 */             if (vs.source instanceof Material) {
/* 249 */               matches = (bs.func_185904_a() == (Material)vs.source);
/*     */             }
/* 251 */             if (vs.source instanceof IBlockState) {
/* 252 */               matches = (bs == (IBlockState)vs.source);
/*     */             }
/*     */             
/* 255 */             if ((vs.source == null || matches) && slot >= 0) {
/*     */               
/* 257 */               if (!vs.player.field_71075_bZ.field_75098_d) {
/*     */                 
/* 259 */                 if (vs.consumeTarget) vs.player.field_71071_by.func_70298_a(slot, 1);
/*     */                 
/* 261 */                 if (vs.pickup) {
/* 262 */                   List<ItemStack> ret = new ArrayList<ItemStack>();
/* 263 */                   if (vs.silk && bs.func_177230_c().canSilkHarvest(world, vs.pos, bs, vs.player)) {
/*     */                     
/* 265 */                     ItemStack itemstack = BlockUtils.getSilkTouchDrop(bs);
/* 266 */                     if (itemstack != null && !itemstack.func_190926_b())
/*     */                     {
/* 268 */                       ret.add(itemstack);
/*     */                     }
/*     */                   } else {
/* 271 */                     ret = bs.func_177230_c().getDrops(world, vs.pos, bs, vs.fortune);
/*     */                   } 
/*     */                   
/* 274 */                   if (ret.size() > 0) for (ItemStack is : ret) {
/* 275 */                       if (!vs.player.field_71071_by.func_70441_a(is)) {
/* 276 */                         world.func_72838_d(new EntityItem(world, vs.pos
/* 277 */                               .func_177958_n() + 0.5D, vs.pos.func_177956_o() + 0.5D, vs.pos.func_177952_p() + 0.5D, is));
/*     */                       }
/*     */                     }  
/*     */                 } 
/* 281 */                 if (vs.visCost > 0.0F) ThaumcraftApi.internalMethods.drainVis(world, vs.pos, vs.visCost, false);
/*     */               
/*     */               } 
/*     */               
/* 285 */               if (vs.target == null || vs.target.func_190926_b()) {
/* 286 */                 world.func_175698_g(vs.pos);
/*     */               } else {
/* 288 */                 Block tb = Block.func_149634_a(vs.target.func_77973_b());
/* 289 */                 if (tb != null && tb != Blocks.field_150350_a) {
/* 290 */                   world.func_180501_a(vs.pos, tb.func_176203_a(vs.target.func_77952_i()), 3);
/*     */                 } else {
/* 292 */                   world.func_175698_g(vs.pos);
/*     */                   
/* 294 */                   EntitySpecialItem entityItem = new EntitySpecialItem(world, vs.pos.func_177958_n() + 0.5D, vs.pos.func_177956_o() + 0.1D, vs.pos.func_177952_p() + 0.5D, vs.target.func_77946_l());
/* 295 */                   entityItem.field_70181_x = 0.0D;
/* 296 */                   entityItem.field_70159_w = 0.0D;
/* 297 */                   entityItem.field_70179_y = 0.0D;
/* 298 */                   world.func_72838_d(entityItem);
/*     */                 } 
/*     */               } 
/*     */               
/* 302 */               if (vs.fx) {
/* 303 */                 PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(vs.pos, vs.color, true, vs.fancy, null), new NetworkRegistry.TargetPoint(world.field_73011_w
/*     */                       
/* 305 */                       .getDimension(), vs.pos
/* 306 */                       .func_177958_n(), vs.pos.func_177956_o(), vs.pos.func_177952_p(), 32.0D));
/*     */               }
/*     */               
/* 309 */               if (vs.lifespan > 0) {
/* 310 */                 for (int xx = -1; xx <= 1; xx++) {
/* 311 */                   for (int yy = -1; yy <= 1; yy++) {
/* 312 */                     for (int zz = -1; zz <= 1; zz++) {
/* 313 */                       matches = false;
/* 314 */                       if (vs.source instanceof Material) {
/* 315 */                         IBlockState bb = world.func_180495_p(vs.pos.func_177982_a(xx, yy, zz));
/* 316 */                         matches = (bb.func_177230_c().func_149688_o(bb) == vs.source);
/*     */                       } 
/* 318 */                       if (vs.source instanceof IBlockState) {
/* 319 */                         matches = (world.func_180495_p(vs.pos.func_177982_a(xx, yy, zz)) == vs.source);
/*     */                       }
/* 321 */                       if ((xx != 0 || yy != 0 || zz != 0) && matches && 
/* 322 */                         BlockUtils.isBlockExposed(world, vs.pos.func_177982_a(xx, yy, zz))) {
/* 323 */                         queue2.offer(new VirtualSwapper(vs.pos
/* 324 */                               .func_177982_a(xx, yy, zz), vs.source, vs.target, vs.consumeTarget, vs.lifespan - 1, vs.player, vs.fx, vs.fancy, vs.color, vs.pickup, vs.silk, vs.fortune, vs.allowSwap, vs.visCost));
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 335 */       swapList.put(Integer.valueOf(dim), queue2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void tickBlockBreak(World world) {
/* 340 */     int dim = world.field_73011_w.getDimension();
/* 341 */     LinkedBlockingQueue<BreakData> queue = (LinkedBlockingQueue)breakList.get(Integer.valueOf(dim));
/* 342 */     LinkedBlockingQueue<BreakData> queue2 = new LinkedBlockingQueue<BreakData>();
/* 343 */     if (queue != null) {
/* 344 */       while (!queue.isEmpty()) {
/* 345 */         BreakData vs = (BreakData)queue.poll();
/* 346 */         if (vs != null) {
/* 347 */           IBlockState bs = world.func_180495_p(vs.pos);
/* 348 */           if (bs == vs.source) {
/*     */             
/* 350 */             if (vs.visCost > 0.0F && AuraHelper.getVis(world, vs.pos) < vs.visCost)
/*     */               continue; 
/* 352 */             if (world.canMineBlockBody(vs.player, vs.pos) && bs.func_185887_b(world, vs.pos) >= 0.0F) {
/* 353 */               if (vs.fx) world.func_175715_c(vs.pos.hashCode(), vs.pos, (int)((1.0F - vs.durabilityCurrent / vs.durabilityMax) * 10.0F)); 
/* 354 */               vs.durabilityCurrent -= vs.strength;
/* 355 */               if (vs.durabilityCurrent <= 0.0F) {
/* 356 */                 BlockUtils.harvestBlock(world, vs.player, vs.pos, true, vs.silk, vs.fortune, false);
/* 357 */                 if (vs.fx) world.func_175715_c(vs.pos.hashCode(), vs.pos, -1); 
/* 358 */                 if (vs.visCost > 0.0F) ThaumcraftApi.internalMethods.drainVis(world, vs.pos, vs.visCost, false);  continue;
/*     */               } 
/* 360 */               queue2.offer(new BreakData(vs.strength, vs.durabilityCurrent, vs.durabilityMax, vs.pos, vs.source, vs.player, vs.fx, vs.silk, vs.fortune, vs.visCost));
/*     */             }  continue;
/*     */           } 
/* 363 */           if (vs.fx) {
/* 364 */             world.func_175715_c(vs.pos.hashCode(), vs.pos, -1);
/*     */           }
/*     */         } 
/*     */       } 
/* 368 */       breakList.put(Integer.valueOf(dim), queue2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addSwapper(World world, BlockPos pos, Object source, ItemStack target, boolean consumeTarget, int life, EntityPlayer player, boolean fx, boolean fancy, int color, boolean pickup, boolean silk, int fortune, Predicate<SwapperPredicate> allowSwap, float visCost) {
/* 377 */     int dim = world.field_73011_w.getDimension();
/*     */     
/* 379 */     LinkedBlockingQueue<VirtualSwapper> queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/* 380 */     if (queue == null) {
/* 381 */       swapList.put(Integer.valueOf(dim), new LinkedBlockingQueue());
/* 382 */       queue = (LinkedBlockingQueue)swapList.get(Integer.valueOf(dim));
/*     */     } 
/* 384 */     queue.offer(new VirtualSwapper(pos, source, target, consumeTarget, life, player, fx, fancy, color, pickup, silk, fortune, allowSwap, visCost));
/*     */ 
/*     */     
/* 387 */     swapList.put(Integer.valueOf(dim), queue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addBreaker(final World world, final BlockPos pos, final IBlockState source, final EntityPlayer player, final boolean fx, final boolean silk, final int fortune, final float str, final float durabilityCurrent, final float durabilityMax, int delay, final float vis, final Runnable run) {
/* 394 */     int dim = world.field_73011_w.getDimension();
/*     */     
/* 396 */     if (delay > 0) {
/* 397 */       addRunnableServer(world, new Runnable() {
/* 398 */             public void run() { ServerEvents.addBreaker(world, pos, source, player, fx, silk, fortune, str, durabilityCurrent, durabilityMax, 0, vis, run); } }delay);
/*     */     } else {
/* 400 */       LinkedBlockingQueue<BreakData> queue = (LinkedBlockingQueue)breakList.get(Integer.valueOf(dim));
/* 401 */       if (queue == null) {
/* 402 */         breakList.put(Integer.valueOf(dim), new LinkedBlockingQueue());
/* 403 */         queue = (LinkedBlockingQueue)breakList.get(Integer.valueOf(dim));
/*     */       } 
/* 405 */       queue.offer(new BreakData(str, durabilityCurrent, durabilityMax, pos, source, player, fx, silk, fortune, vis));
/* 406 */       breakList.put(Integer.valueOf(dim), queue);
/*     */       
/* 408 */       if (run != null) {
/* 409 */         run.run();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 418 */   public static HashMap<Integer, LinkedBlockingQueue<BreakData>> breakList = new HashMap(); public static class BreakData { float strength; float durabilityCurrent; float durabilityMax; IBlockState source; BlockPos pos;
/*     */     public BreakData(float strength, float durabilityCurrent, float durabilityMax, BlockPos pos, IBlockState source, EntityPlayer player, boolean fx, boolean silk, int fortune, float vis) {
/* 420 */       this.strength = 0.0F;
/* 421 */       this.durabilityCurrent = 1.0F;
/* 422 */       this.durabilityMax = 1.0F;
/*     */ 
/*     */       
/* 425 */       this.player = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 432 */       this.strength = strength;
/* 433 */       this.source = source;
/* 434 */       this.pos = pos;
/* 435 */       this.player = player;
/* 436 */       this.fx = fx;
/* 437 */       this.silk = silk;
/* 438 */       this.fortune = fortune;
/* 439 */       this.durabilityCurrent = durabilityCurrent;
/* 440 */       this.durabilityMax = durabilityMax;
/* 441 */       this.visCost = vis;
/*     */     }
/*     */     EntityPlayer player; boolean fx;
/*     */     boolean silk;
/*     */     int fortune;
/*     */     float visCost; }
/* 447 */   public static HashMap<Integer, LinkedBlockingQueue<VirtualSwapper>> swapList = new HashMap();
/* 448 */   public static HashMap<Integer, ArrayList<ChunkPos>> chunksToGenerate = new HashMap();
/*     */   
/*     */   public static class SwapperPredicate { public World world;
/*     */     public EntityPlayer player;
/*     */     public BlockPos pos;
/*     */     
/*     */     public SwapperPredicate(World world, EntityPlayer player, BlockPos pos) {
/* 455 */       this.world = world;
/* 456 */       this.player = player;
/* 457 */       this.pos = pos;
/*     */     } }
/*     */ 
/*     */   
/* 461 */   public static final Predicate<SwapperPredicate> DEFAULT_PREDICATE = new Predicate<SwapperPredicate>()
/*     */     {
/*     */       public boolean apply(@Nullable ServerEvents.SwapperPredicate pred)
/*     */       {
/* 465 */         return true; }
/*     */     };
/*     */   public static class VirtualSwapper { int color; boolean fancy; Predicate<ServerEvents.SwapperPredicate> allowSwap; int lifespan;
/*     */     BlockPos pos;
/*     */     Object source;
/*     */     ItemStack target;
/*     */     
/*     */     VirtualSwapper(BlockPos pos, Object source, ItemStack t, boolean consumeTarget, int life, EntityPlayer p, boolean fx, boolean fancy, int color, boolean pickup, boolean silk, int fortune, Predicate<ServerEvents.SwapperPredicate> allowSwap, float cost) {
/* 473 */       this.lifespan = 0;
/*     */ 
/*     */ 
/*     */       
/* 477 */       this.player = null;
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
/* 488 */       this.pos = pos;
/* 489 */       this.source = source;
/* 490 */       this.target = t;
/* 491 */       this.lifespan = life;
/* 492 */       this.player = p;
/* 493 */       this.consumeTarget = consumeTarget;
/* 494 */       this.fx = fx;
/* 495 */       this.fancy = fancy;
/* 496 */       this.allowSwap = allowSwap;
/* 497 */       this.silk = silk;
/* 498 */       this.fortune = fortune;
/* 499 */       this.pickup = pickup;
/* 500 */       this.color = color;
/* 501 */       this.visCost = cost;
/*     */     }
/*     */     EntityPlayer player; boolean fx; boolean silk; boolean pickup; boolean consumeTarget; int fortune;
/*     */     float visCost; }
/* 505 */   private static HashMap<Integer, LinkedBlockingQueue<RunnableEntry>> serverRunList = new HashMap();
/* 506 */   private static LinkedBlockingQueue<RunnableEntry> clientRunList = new LinkedBlockingQueue();
/*     */   
/*     */   public static class RunnableEntry { Runnable runnable;
/*     */     int delay;
/*     */     
/*     */     public RunnableEntry(Runnable runnable, int delay) {
/* 512 */       this.runnable = runnable;
/* 513 */       this.delay = delay;
/*     */     } }
/*     */ 
/*     */   
/*     */   public static void addRunnableServer(World world, Runnable runnable, int delay) {
/* 518 */     if (world.field_72995_K)
/* 519 */       return;  LinkedBlockingQueue<RunnableEntry> rlist = (LinkedBlockingQueue)serverRunList.get(Integer.valueOf(world.field_73011_w.getDimension()));
/* 520 */     if (rlist == null) {
/* 521 */       serverRunList.put(Integer.valueOf(world.field_73011_w.getDimension()), rlist = new LinkedBlockingQueue<RunnableEntry>());
/*     */     }
/* 523 */     rlist.add(new RunnableEntry(runnable, delay));
/*     */   }
/*     */   
/*     */   public static void addRunnableClient(World world, Runnable runnable, int delay) {
/* 527 */     if (!world.field_72995_K)
/* 528 */       return;  clientRunList.add(new RunnableEntry(runnable, delay));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\ServerEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */