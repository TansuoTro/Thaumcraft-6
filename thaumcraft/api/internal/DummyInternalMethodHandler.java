/*     */ package thaumcraft.api.internal;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DummyInternalMethodHandler
/*     */   implements IInternalMethodHandler
/*     */ {
/*  21 */   public boolean completeResearch(EntityPlayer player, String researchkey) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addWarpToPlayer(EntityPlayer player, int amount, IPlayerWarp.EnumWarpType type) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public AspectList getObjectAspects(ItemStack is) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public AspectList generateTags(ItemStack is) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public float drainVis(World world, BlockPos pos, float amount, boolean simulate) { return 0.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public float drainFlux(World world, BlockPos pos, float amount, boolean simulate) { return 0.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVis(World world, BlockPos pos, float amount) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFlux(World world, BlockPos pos, float amount, boolean showEffect) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public float getTotalAura(World world, BlockPos pos) { return 0.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public float getVis(World world, BlockPos pos) { return 0.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public float getFlux(World world, BlockPos pos) { return 0.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public int getAuraBase(World world, BlockPos pos) { return 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSeal(ISeal seal) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public ISeal getSeal(String key) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public ISealEntity getSealEntity(int dim, SealPos pos) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addGolemTask(int dim, Task task) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public boolean shouldPreserveAura(World world, EntityPlayer player, BlockPos pos) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public ItemStack getSealStack(String key) { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public boolean doesPlayerHaveRequisites(EntityPlayer player, String researchkey) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public boolean addKnowledge(EntityPlayer player, IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory field, int amount) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public boolean progressResearch(EntityPlayer player, String researchkey) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public int getActualWarp(EntityPlayer player) { return 0; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\internal\DummyInternalMethodHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */