/*    */ package thaumcraft.common.lib.events;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.world.BlockEvent;
/*    */ import net.minecraftforge.event.world.NoteBlockEvent;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import thaumcraft.common.golems.seals.SealHandler;
/*    */ import thaumcraft.common.tiles.devices.TileArcaneEar;
/*    */ import thaumcraft.common.world.aura.AuraHandler;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class WorldEvents
/*    */ {
/* 19 */   public static WorldEvents INSTANCE = new WorldEvents();
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void worldLoad(WorldEvent.Load event) {
/* 23 */     if (!(event.getWorld()).field_72995_K)
/*    */     {
/* 25 */       AuraHandler.addAuraWorld((event.getWorld()).field_73011_w.getDimension());
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void worldSave(WorldEvent.Save event) {
/* 31 */     if (!(event.getWorld()).field_72995_K);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void worldUnload(WorldEvent.Unload event) {
/* 38 */     if ((event.getWorld()).field_72995_K)
/*    */       return; 
/* 40 */     SealHandler.sealEntities.remove(Integer.valueOf((event.getWorld()).field_73011_w.getDimension()));
/* 41 */     AuraHandler.removeAuraWorld((event.getWorld()).field_73011_w.getDimension());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/* 47 */   public static void placeBlockEvent(BlockEvent.PlaceEvent event) { if (isNearActiveBoss(event.getWorld(), event.getPlayer(), event.getPos().func_177958_n(), event.getPos().func_177956_o(), event.getPos().func_177952_p())) event.setCanceled(true);
/*    */      }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/* 52 */   public static void placeBlockEvent(BlockEvent.MultiPlaceEvent event) { if (isNearActiveBoss(event.getWorld(), event.getPlayer(), event.getPos().func_177958_n(), event.getPos().func_177956_o(), event.getPos().func_177952_p())) event.setCanceled(true);
/*    */      }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   private static boolean isNearActiveBoss(World world, EntityPlayer player, int x, int y, int z) { return false; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void noteEvent(NoteBlockEvent.Play event) {
/* 71 */     if ((event.getWorld()).field_72995_K)
/*    */       return; 
/* 73 */     if (!TileArcaneEar.noteBlockEvents.containsKey(Integer.valueOf((event.getWorld()).field_73011_w.getDimension()))) {
/* 74 */       TileArcaneEar.noteBlockEvents.put(Integer.valueOf((event.getWorld()).field_73011_w.getDimension()), new ArrayList());
/*    */     }
/*    */     
/* 77 */     ArrayList<Integer[]> list = (ArrayList)TileArcaneEar.noteBlockEvents.get(Integer.valueOf((event.getWorld()).field_73011_w.getDimension()));
/*    */     
/* 79 */     list.add(new Integer[] { null, null, null, null, (new Integer[5][3] = (new Integer[5][2] = (new Integer[5][1] = (new Integer[5][0] = Integer.valueOf(event.getPos().func_177958_n())).valueOf(event.getPos().func_177956_o())).valueOf(event.getPos().func_177952_p())).valueOf(event.getInstrument().ordinal())).valueOf(event.getVanillaNoteId()) });
/*    */     
/* 81 */     TileArcaneEar.noteBlockEvents.put(Integer.valueOf((event.getWorld()).field_73011_w.getDimension()), list);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\WorldEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */