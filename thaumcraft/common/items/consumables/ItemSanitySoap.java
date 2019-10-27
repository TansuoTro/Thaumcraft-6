/*     */ package thaumcraft.common.items.consumables;
/*     */ 
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.ThaumcraftApi;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.potions.PotionWarpWard;
/*     */ 
/*     */ 
/*     */ public class ItemSanitySoap
/*     */   extends ItemTCBase
/*     */ {
/*     */   public ItemSanitySoap() {
/*  29 */     super("sanity_soap", new String[0]);
/*  30 */     func_77627_a(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public int func_77626_a(ItemStack p_77626_1_) { return 100; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public EnumAction func_77661_b(ItemStack p_77661_1_) { return EnumAction.BLOCK; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World p_77659_2_, EntityPlayer player, EnumHand hand) {
/*  54 */     player.func_184598_c(hand);
/*  55 */     return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
/*  62 */     int ticks = func_77626_a(stack) - count;
/*  63 */     if (ticks > 95) player.func_184597_cx(); 
/*  64 */     if (player.field_70170_p.field_72995_K) {
/*  65 */       if (player.field_70170_p.field_73012_v.nextFloat() < 0.2F)
/*  66 */         player.field_70170_p.func_184134_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187540_ab, SoundCategory.PLAYERS, 0.1F, 1.5F + player.field_70170_p.field_73012_v
/*  67 */             .nextFloat() * 0.2F, false); 
/*  68 */       for (int a = 0; a < 10; a++) {
/*  69 */         FXDispatcher.INSTANCE.crucibleBubble((float)player.field_70165_t - 0.5F + player.field_70170_p.field_73012_v
/*  70 */             .nextFloat(), 
/*  71 */             (float)(player.func_174813_aQ()).field_72338_b + player.field_70170_p.field_73012_v.nextFloat() * player.field_70131_O, (float)player.field_70161_v - 0.5F + player.field_70170_p.field_73012_v
/*  72 */             .nextFloat(), 1.0F, 0.8F, 0.9F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, EntityLivingBase player, int timeLeft) {
/*  81 */     int qq = func_77626_a(stack) - timeLeft;
/*  82 */     if (qq > 95 && player instanceof EntityPlayer) {
/*  83 */       stack.func_190918_g(1);
/*  84 */       if (!world.field_72995_K) {
/*  85 */         IPlayerWarp warp = ThaumcraftCapabilities.getWarp((EntityPlayer)player);
/*  86 */         int amt = 1;
/*  87 */         if (player.func_70644_a(PotionWarpWard.instance)) amt++; 
/*  88 */         int i = MathHelper.func_76128_c(player.field_70165_t);
/*  89 */         int j = MathHelper.func_76128_c(player.field_70163_u);
/*  90 */         int k = MathHelper.func_76128_c(player.field_70161_v);
/*  91 */         if (world.func_180495_p(new BlockPos(i, j, k)).func_177230_c() == BlocksTC.purifyingFluid) amt++; 
/*  92 */         if (warp.get(IPlayerWarp.EnumWarpType.NORMAL) > 0) {
/*  93 */           ThaumcraftApi.internalMethods.addWarpToPlayer((EntityPlayer)player, -amt, IPlayerWarp.EnumWarpType.NORMAL);
/*     */         }
/*  95 */         if (warp.get(IPlayerWarp.EnumWarpType.TEMPORARY) > 0) {
/*  96 */           ThaumcraftApi.internalMethods.addWarpToPlayer((EntityPlayer)player, -warp.get(IPlayerWarp.EnumWarpType.TEMPORARY), IPlayerWarp.EnumWarpType.TEMPORARY);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 101 */         player.field_70170_p.func_184134_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundsTC.craftstart, SoundCategory.PLAYERS, 0.25F, 1.0F, false);
/* 102 */         for (int a = 0; a < 40; a++)
/* 103 */           FXDispatcher.INSTANCE.crucibleBubble((float)player.field_70165_t - 0.5F + player.field_70170_p.field_73012_v
/* 104 */               .nextFloat() * 1.5F, 
/* 105 */               (float)(player.func_174813_aQ()).field_72338_b + player.field_70170_p.field_73012_v.nextFloat() * player.field_70131_O, (float)player.field_70161_v - 0.5F + player.field_70170_p.field_73012_v
/* 106 */               .nextFloat() * 1.5F, 1.0F, 0.7F, 0.9F); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemSanitySoap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */