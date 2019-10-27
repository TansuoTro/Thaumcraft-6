package thaumcraft.api.internal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.golems.seals.ISeal;
import thaumcraft.api.golems.seals.ISealEntity;
import thaumcraft.api.golems.seals.SealPos;
import thaumcraft.api.golems.tasks.Task;
import thaumcraft.api.research.ResearchCategory;

public interface IInternalMethodHandler {
  boolean addKnowledge(EntityPlayer paramEntityPlayer, IPlayerKnowledge.EnumKnowledgeType paramEnumKnowledgeType, ResearchCategory paramResearchCategory, int paramInt);
  
  boolean progressResearch(EntityPlayer paramEntityPlayer, String paramString);
  
  boolean completeResearch(EntityPlayer paramEntityPlayer, String paramString);
  
  boolean doesPlayerHaveRequisites(EntityPlayer paramEntityPlayer, String paramString);
  
  void addWarpToPlayer(EntityPlayer paramEntityPlayer, int paramInt, IPlayerWarp.EnumWarpType paramEnumWarpType);
  
  int getActualWarp(EntityPlayer paramEntityPlayer);
  
  AspectList getObjectAspects(ItemStack paramItemStack);
  
  AspectList generateTags(ItemStack paramItemStack);
  
  float drainVis(World paramWorld, BlockPos paramBlockPos, float paramFloat, boolean paramBoolean);
  
  float drainFlux(World paramWorld, BlockPos paramBlockPos, float paramFloat, boolean paramBoolean);
  
  void addVis(World paramWorld, BlockPos paramBlockPos, float paramFloat);
  
  void addFlux(World paramWorld, BlockPos paramBlockPos, float paramFloat, boolean paramBoolean);
  
  float getTotalAura(World paramWorld, BlockPos paramBlockPos);
  
  float getVis(World paramWorld, BlockPos paramBlockPos);
  
  float getFlux(World paramWorld, BlockPos paramBlockPos);
  
  int getAuraBase(World paramWorld, BlockPos paramBlockPos);
  
  void registerSeal(ISeal paramISeal);
  
  ISeal getSeal(String paramString);
  
  ISealEntity getSealEntity(int paramInt, SealPos paramSealPos);
  
  void addGolemTask(int paramInt, Task paramTask);
  
  boolean shouldPreserveAura(World paramWorld, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos);
  
  ItemStack getSealStack(String paramString);
}


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\internal\IInternalMethodHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */