/*    */ package thaumcraft.common.items.consumables;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemAlumentum
/*    */   extends ItemTCBase
/*    */ {
/*    */   public ItemAlumentum() {
/* 17 */     super("alumentum", new String[0]);
/* 18 */     func_77625_d(64);
/* 19 */     func_77627_a(true);
/* 20 */     func_77656_e(0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
/* 26 */     if (!player.field_71075_bZ.field_75098_d)
/*    */     {
/* 28 */       player.func_184586_b(hand).func_190918_g(1);
/*    */     }
/* 30 */     player.func_184185_a(SoundEvents.field_187511_aA, 0.3F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/* 31 */     if (!world.field_72995_K) {
/*    */       
/* 33 */       EntityAlumentum alumentum = new EntityAlumentum(world, player);
/* 34 */       alumentum.func_184538_a(player, player.field_70125_A, player.field_70177_z, -5.0F, 0.4F, 2.0F);
/* 35 */       world.func_72838_d(alumentum);
/*    */     } 
/* 37 */     return new ActionResult(EnumActionResult.SUCCESS, player.func_184586_b(hand));
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemAlumentum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */