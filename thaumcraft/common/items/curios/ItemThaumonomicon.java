/*     */ package thaumcraft.common.items.curios;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.api.research.ResearchEntry;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.config.ModConfig;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.lib.CommandThaumcraft;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.research.ResearchManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemThaumonomicon
/*     */   extends ItemTCBase
/*     */ {
/*     */   public ItemThaumonomicon() {
/*  38 */     super("thaumonomicon", new String[] { "normal", "cheat" });
/*  39 */     func_77627_a(true);
/*  40 */     func_77625_d(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/*  45 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/*  46 */       items.add(new ItemStack(this, 1, 0));
/*  47 */       if (ModConfig.CONFIG_MISC.allowCheatSheet) items.add(new ItemStack(this, 1, 1));
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/*  54 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/*  55 */     if (stack.func_77952_i() == 1) tooltip.add(TextFormatting.DARK_PURPLE + "Creative only");
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
/*  62 */     if (!world.field_72995_K) {
/*  63 */       if (ModConfig.CONFIG_MISC.allowCheatSheet && player.func_184586_b(hand).func_77952_i() == 1) {
/*  64 */         Collection<ResearchCategory> rc = ResearchCategories.researchCategories.values();
/*  65 */         for (ResearchCategory cat : rc) {
/*  66 */           Collection<ResearchEntry> rl = cat.research.values();
/*  67 */           for (ResearchEntry ri : rl) {
/*  68 */             CommandThaumcraft.giveRecursiveResearch(player, ri.getKey());
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  74 */         Collection<ResearchCategory> rc = ResearchCategories.researchCategories.values();
/*  75 */         for (ResearchCategory cat : rc) {
/*  76 */           Collection<ResearchEntry> rl = cat.research.values();
/*  77 */           for (ResearchEntry ri : rl) {
/*  78 */             if (ThaumcraftCapabilities.knowsResearch(player, new String[] { ri.getKey()
/*  79 */                 }) && ri.getSiblings() != null) {
/*  80 */               for (String sib : ri.getSiblings()) {
/*  81 */                 if (!ThaumcraftCapabilities.knowsResearch(player, new String[] { sib })) {
/*  82 */                   ResearchManager.completeResearch(player, sib);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  90 */       ThaumcraftCapabilities.getKnowledge(player).sync((EntityPlayerMP)player);
/*     */     } else {
/*  92 */       world.func_184134_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundsTC.page, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
/*     */     } 
/*  94 */     player.openGui(Thaumcraft.instance, 12, world, 0, 0, 0);
/*  95 */     return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public EnumRarity func_77613_e(ItemStack itemstack) { return (itemstack.func_77952_i() != 1) ? EnumRarity.UNCOMMON : EnumRarity.EPIC; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\curios\ItemThaumonomicon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */