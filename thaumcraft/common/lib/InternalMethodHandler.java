/*     */ package thaumcraft.common.lib;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.api.internal.IInternalMethodHandler;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.common.golems.seals.ItemSealPlacer;
/*     */ import thaumcraft.common.golems.seals.SealHandler;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.fx.PacketFXPollute;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ import thaumcraft.common.world.aura.AuraHandler;
/*     */ 
/*     */ 
/*     */ public class InternalMethodHandler
/*     */   implements IInternalMethodHandler
/*     */ {
/*     */   public boolean addKnowledge(EntityPlayer player, IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory field, int amount) {
/*  35 */     if (amount == 0 || player.field_70170_p.field_72995_K) return false; 
/*  36 */     return ResearchManager.addKnowledge(player, type, field, amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addWarpToPlayer(EntityPlayer player, int amount, IPlayerWarp.EnumWarpType type) {
/*  41 */     if (amount == 0 || player.field_70170_p.field_72995_K)
/*  42 */       return;  IPlayerWarp pw = ThaumcraftCapabilities.getWarp(player);
/*  43 */     int cur = pw.get(type);
/*  44 */     if (amount < 0 && cur + amount < 0) amount = cur; 
/*  45 */     pw.add(type, amount);
/*     */     
/*  47 */     if (type == IPlayerWarp.EnumWarpType.PERMANENT) {
/*  48 */       PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)0, amount), (EntityPlayerMP)player);
/*     */     }
/*  50 */     if (type == IPlayerWarp.EnumWarpType.NORMAL) {
/*  51 */       PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)1, amount), (EntityPlayerMP)player);
/*     */     }
/*  53 */     if (type == IPlayerWarp.EnumWarpType.TEMPORARY) {
/*  54 */       PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte)2, amount), (EntityPlayerMP)player);
/*     */     }
/*     */     
/*  57 */     if (amount > 0) pw.setCounter(pw.get(IPlayerWarp.EnumWarpType.TEMPORARY) + pw.get(IPlayerWarp.EnumWarpType.PERMANENT) + pw.get(IPlayerWarp.EnumWarpType.NORMAL));
/*     */     
/*  59 */     if (type != IPlayerWarp.EnumWarpType.TEMPORARY && ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { "FIRSTSTEPS" }) && !ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { "WARP" })) {
/*  60 */       completeResearch(player, "WARP");
/*  61 */       player.func_146105_b(new TextComponentTranslation("research.WARP.warn", new Object[0]), true);
/*     */     } 
/*     */     
/*  64 */     pw.sync((EntityPlayerMP)player);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean progressResearch(EntityPlayer player, String researchkey) {
/*  69 */     if (researchkey == null || player.field_70170_p.field_72995_K) return false; 
/*  70 */     return ResearchManager.progressResearch(player, researchkey);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean completeResearch(EntityPlayer player, String researchkey) {
/*  75 */     if (researchkey == null || player.field_70170_p.field_72995_K) return false; 
/*  76 */     return ResearchManager.completeResearch(player, researchkey);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public boolean doesPlayerHaveRequisites(EntityPlayer player, String researchkey) { return ResearchManager.doesPlayerHaveRequisites(player, researchkey); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public AspectList getObjectAspects(ItemStack is) { return ThaumcraftCraftingManager.getObjectTags(is); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public AspectList generateTags(ItemStack is) { return ThaumcraftCraftingManager.generateTags(is); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public float drainFlux(World world, BlockPos pos, float amount, boolean simulate) { return AuraHandler.drainFlux(world, pos, amount, simulate); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public float getFlux(World world, BlockPos pos) { return AuraHandler.getFlux(world, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public float drainVis(World world, BlockPos pos, float amount, boolean simulate) { return AuraHandler.drainVis(world, pos, amount, simulate); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void addVis(World world, BlockPos pos, float amount) { AuraHandler.addVis(world, pos, amount); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public float getTotalAura(World world, BlockPos pos) { return AuraHandler.getTotalAura(world, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public float getVis(World world, BlockPos pos) { return AuraHandler.getVis(world, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int getAuraBase(World world, BlockPos pos) { return AuraHandler.getAuraBase(world, pos); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFlux(World world, BlockPos pos, float amount, boolean showEffect) {
/* 132 */     if (world.field_72995_K)
/* 133 */       return;  AuraHandler.addFlux(world, pos, amount);
/* 134 */     if (showEffect && amount > 0.0F) {
/* 135 */       PacketHandler.INSTANCE.sendToAllAround(new PacketFXPollute(pos, amount), new NetworkRegistry.TargetPoint(world.field_73011_w
/* 136 */             .getDimension(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), 32.0D));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public void registerSeal(ISeal seal) { SealHandler.registerSeal(seal); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public ISeal getSeal(String key) { return SealHandler.getSeal(key); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public ISealEntity getSealEntity(int dim, SealPos pos) { return SealHandler.getSealEntity(dim, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public void addGolemTask(int dim, Task task) { TaskHandler.addTask(dim, task); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public boolean shouldPreserveAura(World world, EntityPlayer player, BlockPos pos) { return AuraHandler.shouldPreserveAura(world, player, pos); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   public ItemStack getSealStack(String key) { return ItemSealPlacer.getSealStack(key); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActualWarp(EntityPlayer player) {
/* 173 */     IPlayerWarp wc = ThaumcraftCapabilities.getWarp(player);
/* 174 */     return wc.get(IPlayerWarp.EnumWarpType.NORMAL) + wc.get(IPlayerWarp.EnumWarpType.PERMANENT);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\InternalMethodHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */