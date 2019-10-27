/*    */ package thaumcraft.common.items.baubles;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.common.ForgeHooks;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ import thaumcraft.common.lib.network.PacketHandler;
/*    */ import thaumcraft.common.lib.network.playerdata.PacketPlayerFlagToServer;
/*    */ 
/*    */ public class ItemCloudRing
/*    */   extends ItemTCBase
/*    */   implements IBauble {
/*    */   public ItemCloudRing() {
/* 25 */     super("cloud_ring", new String[0]);
/* 26 */     this.field_77777_bU = 1;
/* 27 */     this.canRepair = false;
/* 28 */     func_77656_e(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public BaubleType getBaubleType(ItemStack itemstack) { return BaubleType.RING; }
/*    */ 
/*    */   
/* 41 */   public static HashMap<String, Boolean> jumpList = new HashMap();
/*    */ 
/*    */ 
/*    */   
/*    */   public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
/* 46 */     if ((player.func_130014_f_()).field_72995_K) {
/* 47 */       boolean spacePressed = (Minecraft.func_71410_x()).field_71474_y.field_74314_A.func_151468_f();
/* 48 */       if (spacePressed && !jumpList.containsKey(player.func_70005_c_())) {
/* 49 */         jumpList.put(player.func_70005_c_(), Boolean.valueOf(true));
/*    */       }
/*    */       
/* 52 */       if (spacePressed && !player.field_70122_E && !player.func_70090_H() && player.field_70773_bE == 0 && jumpList
/* 53 */         .containsKey(player.func_70005_c_()) && ((Boolean)jumpList
/* 54 */         .get(player.func_70005_c_())).booleanValue() == true) {
/* 55 */         FXDispatcher.INSTANCE.drawBamf(player.field_70165_t, player.field_70163_u + 0.5D, player.field_70161_v, 1.0F, 1.0F, 1.0F, false, false, EnumFacing.UP);
/*    */         
/* 57 */         player.func_130014_f_().func_184134_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187730_dW, SoundCategory.PLAYERS, 0.1F, 1.0F + 
/* 58 */             (float)(player.func_130014_f_()).field_73012_v.nextGaussian() * 0.05F, false);
/*    */         
/* 60 */         jumpList.put(player.func_70005_c_(), Boolean.valueOf(false));
/*    */         
/* 62 */         player.field_70181_x = 0.75D;
/* 63 */         if (player.func_70644_a(MobEffects.field_76430_j))
/*    */         {
/* 65 */           player.field_70181_x += ((player.func_70660_b(MobEffects.field_76430_j).func_76458_c() + 1) * 0.1F);
/*    */         }
/*    */         
/* 68 */         if (player.func_70051_ag()) {
/*    */           
/* 70 */           float f = player.field_70177_z * 0.017453292F;
/* 71 */           player.field_70159_w -= (MathHelper.func_76126_a(f) * 0.2F);
/* 72 */           player.field_70179_y += (MathHelper.func_76134_b(f) * 0.2F);
/*    */         } 
/* 74 */         player.field_70143_R = 0.0F;
/* 75 */         PacketHandler.INSTANCE.sendToServer(new PacketPlayerFlagToServer(player, 1));
/* 76 */         ForgeHooks.onLivingJump(player);
/*    */       } 
/*    */       
/* 79 */       if (player.field_70122_E && player.field_70773_bE == 0)
/* 80 */         jumpList.remove(player.func_70005_c_()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\baubles\ItemCloudRing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */