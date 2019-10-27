/*     */ package thaumcraft.common.items.baubles;
/*     */ 
/*     */ import baubles.api.BaubleType;
/*     */ import baubles.api.IBauble;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.items.IRechargable;
/*     */ import thaumcraft.api.items.RechargeHelper;
/*     */ import thaumcraft.api.potions.PotionFluxTaint;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ 
/*     */ 
/*     */ public class ItemVerdantCharm
/*     */   extends ItemTCBase
/*     */   implements IBauble, IRechargable
/*     */ {
/*     */   public ItemVerdantCharm() {
/*  35 */     super("verdant_charm", new String[0]);
/*  36 */     this.field_77777_bU = 1;
/*  37 */     this.canRepair = false;
/*  38 */     func_77656_e(0);
/*     */     
/*  40 */     func_185043_a(new ResourceLocation("type"), new IItemPropertyGetter()
/*     */         {
/*     */           @SideOnly(Side.CLIENT)
/*     */           public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
/*     */           {
/*  45 */             if (stack.func_77973_b() instanceof ItemVerdantCharm && stack.func_77942_o())
/*     */             {
/*  47 */               return stack.func_77978_p().func_74771_c("type");
/*     */             }
/*     */ 
/*     */             
/*  51 */             return 0.0F;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public BaubleType getBaubleType(ItemStack itemstack) { return BaubleType.CHARM; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/*  71 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/*  72 */       items.add(new ItemStack(this));
/*  73 */       ItemStack vhbl = new ItemStack(this);
/*  74 */       vhbl.func_77983_a("type", new NBTTagByte((byte)1));
/*  75 */       items.add(vhbl);
/*  76 */       ItemStack vhbl2 = new ItemStack(this);
/*  77 */       vhbl2.func_77983_a("type", new NBTTagByte((byte)2));
/*  78 */       items.add(vhbl2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/*  85 */     if (stack.func_77942_o() && stack.func_77978_p().func_74771_c("type") == 1) {
/*  86 */       tooltip.add(TextFormatting.GOLD + I18n.func_74838_a("item.verdant_charm.life.text"));
/*     */     }
/*  88 */     if (stack.func_77942_o() && stack.func_77978_p().func_74771_c("type") == 2) {
/*  89 */       tooltip.add(TextFormatting.GOLD + I18n.func_74838_a("item.verdant_charm.sustain.text"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
/*  96 */     if (!player.field_70170_p.field_72995_K && player.field_70173_aa % 20 == 0 && player instanceof EntityPlayer) {
/*     */       
/*  98 */       if (player.func_70660_b(MobEffects.field_82731_v) != null && 
/*  99 */         RechargeHelper.consumeCharge(itemstack, player, 20)) {
/* 100 */         player.func_184589_d(MobEffects.field_82731_v);
/*     */         return;
/*     */       } 
/* 103 */       if (player.func_70660_b(MobEffects.field_76436_u) != null && 
/* 104 */         RechargeHelper.consumeCharge(itemstack, player, 10)) {
/* 105 */         player.func_184589_d(MobEffects.field_76436_u);
/*     */         return;
/*     */       } 
/* 108 */       if (player.func_70660_b(PotionFluxTaint.instance) != null && 
/* 109 */         RechargeHelper.consumeCharge(itemstack, player, 5)) {
/* 110 */         player.func_184589_d(PotionFluxTaint.instance);
/*     */         return;
/*     */       } 
/* 113 */       if (itemstack.func_77942_o() && itemstack.func_77978_p().func_74771_c("type") == 1 && player
/* 114 */         .func_110143_aJ() < player.func_110138_aP() && 
/* 115 */         RechargeHelper.consumeCharge(itemstack, player, 5)) {
/* 116 */         player.func_70691_i(1.0F);
/*     */         return;
/*     */       } 
/* 119 */       if (itemstack.func_77942_o() && itemstack.func_77978_p().func_74771_c("type") == 2) {
/* 120 */         if (player.func_70086_ai() < 100 && RechargeHelper.consumeCharge(itemstack, player, 1)) {
/* 121 */           player.func_70050_g(300);
/*     */           return;
/*     */         } 
/* 124 */         if (player instanceof EntityPlayer && ((EntityPlayer)player).func_71043_e(false) && 
/* 125 */           RechargeHelper.consumeCharge(itemstack, player, 1)) {
/* 126 */           ((EntityPlayer)player).func_71024_bL().func_75122_a(1, 0.3F);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public int getMaxCharge(ItemStack stack, EntityLivingBase player) { return 200; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public IRechargable.EnumChargeDisplay showInHud(ItemStack stack, EntityLivingBase player) { return IRechargable.EnumChargeDisplay.NORMAL; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) { return true; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\baubles\ItemVerdantCharm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */