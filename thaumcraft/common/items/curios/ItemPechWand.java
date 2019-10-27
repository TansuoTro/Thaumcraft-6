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
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemPechWand
/*     */   extends ItemTCBase
/*     */ {
/*  33 */   public ItemPechWand() { super("pech_wand", new String[0]); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*  45 */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) { tooltip.add(I18n.func_74838_a("item.curio.text")); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer player, EnumHand hand) {
/*  88 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/*     */     
/*  90 */     if (!knowledge.isResearchKnown("BASEAUROMANCY")) {
/*  91 */       if (!worldIn.field_72995_K)
/*  92 */         player.func_145747_a(new TextComponentString(TextFormatting.RED + I18n.func_74838_a("not.pechwand"))); 
/*  93 */       return super.func_77659_a(worldIn, player, hand);
/*     */     } 
/*     */     
/*  96 */     if (!player.field_71075_bZ.field_75098_d)
/*     */     {
/*  98 */       player.func_184586_b(hand).func_190918_g(1);
/*     */     }
/*     */     
/* 101 */     worldIn.func_184148_a((EntityPlayer)null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundsTC.learn, SoundCategory.NEUTRAL, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*     */     
/* 103 */     if (!worldIn.field_72995_K) {
/*     */       
/* 105 */       if (!knowledge.isResearchKnown("FOCUSPECH")) {
/* 106 */         ThaumcraftApi.internalMethods.progressResearch(player, "FOCUSPECH");
/* 107 */         player.func_145747_a(new TextComponentString(TextFormatting.DARK_PURPLE + I18n.func_74838_a("got.pechwand")));
/*     */       } 
/*     */       
/* 110 */       int oProg = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
/* 111 */       ResearchCategory[] rc = (ResearchCategory[])ResearchCategories.researchCategories.values().toArray(new ResearchCategory[0]);
/* 112 */       ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc[player.func_70681_au().nextInt(rc.length)], MathHelper.func_76136_a(player.func_70681_au(), oProg / 3, oProg / 2));
/* 113 */       int tProg = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();
/* 114 */       ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, rc[player.func_70681_au().nextInt(rc.length)], MathHelper.func_76136_a(player.func_70681_au(), tProg / 5, tProg / 4));
/*     */     } 
/*     */ 
/*     */     
/* 118 */     player.func_71029_a(StatList.func_188057_b(this));
/*     */     
/* 120 */     return super.func_77659_a(worldIn, player, hand);
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\curios\ItemPechWand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */