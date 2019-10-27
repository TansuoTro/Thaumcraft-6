/*     */ package thaumcraft.common.items.curios;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextComponentString;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemCurio
/*     */   extends ItemTCBase
/*     */ {
/*  38 */   public ItemCurio() { super("curio", new String[] { "arcane", "preserved", "ancient", "eldritch", "knowledge", "twisted", "rites" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  50 */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) { tooltip.add(I18n.func_74838_a("item.curio.text")); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer player, EnumHand hand) {
/*  57 */     worldIn.func_184148_a((EntityPlayer)null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundsTC.learn, SoundCategory.NEUTRAL, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*     */     
/*  59 */     if (!worldIn.field_72995_K) {
/*     */ 
/*     */       
/*  62 */       int aw, oProg = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
/*  63 */       int tProg = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();
/*     */       
/*  65 */       switch (player.func_184586_b(hand).func_77952_i()) {
/*     */         default:
/*  67 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("AUROMANCY"), MathHelper.func_76136_a(player.func_70681_au(), oProg / 2, oProg));
/*  68 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("AUROMANCY"), MathHelper.func_76136_a(player.func_70681_au(), tProg / 3, tProg / 2));
/*     */           break;
/*     */         case 1:
/*  71 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ALCHEMY"), MathHelper.func_76136_a(player.func_70681_au(), oProg / 2, oProg));
/*  72 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("ALCHEMY"), MathHelper.func_76136_a(player.func_70681_au(), tProg / 3, tProg / 2));
/*     */           break;
/*     */         case 2:
/*  75 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("GOLEMANCY"), MathHelper.func_76136_a(player.func_70681_au(), oProg / 2, oProg));
/*  76 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("GOLEMANCY"), MathHelper.func_76136_a(player.func_70681_au(), tProg / 3, tProg / 2));
/*     */           break;
/*     */         case 3:
/*  79 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ELDRITCH"), MathHelper.func_76136_a(player.func_70681_au(), oProg / 2, oProg));
/*  80 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("ELDRITCH"), MathHelper.func_76136_a(player.func_70681_au(), tProg / 3, tProg / 2));
/*  81 */           ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, IPlayerWarp.EnumWarpType.NORMAL);
/*  82 */           ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, IPlayerWarp.EnumWarpType.TEMPORARY);
/*     */           break;
/*     */         case 4:
/*  85 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("INFUSION"), MathHelper.func_76136_a(player.func_70681_au(), oProg / 2, oProg));
/*  86 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("INFUSION"), MathHelper.func_76136_a(player.func_70681_au(), tProg / 3, tProg / 2));
/*     */           break;
/*     */         case 5:
/*  89 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ARTIFICE"), MathHelper.func_76136_a(player.func_70681_au(), oProg / 2, oProg));
/*  90 */           ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("ARTIFICE"), MathHelper.func_76136_a(player.func_70681_au(), tProg / 3, tProg / 2));
/*     */           break;
/*     */         case 6:
/*  93 */           aw = ThaumcraftApi.internalMethods.getActualWarp(player);
/*  94 */           if (aw > 20) {
/*  95 */             IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/*  96 */             if (!knowledge.isResearchKnown("CrimsonRites")) {
/*  97 */               ThaumcraftApi.internalMethods.completeResearch(player, "CrimsonRites");
/*     */             }
/*  99 */             ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ELDRITCH"), MathHelper.func_76136_a(player.func_70681_au(), oProg / 2, oProg));
/* 100 */             ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, ResearchCategories.getResearchCategory("ELDRITCH"), MathHelper.func_76136_a(player.func_70681_au(), tProg / 3, tProg / 2));
/* 101 */             ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, IPlayerWarp.EnumWarpType.NORMAL);
/* 102 */             ThaumcraftApi.internalMethods.addWarpToPlayer(player, 5, IPlayerWarp.EnumWarpType.TEMPORARY);
/* 103 */             if (player.func_70681_au().nextBoolean()) ThaumcraftApi.internalMethods.addWarpToPlayer(player, 1, IPlayerWarp.EnumWarpType.PERMANENT);  break;
/*     */           } 
/* 105 */           player.func_145747_a(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("fail.crimsonrites")));
/* 106 */           return super.func_77659_a(worldIn, player, hand);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 111 */       ResearchCategory[] rc = (ResearchCategory[])ResearchCategories.researchCategories.values().toArray(new ResearchCategory[0]);
/* 112 */       ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc[player.func_70681_au().nextInt(rc.length)], MathHelper.func_76136_a(player.func_70681_au(), oProg / 2, oProg));
/* 113 */       ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, rc[player.func_70681_au().nextInt(rc.length)], MathHelper.func_76136_a(player.func_70681_au(), tProg / 3, tProg / 2));
/*     */       
/* 115 */       if (!player.field_71075_bZ.field_75098_d) {
/* 116 */         player.func_184586_b(hand).func_190918_g(1);
/*     */       }
/* 118 */       player.func_145747_a(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("tc.knowledge.gained")));
/*     */     } 
/*     */     
/* 121 */     player.func_71029_a(StatList.func_188057_b(this));
/*     */     
/* 123 */     return super.func_77659_a(worldIn, player, hand);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\curios\ItemCurio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */