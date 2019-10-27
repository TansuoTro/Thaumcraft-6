/*     */ package thaumcraft.api.items;
/*     */ 
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aura.AuraHelper;
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
/*     */ public class RechargeHelper
/*     */ {
/*     */   public static final String NBT_TAG = "tc.charge";
/*     */   
/*     */   public static float rechargeItem(World world, ItemStack is, BlockPos pos, EntityPlayer player, int amt) {
/*  34 */     if (is == null || is.func_190926_b() || !(is.func_77973_b() instanceof IRechargable)) return 0.0F; 
/*  35 */     IRechargable chargeItem = (IRechargable)is.func_77973_b();
/*  36 */     if (player != null && AuraHelper.shouldPreserveAura(world, player, pos)) return 0.0F; 
/*  37 */     amt = Math.min(amt, chargeItem.getMaxCharge(is, player) - getCharge(is));
/*  38 */     int drained = (int)AuraHelper.drainVis(world, pos, amt, false);
/*  39 */     if (drained > 0) {
/*  40 */       addCharge(is, player, drained);
/*  41 */       return drained;
/*     */     } 
/*  43 */     return 0.0F;
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
/*     */   public static float rechargeItemBlindly(ItemStack is, EntityPlayer player, int amt) {
/*  55 */     if (is == null || is.func_190926_b() || !(is.func_77973_b() instanceof IRechargable)) return 0.0F; 
/*  56 */     IRechargable chargeItem = (IRechargable)is.func_77973_b();
/*  57 */     amt = Math.min(amt, chargeItem.getMaxCharge(is, player) - getCharge(is));
/*  58 */     if (amt > 0) addCharge(is, player, amt); 
/*  59 */     return amt;
/*     */   }
/*     */   
/*     */   private static void addCharge(ItemStack is, EntityLivingBase player, int amt) {
/*  63 */     if (is == null || is.func_190926_b() || !(is.func_77973_b() instanceof IRechargable))
/*  64 */       return;  IRechargable chargeItem = (IRechargable)is.func_77973_b();
/*  65 */     int amount = Math.min(chargeItem.getMaxCharge(is, player), amt + getCharge(is));
/*  66 */     is.func_77983_a("tc.charge", new NBTTagInt(amount));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCharge(ItemStack is) {
/*  74 */     if (is == null || is.func_190926_b() || !(is.func_77973_b() instanceof IRechargable)) return -1; 
/*  75 */     if (is.func_77942_o()) return is.func_77978_p().func_74762_e("tc.charge"); 
/*  76 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getChargePercentage(ItemStack is, EntityPlayer player) {
/*  85 */     if (is == null || is.func_190926_b() || !(is.func_77973_b() instanceof IRechargable)) return -1.0F; 
/*  86 */     float c = getCharge(is);
/*  87 */     float m = ((IRechargable)is.func_77973_b()).getMaxCharge(is, player);
/*  88 */     return c / m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean consumeCharge(ItemStack is, EntityLivingBase player, int amt) {
/*  99 */     if (is == null || is.func_190926_b() || !(is.func_77973_b() instanceof IRechargable)) return false; 
/* 100 */     if (is.func_77942_o()) {
/* 101 */       int charge = is.func_77978_p().func_74762_e("tc.charge");
/* 102 */       if (charge >= amt) {
/* 103 */         charge -= amt;
/* 104 */         is.func_77983_a("tc.charge", new NBTTagInt(charge));
/* 105 */         return true;
/*     */       } 
/*     */     } 
/* 108 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\items\RechargeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */