/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectHelper;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScanAspect
/*    */   implements IScanThing
/*    */ {
/*    */   String research;
/*    */   Aspect aspect;
/*    */   
/*    */   public ScanAspect(String research, Aspect aspect) {
/* 26 */     this.research = research;
/* 27 */     this.aspect = aspect;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj) {
/* 32 */     if (obj == null) return false;
/*    */     
/* 34 */     AspectList al = null;
/*    */     
/* 36 */     if (obj instanceof Entity && !(obj instanceof EntityItem)) {
/* 37 */       al = AspectHelper.getEntityAspects((Entity)obj);
/*    */     } else {
/* 39 */       ItemStack is = null;
/* 40 */       if (obj instanceof ItemStack)
/* 41 */         is = (ItemStack)obj; 
/* 42 */       if (obj instanceof EntityItem && ((EntityItem)obj).func_92059_d() != null)
/* 43 */         is = ((EntityItem)obj).func_92059_d(); 
/* 44 */       if (obj instanceof BlockPos) {
/* 45 */         Block b = player.field_70170_p.func_180495_p((BlockPos)obj).func_177230_c();
/* 46 */         is = new ItemStack(b, 1, b.func_176201_c(player.field_70170_p.func_180495_p((BlockPos)obj)));
/*    */       } 
/*    */       
/* 49 */       if (is != null) {
/* 50 */         al = AspectHelper.getObjectAspects(is);
/*    */       }
/*    */     } 
/*    */     
/* 54 */     return (al != null && al.getAmount(this.aspect) > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSuccess(EntityPlayer player, Object obj) {
/* 59 */     ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("AUROMANCY"), 1);
/* 60 */     ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("BASICS"), 1);
/* 61 */     ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ALCHEMY"), 1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public String getResearchKey(EntityPlayer player, Object object) { return this.research; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ScanAspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */