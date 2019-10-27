/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.EnumHelper;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.IWarpingGear;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ 
/*     */ 
/*     */ public class ItemPrimalCrusher
/*     */   extends ItemTool
/*     */   implements IWarpingGear, IThaumcraftItems
/*     */ {
/*  34 */   public static Item.ToolMaterial material = EnumHelper.addToolMaterial("PRIMALVOID", 5, 500, 8.0F, 4.0F, 20).setRepairItem(new ItemStack(ItemsTC.ingots, 1, 1));
/*     */ 
/*     */   
/*  37 */   private static final Set isEffective = Sets.newHashSet(new Block[] { Blocks.field_150347_e, Blocks.field_150334_T, Blocks.field_150333_U, Blocks.field_150348_b, Blocks.field_150322_A, Blocks.field_150341_Y, Blocks.field_150366_p, Blocks.field_150339_S, Blocks.field_150365_q, Blocks.field_150340_R, Blocks.field_150352_o, Blocks.field_150482_ag, Blocks.field_150484_ah, Blocks.field_150432_aD, Blocks.field_150424_aL, Blocks.field_150369_x, Blocks.field_150368_y, Blocks.field_150450_ax, Blocks.field_150439_ay, Blocks.field_150448_aq, Blocks.field_150319_E, Blocks.field_150318_D, Blocks.field_150408_cc, Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150431_aC, Blocks.field_150433_aE, Blocks.field_150435_aG, Blocks.field_150458_ak, Blocks.field_150425_aM, Blocks.field_150391_bh, BlocksTC.taintCrust, BlocksTC.taintRock, BlocksTC.taintSoil, BlocksTC.taintFeature, BlocksTC.taintLog, BlocksTC.taintFibre, Blocks.field_150343_Z });
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
/*     */   public ItemPrimalCrusher() {
/*  49 */     super(3.5F, -2.8F, material, isEffective);
/*  50 */     func_77637_a(ConfigItems.TABTC);
/*  51 */     setRegistryName("primal_crusher");
/*  52 */     func_77655_b("primal_crusher");
/*  53 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  58 */   public Item getItem() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_150897_b(IBlockState p_150897_1_) {
/*  84 */     return (p_150897_1_.func_185904_a() != Material.field_151575_d && p_150897_1_
/*  85 */       .func_185904_a() != Material.field_151584_j && p_150897_1_
/*  86 */       .func_185904_a() != Material.field_151585_k);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_150893_a(ItemStack stack, IBlockState state) {
/*  93 */     return (state.func_185904_a() != Material.field_151573_f && state.func_185904_a() != Material.field_151574_g && state
/*  94 */       .func_185904_a() != Material.field_151576_e) ? super
/*  95 */       .func_150893_a(stack, state) : this.field_77864_a;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public Set<String> getToolClasses(ItemStack stack) { return ImmutableSet.of("shovel", "pickaxe"); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isEffectiveAgainst(Block block) {
/* 106 */     for (Object b : isEffective) {
/* 107 */       if (b == block) {
/* 108 */         return true;
/*     */       }
/*     */     } 
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 116 */   public int func_77619_b() { return 20; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public int getWarp(ItemStack itemstack, EntityPlayer player) { return 2; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
/* 126 */     super.func_77663_a(stack, world, entity, p_77663_4_, p_77663_5_);
/*     */     
/* 128 */     if (stack.func_77951_h() && entity != null && entity.field_70173_aa % 20 == 0 && entity instanceof EntityLivingBase) {
/* 129 */       stack.func_77972_a(-1, (EntityLivingBase)entity);
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
/*     */ 
/*     */   
/*     */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/* 145 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/* 146 */       ItemStack w1 = new ItemStack(this);
/* 147 */       EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.DESTRUCTIVE, 1);
/* 148 */       EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.REFINING, 1);
/* 149 */       items.add(w1);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemPrimalCrusher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */