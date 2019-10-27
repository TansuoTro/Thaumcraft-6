/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.entities.projectile.EntityBottleTaint;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemBottleTaint
/*    */   extends ItemTCBase
/*    */ {
/*    */   public ItemBottleTaint() {
/* 18 */     super("bottle_taint", new String[0]);
/* 19 */     this.field_77777_bU = 8;
/* 20 */     func_77656_e(0);
/* 21 */     func_77637_a(ConfigItems.TABTC);
/* 22 */     func_77627_a(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
/* 29 */     if (!player.field_71075_bZ.field_75098_d)
/*    */     {
/* 31 */       player.func_184586_b(hand).func_190918_g(1);
/*    */     }
/*    */     
/* 34 */     player.func_184185_a(SoundEvents.field_187511_aA, 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*    */     
/* 36 */     if (!world.field_72995_K) {
/*    */       
/* 38 */       EntityBottleTaint entityBottle = new EntityBottleTaint(world, player);
/* 39 */       entityBottle.func_184538_a(player, player.field_70125_A, player.field_70177_z, -5.0F, 0.66F, 1.0F);
/* 40 */       world.func_72838_d(entityBottle);
/*    */     } 
/*    */     
/* 43 */     return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemBottleTaint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */