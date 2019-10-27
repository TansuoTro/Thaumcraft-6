/*    */ package thaumcraft.common.lib.events;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.event.AnvilUpdateEvent;
/*    */ import net.minecraftforge.fml.common.IFuelHandler;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.PlayerEvent;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.capabilities.IPlayerWarp;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.config.ModConfig;
/*    */ import thaumcraft.common.lib.research.ResearchManager;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber
/*    */ public class CraftingEvents
/*    */   implements IFuelHandler
/*    */ {
/*    */   public int getBurnTime(ItemStack fuel) {
/* 23 */     if (fuel.func_77969_a(new ItemStack(ItemsTC.alumentum))) return 4800; 
/* 24 */     if (fuel.func_77969_a(new ItemStack(BlocksTC.logGreatwood))) return 500; 
/* 25 */     if (fuel.func_77969_a(new ItemStack(BlocksTC.logSilverwood))) return 400; 
/* 26 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onCrafting(PlayerEvent.ItemCraftedEvent event) {
/* 32 */     int warp = ThaumcraftApi.getWarp(event.crafting);
/* 33 */     if (!ModConfig.CONFIG_MISC.wussMode && warp > 0 && 
/* 34 */       !event.player.field_70170_p.field_72995_K) {
/* 35 */       ThaumcraftApi.internalMethods.addWarpToPlayer(event.player, warp, IPlayerWarp.EnumWarpType.NORMAL);
/*    */     }
/*    */ 
/*    */     
/* 39 */     if (event.crafting.func_77973_b() == ItemsTC.label && event.crafting.func_77942_o()) {
/* 40 */       for (int var2 = 0; var2 < 9; var2++) {
/*    */         
/* 42 */         ItemStack var3 = event.craftMatrix.func_70301_a(var2);
/* 43 */         if (var3 != null && var3.func_77973_b() instanceof thaumcraft.common.items.consumables.ItemPhial) {
/*    */           
/* 45 */           var3.func_190917_f(1);
/* 46 */           event.craftMatrix.func_70299_a(var2, var3);
/*    */         } 
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/* 52 */     if (event.player != null && !event.player.field_70170_p.field_72995_K) {
/* 53 */       int stackHash = ResearchManager.createItemStackHash(event.crafting.func_77946_l());
/* 54 */       if (ResearchManager.craftingReferences.contains(Integer.valueOf(stackHash))) {
/* 55 */         ResearchManager.completeResearch(event.player, "[#]" + stackHash);
/*    */       } else {
/* 57 */         stackHash = ResearchManager.createItemStackHash(new ItemStack(event.crafting
/* 58 */               .func_77973_b(), event.crafting.func_190916_E(), event.crafting.func_77952_i()));
/* 59 */         if (ResearchManager.craftingReferences.contains(Integer.valueOf(stackHash))) {
/* 60 */           ResearchManager.completeResearch(event.player, "[#]" + stackHash);
/*    */         }
/*    */       } 
/*    */       try {
/* 64 */         int[] ids = OreDictionary.getOreIDs(event.crafting.func_77946_l());
/* 65 */         for (int id : ids) {
/* 66 */           String ore = OreDictionary.getOreName(id);
/* 67 */           if (ore != null) {
/* 68 */             int cd = ("oredict:" + ore).hashCode();
/* 69 */             if (ore != null && ResearchManager.craftingReferences.contains(Integer.valueOf(cd))) {
/* 70 */               ResearchManager.completeResearch(event.player, "[#]" + cd);
/*    */             }
/*    */           } 
/*    */         } 
/* 74 */       } catch (Exception exception) {}
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onAnvil(AnvilUpdateEvent event) {
/* 80 */     if (event.getLeft().func_77973_b() == ItemsTC.primordialPearl || event
/* 81 */       .getRight().func_77973_b() == ItemsTC.primordialPearl)
/* 82 */       event.setCanceled(true); 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\events\CraftingEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */