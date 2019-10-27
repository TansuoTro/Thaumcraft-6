/*     */ package thaumcraft.api.casters;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
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
/*     */ public class CasterTriggerRegistry
/*     */ {
/*  25 */   private static HashMap<String, LinkedHashMap<IBlockState, List<Trigger>>> triggers = new HashMap();
/*     */   private static final String DEFAULT = "default";
/*     */   
/*     */   private static class Trigger {
/*     */     ICasterTriggerManager manager;
/*     */     int event;
/*     */     
/*     */     public Trigger(ICasterTriggerManager manager, int event) {
/*  33 */       this.manager = manager;
/*  34 */       this.event = event;
/*     */     }
/*     */   }
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
/*     */   public static void registerWandBlockTrigger(ICasterTriggerManager manager, int event, IBlockState state, String modid) {
/*  48 */     if (!triggers.containsKey(modid)) {
/*  49 */       triggers.put(modid, new LinkedHashMap());
/*     */     }
/*  51 */     LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/*  52 */     List<Trigger> ts = (List)temp.get(state);
/*  53 */     if (ts == null) ts = new ArrayList<Trigger>(); 
/*  54 */     ts.add(new Trigger(manager, event));
/*  55 */     temp.put(state, ts);
/*  56 */     triggers.put(modid, temp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static void registerCasterBlockTrigger(ICasterTriggerManager manager, int event, IBlockState state) { registerWandBlockTrigger(manager, event, state, "default"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasTrigger(IBlockState state) {
/*  73 */     for (String modid : triggers.keySet()) {
/*  74 */       LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/*  75 */       if (temp.containsKey(state)) return true; 
/*     */     } 
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasTrigger(IBlockState state, String modid) {
/*  84 */     if (!triggers.containsKey(modid)) return false; 
/*  85 */     LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/*  86 */     if (temp.containsKey(state)) return true; 
/*  87 */     return false;
/*     */   }
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
/*     */   public static boolean performTrigger(World world, ItemStack casterStack, EntityPlayer player, BlockPos pos, EnumFacing side, IBlockState state) {
/* 107 */     for (String modid : triggers.keySet()) {
/* 108 */       LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/* 109 */       List<Trigger> l = (List)temp.get(state);
/* 110 */       if (l == null || l.size() == 0)
/* 111 */         continue;  for (Trigger trig : l) {
/* 112 */         boolean result = trig.manager.performTrigger(world, casterStack, player, pos, side, trig.event);
/* 113 */         if (result) return true; 
/*     */       } 
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean performTrigger(World world, ItemStack casterStack, EntityPlayer player, BlockPos pos, EnumFacing side, IBlockState state, String modid) {
/* 124 */     if (!triggers.containsKey(modid)) return false; 
/* 125 */     LinkedHashMap<IBlockState, List<Trigger>> temp = (LinkedHashMap)triggers.get(modid);
/* 126 */     List<Trigger> l = (List)temp.get(state);
/* 127 */     if (l == null || l.size() == 0) return false; 
/* 128 */     for (Trigger trig : l) {
/* 129 */       boolean result = trig.manager.performTrigger(world, casterStack, player, pos, side, trig.event);
/* 130 */       if (result) return true; 
/*     */     } 
/* 132 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\CasterTriggerRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */