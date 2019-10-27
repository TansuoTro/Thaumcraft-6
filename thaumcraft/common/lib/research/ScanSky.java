/*     */ package thaumcraft.common.lib.research;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.world.DimensionType;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.IScanThing;
/*     */ import thaumcraft.common.lib.utils.InventoryUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScanSky
/*     */   implements IScanThing
/*     */ {
/*     */   public boolean checkThing(EntityPlayer player, Object obj) {
/*  27 */     if (obj != null || player.field_70125_A > 0.0F || !player.field_70170_p.func_175678_i(player.func_180425_c().func_177984_a()) || player.field_70170_p.field_73011_w
/*  28 */       .func_186058_p() != DimensionType.OVERWORLD || 
/*  29 */       !ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { "CELESTIALSCANNING" })) return false;
/*     */     
/*  31 */     int yaw = (int)(player.field_70177_z + 90.0F) % 360;
/*  32 */     int pitch = (int)Math.abs(player.field_70125_A);
/*     */     
/*  34 */     int ca = (int)((player.field_70170_p.func_72826_c(0.0F) + 0.25D) * 360.0D) % 360;
/*  35 */     boolean night = (ca > 180);
/*  36 */     boolean inRangeYaw = false;
/*  37 */     boolean inRangePitch = false;
/*  38 */     if (night) ca -= 180; 
/*  39 */     if (ca > 90) {
/*  40 */       inRangeYaw = (Math.abs(Math.abs(yaw) - 180) < 10);
/*  41 */       inRangePitch = (Math.abs(180 - ca - pitch) < 7);
/*     */     } else {
/*  43 */       inRangeYaw = (Math.abs(yaw) < 10);
/*  44 */       inRangePitch = (Math.abs(ca - pitch) < 7);
/*     */     } 
/*  46 */     if (inRangeYaw && inRangePitch)
/*  47 */       return true; 
/*  48 */     if (night) {
/*  49 */       return true;
/*     */     }
/*     */     
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onSuccess(EntityPlayer player, Object object) {
/*  58 */     if (object != null || player.field_70125_A > 0.0F || !player.field_70170_p.func_175678_i(player.func_180425_c().func_177984_a()) || 
/*  59 */       !ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { "CELESTIALSCANNING" }))
/*     */       return; 
/*  61 */     int yaw = (int)(player.field_70177_z + 90.0F) % 360;
/*  62 */     int pitch = (int)Math.abs(player.field_70125_A);
/*     */     
/*  64 */     int ca = (int)((player.field_70170_p.func_72826_c(0.0F) + 0.25D) * 360.0D) % 360;
/*  65 */     boolean night = (ca > 180);
/*  66 */     boolean inRangeYaw = false;
/*  67 */     boolean inRangePitch = false;
/*  68 */     if (night) ca -= 180; 
/*  69 */     if (ca > 90) {
/*  70 */       inRangeYaw = (Math.abs(Math.abs(yaw) - 180) < 10);
/*  71 */       inRangePitch = (Math.abs(180 - ca - pitch) < 7);
/*     */     } else {
/*  73 */       inRangeYaw = (Math.abs(yaw) < 10);
/*  74 */       inRangePitch = (Math.abs(ca - pitch) < 7);
/*     */     } 
/*     */     
/*  77 */     int worldDay = (int)(player.field_70170_p.func_82737_E() / 24000L);
/*     */     
/*  79 */     if (inRangeYaw && inRangePitch) {
/*  80 */       String pk = "CEL_" + worldDay + "_";
/*  81 */       String key = pk + (night ? ("Moon" + player.field_70170_p.field_73011_w.func_76559_b(player.field_70170_p.func_72820_D())) : "Sun");
/*  82 */       if (ThaumcraftCapabilities.knowsResearch(player, new String[] { key })) {
/*  83 */         player.func_146105_b(new TextComponentTranslation("tc.celestial.fail.1", new Object[] { "" }), true);
/*     */         return;
/*     */       } 
/*  86 */       if (InventoryUtils.isPlayerCarryingAmount(player, new ItemStack(ItemsTC.scribingTools, 1, 32767), true) && 
/*  87 */         InventoryUtils.consumePlayerItem(player, new ItemStack(Items.field_151121_aF), false, true)) {
/*  88 */         ItemStack stack = new ItemStack(ItemsTC.celestialNotes, 1, night ? (5 + player.field_70170_p.field_73011_w.func_76559_b(player.field_70170_p.func_72820_D())) : 0);
/*  89 */         if (!player.field_71071_by.func_70441_a(stack)) player.func_71019_a(stack, false); 
/*  90 */         ThaumcraftApi.internalMethods.progressResearch(player, key);
/*     */       } else {
/*  92 */         player.func_146105_b(new TextComponentTranslation("tc.celestial.fail.2", new Object[] { "" }), true);
/*     */       } 
/*     */ 
/*     */       
/*  96 */       cleanResearch(player, pk); return;
/*     */     } 
/*  98 */     if (night) {
/*     */       
/* 100 */       EnumFacing face = player.func_184172_bi();
/* 101 */       int num = face.func_176745_a() - 2;
/* 102 */       String pk = "CEL_" + worldDay + "_";
/* 103 */       String key = pk + "Star" + num;
/*     */       
/* 105 */       if (ThaumcraftCapabilities.knowsResearch(player, new String[] { key })) {
/* 106 */         player.func_146105_b(new TextComponentTranslation("tc.celestial.fail.1", new Object[] { "" }), true);
/*     */         return;
/*     */       } 
/* 109 */       if (InventoryUtils.isPlayerCarryingAmount(player, new ItemStack(ItemsTC.scribingTools, 1, 32767), true) && 
/* 110 */         InventoryUtils.consumePlayerItem(player, new ItemStack(Items.field_151121_aF), false, true)) {
/* 111 */         ItemStack stack = new ItemStack(ItemsTC.celestialNotes, 1, 1 + num);
/* 112 */         if (!player.field_71071_by.func_70441_a(stack)) player.func_71019_a(stack, false); 
/* 113 */         ThaumcraftApi.internalMethods.progressResearch(player, key);
/*     */       } else {
/* 115 */         player.func_146105_b(new TextComponentTranslation("tc.celestial.fail.2", new Object[] { "" }), true);
/*     */       } 
/*     */ 
/*     */       
/* 119 */       cleanResearch(player, pk);
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void cleanResearch(EntityPlayer player, String pk) {
/* 125 */     ArrayList<String> list = new ArrayList<String>();
/* 126 */     for (String key : ThaumcraftCapabilities.getKnowledge(player).getResearchList()) {
/* 127 */       if (key.startsWith("CEL_") && !key.startsWith(pk)) {
/* 128 */         list.add(key);
/*     */       }
/*     */     } 
/* 131 */     for (String key : list) {
/* 132 */       ThaumcraftCapabilities.getKnowledge(player).removeResearch(key);
/*     */     }
/* 134 */     ResearchManager.syncList.put(player.func_70005_c_(), Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 139 */   public String getResearchKey(EntityPlayer player, Object object) { return ""; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\ScanSky.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */